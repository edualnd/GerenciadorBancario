package main.bancaria.gui.panels;

import javax.swing.*;
import java.awt.*;

public class Tarifa extends JPanel {
    private JTextField valorField;
    
    public Tarifa() {
        setLayout(null);
        setPreferredSize(new Dimension(600, 165));
        
        JLabel label = new JLabel("Valor da tarifa:");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setBounds(182, 68, 124, 20);
        add(label);
        
        valorField = new JTextField();
        valorField.setBounds(306, 68, 133, 24);
        add(valorField);
    }
    
    public void setTarifa(double valor) {
        valorField.setText(String.format("%.2f", valor));
    }
}