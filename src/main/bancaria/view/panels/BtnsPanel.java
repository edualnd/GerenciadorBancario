package main.bancaria.view.panels;


import main.bancaria.view.listener.BtnListener;
import main.bancaria.view.strategy.OperacaoStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BtnsPanel extends JPanel {

    private BtnListener listener;
    private final JButton btnCriarConta = new JButton();
    private final JButton btnDeletarConta = new JButton();
    private final JButton btnFaixaSaldo = new JButton();
    private final JButton btnFiltrarConta = new JButton();
    private final JButton btnSaque = new JButton();
    private final JButton btnDeposito = new JButton();
    private final JButton btnTranferir = new JButton();
    private final JButton btnOrdenar = new JButton();
    private final JButton btnSaldoTotal = new JButton();

    private final JButton btnCalcularTarifa = new JButton();
    private final JButton btnListarContas = new JButton();
    private final JPanel btns = new JPanel(new GridLayout(3, 4,10,10));

    public BtnsPanel(){
        initComponents();
    }



    private void initComponents() {

        setPreferredSize(new Dimension(800,150));
        setOpaque(false);

        btns.setPreferredSize(new Dimension(800,150));
        btns.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        btns.setOpaque(false);

        btnConfig(btnCriarConta,"Criar conta");
        btnCriarConta.addActionListener(this::btnCriarContaMouseClicked);

        btnConfig(btnFiltrarConta,"Filtrar Conta");
        btnFiltrarConta.addActionListener(this::btnFiltrarContaMouseClicked);

        btnConfig(btnSaldoTotal,"Saldo total");
        btnSaldoTotal.addActionListener(this::btnSaldoTotalMouseClicked);

        btnConfig(btnFaixaSaldo,"Faixa de saldo");
        btnFaixaSaldo.addActionListener(this::btnFaixaSaldoMouseClicked);

        btnConfig(btnSaque,"Saque");
        btnSaque.addActionListener(this::btnSaqueMouseClicked);

        btnConfig(btnDeposito,"Deposito");
        btnDeposito.addActionListener(this::btnDepositoMouseClicked);

        btnConfig(btnTranferir,"Transferencia");
        btnTranferir.addActionListener(this::btnTranferirMouseClicked);

        btnConfig(btnOrdenar,"Ordenar");
        btnOrdenar.addActionListener(this::btnOrdenarMouseClicked);

        btnConfig(btnDeletarConta,"Deletar conta");
        btnDeletarConta.addActionListener(this::btnDeletarContaMouseClicked);

        btnConfig(btnListarContas,"Listar");
        btnListarContas.addActionListener(this::btnListarContasMouseClicked);

        btnConfig(btnCalcularTarifa,"Calcular Tarifa");
        btnCalcularTarifa.addActionListener(this::btnCalcularTarifaMouseClicked);

        btns.add(btnFiltrarConta);
        btns.add(btnOrdenar);
        btns.add(btnSaldoTotal);
        btns.add(btnFaixaSaldo);
        btns.add(btnSaque);
        btns.add(btnDeposito);
        btns.add(btnTranferir);
        btns.add(btnCalcularTarifa);
        btns.add(btnCriarConta);
        btns.add(btnDeletarConta);
        btns.add(btnListarContas);

        add(btns);
    }

    private void btnCalcularTarifaMouseClicked(ActionEvent actionEvent) {
        listener.onClickBtn(OperacaoStrategy.Calcular);
    }

    private void btnListarContasMouseClicked(ActionEvent actionEvent) {
        listener.onClickBtn(OperacaoStrategy.Listar);
    }

    private void btnConfig(JButton btn, String nome){
        btn.setText(nome);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(150, 8));
        btn.setMaximumSize(new Dimension(150, 8));
        btn.setBackground(new Color(223,223,223));
    }

    public void setListener(BtnListener listener) {
        this.listener = listener;
    }

    private void btnDeletarContaMouseClicked(ActionEvent e) {
        listener.onClickBtn(OperacaoStrategy.Deletar);
    }

    private void btnSaqueMouseClicked(ActionEvent e) {
        listener.onClickBtn(OperacaoStrategy.Saque);
    }

    private void btnDepositoMouseClicked(ActionEvent e) {
        listener.onClickBtn(OperacaoStrategy.Deposito);
    }

    private void btnOrdenarMouseClicked(ActionEvent e) {
        listener.onClickBtn(OperacaoStrategy.Ordenar);
    }

    private void btnTranferirMouseClicked(ActionEvent e) {
        listener.onClickBtn(OperacaoStrategy.Transferir);
    }

    private void btnFaixaSaldoMouseClicked(ActionEvent e) {
        listener.onClickBtn(OperacaoStrategy.Faixa);
    }

    private void btnSaldoTotalMouseClicked(ActionEvent e) {
        listener.onClickBtn(OperacaoStrategy.Saldo);
    }

    private void btnFiltrarContaMouseClicked(ActionEvent e) {
        listener.onClickBtn(OperacaoStrategy.Filtrar);

    }

    private void btnCriarContaMouseClicked(ActionEvent e) {
        listener.onClickBtn(OperacaoStrategy.Criar);
    }
}
