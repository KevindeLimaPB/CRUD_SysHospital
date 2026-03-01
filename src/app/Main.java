package app;
import Config.config;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        //ADMIN_EMAIL=devHospital@gmail.com
        //ADMIN_SENHA=hospital1234

        mainAdmin adm = new mainAdmin();
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o email: ");
        String email = sc.nextLine();
        System.out.println("Informe a senha: ");
        String senha = sc.nextLine();

       if (email.equals(config.getAdmEmail()) && senha.equals(config.getAdmSenha())){
           System.out.println("Entrou");
                adm.exibirAdm();
       }else {
           System.out.println("ERROU");
       }
    }
}
