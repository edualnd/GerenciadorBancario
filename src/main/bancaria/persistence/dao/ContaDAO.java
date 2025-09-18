package main.bancaria.persistence.dao;



import main.bancaria.enums.TarifaStrategy;
import main.bancaria.model.Conta;
import main.bancaria.model.ContaCorrente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static main.bancaria.persistence.config.ConnectionConfig.getConnection;


public class ContaDAO {
    public boolean inserir(Conta conta){
        String sql = "INSERT INTO contas (titular, saldo, tarifa) VALUES ( ?, ?, ?);";
        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, conta.getTitular());
            stmt.setDouble(2, conta.getSaldo());
            stmt.setString(3, conta.getTarifa().name());
            int res = stmt.executeUpdate();
            if (res> 0) return true;
        } catch(SQLException e){
            System.out.println("Erro inserir");
        }
        return false;
    }

    public List<Conta> listar(){
        String sql = "SELECT * from contas;";
        List<Conta> lista = new ArrayList<>();
        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                 lista.add(new ContaCorrente(resultSet.getInt("numero"),
                         resultSet.getString("titular"),
                         resultSet.getDouble("saldo"),
                         TarifaStrategy.valueOf(resultSet.getString("tarifa"))));
            }
        } catch(SQLException e){
            System.out.println("Erro listar");
        }
        return lista;
    }
    
    public Conta buscarPorNumero(int numero){
        String sql = "SELECT * from contas where numero = ?;";
        Conta conta = new ContaCorrente();
        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, numero);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()){
                conta.setNumero(resultSet.getInt("numero"));
                conta.setTitular(resultSet.getString("titular"));
                conta.setSaldo(resultSet.getDouble("saldo"));
            }

        } catch(SQLException e){

            System.out.println("Erro buscar por numero");

        }
        return conta;
    }

    public boolean atualizarSaldo(int numero, double novoSaldo){
        String sql = "UPDATE contas set saldo = ? WHERE numero = ?;";
        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setDouble(1, novoSaldo);
            stmt.setInt(2, numero);
            int res = stmt.executeUpdate();
            if (res> 0) return true;

        } catch(SQLException e){
            System.out.println("Erro ao atualizar");
        }
        return false;
    }

    public boolean remover(int numero){
        String sql = "DELETE FROM contas WHERE numero = ?";
        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setDouble(1, numero);
            int res = stmt.executeUpdate();
            if (res> 0) return true;

        } catch(SQLException e){
            System.out.println("Erro ao remover");
        }
        return false;
    }

    public boolean transferir(int numeroOrigem, int numeroDestino, double valor){
        String sqlOrigem = "UPDATE contas set saldo = saldo - ? WHERE numero = ?;";
        String sqlDestino = "UPDATE contas set saldo = saldo + ? WHERE numero = ?;";
        try(Connection conn = getConnection()){
            conn.setAutoCommit(false);
            try(PreparedStatement stmtOrigem = conn.prepareStatement(sqlOrigem);
                PreparedStatement stmtDepositar = conn.prepareStatement(sqlDestino)){
                stmtOrigem.setDouble(1, valor);
                stmtOrigem.setInt(2, numeroOrigem);

                stmtDepositar.setDouble(1, valor);
                stmtDepositar.setInt(2, numeroDestino);

                int resOrigem = stmtOrigem.executeUpdate();
                int resDestino = stmtDepositar.executeUpdate();
                if (resOrigem == 1 && resDestino == 1){
                    conn.commit();
                    return true;
                }
                conn.rollback();
            } catch(SQLException e){
                conn.rollback();
                System.out.println("Erro ao atualizar");
            }

        } catch(SQLException e){
            System.out.println("Erro ao conectar com o banco");

        }

        return false;

    }
}
