using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MySql.Data.MySqlClient;
using System.Windows.Forms;
using System.Data;

namespace VitruviusTest
{
    class ConectionFactory
    {
        private string caminho = "server=localhost;uid=root;pwd=root;database=gonio;";
        private MySqlConnection conexao;

        public MySqlConnection Conexao()
        {
            return this.conexao;
        }

        public ConectionFactory()
        {
            try
            {
                conexao = new MySqlConnection(caminho);
                conexao.Open();
                Console.Write("Conectado com sucesso...");
            }
            catch (Exception)
            {
                Console.Write("Erro na conexão com o banco de dados!");
            }
            finally
            {
                conexao.Close();
            }
        }

        public MySqlConnection GetConnection()
        {
            return this.conexao;
        }
    }
}
