package login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    
    // Método para estabelecer conexão com o banco de dados
    public Connection conectarBD() {
        Connection conn = null;
        try{
            // Tentativa de carregar o driver do MySQL
            Class.forName("conn.mysql.Driver.Manager").newInstance();
            // URL de conexão com o banco de dados
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            // Estabelecendo a conexão
            conn = DriverManager.getConnection(url);
        }catch (Exception e) {
            // Exceção capturada mas não tratada - PODE CAUSAR FALHAS SILENCIOSAS
        }
        return conn;
    }
    
    // Variável para armazenar o nome do usuário
    public String nome = "";
    // Variável para indicar se o usuário foi autenticado
    public boolean result = false;
    
    // Método para verificar credenciais de login
    public boolean verifyLatGuardio(String login, String senha) {
        String sql = "";
        // Obtém conexão com o banco de dados
        Connection conn = conectarBD();
        
        // CONSTRUÇÃO DA INSTRUÇÃO SQL - VULNERÁVEL A SQL INJECTION
        sql += "select nome from usuarios ";
        sql += "where login = " + "'" + login + "'";
        sql += " and senha = " + "'" + senha + "';";
        
        try{
            // Cria statement para executar a query
            Statement st = conn.createStatement();
            // Executa a query SQL
            ResultSet rs = st.executeQuery(sql);
            
            // Verifica se encontrou algum resultado
            if(rs.next()) {
                result = true;
                // Armazena o nome do usuário encontrado
                nome = rs.getString("nome");
            }
            // FALTA: Fechar ResultSet, Statement e Connection
            // ISSO PODE CAUSAR VAZAMENTO DE RECURSOS
            
        }catch (Exception e) {
            // Exceção capturada mas não tratada - PODE OCULTAR ERROS
        }
        return result;
    }
}//fim da classe