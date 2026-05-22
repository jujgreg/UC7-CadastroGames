package br.ds.senac.gamesfx.ui.jogos;

import br.ds.senac.gamesfx.model.Jogo;
import br.ds.senac.gamesfx.data.repository.JogoRepository;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Optional;


public class PainelJogos {

    private  Stage stage;

    public PainelJogos(Stage stage){
        this.stage = stage;


    }

    public VBox criarPainelJogos() {


        VBox painelJogos = new VBox();
        painelJogos.setPadding(new Insets(5, 20, 20, 20));
        painelJogos.setStyle("-fx-background-color: #1C1C1C");

        Label lblTitulo = new Label("Listagem de jogos");
        lblTitulo.setStyle("-fx-font-size: 24; -fx-text-fill: #98FB98; -fx-font-weight: bold");

        Separator linha = new Separator();

        //tabela de jogos
        TableView<Jogo> tblJogos = new TableView<>();

        tblJogos.setStyle(
                "-fx-background-color: #696969;"
        );

        VBox.setVgrow(tblJogos, Priority.ALWAYS);

        //estilo da tabela




        // BOTOES



        //colunas da tabela
        TableColumn<Jogo, Integer> colunaId = new TableColumn<>("ID");
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaId.setResizable(false);
        colunaId.setPrefWidth(50);
        colunaId.setStyle("-fx-text-fill: #000000; -fx-background-color: #696969; -fx-border-color: #444;");

        TableColumn<Jogo, String> colunaTitulo = new TableColumn<>("TITULO");
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaTitulo.setPrefWidth(400);
        colunaTitulo.setStyle("-fx-text-fill: #000000; -fx-background-color: #696969; -fx-border-color: #444;");


        TableColumn<Jogo, String> colunaPlataforma = new TableColumn<>("PLATAFORMA");
        colunaPlataforma.setCellValueFactory(new PropertyValueFactory<>("plataforma"));
        colunaPlataforma.setPrefWidth(400);
        colunaPlataforma.setStyle("-fx-text-fill: #000000; -fx-background-color: #696969; -fx-border-color: #444;");

       TableColumn<Jogo, Boolean> colunaPlatinado = new TableColumn<>("PLATINADO");
        colunaPlatinado.setCellValueFactory(new PropertyValueFactory<>("platinado"));
       colunaPlatinado.setPrefWidth(400);

        JogoRepository repository = new JogoRepository();
        tblJogos.setItems(repository.getJogos());

        HBox painelBotoes = new HBox();
        painelBotoes.setPadding(new Insets(20, 20, 0, 0));
        painelBotoes.setAlignment(Pos.BASELINE_RIGHT);

        //criar botao
        Button btnAdicionar = criarBotao("Adicionar","/imagens/mais.png");
        btnAdicionar.setOnAction(e  ->{
            TelaJogo telaJogo = new TelaJogo();
            telaJogo.criarTela(stage);
            tblJogos.setItems(repository.getJogos());
        });

        Button btnEditar = criarBotao("Editar", "/imagens/lapis.png");
        btnEditar.setOnAction(e ->{

           Jogo editarJogo  = tblJogos.getSelectionModel().getSelectedItem();
           TelaJogo telaJogo = new TelaJogo(editarJogo);
           telaJogo.criarTela(stage);


        });



        Button btnExibir = criarBotao("Exibir", "/imagens/informacoes.png");
        Button btnExcluir = criarBotao("Excluir", "/imagens/cruz-pequeno.png");
        btnExcluir.setOnAction(e -> {

            Alert confirmaExclusão = new Alert(Alert.AlertType.CONFIRMATION);
            confirmaExclusão.setTitle("titulo do erro");
            confirmaExclusão.setHeaderText("Você está excluindo um jogo.");
            confirmaExclusão.setContentText("Deseja continuar?");

            Optional<ButtonType> reposta = confirmaExclusão.showAndWait();
            ButtonType botaoSelecionado = reposta.get();

            Jogo jogoExcluir = tblJogos.getSelectionModel().getSelectedItem();
            if(jogoExcluir == null){
                Alert alertaJogoNaoSekecioando = new Alert(Alert.AlertType.WARNING);
                alertaJogoNaoSekecioando.setTitle("Exclusao de jogo");
                alertaJogoNaoSekecioando.setHeaderText("Você não selecionou um jogo antes de excluir");
                alertaJogoNaoSekecioando.showAndWait();
                return;
            }

            if(botaoSelecionado == ButtonType.OK){

                int resultado = repository.excluir(jogoExcluir.getId());
                tblJogos.setItems(repository.getJogos());

            }





//
//
//            if (resultado > 0) {
//
//                JOptionPane.showMessageDialog(null, "Jogo excluído com sucesso!");
//                tblJogos.setItems(repository.getJogos());
//
//            }

        });




        painelBotoes.getChildren().addAll(btnAdicionar, btnEditar, btnExibir, btnExcluir );


        //adicionar coluna da tabela
        tblJogos.getColumns().addAll(colunaId, colunaTitulo, colunaPlataforma);


        painelJogos.getChildren().addAll(lblTitulo, linha, tblJogos, painelBotoes);

        return painelJogos;
    }

    private Button criarBotao(String textoBotao, String urlImagem) {

        Image image = new Image(getClass().getResourceAsStream(urlImagem));
        ImageView imageView = new ImageView(image);


        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        Button button = new Button();
        button.setText(textoBotao);
        button.setGraphic(imageView);
        button.setPrefWidth(100);
        button.setPrefHeight(50);


        button.setContentDisplay(ContentDisplay.TOP);

        return button;
    }

}
