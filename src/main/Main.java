package main;

import dao.AlunoDAO;
import dao.TreinoDAO;
import model.Aluno;
import model.Treino;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final AlunoDAO alunoDAO = new AlunoDAO();
    private static final TreinoDAO treinoDAO = new TreinoDAO();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE GERENCIAMENTO DE ACADEMIA ===");
            System.out.println("1. Gerenciar Alunos");
            System.out.println("2. Gerenciar Treinos");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> menuAlunos();
                case 2 -> menuTreinos();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);
    }

    private static void menuAlunos() {
        int op;
        do {
            System.out.println("\n-- Menu Alunos --");
            System.out.println("1. Cadastrar novo aluno");
            System.out.println("2. Listar alunos");
            System.out.println("3. Buscar aluno por CPF");
            System.out.println("4. Atualizar aluno");
            System.out.println("5. Excluir aluno");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> {
                    Aluno aluno = new Aluno();
                    System.out.print("Nome: ");
                    aluno.setNome(sc.nextLine());

                    // Validação do CPF
                    String cpf;
                    do {
                        System.out.print("CPF (somente números - 11 dígitos): ");
                        cpf = sc.nextLine();
                    } while (!cpf.matches("\\d{11}"));
                    aluno.setCpf(cpf);

                    System.out.print("Data Nascimento (YYYY-MM-DD): ");
                    aluno.setDataNascimento(sc.nextLine());

                    // Validação do telefone
                    String telefone;
                    do {
                        System.out.print("Telefone ((xx)-xxxxx-xxxx): ");
                        telefone = sc.nextLine();
                    } while (!telefone.matches("\\(\\d{2}\\)-\\d{5}-\\d{4}"));
                    aluno.setTelefone(telefone);

                    System.out.print("E-mail: ");
                    aluno.setEmail(sc.nextLine());

                    alunoDAO.cadastrarAluno(aluno);
                    System.out.println("Aluno cadastrado com sucesso!");
                }
                case 2 -> {
                    List<Aluno> alunos = alunoDAO.listarAlunos();
                    System.out.println("\n--- Lista de Alunos ---");
                    alunos.forEach(a ->
                        System.out.printf("ID: %d | Nome: %s | CPF: %s\n", a.getId(), a.getNome(), a.getCpf())
                    );
                }
                case 3 -> {
                    System.out.print("CPF (somente números): ");
                    String cpfBusca = sc.nextLine();
                    Aluno a = alunoDAO.buscarPorCPF(cpfBusca);
                    if (a != null) {
                        System.out.printf("Aluno encontrado: %s | ID: %d\n", a.getNome(), a.getId());
                    } else {
                        System.out.println("Aluno não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("ID do aluno a atualizar: ");
                    int id = sc.nextInt(); sc.nextLine();
                    Aluno a = new Aluno();
                    a.setId(id);

                    System.out.print("Nome: ");
                    a.setNome(sc.nextLine());

                    // Validação do CPF
                    String cpf;
                    do {
                        System.out.print("CPF (somente números - 11 dígitos): ");
                        cpf = sc.nextLine();
                    } while (!cpf.matches("\\d{11}"));
                    a.setCpf(cpf);

                    System.out.print("Data Nascimento (YYYY-MM-DD): ");
                    a.setDataNascimento(sc.nextLine());

                    // Validação do telefone
                    String telefone;
                    do {
                        System.out.print("Telefone ((xx)-xxxxx-xxxx): ");
                        telefone = sc.nextLine();
                    } while (!telefone.matches("\\(\\d{2}\\)-\\d{5}-\\d{4}"));
                    a.setTelefone(telefone);

                    System.out.print("E-mail: ");
                    a.setEmail(sc.nextLine());

                    alunoDAO.atualizarAluno(a);
                    System.out.println("Aluno atualizado com sucesso!");
                }
                case 5 -> {
                    System.out.print("ID do aluno: ");
                    alunoDAO.excluirAluno(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Aluno excluído com sucesso!");
                }
            }
        } while (op != 0);
    }

    private static void menuTreinos() {
        int op;
        do {
            System.out.println("\n-- Menu Treinos --");
            System.out.println("1. Cadastrar novo treino");
            System.out.println("2. Listar treinos de um aluno");
            System.out.println("3. Atualizar treino");
            System.out.println("4. Excluir treino");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> {
                    Treino t = new Treino();
                    System.out.print("ID do Aluno: ");
                    t.setAlunoId(sc.nextInt()); sc.nextLine();
                    System.out.print("Tipo de Treino: ");
                    t.setTipoTreino(sc.nextLine());
                    System.out.print("Descrição: ");
                    t.setDescricao(sc.nextLine());
                    System.out.print("Duração (minutos): ");
                    t.setDuracaoMinutos(sc.nextInt()); sc.nextLine();
                    System.out.print("Data de Início (YYYY-MM-DD): ");
                    t.setDataInicio(sc.nextLine());

                    treinoDAO.cadastrarTreino(t);
                    System.out.println("Treino cadastrado com sucesso!");
                }
                case 2 -> {
                    System.out.print("ID do aluno: ");
                    int id = sc.nextInt(); sc.nextLine();
                    List<Treino> treinos = treinoDAO.listarTreinosDoAluno(id);
                    System.out.println("\n--- Lista de Treinos ---");
                    treinos.forEach(t ->
                        System.out.printf("Treino %d: %s (%s) %d min\n",
                            t.getId(), t.getTipoTreino(), t.getDataInicio(), t.getDuracaoMinutos())
                    );
                }
                case 3 -> {
                    Treino t = new Treino();
                    System.out.print("ID do treino a atualizar: ");
                    t.setId(sc.nextInt()); sc.nextLine();
                    System.out.print("Tipo de Treino: ");
                    t.setTipoTreino(sc.nextLine());
                    System.out.print("Descrição: ");
                    t.setDescricao(sc.nextLine());
                    System.out.print("Duração (minutos): ");
                    t.setDuracaoMinutos(sc.nextInt()); sc.nextLine();
                    System.out.print("Data de Início (YYYY-MM-DD): ");
                    t.setDataInicio(sc.nextLine());

                    treinoDAO.atualizarTreino(t);
                    System.out.println("Treino atualizado com sucesso!");
                }
                case 4 -> {
                    System.out.print("ID do treino: ");
                    treinoDAO.excluirTreino(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Treino excluído com sucesso!");
                }
            }
        } while (op != 0);
    }
}
