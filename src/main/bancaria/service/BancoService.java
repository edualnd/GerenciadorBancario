package main.bancaria.service;


import main.bancaria.enums.FiltrarStrategy;
import main.bancaria.enums.OrdenarStrategy;
import main.bancaria.enums.TarifaStrategy;
import main.bancaria.model.Conta;
import main.bancaria.model.ContaCorrente;
import main.bancaria.persistence.dao.ContaDAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class BancoService {
    public BancoService() {
    }

    public List<ContaCorrente> lerDadosTxt(){
        Path caminho = Paths.get("src/main.bancaria/doc/conta.txt");
        List<String> linhas = null;
        try {
            linhas = Files.readAllLines(caminho);
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo");
        }

        List<ContaCorrente> contas = new ArrayList<>();
        if (linhas != null) {
            for (String l : linhas) {
                String[] dados = l.split(",");
                int numero = Integer.parseInt(dados[0]);
                double saldo = Double.parseDouble(dados[2]);
                contas.add(new ContaCorrente(numero, dados[1], saldo, TarifaStrategy.valueOf(dados[3])));
            }
        }
        return contas;
    }

    public void salvarDadosAtualizadosTxt(ContaCorrente c) {
        Path caminho = Paths.get("src/main.bancaria/aula2/doc/conta_atualizada.txt");
        String dadosAtualizados =  c.getNumero() + ","
                + c.getTitular() + ","
                + c.getSaldo() + ","
                + c.getTarifa() +"\n";
        try {
            Files.writeString(caminho, dadosAtualizados, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo");
        }
        System.out.println("Dados atualizados com sucesso");
    }

    public List<ContaCorrente> filtrarContas(List<ContaCorrente> contas,
                                             FiltrarStrategy strategy){
        return strategy.filtrarContas(contas);
    }

    public void ordenacaoComStrategy(List<ContaCorrente> contas, OrdenarStrategy strategy){
        strategy.ordenar(contas);
    }

    public double saldoTotal(List<ContaCorrente> contas){
        return contas.stream().map(Conta::getSaldo).reduce((double) 0, Double::sum);
    }

    public Map<String, List<ContaCorrente>> faixaSaldo(List<ContaCorrente> contas){
        Map<String, List<ContaCorrente>> res;
        res = contas.stream()
                .collect(groupingBy(c -> {
                    if(c.getSaldo() <= 5000) return "Até R$ 5000";
                    else if (c.getSaldo() >= 5001 && c.getSaldo() < 10000) return "De R$ 5001 a R$ 10000";
                    else return "Acima de R$ 10000";
                }));
        res.putIfAbsent("Até R$ 5000", new ArrayList<>());
        res.putIfAbsent("De R$ 5001 a R$ 10000", new ArrayList<>());
        res.putIfAbsent("Acima de R$ 10000", new ArrayList<>());
        return res;
    }

    //Metodos JDBC
    public List<ContaCorrente> lerDados(){
        ContaDAO dao = new ContaDAO();

        return dao.listar().stream().map(c-> new ContaCorrente(c.getNumero(),
                c.getTitular(), c.getSaldo(),c.getTarifa())).collect(Collectors.toList());
    }

    public ContaCorrente escolherConta(int num) {
        ContaDAO dao = new ContaDAO();
        Conta c = dao.buscarPorNumero(num);
        return new ContaCorrente(c.getNumero(), c.getTitular(), c.getSaldo(), c.getTarifa());
    }
}
