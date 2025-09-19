package main.bancaria.gui.panels;

import javax.swing.*;
import java.awt.*;



public class Ordenar extends JPanel {
    private ButtonGroup orderGroup;
    private JRadioButton porNome, porSaldo;
    
    public Ordenar() {
        setLayout(null);
        setPreferredSize(new Dimension(600, 110));
        
        orderGroup = new ButtonGroup();
        
        porNome = createRadioButton("Por nome", 221, 43);
        porSaldo = createRadioButton("Por saldo", 331, 43);
        
        porNome.setSelected(true);
    }
    
    private JRadioButton createRadioButton(String text, int x, int y) {
        JRadioButton radio = new JRadioButton(text);
        radio.setFont(new Font("Arial", Font.BOLD, 12));
        radio.setBounds(x, y, 100, 30);
        orderGroup.add(radio);
        add(radio);
        return radio;
    }
    
    public String getOrdenacaoSelecionada() {
        if (porNome.isSelected()) return "nome";
        if (porSaldo.isSelected()) return "saldo";
        return "";
    }
}