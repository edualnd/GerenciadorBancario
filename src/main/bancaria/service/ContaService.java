package main.bancaria.service;


import main.bancaria.enums.TarifaStrategy;
import main.bancaria.exception.SaldoInsuficienteException;
import main.bancaria.model.ContaCorrente;
import main.bancaria.persistence.dao.ContaDAO;

public class ContaService {
    public ContaService() {
    }


    public void solicitarSaqueTxt(ContaCorrente c, double valor) throws SaldoInsuficienteException{
        c.sacar(valor);
    }
    public void solicitarDespositoTxt(ContaCorrente c, double valor){
        c.depositar(valor);
    }
    public double calcularTarifa(ContaCorrente c){
        return c.getTarifa().calcularTarifa(c.getSaldo());
    }

    //Metodos JDBC
    public boolean solicitarTransferencia(ContaCorrente cSacar, ContaCorrente cDepositar,
                                    double valor){
        ContaDAO dao = new ContaDAO();
        if (cSacar.getSaldo() >= valor){

            if (dao.transferir(cSacar.getNumero(),cDepositar.getNumero(),valor)){
                cSacar.sacar(valor);
                cDepositar.depositar(valor);
                return true;
            }
        }
        return false;
    }
    public boolean solicitarDesposito(ContaCorrente c, double valor){
        ContaDAO dao = new ContaDAO();
        c.depositar(valor);
        return dao.atualizarSaldo(c.getNumero(), c.getSaldo());
    }
    public boolean solicitarSaque(ContaCorrente c, double valor) throws SaldoInsuficienteException {
        ContaDAO dao = new ContaDAO();
        c.sacar(valor);
        return dao.atualizarSaldo(c.getNumero(), c.getSaldo());
    }

    public boolean criarConta(String titular, TarifaStrategy tarifa){
        ContaDAO dao = new ContaDAO();
        return dao.inserir(new ContaCorrente(0, titular, 0, tarifa));

    }
    public boolean deletarConta(int num){

        ContaDAO dao = new ContaDAO();
        return dao.remover(num);

    }

}
