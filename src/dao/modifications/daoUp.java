package dao.modifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Config.config;
import model.Paciente;
import model.Usuario;

public class daoUp {

    Connection conn;
    PreparedStatement stmt;

    public void atualizarPerfil(Usuario usuario){
        String sql = "UPDATE usuario SET perfil = ? WHERE id = ?";
        try {
            conn = config.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getPerfil());
            stmt.setInt(2, usuario.getId());
            stmt.executeUpdate();
            stmt.close();
        }catch (SQLException e){
            System.err.println("Não foi possível altera o perfil!" + e);
        }
    }
}
