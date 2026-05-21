package br.ds.senac.gamesfx.data;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQLite {

    public static Connection getConexao(){
        // String de conexão - URL do banco de dados
        String url = "jdbc:sqlite:/C:\\Users\\joao.lgplima\\databases\\db_games.db";
    try {
        Connection conexao = DriverManager.getConnection(url);
        return conexao;
    } catch (SQLException e ){
        System.out.println("Ocorreu um erro Durante a conexão com o banco");
        e.printStackTrace();
    }


        return null;
    }

}
