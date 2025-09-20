package main.bancaria.gui.panels;

import javax.swing.*;
import java.awt.*;

public class SaldoTotal extends JPanel {
    private JTextField saldoField;
    
    public SaldoTotal() {
        setPreferredSize(new Dimension(600, 110));
        setOpaque(false);
        
        JLabel label = new JLabel("Saldo Total:");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label);
        
        saldoField = new JTextField();
        saldoField.setColumns(10);
        saldoField.setEditable(false);
        add(saldoField);
    }
    
    public void setSaldo(double valor) {
        saldoField.setText(String.format("%.2f", valor));
    }
}