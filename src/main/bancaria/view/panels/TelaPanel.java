package main.bancaria.view.panels;

import javax.swing.*;
import java.awt.*;

public class TelaPanel extends JPanel {

    private final BtnsPanel btns = new BtnsPanel();
    private final ListarContasPanel lista = new ListarContasPanel();

    public TelaPanel(){
        initComponents();
    }



    private void initComponents() {

        setPreferredSize(new Dimension(800,600));
        setBackground(new Color(89,89,89));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0,10,5,10));

        add(btns);
        add(Box.createRigidArea(new Dimension(0,5)));
        add(lista);

        configListeners();
    }
    private void configListeners(){
        this.btns.setListener(lista::onClickBtn);
    }


}
