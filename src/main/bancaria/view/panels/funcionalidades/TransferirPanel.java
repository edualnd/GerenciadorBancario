package main.bancaria.view.panels.funcionalidades;



import main.bancaria.model.ContaCorrente;
import main.bancaria.service.ContaService;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;

public class TransferirPanel extends JPanel{

    private final List<ContaCorrente> dados;
    private JComboBox<String> contaEscolhida = new JComboBox<>();
    private JComboBox<String> contaDestinataria = new JComboBox<>();
    private JPanel containerTransferirDe = new JPanel();
    private JPanel containerTransferirPara = new JPanel();
    private NumberFormat format = NumberFormat.getInstance();
    private final JFormattedTextField valor = new JFormattedTextField(format);
    private final JButton btnEnviar = new JButton();

    public TransferirPanel(List<ContaCorrente> dados) {
        this.dados = dados;
        initComponents();
    }

    private void initComponents(){
        JLabel titulo = new JLabel();
        titulo.setText("Transferir");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        setPreferredSize(new Dimension(500,400));
        setForeground(Color.BLACK);
        setLayout(new GridBagLayout());

        JPanel res = new JPanel();
        res.setPreferredSize(new Dimension(250,250));
        res.setMinimumSize(new Dimension(250,250));
        res.setLayout(new GridBagLayout());

        JLabel transferirDeLbl = new JLabel();
        JLabel transferirParaLbl = new JLabel();

        transferirParaLbl.setText("Transferir para: ");
        transferirDeLbl.setText("Transferir de: ");

        btnEnviar.setText("Transferir");

        contaEscolhida.setPreferredSize(new Dimension(200,20));
        contaEscolhida = Util.preencherComboBox(dados);

        contaDestinataria.setPreferredSize(new Dimension(200,20));
        contaDestinataria = Util.preencherComboBox(dados);

        contaEscolhida.addActionListener(e->

                onChangeContaEscolhida()

        );
        contaDestinataria.addActionListener(e->
                onChangeContaDestinataria()
          );


        containerTransferirPara.setPreferredSize(new Dimension(200,50));
        containerTransferirDe.setPreferredSize(new Dimension(200,50));

        containerTransferirDe.add(transferirDeLbl);
        containerTransferirPara.add(transferirParaLbl);

        containerTransferirDe.add(contaEscolhida);
        containerTransferirPara.add(contaDestinataria);

        format = NumberFormat.getInstance();
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

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,0,0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        gbc.insets = new Insets(15,10,5,10);
        gbc.gridx = 0;
        res.add(containerTransferirDe,gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        res.add(containerTransferirPara,gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        res.add(containerValor,gbc);
        gbc.gridy = 3;
        gbc.gridx = 0;
        res.add(btnEnviar,gbc);

        btnEnviar.addActionListener(e-> onClickBtnEnviar());

        gbc.insets = new Insets(0,0,0,10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;


        gbc.gridy = 0;
        add(titulo, gbc);
        gbc.gridy = 1;
        add(res, gbc);


    }

    private void onClickBtnEnviar() {
        int nSacar = Integer.parseInt(contaEscolhida.getSelectedItem().toString().split(
                "-")[0].trim());
        int nDepositar = Integer.parseInt(contaDestinataria.getSelectedItem().toString().split(
                "-")[0].trim());
        ContaCorrente cSacar =
                dados.stream().filter(cc -> cc.getNumero() == nSacar).findFirst().orElse(null);
        ContaCorrente cDeposito =
                dados.stream().filter(cc -> cc.getNumero() == nDepositar).findFirst().orElse(null);
        double valor = ((Number) this.valor.getValue()).doubleValue();


        ContaService cs = new ContaService();
        if( cSacar != null && cDeposito !=null){
            if (cs.solicitarTransferencia(cSacar, cDeposito, valor)){
                JOptionPane.showMessageDialog(null,"Transferencia feita com sucesso");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao transferir");
            }
        }
        this.valor.setValue(0);
        contaDestinataria.setSelectedIndex(0);
        contaEscolhida.setSelectedIndex(0);

    }

    private void onChangeContaDestinataria() {

        int selectedIndex = contaDestinataria.getSelectedIndex();

        if (contaEscolhida.getSelectedIndex() <= 0) {
            Util.atualizarComboBox(dados, contaEscolhida);
        }
        if (selectedIndex > 0 && contaEscolhida.getSelectedIndex() <= 0) contaEscolhida.removeItemAt(selectedIndex);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 10, 5, 10);
        gbc.gridy = 0;
        gbc.gridx = 0;

        Container res = containerTransferirDe.getParent();
        res.remove(containerTransferirDe);
        res.add(containerTransferirDe, gbc);
        res.revalidate();
        res.repaint();
    }

    private void onChangeContaEscolhida() {

        int selectedIndex = contaEscolhida.getSelectedIndex();

        if (contaDestinataria.getSelectedIndex() <= 0) {
            Util.atualizarComboBox(dados, contaDestinataria);
        }
        if (selectedIndex > 0 && contaDestinataria.getSelectedIndex() <= 0) contaDestinataria.removeItemAt(selectedIndex);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 10, 5, 10);
        gbc.gridy = 1;
        gbc.gridx = 0;

        Container res = containerTransferirPara.getParent();
        res.remove(containerTransferirPara);
        res.add(containerTransferirPara, gbc);
        res.revalidate();
        res.repaint();

    }
}
