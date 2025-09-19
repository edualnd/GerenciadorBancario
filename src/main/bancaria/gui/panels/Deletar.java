package main.bancaria.gui.panels;

import javax.swing.*;
import java.awt.*;

public class Deletar extends JPanel {
    private JButton deletarBtn;
    
    public Deletar() {
        setLayout(null);
        setPreferredSize(new Dimension(600, 165));
        
        JLabel title = new JLabel("Deseja deletar a conta?");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBounds(203, 38, 200, 20);
        add(title);
        
        deletarBtn = new JButton("Deletar conta");
        deletarBtn.setBackground(Color.RED);
        deletarBtn.setForeground(Color.WHITE);
        deletarBtn.setFont(new Font("Arial", Font.BOLD, 12));
        deletarBtn.setBounds(248, 71, 122, 35);
        deletarBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(deletarBtn);
    }
    
    public JButton getDeletarBtn() { return deletarBtn; }
}