package main.bancaria.gui;

import main.bancaria.gui.panels.*;

import javax.swing.*;

public enum BtnStrategy {
    Filtrar{
        @Override
        public  JPanel mudarPanel(){
            return new Filtrar();
        }
    },
    SaldoTotal{
        @Override
        public  JPanel mudarPanel(){
            return new SaldoTotal();
        }
    },
    FaixadeSaldo{
        @Override
        public  JPanel mudarPanel(){
            return new FaixaSaldo();
        }
    },
    Ordenar{
        @Override
        public  JPanel mudarPanel(){
            return new Ordenar();
        }
    },
    Operaçõesbancarias{
        @Override
        public  JPanel mudarPanel(){
            return new EscolherConta();
        }
    },
    Analisarcontas{
        @Override
        public  JPanel mudarPanel(){
            return new AnaliseContas();
        }
    },
    CriarConta{
        @Override
        public  JPanel mudarPanel(){
            return new CriarConta();
        }
    };

    public abstract JPanel mudarPanel();
}
