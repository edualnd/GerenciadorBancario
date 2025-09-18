package main.bancaria.view.panels.funcionalidades;



import main.bancaria.model.ContaCorrente;
import main.bancaria.service.ContaService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CalcularPanel extends JPanel{

    private final List<ContaCorrente> dados;
    private JComboBox<String> contaEscolhida = new JComboBox<>();
    private final JLabel tarifa = new JLabel("");


    public CalcularPanel(List<ContaCorrente> dados) {
        this.dados = dados;
        initComponents();
    }

    private void initComponents(){

        JLabel titulo = new JLabel();
        titulo.setText("Calcular Tarifa");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        setPreferredSize(new Dimension(500,400));
        setForeground(Color.BLACK);
        setLayout(new GridBagLayout());


        contaEscolhida.setPreferredSize(new Dimension(200,20));
        contaEscolhida = Util.preencherComboBox(dados);

        JPanel res = new JPanel();
        res.setPreferredSize(new Dimension(250,180));
        res.setMinimumSize(new Dimension(250,180));
        res.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(10,10, 10, 10);
        res.add(contaEscolhida,gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        res.add(tarifa,gbc);
        contaEscolhida.addActionListener(e ->{
            if (contaEscolhida.getSelectedIndex() != 0){
                onChangeContaEscolhida();
            }
        });



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
        ContaService cs = new ContaService();
        ContaCorrente c =
                dados.stream().filter(cc -> cc.getNumero() == num).toList().getFirst();
        tarifa.setText("Tarifa: R$"+ cs.calcularTarifa(c));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,10, 10, 10);
        gbc.gridy = 1;
        gbc.gridx = 0;

        Container res = tarifa.getParent();
        res.remove(tarifa);
        res.add(tarifa,gbc);
        res.revalidate();
        res.repaint();
    }


}
