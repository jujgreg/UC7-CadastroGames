package br.ds.senac.gamesfx.ui.plataforma;

import br.ds.senac.gamesfx.data.repository.PlataformaRepository;
import br.ds.senac.gamesfx.model.Plataforma;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.geom.Line2D;
import java.time.LocalDate;
import java.util.Optional;

public class PainelPlataforma {
    private Stage stage;

    public PainelPlataforma(Stage stage){
        this.stage = stage;
    }

    public VBox criarPainelPlataforma(){
        VBox painelPlataforma = new VBox();
        painelPlataforma.setPadding(new Insets(5,20,20,20));
        painelPlataforma.setStyle("-fx-background-color: #1C1C1C");

        Label lblTitulo = new Label("Lista de Plataformas");
        lblTitulo.setStyle("-fx-font-size: 24; -fx-text-fill: #98FB98; -fx-font-weight: bold");

        Separator linha = new Separator();

        TableView<Plataforma> tblPlataforma = new TableView<>();

        tblPlataforma.setStyle(
                "-fx-background-color: #23272A;" +
                "-fx-control-inner-background: #23272A;" +
                "-fx-table-cell-border-color: transparent;");

        VBox.setVgrow(tblPlataforma, Priority.ALWAYS);

        TableColumn<Plataforma, Integer> colunaId = new TableColumn<>("ID");
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaId.setResizable(false);
        colunaId.setPrefWidth(50);

        TableColumn<Plataforma, TextField> colunaNomePlataforma = new TableColumn<>("Nome Plataforma");
        colunaNomePlataforma.setCellValueFactory(new PropertyValueFactory<>("nomePlataforma"));
        colunaNomePlataforma.setResizable(false);
        colunaNomePlataforma.setPrefWidth(200);

        TableColumn<Plataforma, TextField> colunaFabricante = new TableColumn<>("Nome Fabricante");
        colunaFabricante.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
        colunaFabricante.setResizable(false);
        colunaFabricante.setPrefWidth(200);

        TableColumn<Plataforma, LocalDate> colunaData_P = new TableColumn<>("Data de lançamento");
        colunaData_P.setCellValueFactory(new PropertyValueFactory<>("data_P"));
        colunaData_P.setResizable(false);
        colunaData_P.setPrefWidth(200);

        TableColumn<Plataforma, Double> colunaValor =new TableColumn<>("valor");
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaValor.setPrefWidth(200);
        colunaValor.setResizable(false);

        PlataformaRepository repository = new PlataformaRepository();
        tblPlataforma.setItems(PlataformaRepository.getPlataforma());

        HBox painelBotoes = new HBox();
        painelBotoes.setPadding(new Insets(20, 20, 0, 0));
        painelBotoes.setAlignment(Pos.BASELINE_RIGHT);

        Button btnAdicionar = criarBotao("Adicionar","/imagens/mais.png");
        btnAdicionar.setOnAction(e ->{
            TelaPlataforma telaPlataforma = new TelaPlataforma();
            telaPlataforma.criarTela(stage);
            tblPlataforma.setItems(PlataformaRepository.getPlataforma());
        });

        Button btnEditar = criarBotao("Editar","/imagens/lapis.png" );
        btnEditar.setOnAction(e ->{
            Plataforma editar = tblPlataforma.getSelectionModel().getSelectedItem();
            TelaPlataforma telaPlataforma = new TelaPlataforma(editar);
            telaPlataforma.criarTela(stage);

        });

        Button btnExibir = criarBotao("Exibir", "/imagens/informacoes.png");
        Button btnExcluir = criarBotao("Excluir", "/imagens/cruz-pequeno.png");
        btnExcluir.setOnAction(e -> {
            Alert confirmaExclusão = new Alert(Alert.AlertType.CONFIRMATION);
            confirmaExclusão.setTitle("Exclusão");
            confirmaExclusão.setHeaderText("Você está excluindo um jogo.");
            confirmaExclusão.setContentText("Deseja Continuar?");

            Optional<ButtonType> resposta = confirmaExclusão.showAndWait();
            ButtonType botaoSelecionar = resposta.get();

            Plataforma plataformaExcluir = tblPlataforma.getSelectionModel().getSelectedItem();
            if(plataformaExcluir == null){
                Alert alertaPlataNaoSelecionado = new Alert(Alert.AlertType.WARNING);
                alertaPlataNaoSelecionado.setTitle("Exclusão de plataforma");
                alertaPlataNaoSelecionado.setHeaderText("Você não selecionou uma Plataforma");
                alertaPlataNaoSelecionado.showAndWait();
                return;
            }
            if(botaoSelecionar == ButtonType.OK){
                int resultado = PlataformaRepository.excluir(plataformaExcluir.getId());
                tblPlataforma.setItems(PlataformaRepository.getPlataforma());


            }
        });

        painelBotoes.getChildren().addAll(btnAdicionar, btnEditar, btnExibir, btnExcluir);

        tblPlataforma.getColumns().addAll(colunaId, colunaNomePlataforma,colunaFabricante, colunaData_P, colunaValor);

        painelPlataforma.getChildren().addAll(lblTitulo, linha, tblPlataforma, painelBotoes);

        return  painelPlataforma;

    }
    private Button criarBotao(String textoBotao, String urlImagem){
        Image image = new Image(getClass().getResourceAsStream(urlImagem));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        Button button = new Button();
        button.setText(textoBotao);
        button.setGraphic(imageView);
        button.setPrefHeight(50);
        button.setPrefWidth(100);

        button.setContentDisplay(ContentDisplay.TOP);
        return button;
    }
}
