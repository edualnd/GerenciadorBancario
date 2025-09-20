package main.bancaria.gui;

import main.bancaria.gui.panels.*;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
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
        add(new EscolherConta());

    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}