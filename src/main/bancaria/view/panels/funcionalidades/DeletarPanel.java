package main.bancaria.view.panels.funcionalidades;



import main.bancaria.model.ContaCorrente;
import main.bancaria.service.ContaService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DeletarPanel extends JPanel{

    private final List<ContaCorrente> dados;
    private final JButton btnEnviar = new JButton();
    private final JButton btnConfirmar = new JButton("Deletar");
    private JComboBox<String> contaEscolhida = new JComboBox<>();

    public DeletarPanel(List<ContaCorrente> dados) {
        this.dados = dados;
        initComponents();
    }

    private void initComponents(){
        JLabel titulo = new JLabel();
        titulo.setText("Deletar conta");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        setPreferredSize(new Dimension(500,400));
        setForeground(Color.BLACK);
        setLayout(new GridBagLayout());

        JPanel res = new JPanel();
        res.setPreferredSize(new Dimension(250,150));
        res.setMinimumSize(new Dimension(250,150));
        res.setLayout(new GridBagLayout());

        contaEscolhida = Util.preencherComboBox(dados);

        btnEnviar.setText("Certeza?");
        btnEnviar.addActionListener(e -> onClickBtnEnviar());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,0,0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        gbc.insets = new Insets(15,10,5,10);
        gbc.gridx = 0;
        res.add(contaEscolhida,gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        res.add(btnConfirmar,gbc);
        btnConfirmar.addActionListener(e ->{
            if (contaEscolhida.getSelectedIndex() != 0){
                res.remove(1);
                res.add(btnEnviar, gbc);
                btnEnviar.setBackground(new Color(220, 79, 79));
                btnEnviar.setForeground(Color.white);
                res.revalidate();
                res.repaint();
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

    private void onClickBtnEnviar() {
        int num = Integer.parseInt(contaEscolhida.getSelectedItem().toString().split(
                "-")[0].trim());
        ContaService cs = new ContaService();
        if(cs.deletarConta(num)){
            dados.removeIf(c -> c.getNumero() == num);
            Util.atualizarComboBox(dados, contaEscolhida);
            contaEscolhida.setSelectedIndex(0);

            Container res = btnEnviar.getParent();
            res.remove(btnEnviar);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(15,10,5,10);

            gbc.gridy = 1;
            gbc.gridx = 0;
            res.add(btnConfirmar,gbc);
            res.revalidate();
            res.repaint();
        }
    }
}
