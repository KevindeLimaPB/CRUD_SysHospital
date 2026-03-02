package app.telas;

import dao.AdminDao;
import model.Medico;
import model.Paciente;
import model.Usuario;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;
import Service.Servicos;
public class telaAdm {
    public void exibirAdmin() {
        AdminDao dao = new AdminDao();
        Scanner sc = new Scanner(System.in);
        Usuario usuario = new Usuario();
        Paciente paciente = new Paciente();
        Servicos servicos = new Servicos();
        Medico medico = new Medico();
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
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("****CADASTRAR USUÁRIO****");
                    System.out.println("Informe o nome: ");
                    String nome = sc.nextLine();
                    String criptoName = servicos.criptografia(nome);
                    usuario.setNome(nome);

                    System.out.println("Informe o email: ");
                    String email = sc.nextLine();
                    String criptoEmail = servicos.criptografia(email);
                    usuario.setEmail(email);

                    System.out.println("Informe a senha: ");
                    String senha = sc.nextLine();
                    String criptoSenha = servicos.criptografia(senha);
                    usuario.setSenha(senha);

                    paciente.setNome(criptoName);
                    paciente.setEmail(criptoEmail);
                    paciente.setSenha(criptoSenha);
                    paciente.setPerfil("Paciente");
                    usuarioHashMap.put(usuario.getId(), paciente);
                    dao.cadastrarUsuario(usuario);
                    break;
                case 2:
                    System.out.println("****ESCOLHA O PERFIL****");
                    while (true){
                        int opcoes;
                        System.out.println("1 - Paciente");
                        System.out.println("2 - Médico");
                        opcoes = sc.nextInt();
                        sc.nextLine();
                        switch (opcoes) {
                            case 1:
                                usuario.setPerfil("Paciente");
                                System.out.println("INFORME O ID: ");
                                int id = sc.nextInt();

                                System.out.println("Informe o CPF: ");
                                String cpf = sc.nextLine();
                                paciente.setCpf(cpf);
                                String criptoCpf = servicos.criptografia(cpf);
                                sc.nextLine();
                                boolean dataValida = false;
                                DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                                while (!dataValida) {
                                    System.out.println("Informe sua data de nascimento(dd/mm/yyyy): ");
                                    String dateNascim = sc.nextLine();
                                    try {
                                        LocalDate datFormatada = LocalDate.parse(dateNascim, date);
                                        paciente.setData_nascimento(datFormatada);
                                        pacienteHashMap.put(id, paciente);
                                        dataValida = true;
                                    } catch (DateTimeParseException e) {
                                        System.out.println("Erro: Formato de data inválida");
                                    }
                                    dao.cadastrarPaciente(id, paciente);
                                }
                                break;
                            case 2:
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                        break;
                    }
            }
        }while (opcao != 7);
    }
}
