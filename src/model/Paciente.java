package model;
import java.time.LocalDate;

public class Paciente extends Usuario{
    private String cpf;
    private LocalDate data_nascimento;
    private int id_usuario;

    public Paciente(int id,String nome, String email,String senha, String perfil, String cpf, LocalDate data_nascimento, int id_usuario){
        super(id,nome,email,senha,perfil);
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
        this.id_usuario = id_usuario;
    }
    public Paciente(){super();}

    public int getId_usuario() {return id_usuario;}
    public void setId_usuario(int id_usuario) {this.id_usuario = id_usuario;}
    public String getCpf(){return cpf;}
    public void setCpf(String cpf){this.cpf = cpf;}
    public LocalDate getData_nascimento(){return data_nascimento;}
    public void setData_nascimento(LocalDate data_nascimento){this.data_nascimento = data_nascimento;}
}
