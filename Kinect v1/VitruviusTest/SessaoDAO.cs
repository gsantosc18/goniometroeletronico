using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;

namespace VitruviusTest
{
    class SessaoDAO
    {
        private MySqlConnection connection;

        public SessaoDAO(MySqlConnection connection)
        {
            this.connection = connection;
        }

        public void add(Sessao sessao)
        {
            try
            {
                this.connection.Open();
                string query = "INSERT INTO sessao (aie,afe,aid,afd,registro) VALUES (@aie,@afe,@aid,@afd,@registro)";

                MySqlCommand cmd = new MySqlCommand(query, this.connection);

                cmd.Parameters.AddWithValue("@aie", sessao.AnguloInicialEsquerdo);
                cmd.Parameters.AddWithValue("@afe", sessao.AnguloFinalEsquerdo);
                cmd.Parameters.AddWithValue("@aid", sessao.AnguloInicialDireito);
                cmd.Parameters.AddWithValue("@afd", sessao.AnguloFinalDireito);
                cmd.Parameters.AddWithValue("@registro", sessao.Registro);

                cmd.ExecuteNonQuery();

                this.connection.Close();
                MessageBox.Show("Sessão gravada com sucesso...");
            }
            catch (Exception erro)
            {
                MessageBox.Show("Erro de gravação a sessão: Erro -> " + erro);
            }
        }
    }
}
