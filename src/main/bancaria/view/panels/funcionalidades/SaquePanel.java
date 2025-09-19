package main.bancaria.view.panels.funcionalidades;



import main.bancaria.exception.SaldoInsuficienteException;
import main.bancaria.model.ContaCorrente;
import main.bancaria.service.ContaService;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Optional;

public class SaquePanel extends JPanel{

    private final List<ContaCorrente> dados;
    private JComboBox<String> contaEscolhida = new JComboBox<>();
    private final NumberFormat format = NumberFormat.getInstance();
    private final JFormattedTextField valor = new JFormattedTextField(format);
    private final JLabel saldoAtual = new JLabel("");
    private final JButton btnEnviar = new JButton("main.bancaria.gui.panels.Sacar");

    public SaquePanel(List<ContaCorrente> dados) {
        this.dados = dados;
        initComponents();
    }

    private void initComponents(){
        format.setMaximumFractionDigits(2);

        JLabel labelValor = new JLabel();
        labelValor.setText("Valor: ");

        valor.setPreferredSize(new Dimension(100, 25));
        valor.setValue(0);
        valor.setColumns(10);

        JPanel containerValor = new JPanel();
        containerValor.setOpaque(false);
        containerValor.add(labelValor);
        containerValor.add(valor);

        JLabel titulo = new JLabel();
        titulo.setText("main.bancaria.gui.panels.Sacar");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        setPreferredSize(new Dimension(500,400));
        setForeground(Color.BLACK);
        setLayout(new GridBagLayout());

        JPanel res = new JPanel();
        res.setPreferredSize(new Dimension(250,200));
        res.setMinimumSize(new Dimension(250,200));
        res.setLayout(new GridBagLayout());



        contaEscolhida.setPreferredSize(new Dimension(200,20));
        contaEscolhida = Util.preencherComboBox(dados);

        btnEnviar.addActionListener(e -> {
            if (contaEscolhida.getSelectedIndex() != 0){
                onClickBtnEnviar();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(10,10, 10, 10);
        res.add(contaEscolhida,gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        res.add(saldoAtual,gbc);
        contaEscolhida.addActionListener(e ->{
            if (contaEscolhida.getSelectedIndex() != 0){
               onChangeContaEscolhida();
            }
        });


        gbc.gridy = 2;
        gbc.gridx = 0;
        res.add(containerValor,gbc);
        gbc.gridy = 3;
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

    private void onChangeContaEscolhida() {
        int num = Integer.parseInt(contaEscolhida.getSelectedItem().toString().split(
                "-")[0].trim());
        ContaCorrente c =
                dados.stream().filter(cc -> cc.getNumero() == num).toList().getFirst();
        saldoAtual.setText("Saldo atual: R$"+ c.getSaldo());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,10, 10, 10);
        gbc.gridy = 1;
        gbc.gridx = 0;

        Container res = saldoAtual.getParent();
        res.remove(saldoAtual);
        res.add(saldoAtual,gbc);
        res.revalidate();
        res.repaint();
    }

    private void onClickBtnEnviar() {
        int num = Integer.parseInt(contaEscolhida.getSelectedItem().toString().split(
                "-")[0].trim());
        Optional<ContaCorrente> c =
                dados.stream().filter(cc -> cc.getNumero() == num).findFirst();

        double valor = ((Number) this.valor.getValue()).doubleValue();
        ContaService cs = new ContaService();
        boolean ret = false;
        try{
            ret = cs.solicitarSaque(c.get(), valor);
        } catch (SaldoInsuficienteException e) {
            System.out.println(e.getMessage() + "  Tente novamente");
            System.out.println("Saldo atual: " + c.get().getSaldo());
        }
        if (ret) {

            saldoAtual.setText("Saldo atual: R$"+ c.get().getSaldo());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10,10, 10, 10);
            gbc.gridy = 1;
            gbc.gridx = 0;

            Container res = saldoAtual.getParent();
            res.remove(saldoAtual);
            res.add(saldoAtual,gbc);
            revalidate();
            repaint();
            System.out.println("Saque feito com sucesso");
        }
        else System.out.println("Erro ao depositar");
        this.valor.setValue(0);

    }
}
