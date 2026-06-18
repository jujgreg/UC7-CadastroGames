package br.ds.senac.gamesfx;

import br.ds.senac.gamesfx.ui.estudio.PainelEstudio;
import br.ds.senac.gamesfx.ui.home.PainelHome;
import br.ds.senac.gamesfx.ui.jogos.PainelJogos;
import br.ds.senac.gamesfx.ui.plataforma.PainelPlataforma;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TelaPrincipal extends Application {
    private  static final  String COR_PADRAO = "#778899;"+"-fx-text-fill: black;"+
            "-fx-border-color: #000000;" +
            "-fx-border-width: 2px;" +
            "-fx-border-radius: 7;" +
            "-fx-background-radius: 6;"+
            "-fx-cursor: hand";

    private  static final  String COR_HOVER = "#696969;"+"-fx-text-fill: black;"+
            "-fx-border-color: #000000;" +
            "-fx-border-width: 2px;" +
            "-fx-border-radius: 7" +
            "-fx-background-radius: 6;"+
            "-fx-cursor: hand";


    @Override
    public void start(Stage stage) throws Exception {

        Image Iconetela = new Image(getClass().getResourceAsStream("/imagens/salve-.png"));
                stage.getIcons().add(Iconetela);


        BorderPane raiz = new BorderPane();


        VBox painelLateral = new VBox();
        painelLateral.setSpacing(20);
        painelLateral.setPrefWidth(150);
        painelLateral.setPadding(new Insets(10));

        //===========estilos da pagina=================

        painelLateral.setStyle("-fx-background-color: #363636;"+  "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0.3, 2, 0)");
        raiz.setStyle("-fx-background-color: #2F3336;");

        // ================Botões=========================

        Button btnJogos = criarBotaoMenu("Jogos");
        btnJogos.setOnAction(click -> {
            PainelJogos painelJogos = new PainelJogos(stage);
            raiz.setCenter(painelJogos.criarPainelJogos());
        });

        Button btnPlataformas = criarBotaoMenu("Plataformas");
        btnPlataformas.setOnAction(click ->{
            PainelPlataforma painelPlataforma = new PainelPlataforma(stage);
            raiz.setCenter(painelPlataforma.criarPainelPlataforma());
        });

        Button btnEstudios = criarBotaoMenu("Estudios");
        btnEstudios.setOnAction(click ->{
            PainelEstudio painelEstudio = new PainelEstudio(stage);
            raiz.setCenter(painelEstudio.cirarPainelEstudio());
        });

        Button btnHome = criarBotaoMenu("Home");
        aplicarEfeitoHover(btnJogos,btnHome,btnEstudios,btnPlataformas);
        btnHome.setOnAction(click -> {
            PainelHome painelHome = new PainelHome();
            raiz.setCenter(painelHome.criarPainelHome());
        });
        //======================Vincular botão ao painel lateral=========================

        painelLateral.getChildren().addAll(btnHome,btnJogos,btnPlataformas,btnEstudios);

        //===========vinculando o painel lateral a esquerda da "raiz"====================

        raiz.setLeft(painelLateral);

        //======================Criando a cena da apresentação e o customizando======================================

        Scene cena = new Scene(raiz,1200,800);
//        stage.setResizable(false);
        stage.setTitle("Sistema de Gestão de Platinas de Jogos V1.0");
        stage.setMaximized(true);



        //=====================Atribuindo a cena ao stage(palco)=====================

        stage.setScene(cena);
        stage.show();

        //====================Enfeitando o site==================
        //Nao deu pra anotar mexi o codiogo tudo

       PainelHome painelHome = new PainelHome();

    raiz.setCenter(painelHome.criarPainelHome());
    }
    private Button criarBotaoMenu(String textoDoBotao){
       Button button = new Button(textoDoBotao);
       button.setPadding(new Insets(5));
       button.setPrefWidth(Double.MAX_VALUE);


               return button;
   }
   private void aplicarEfeitoHover(Button... botoes){
        for (Button button : botoes){
            button.setStyle("-fx-background-Color: " + COR_PADRAO);

            //Ao entrar no botão-> muda de cor
            button.setOnMouseEntered(e -> {
                button.setStyle("-fx-background-Color: " + COR_HOVER);});

            //Saiu do botao volta a cor
            button.setOnMouseExited(e ->{
                button.setStyle("-fx-background-Color: " + COR_PADRAO);});
            };

        }
   }


