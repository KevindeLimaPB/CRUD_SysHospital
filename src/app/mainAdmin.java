package app;

import dao.AdminDao;
import model.Medico;
import model.Paciente;
import model.Usuario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;
import Service.Servicos;

public class mainAdmin {
    public void exibirAdm(){
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
            System.out.println("*****Sistema de Hospital-ADMIN*****");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Listas do Hospital");
            System.out.println("3 - Marcar Consulta");
            System.out.println("4 - Atualizar dados");
            System.out.println("5 - Remove Usuário");
            System.out.println("6 - Marcar Consulta");
            System.out.println("7 - Sair do sistema");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
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
                    while (true) {
                        int opcoes = 0;
                        System.out.println("Escolha o perfil: ");
                        System.out.println("1 - Paciente");
                        System.out.println("2 - Médico");
                        opcoes = sc.nextInt();
                        sc.nextLine();
                        switch (opcoes) {
                            case 1:
                                System.out.println("Paciente");
                                usuario.setPerfil("Paciente");
                                System.out.println("Informe o CPF: ");
                                String cpf = sc.nextLine();
                                paciente.setCpf(cpf);
                                String criptoCpf = servicos.criptografia(cpf);

                                System.out.println("Informe sua data de nascimento: ");
                                String dateNascim = sc.nextLine();

                                DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                LocalDate datFormatada = LocalDate.parse(dateNascim, date);
                                paciente.setData_nascimento(datFormatada);

                                paciente.setNome(criptoName);
                                paciente.setEmail(criptoEmail);
                                paciente.setSenha(criptoSenha);
                                paciente.setPerfil("Paciente");
                                paciente.setCpf(criptoCpf);

                                dao.cadastrarUsuario(usuario);
                                dao.cadastrarPaciente(paciente);

                                usuarioHashMap.put(usuario.getId(), paciente);
                                pacienteHashMap.put(paciente.getIdPaciente(), paciente);
                                break;
                            case 2:
                                System.out.println("Médico");
                                System.out.println("Informe a profissão: ");
                                String profissao = sc.nextLine();


                                System.out.println("Informe a especialidade : ");
                                String especialidade = sc.nextLine();
                                medicoHashMap.put(medico.getIdMedico(), new Medico());

                                break;
                            default:
                                System.out.println("Opção Inválida");
                                break;

                        }
                        break;
                    }
            }
        }while (opcao != 7);
    }
}
