package dao.modifications;

import model.Medico;
import model.Paciente;
import model.Usuario;
import Config.config;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class daoSelect {

    Connection conn;
    PreparedStatement stmt;

    public List<Usuario> listaUsers(){
        List<Usuario> user = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try {
            conn = config.getConnection();
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setPerfil(rs.getString("perfil"));
                user.add(u);
            }
        }catch (SQLException e){
            System.err.println("ERRO ao lista os Usuarios");
        }
        return user;
    }

    public List<Paciente> listaPacient(){
        List<Paciente> pacient = new ArrayList<>();
        String sql = "SELECT * FROM paciente";

        try{
            conn = config.getConnection();
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Paciente p = new Paciente();
                p.setId_usuario(rs.getInt("id_usuario"));
                p.setCpf(rs.getString("cpf"));
                p.setData_nascimento(rs.getDate("data_nascimento").toLocalDate());
                pacient.add(p);
            }
        }catch (SQLException e){
            System.err.println("ERRO ao Listar os Pacientes");
        }
        return  pacient;
    }

    public List<Medico> listaMedic(){
        List<Medico> medico = new ArrayList<>();
        String sql = "SELECT * FROM medico";
        try {
            conn = config.getConnection();
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Medico medic = new Medico();
                medic.setIdMedico(rs.getInt("Id_medico"));
                medic.setProfissao(rs.getString("profissao"));
                medic.setEspecialidade(rs.getString("especialidade"));
                medico.add(medic);
            }
        }catch (SQLException e){
            System.err.println("ERRO ao listar médicos!");
        }
        return medico;
    }
}
