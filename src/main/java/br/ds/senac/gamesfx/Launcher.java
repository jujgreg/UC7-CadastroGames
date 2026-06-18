package br.ds.senac.gamesfx;

import br.ds.senac.gamesfx.data.repository.JogoRepository;
import br.ds.senac.gamesfx.model.Jogo;
import javafx.application.Application;

import java.time.LocalDate;

public class Launcher {
    public static void main(String[] args) {
        Application.launch(TelaPrincipal.class, args);

    }
}
