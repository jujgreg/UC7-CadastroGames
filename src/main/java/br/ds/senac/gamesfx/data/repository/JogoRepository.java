package br.ds.senac.gamesfx.data.repository;


import br.ds.senac.gamesfx.data.ConexaoSQLite;
import br.ds.senac.gamesfx.model.Jogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JogoRepository {

    public ObservableList<Jogo> getJogos(){


        ObservableList<Jogo> ListaJogos = FXCollections
                .observableArrayList(
                new Jogo(1, "God Of War", "PC"),
                new Jogo(2, "Naruto", "PC"),
                new Jogo(3, "dragon ball", "PC"),
                new Jogo(4, "dark souls", "PC"),
                new Jogo(5, "elder rings", "PC")

        );
        return ListaJogos;


    }

    public  void salvar(Jogo jogo){
        //instruçao SQL cadastro jogo
        String sql = "INSERT INTO tb_games(titulo, plataforma, estudio, categoria, preco, data_lancamento, platinado)" +
                "VALUES (?,?,?,?,?,?,?)";
            // prepara a instrução
        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
                stm.setString(1, jogo.getTitulo());
                stm.setString(2, jogo.getPlataforma());
                stm.setString(3, jogo.getEstudio());
                stm.setString(4, jogo.getCategoria());
                stm.setDouble(5, jogo.getPreco());
                stm.setString(6, jogo.getDataLancamento().toString());
                stm.setInt(7, jogo.isPlatinado() ? 1 : 0);
                stm.executeUpdate();
        }catch (SQLException e){
            System.out.println("ocorreu um erro inesperado");
            e.printStackTrace();

        }


    }
}
