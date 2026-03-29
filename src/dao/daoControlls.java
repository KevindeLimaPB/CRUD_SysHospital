package dao;

import Config.config;
import Service.CriptoDice;
import model.Consultas;
import model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class daoControlls {
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
                System.out.println("ERRO ao logar " + e.getMessage());
            }
            return null;
        }

        public void consultasRealizadas(Consultas consultas){
            String sql = "INSERT INTO consulta(id_doctor, id_patient, data_realizada, relatorio)VALUES(?,?,?,?)";
            try {
                conn = config.getConnection();
                stmt = conn.prepareStatement(sql);

                stmt.setInt(1, consultas.getMedico());
                stmt.setInt(2, consultas.getPaciente());
                stmt.setDate(3, java.sql.Date.valueOf(consultas.getData_realizada()));
                stmt.setString(4,consultas.getRelatorio());
                stmt.execute();
                stmt.close();
            }catch (SQLException e){
                System.out.println("ERRO ao coloca consulta " + e.getMessage());
            }
        }
    }
