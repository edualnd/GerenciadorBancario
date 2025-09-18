package main.bancaria.view.panels;


import main.bancaria.model.ContaCorrente;
import main.bancaria.service.BancoService;
import main.bancaria.view.strategy.OperacaoStrategy;

import javax.swing.*;
import  java.util.List;


import java.awt.*;

public class ListarContasPanel extends JPanel {
    private final DefaultListModel<String> model = new DefaultListModel<>();
    private final JList<String> listaFormatada = new JList<>(model);
    private final JScrollPane scroll = new JScrollPane(listaFormatada);
    private List<ContaCorrente> dados;
    public ListarContasPanel() {
        carregarDados();
        initComponents();

    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(500,400));
        this.setForeground(Color.BLACK);
        this.setOpaque(false);


        scroll.setPreferredSize(new Dimension(500,400));
        this.add(scroll);



    }
    public void carregarDados() {
        BancoService bs = new BancoService();
        List<ContaCorrente> listaC = bs.lerDados();
        dados = listaC;
        model.clear();
        for (ContaCorrente c: listaC){
            String num = String.format("%04d", c.getNumero());
            String dados =
                    "Numero: " + num + "  //  Titular: " + c.getTitular() + "//  Saldo: R$" + c.getSaldo() + "//  Tarifa: " + c.getTarifa().name();
            model.addElement(dados);
        }


    }

    public void onClickBtn(OperacaoStrategy mostrar){
        this.removeAll();
        carregarDados();
        JPanel inpPanel = mostrar.getPanel(dados);
        this.add(inpPanel);
        this.revalidate();
        this.repaint();
    }

}
