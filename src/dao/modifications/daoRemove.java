package dao.modifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Config.config;

public class daoRemove {

    Connection conn;
    PreparedStatement stmt;

    public void removeUser(int id){
        String sql = "DELETE FROM usuario WHERE id = ?";

        try {
            conn = config.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                System.out.println("✅Usuário Removido!");
            }else {
                System.out.println("ID não encontrado!");
            }

        }catch (SQLException e){
            System.err.println("ERRO ao deletar");
        }
    }
}
