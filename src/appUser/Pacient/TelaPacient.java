package appUser.Pacient;

import InitMain.Main;
import Service.ScanEntry;
import appUser.Pacient.OptionsPacient.*;

public class TelaPacient {
    public void TelaPaciente(){
        int opcao;
        do {
                    System.out.println("*--- Tela Paciente ---*");
                    System.out.println("1 - Verificar consulta realizada");
                    System.out.println("2 - Mudar CPF");
                    System.out.println("3 - Deletar conta");
                    System.out.println("4 - sair");
                    opcao = ScanEntry.sc.nextInt();
                    ScanEntry.sc.nextLine();

                    if (opcao == 1){new BuscConsul().consulPacient();}

                    if (opcao == 2){new AttCpf().upCpf();}

                    if (opcao == 3){new RemovPacient().deletPacient();}

                    if (opcao == 4){
                        new Main().inicio();
                    }
        }while (opcao != 5);
    }
}
