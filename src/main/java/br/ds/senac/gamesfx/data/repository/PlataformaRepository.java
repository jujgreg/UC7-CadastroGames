package br.ds.senac.gamesfx.data.repository;

import br.ds.senac.gamesfx.data.ConexaoSQLite;
import br.ds.senac.gamesfx.model.Plataforma;
import br.ds.senac.gamesfx.ui.plataforma.PainelPlataforma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlataformaRepository {
     public static ObservableList<Plataforma> getPlataforma() {

         String sql = "SELECT * FROM tb_plataforma";

         ObservableList<Plataforma> listaPlataforma = FXCollections.observableArrayList();

         Plataforma plataforma;
         try {
             PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
             ResultSet rs = stm.executeQuery();

             while (rs.next()) {
                 plataforma = new Plataforma();
                 int id = rs.getInt("id");
                 String NomePlataforma = rs.getString("nomePlataforma");
                 String fabricante = rs.getString("fabricante");
                 String date_P = rs.getString("data_P");
                 double valor = rs.getDouble("valor");

                 plataforma.setId(id);
                 plataforma.setNomePlataforma(NomePlataforma);
                 plataforma.setFabricante(fabricante);
                 plataforma.setData_P(date_P);
                 plataforma.setValor(valor);

                 listaPlataforma.add(plataforma);
             }
             ConexaoSQLite.fecharConexao();
             return listaPlataforma;

         } catch (SQLException e) {
             System.out.println("Ocorreu um erro na leitura dos dados");
             e.printStackTrace();
             return null;
         }


     }
    public void salvar (Plataforma plataforma){
        String sql = "INSERT INTO tb_plataforma(nomePlataforma, fabricante, data_P, valor)" +
                "VALUES(?, ?, ?, ?);";
        try{
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            stm.setString(1, plataforma.getNomePlataforma());
            stm.setString(2, plataforma.getFabricante());
            stm.setString(3, plataforma.getData_P().toString());
            stm.setDouble(4, plataforma.getValor());
            stm.executeUpdate();
            ConexaoSQLite.fecharConexao();
        }catch (SQLException e){
            System.out.println("Ocorreu um erro na Gravação ");
            e.printStackTrace();
        }
    }

    public int getTotalPlataformas(){
         String sql = "SELECT COUNT(id) as total_plataformas FROM tb_platafromas";
         try{
             PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
             ResultSet rs = stm.executeQuery();
             rs.next();
             int total = rs.getInt("total_plataforma");
             ConexaoSQLite.fecharConexao();
             return total;
         }catch (SQLException e){
             e.printStackTrace();
             return 0;
         }
    }


    public static int excluir(int id) {
        String sql = "DELETE FROM tb_plataforma WHERE id = ?";

        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            stm.setInt(1, id);
            int resultado = stm.executeUpdate();

            ConexaoSQLite.fecharConexao();
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    public void editar(Plataforma plataforma){
         String sql = "UPDATE tb_plataforma SET"+
                 "nomePlataforma = ?"+
                 "fabricante = ?"+
                 "data_P = ?"+
                 "valor = ?"+
                 "WHERE id = ?;";
         try{
             PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
             stm.setString(1, plataforma.getNomePlataforma());
             stm.setString(2, plataforma.getFabricante());
             stm.setString(3, plataforma.getData_P().toString());
             stm.setDouble(4, plataforma.getValor());
             stm.executeUpdate();
             ConexaoSQLite.fecharConexao();
         }catch (SQLException e){
             System.out.println("Ocorreu um erro na Edição");
             e.printStackTrace();
         }
    }


}
