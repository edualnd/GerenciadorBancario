package main.bancaria.view.panels.funcionalidades;

import main.bancaria.model.ContaCorrente;
import main.bancaria.service.BancoService;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaixaPanel extends JPanel{

    private JComboBox<String> faixaCmbBox = new JComboBox<>();
    private final DefaultListModel<String> model = new DefaultListModel<>();
    private final JList<String> listaFormatada = new JList<>(model);
    private final JScrollPane scroll = new JScrollPane(listaFormatada);

    private final List<ContaCorrente> dados;
    private Map<String, List<ContaCorrente>> listaFaixas = new HashMap<>();


    public FaixaPanel(List<ContaCorrente> dados) {
        this.dados= dados;
        carregarFaixas();
        initComponents();
    }

    private void carregarFaixas() {
        BancoService bs = new BancoService();
        listaFaixas = bs.faixaSaldo(dados);
    }

    private void initComponents(){
        JLabel titulo = new JLabel();
        titulo.setText("Faixa de saldo");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        setPreferredSize(new Dimension(500,400));
        setForeground(Color.BLACK);
        setLayout(new GridBagLayout());

        JPanel res = new JPanel();
        res.setPreferredSize(new Dimension(400,300));
        res.setMinimumSize(new Dimension(400,300));
        res.setLayout(new GridBagLayout());


        faixaCmbBox = new JComboBox<>();
        faixaCmbBox.addItem("Escolha uma faixa");
        faixaCmbBox.addItem("Até R$ 5000");
        faixaCmbBox.addItem("De R$ 5001 a R$ 10000");
        faixaCmbBox.addItem("Acima de R$ 10000");

        faixaCmbBox.addActionListener(e -> {
            if (faixaCmbBox.getSelectedIndex() != 0) onClickFaixaCmbBox();
            else model.clear();
        });

        JPanel inpContainer = new JPanel();
        inpContainer.setPreferredSize(new Dimension(100,70));
        inpContainer.add(faixaCmbBox);
        inpContainer.add(Box.createHorizontalStrut(15));
        inpContainer.setOpaque(false);


        scroll.setPreferredSize(new Dimension(400,200));
        scroll.setBackground(Color.BLACK);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,0,10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 5, 10);
        res.add(inpContainer, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 10, 10);
        res.add(scroll, gbc);

        gbc.insets = new Insets(0, 0,0,0);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridy = 0;
        add(titulo, gbc);
        gbc.gridy = 1;
        add(res, gbc);


    }

    private void onClickFaixaCmbBox() {
        String faixaEscolhida = faixaCmbBox.getSelectedItem().toString();
        List<ContaCorrente> l = listaFaixas.get(faixaEscolhida);
        model.clear();
        if (!l.isEmpty()){
            for (ContaCorrente c: l){
                String num = String.format("%04d", c.getNumero());
                String dados =
                        "Numero: " + num + "  //  Titular: " + c.getTitular() + "  //  " +
                                "Saldo: " + c.getSaldo();
                model.addElement(dados);
            }
        }
        else model.addElement("Nenhuma conta atende a condição");

    }
}
