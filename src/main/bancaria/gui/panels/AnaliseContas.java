package main.bancaria.gui.panels;

import main.bancaria.gui.BtnStrategy;
import main.bancaria.model.ContaCorrente;
import main.bancaria.view.panels.funcionalidades.FaixaPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AnaliseContas extends JPanel {
    private JTable tableAnalise;
    private JButton filtrarBtn, saldoTotalBtn, faixaSaldoBtn, ordenarBtn;
    private JPanel panel;
    private JButton sairBtn;
    
    public AnaliseContas() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600, 400));
        setBackground(new Color(248, 102, 36));

        JLabel title = new JLabel("Analisar contas");
        title.setFont(new Font("Arial", Font.BOLD, 23));
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
        gbc.anchor = GridBagConstraints.NORTHWEST; // Canto superior esquerdo
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(sairBtn, gbc);

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

        sairBtn.addActionListener(e -> {
            Container pai = getParent();
            if (pai != null) {
                Container avo = pai.getParent();
                pai.remove(this);
                pai.add(new Inicio());
                pai.revalidate();
                pai.repaint();

            }
        });

    }
    private JButton configBotao(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(248, 102, 36));
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        text = text.replace(" ", "");
        btn.setName(text);
        configActionBtn(btn);
        return btn;
    }

    private void configActionBtn(JButton btn) {
        btn.addActionListener(e -> mudarPanel(btn.getName()));
    }

    private void mudarPanel(String name) {
        BtnStrategy strategy = BtnStrategy.valueOf(name);
        panel.removeAll();
        panel.add(strategy.mudarPanel());
        repaint();
        revalidate();
    }


    
    public JTable getTable() {
        return tableAnalise;
    }
    
    public JButton getFiltrarBtn() { return filtrarBtn; }
    public JButton getSaldoTotalBtn() { return saldoTotalBtn; }
    public JButton getFaixaSaldoBtn() { return faixaSaldoBtn; }
    public JButton getOrdenarBtn() { return ordenarBtn; }
}