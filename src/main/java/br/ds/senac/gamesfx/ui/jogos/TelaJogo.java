package br.ds.senac.gamesfx.ui.jogos;

import br.ds.senac.gamesfx.model.Jogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;


public class TelaJogo {

    private TextField tfId;
    private TextField tfTitulo;
    private TextField tfValor;
    private ComboBox<String> comboPlataforma;
    private ComboBox<String> comboEstudio;
    private DatePicker dpDataLancamento;
    private CheckBox cbFinalizado;


    public void criarTela(Stage stagePai){
        Stage stage = new Stage();
        stage.initOwner(stagePai);
        stage.initModality(Modality.APPLICATION_MODAL);


        stage.setMaxWidth(500);
        stage.setHeight(500);
        stage.setTitle("Cadastro de Jogo");

        BorderPane raiz = new BorderPane();
        raiz.setTop(criarPainelTitulo());
        raiz.setCenter(criarFormulario());
        raiz.setBottom(criarRodape());
        Scene cena = new Scene(raiz,500,550);


        stage.setHeight(550);
        stage.setWidth(500);
        stage.setResizable(false);
        stage.setScene(cena);




        stage.showAndWait();







    }
    public HBox criarPainelTitulo(){

        HBox painelTitulo = new HBox();



        painelTitulo.setPadding(new Insets (20,0,20,20));
        painelTitulo.setStyle("-fx-background-color:#363636; ");
//        painelTitulo.set
        painelTitulo.setAlignment(Pos.CENTER_LEFT);



        Label lblTitulo = new Label("Cadastro de Jogos");

        lblTitulo.setStyle( "-fx-font-size: 28; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill:#98FB98;");
        painelTitulo.getChildren().addAll(lblTitulo);

        return painelTitulo;
    }

    private VBox criarFormulario(){


        ObservableList<String> plataformas = FXCollections
                .observableArrayList(
                        "Computador", "PlayStation 1", "PlayStation 2", "PlayStation 3", "PlayStation 4", "PlayStation 5", "Xbox", "Xbox 360", "Xbox One", "Xbox Series X", "Xbox Series S", "NES", "SNES", "Nintendo 64", "GameCube", "Wii", "Wii U", "Nintendo Switch", "Sega Master System", "Sega Mega Drive", "Sega Saturn", "Sega Dreamcast"
                );
        ObservableList<String> estudios = FXCollections
                .observableArrayList(
                        "Rockstar Games", "Naughty Dog", "Ubisoft", "Electronic Arts", "Capcom", "Square Enix", "Bethesda", "Nintendo", "Insomniac Games", "FromSoftware"
                );

        VBox formulario = new VBox();
        formulario.setPadding(new Insets(10));
//====================================================================================================

        GridPane gridFormulario = new GridPane();
        gridFormulario.setVgap(7);


        //Criar os componentes para a grid

        Label lblid = new Label("ID: ");
        tfId = new TextField();
        tfId.setEditable(false);
        tfId.setDisable(true);

        Label lblTitulo = new Label("Título: ");
        tfTitulo = new TextField();
        tfTitulo.setPromptText("Ex. Minecraft");

        Label lblPlataformas = new Label("Plataforma: ");
        comboPlataforma = new ComboBox<>(plataformas);

        Label lblEstudios = new Label("Estudio: ");
        comboEstudio = new ComboBox<>(estudios);

        Label lblValor = new Label("Valor: ");
        tfValor = new TextField();
        tfValor.setPromptText("Ex. 9,99");

        Label lblLancamento = new Label("Data de Lançamento: ");
        dpDataLancamento = new DatePicker(LocalDate.now());

        cbFinalizado = new CheckBox("Finalizado?");

        //adicionar na grid
        gridFormulario.add(lblid,0,0);
        gridFormulario.add(tfId,1,0);
        gridFormulario.add(lblTitulo,0,1);
        gridFormulario.add(tfTitulo,1,1);
        gridFormulario.add(lblPlataformas,0,2);
        gridFormulario.add(comboPlataforma,1,2);
        gridFormulario.add(lblEstudios,0,3);
        gridFormulario.add(comboEstudio,1,3);
        gridFormulario.add(lblValor,0,4);
        gridFormulario.add(tfValor,1,4);
        gridFormulario.add(lblLancamento,0,4);
        gridFormulario.add(dpDataLancamento,1,4);
//====================================================================================================


        formulario.getChildren().addAll(gridFormulario);


        return  formulario;
    }
    private Button criarBotao(String textoBotao, String urlImg) {


        Image image = new Image(getClass().getResourceAsStream(urlImg));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(40);
        imageView.setFitHeight(40);


        Button botao = new Button();
        botao.setText(textoBotao);
        botao.setGraphic(imageView);
        botao.setPrefHeight(40);
        botao.setPrefWidth(120);
        botao.setStyle(" -fx-background-color: #3B4045; -fx-text-fill: #98FB98;-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #98FB98; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 8 14;");


        return botao;
    }
    private HBox criarRodape(){
        HBox rodape = new HBox();

        rodape.setPadding(new Insets (10,5,10,10));
        rodape.setStyle("-fx-background-color:#6B6D70 ; ");

        Button btnSalvar = criarBotao("salvar","/imagens/botao-de-download.png");
        btnSalvar.setTooltip(new Tooltip("Salvar"));
        Button btnApagar = criarBotao("Apagar","/imagens/risonho.png");
        btnApagar.setTooltip(new Tooltip("Apagar"));
        rodape.setAlignment(Pos.BASELINE_RIGHT);
        rodape.getChildren().addAll(btnApagar,btnSalvar);
        rodape.setSpacing(10);

        return rodape;
    }
}

