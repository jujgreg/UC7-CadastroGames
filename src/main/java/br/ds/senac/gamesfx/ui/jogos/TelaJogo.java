package br.ds.senac.gamesfx.ui.jogos;

import br.ds.senac.gamesfx.data.repository.EstudioRepository;
import br.ds.senac.gamesfx.data.repository.JogoRepository;
import br.ds.senac.gamesfx.data.repository.PlataformaRepository;
import br.ds.senac.gamesfx.model.Estudio;
import br.ds.senac.gamesfx.model.Jogo;
import br.ds.senac.gamesfx.model.Plataforma;
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

import javax.swing.*;
import java.time.LocalDate;
import java.util.Optional;


public class TelaJogo {

    private TextField tfId = new TextField();
    private TextField tfTitulo = new TextField();
    private TextField tfValor = new TextField();
    private ComboBox<Plataforma> comboPlataforma =  new ComboBox<>();
    private ComboBox<Estudio> comboEstudio =new ComboBox<>();
    private DatePicker dpDataLancamento = new DatePicker();
    private CheckBox cbFinalizado = new CheckBox();
    EstudioRepository repository = new EstudioRepository();
    PlataformaRepository repositoryP = new PlataformaRepository();


    public TelaJogo(Jogo jogo){

        tfId.setText(String.valueOf(jogo.getId()));
        tfTitulo.setText(jogo.getTitulo());
        tfValor.setText(String.valueOf(jogo.getPreco()));
        comboPlataforma.setItems(repositoryP.getPlataforma());
        comboEstudio.setItems(repository.getEstudio());
        dpDataLancamento.setValue(jogo.getDataLancamento());
        cbFinalizado.setSelected(jogo.isPlatinado());

    }

    public TelaJogo(){}


    public void criarTela(Stage stagePai){
        Stage stage = new Stage();
        stage.initOwner(stagePai);
        stage.initModality(Modality.APPLICATION_MODAL);


        stage.setMaxWidth(500);
      //  stage.setHeight(800);
        stage.setTitle("Cadastro de Jogo");

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



        painelTitulo.setPadding(new Insets (20,0,20,20));
        painelTitulo.setStyle("-fx-background-color:#363636; ");

        painelTitulo.setAlignment(Pos.CENTER_LEFT);



        Label lblTitulo = new Label("Cadastro de Jogos");

        lblTitulo.setStyle( "-fx-font-size: 28; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill:#98FB98;");
        painelTitulo.getChildren().addAll(lblTitulo);

        return painelTitulo;
    }

    private VBox criarFormulario(){





        VBox formulario = new VBox();
        formulario.setPadding(new Insets(10));
//====================================================================================================

        GridPane gridFormulario = new GridPane();
        gridFormulario.setVgap(7);


        //Criar os componentes para a grid

        Label lblid = new Label("ID: ");
        tfId.setEditable(false);
        tfId.setDisable(true);

        Label lblTitulo = new Label("Título: ");
        tfTitulo.setPromptText("Ex. Minecraft");

        Label lblPlataformas = new Label("Plataforma: ");
        comboPlataforma.setItems(repositoryP.getPlataforma());

        Label lblEstudios = new Label("Estudio: ");
        comboEstudio.setItems(repository.getEstudio());

        Label lblValor = new Label("Valor: ");
        tfValor.setPromptText("Ex. 9,99");

        Label lblLancamento = new Label("Data de Lançamento: ");

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
        gridFormulario.add(lblLancamento,0,5);
        gridFormulario.add(dpDataLancamento,1,5);
        gridFormulario.add(cbFinalizado,0, 6);
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
        btnSalvar.setTooltip(new Tooltip("Salvar dados do jogo"));

        btnSalvar.setOnAction(evento -> {
            Jogo jogo = new Jogo();
            jogo.setTitulo(tfTitulo.getText());
            jogo.setPlataforma(comboPlataforma.getValue().getNomePlataforma());
            jogo.setEstudio(comboEstudio.getValue().getNomeStudio());
            jogo.setDataLancamento(dpDataLancamento.getValue());
            jogo.setCategoria("Jogo");
            jogo.setPlatinado(cbFinalizado.isSelected());

            try {
                jogo.setPreco(Double.parseDouble(tfValor.getText().replace(",", ".")));
            }catch (NumberFormatException e) {

                Alert valorIncorreto = new Alert(Alert.AlertType.ERROR);
                valorIncorreto.setTitle("valor incorreto");
                valorIncorreto.setHeaderText("O valor deve ter apenas números!\nUltilize ponto ou virgula como centavos");
                valorIncorreto.showAndWait();
                tfValor.requestFocus();
                return;
            }

            // Criar o repositório para enviar o jogo

            JogoRepository repository = new JogoRepository();
            if (tfId.getText().equals("")){
                repository.salvar(jogo);

               Alert mensagemSalvar = new Alert(Alert.AlertType.CONFIRMATION);
               mensagemSalvar.setTitle("cadastro de jogos");
               mensagemSalvar.setHeaderText("o jogo foi gravado com sucesso");
               mensagemSalvar.setContentText("deseja cadastrar outro jogo?");

                Optional<ButtonType> escolhaCadastra = mensagemSalvar.showAndWait();

                if(escolhaCadastra.get() == ButtonType.OK){
                    limparCampos();

                }else {
                    stage.close();
                }


            }else{
                jogo.setId(Integer.parseInt(tfId.getText()));
                repository.editar(jogo);

                Alert mensagemEditar = new Alert(Alert.AlertType.INFORMATION);
                mensagemEditar.setTitle("Editar Jogo");
                mensagemEditar.setHeaderText("o jogo foi editado com sucesso");
            Optional<ButtonType> escolha = mensagemEditar.showAndWait();
                stage.close();


            }


        limparCampos();
        });
        rodape.getChildren().addAll(btnSalvar, btnApagar);
        return rodape;
    }
    private void limparCampos() {

        tfTitulo.clear();
        tfValor.clear();
        comboEstudio.setItems(null);
        comboPlataforma.setItems(null);
        cbFinalizado.setSelected(false);
        dpDataLancamento.setValue(LocalDate.now());
        tfTitulo.requestFocus();
    }
    }

