package main.bancaria.gui.panels;

import javax.swing.*;
import java.awt.*;

public class Filtrar extends JPanel {
    private ButtonGroup filterGroup;
    private JRadioButton saldoMaior10k, contasPares, saldoMaior5k;

    public Filtrar() {
        setPreferredSize(new Dimension(600, 200));

        filterGroup = new ButtonGroup();

        saldoMaior10k = createRadioButton("Saldo > 10000", 120, 40);
        contasPares = createRadioButton("Contas pares", 250, 40);
        saldoMaior5k = createRadioButton("Saldo > 5000", 380, 40);

        saldoMaior10k.setSelected(true);
    }

    private JRadioButton createRadioButton(String text, int x, int y) {
        JRadioButton radio = new JRadioButton(text);
        radio.setFont(new Font("Arial", Font.BOLD, 12));
        radio.setBounds(x, y, 120, 30);
        filterGroup.add(radio);
        add(radio);
        return radio;
    }

    public String getFiltroSelecionado() {
        if (saldoMaior10k.isSelected()) return "saldo_10000";
        if (contasPares.isSelected()) return "contas_pares";
        if (saldoMaior5k.isSelected()) return "saldo_5000";
        return "";
    }
}