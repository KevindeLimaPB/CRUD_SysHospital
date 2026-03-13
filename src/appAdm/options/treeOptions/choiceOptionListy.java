package appAdm.options.treeOptions;

import Service.ScanEntry;
import appAdm.options.treeOptions.ListyOptions.*;
import appAdm.telas.telaAdm;

public class choiceOptionListy {
    public void choiceOption() {
        int opcao;

        do {
            System.out.println("-------------------------");
            System.out.println("| 1 - Lista de Usuários");
            System.out.println("| 2 - Lista de Pacientes");
            System.out.println("| 3 - Lista de Médicos");
            System.out.println("| 4 - Lista de Consultas");
            System.out.println("| 5 - Voltar");
            System.out.println("-------------------------");
            opcao = ScanEntry.sc.nextInt();
            ScanEntry.sc.nextLine();

            if (opcao == 1){
                System.out.println("Lista dos Usuário");
                new listyUser().lista();
            }
            if (opcao == 2){
                System.out.println("Lista dos Pacientes");
                new listyPacient().listaPaciente();
            }
            if (opcao == 3){
                System.out.println("Lista dos Médicos");
                new listyMedic().listaMedic();
            }
            if (opcao == 4){
                System.out.println("Lista das Consultas");
                new listyConsults().listaConsult();
            }
            if (opcao == 5){
              System.out.println("Voltou");
              new telaAdm().exibirAdmin();
            }
        }while (opcao != 5);
    }
}
