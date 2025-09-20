package main.bancaria.gui.panels;

import javax.swing.*;
import java.awt.*;

public class CriarConta extends JPanel {
    private JTextField titularField;
    private JComboBox<String> tarifaCombo;
    private JButton criarButton;
    private JButton sairBtn;

    public CriarConta() {
        setPreferredSize(new Dimension(387, 300));
        setBackground(new Color(209, 87, 31));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Criar conta");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.WHITE);

        sairBtn = new JButton("X");
        sairBtn.setFont(new Font("Arial", Font.BOLD, 12));
        sairBtn.setForeground(Color.WHITE);
        sairBtn.setPreferredSize(new Dimension(40, 30));
        sairBtn.setOpaque(false);
        sairBtn.setBackground(new Color(0, 0, 0, 0));
        sairBtn.setBorder(null);
        sairBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(title, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(sairBtn, gbc);

        JLabel titularLabel = new JLabel("Titular:");
        titularLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        titularLabel.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(titularLabel, gbc);

        titularField = new JTextField();
        titularField.setColumns(15);

        gbc.gridy = 2;
        add(titularField, gbc);

        JLabel tarifaLabel = new JLabel("Tarifa:");
        tarifaLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        tarifaLabel.setForeground(Color.WHITE);

        gbc.gridy = 3;
        add(tarifaLabel, gbc);

        tarifaCombo = new JComboBox<>();
        tarifaCombo.addItem("Escolha uma tarifa");
        tarifaCombo.addItem("ISENTA");
        tarifaCombo.addItem("FIXA");
        tarifaCombo.addItem("PERCENTUAL");

        gbc.gridy = 4;
        add(tarifaCombo, gbc);

        criarButton = new JButton("Criar conta");
        criarButton.setBackground(new Color(100, 189, 91));
        criarButton.setForeground(Color.WHITE);
        criarButton.setFont(new Font("Arial", Font.BOLD, 16));
        criarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        gbc.gridy = 5;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        add(criarButton, gbc);

        sairBtn.addActionListener(e -> {
            Container pai = getParent();
            if (pai != null) {
                Container avo = pai.getParent();
                if (avo != null) {
                    avo.remove(pai);
                    avo.add(new EscolherConta());
                    avo.revalidate();
                    avo.repaint();
                }
            }
        });
    }

    
    public JButton getCriarButton() {
        return criarButton;
    }
    
    public JComboBox<String> getTarifaCombo() {
        return tarifaCombo;
    }

    public JTextField getTitularField() {
        return titularField;
    }

    public JButton getSairBtn() {
        return sairBtn;
    }
}