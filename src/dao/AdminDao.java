package dao;
import Config.config;
import Service.Servicos;
import model.Consultas;
import model.Usuario;
import model.Paciente;
import model.Medico;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;


//PARTE EM MANUTENÇÂO
public class AdminDao {

    public void cadastrarUsuario(Usuario usuario){
        Servicos servicos = new Servicos();
        String sql = "INSERT INTO usuario (nome, email, senha, perfil) VALUES (?, ? , ?, ?)";
        String emailCripto = servicos.criptografia(usuario.getEmail());//criptografia

        try(Connection conn = config.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, emailCripto);
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getPerfil());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                int idGerado = rs.getInt(1);
                usuario.setId(idGerado);
            }
            System.out.println("✅O novo usuário foi adicionado com sucesso");
        }catch (SQLException e){
            System.err.println("❎Erro ao adicionar cliente " + e.getMessage());
        }
    }

    public void cadastrarPaciente(Paciente paciente){
        //MANUTENÇÂO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        String sqlPacient = "INSERT INTO paciente (id_usuario, cpf, data_nascimento, historico_medico) VALUES (?, ?, ?)";

        try(Connection conn = config.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlPacient, PreparedStatement.RETURN_GENERATED_KEYS)){

            stmt.setInt(1, paciente.getId_usuario());
            stmt.setString(2, paciente.getCpf());
            stmt.set(3, paciente.getData_nascimento());
            stmt.setString(4, "Paciente");
            stmt.executeUpdate();
        }catch (SQLException e){
            System.err.println("❎Erro ao adiciona paciente " + e.getMessage());
        }
    }

    public void cadastrarMedico(Medico medico){
        String sqlUsuario = "INSERT INTO usuario(nome, email, senha, perfil) VALUES (?, ?, ?, ?)";

        String sqlMedico = "INSERT INTO medico(id_medico, profissao, especialidade) VALUES (?, ?, ?)";

        try(Connection conn = config.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getEmail());
            stmt.setString(3, medico.getSenha());
            stmt.setString(4, "Médico");
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                int idGerado = rs.getInt(1);
                medico.setIdMedico(idGerado);

                PreparedStatement stmtMedico = conn.prepareStatement(sqlMedico);
                stmtMedico.setInt(1, idGerado);
                stmtMedico.setString(2, medico.getProfissao());
                stmtMedico.setString(3, medico.getEspecialidade());
                stmtMedico.executeUpdate();
            }
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
                Timestamp timestamp = rs.getTimestamp("data_nascimento");
                p.setCpf(rs.getString("cpf"));
                p.setData_nascimento(LocalDate.from(timestamp.toLocalDateTime()));
                p.setHistorico(rs.getString("historico"));
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

    public void atualizarEmailDoUsuario(Usuario usuario){
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

    public void deletarMedico(int id){
        String sqlM = "DELETE FROM medico WHERE id_medico = ?";
        String sql = "DELETE FROM usuario WHERE id = ?";

        try(Connection conn = config.getConnection()) {
            conn.setAutoCommit(false);//O java sfd manda o banco para
            try(PreparedStatement stmt = conn.prepareStatement(sqlM);
            PreparedStatement statement = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
                statement.setInt(1 , id);
                statement.executeUpdate();
                conn.commit();
                System.out.println("Usuario removido");
            }catch (SQLException e){
                conn.rollback();
                System.out.println("ERRO");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarPaciente(int id){
        String sqlP = "DELETE FROM paciente WHERE id_usuario = ?";
        String sqlU = "DELETE FROM usuario WHERE id = ?";
        try(Connection conn = config.getConnection()){
            conn.setAutoCommit(false);
            try(PreparedStatement stmtP = conn.prepareStatement(sqlP);
            PreparedStatement stmtU = conn.prepareStatement(sqlU)) {
                stmtP.setInt(1, id);
                stmtP.executeUpdate();

                stmtU.setInt(1, id);
                stmtU.executeUpdate();
                conn.commit();
            }catch (SQLException e){
                conn.rollback();
                System.out.println("ERRO");
            }
            }catch (SQLException e){
            e.printStackTrace();
        }
    }
}