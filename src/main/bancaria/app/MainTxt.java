package main.bancaria.app;




import main.bancaria.enums.FiltrarStrategy;
import main.bancaria.enums.OrdenarStrategy;
import main.bancaria.exception.SaldoInsuficienteException;
import main.bancaria.model.ContaCorrente;
import main.bancaria.service.BancoService;
import main.bancaria.service.ContaService;

import java.util.List;

import java.util.Map;
import java.util.Scanner;

/* Funciona salvando os dados em arquivo .txt*/

public class MainTxt {

    private static final ContaService cs = new ContaService();
    private static final BancoService bs = new BancoService();
    public static final Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        
        List<ContaCorrente> contas = bs.lerDadosTxt();
        ContaCorrente contaEscolhida;
        for (ContaCorrente c : contas){
            System.out.println("Numero: " + c.getNumero());
            System.out.println("Titular: " + c.getTitular());
            System.out.println("------------------------------");
        }

        int ent;
        System.out.println("O que deseja fazer: ");
        System.out.print("""
                [1] Filtrar contas
                [2] Saldo total das contas
                [3] Faixa de saldo
                [4] Operações bancarias
                [5] Ordenar
                >>\s""");
        ent = sc.nextInt();
        switch (ent){
            case 1 -> {
                FiltrarStrategy strategy = null;
                do{
                    System.out.print("""
                            Filtrar por:
                            [1] Saldo maior que 10000
                            [2] Saldo maior que 5000
                            [3] Contas pares
                            >>""");
                    int filtro = sc.nextInt();

                    switch (filtro){
                        case 1 -> strategy = FiltrarStrategy.SALDOMAIOR10000;
                        case 2 -> strategy = FiltrarStrategy.SALDOMAIOR5000;
                        case 3 -> strategy = FiltrarStrategy.NUMEROPAR;
                        default -> System.out.println("Invalido tente novamente");
                    }
                }while(strategy == null);

                List<ContaCorrente> listaFiltrada = bs.filtrarContas(contas, strategy);
                if (listaFiltrada.isEmpty()) System.out.println("Nenhuma conta " +
                        "encontrada");
                else listaFiltrada.forEach(ContaCorrente::imprimirDados);
            }
            case 2 -> {
                double saldo = bs.saldoTotal(contas);
                System.out.println(saldo);
            }
            case 3 -> {
                Map<String, List<ContaCorrente>> res = bs.faixaSaldo(contas);
                res.forEach((faixa, total) ->
                {
                    System.out.println("-------------");
                    System.out.println(faixa + ": ");
                    System.out.println("-------------");
                    total.forEach(c -> {
                        System.out.println("N° - " + c.getNumero() + " / " + c.getTitular());
                        System.out.println("----------");
                    } );
                });
            }
            case 4 -> {
                contaEscolhida = escolherConta(contas);
                contaEscolhida.imprimirDados();
                operacoes(contaEscolhida);
            }
            case 5 -> {
                System.out.print("""
                        Ordenar por:
                        [1] Nome
                        [2] Saldo
                        >>""");
                int ordenarPor = sc.nextInt();
                if (ordenarPor == 1){
                    bs.ordenacaoComStrategy(contas, OrdenarStrategy.PORNOME);
                } else if (ordenarPor == 2) {
                    bs.ordenacaoComStrategy(contas, OrdenarStrategy.PORSALDO);
                }
                contas.forEach(c ->{
                    System.out.println("Numero: " + c.getNumero());
                    System.out.println("Titular: " + c.getTitular());
                    System.out.println("Saldo: " + c.getSaldo());
                    System.out.println("------------------------------");
                });
            }
        }


    }

    private static ContaCorrente escolherConta(List<ContaCorrente> contas) {
        ContaCorrente contaEscolhida = null;
        do{
            System.out.println("Escolha a conta para efetuar as operações, e digite seu numero");
            int numero = sc.nextInt();
            for (ContaCorrente c : contas){
                if(c.getNumero() == numero){
                    contaEscolhida = c;
                    break;
                }
            }
        }while(contaEscolhida == null);
        return contaEscolhida;
    }

    private static void operacoes(ContaCorrente contaEscolhida) {
        int ent;
        do{
            System.out.print("""
                    Qual operação deseja realizar?
                    [1] Saque
                    [2] Deposito
                    [3] Calcular tarifa
                    [0] Sair
                    >>\s""");
            ent = sc.nextInt();
            switch (ent){
                case 1 -> {
                    boolean isSaqueFeito = false;
                    do{
                        System.out.print("Digite o valor: ");
                        double valor = sc.nextDouble();
                        try{
                            cs.solicitarSaqueTxt(contaEscolhida, valor);
                            isSaqueFeito = true;
                        } catch (SaldoInsuficienteException e) {
                            System.out.println(e.getMessage() + "  Tente novamente");
                            System.out.println("Saldo atual: " + contaEscolhida.getSaldo());
                        }
                    }while(!isSaqueFeito);
                    System.out.println("Saque realizado com sucesso");

                }
                case 2 -> {
                    System.out.print("Digite o valor: ");
                    double valor = sc.nextDouble();
                    cs.solicitarDespositoTxt(contaEscolhida,valor);
                    System.out.println("Deposito realizado com sucesso");
                }
                case 3 -> {
                    double valor = cs.calcularTarifa(contaEscolhida);
                    System.out.println("Valor: R$" + valor);
                }
                case 0 -> System.out.println("Finalizado");
                default -> System.out.println("Escolha Invalida. Tente novamente");
            }

        }while(ent<0 || ent >3);
        bs.salvarDadosAtualizadosTxt(contaEscolhida);
    }


}
