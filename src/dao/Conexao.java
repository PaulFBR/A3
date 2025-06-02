package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe que gerencia a conexão com o banco de dados MySQL
public class Conexao {

    // URL de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/a3";
    private static final String USUARIO = "root";
    private static final String SENHA = "123456789";

    // Método para obter a conexão
    public static Connection conectar() throws SQLException {
        try {
            // Força o carregamento do driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado.");
            return null;
        }

        // Cria e retorna a conexão
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
