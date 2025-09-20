package main.bancaria.gui.panels;

import javax.swing.*;
import java.awt.*;



public class Ordenar extends JPanel {
    private ButtonGroup orderGroup;
    private JRadioButton porNome, porSaldo;
    
    public Ordenar() {
        setPreferredSize(new Dimension(600, 100));
        setOpaque(false);
        
        orderGroup = new ButtonGroup();
        
        porNome = configBtn("Por nome");
        porSaldo = configBtn("Por saldo");
        
        porNome.setSelected(true);

        add(porNome);
        add(porSaldo);
    }
    
    private JRadioButton configBtn(String text) {
        JRadioButton radio = new JRadioButton(text);
        radio.setFont(new Font("Arial", Font.BOLD, 12));
        radio.setOpaque(false);
        orderGroup.add(radio);
        return radio;
    }
    
    public String getOrdenacaoSelecionada() {
        if (porNome.isSelected()) return "nome";
        if (porSaldo.isSelected()) return "saldo";
        return "";
    }
}