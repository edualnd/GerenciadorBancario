package main.bancaria.gui;

import main.bancaria.gui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Inicio inicio;

    public MainFrame() {
        setTitle("Sistema Bancário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        initializeComponents();

        setVisible(true);
    }

    private void initializeComponents() {
        inicio = new Inicio();

        add(new AnaliseContas());

        configActions();
    }

    private void configActions() {
        JButton escolherContaBtn = inicio.getOperacoesBtn();
        JButton analiseContasBtn = inicio.getAnalisarBtn();

        escolherContaBtn.addActionListener(e-> mudarPagina1());
        analiseContasBtn.addActionListener(e-> mudarPagina());
    }

    private void mudarPagina1() {
        remove(inicio);
        add(new EscolherConta());
        repaint();
        revalidate();
    }

    private void mudarPagina() {
        remove(inicio);
        add(new AnaliseContas());
        repaint();
        revalidate();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();

        });
    }
}