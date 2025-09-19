package main.bancaria.gui.panels;

import javax.swing.*;
import java.awt.*;

public class Transferir extends JPanel {
    private JComboBox<String> contaDestinoCombo;
    private JTextField valorField;
    private JButton transferirBtn;
    
    public Transferir() {
        setLayout(null);
        setPreferredSize(new Dimension(600, 165));
        
        JLabel title = new JLabel("Para quem deseja transferir?");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBounds(178, 14, 250, 20);
        add(title);
        
        contaDestinoCombo = new JComboBox<>();
        contaDestinoCombo.setBounds(236, 44, 150, 25);
        add(contaDestinoCombo);
        
        JLabel valorLabel = new JLabel("Valor:");
        valorLabel.setFont(new Font("Arial", Font.BOLD, 16));
        valorLabel.setBounds(178, 83, 50, 20);
        add(valorLabel);
        
        valorField = new JTextField();
        valorField.setBounds(236, 81, 150, 24);
        add(valorField);
        
        transferirBtn = new JButton("Transferir");
        transferirBtn.setBackground(Color.GREEN);
        transferirBtn.setForeground(Color.WHITE);
        transferirBtn.setFont(new Font("Arial", Font.BOLD, 12));
        transferirBtn.setBounds(248, 127, 122, 20);
        transferirBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(transferirBtn);
    }
    
    public JComboBox<String> getContaDestinoCombo() { return contaDestinoCombo; }
    public JTextField getValorField() { return valorField; }
    public JButton getTransferirBtn() { return transferirBtn; }
}