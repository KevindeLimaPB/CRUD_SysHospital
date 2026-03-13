package app.options.treeOptions.ListyOptions;


import model.Usuario;
import dao.modifications.daoSelect;
public class listyUser {

    daoSelect dao = new daoSelect();
    public void lista(){
        System.out.println("A Lista dos Usuários");

        for (Usuario user : dao.listaUsers()){
            System.out.println("ID: " + user.getId() + " Nome: " + user.getNome() + " Email: " + user.getEmail()
            + " Senha: " + user.getSenha() + " Perfil: " + user.getPerfil());
        }
    }
}
