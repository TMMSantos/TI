import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DAO dao = new DAO();

        while (true) {
            System.out.println("1. Inserir");
            System.out.println("2. Listar");
            System.out.println("3. Atualizar");
            System.out.println("4. Excluir");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.print("Digite o código: ");
                    int codigoToInsert = scanner.nextInt();

                    scanner.nextLine();  // Consumir a nova linha deixada pelo método nextInt()

                    System.out.print("Digite o login: ");
                    String loginToInsert = scanner.nextLine();

                    System.out.print("Digite a senha: ");
                    String senhaToInsert = scanner.nextLine();

                    System.out.print("Digite o sexo (M/F): ");
                    char sexoToInsert = scanner.next().charAt(0);

                    Usuario usuarioToInsert = new Usuario(codigoToInsert, loginToInsert, senhaToInsert, sexoToInsert);
                    dao.inserir(usuarioToInsert);
                    System.out.println("Dados inseridos com sucesso!");
                    break;
                case 2:
                    List<Usuario> list = dao.listar();
                    for (Usuario item : list) {
                        System.out.println("Código: " + item.getCodigo() + ", Login: " + item.getLogin() + ", Senha: " + item.getSenha() + ", Sexo: " + item.getSexo());
                    }
                    break;
                case 3:
                    System.out.print("Digite o código do registro que você deseja atualizar: ");
                    int codigoToUpdate = scanner.nextInt();
                    scanner.nextLine();  // Consumir a nova linha

                    System.out.print("Digite o novo login: ");
                    String newLogin = scanner.nextLine();

                    System.out.print("Digite a nova senha: ");
                    String newSenha = scanner.nextLine();

                    System.out.print("Digite o novo sexo (M/F): ");
                    char newSexo = scanner.next().charAt(0);

                    Usuario usuarioToUpdate = new Usuario(codigoToUpdate, newLogin, newSenha, newSexo);
                    dao.atualizar(usuarioToUpdate);
                    System.out.println("Dados atualizados com sucesso!");
                    break;
                case 4:
                    System.out.print("Digite o código do registro que você deseja excluir: ");
                    int codigoToDelete = scanner.nextInt();
                    dao.excluir(codigoToDelete);
                    System.out.println("Registro excluído com sucesso!");
                    break;
                case 5:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
