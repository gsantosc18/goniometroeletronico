   M S B A M L   `   `   `  ����R  NLightBuzz.Vitruvius.WPF, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nulllKclr-namespace:LightBuzz.Vitruvius.Controls;assembly=LightBuzz.Vitruvius.WPFLightBuzz.Vitruvius.Controls  5       VitruviusTest   VitruviusTest.MainWindow   5      \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    5      R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5      a	vitruviusKclr-namespace:LightBuzz.Vitruvius.Controls;assembly=LightBuzz.Vitruvius.WPF     5      -   5        $��991.955��5      $	��580��6     � Title$  Área de Sessão��5      .��5      �  � ColumnDefinitions 5   
   �� 5      $��* �6   �� 5      $	��250 �6   .+�5   
   � $��1��6   $��0q�6#    � RowDefinitions 5      �� 5      $��* �6    �� 5      $
��Auto �6    .+�5      /   )LightBuzz.Vitruvius.Controls.KinectViewer  -   5          Name$ kinectViewer��5   %   $��0��6;   $
��Auto��6H   5)      �� 5+   
   $��1��6      StackPanelTextBlocksStyle#��[  6%   $��	33,0,0,10q�6X   $	��217��6k   .+�5,      �� �� 5.      #��[  6   $	��212��6L   $��5,5,0,5q�6X   .+�5/      �� $��Altura do paciente:��6   �� 50      -   5        $��
tblHeights��50      $��-��6.   �� 51      $��Braço Direito:��6   �� 52      -   5         ��Name$ AnguloDireito��52      $��-��63   �� 53      $��Braço Esquerdo:��6   �� 54      -   5        $ AnguloEsquerdo��54      $��-��64   �� 55      $��Ângulo Inicial Direito:��6   �� 56      -   5        $ AnguloDireitoInicial��56      $��-��6:   �� 57      $��Ângulo Final Direito:��6   �� 58      -   5        $ AnguloDireitoFinal��58      $��-��68   �� 59      $��Ângulo Inicial Esquerdo:��6   �� 5:      -   5        $ AnguloEsquerdoInicial��5:      $��-��6;   �� 5;      $��Ângulo Final Esquerdo:��6   �� 5<      -	   5        $ AnguloEsquerdoFinal��5<      $��-��69   5=      �� 5>      -
   5         ��Name$ btnStart��5>      $��Iniciar��6'   $��0q�69   $	��138��6D   ���	#FFEC0000
�������$
��Bold"�6z   $��16(�6�   6�   �� 5?      -   5        $ 
btnRestart��5?      $��	Reiniciar��6)   $��
40,10,39,0q�6=   $	��138��6Q   ���	#FF000ADA
�������$
��Bold"�6�   $��16(�6�   6�   �� 5@      -   5        $ 	btnGravar��5@      $��Gravar��6(   $��
40,10,39,0q�69   $	��138��6M   ���	#FF00DA59
�������$
��Bold"�6�   $��16(�6�   6�   5A      5C      5D      