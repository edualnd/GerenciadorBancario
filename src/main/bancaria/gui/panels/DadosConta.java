package main.bancaria.gui.panels;

import javax.swing.*;
import java.awt.*;

public class DadosConta extends JPanel {
    private JTextField titularField, numeroField, saldoField, tarifaField;
    private JButton depositarBtn, transferirBtn, calcularBtn, deletarBtn, sacarBtn;
    
    public DadosConta() {
        setLayout(null);
        setPreferredSize(new Dimension(630, 400));
        
        JLabel title = new JLabel("Operações bancarias");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBounds(239, 8, 200, 20);
        add(title);
        
        // Campos de texto
        titularField = createTextField("Titular:", 178, 36, 95, 182);
        numeroField = createTextField("Numero:", 178, 73, 95, 182);
        saldoField = createTextField("Saldo:", 178, 110, 95, 182);
        tarifaField = createTextField("main.bancaria.gui.panels.Tarifa:", 178, 147, 95, 182);
        
        // Botões
        sacarBtn = createButton("Saque", 0, 361);
        depositarBtn = createButton("Deposito", 1, 361);
        transferirBtn = createButton("Transferencia", 2, 361);
        calcularBtn = createButton("Valor tarifa", 3, 361);
        deletarBtn = createButton("Deletar conta", 4, 361);
        
        // Separadores
        JSeparator sep1 = new JSeparator();
        sep1.setBounds(0, 187, 630, 7);
        add(sep1);
        
        JSeparator sep2 = new JSeparator();
        sep2.setBounds(1, 353, 630, 7);
        add(sep2);
        
        JSeparator sep3 = new JSeparator();
        sep3.setBounds(0, 32, 630, 7);
        add(sep3);
    }
    
    private JTextField createTextField(String labelText, int x, int y, int labelWidth, int fieldWidth) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setBounds(x, y, labelWidth, 30);
        add(label);
        
        JTextField field = new JTextField();
        field.setEditable(false);
        field.setBounds(x + labelWidth, y, fieldWidth, 25);
        add(field);
        
        return field;
    }
    
    private JButton createButton(String text, int column, int y) {
        JButton button = new JButton(text);
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBounds(column * 126, y, 120, 30);
        add(button);
        return button;
    }
    
    // Getters para os campos e botões
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