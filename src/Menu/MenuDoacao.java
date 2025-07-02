package Menu;

import Model.DAO.DoacaoDao;
import Model.DAO.DoadorDAO;
import Model.Doacao;
import Model.Doador;
import Model.Situacao;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MenuDoacao {

    private static LocalDate formatacao(String data) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(data, format);        
    }

    private static LocalTime formatacaoTime(String hora) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalTime.parse(hora,format);
    }

    public static Doacao cadastro(Scanner entrada) {
        long codigo;
        double volume = 0;
        DoadorDAO doadorDAO = new DoadorDAO();
        DoadorDAO dao = new DoadorDAO();
        List<Doador> lista = dao.findAll();
        lista.forEach(e -> System.out.println(e.toString()));
        System.out.print("Digite o codigo do doador: ");
        codigo = Long.parseLong(entrada.nextLine());
        Doador doador = doadorDAO.find_code(codigo);
        if (doador != null) {
            if (doador.getSituacao() == Situacao.ATIVO) {
                System.out.print("Digite o volume para doação: ");
                do {
                    volume = Double.parseDouble(entrada.nextLine());
                    if(volume < 0)
                    {
                        System.err.println("Volume negativo ...\nTente Novamente ...");
                    }
                } while (volume < 0);
                System.out.print("Digite a data da doação: ");
                String data;

                data = entrada.nextLine();

                System.out.print("Digite o horario da doação: ");
                String hora = entrada.nextLine();

                return new Doacao(formatacao(data), formatacaoTime(hora), volume, doador);
            } else {
                System.err.println("É necessario atualizar a situação do doador para 'ATIVO'");
                return null;
            }
        } else {
            System.err.println("Doador não encontrado\n");
            return null;
        }
    }

    public static void pesquisa(Scanner entrada, DoacaoDao dao) {
        int menu;
        do {
            System.out.println("\nPesquisa");
            System.out.println(
                    "1 - Pelo Código do Doador\n2 - Pelo Nome do Doardor\n3 - Pelo CPF do Doador\n4 - Pelo codigo da Doação\n5 - Pela Data da Doação\n6 - Voltar");
            System.out.print("Opção: ");
            menu = Integer.parseInt(entrada.nextLine());
            if (menu == 1) {
                System.out.print("Digite o codigo do Doador: ");
                Long codigo = Long.parseLong(entrada.nextLine());
                List<Doacao> lista = dao.find_code_doador(codigo);
                if (lista.isEmpty()) {
                    System.err.println("Dado(s) não encontrado(s)\n");
                } else {
                    lista.forEach(e -> System.out.println(e.toString()));
                }

            } else if (menu == 2) {
                System.out.print("Digite o nome Desejado: ");
                String nome = entrada.nextLine();
                List<Doacao> lista = dao.find_name(nome, entrada);
                if (lista.isEmpty()) {
                    System.err.println("Dados não encontrados\n");
                } else {
                    lista.forEach(e -> System.out.println(e.toString()));
                }
            } else if (menu == 3) {
                System.out.print("Digite o CPF do doador: ");
                String cpf = entrada.nextLine();
                List<Doacao> lista = dao.find_cpf(cpf, entrada);
                if (lista.isEmpty()) {
                    System.err.println("Dados não encontrados\n");
                } else {
                    lista.forEach(e -> System.out.println(e.toString()));
                }
            } else if (menu == 4) {
                System.out.println("Digite o codigo da doação: ");
                Long codigo = Long.parseLong(entrada.nextLine());
                Doacao doacao = dao.find_code(codigo);
                if (doacao != null) {
                    System.out.println(doacao.toString());
                } else {
                    System.out.println("Dado(s) não encontrado(s)\n");
                }
            } else if (menu == 5) {
                int pes_data;
                do {
                    System.out.println("Pesquisa por Data");
                    System.out.println(
                            "Deseja pesquisa com:\n1 - Antes da data desejada\n2 - Com intervalo de data\n3 - Depois da data desejada\n4 - Voltar");
                    System.out.print("Opção: ");
                    pes_data = Integer.parseInt(entrada.nextLine());
                    if (pes_data == 1) {
                        System.out.print("Digite a data desejada: ");
                        List<Doacao> lista = dao.find_before(formatacao(entrada.nextLine()));
                        if (lista.isEmpty()) {
                            System.err.println("Dado(s) não encontrado(s)\n");
                        } else {
                            lista.forEach(e -> System.out.println(e.toString()));
                        }
                    } else if (pes_data == 2) {
                        System.out.print("Digite a data inicial: ");
                        String data_i = entrada.nextLine();
                        System.out.print("Digite a data final: ");
                        String data_f = entrada.nextLine();
                        List<Doacao> lista = dao.find_between(formatacao(data_i), formatacao(data_f));
                        if (lista.isEmpty()) {
                            System.err.println("Nenhum dado historico\n");
                        } else {
                            lista.forEach(e -> System.out.println(e.toString()));
                        }
                    } else if (pes_data == 3) {
                        System.out.println("Digite a data para pesquisa: ");
                        List<Doacao> lista = dao.find_after(formatacao(entrada.nextLine()));
                        if (lista.isEmpty()) {
                            System.err.println("Nenhum dado historico\n");
                        } else {
                            lista.forEach(e -> System.out.println(e.toString()));
                        }
                    } else if (pes_data > 4) {
                        System.err.println("Opção não disponivel\n");
                    }
                } while (pes_data != 4);
            } else if (menu > 6) {
                System.err.println("Opção não disponivel");
            }
        } while (menu != 6);
    }

    public static void atualizacao(Scanner entrada, DoacaoDao dao) {
        System.out.println("Atualização");
        dao.findAll().forEach(e -> System.out.println(e.toString()));
        System.out.print("Digite o codigo da doação para atualização: ");
        Long codigo = Long.parseLong(entrada.nextLine());
        int opc_aux;
        int menu, opc;
        Doacao doacao = dao.find_code(codigo);
        System.out.println(doacao.toString());
        System.out.println("Deseja Atualizar [1 - Sim ou 2 - Não] ?");
        menu = Integer.parseInt(entrada.nextLine());
        if (menu == 1) {
            do {
                System.out.println("1 - Data\n2 - hora\n3 - volume\n4 - Doador\n");
                System.out.print("Opção: ");
                opc = Integer.parseInt(entrada.nextLine());
                switch (opc) {
                    case 1:
                        System.out.print("Digite a nova data: ");
                        doacao.setData(formatacao(entrada.nextLine()));
                        break;
                    case 2:
                        System.out.print("Digite o novo horario: ");
                        doacao.setHora(LocalTime.parse(entrada.nextLine()));
                        break;
                    case 3:
                        double volume = 0l;
                        System.out.print("Digite o novo volume: ");
                        do {
                            volume = Double.parseDouble(entrada.nextLine());
                            if(volume < 0)
                            {
                                System.err.println("Volume negativo");
                                volume = -1;
                            }
                        } while (volume < 0);
                        doacao.setVolume(volume);
                        break;
                    case 4:
                        DoadorDAO dao_aux = new DoadorDAO();
                        MenuDoador.atualizacao(entrada, dao_aux, doacao.getDoador().getCodigo());
                        break;
                    default:
                        System.err.println("Opção não disponivel\n");
                        break;
                }
                System.out.println("Deseja atualizar algum dado [1 - Sim ou 2 - Não] ?");
                opc_aux = Integer.parseInt(entrada.nextLine());
            } while (opc != 5 && opc_aux == 1);
            if (dao.update(doacao)) {
                System.out.println("Atualizado com sucesso\n");
                System.out.println(doacao.toString());
            } else {
                System.err.println("Erro para realizar a atualização\n");
            }
        }
    }

    public static void delete(Scanner entrada, DoacaoDao dao) {
        Long codigo;
        dao.findAll().forEach(e -> System.out.println(e.toString()));
        System.out.println("Deletar dados");
        System.out.print("Digite o codigo da doação para apagar: ");
        codigo = Long.parseLong(entrada.nextLine());
        dao.delete(codigo);
    }

    public static void doacao(Scanner entrada) {
        int menu = 0;
        DoacaoDao dao = new DoacaoDao();
        do {
            System.out.println("Doação");
            System.out.println("1 - Cadastrar\n2 - Pesquisar\n3 - Alterar\n4 - Remover\n5 - Voltar");
            System.out.print("Opção: ");
            menu = Integer.parseInt(entrada.nextLine());
            if (menu == 1) {
                Doacao doacao = cadastro(entrada);
                if (doacao != null) {
                    System.out.println("Registro criado com sucesso");
                    dao.create(doacao);
                }
            } else if (menu == 2) {
                pesquisa(entrada, dao);
            } else if (menu == 3) {
                atualizacao(entrada, dao);
            } else if (menu == 4) {
                delete(entrada, dao);
            } else if (menu > 5) {
                System.err.println("Opção não disponivel\n");
            }
        } while (menu != 5);
    }
}
