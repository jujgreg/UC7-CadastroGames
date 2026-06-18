package br.ds.senac.gamesfx.data.repository;

import br.ds.senac.gamesfx.data.ConexaoSQLite;
import br.ds.senac.gamesfx.model.Estudio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstudioRepository {

    public ObservableList<Estudio> getEstudio(){
        String sql = "SELECT * FROM tb_studio";

        ObservableList<Estudio> listaEstudio = FXCollections.observableArrayList();

        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()){
                Estudio estudio = new Estudio();
                int id = rs.getInt("id");
                String nomeFundador = rs.getString("nomeFundador");
                String anoFundacao = rs.getString("anoFundacao");
                String paisOrigem = rs.getString("paisOrigem");
                String nomeStudio = rs.getString("nomeStudio");

                estudio.setId(id);
                estudio.setNomeFundador(nomeFundador);
                estudio.setAnoFundacao(anoFundacao);
                estudio.setPaisOrigem(paisOrigem);
                estudio.setNomeStudio(nomeStudio);

                listaEstudio.add(estudio);
            }
            ConexaoSQLite.fecharConexao();
            return listaEstudio;
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na leitura dos dados");
            e.printStackTrace();
            return null;
        }

    }

    public void salvar(Estudio estudio){
        String sql = "INSERT INTO tb_studio (nomeFundador, anoFundacao, paisOrigem, nomeStudio) " +
                "VALUES(?,?,?,?);";

        try{
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            stm.setString(1, estudio.getNomeFundador());
            stm.setString(2, estudio.getAnoFundacao());
            stm.setString(3, estudio.getPaisOrigem());
            stm.setString(4,estudio.getNomeStudio());
            stm.executeUpdate();
            ConexaoSQLite.fecharConexao();
        }catch (SQLException erro){
            System.out.println("Ocorreu um erro na gravação.");
            erro.printStackTrace();
        }
    }

    public int excluir(int id){
        String sql = "DELETE FROM tb_studio where id = ?";
        try{
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            stm.setInt(1, id);
            int resultado = stm.executeUpdate();
            ConexaoSQLite.fecharConexao();
            return resultado;
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }
    public void editar(Estudio estudio){
        String sql = "UPDATE tb_studio SET" +
                "nomeFundador = ?" +
                "anoFundacao = ?" +
                "paisOrigem = ?" +
                "nomeStudio = ?";
        try{
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            stm.setString(1, estudio.getNomeFundador());
            stm.setString(2,estudio.getAnoFundacao());
            stm.setString(3, estudio.getPaisOrigem());
            stm.setString(4, estudio.getNomeStudio());
            stm.executeUpdate();
            ConexaoSQLite.fecharConexao();
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na edição.");
            e.printStackTrace();
        }
    }


}
