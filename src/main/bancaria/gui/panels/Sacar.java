package main.bancaria.gui.panels;

import javax.swing.*;
import java.awt.*;



// Classe main.bancaria.gui.panels.Sacar é similar
public class Sacar extends JPanel {
    private JTextField valorField;
    private JButton sacarBtn;
    
    public Sacar() {
        setLayout(null);
        setPreferredSize(new Dimension(600, 165));
        
        JLabel label = new JLabel("Valor:");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setBounds(200, 68, 50, 20);
        add(label);
        
        valorField = new JTextField();
        valorField.setBounds(254, 68, 146, 25);
        add(valorField);
        
        sacarBtn = new JButton("main.bancaria.gui.panels.Sacar");
        sacarBtn.setBounds(255, 112, 90, 24);
        add(sacarBtn);
    }
    
    public JButton getSacarBtn() { return sacarBtn; }
    public String getValor() { return valorField.getText(); }
}