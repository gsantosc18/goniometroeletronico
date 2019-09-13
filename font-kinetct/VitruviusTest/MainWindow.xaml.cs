#define USE_KINECTVIEWER //comment this out if you edit the XAML and use instead of KinectViewer control the Viewbox control (that contains a Grid with an Image and a Canvas)

using LightBuzz.Vitruvius;
using LightBuzz.Vitruvius.WPF;
using Microsoft.Kinect;
using System;
using System.Linq;
using System.Windows;

namespace VitruviusTest
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        #region --- Fields ---

        private Boolean isStart = false;
        private Double startAngleLeft = 0, startAngleRight = 0;
        private Double endAngleLeft = 0, endAngleRight = 0;
        private GestureController _gestureController;
        private Skeleton[] skeletons;

        #endregion

        #region --- Initialization ---

        public MainWindow()
        {
            try
            {

                InitializeComponent();

            } catch (Exception e) {
                MessageBox.Show(string.Concat("Ocorreu um erro Iniciar o Kinect! Error: ",e.Message.ToString()));
            }

            #if USE_KINECTVIEWER
            /* optional display flipping (vertical flipping may be useful when using a projector) */
            kinectViewer.FlippedHorizontally = true;
            kinectViewer.FlippedVertically = false;
            #endif
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            KinectSensor sensor = SensorExtensions.Default();

            if (sensor != null)
            {
                sensor.EnableAllStreams();
                sensor.ColorFrameReady += Sensor_ColorFrameReady;
                sensor.DepthFrameReady += Sensor_DepthFrameReady;
                sensor.SkeletonFrameReady += Sensor_SkeletonFrameReady;

                _gestureController = new GestureController(GestureType.All);
                _gestureController.GestureRecognized += GestureController_GestureRecognized;

                sensor.Start();
            }
            else
            {
                MessageBox.Show("O Kinect não está conectado!!");
            }
        }

        #endregion

        #region --- Properties ---

        public VisualizationMode Mode
        {
            #if USE_KINECTVIEWER
            get { return kinectViewer.FrameType; }
            set { kinectViewer.FrameType = value; }
            #else
            get; set;
            #endif
        }

        #endregion

        #region --- Events ---

        private void Sensor_ColorFrameReady(object sender, ColorImageFrameReadyEventArgs e)
        {
            if (Mode != VisualizationMode.Color) return;

            using (var frame = e.OpenColorImageFrame())
                if (frame != null)
                    #if USE_KINECTVIEWER
                    kinectViewer.Update(frame.ToBitmap());
                    #else
                    camera.Source = frame.ToBitmap();
                    #endif
        }

        private void Sensor_DepthFrameReady(object sender, DepthImageFrameReadyEventArgs e)
        {
            if (Mode != VisualizationMode.Depth) return;

            using (var frame = e.OpenDepthImageFrame())
                if (frame != null)
                    #if USE_KINECTVIEWER
                    kinectViewer.Update(frame.ToBitmap());
                    #else
                    camera.Source = frame.ToBitmap();
                    #endif
        }

        private void Sensor_SkeletonFrameReady(object sender, SkeletonFrameReadyEventArgs e)
        {
            using (var frame = e.OpenSkeletonFrame())
                if (frame != null)
                {
                    #if USE_KINECTVIEWER
                    kinectViewer.Clear();
#else
                    canvas.ClearSkeletons();
#endif

                    if ((this.skeletons == null) || (this.skeletons.Length != frame.SkeletonArrayLength))
                    {
                        this.skeletons = new Skeleton[frame.SkeletonArrayLength];
                    }

                    tblHeights.Text = string.Empty;

                    frame.CopySkeletonDataTo(this.skeletons);

                    foreach (Skeleton skeleton in this.skeletons)
                    {
                        if (SkeletonTrackingState.Tracked == skeleton.TrackingState)
                        {
                            // Update skeleton gestures
                            _gestureController.Update(skeleton);

                            // Articulacoes
                            // Direito
                            Joint handRigthtt = skeleton.Joints[JointType.HandRight];
                            Joint shoulderrightt = skeleton.Joints[JointType.ShoulderRight];
                            Joint elbowghtt = skeleton.Joints[JointType.ElbowRight];
                            // Esquerdo
                            Joint handLeftt = skeleton.Joints[JointType.HandLeft];
                            Joint shoulderLeftt = skeleton.Joints[JointType.ShoulderLeft];
                            Joint elbowLeftt = skeleton.Joints[JointType.ElbowLeft];

                            String angulodireito;
                            String anguloesquerdo;

                            angulodireito = "" + (int)(Angulos(handRigthtt, elbowghtt, shoulderrightt));
                            anguloesquerdo = "" + (int)(Angulos(handLeftt, elbowLeftt, shoulderLeftt));



                            if (isStart && startAngleLeft == 0 && startAngleRight == 0)
                            {
                                startAngleLeft = Double.Parse(anguloesquerdo);
                                startAngleRight = Double.Parse(angulodireito);
                            }
                            else if (!isStart && !(startAngleLeft == 0 && startAngleRight == 0) && endAngleLeft == 0 && endAngleRight == 0)
                            {
                                endAngleLeft = Double.Parse(anguloesquerdo);
                                endAngleRight = Double.Parse(angulodireito);
                            }

                            AnguloEsquerdo.Text = anguloesquerdo;
                            AnguloDireito.Text = angulodireito;
                            AnguloDireitoInicial.Text = "" + startAngleRight;
                            AnguloDireitoFinal.Text = "" + endAngleRight;
                            AnguloEsquerdoInicial.Text = "" + startAngleLeft;
                            AnguloEsquerdoFinal.Text = "" + endAngleLeft;

                            // Draw skeleton
#if USE_KINECTVIEWER
                            kinectViewer.DrawBody(skeleton);
#else
                            canvas.DrawSkeleton(skeleton);
#endif

                            // Display user height
                            tblHeights.Text = string.Format("{0} cm", skeleton.Height().ToString("0.00"));
                        }
                    }
                }
        }

        private void GestureController_GestureRecognized(object sender, GestureEventArgs e)
        {
            // Display the gesture type
          //  tblGestures.Text = e.Name;

            // Do something according to the type of the gesture
            switch (e.Type)
            {
                case GestureType.JoinedHands:
                    break;
                case GestureType.Menu:
                    break;
                case GestureType.SwipeDown:
                    break;
                case GestureType.SwipeLeft:
                    break;
                case GestureType.SwipeRight:
                    break;
                case GestureType.SwipeUp:
                    break;
                case GestureType.WaveLeft:
                    break;
                case GestureType.WaveRight:
                    break;
                case GestureType.ZoomIn:
                    break;
                case GestureType.ZoomOut:
                    break;
                default:
                    break;
            }
        }

        private void Color_Click(object sender, RoutedEventArgs e)
        {
            Mode = VisualizationMode.Color;
        }

        private void Depth_Click(object sender, RoutedEventArgs e)
        {
            Mode = VisualizationMode.Depth;
        }

        #endregion

        //Evento para calcular angulos:
        private double Angulos(Joint j1, Joint j2, Joint j3)
        {
            double Angulo = 0;
            double shrhX = j1.Position.X - j2.Position.X;
            double shrhY = j1.Position.Y - j2.Position.Y;
            double shrhZ = j1.Position.Z - j2.Position.Z;
            double hsl = vectorNorm(shrhX, shrhY, shrhZ);
            double unrhX = j3.Position.X - j2.Position.X;
            double unrhY = j3.Position.Y - j2.Position.Y;
            double unrhZ = j3.Position.Z - j2.Position.Z;
            double hul = vectorNorm(unrhX, unrhY, unrhZ);
            double mhshu = shrhX * unrhX + shrhY * unrhY + shrhZ * unrhZ;
            double x = mhshu / (hul * hsl);

            if (x != double.NaN)
            {
                if (-1 <= x && x <= 1)
                {
                    double angleRAd = Math.Acos(x);
                    Angulo = angleRAd * (180.0 / Math.PI);
                }
                else
                    Angulo = 0;
            }
            else
                Angulo = 0;

            return Angulo;
        }

        private void btnRestart_Click(object sender, RoutedEventArgs e)
        {
            isStart = false;
            startAngleLeft = 0;
            startAngleRight = 0;
            endAngleLeft = 0;
            endAngleRight = 0;
    }

        private void btnGravar_Click(object sender, RoutedEventArgs e)
        {
            ConectionFactory conexao = new ConectionFactory();
            Sessao sessao = new Sessao();
            SessaoDAO sessaodao = new SessaoDAO(conexao.GetConnection());

            // Direito
            sessao.AnguloFinalDireito = endAngleRight;
            sessao.AnguloInicialDireito = startAngleRight;
            // Esquerdo
            sessao.AnguloInicialEsquerdo = startAngleLeft;
            sessao.AnguloFinalEsquerdo = endAngleLeft;
            // Data do registro
            sessao.Registro = DateTime.Now;

            sessaodao.add(sessao);
        }

        //Muestra:
        private static double vectorNorm(double x, double y, double z)
        {

            return Math.Sqrt(Math.Pow(x, 2) + Math.Pow(y, 2) + Math.Pow(z, 2));

        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            isStart = !isStart;

            if (isStart)
            {
                btnStart.Content = "Parar";
            }
            else
            {
                btnStart.Content = "Iniciar";
            }
        }
    }

}
