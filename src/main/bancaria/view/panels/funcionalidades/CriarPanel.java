package main.bancaria.view.panels.funcionalidades;



import main.bancaria.enums.TarifaStrategy;
import main.bancaria.service.ContaService;

import javax.swing.*;
import java.awt.*;

public class CriarPanel extends JPanel{

    private final JButton btnEnviar = new JButton();
    private final JComboBox<String> tiposTarifa = new JComboBox<>();
    private final JTextField nomeTxtField = new JTextField();

    public CriarPanel() {
        initComponents();
    }

    private void initComponents(){
        JLabel titulo = new JLabel();
        titulo.setText("Criar conta");

        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setOpaque(true);

        setPreferredSize(new Dimension(500,400));
        setForeground(Color.BLACK);
        setLayout(new GridBagLayout());


        JPanel res = new JPanel();
        res.setPreferredSize(new Dimension(250,150));
        res.setMinimumSize(new Dimension(250,150));
        res.setLayout(new GridBagLayout());


        btnEnviar.setText("Criar");
        btnEnviar.addActionListener(e -> {
            if(tiposTarifa.getSelectedIndex() == 0){
                JOptionPane.showMessageDialog(null,"Escolha uma tarifa");
            } else if (nomeTxtField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Nome é obrigatorio");
            }else onClickBtnEnviar();
        });

        JPanel nomeContainer = new JPanel();
        nomeContainer.setLayout((new GridLayout(2, 1, 5, 5)));
        nomeContainer.setPreferredSize(new Dimension(200,40));
        nomeContainer.setOpaque(false);

        JLabel nomeLbl = new JLabel("Nome: ");
        nomeTxtField.setColumns(10);

        JPanel tarifaContainer = new JPanel();
        tarifaContainer.setLayout((new GridLayout(2, 1, 5, 5)));
        tarifaContainer.setPreferredSize(new Dimension(200,40));

        JLabel tarifaLbl = new JLabel("Tipo de tarifa: ");
        tiposTarifa.addItem("Escolha uma tarifa");
        tiposTarifa.addItem("ISENTA");
        tiposTarifa.addItem("FIXA");
        tiposTarifa.addItem("PERCENTUAL");

        nomeContainer.add(nomeLbl);
        tarifaContainer.add(tarifaLbl);

        nomeContainer.add(nomeTxtField);
        tarifaContainer.add(tiposTarifa);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        gbc.gridx = 0;
        res.add(nomeContainer,gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        res.add(tarifaContainer,gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        res.add(btnEnviar,gbc);

        gbc.insets = new Insets(0,0,0,10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridy = 0;
        add(titulo, gbc);
        gbc.gridy = 1;
        add(res, gbc);



    }

    private void onClickBtnEnviar() {
        String nome = nomeTxtField.getText();
        TarifaStrategy tarifa =
                TarifaStrategy.valueOf(tiposTarifa.getSelectedItem().toString());

        ContaService cs = new ContaService();
        if(cs.criarConta(nome,tarifa)){
            JOptionPane.showMessageDialog(null,"Conta criada com sucesso");
            nomeTxtField.setText("");
            tiposTarifa.setSelectedIndex(0);
        }else{
            JOptionPane.showMessageDialog(null,"Erro ao criar a conta");
        }

    }
}