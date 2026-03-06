package model;

import java.time.LocalDateTime;

public class Consultas {
    private int id;
    private Medico medico;
    private Paciente paciente;
    private LocalDateTime data_realizada;
    private String relatorio;

    public Consultas(int id, Medico medico, Paciente paciente, LocalDateTime data_realizada, String relatorio){
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.data_realizada = data_realizada;
        this.relatorio = relatorio;
    }
    public Consultas(){}

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public Medico getMedico() {return medico;}
    public void setMedico(Medico medico) {this.medico = medico;}

    public Paciente getPaciente() {return paciente;}
    public void setPaciente(Paciente paciente) {this.paciente = paciente;}

    public LocalDateTime getData_realizada() {return data_realizada;}
    public void setData_realizada(LocalDateTime data_realizada) {this.data_realizada = data_realizada;}

    public String getRelatorio() {return relatorio;}
    public void setRelatorio(String relatorio) {this.relatorio = relatorio;}
}
