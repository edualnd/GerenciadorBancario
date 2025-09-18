package main.bancaria.view.panels.funcionalidades;


import main.bancaria.enums.FiltrarStrategy;
import main.bancaria.model.ContaCorrente;
import main.bancaria.service.BancoService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FiltrarPanel extends JPanel {

    private final List<ContaCorrente> dados;
    private final JComboBox<String> filtrarCmbBox = new JComboBox<>();
    private final DefaultListModel<String> model = new DefaultListModel<>();
    private final JList<String> listaFormatada = new JList<>(model);
    private final JScrollPane scroll = new JScrollPane(listaFormatada);
    private final JButton btnEnviar = new JButton();

    public FiltrarPanel(List<ContaCorrente> dados) {
        this.dados = dados;
        initComponents();
    }

    private void initComponents(){
        setPreferredSize(new Dimension(500,400));
        setForeground(Color.BLACK);
        setLayout(new GridBagLayout());

        JLabel titulo = new JLabel();
        titulo.setText("Filtrar contas");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel res = new JPanel();
        res.setPreferredSize(new Dimension(400,300));
        res.setMinimumSize(new Dimension(400,300));
        res.setLayout(new GridBagLayout());


        filtrarCmbBox.addItem("Escolha um filtro");
        filtrarCmbBox.addItem("Saldo > 1000");
        filtrarCmbBox.addItem("Saldo > 5000 E contas pares");

        btnEnviar.setText("Filtrar");
        btnEnviar.addActionListener(e -> onClickBtnEnviar());

        JPanel inpContainer = new JPanel();
        inpContainer.add(filtrarCmbBox);
        inpContainer.add(Box.createHorizontalStrut(15));
        inpContainer.add(btnEnviar);
        inpContainer.setOpaque(false);


        scroll.setPreferredSize(new Dimension(400,150));
        scroll.setMaximumSize(new Dimension(400,150));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.insets = new Insets(5, 10, 5, 10);
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
        List<ContaCorrente> listaFiltrada = new ArrayList<>();
        int value = filtrarCmbBox.getSelectedIndex();
        FiltrarStrategy strategy = null;
        switch (value){
            case 1 -> strategy = FiltrarStrategy.SALDOMAIOR10000;
            case 2 -> strategy = FiltrarStrategy.SALDOMAIOR5000;
            case 3 -> strategy = FiltrarStrategy.NUMEROPAR;
        }
        if (strategy != null) listaFiltrada = bs.filtrarContas(dados, strategy);


        model.clear();
        if (value != 0){

            for (ContaCorrente c: listaFiltrada){
                String num = String.format("%04d", c.getNumero());
                String dados =
                        "Numero: " + num + "  //  Titular: " + c.getTitular() + "  //  " +
                                "Saldo: " + c.getSaldo();
                model.addElement(dados);
            }
            if (listaFiltrada.isEmpty()){
                model.addElement("Nenhuma conta atende a condição");
            }
        }
    }
}
