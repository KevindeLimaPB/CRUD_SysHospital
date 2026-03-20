package InitMain;

import Logs.Password;
import Logs.createLog;
import Logs.entrada;
import Service.ScanEntry;

public class Main {
    public void inicio(){
        int opcao;
        do {
            System.out.println("****SEJA BEM-VINDO****");
            System.out.println("1 - Login");
            System.out.println("2 - Cria um login");
            System.out.println("3 - Esqueci a senha");
            opcao = ScanEntry.sc.nextInt();
            ScanEntry.sc.nextLine();

            if(opcao == 1){
                new entrada().entrad();
            }

            if (opcao == 2){
                new createLog().criarLog();
            }

            if (opcao == 3){
                new Password().passSenha();
            }
        }while (opcao != 3);
    }
}
