package main.bancaria.view.panels.funcionalidades;



import main.bancaria.model.ContaCorrente;
import main.bancaria.service.BancoService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SaldoTotalPanel extends JPanel{

    private final List<ContaCorrente> dados;
    private double saldo;

    public SaldoTotalPanel(List<ContaCorrente> dados) {
        this.dados = dados;
        carregarSaldo();
        initComponents();
    }

    private void carregarSaldo() {
        BancoService bs = new BancoService();
        saldo = bs.saldoTotal(dados);
    }

    private void initComponents(){
        JLabel titulo = new JLabel();
        titulo.setText("Saldo total: ");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        setPreferredSize(new Dimension(500,400));
        setForeground(Color.BLACK);
        setLayout(new GridBagLayout());

        JPanel res = new JPanel();
        res.setPreferredSize(new Dimension(400,215));
        res.setMinimumSize(new Dimension(400,215));
        res.setLayout(new GridBagLayout());
        JLabel saldoTotal = new JLabel("R$ " + saldo);
        saldoTotal.setFont(new Font("Arial", Font.BOLD, 28));

        res.add(saldoTotal);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,0,10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;


        gbc.gridy = 0;
        add(titulo, gbc);
        gbc.gridy = 1;
        add(res, gbc);


    }
}
