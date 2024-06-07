package aplicacao;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Livro;
import model.Usuario;
import model.Emprestimo;
import repository.LivroRepository;
import repository.UsuarioRepository;
import repository.EmprestimoRepository;

public class Programa {

    public static void main(String[] args) {
        
        LivroRepository livroRepository = new LivroRepository();
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        EmprestimoRepository emprestimoRepository = new EmprestimoRepository();
        Scanner sc = new Scanner(System.in);
        
        int op;
        
        do {
            System.out.println("======Escolha uma opção abaixo======");
            System.out.println("======[1] - Cadastrar Livro ======");
            System.out.println("======[2] - Buscar por um Livro ======");
            System.out.println("======[3] - Listar livros cadastrados ======");
            System.out.println("======[4] - Remover Livro======");
            System.out.println("======[5] - Cadastrar Usuário ======");
            System.out.println("======[6] - Buscar por um Usuário ======");
            System.out.println("======[7] - Listar usuários cadastrados ======");
            System.out.println("======[8] - Remover Usuário======");
            System.out.println("======[9] - Registrar Empréstimo ======");
            System.out.println("======[10] - Buscar por um Empréstimo ======");
            System.out.println("======[11] - Listar Empréstimos ======");
            System.out.println("======[12] - Remover Empréstimo ======");
            System.out.println("======[13] - Devolver Empréstimo ======");
            System.out.println("======[0] - Para sair");
            op = sc.nextInt();
            sc.nextLine(); // Consumir a quebra de linha

            switch (op) {
                case 1:
                    System.out.println("Digite o título do livro:");
                    String titulo = sc.nextLine();
                    System.out.println("Digite o autor do livro:");
                    String autor = sc.nextLine();
                    System.out.println("O livro está disponível? (true/false):");
                    boolean disponivel = sc.nextBoolean();

                    Livro novoLivro = new Livro(null, titulo, autor, disponivel);
                    livroRepository.salvar(novoLivro);
                    System.out.println("Livro cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("Digite o ID do livro que deseja buscar:");
                    int idLivroBusca = sc.nextInt();
                    Livro livroBuscado = livroRepository.buscarPorId(idLivroBusca);
                    if (livroBuscado != null) {
                        System.out.println("Livro encontrado: " + livroBuscado);
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;

                case 3:
                    List<Livro> livros = livroRepository.buscarTodos();
                    if (livros.isEmpty()) {
                        System.out.println("Nenhum livro cadastrado.");
                    } else {
                        System.out.println("Lista de livros cadastrados:");
                        for (Livro livro : livros) {
                            System.out.println(livro);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Digite o ID do livro que deseja remover:");
                    int idLivroRemover = sc.nextInt();
                    livroRepository.removeLivro(idLivroRemover);
                    System.out.println("Livro removido com sucesso!");
                    break;

                case 5:
                    System.out.println("Digite o nome do usuário:");
                    String nome = sc.nextLine();
                    System.out.println("Digite o email do usuário:");
                    String email = sc.nextLine();

                    Usuario novoUsuario = new Usuario(null, nome, email);
                    usuarioRepository.salvarUsuario(novoUsuario);
                    System.out.println("Usuário cadastrado com sucesso!");
                    break;

                case 6:
                    System.out.println("Digite o ID do usuário que deseja buscar:");
                    int idUsuarioBusca = sc.nextInt();
                    Usuario usuarioBuscado = usuarioRepository.buscarPorId(idUsuarioBusca);
                    if (usuarioBuscado != null) {
                        System.out.println("Usuário encontrado: " + usuarioBuscado);
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 7:
                    List<Usuario> usuarios = usuarioRepository.listarUsuarios();
                    if (usuarios.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado.");
                    } else {
                        System.out.println("Lista de usuários cadastrados:");
                        for (Usuario usuario : usuarios) {
                            System.out.println(usuario);
                        }
                    }
                    break;

                case 8:
                    System.out.println("Digite o ID do usuário que deseja remover:");
                    int idUsuarioRemover = sc.nextInt();
                    usuarioRepository.removeUsuario(idUsuarioRemover);
                    System.out.println("Usuário removido com sucesso!");
                    break;

                case 9:
                    System.out.println("Digite o ID do usuário:");
                    int usuarioId = sc.nextInt();
                    Usuario usuario = usuarioRepository.buscarPorId(usuarioId);
                    if (usuario == null) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }

                    System.out.println("Digite o ID do livro:");
                    int livroId = sc.nextInt();
                    Livro livro = livroRepository.buscarPorId(livroId);
                    if (livro == null || !livro.getDisponivel()) {
                        System.out.println("Livro não encontrado ou não disponível.");
                        break;
                    }

                    livro.setDisponivel(false);
                    livroRepository.salvar(livro);

                    Emprestimo novoEmprestimo = new Emprestimo(usuario, livro, new Date(), null);
                    emprestimoRepository.salvar(novoEmprestimo);
                    System.out.println("Empréstimo registrado com sucesso!");
                    break;


                case 10:
                    System.out.println("Digite o ID do empréstimo que deseja buscar:");
                    int idEmprestimoBusca = sc.nextInt();
                    Emprestimo emprestimoBuscado = emprestimoRepository.buscarPorId(idEmprestimoBusca);
                    if (emprestimoBuscado != null) {
                        System.out.println("Empréstimo encontrado: " + emprestimoBuscado);
                    } else {
                        System.out.println("Empréstimo não encontrado.");
                    }
                    break;

                case 11:
                    List<Emprestimo> emprestimos = emprestimoRepository.buscarTodos();
                    if (emprestimos.isEmpty()) {
                        System.out.println("Nenhum empréstimo cadastrado.");
                    } else {
                        System.out.println("Lista de empréstimos cadastrados:");
                        for (Emprestimo emprestimo : emprestimos) {
                            System.out.println(emprestimo);
                        }
                    }
                    break;

                case 12:
                    System.out.println("Digite o ID do empréstimo que deseja remover:");
                    int idEmprestimoRemover = sc.nextInt();
                    Emprestimo emprestimo = emprestimoRepository.buscarPorId(idEmprestimoRemover);
                    if (emprestimo != null) {
                        Livro livroDevolvido = emprestimo.getLivro();
                        livroDevolvido.setDisponivel(true);
                        livroRepository.salvar(livroDevolvido);
                        emprestimoRepository.remover(idEmprestimoRemover);
                        System.out.println("Empréstimo removido com sucesso!");
                    } else {
                        System.out.println("Empréstimo não encontrado.");
                    }
                    break;

                case 13:
                    System.out.println("Digite o ID do empréstimo que deseja devolver:");
                    int idEmprestimoDevolver = sc.nextInt();
                    emprestimoRepository.devolverEmprestimo(idEmprestimoDevolver);
                    System.out.println("Empréstimo devolvido com sucesso!");
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (op != 0);
        
        sc.close();
    }
}
