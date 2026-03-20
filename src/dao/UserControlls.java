package dao;

import Config.config;
import Service.CriptoDice;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserControlls {
        Connection conn;
        PreparedStatement stmt;
        public void criandoConta(Usuario usuario) {
            String sql = "INSERT INTO usuario(nome, email, senha, perfil) VALUES (?, ?, ?, ?)";
            try {
                conn = config.getConnection();
                stmt = conn.prepareStatement(sql);
                String
                        email = new CriptoDice().criptografia(usuario.getEmail()),
                        password = new CriptoDice().criptografia(usuario.getSenha());
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, email);
                stmt.setString(3, password);
                stmt.setString(4, usuario.getPerfil());
                stmt.execute();
                stmt.close();
                System.out.println("✅Usuário criado com sucesso!");
            } catch (SQLException e) {
                System.err.println("❎Erro ao cadastrar Usuário!");
            }
        }

        public Usuario validacaoLog(String email, String senha){
            String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

            try {

                conn = config.getConnection();
                stmt = conn.prepareStatement(sql);

                stmt.setString(1, email);
                stmt.setString(2, senha);

                ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        Usuario user = new Usuario();
                        user.setEmail(rs.getString("email"));
                        user.setSenha(rs.getString("senha"));
                        user.setPerfil(rs.getString("perfil"));
                        return user;
                    }

            }catch (SQLException e){
                System.out.println("ERRO ao logar");
            }
            return null;
        }
    }
