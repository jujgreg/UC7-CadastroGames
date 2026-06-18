package br.ds.senac.gamesfx.ui.estudio;
import br.ds.senac.gamesfx.data.repository.EstudioRepository;
import br.ds.senac.gamesfx.model.Estudio;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class PainelEstudio {
    private Stage stage;

    public PainelEstudio(Stage stage){
        this.stage = stage;


    }

    public VBox cirarPainelEstudio(){

        VBox painelEstudio = new VBox();
        painelEstudio.setPadding(new Insets(5, 20, 20, 20));
        painelEstudio.setStyle("-fx-background-color: #1C1C1C");

        Label lblTituloEstudio = new Label("Listagen Estudios");
        lblTituloEstudio.setStyle("-fx-font-size: 24; -fx-text-fill: #98FB98; -fx-font-weight: bold");

        Separator linha = new Separator();

        //Tabela de estudios
        TableView<Estudio> tblEstudio = new TableView<>();

        tblEstudio.setStyle(
                "-fx-background-color: #23272A;" +
                        "-fx-control-inner-background: #23272A;" +
                        "-fx-table-cell-border-color: transparent;"

        );

        VBox.setVgrow(tblEstudio, Priority.ALWAYS);

        TableColumn<Estudio, Integer> colunaId = new TableColumn<>("ID");
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaId.setResizable(false);
        colunaId.setPrefWidth(50);


        TableColumn<Estudio, String> colunaFundador = new TableColumn<>("Nome do Fundador");
        colunaFundador.setCellValueFactory(new PropertyValueFactory<>("nomeFundador"));
        colunaFundador.setPrefWidth(400);

        TableColumn<Estudio, String> colunaAnoFundacao = new TableColumn<>("Ano de Fundaçao");
        colunaAnoFundacao.setCellValueFactory(new PropertyValueFactory<>("anoFundacao"));
        colunaAnoFundacao.setPrefWidth(400);

        TableColumn<Estudio, String> colunaPaisOrigem = new TableColumn<>("Pais de Origem");
        colunaPaisOrigem.setCellValueFactory(new PropertyValueFactory<>("paisOrigem"));
        colunaPaisOrigem.setPrefWidth(400);

        TableColumn<Estudio, String> colunaNomeStudio = new TableColumn<>("Nome do Estuido");
        colunaNomeStudio.setCellValueFactory(new PropertyValueFactory<>("nomeStudio"));
        colunaNomeStudio.setPrefWidth(400);

        EstudioRepository repository = new EstudioRepository();
        tblEstudio.setItems(repository.getEstudio());

        HBox painelBotoes = new HBox();
        painelBotoes.setPadding(new Insets(20,20,0,0));
        painelBotoes.setAlignment(Pos.BASELINE_RIGHT);

        Button btnAdicionar = criarBotao("Adicionar","/imagens/mais.png");
        btnAdicionar.setOnAction(e ->{
            TelaEstudio telaEstudio = new TelaEstudio();
            telaEstudio.criarTela(stage);
            tblEstudio.setItems(repository.getEstudio());
        });
        Button btnEditar = criarBotao("Editar", "/imagens/lapis.png");
        Button btnExibir = criarBotao("Exibir", "/imagens/informacoes.png");
        Button btnExcluir = criarBotao("Excluir", "/imagens/cruz-pequeno.png");

        painelBotoes.getChildren().addAll(btnAdicionar, btnEditar, btnExibir, btnExcluir);

        tblEstudio.getColumns().addAll(colunaId, colunaFundador, colunaAnoFundacao, colunaNomeStudio, colunaPaisOrigem);


        painelEstudio.getChildren().addAll(lblTituloEstudio, linha, tblEstudio, painelBotoes);
        return painelEstudio;


    }
    private Button criarBotao(String textoBotao, String urlImagem){

        Image image = new Image(getClass().getResourceAsStream(urlImagem));
        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        Button button = new Button();
        button.setText(textoBotao);
        button.setGraphic(imageView);
        button.setPrefHeight(50);
        button.setPrefWidth(100);

        button.setContentDisplay(ContentDisplay.TOP);

        return button;
    }


}
