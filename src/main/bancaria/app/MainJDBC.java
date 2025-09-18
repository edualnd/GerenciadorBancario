package main.bancaria.app;




import main.bancaria.enums.FiltrarStrategy;
import main.bancaria.enums.OrdenarStrategy;
import main.bancaria.enums.TarifaStrategy;
import main.bancaria.exception.SaldoInsuficienteException;
import main.bancaria.model.ContaCorrente;
import main.bancaria.service.BancoService;
import main.bancaria.service.ContaService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class MainJDBC {

    private static final ContaService cs = new ContaService();
    private static final BancoService bs = new BancoService();
    public static final Scanner sc = new Scanner(System.in);

     public static void main(String[] args) {

        List<ContaCorrente> contas = bs.lerDados();

        int ent;
        do {

            ContaCorrente contaEscolhida;
            for (ContaCorrente c : contas){
                System.out.println("Numero: " + c.getNumero());
                System.out.println("Titular: " + c.getTitular());
                System.out.println("------------------------------");
            }
            System.out.println("O que deseja fazer: ");
            System.out.print("""
                    [1] Filtrar contas
                    [2] Saldo total das contas
                    [3] Faixa de saldo
                    [4] Operações bancarias
                    [5] Ordenar
                    [6] Criar conta
                    [7] Deletar conta
                    [0] Sair
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
                    esperar();
                }
                case 2 -> {
                    double saldo = bs.saldoTotal(contas);
                    System.out.println(saldo);
                    esperar();
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
                    esperar();
                }
                case 4 -> {
                    contaEscolhida = escolherConta();
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

                }
                case 6 -> {
                    sc.nextLine();
                    System.out.println("Nome do titular: ");
                    String titular = sc.nextLine();
                    System.out.println("[1]-ISENTA [2]-PERCENTUAl [3]-FIXA");
                    ent = sc.nextInt();
                    TarifaStrategy tarifa = null;
                    switch (ent){
                        case 1 -> tarifa = TarifaStrategy.ISENTA;
                        case 2 -> tarifa = TarifaStrategy.PERCENTUAL;
                        case 3 -> tarifa = TarifaStrategy.FIXA;
                    }
                    if (cs.criarConta(titular, tarifa)) System.out.println("Conta criada com sucesso");
                    else System.out.println("Erro ao criar a conta");
                    esperar();
                    contas = bs.lerDados();
                }
                case 7 ->{
                    System.out.println("Numero da conta: ");
                    int num = sc.nextInt();
                    if (cs.deletarConta(num)){
                        System.out.println("Conta deletada!");
                        esperar();
                    }
                    contas = bs.lerDados();
                    esperar();
                }
                case 0 -> System.out.println("Programa finalizado");
                default -> System.out.println("Opcao invalida");
            }
            System.out.println("\n----------------------------------------------");
        }while (ent != 0);


    }

    private static void esperar(){
         sc.nextLine();
        System.out.println("Pressione Enter para continuar...");
        sc.nextLine();
    }

    private static ContaCorrente escolherConta() {
        ContaCorrente contaEscolhida;
        do{
            System.out.println("Escolha a conta para efetuar as operações, e digite seu numero");
            int numero = sc.nextInt();
            contaEscolhida = bs.escolherConta(numero);
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
                    [0] Voltar
                    >>\s""");
            ent = sc.nextInt();
            switch (ent){
                case 1 -> {
                    boolean isSaqueFeito = false;
                    boolean ret = false;
                    do{
                        try{
                            System.out.print("Digite o valor: ");
                            double valor = sc.nextDouble();
                            ret = cs.solicitarSaque(contaEscolhida, valor);
                            isSaqueFeito = true;
                        } catch (SaldoInsuficienteException e) {
                            System.out.println(e.getMessage() + "  Tente novamente");
                            System.out.println("Saldo atual: " + contaEscolhida.getSaldo());
                        }
                    }while(!isSaqueFeito);
                    if (ret) System.out.println("Saque realizado com sucesso");
                    else System.out.println("Erro ao sacar");

                }
                case 2 -> {
                    System.out.print("Digite o valor: ");
                    double valor = sc.nextDouble();

                    if (cs.solicitarDesposito(contaEscolhida, valor)){
                        System.out.println("Deposito feito com sucesso");
                    }else System.out.println("Erro ao depositar");
                }
                case 3 -> {
                    double valor = cs.calcularTarifa(contaEscolhida);
                    System.out.println("Valor: R$" + valor);
                }
                case 0 -> System.out.println("Voltando");
                default -> System.out.println("Escolha Invalida. Tente novamente");
            }
            System.out.println("\n----------------------------------------------");

        }while(ent!=0);


    }

}
