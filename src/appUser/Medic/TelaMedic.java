package appUser.Medic;

import Service.ScanEntry;
import appUser.Medic.OptionsMedic.*;
import InitMain.Main;

public class TelaMedic {
    public void telaMedic(){
        int opcao;
        do {
            System.out.println("*--- Tela Médica ---*");
            System.out.println("1 - informa consultas realizadas");
            System.out.println("2 - Deletar conta");
            System.out.println("3 - Sair");
            opcao = ScanEntry.sc.nextInt();
            ScanEntry.sc.nextLine();

            if (opcao == 1 ){
                new Consults().inform();
            }

            if (opcao == 2){
                new DeleteMedic().deletarMedic();
            }

            if (opcao == 3) {
                new Main().inicio();
            }
        }while (opcao != 3);
    }
}
