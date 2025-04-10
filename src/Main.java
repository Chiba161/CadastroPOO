import model.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    private static final PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
    private static final PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            while (true) {
                System.out.println("""
                    ==============================
                    1 - Incluir Pessoa
                    2 - Alterar Pessoa
                    3 - Excluir Pessoa
                    4 - Buscar pelo Id
                    5 - Exibir Todos
                    6 - Persistir Dados
                    7 - Recuperar Dados
                    0 - Finalizar Programa
                    ==============================""");
                
                String opcao = scanner.nextLine();
                
                switch (opcao) {
                    case "1" -> incluirPessoa();
                    case "2" -> alterarPessoa();
                    case "3" -> excluirPessoa();
                    case "4" -> buscarPorId();
                    case "5" -> exibirTodos();
                    case "6" -> persistirDados();
                    case "7" -> recuperarDados();
                    case "0" -> { return; }
                    default -> System.out.println("Opção inválida!");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void incluirPessoa() {
        System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
        String tipo = scanner.nextLine().toUpperCase();
        
        System.out.println("Digite o id da pessoa:");
        int id = Integer.parseInt(scanner.nextLine());
        
        System.out.println("Insira os dados...");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        try {
            if (tipo.equals("F")) {
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Idade: ");
                int idade = Integer.parseInt(scanner.nextLine());
                
                repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
                System.out.println("Pessoa física incluída com sucesso!");
            } else {
                System.out.print("CNPJ: ");
                String cnpj = scanner.nextLine();
                
                repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
                System.out.println("Pessoa jurídica incluída com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void alterarPessoa() {
        System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
        String tipo = scanner.nextLine().toUpperCase();
        
        System.out.println("Digite o id da pessoa:");
        int id = Integer.parseInt(scanner.nextLine());
        
        try {
            if (tipo.equals("F")) {
                PessoaFisica pf = repoFisica.obter(id);
                if (pf != null) {
                    System.out.println("Dados atuais:");
                    System.out.println("ID: " + pf.getId());
                    System.out.println("Nome: " + pf.getNome());
                    System.out.println("CPF: " + pf.getCpf());
                    System.out.println("Idade: " + pf.getIdade());
                    
                    System.out.println("Insira os novos dados...");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = Integer.parseInt(scanner.nextLine());
                    
                    repoFisica.alterar(new PessoaFisica(id, nome, cpf, idade));
                    System.out.println("Pessoa física alterada com sucesso!");
                } else {
                    System.out.println("Pessoa não encontrada!");
                }
            } else {
                PessoaJuridica pj = repoJuridica.obter(id);
                if (pj != null) {
                    System.out.println("Dados atuais:");
                    System.out.println("ID: " + pj.getId());
                    System.out.println("Nome: " + pj.getNome());
                    System.out.println("CNPJ: " + pj.getCnpj());
                    
                    System.out.println("Insira os novos dados...");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CNPJ: ");
                    String cnpj = scanner.nextLine();
                    
                    repoJuridica.alterar(new PessoaJuridica(id, nome, cnpj));
                    System.out.println("Pessoa jurídica alterada com sucesso!");
                } else {
                    System.out.println("Pessoa não encontrada!");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void excluirPessoa() {
        System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
        String tipo = scanner.nextLine().toUpperCase();
        
        System.out.println("Digite o id da pessoa:");
        int id = Integer.parseInt(scanner.nextLine());
        
        try {
            boolean sucesso;
            if (tipo.equals("F")) {
                sucesso = repoFisica.excluir(id);
            } else {
                sucesso = repoJuridica.excluir(id);
            }
            
            System.out.println(sucesso 
                ? "Pessoa excluída com sucesso!" 
                : "Pessoa não encontrada!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void buscarPorId() {
        System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
        String tipo = scanner.nextLine().toUpperCase();
        
        System.out.println("Digite o id da pessoa:");
        int id = Integer.parseInt(scanner.nextLine());
        
        try {
            if (tipo.equals("F")) {
                PessoaFisica pf = repoFisica.obter(id);
                if (pf != null) {
                    System.out.println("Dados da pessoa:");
                    System.out.println("ID: " + pf.getId());
                    System.out.println("Nome: " + pf.getNome());
                    System.out.println("CPF: " + pf.getCpf());
                    System.out.println("Idade: " + pf.getIdade());
                } else {
                    System.out.println("Pessoa não encontrada!");
                }
            } else {
                PessoaJuridica pj = repoJuridica.obter(id);
                if (pj != null) {
                    System.out.println("Dados da pessoa:");
                    System.out.println("ID: " + pj.getId());
                    System.out.println("Nome: " + pj.getNome());
                    System.out.println("CNPJ: " + pj.getCnpj());
                } else {
                    System.out.println("Pessoa não encontrada!");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void exibirTodos() {
        System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
        String tipo = scanner.nextLine().toUpperCase();
        
        try {
            if (tipo.equals("F")) {
                System.out.println("Pessoas físicas cadastradas:");
                for (PessoaFisica pf : repoFisica.obterTodos()) {
                    System.out.println("\nID: " + pf.getId());
                    System.out.println("Nome: " + pf.getNome());
                    System.out.println("CPF: " + pf.getCpf());
                    System.out.println("Idade: " + pf.getIdade());
                }
            } else {
                System.out.println("Pessoas jurídicas cadastradas:");
                for (PessoaJuridica pj : repoJuridica.obterTodos()) {
                    System.out.println("\nID: " + pj.getId());
                    System.out.println("Nome: " + pj.getNome());
                    System.out.println("CNPJ: " + pj.getCnpj());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void persistirDados() {
        System.out.println("Digite o prefixo para os arquivos:");
        String prefixo = scanner.nextLine();
        
        try {
            repoFisica.persistir(prefixo + ".fisica.bin");
            repoJuridica.persistir(prefixo + ".juridica.bin");
            System.out.println("Dados persistidos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao persistir dados: " + e.getMessage());
        }
    }

    private static void recuperarDados() {
        System.out.println("Digite o prefixo para os arquivos:");
        String prefixo = scanner.nextLine();
        
        try {
            repoFisica.recuperar(prefixo + ".fisica.bin");
            repoJuridica.recuperar(prefixo + ".juridica.bin");
            System.out.println("Dados recuperados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar dados: " + e.getMessage());
        }
    }
}