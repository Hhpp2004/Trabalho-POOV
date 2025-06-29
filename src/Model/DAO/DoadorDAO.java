package Model.DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Doador;
import Model.RH;
import Model.Situacao;
import Model.TipoSanguineo;
import Model.DAO.Core.ConnectFactory;

public class DoadorDAO {

    public List<Doador> findAll() {
        Connection connection = ConnectFactory.getConnect();
        List<Doador> lista = new ArrayList<>();
        if (connection != null) {
            String sql = "select * from doador order by codigo asc";
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);

                while (rs.next()) {
                    lista.add(new Doador(rs.getLong("codigo"), rs.getString("nome"), rs.getString("cpf"),
                            rs.getString("contato"), rs.getBoolean("tipo_rh"), RH.valueOf(rs.getString("rh")),
                            TipoSanguineo.valueOf(rs.getString("tiposanguineo")),
                            Situacao.valueOf(rs.getString("situacao"))));
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

    public List<Doador> find_cpf(String cpf) {
        Connection connection = ConnectFactory.getConnect();
        List<Doador> lista = new ArrayList<>();
        if (connection != null) {
            try {
                String sql = "select * from doador where cpf like ? order by codigo asc";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, "%" + cpf + "%");
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    lista.add(new Doador(rs.getLong("codigo"), rs.getString("nome"), rs.getString("cpf"),
                            rs.getString("contato"), rs.getBoolean("tipo_rh"), RH.valueOf(rs.getString("rh")),
                            TipoSanguineo.valueOf(rs.getString("tiposanguineo")),
                            Situacao.valueOf(rs.getString("situacao"))));
                }
                if (lista.isEmpty()) {
                    System.err.println("Doador não encontrado");
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

    public Doador find_code(Long codigo) {
        Connection connection = ConnectFactory.getConnect();
        Doador doador = null;
        if (connection != null) {
            String sql = "select * from doador where codigo = ? order by codigo asc";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, codigo);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    doador = new Doador(rs.getLong("codigo"), rs.getString("nome"), rs.getString("cpf"),
                            rs.getString("contato"), rs.getBoolean("tipo_rh"), RH.valueOf(rs.getString("rh")),
                            TipoSanguineo.valueOf(rs.getString("tiposanguineo")),
                            Situacao.valueOf(rs.getString("situacao")));
                } else {
                    System.err.println("Doador não encontrado");
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
        return doador;
    }

    public List<Doador> find_name(String nome) {
        Connection connection = ConnectFactory.getConnect();
        List<Doador> lista = new ArrayList<>();
        if (connection != null) {
            try {
                String sql = "select * from doador where nome ilike ? order by codigo asc";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, "%" + nome + "%");
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    lista.add(new Doador(rs.getLong("codigo"), rs.getString("nome"), rs.getString("cpf"),
                            rs.getString("contato"), rs.getBoolean("tipo_rh"), RH.valueOf(rs.getString("rh")),
                            TipoSanguineo.valueOf(rs.getString("tiposanguineo")),
                            Situacao.valueOf(rs.getString("situacao"))));
                }
                if (lista.isEmpty()) {
                    System.err.println("Doador(es) não encontrado(s)");
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

    public boolean update(Doador doador) {
        Connection connection = ConnectFactory.getConnect();
        if (connection != null) {
            try {
                String sql = "update doador set nome = ?, cpf = ?,contato = ?,tipo_rh = ?,rh = ?, tiposanguineo = ?, situacao = ? where codigo = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, doador.getNome());
                preparedStatement.setString(2, doador.getCpf());
                preparedStatement.setString(3, doador.getContato());
                preparedStatement.setBoolean(4, doador.getTipoERhCorretos());
                preparedStatement.setString(5, doador.getRh().toString());
                preparedStatement.setString(6, doador.getTipoSanguineo().toString());
                preparedStatement.setString(7, doador.getSituacao().toString());
                preparedStatement.setLong(8, doador.getCodigo());
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

    public Boolean delete(Long codigo) {
        Connection connection = ConnectFactory.getConnect();
        if (connection != null) {
            try {
                String sql = "delete from doador where codigo = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, codigo);
                int remocao = preparedStatement.executeUpdate();
                if (remocao == 1) {
                    System.out.println("Doador apagado");
                } else {
                    System.err.println(
                            "Erro na operação");
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
                        System.out.println("Erro fechando a conexão com o banco de dados.\n");
                    }
                    System.out.println("Conexão com o banco de dados terminada.\n");
                }
            }
        }
        return false;
    }

    public Boolean create(Doador doador) {
        Connection connection = ConnectFactory.getConnect();
        if (connection != null) {
            try {
                String sql = "insert into doador (nome,cpf,contato,tipo_rh,rh,tiposanguineo,situacao) values(?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, doador.getNome());
                preparedStatement.setString(2, doador.getCpf());
                preparedStatement.setString(3, doador.getContato());
                preparedStatement.setBoolean(4, doador.getTipoERhCorretos());
                preparedStatement.setString(5, doador.getRh().toString());
                preparedStatement.setString(6, doador.getTipoSanguineo().toString());
                preparedStatement.setString(7, doador.getSituacao().toString());
                int gravado = preparedStatement.executeUpdate();
                if (gravado == 1) {
                    System.out.println("Usuario(a) criado com sucesso");
                    ResultSet rs = preparedStatement.getGeneratedKeys();
                    if (rs.next()) {
                        doador.setCodigo(rs.getLong(1));
                    } else {
                        System.err.println("Erro para gerar a chave");
                    }
                    rs.close();
                } else {
                    System.err.println("Erro para criar o usuario(a)");
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
}

