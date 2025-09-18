package main.bancaria.view.strategy;



import main.bancaria.model.ContaCorrente;
import main.bancaria.view.panels.ListarContasPanel;
import main.bancaria.view.panels.funcionalidades.*;

import javax.swing.*;
import java.util.List;

public enum OperacaoStrategy {
    Calcular {
        @Override
        public JPanel getPanel(List<ContaCorrente>dados){
            return new CalcularPanel(dados);

        }
    },
    Listar {
        @Override
        public JPanel getPanel(List<ContaCorrente>dados){
            return new ListarContasPanel();

        }
    },
    Filtrar {
        @Override
        public JPanel getPanel(List<ContaCorrente>dados){
            return new FiltrarPanel(dados);

        }
    },
    Faixa {
        @Override
        public JPanel getPanel(List<ContaCorrente>dados){
            return new FaixaPanel(dados);


        }
    },
    Ordenar {
        @Override
        public JPanel getPanel(List<ContaCorrente>dados){
            return new OrdenarPanel(dados);

        }
    },
    Saldo{
        @Override
        public JPanel getPanel(List<ContaCorrente>dados){
            return new SaldoTotalPanel(dados);

        }
    },
    Saque{
        @Override
       public JPanel getPanel(List<ContaCorrente>dados){
            return new SaquePanel(dados);

        }
    },
    Deposito{
        @Override
        public JPanel getPanel(List<ContaCorrente>dados){
            return new DepositoPanel(dados);

        }
    },
    Transferir{
        @Override
        public JPanel getPanel(List<ContaCorrente>dados){
            return new TransferirPanel(dados);

        }
    },
    Criar{
        @Override
       public JPanel getPanel(List<ContaCorrente>dados){
            return new CriarPanel();

        }
    },
    Deletar{
        @Override
        public JPanel getPanel(List<ContaCorrente>dados){
            return new DeletarPanel(dados);

        }
    };
    public abstract JPanel getPanel(List<ContaCorrente>dados);


}
