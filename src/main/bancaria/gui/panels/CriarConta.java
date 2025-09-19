package main.bancaria.gui.panels;

import javax.swing.*;
import java.awt.*;

public class CriarConta extends JPanel {
    private JTextField titularField;
    private JComboBox<String> tarifaCombo;
    private JButton criarButton;

    public JTextField getTitularField() {
        return titularField;
    }

    public void setTitularField(JTextField titularField) {
        this.titularField = titularField;
    }

    public void setTarifaCombo(JComboBox<String> tarifaCombo) {
        this.tarifaCombo = tarifaCombo;
    }

    public void setCriarButton(JButton criarButton) {
        this.criarButton = criarButton;
    }

    public CriarConta() {
        setLayout(null);
        setPreferredSize(new Dimension(387, 356));
        
        JLabel title = new JLabel("Criar conta");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBounds(152, 14, 100, 20);
        add(title);
        
        JLabel titularLabel = new JLabel("Titular:");
        titularLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        titularLabel.setBounds(55, 128, 60, 30);
        add(titularLabel);
        
        JLabel tarifaLabel = new JLabel("main.bancaria.gui.panels.Tarifa:");
        tarifaLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        tarifaLabel.setBounds(55, 165, 60, 30);
        add(tarifaLabel);
        
        titularField = new JTextField();
        titularField.setEditable(false);
        titularField.setBounds(118, 128, 214, 25);
        add(titularField);
        
        tarifaCombo = new JComboBox<>();
        tarifaCombo.setBounds(118, 165, 214, 25);
        add(tarifaCombo);
        
        criarButton = new JButton("Criar conta");
        criarButton.setBackground(Color.GREEN);
        criarButton.setForeground(Color.WHITE);
        criarButton.setFont(new Font("Arial", Font.BOLD, 16));
        criarButton.setBounds(141, 219, 120, 30);
        add(criarButton);
    }
    
    public JButton getCriarButton() {
        return criarButton;
    }
    
    public JComboBox<String> getTarifaCombo() {
        return tarifaCombo;
    }
    
    public void setTitular(String titular) {
        titularField.setText(titular);
    }
}