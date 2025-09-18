package main.bancaria.model;


import main.bancaria.enums.TarifaStrategy;
import main.bancaria.exception.SaldoInsuficienteException;

public class ContaCorrente extends Conta{
    public ContaCorrente(int numero, String titular, double saldo, TarifaStrategy tarifa) {
        super(numero, titular, saldo, tarifa);
    }
    public ContaCorrente() {

    }



    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        if(super.getSaldo() < valor){
            throw new SaldoInsuficienteException();
        }
        double novoSaldo = super.getSaldo() - valor;
        super.setSaldo(novoSaldo);
    }


}
