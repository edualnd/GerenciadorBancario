package main.bancaria.gui.panels;

import main.bancaria.gui.BtnStrategy;

import javax.swing.*;
import java.awt.*;

public class Inicio extends JPanel {
    private JButton operacoesBtn, analisarBtn;
    
    public Inicio() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600, 400));
        setBackground(new Color(248, 102, 36));
        
        JLabel title = new JLabel("Gerenciamento Bancario");
        title.setFont(new Font("Arial", Font.BOLD, 23));
        title.setForeground(Color.WHITE);

        operacoesBtn = configBotao("Operações bancarias");
        analisarBtn = configBotao("Analisar contas");


        operacoesBtn.setBounds(226, 159, 160, 36);
        analisarBtn.setBounds(226, 200, 160, 36);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        add(title,gbc);
        gbc.gridy = 1;
        add(operacoesBtn,gbc);
        gbc.gridy = 2;
        add(analisarBtn,gbc);
    }
    
    private JButton configBotao(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(209, 87, 31));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(null);
        btn.setPreferredSize(new Dimension(100, 30));
        text = text.replace(" ","");
        btn.setName(text);
        btn.addActionListener(e -> mudarPagina(btn.getName()));
        return btn;
    }

    private void mudarPagina(String name) {
        BtnStrategy strategy = BtnStrategy.valueOf(name);
        removeAll();
        add(strategy.mudarPanel());
        repaint();
        revalidate();
    }

    public JButton getOperacoesBtn() { return operacoesBtn; }
    public JButton getAnalisarBtn() { return analisarBtn; }
}