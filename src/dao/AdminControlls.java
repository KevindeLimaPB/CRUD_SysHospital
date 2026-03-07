package dao;

import model.*;
import Config.config;
import Service.CriptoDice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminControlls {

    Connection conn;
    PreparedStatement stmt;

    public void cadastrarUser(Usuario usuario){
        String sql = "INSERT INTO usuario(nome, email, senha, perfil) VALUES (?, ?, ?, ?)";

        try {
            conn = config.getConnection();
            stmt = conn.prepareStatement(sql);

                        String
                    email = new CriptoDice().criptografia(usuario.getEmail()),
                password = new  CriptoDice().criptografia(usuario.getSenha());


            stmt.setString(1, usuario.getNome());
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, usuario.getPerfil());
            stmt.execute();
            stmt.close();
            System.out.println("✅Usuário criado com sucesso!");
        }catch (SQLException e){
            System.err.println("❎Erro ao cadastrar Usuário!");
        }
    }

    public void cadastrarPacient(Paciente paciente){
        String sql = "INSERT INTO paciente(id_usuario, cpf, data_nascimento) VALUES(?, ?, ?)";

        try {
            conn = config.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, paciente.getId_usuario());
            stmt.setString(2, paciente.getCpf());
            stmt.setDate(3, java.sql.Date.valueOf(paciente.getData_nascimento()));
            stmt.execute();
            stmt.close();
            System.out.println("✅Paciente criado com sucesso!");

        }catch (SQLException e){
            System.err.println("❎Erro ao cadastrar Paciente!");
        }

    }
}