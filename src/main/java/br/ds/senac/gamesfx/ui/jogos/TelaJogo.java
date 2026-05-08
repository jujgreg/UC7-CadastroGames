package br.ds.senac.gamesfx.ui.jogos;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaJogo {

    public void criarTela(Stage stagePai){


        Stage stage = new Stage();
        stage.initOwner(stagePai);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setMaxHeight(400);
        stage.setMaxWidth(400);

        stage.setTitle("Cadastrar jogos");

        stage.showAndWait();

    }
}
