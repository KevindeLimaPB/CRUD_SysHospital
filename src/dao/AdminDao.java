package dao;
import Config.config;
import Service.Servicos;
import model.Consultas;
import model.Usuario;
import model.Paciente;
import model.Medico;

import java.security.SecureRandom;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//PARTE EM MANUTENÇÃO
public class AdminDao {
    Servicos servicos = new Servicos();
    public void cadastrarUsuario(Usuario usuario){

        String sql = "INSERT INTO usuario (nome, email, senha, perfil) VALUES (?, ? , ?, ?)";

        String emailCripto = servicos.criptografia(usuario.getEmail()), nomeCripto = servicos.criptografia(usuario.getNome());
        String senhaCripto = servicos.criptografia(usuario.getSenha()), perfilCripto = servicos.criptografia(usuario.getPerfil());

        try(Connection conn = config.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, nomeCripto);
            stmt.setString(2, emailCripto);
            stmt.setString(3, senhaCripto);
            stmt.setString(4, perfilCripto);
            stmt.executeUpdate();
            System.out.println("✅O novo usuário foi adicionado com sucesso");
        }catch (SQLException e){
            System.err.println("❎Erro ao adicionar cliente " + e.getMessage());
        }
    }

    public void cadastrarPaciente(int id_usuario, Paciente paciente) {

        //MANUTENÇÂO - ERRO AO INSERIR
        String sqlPacient = "INSERT INTO paciente (id_usuario, cpf, data_nascimento) VALUES (?, ?, ?);";
        String perfilUser = "UPDATE usuario SET perfil = ? WHERE id = ?;";
        Connection conn = null;

        try {
            conn = config.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sqlPacient, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement stmtUser = conn.prepareStatement(perfilUser, PreparedStatement.RETURN_GENERATED_KEYS)) {
                String cpfCripto = servicos.criptografia(paciente.getCpf());
                String dateCripto = paciente.getData_nascimento().toString();
                String criptoDate = servicos.criptografia(dateCripto);
                stmt.setInt(1, id_usuario);
                stmt.setString(2, cpfCripto);
                stmt.setObject(3, criptoDate);//setObject é perfeito para o LocalDate
                stmt.executeUpdate();

                stmtUser.setString(1, "Paciente");
                stmtUser.setInt(2, id_usuario);
                stmtUser.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                if (conn != null) {
                    try {
                        conn.rollback();
                        System.out.println("Sujeia limpa");
                    }catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cadastrarMedico(int idMedico, Medico medico){
        String sqlMedico = "INSERT INTO medico(id_medico, profissao, especialidade) VALUES (?, ?, ?)";
        try(Connection conn = config.getConnection();
        PreparedStatement stmtMedico = conn.prepareStatement(sqlMedico, PreparedStatement.RETURN_GENERATED_KEYS)){
            stmtMedico.setInt(1, idMedico);
            stmtMedico.setString(2, medico.getProfissao());
            stmtMedico.setString(3, medico.getEspecialidade());
            stmtMedico.executeUpdate();
        }catch (SQLException e){
            System.err.println("❎Erro ao adiciona médico " + e.getMessage());
        }
    }

    public void marcarConsulta(Consultas consultas) {
        String sql = "INSERT INTO consulta(id_doctor ,id_patient ,data_realizada , relatorio) VALUES (?,?,?,?)";
        try(Connection conn = config.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, consultas.getMedico().getId());
            stmt.setInt(2,consultas.getPaciente().getId());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(consultas.getData_realizada()));
            stmt.setString(4, consultas.getRelatorio());
            stmt.executeUpdate();
        }catch (SQLException e){
            System.err.println("❎Erro ao marcar á consulta " + e.getMessage());
        }
    }

    public List<Usuario> listaUsuarios(){
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try(Connection conn = config.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setPerfil(rs.getString("perfil"));
                listaUsuarios.add(usuario);

            }
        }catch (SQLException e){
            System.err.println("❎Erro ao Listar os clientes");
        }
        return listaUsuarios;
    }

    public List<Paciente> ListPacientes(){
        String sqlpac = "SELECT * FROM paciente";
        ArrayList<Paciente> listPacientes =  new ArrayList<>();

        try(Connection conn = config.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sqlpac)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Paciente p = new Paciente();
                p.setCpf(rs.getString("cpf"));
                LocalDate data = rs.getObject("data_nascimento", LocalDate.class);
                p.setData_nascimento(data);
                listPacientes.add(p);
            }
        }catch (SQLException e){
            System.err.println("❎Erro ao lista pacientes");
        }
        return listPacientes;
    }

    public List<Medico> listMedico() {
        String sql = "SELECT * FROM medico";
        ArrayList<Medico> medicos = new ArrayList<>();
        try(Connection conn =  config.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Medico m = new Medico();
                m.setProfissao(rs.getString("profissao"));
                m.setEspecialidade(rs.getString("especialidade"));
                medicos.add(m);
            }
        }catch (SQLException e){
            System.err.println("❎Erro ao listar médicos!");
        }
        return medicos;
    }
    //OBSERVAÇÃO
    public List<Consultas> listConsultas(){
        ArrayList<Consultas> consultas = new ArrayList<>();
        String sql = "SELECT c.id, c.data_realizada, c.relatorio," +
                "u_med.nome AS nome_medico, " +
                "u_pac.nome AS nome_paciente " +
                " FROM consulta c " +
                "INNER JOIN medico m ON c.id_doctor = m.id " +
                "INNER JOIN usuario u_med ON m.id_medico = u_med.id " +
                "INNER JOIN paciente p ON c.id_patient = p.id " +
                "INNER JOIN usuario u_pac ON p.id_usuario = u_pac.id ";
        try(Connection conn = config.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Consultas con = new Consultas();
                con.setId(rs.getInt("id"));
                con.setRelatorio(rs.getString("relatorio"));
                java.sql.Date date = rs.getDate("data_realizada");

                if (date != null){
                    con.setData_realizada(date.toLocalDate().atStartOfDay());//o atStartOfDat quebra as horas do LocalDatetime para meia noite
                }
                Medico medico = new Medico();
                medico.setNome(rs.getString("nome_medico"));
                con.setMedico(medico);

                Paciente paciente = new Paciente();
                paciente.setNome(rs.getString("nome_paciente"));
                con.setPaciente(paciente);



                consultas.add(con);
            }
        }catch (SQLException e){
            System.err.println("❎Erro ao lista consultas " + e.getMessage());
        }
        return consultas;
    }
    //OBSERVAÇÂO
    public void atualizarEmailDoUsuario(Usuario usuario){
        //OBSERVAÇÂO
        String sql = "UPDATE usuario SET email = ? where id = ?";
        try(Connection conn = config.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, usuario.getEmail());
            stmt.setInt(2, usuario.getId());
            int contador = stmt.executeUpdate();
            if (contador > 0){
                System.out.println("✅Email atualizado");
            }else {
                System.out.println("❎Erro ao mudar email");
            }
        }catch (SQLException e){
            System.err.println("❎Erro na atualização");
        }
    }

    public void atualizarSenhaDoUsuario(Usuario usuario){
        String sql = "UPDATE usuario SET senha = ? WHERE id = ?";

        try(Connection conn = config.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getSenha());
            stmt.setInt(2, usuario.getId());
            int gerador = stmt.executeUpdate();

            if (gerador > 0) {
                System.out.println("✅Senha atualizada");
            }else {
                System.out.println("❎ERRO 400");
            }
        }catch (SQLException e){
            System.err.println("❎ERRO ao atualizar " + e.getMessage());
        }
    }
    //consertados!
    public void deletarMedico(int id){
        String sqlM = "DELETE FROM medico WHERE id_medico = ?";
        try(Connection conn = config.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlM)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
                System.out.println("Médico removido");
        }catch (SQLException e) {
            System.err.println("ERRO");
        }
    }

    public void deletarPaciente(int id){
        String sqlP = "DELETE FROM paciente WHERE id_usuario = ?";
        try(Connection conn = config.getConnection();
            PreparedStatement stmtP = conn.prepareStatement(sqlP)){
                stmtP.setInt(1, id);
                stmtP.executeUpdate();
            }catch (SQLException e){
                System.out.println("ERRO");
            }
        }
    }
