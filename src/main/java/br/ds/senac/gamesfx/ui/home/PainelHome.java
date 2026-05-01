package br.ds.senac.gamesfx.ui.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PainelHome {
    public VBox criarPainelHome(){
        VBox painelPrincipal = new VBox();
        painelPrincipal.setAlignment(Pos.TOP_CENTER);
        painelPrincipal.setPadding(new Insets(15,20,20,20));
        painelPrincipal.prefWidth(300);
        painelPrincipal.setStyle("-fx-background-color: #1C1C1C;");

        VBox painelTitulo = new VBox();
        painelTitulo.setAlignment(Pos.TOP_LEFT);


        Label lblTitulo = new Label("Seja Bem-Vindo!");
        lblTitulo.setAlignment(Pos.TOP_LEFT);
        lblTitulo.setStyle("-fx-font-size: 26;-fx-font-weight: bold; -fx-text-fill:#98FB98;" );

        painelTitulo.getChildren().addAll(lblTitulo, new Separator());


        //Imagem da aplicação

        Image imgLogo = new Image(getClass().getResourceAsStream("/Imagens/MAcaco.png"));
        ImageView ivLogo = new ImageView(imgLogo);
        ivLogo.setScaleX(1);
        ivLogo.setScaleY(1);

        Label lblNomeApp = new Label("GamesPlatinum");
        lblNomeApp.setStyle("-fx-font-weight: bold; -fx-font-size: 36; -fx-text-fill: #98FB98 ");
        Label lblDisc = new Label("Software de gerenciamento de jogos platinados | Versão - 1.0");
        lblDisc.setStyle("-fx-font-weight: regular; -fx-font-size: 22; -fx-text-fill: #98FB98 ");

        //painel logo aonde fica a imagem e titulo
        VBox painelLogo = new VBox();

        VBox.setVgrow(painelLogo, Priority.ALWAYS);
         painelLogo.setAlignment(Pos.CENTER);

         //criar o painel de contatos de suporte
        VBox painelContatos = new VBox(10);
        painelContatos.setStyle("-fx-background-color: #100C1C; -fx-border-width: 1; -fx-border-color: #ffffff; -fx-border-radius: 16; -fx-background-radius: 15");
        painelContatos.setMaxWidth(600);
        painelContatos.setPadding(new Insets(30));

        VBox.setMargin(painelContatos, new Insets(40, 10, 40, 10));


        Label lblTituliEmail = new Label("E-mail para suporte:");
        lblTituliEmail.setStyle("-fx-Text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 15;");

        Label lblEmail = new Label("suporte@gameapp.com.br:");
        lblEmail.setStyle("-fx-Text-fill: #ffffff");

        Label lblTituliTelefone = new Label("Telefone para suporte: ");
        lblTituliTelefone.setStyle("-fx-Text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 15");

        Label lblTelefone = new Label("(11)949834508");
        lblTelefone.setStyle("-fx-Text-fill: #ffffff;");

        painelContatos.getChildren().addAll(lblTituliEmail, lblEmail, lblTituliTelefone, lblTelefone);

        Label lblDesenvolvidoPor = new Label("Desenvolvido por JUJUGREGO - 2026");
        lblDesenvolvidoPor.setStyle("-fx-font-weight: bold; -fx-font-size: 20; -fx-Text-fill: #ffffff");
        painelLogo.getChildren().addAll(ivLogo,lblNomeApp,lblDisc, painelContatos);

        painelPrincipal.getChildren().addAll(painelTitulo,painelLogo,lblDesenvolvidoPor);

        return painelPrincipal;
    }
}
