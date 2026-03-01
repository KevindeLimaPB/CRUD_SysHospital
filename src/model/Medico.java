package model;

public class Medico extends Usuario{
    private String profissao;
    private String especialidade;
    private int idMedico;

    public Medico(int id, String nome, String email, String senha, String perfil,String profissao, String especialidade, int idMedico){
        super(id, nome, email,senha,perfil);
        this.profissao = profissao;
        this.especialidade = especialidade;
        this.idMedico = idMedico;
    }
    public Medico(){super();}

    public int getIdMedico() {return idMedico;}

    public void setIdMedico(int idMedico) {this.idMedico = idMedico;}

    public String getProfissao(){return profissao;}
    public void setProfissao(String profissao){this.profissao = profissao;}
    public String getEspecialidade(){return especialidade;}
    public void setEspecialidade(String especialidade){this.especialidade = especialidade;}
}
