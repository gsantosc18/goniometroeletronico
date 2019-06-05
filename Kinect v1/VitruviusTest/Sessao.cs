using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VitruviusTest
{
    class Sessao
    {
        public int IdSessao { get; set; }
        public double AnguloInicialDireito { get; set; }
        public double AnguloFinalDireito { get; set; }
        public double AnguloInicialEsquerdo { get; set; }
        public double AnguloFinalEsquerdo { get; set; }
        public DateTime Registro { get; set; }
    }
}
