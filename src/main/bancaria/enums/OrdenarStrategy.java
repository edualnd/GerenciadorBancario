package main.bancaria.enums;


import main.bancaria.model.ContaCorrente;

import java.util.Comparator;
import java.util.List;

public enum OrdenarStrategy {
    PORSALDO {
        public void ordenar(List<ContaCorrente> contas){
            Comparator<ContaCorrente> porSaldo =  (c1, c2) -> Double.compare(c2.getSaldo(), c1.getSaldo());
            contas.sort(porSaldo);
        }
    },
    PORNOME {
        public void ordenar(List<ContaCorrente> contas){
            Comparator<ContaCorrente> porNome =  (c1, c2) -> c1.getTitular().compareTo(c2.getTitular());
            contas.sort(porNome);
        }
    };
    public abstract void ordenar(List<ContaCorrente> contas);
}
