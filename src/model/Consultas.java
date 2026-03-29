package model;

import java.time.LocalDate;

public class Consultas {
    private int id;
    private int medico;
    private int paciente;
    private LocalDate data_realizada;
    private String relatorio;

    public Consultas(int id, int medico, int paciente, LocalDate data_realizada, String relatorio){
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

    public int getMedico() {return medico;}
    public void setMedico(int medico) {this.medico = medico;}

    public int getPaciente() {return paciente;}
    public void setPaciente(int paciente) {this.paciente = paciente;}

    public LocalDate getData_realizada() {return data_realizada;}
    public void setData_realizada(LocalDate data_realizada) {this.data_realizada = data_realizada;}

    public String getRelatorio() {return relatorio;}
    public void setRelatorio(String relatorio) {this.relatorio = relatorio;}
}
