package main.bancaria.enums;

public enum TarifaStrategy {
    FIXA {
        public double calcularTarifa(double valor){
            return 10;
        }
    },
    PERCENTUAL {
        public double calcularTarifa(double valor){
            return valor * 0.01;
        }
    },
    ISENTA {
        public double calcularTarifa(double valor){
            return 0;
        }
    };
    public abstract double calcularTarifa(double valor);

}
