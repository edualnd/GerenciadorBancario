package main.bancaria.gui.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AnaliseContas extends JPanel {
    private JTable tableAnalise;
    private JButton filtrarBtn, saldoTotalBtn, faixaSaldoBtn, ordenarBtn;
    private JPanel panel;
    
    public AnaliseContas() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600, 400));
        setBackground(new Color(248, 102, 36));

        JLabel title = new JLabel("Analisar contas");
        title.setFont(new Font("Arial", Font.BOLD, 23));
        title.setForeground(Color.WHITE);

        String[] colunas = {"Numero", "Titular", "Saldo"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        tableAnalise = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableAnalise);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 275));
        panel.setBackground(Color.WHITE);

        filtrarBtn = configBotao("Filtrar");
        saldoTotalBtn = configBotao("Saldo Total");
        faixaSaldoBtn = configBotao("Faixa de Saldo");
        ordenarBtn = configBotao("Ordenar");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(title, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);


        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        add(panel, gbc);


        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        add(filtrarBtn, gbc);

        gbc.gridx = 1;
        add(saldoTotalBtn, gbc);

        gbc.gridx = 2;
        add(faixaSaldoBtn, gbc);

        gbc.gridx = 3;
        add(ordenarBtn, gbc);
        
        configActionBtn(filtrarBtn);
        configActionBtn(saldoTotalBtn);
        configActionBtn(faixaSaldoBtn);
        configActionBtn(ordenarBtn);

    }

    private void configActionBtn(JButton btn) {
        btn.addActionListener(e -> mudarPanel(btn.getName()));
    }

    private void mudarPanel(String name) {
        panel.removeAll();
        panel.add(new Filtrar());
        repaint();
        revalidate();
    }

    private JButton configBotao(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(248, 102, 36));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setName(text);
        return button;
    }
    
    public JTable getTable() {
        return tableAnalise;
    }
    
    public JButton getFiltrarBtn() { return filtrarBtn; }
    public JButton getSaldoTotalBtn() { return saldoTotalBtn; }
    public JButton getFaixaSaldoBtn() { return faixaSaldoBtn; }
    public JButton getOrdenarBtn() { return ordenarBtn; }
}