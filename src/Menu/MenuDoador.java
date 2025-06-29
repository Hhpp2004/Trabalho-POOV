package Menu;

import java.util.List;
import java.util.Scanner;

import Model.Doador;
import Model.RH;
import Model.Situacao;
import Model.TipoSanguineo;
import Model.DAO.DoadorDAO;

public class MenuDoador {

    public static Doador cadastro(Scanner entrada) {
        String nome;
        String cpf;
        String contato;
        int indice1;
        int indice2;
        int indice3;
        TipoSanguineo ts;
        Situacao situacao;
        RH rh;

        System.out.println("Digite o seu nome: ");
        nome = entrada.nextLine();

        System.out.println("Digite o seu CPF: ");
        cpf = entrada.nextLine();

        System.out.println("Digite o seu contato: ");
        contato = entrada.nextLine();

        do {
            System.out.println("Digite o seu RH:\n1 - Positivo\n2 - Negativo\n3 - Desconhecido");
            try {
                indice1 = Integer.parseInt(entrada.nextLine());
                if (indice1 - 1 >= RH.values().length || indice1 < 1) {
                    System.err.println("Indice não encontrado");
                }
            } catch (NumberFormatException e) {
                System.err.println("Por favor, digite apenas números!");
                indice1 = -1;
            }
        } while (indice1 - 1 >= RH.values().length || indice1 < 1);
        rh = RH.values()[indice1 - 1];

        do {
            System.out.println("Digite o seu tipoSanguineo:\n1 - A\n2 - B\n3 - AB\n4 - O\n5 - Desconhecido");
            try {
                indice2 = Integer.parseInt(entrada.nextLine());
                if (indice2 - 1 >= TipoSanguineo.values().length || indice2 < 1) {
                    System.err.println("Indice não encontrado");
                }
            } catch (NumberFormatException e) {
                System.err.println("Por favor, digite apenas números!");
                indice2 = -1;
            }
        } while (indice2 - 1 >= TipoSanguineo.values().length || indice2 < 1);
        ts = TipoSanguineo.values()[indice2 - 1];

        do {
            System.out.println("Digite a sua situação:\n1 - Ativa\n2 - Inativa");
            try {
                indice3 = Integer.parseInt(entrada.nextLine());
                if (indice3 - 1 >= Situacao.values().length || indice3 < 1) {
                    System.err.println("Indice não encontrado");
                }
            } catch (NumberFormatException e) {
                System.err.println("Por favor, digite apenas números!");
                indice3 = -1;
            }
        } while (indice3 - 1 >= Situacao.values().length || indice3 < 1);
        situacao = Situacao.values()[indice3 - 1];

        return new Doador(nome, cpf, contato, rh, ts, situacao);
    }

    public static void pesquisa(Scanner entrada, DoadorDAO dao) {
        int menu = 0;
        do {
            System.out.println("Pesquisa");
            System.out.println("1 - Pelo código\n2 - Pelo nome\n3 - Pelo CPF\n4 - Voltar");
            System.out.printf("Opção: ");
            do {
                try {
                    menu = Integer.parseInt(entrada.nextLine());
                } catch (NumberFormatException e) {
                    System.err.println("Por favor, digite apenas números!");
                    menu = -1;
                }
            } while (menu == -1);
            if (menu == 1) {
                System.out.println("Pesquisa pelo o codigo");
                System.out.println("Digite o codigo: ");
                Long codigo = Long.parseLong(entrada.nextLine());
                Doador doador = dao.find_code(codigo);
                if (doador != null) {
                    System.out.println(doador.toString());
                } else {
                    System.err.println("Usuario não encontrado");
                }
            } else if (menu == 2) {
                System.out.println("Pesquisa pelo nome");
                System.out.println("Digite o nome: ");
                String nome = entrada.nextLine();
                List<Doador> doador = dao.find_name(nome);
                if (doador.isEmpty()) {
                    System.err.println("Usuario(s) não encontrado(s)");
                } else {

                    doador.forEach(e -> System.out.println(e.toString()));
                }
            } else if (menu == 3) {
                System.out.println("Pesquisa pelo CPF");
                System.out.println("Digite o CPF: ");
                String cpf = entrada.nextLine();
                List<Doador> doador = dao.find_cpf(cpf);
                if (doador.isEmpty()) {
                    System.err.println("Usuario(s) não encontrado(s)");
                } else {
                    doador.forEach(e -> System.out.println(e.toString()));
                }
            } else if (menu > 4) {
                System.err.println("Opção não disponivel");
            }
        } while (menu != 4);
    }

    public static void atualizacao(Scanner entrada, DoadorDAO dao, long codigo) {
        List<Doador> lista = dao.findAll();
        Doador doador;
        if (codigo == 0l) {
            lista.forEach(e -> System.out.println(e.toString()));
            System.out.println("Digite o codigo do doador para atualização: ");
            codigo = Long.parseLong(entrada.nextLine());
            doador = dao.find_code(codigo);
        } else {
            doador = dao.find_code(codigo);
        }
        if (doador != null) {
            System.out.println(doador.toString());
            System.out.println("Deseja atualizar os dados ?\n1 - Sim\n2 - Não");
            int opc = Integer.parseInt(entrada.nextLine());
            if (opc == 1) {
                int menu;
                do {
                    System.out.println("O que você deseja atualizar ?");
                    System.out.println(
                            "1 - Nome\n2 - CPF\n3 - Contato\n4 - Tipo RH\n5 - RH\n6 - Tipo Sanguieno\n7 - Situação");
                    int opcao = Integer.parseInt(entrada.nextLine());
                    switch (opcao) {
                        case 1:
                            System.out.println("Digite o novo nome: ");
                            doador.setNome(entrada.nextLine());
                            break;
                        case 2:
                            System.out.println("Digite o novo CPF: ");
                            doador.setCpf(entrada.nextLine());
                            break;
                        case 3:
                            System.out.println("Digite o novo contato: ");
                            doador.setContato(entrada.nextLine());
                            break;
                        case 4:
                            System.out.println("Trocando para " + (doador.getTipoERhCorretos() ? false : true));
                            doador.setTipoERhCorretos(doador.getTipoERhCorretos() ? false : true);
                            break;
                        case 5:
                            System.out.println("Digite o seu RH:\n1 - Positivo\n2 - Negativo\n3 - Desconhecido");
                            int indice1 = Integer.parseInt(entrada.nextLine());
                            doador.setRh(RH.values()[indice1 - 1]);
                            break;
                        case 6:
                            System.out.println(
                                    "Digite o seu tipoSanguineo:\n1 - A\n2 - B\n3 - AB\n4 - O\n5 - Desconhecido");
                            int indice2 = Integer.parseInt(entrada.nextLine());
                            doador.setTipoSanguineo(TipoSanguineo.values()[indice2 - 1]);
                            break;
                        case 7:
                            System.out.println("Digite a sua situação:\n1 - Ativa\n2 - Inativa");
                            int indice3 = Integer.parseInt(entrada.nextLine());
                            doador.setSituacao(Situacao.values()[indice3 - 1]);
                        default:
                            System.err.println("Opção não disponivel");
                            break;
                    }
                    System.out.println("Deseja Continuar ?\n0 - Não\n1 - Sim");
                    menu = Integer.parseInt(entrada.nextLine());
                } while (menu != 0);
                dao.update(doador);
                System.out.println(doador.toString());
            }
        } else {
            System.err.println("Usuario não encontrado");
        }
    }

    public static void deletar(Scanner entrada, DoadorDAO dao) {
        List<Doador> lista = null;
        lista = dao.findAll();
        if (!lista.isEmpty()) {
            lista.forEach(e -> System.out.println(e.toString()));
            System.out.println("Digite o codigo para deletar: ");
            Long codigo = Long.parseLong(entrada.nextLine());
            Doador doador = dao.find_code(codigo);
            if (doador != null) {
                if (!dao.delete(codigo))
                    System.out.println("Apagado com sucesso");
                else
                    System.err.println("Erro ao deletar");
            } else {
                System.err.println("Dado não encontrado");
            }
        } else {
            System.err.println("Sem dados no Banco");
        }
    }

    public static void doador(Scanner entrada) {
        int menu = 0;
        DoadorDAO dao = new DoadorDAO();
        do {
            System.out.println("Doador");
            System.out.println("1 - Cadastrar\n2 - Pesquisar\n3 - Alterar\n4 - Remover\n5 - Voltar");
            System.out.printf("Opção: ");

            menu = Integer.parseInt(entrada.nextLine());

            if (menu == 1) {
                Doador doador = cadastro(entrada);
                dao.create(doador);
                System.out.println("Doador criado com sucesso");
            } else if (menu == 2) {
                pesquisa(entrada, dao);
            } else if (menu == 3) {
                atualizacao(entrada, dao, 0l);
            } else if (menu == 4) {
                deletar(entrada, dao);
            } else if (menu > 5) {
                System.err.println("Opção não disponivel\n");
            }
        } while (menu != 5);
    }

}

