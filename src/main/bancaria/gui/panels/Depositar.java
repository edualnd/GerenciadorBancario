package main.bancaria.gui.panels;

import javax.swing.*;
import java.awt.*;

public class Depositar extends JPanel {
    private JTextField valorField;
    private JButton depositarBtn;

    public Depositar() {
        setLayout(null);
        setPreferredSize(new Dimension(600, 165));

        JLabel label = new JLabel("Valor:");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setBounds(200, 68, 50, 20);
        add(label);

        valorField = new JTextField();
        valorField.setBounds(254, 68, 146, 25);
        add(valorField);

        depositarBtn = new JButton("Depositar");
        depositarBtn.setBounds(255, 112, 90, 24);
        add(depositarBtn);
    }

    public JButton getDepositarBtn() { return depositarBtn; }
    public String getValor() { return valorField.getText(); }
}