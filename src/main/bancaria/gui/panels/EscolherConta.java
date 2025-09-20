package main.bancaria.gui.panels;

import main.bancaria.gui.BtnStrategy;
import main.bancaria.gui.Util;
import main.bancaria.model.ContaCorrente;
import main.bancaria.service.BancoService;
import main.bancaria.service.ContaService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EscolherConta extends JPanel {

    private JButton criarContaBtn;
    private List<ContaCorrente> dados;
    private final DefaultListModel<String> model = new DefaultListModel<>();
    private JComboBox<String> contaCombo;
    private JButton sairBtn;

    public EscolherConta() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600, 400));
        setBackground(new Color(248, 102, 36));
        carregarDados();
        inializeComponents();
    }

    private void inializeComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

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
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(sairBtn, gbc);


        JLabel title = new JLabel("Escolha uma conta");
        title.setFont(new Font("Arial", Font.BOLD, 23));
        title.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0;
        add(title, gbc);

        contaCombo = Util.preencherComboBox(dados);
        contaCombo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contaCombo.setPreferredSize(new Dimension(300,30));
        contaCombo.addActionListener(e -> {
            if (contaCombo.getSelectedIndex() != 0) contaEscolhida((String) contaCombo.getSelectedItem());
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(30, 10, 5, 10);
        add(contaCombo, gbc);

        criarContaBtn = new JButton("Não tem conta? Criar conta");
        criarContaBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        criarContaBtn.setOpaque(false);
        criarContaBtn.setBackground(new Color(Color.TRANSLUCENT));
        criarContaBtn.setBorder(null);
        criarContaBtn.setForeground(Color.WHITE);
        criarContaBtn.setName("CriarConta");

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(criarContaBtn, gbc);

        criarContaBtn.addActionListener(e -> mudarPagina(criarContaBtn.getName()));

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
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weighty = 1.0;
        add(Box.createGlue(), gbc);
    }

    private void contaEscolhida(String selectedItem) {
        int numConta = Integer.parseInt(selectedItem.split(" - ")[0]);
        removeAll();
        add(new DadosConta(numConta));
        repaint();
        revalidate();




    }


    private void mudarPagina(String name) {
        BtnStrategy strategy = BtnStrategy.valueOf(name);
        removeAll();
        add(strategy.mudarPanel());
        repaint();
        revalidate();
    }

    public void carregarDados() {
        BancoService bs = new BancoService();
        dados = bs.lerDados();
    }

    public JComboBox<String> getContaCombo() { return contaCombo; }
    public JButton getCriarContaBtn() { return criarContaBtn; }
}