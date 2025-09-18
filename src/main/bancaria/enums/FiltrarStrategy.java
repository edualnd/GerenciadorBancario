package main.bancaria.enums;


import main.bancaria.model.Conta;
import main.bancaria.model.ContaCorrente;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public enum FiltrarStrategy {
    SALDOMAIOR5000{
        public List<ContaCorrente> filtrarContas(List<ContaCorrente> contas){
            Predicate<ContaCorrente> condicao = c -> c.getSaldo() > 5000;
            return contas.stream().filter(condicao).collect(Collectors.toList());
        }
    },
    NUMEROPAR {
        public List<ContaCorrente> filtrarContas(List<ContaCorrente> contas){
            Predicate<ContaCorrente> condicao = c -> c.getNumero() % 2 == 0;
            return contas.stream().filter(condicao).collect(Collectors.toList());
        }
    },

    SALDOMAIOR10000 {
        public List<ContaCorrente> filtrarContas(List<ContaCorrente> contas){
            contas.stream()
                    .filter(c -> c.getSaldo() > 10000)
                    .forEach(Conta::imprimirDados);
            return contas.stream()
                    .filter(c -> c.getSaldo() > 10000).collect(Collectors.toList());
        }
    };
    public abstract List<ContaCorrente> filtrarContas(List<ContaCorrente> contas);


}
