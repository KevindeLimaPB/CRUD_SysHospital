package model;
import java.time.LocalDate;

public class Paciente {
    private String cpf;
    private LocalDate data_nascimento;
    private int id_usuario;


    public int getId_usuario() {return id_usuario;}
    public void setId_usuario(int id_usuario) {this.id_usuario = id_usuario;}
    public String getCpf(){return cpf;}
    public void setCpf(String cpf){this.cpf = cpf;}
    public LocalDate getData_nascimento(){return data_nascimento;}
    public void setData_nascimento(LocalDate data_nascimento){this.data_nascimento = data_nascimento;}
}
