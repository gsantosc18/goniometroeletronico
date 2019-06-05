﻿//
// Copyright (c) LightBuzz Software.
// All rights reserved.
//
// http://lightbuzz.com
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
//
// 1. Redistributions of source code must retain the above copyright
//    notice, this list of conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright
//    notice, this list of conditions and the following disclaimer in the
//    documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
// FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
// COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
// INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
// BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
// OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
// AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
// LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
// WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.
//

using Microsoft.Kinect;
using System.Linq;
using System.Collections.Generic;

namespace LightBuzz.Vitruvius
{
    /// <summary>
    /// Provides some common functionality for manupulating body data.
    /// </summary>
    public static class BodyExtensions
    {
        #region Members

        /// <summary>
        /// The body collection a Kinect sensor can recognize.
        /// </summary>
        static IList<Body> _bodies = null;

        /// <summary>
        /// The frame capture utility.
        /// </summary>
        static FrameCapture _capture = new FrameCapture();

        #endregion

        #region Public methods

        /// <summary>
        /// Returns the bodies found in the current frame.
        /// </summary>
        /// <param name="frame">The BodyFrame generated by the Kinect sensor.</param>
        /// <returns>An array of bodies or an empty array if no bodies were found.</returns>
        public static IEnumerable<Body> Bodies(this BodyFrame frame)
        {
            if (_bodies == null)
            {
                _bodies = new Body[frame.BodyFrameSource.BodyCount];
            }

            frame.GetAndRefreshBodyData(_bodies);

            return _bodies;
        }

        /// <summary>
        /// Returns the body that is currently to the sensor.
        /// </summary>
        /// <param name="bodies">A list of bodies to look at.</param>
        /// <returns>The first tracked body.</returns>
        public static Body Closest(this IEnumerable<Body> bodies)
        {
            Body result = null;
            double closestBodyDistance = double.MaxValue;

            foreach (var body in bodies)
            {
                if (body.IsTracked)
                {
                    var position = body.Joints[JointType.SpineBase].Position;
                    var distance = position.Length();

                    if (result == null || distance < closestBodyDistance)
                    {
                        result = body;
                        closestBodyDistance = distance;
                    }
                }
            }

            return result;
        }

        /// <summary>
        /// Determines whether the current body is stable, without leaning.
        /// </summary>
        /// <param name="body">The body to examine.</param>
        /// <param name="thresholdX">The accepted lean bound in the X axis. Defaults to 0.2</param>
        /// <param name="thresholdY">The accepted lean bound in the Y axis. Defaults to 0.3.</param>
        /// <returns>True if the body is stable. False if the body is leaning on one side.</returns>
        public static bool IsStable(this Body body, float thresholdX = 0.2f, float thresholdY = 0.3f)
        {
            if (body.LeanTrackingState == TrackingState.Tracked)
            {
                if (body.Lean.X >= -thresholdX && body.Lean.X <= thresholdX &&
                    body.Lean.Y >= -thresholdY && body.Lean.Y <= thresholdY)
                {
                    return true;
                }
            }

            return false;
        }

        /// <summary>
        /// Retruns the height of the current body.
        /// </summary>
        /// <param name="body">The specified user body.</param>
        /// <returns>The height of the body in meters.</returns>
        public static double Height(this Body body)
        {
            const double HEAD_DIVERGENCE = 0.1;

            var head = body.Joints[JointType.Head];
            var neck = body.Joints[JointType.Neck];
            var shoulders = body.Joints[JointType.SpineShoulder];
            var spine = body.Joints[JointType.SpineMid];
            var waist = body.Joints[JointType.SpineBase];
            var hipLeft = body.Joints[JointType.HipLeft];
            var hipRight = body.Joints[JointType.HipRight];
            var kneeLeft = body.Joints[JointType.KneeLeft];
            var kneeRight = body.Joints[JointType.KneeRight];
            var ankleLeft = body.Joints[JointType.AnkleLeft];
            var ankleRight = body.Joints[JointType.AnkleRight];
            var footLeft = body.Joints[JointType.FootLeft];
            var footRight = body.Joints[JointType.FootRight];

            // Find which leg is tracked more accurately.
            int legLeftTrackedJoints = NumberOfTrackedJoints(hipLeft, kneeLeft, ankleLeft, footLeft);
            int legRightTrackedJoints = NumberOfTrackedJoints(hipRight, kneeRight, ankleRight, footRight);

            double legLength = legLeftTrackedJoints > legRightTrackedJoints ?
                MathExtensions.Length(hipLeft.Position, kneeLeft.Position, ankleLeft.Position, footLeft.Position) :
                MathExtensions.Length(hipRight.Position, kneeRight.Position, ankleRight.Position, footRight.Position);

            return MathExtensions.Length(head.Position, neck.Position, shoulders.Position, spine.Position, waist.Position) + legLength + HEAD_DIVERGENCE;
        }

        /// <summary>
        /// Returns the upper height of the current body (head to waist).
        /// </summary>
        /// <param name="body">A user body.</param>
        /// <returns>The upper height of the body in meters.</returns>
        public static double UpperHeight(this Body body)
        {
            var head = body.Joints[JointType.Head].Position;
            var neck = body.Joints[JointType.Neck].Position;
            var shoulders = body.Joints[JointType.SpineShoulder].Position;
            var spine = body.Joints[JointType.SpineMid].Position;
            var waist = body.Joints[JointType.SpineBase].Position;

            return MathExtensions.Length(head, neck, shoulders, spine, waist);
        }

        /// <summary>
        /// Returns a collection of the tracked joints of the current body.
        /// </summary>
        /// <param name="body">A user body.</param>
        /// <param name="includeInferred">True to include the joints with a TrackingState of Tracked or Inferred. False to include only the joints with a TrackingState of Tracked.</param>
        /// <returns>A collection of the tracked joints.</returns>
        public static IEnumerable<Joint> TrackedJoints(this Body body, bool includeInferred = true)
        {
            List<Joint> joints = new List<Joint>();

            foreach (var joint in body.Joints.Values)
            {
                switch (joint.TrackingState)
                {
                    case TrackingState.NotTracked:
                        break;
                    case TrackingState.Inferred:
                        if (includeInferred)
                        {
                            joints.Add(joint);
                        }
                        break;
                    case TrackingState.Tracked:
                        joints.Add(joint);
                        break;
                    default:
                        break;
                }
            }

            return joints;
        }

        /// <summary>
        /// Serializes the bodies of the current <see cref="BodyFrame"/> and saves the file to the specified location.
        /// </summary>
        /// <param name="frame">The frame to capture.</param>
        /// <param name="path">The destination file path.</param>
        /// <returns>True if the frame was successfully saved. False otherwise.</returns>
        public static bool Save(this BodyFrame frame, string path)
        {
            string json = frame.Serialize();

            return _capture.Save(json, path);
        }

        /// <summary>
        /// Serializes the current collection of <see cref="Body"/> and saves the file to the specified location.
        /// </summary>
        /// <param name="bodies">The body collection to capture.</param>
        /// <param name="path">The destination file path.</param>
        /// <returns>True if the body collection was successfully saved. False otherwise.</returns>
        public static bool Save(this IEnumerable<Body> bodies, string path)
        {
            string json = bodies.Serialize();

            return _capture.Save(json, path);
        }

        /// <summary>
        /// Serializes the current <see cref="Body"/> and saves the file to the specified location.
        /// </summary>
        /// <param name="body">The body to capture.</param>
        /// <param name="path">The destination file path.</param>
        /// <returns>True if the body was successfully saved. False otherwise.</returns>
        public static bool Save(this Body body, string path)
        {
            string json = body.Serialize();

            return _capture.Save(json, path);
        }

        /// <summary>
        /// Serializes the current <see cref="Joint"/> and saves the file to the specified location.
        /// </summary>
        /// <param name="joint">The joint to capture.</param>
        /// <param name="path">The destination file path.</param>
        /// <returns>True if the joint was successfully saved. False otherwise.</returns>
        public static bool Save(this Joint joint, string path)
        {
            string json = joint.Serialize();

            return _capture.Save(json, path);
        }

        #endregion

        #region Utilities

        /// <summary>
        /// Calculates the number of the tracked joints from the current collection.
        /// </summary>
        /// <param name="joints">A collection of joints.</param>
        /// <returns>The number of the accurately tracked joints.</returns>
        static int NumberOfTrackedJoints(IEnumerable<Joint> joints)
        {
            int trackedJoints = 0;

            foreach (var joint in joints)
            {
                if (joint.TrackingState == TrackingState.Tracked)
                {
                    trackedJoints++;
                }
            }

            return trackedJoints;
        }

        /// <summary>
        /// Calculates the number of the tracked joints from the specified collection.
        /// </summary>
        /// <param name="joints">A collection of joints.</param>
        /// <returns>The number of the accurately tracked joints.</returns>
        static int NumberOfTrackedJoints(params Joint[] joints)
        {
            return NumberOfTrackedJoints(joints.ToList());
        }

        #endregion
    }
}
