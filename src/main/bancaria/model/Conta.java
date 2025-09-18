package main.bancaria.model;


import main.bancaria.enums.TarifaStrategy;
import main.bancaria.exception.SaldoInsuficienteException;

public abstract class Conta {

    private int numero;
    private String titular;
    private double saldo;
    private TarifaStrategy tarifa;

    public Conta(int numero, String titular, double saldo, TarifaStrategy tarifa) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
        this.tarifa = tarifa;
    }

    public Conta() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public abstract void sacar(double valor) throws SaldoInsuficienteException;

    public void depositar(double valor){
        if(valor>0){
            saldo+=valor;
        }

    }

    public TarifaStrategy getTarifa() {
        return tarifa;
    }

    public void setTarifa(TarifaStrategy tarifa) {
        this.tarifa = tarifa;
    }
    public double calcularTarifa(){
        return tarifa.calcularTarifa(saldo);
    }

    public void imprimirDados(){
        System.out.printf("""
                Numero: %d
                Titular: %s
                Saldo: R$%.2f
                Tipo de Tarifa: %s%n%n""",numero, titular, saldo, tarifa.name());
    }


}
