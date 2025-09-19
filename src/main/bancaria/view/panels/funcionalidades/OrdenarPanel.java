package main.bancaria.view.panels.funcionalidades;


import main.bancaria.enums.OrdenarStrategy;
import main.bancaria.model.ContaCorrente;
import main.bancaria.service.BancoService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrdenarPanel extends JPanel{

    private final List<ContaCorrente> dados;
    private final JButton btnEnviar = new JButton();
    private JComboBox<String> ordenarCmbBox = new JComboBox<>();
    private final DefaultListModel<String> model = new DefaultListModel<>();
    private final JList<String> listaFormatada = new JList<>(model);
    private final JScrollPane scroll = new JScrollPane(listaFormatada);

    public OrdenarPanel(List<ContaCorrente> dados) {
        this.dados = dados;
        initComponents();
    }

    private void initComponents(){
        JLabel titulo = new JLabel();
        titulo.setText("main.bancaria.gui.panels.Ordenar contas");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        setPreferredSize(new Dimension(500,400));
        setForeground(Color.BLACK);
        setLayout(new GridBagLayout());

        JPanel res = new JPanel();
        res.setPreferredSize(new Dimension(400,300));
        res.setMinimumSize(new Dimension(400,300));
        res.setLayout(new GridBagLayout());

        ordenarCmbBox = new JComboBox<>();
        ordenarCmbBox.addItem("Escolha uma ordenação");
        ordenarCmbBox.addItem("Por nome");
        ordenarCmbBox.addItem("Por saldo");

        btnEnviar.setText("main.bancaria.gui.panels.Ordenar");
        btnEnviar.addActionListener(e -> onClickBtnEnviar());

        JPanel inpContainer = new JPanel();
        inpContainer.setPreferredSize(new Dimension(100,70));
        inpContainer.add(ordenarCmbBox);
        inpContainer.add(Box.createHorizontalStrut(15));
        inpContainer.add(btnEnviar);
        inpContainer.setOpaque(false);

        scroll.setPreferredSize(new Dimension(400,200));
        scroll.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;

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

    private void onClickBtnEnviar() {
        BancoService bs = new BancoService();
        int value = ordenarCmbBox.getSelectedIndex();
        if (value == 1){
            bs.ordenacaoComStrategy(dados, OrdenarStrategy.PORNOME);
        }else if(value == 2){
            bs.ordenacaoComStrategy(dados, OrdenarStrategy.PORSALDO);
        }

        model.clear();
        if (value != 0){
            for (ContaCorrente c: dados){
                String num = String.format("%04d", c.getNumero());
                String dados =
                        "Numero: " + num + "  //  Titular: " + c.getTitular() + "  //  " +
                                "Saldo: " + c.getSaldo();
                model.addElement(dados);
            }
        }
    }
}
