package main.bancaria.gui.panels;

import javax.swing.*;
import java.awt.*;

public class EscolherConta extends JPanel {
    private JComboBox<String> contaCombo;
    private JLabel criarContaLbl;
    
    public EscolherConta() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600, 400));
        setBackground(new Color(248, 102, 36));

        JLabel title = new JLabel("Escolha uma conta");
        title.setFont(new Font("Arial", Font.BOLD, 23));
        title.setForeground(Color.WHITE);
        add(title);

        contaCombo = new JComboBox<>();
        contaCombo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        criarContaLbl = new JLabel("Não tem conta? Criar conta");
        criarContaLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        criarContaLbl.setForeground(Color.WHITE);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30,10, 5, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(title, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(contaCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(criarContaLbl, gbc);
    }
    
    public JComboBox<String> getContaCombo() { return contaCombo; }
    public JLabel getCriarContaLbl() { return criarContaLbl; }
}