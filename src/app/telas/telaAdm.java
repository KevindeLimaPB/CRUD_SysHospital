package app.telas;

import dao.*;
import model.*;
import java.time.*;
import java.util.HashMap;
import Service.*;
public class telaAdm {
    public void exibirAdmin() {

        HashMap<Integer, Usuario> usuarioHashMap = new HashMap<>();
        HashMap<Integer, Paciente> pacienteHashMap = new HashMap<>();
        HashMap<Integer, Medico> medicoHashMap = new HashMap<>();
        int opcao;

        do {
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - informa o perfil(P/M)");
            System.out.println("3 - Atualizar dados");
            System.out.println("4 - Listas do Hospital");
            System.out.println("5 - Consultas Realizadas");
            System.out.println("6 - Remove Usuário");
            System.out.println("7 - Sair do Sistema");
            opcao = ScanEntry.sc.nextInt();
            ScanEntry.sc.nextLine();


        }while (opcao != 7);
    }
}
