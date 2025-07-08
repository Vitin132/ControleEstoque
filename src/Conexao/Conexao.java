package Conexao;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class Conexao {
//    private static final String URL = "jdbc:mysql://localhost:3306/estoque";
//    private static final String USER = "root";
//    private static final String PASS = "root";
//
//    public static Connection conectar() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            return DriverManager.getConnection(URL, USER, PASS);
//        } catch (Exception e) {
//            throw new RuntimeException("Erro na conexão: " + e);
//        }
//    }
//}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Aluno
 */
public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/estoque_db";

    private static final String USUARIO = "root";

    private static final String SENHA = "root";

    private static Connection connection;
    private static Conexao conn;

    //private Conexao(){}
    public static Conexao getConn() {
        if (conn == null) {
            conn = new Conexao();
        }
        return conn;
    }

    public Connection abrirConexao() {
        try {
            System.out.println("Conectando ao Banco de Dados...");
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão estabelecida com sucesso!");
            return connection;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados.");
            e.printStackTrace();
            return null;
        }

    }
}
