package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.Doacao;
import Model.Doador;
import Model.DAO.Core.ConnectFactory;

public class DoacaoDao {
    public List<Doacao> findAll() {
        Connection connection = ConnectFactory.getConnect();
        List<Doacao> lista = new ArrayList<>();
        if (connection != null) {
            try {
                String sql = "select * from doacao order by codigo asc";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    lista.add(new Doacao(rs.getLong("codigo"), rs.getDate("data").toLocalDate(),
                            rs.getTime("hora").toLocalTime(),
                            rs.getDouble("volume"), new DoadorDAO().find_code(rs.getLong("codigo_doador"))));
                }
                if (lista.isEmpty()) {
                    System.err.println("Dado não encontrado");
                }
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                SQLException e = ex;
                while (e != null) {
                    System.out.println("SQL State: " + e.getSQLState());
                    System.out.println("Error Code: " + e.getErrorCode());
                    System.out.println("Mensagem: " + e.getMessage());
                    Throwable t = e.getCause();
                    while (t != null) {
                        System.out.println("Causa: " + t);
                        t = t.getCause();
                    }
                    e = e.getNextException();
                }
            } finally {
                if (connection != null) {
                    System.out.println("Terminando a conexão com o banco de dados.");
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        System.out.println("Erro fechando a conexão com o banco de dados.\n");
                    }
                    System.out.println("Conexão com o banco de dados terminada.\n");
                }
            }
        }
        return lista;
    }

    public Boolean create(Doacao doacao) {
        Connection connection = ConnectFactory.getConnect();
        if (connection != null) {
            try {
                String sql = "insert into doacao (codigo_doador,data,hora,volume,situacao) values (?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setLong(1, doacao.getDoador().getCodigo());
                preparedStatement.setDate(2, java.sql.Date.valueOf(doacao.getData()));
                preparedStatement.setTime(3, java.sql.Time.valueOf(doacao.getHora()));
                preparedStatement.setDouble(4, doacao.getVolume());
                preparedStatement.setString(5, doacao.getDoador().getSituacao().toString());
                int gravado = preparedStatement.executeUpdate();
                if (gravado == 1) {
                    System.out.println("Dado criado");
                    ResultSet rs = preparedStatement.getGeneratedKeys();
                    if (rs.next()) {
                        doacao.setCodigo(rs.getLong(1));
                    } else {
                        System.err.println("Erro ao gerar a chave");
                    }
                } else {
                    System.err.println("Erro ao salvar");
                }
                preparedStatement.close();
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                SQLException e = ex;
                while (e != null) {
                    System.out.println("SQL State: " + e.getSQLState());
                    System.out.println("Error Code: " + e.getErrorCode());
                    System.out.println("Mensagem: " + e.getMessage());
                    Throwable t = e.getCause();
                    while (t != null) {
                        System.out.println("Causa: " + t);
                        t = t.getCause();
                    }
                    e = e.getNextException();
                }
            } finally {
                if (connection != null) {
                    System.out.println("Terminando a conexão com o banco de dados.");
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        System.err.println("Erro fechando a conexão com o banco de dados.\n");
                    }
                    System.out.println("Conexão com o banco de dados terminada.\n");
                }
            }
        }
        return true;
    }

    public List<Doacao> find_name(String nome, Scanner entrada) {
        DoadorDAO dao = new DoadorDAO();
        Connection connection = ConnectFactory.getConnect();
        List<Doacao> list = new ArrayList<>();
        if (connection != null) {
            try {
                String sql = "select * from doacao where codigo_doador = ? order by codigo asc";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                List<Doador> lista = dao.find_name(nome);
                if (lista.isEmpty()) {
                    System.err.println("Doador não encontrado\n");
                } else {
                    if (lista.size() == 1) {
                        preparedStatement.setLong(1, lista.getFirst().getCodigo());
                    } else {
                        System.out.println("Existe outros doadores");
                        lista.forEach(e -> System.out.println(e.toString()));
                        System.out.println("Digite o codigo do Doador desejado: ");
                        Long codigo = Long.parseLong(entrada.nextLine());
                        preparedStatement.setLong(1, codigo);
                    }
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        list.add(new Doacao(rs.getLong("codigo"), rs.getDate("data").toLocalDate(),
                                rs.getTime("hora").toLocalTime(), rs.getDouble("volume"),
                                new DoadorDAO().find_code(rs.getLong("codigo_doador"))));
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                SQLException e = ex;
                while (e != null) {
                    System.out.println("SQL State: " + e.getSQLState());
                    System.out.println("Error Code: " + e.getErrorCode());
                    System.out.println("Mensagem: " + e.getMessage());
                    Throwable t = e.getCause();
                    while (t != null) {
                        System.out.println("Causa: " + t);
                        t = t.getCause();
                    }
                    e = e.getNextException();
                }
            } finally {
                if (connection != null) {
                    System.out.println("Terminando a conexão com o banco de dados.");
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        System.out.println("Erro fechando a conexão com o banco de dados.\n");
                    }
                    System.out.println("Conexão com o banco de dados terminada.\n");
                }
            }
        }
        return list;
    }

    public List<Doacao> find_code_doador(Long codigo) {
        Connection connection = ConnectFactory.getConnect();
        ArrayList<Doacao> list = new ArrayList<>();
        if (connection != null) {
            try {
                String sql = "select * from doacao where codigo_doador = ? order by codigo asc";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, codigo);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    list.add(new Doacao(rs.getLong("codigo"), rs.getDate("data").toLocalDate(),
                            rs.getTime("hora").toLocalTime(), rs.getDouble("volume"),
                            new DoadorDAO().find_code(rs.getLong("codigo_doador"))));
                }
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                SQLException e = ex;
                while (e != null) {
                    System.out.println("SQL State: " + e.getSQLState());
                    System.out.println("Error Code: " + e.getErrorCode());
                    System.out.println("Mensagem: " + e.getMessage());
                    Throwable t = e.getCause();
                    while (t != null) {
                        System.out.println("Causa: " + t);
                        t = t.getCause();
                    }
                    e = e.getNextException();
                }
            } finally {
                if (connection != null) {
                    System.out.println("Terminando a conexão com o banco de dados.");
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        System.out.println("Erro fechando a conexão com o banco de dados.\n");
                    }
                    System.out.println("Conexão com o banco de dados terminada.\n");
                }
            }
        }
        return list;
    }

    public List<Doacao> find_cpf(String cpf, Scanner entrada) {
        List<Doacao> lista = new ArrayList<>();
        DoadorDAO dao = new DoadorDAO();
        Connection connection = ConnectFactory.getConnect();
        if (connection != null) {
            try {
                String sql = "select * from doacao where codigo_doador = ? order by codigo asc";
                List<Doador> doadors = dao.find_cpf(cpf);
                if (doadors.isEmpty()) {
                    System.out.println("Doador não encontrado");
                } else {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    if (doadors.size() == 1) {
                        preparedStatement.setLong(1, doadors.getFirst().getCodigo());
                    } else {
                        System.out.println("Mais de um doador encontrado");
                        System.out.println("Escolha um doador:");
                        doadors.forEach(e -> System.out.println(e.toString()));
                        System.out.println("Digite o código do doador:");
                        long codigo = Long.parseLong(entrada.nextLine());
                        preparedStatement.setLong(1, codigo);
                    }
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        lista.add(new Doacao(rs.getLong("codigo"), rs.getDate("data").toLocalDate(),
                                rs.getTime("hora").toLocalTime(), rs.getDouble("volume"),
                                new DoadorDAO().find_code(rs.getLong("codigo_doador"))));
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                SQLException e = ex;
                while (e != null) {
                    System.out.println("SQL State: " + e.getSQLState());
                    System.out.println("Error Code: " + e.getErrorCode());
                    System.out.println("Mensagem: " + e.getMessage());
                    Throwable t = e.getCause();
                    while (t != null) {
                        System.out.println("Causa: " + t);
                        t = t.getCause();
                    }
                    e = e.getNextException();
                }
            } finally {
                if (connection != null) {
                    System.out.println("Terminando a conexão com o banco de dados.");
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        System.out.println("Erro fechando a conexão com o banco de dados.\n");
                    }
                    System.out.println("Conexão com o banco de dados terminada.\n");
                }
            }
        }
        return lista;
    }

    public Doacao find_code(Long codigo) {
        Connection connection = ConnectFactory.getConnect();
        Doacao doacao = null;
        if (connection != null) {
            try {
                String sql = "select * from doacao where codigo = ? order by codigo asc";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, codigo);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    doacao = new Doacao(rs.getLong("codigo"), rs.getDate("data").toLocalDate(),
                            rs.getTime("hora").toLocalTime(), rs.getDouble("volume"),
                            new DoadorDAO().find_code(rs.getLong("codigo_doador")));
                }
                rs.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                SQLException e = ex;
                while (e != null) {
                    System.out.println("SQL State: " + e.getSQLState());
                    System.out.println("Error Code: " + e.getErrorCode());
                    System.out.println("Mensagem: " + e.getMessage());
                    Throwable t = e.getCause();
                    while (t != null) {
                        System.out.println("Causa: " + t);
                        t = t.getCause();
                    }
                    e = e.getNextException();
                }
            } finally {
                if (connection != null) {
                    System.out.println("Terminando a conexão com o banco de dados.");
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        System.out.println("Erro fechando a conexão com o banco de dados.\n");
                    }
                    System.out.println("Conexão com o banco de dados terminada.\n");
                }
            }
        }
        return doacao;
    }

    public List<Doacao> find_before(LocalDate date) {
        Connection connection = ConnectFactory.getConnect();
        List<Doacao> lista = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT * FROM doacao WHERE data < ? order by codigo asc";
            try {
                PreparedStatement pStatement = connection.prepareStatement(sql);
                pStatement.setDate(1, java.sql.Date.valueOf(date));
                ResultSet rs = pStatement.executeQuery();
                while (rs.next()) {
                    lista.add(new Doacao(rs.getLong("codigo"), rs.getDate("data").toLocalDate(),
                            rs.getTime("hora").toLocalTime(), rs.getDouble("volume"),
                            new DoadorDAO().find_code(rs.getLong("codigo_doador"))));
                }
                rs.close();
                pStatement.close();
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                SQLException e = ex;
                while (e != null) {
                    System.out.println("SQL State: " + e.getSQLState());
                    System.out.println("Error Code: " + e.getErrorCode());
                    System.out.println("Mensagem: " + e.getMessage());
                    Throwable t = e.getCause();
                    while (t != null) {
                        System.out.println("Causa: " + t);
                        t = t.getCause();
                    }
                    e = e.getNextException();
                }
            } finally {
                if (connection != null) {
                    System.out.println("Terminando a conexão com o banco de dados.");
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        System.out.println("Erro fechando a conexão com o banco de dados.\n");
                    }
                    System.out.println("Conexão com o banco de dados terminada.\n");
                }
            }
        }
        return lista;
    }

    public List<Doacao> find_between(LocalDate data_inicial, LocalDate data_final) {
        Connection connection = ConnectFactory.getConnect();
        List<Doacao> lista = new ArrayList<>();
        if (connection != null) {
            try {
                String sql = "select * from doacao where data between ? and ? order by codigo asc";
                PreparedStatement pStatement = connection.prepareStatement(sql);
                pStatement.setDate(1, java.sql.Date.valueOf(data_inicial));
                pStatement.setDate(2, java.sql.Date.valueOf(data_final));
                ResultSet rs = pStatement.executeQuery();
                while (rs.next()) {
                    lista.add(new Doacao(rs.getLong("codigo"), rs.getDate("data").toLocalDate(),
                            rs.getTime("hora").toLocalTime(), rs.getDouble("volume"),
                            new DoadorDAO().find_code(rs.getLong("codigo_doador"))));
                }
                pStatement.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                SQLException e = ex;
                while (e != null) {
                    System.out.println("SQL State: " + e.getSQLState());
                    System.out.println("Error Code: " + e.getErrorCode());
                    System.out.println("Mensagem: " + e.getMessage());
                    Throwable t = e.getCause();
                    while (t != null) {
                        System.out.println("Causa: " + t);
                        t = t.getCause();
                    }
                    e = e.getNextException();
                }
            } finally {
                if (connection != null) {
                    System.out.println("Terminando a conexão com o banco de dados.");
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        System.out.println("Erro fechando a conexão com o banco de dados.\n");
                    }
                    System.out.println("Conexão com o banco de dados terminada.\n");
                }
            }
        }
        return lista;
    }

    public List<Doacao> find_after(LocalDate data) {
        List<Doacao> lista = new ArrayList<>();
        Connection connection = ConnectFactory.getConnect();
        if (connection != null) {
            try {
                String sql = "select * from doacao where data > ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDate(1, java.sql.Date.valueOf(data));
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    lista.add(new Doacao(rs.getLong("codigo"), rs.getDate("data").toLocalDate(),
                            rs.getTime("hora").toLocalTime(), rs.getDouble("volume"),
                            new DoadorDAO().find_code(rs.getLong("codigo_doador"))));
                }
                rs.close();
                preparedStatement.close();
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                SQLException e = ex;
                while (e != null) {
                    System.out.println("SQL State: " + e.getSQLState());
                    System.out.println("Error Code: " + e.getErrorCode());
                    System.out.println("Mensagem: " + e.getMessage());
                    Throwable t = e.getCause();
                    while (t != null) {
                        System.out.println("Causa: " + t);
                        t = t.getCause();
                    }
                    e = e.getNextException();
                }
            } finally {
                if (connection != null) {
                    System.out.println("Terminando a conexão com o banco de dados.");
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        System.out.println("Erro fechando a conexão com o banco de dados.\n");
                    }
                    System.out.println("Conexão com o banco de dados terminada.\n");
                }
            }
        }
        return lista;
    }

    public boolean update(Doacao doacao) {
        Connection connection = ConnectFactory.getConnect();
        if (connection != null) {
            try {
                String sql = "update doacao set data = ?, hora = ?, volume = ? where codigo = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDate(1, java.sql.Date.valueOf(doacao.getData()));
                preparedStatement.setTime(2, java.sql.Time.valueOf(doacao.getHora()));
                preparedStatement.setDouble(3, doacao.getVolume());
                preparedStatement.setLong(4, doacao.getCodigo());
                int alterado = preparedStatement.executeUpdate();
                preparedStatement.close();
                if (alterado == 1) {
                    return true;
                }
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                SQLException e = ex;
                while (e != null) {
                    System.out.println("SQL State: " + e.getSQLState());
                    System.out.println("Error Code: " + e.getErrorCode());
                    System.out.println("Mensagem: " + e.getMessage());
                    Throwable t = e.getCause();
                    while (t != null) {
                        System.out.println("Causa: " + t);
                        t = t.getCause();
                    }
                    e = e.getNextException();
                }
            } finally {
                if (connection != null) {
                    System.out.println("Terminando a conexão com o banco de dados.");
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        System.out.println("Erro fechando a conexão com o banco de dados.\n");
                    }
                    System.out.println("Conexão com o banco de dados terminada.\n");
                }
            }
        }
        return false;
    }

    public void delete(Long codigo) {
        Connection connection = ConnectFactory.getConnect();
        if (connection != null) {
            try {
                String sql = "delete from doacao where codigo = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, codigo);
                int remocao = preparedStatement.executeUpdate();
                if (remocao == 1) {
                    System.out.println("Doação apagado");
                } else {
                    System.err.println("Erro ao apagar o dado");
                }
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                SQLException e = ex;
                while (e != null) {
                    System.out.println("SQL State: " + e.getSQLState());
                    System.out.println("Error Code: " + e.getErrorCode());
                    System.out.println("Mensagem: " + e.getMessage());
                    Throwable t = e.getCause();
                    while (t != null) {
                        System.out.println("Causa: " + t);
                        t = t.getCause();
                    }
                    e = e.getNextException();
                }
            } finally {
                if (connection != null) {
                    System.out.println("Terminando a conexão com o banco de dados.");
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        System.out.println("Erro fechando a conexão com o banco de dados.\n");
                    }
                    System.out.println("Conexão com o banco de dados terminada.\n");
                }
            }
        }
    }
}
