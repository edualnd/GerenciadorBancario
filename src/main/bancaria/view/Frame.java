/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.bancaria.view;





import main.bancaria.view.panels.TelaPanel;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author cg3034011
 */
public class Frame extends JFrame{
    private final JPanel painel = new JPanel(new GridBagLayout());
    private final JButton btnComecar = new JButton();
    private final JLabel titulo = new JLabel();
    
    public Frame(){
        initComponents();
    }
    static void main() {
        EventQueue.invokeLater(() -> new Frame().setVisible(true));
    }
    private void initComponents(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new Font("Arial", Font.PLAIN, 24));
        setSize(800,600);
        

        GridBagConstraints gridBagCons = new GridBagConstraints();
        gridBagCons.gridx = 0;
        gridBagCons.anchor = GridBagConstraints.CENTER;
        gridBagCons.insets = new Insets(10, 0, 10, 0);


        btnComecar.setText("Começar");
        btnComecar.setPreferredSize(new Dimension(200,40));
        btnComecar.setBackground(new Color(255,255,255));
        btnComecar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnComecar.addActionListener(this::btnComecarMouseClicked);
        gridBagCons.gridy = 1;
        painel.add(btnComecar, gridBagCons);

        titulo.setText("Gerenciamento Bancário");
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setForeground(new Color(232,229,229));
        gridBagCons.gridy = 0;
        painel.add(titulo,gridBagCons);

        painel.setVisible(true);
        painel.setBackground(new Color(89,89,89));

        getContentPane().add(painel);

    }

    private void btnComecarMouseClicked(ActionEvent evt) {
        JPanel telaPanel = new TelaPanel();
        setLayout(new BorderLayout());
        getContentPane().removeAll();
        getContentPane().add(telaPanel, BorderLayout.CENTER);
        revalidate();
        repaint();

    }

}
