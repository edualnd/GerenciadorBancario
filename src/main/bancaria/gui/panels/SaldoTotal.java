package main.bancaria.gui.panels;

import javax.swing.*;
import java.awt.*;

public class SaldoTotal extends JPanel {
    private JTextField saldoField;
    
    public SaldoTotal() {
        setLayout(null);
        setPreferredSize(new Dimension(600, 110));
        
        JLabel label = new JLabel("Saldo Total:");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBounds(156, 45, 100, 20);
        add(label);
        
        saldoField = new JTextField();
        saldoField.setBounds(273, 43, 150, 25);
        add(saldoField);
    }
    
    public void setSaldo(double valor) {
        saldoField.setText(String.format("%.2f", valor));
    }
}