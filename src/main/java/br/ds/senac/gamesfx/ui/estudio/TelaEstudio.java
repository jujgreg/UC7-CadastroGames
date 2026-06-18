package br.ds.senac.gamesfx.ui.estudio;


import br.ds.senac.gamesfx.data.repository.EstudioRepository;
import br.ds.senac.gamesfx.model.Estudio;
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

import java.util.Optional;

public class TelaEstudio {

    private TextField tfid = new TextField();
    private TextField tfNomeFundador = new TextField();
    private TextField tfAnoFundacao = new TextField();
    private TextField tfPaisOrigem = new TextField();
    private TextField tfNomeStudio =new TextField();

    public TelaEstudio(Estudio estudio){

        tfid.setText(String.valueOf(estudio.getId()));
        tfNomeFundador.setText(String.valueOf(estudio.getNomeFundador()));
        tfAnoFundacao.setText(String.valueOf(estudio.getAnoFundacao()));
        tfPaisOrigem.setText(String.valueOf(estudio.getPaisOrigem()));
        tfNomeStudio.setText(String.valueOf(estudio.getNomeStudio()));
    }

    public TelaEstudio(){}

    public void criarTela(Stage stagePai){
        Stage stage = new Stage();
        stage.initOwner(stagePai);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setMaxWidth(500);
        stage.setTitle("Cadastro de Estudio");

        BorderPane raiz = new BorderPane();

        raiz.setTop(criarPainelTitulo());
        raiz.setCenter(criarFomulario());
        raiz.setBottom(criarRodape(stage));

        Scene cena = new Scene(raiz);

        stage.setResizable(false);
        stage.setScene(cena);

        stage.showAndWait();
    }
    public HBox criarPainelTitulo(){
        HBox painelTitulo = new HBox();

        painelTitulo.setPadding(new Insets(20,0,20,20));
        painelTitulo.setStyle("-fx-background-color:#363636; ");
        painelTitulo.setAlignment(Pos.CENTER_LEFT);

        Label lblTitulo = new Label("Cadastro de Estudio");

        lblTitulo.setStyle("-fx-font-size: 28; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill:#98FB98;");
        painelTitulo.getChildren().addAll(lblTitulo);

        return painelTitulo;
    }
    private VBox criarFomulario(){
        VBox formulario = new VBox();
        formulario.setPadding(new Insets(10));

        GridPane gridFormulario = new GridPane();
        gridFormulario.setVgap(7);

        Label lblid = new Label("ID: ");
        tfid.setEditable(false);
        tfid.setDisable(true);

        Label lblNomeFundador = new Label("Nome do Fundador: ");
        tfNomeStudio.setPromptText("Ex. kojima");

        Label lblAnoFundacao = new Label("Ano de Fundação: ");
        tfAnoFundacao.setPromptText("Ex. 1994");

        Label lblPaisOrigem = new Label("Pais de origem: ");
        tfPaisOrigem.setPromptText("Ex. Japão");

        Label lblNomeStudio = new Label("Nome do estudio: ");
        tfNomeStudio.setPromptText("Ex. RockStar");

        gridFormulario.add(lblid, 0,0);
        gridFormulario.add(tfid,1,0);
        gridFormulario.add(lblNomeFundador, 0, 1);
        gridFormulario.add(tfNomeFundador, 1,1);
        gridFormulario.add(lblAnoFundacao,0,2);
        gridFormulario.add(tfAnoFundacao,1,2);
        gridFormulario.add(lblPaisOrigem, 0,3);
        gridFormulario.add(tfPaisOrigem,1,3);
        gridFormulario.add(lblNomeStudio,0,4);
        gridFormulario.add(tfNomeStudio,1,4);

        formulario.getChildren().addAll(gridFormulario);

        return formulario;

    }

    private Button criarBotao(String textoBotao, String urlImg) {


        javafx.scene.image.Image image = new Image(getClass().getResourceAsStream(urlImg));
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
    private  HBox criarRodape(Stage stage) {
        HBox rodape = new HBox();

        rodape.setPadding(new Insets(10, 5, 10, 10));
        rodape.setStyle("-fx-background-color:#6B6D70 ; ");

        Button btnSalvar = criarBotao("salvar", "/imagens/salve-.png");
        btnSalvar.setTooltip(new Tooltip("Salvar"));
        Button btnApagar = criarBotao("Apagar", "/imagens/risonho.png");
        btnApagar.setTooltip(new Tooltip("Apagar"));

        rodape.setAlignment(Pos.BASELINE_RIGHT);

        rodape.setSpacing(10);
        btnSalvar.setTooltip(new Tooltip("Salvar dados de Estudio"));

        btnSalvar.setOnAction(e -> {
            Estudio estudio = new Estudio();
            estudio.setNomeFundador(tfNomeFundador.getText());
            estudio.setAnoFundacao(tfAnoFundacao.getText());
            estudio.setPaisOrigem(tfPaisOrigem.getText());
            estudio.setNomeStudio(tfNomeStudio.getText());

            EstudioRepository repository = new EstudioRepository();
            if (tfid.getText().equals("")) {
                repository.salvar(estudio);

                Alert mensagemSalvar = new Alert(Alert.AlertType.CONFIRMATION);
                mensagemSalvar.setTitle("Cadastro de plataforma");
                mensagemSalvar.setHeaderText("O  estudio foi gravado com sucesso");
                mensagemSalvar.setContentText("deseja cadastrar outro jogo");
                Optional<ButtonType> escolhaCadastro = mensagemSalvar.showAndWait();

                if (escolhaCadastro.get() == ButtonType.OK) {
                    limparCampos();
                } else {
                    stage.close();
                }

            } else {
                estudio.setId(Integer.parseInt(tfid.getText()));
                repository.editar(estudio);

                Alert mensagemEditar = new Alert(Alert.AlertType.INFORMATION);
                mensagemEditar.setTitle("editar plataforma");
                mensagemEditar.setHeaderText("o estudio foi editado com sucesso!");
                Optional<ButtonType> escolha = mensagemEditar.showAndWait();
                stage.close();
            }
            limparCampos();
        });
        rodape.getChildren().addAll(btnSalvar, btnApagar);
        return rodape;
    }

    private void limparCampos(){
        tfNomeStudio.clear();
        tfAnoFundacao.clear();
        tfPaisOrigem.clear();
        tfNomeFundador.clear();
    }

}
