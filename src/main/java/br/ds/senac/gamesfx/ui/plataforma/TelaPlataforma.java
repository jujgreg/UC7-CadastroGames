package br.ds.senac.gamesfx.ui.plataforma;

import br.ds.senac.gamesfx.data.repository.PlataformaRepository;
import br.ds.senac.gamesfx.model.Plataforma;
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
import java.util.Optional;

public class TelaPlataforma {
    private TextField tfId = new TextField();
    private TextField tfNomePlataforma = new TextField();
    private TextField tfFabricante = new TextField();
    private TextField tfData_P = new TextField();
    private TextField tfValor = new TextField();

    public TelaPlataforma(Plataforma plataforma){

        tfId.setText(String.valueOf(plataforma.getId()));
        tfNomePlataforma.setText(String.valueOf(plataforma.getNomePlataforma()));
        tfFabricante.setText(String.valueOf(plataforma.getFabricante()));
        tfData_P.setText(plataforma.getData_P());
        tfValor.setText(String.valueOf(plataforma.getValor()));

    }

    public TelaPlataforma(){}

        public void criarTela(Stage stagePai){
            Stage stage = new Stage();
            stage.initOwner(stagePai);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setMaxWidth(500);
            stage.setTitle("cadastro de Estudio");

            BorderPane raiz = new BorderPane();

            raiz.setTop(criarPainelTitulo());
            raiz.setCenter(criarFormulario());
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
        public VBox criarFormulario(){
            VBox formulario = new VBox();
            formulario.setPadding(new Insets(10));

            GridPane gridFormulario = new GridPane();
            gridFormulario.setVgap(7);

            Label lblid = new Label("ID: ");
            tfId.setEditable(false);
            tfId.setDisable(true);

            Label lblNomePlatafomra = new Label("Plataforma: ");
            tfNomePlataforma.setPromptText("Ex. Xbox");

            Label lblfabriacante = new Label("fabricante: ");
            tfFabricante.setPromptText("Ex. china");

            Label lblData_P = new Label("Data de Lançamento: ");

            Label lblValor = new Label("Valor: ");
            tfValor.setPromptText("Ex. 199.99");

            gridFormulario.add(lblid, 0, 0);
            gridFormulario.add(tfId, 1, 0);
            gridFormulario.add(lblNomePlatafomra, 0, 1);
            gridFormulario.add(tfNomePlataforma, 1,1);
            gridFormulario.add(lblfabriacante, 0,2);
            gridFormulario.add(tfFabricante,1,2);
            gridFormulario.add(lblData_P,0,3);
            gridFormulario.add(tfData_P, 1,3);
            gridFormulario.add(lblValor,0,4);
            gridFormulario.add(tfValor,1,4);

            formulario.getChildren().addAll(gridFormulario);

            return formulario;
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
    private HBox criarRodape(Stage stage) {
        HBox rodape = new HBox();

        rodape.setPadding(new Insets(10, 5, 10, 10));
        rodape.setStyle("-fx-background-color:#6B6D70 ; ");

        Button btnSalvar = criarBotao("salvar", "/imagens/salve-.png");
        btnSalvar.setTooltip(new Tooltip("Salvar"));
        Button btnApagar = criarBotao("Apagar", "/imagens/risonho.png");
        btnApagar.setTooltip(new Tooltip("Apagar"));

        rodape.setAlignment(Pos.BASELINE_RIGHT);

        rodape.setSpacing(10);
        btnSalvar.setTooltip(new Tooltip("Salva Dados da plataforma"));

        btnSalvar.setOnAction(evento ->{
            Plataforma plataforma = new Plataforma();
            plataforma.setNomePlataforma(tfNomePlataforma.getText());
            plataforma.setFabricante(tfFabricante.getText());
            plataforma.setData_P(tfData_P.getText());
            try {
                plataforma.setValor(Double.parseDouble(tfValor.getText().replace(",",".")));
            }catch (NumberFormatException e){
                Alert valorIncorreto = new Alert(Alert.AlertType.ERROR);
                valorIncorreto.setTitle("valor incorreto");
                valorIncorreto.setHeaderText("Ovalor deve ser apenas números e positivos");
                valorIncorreto.showAndWait();
                tfValor.requestFocus();
                }
            PlataformaRepository repository = new PlataformaRepository();

            if(tfId.getText().equals("")){
                repository.salvar(plataforma);

                Alert mensagemSalvar = new Alert(Alert.AlertType.CONFIRMATION);
                mensagemSalvar.setTitle("cadastro da plataforma");
                mensagemSalvar.setHeaderText("A Plataforma foi salva com sucesso");
                mensagemSalvar.setContentText("deseja cadastrar outra plataforma?");

                Optional<ButtonType> escolhaCadastro = mensagemSalvar.showAndWait();

                if(escolhaCadastro.get() == ButtonType.OK){
                    limparCampos();

                }else {
                    stage.close();
                }
            }else {
                plataforma.setId(Integer.parseInt(tfId.getText()));
                repository.editar(plataforma);

                Alert mensagemEditar = new Alert(Alert.AlertType.INFORMATION);
                mensagemEditar.setTitle("editar Plataforma");
                mensagemEditar.setHeaderText("a plataforma foi editada com sucesso");
                Optional<ButtonType> escolha = mensagemEditar.showAndWait();
                stage.close();
            }
            limparCampos();
        });
        rodape.getChildren().addAll(btnSalvar, btnApagar);
        return rodape;
    }
    private void limparCampos(){
        tfNomePlataforma.clear();
        tfValor.clear();
        tfFabricante.clear();
        tfData_P.clear();
        tfNomePlataforma.requestFocus();
    }

}
