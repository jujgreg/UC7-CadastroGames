module br.ds.senac.gamesfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;
    requires org.controlsfx.controls;
    requires jdk.jdi;
    requires java.sql;

    opens br.ds.senac.gamesfx to javafx.fxml;
    opens br.ds.senac.gamesfx.model to javafx.base;
    exports br.ds.senac.gamesfx;
}