package main.bancaria.gui.panels;

import main.bancaria.model.ContaCorrente;
import main.bancaria.service.ContaService;

import javax.swing.*;
import java.awt.*;

public class DadosConta extends JPanel {
    private JTextField titularField, numeroField, saldoField, tarifaField;
    private JButton depositarBtn, transferirBtn, calcularBtn, deletarBtn, sacarBtn;
    private ContaCorrente contaEscolhida;
    private JPanel panel;
    
    public DadosConta(int numConta) {
        setPreferredSize(new Dimension(630, 400));
        setOpaque(false);
        carregarConta(numConta);
        iniciarComponetes();

    }

    private void carregarConta(int numConta) {
        ContaService cs = new ContaService();
        contaEscolhida = cs.selecionarConta(numConta);
    }

    private void iniciarComponetes(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

// Título (linha 0)
        JLabel title = new JLabel("Operações bancarias");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(title, gbc);

// Configurar os campos
        titularField = configTxtField();
        numeroField = configTxtField();
        saldoField = configTxtField();
        tarifaField = configTxtField();

// Campos (linhas 1, 2, 3, 4)
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0.0; // Importante: resetar weighty

// Linha 1 - Titular
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(configTxtLabel("Titular:"), gbc);

        gbc.gridx = 1;
        add(titularField, gbc);

// Linha 2 - Numero
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(configTxtLabel("Numero:"), gbc);

        gbc.gridx = 1;
        add(numeroField, gbc);

// Linha 3 - Saldo
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(configTxtLabel("Saldo:"), gbc);

        gbc.gridx = 1;
        add(saldoField, gbc);

// Linha 4 - Tarifa
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(configTxtLabel("Tarifa:"), gbc);

        gbc.gridx = 1;
        add(tarifaField, gbc);

// Panel principal (linha 5)
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 275));
        panel.setBackground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.weighty = 1.0; // Faz o panel expandir
        gbc.fill = GridBagConstraints.BOTH;
        add(panel, gbc);

// Botões (linha 6)
        sacarBtn = configBtn("Saque");
        depositarBtn = configBtn("Deposito");
        transferirBtn = configBtn("Transferencia");
        calcularBtn = configBtn("Valor tarifa");
        deletarBtn = configBtn("Deletar conta");

        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.weighty = 0.0; // Reset weighty
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        add(sacarBtn, gbc);

        gbc.gridx = 1;
        add(depositarBtn, gbc);

        gbc.gridx = 2;
        add(transferirBtn, gbc);

        gbc.gridx = 3;
        add(calcularBtn, gbc);

// Se quiser o botão deletar na mesma linha
        gbc.gridx = 4;
        add(deletarBtn, gbc);
    }
    
    private JTextField configTxtField() {
        JTextField field = new JTextField();
        field.setColumns(10);

        return field;
    }
    private JLabel configTxtLabel(String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setForeground(Color.WHITE);

        return label;
    }
    
    private JButton configBtn(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(248, 102, 36));
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        text = text.replace(" ", "");
        btn.setName(text);

        return btn;
    }

    public JTextField getTitularField() { return titularField; }
    public JTextField getNumeroField() { return numeroField; }
    public JTextField getSaldoField() { return saldoField; }
    public JTextField getTarifaField() { return tarifaField; }
    public JButton getDepositarBtn() { return depositarBtn; }
    public JButton getTransferirBtn() { return transferirBtn; }
    public JButton getCalcularBtn() { return calcularBtn; }
    public JButton getDeletarBtn() { return deletarBtn; }
    public JButton getSacarBtn() { return sacarBtn; }
}