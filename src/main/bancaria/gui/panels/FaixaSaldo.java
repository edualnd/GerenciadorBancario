package main.bancaria.gui.panels;

import javax.swing.*;
import java.awt.*;

public class FaixaSaldo extends JPanel {
    private ButtonGroup filterGroup;
    private JRadioButton saldoMaior10k, contasPares, saldoMaior5k;

    public FaixaSaldo() {
        setPreferredSize(new Dimension(600, 100));
        setOpaque(false);

        filterGroup = new ButtonGroup();

        saldoMaior10k = configBtn("Até 5000");
        contasPares = configBtn("de 5001 a 10000");
        saldoMaior5k = configBtn("Acima de 10000");

        saldoMaior10k.setSelected(true);

        add(saldoMaior10k);
        add(contasPares);
        add(saldoMaior5k);

    }

    private JRadioButton configBtn(String text) {
        JRadioButton radio = new JRadioButton(text);
        radio.setFont(new Font("Arial", Font.BOLD, 12));
        radio.setOpaque(false);
        filterGroup.add(radio);
        return radio;
    }

    public String getFiltroSelecionado() {
        if (saldoMaior10k.isSelected()) return "saldo_10000";
        if (contasPares.isSelected()) return "contas_pares";
        if (saldoMaior5k.isSelected()) return "saldo_5000";
        return "";
    }
}