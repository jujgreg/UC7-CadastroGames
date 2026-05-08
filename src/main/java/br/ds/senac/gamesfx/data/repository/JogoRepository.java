package br.ds.senac.gamesfx.data.repository;


import br.ds.senac.gamesfx.model.Jogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
}
