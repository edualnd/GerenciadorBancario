package main.bancaria.gui;


import main.bancaria.model.ContaCorrente;

import javax.swing.*;
import java.util.List;

public class Util {
    public static JComboBox<String>  preencherComboBox(List<ContaCorrente> dados){
        JComboBox<String> j = new JComboBox<>();
        j.removeAllItems();
        j.addItem("Escolha uma conta");
        for (ContaCorrente c : dados) {
            j.addItem(c.getNumero()+ " - " + c.getTitular());
        }
        return j;
    }
    public static void atualizarComboBox(List<ContaCorrente> dados, JComboBox<String> j){
        j.removeAllItems();
        j.addItem("Escolha uma conta");
        for (ContaCorrente c : dados) {
            j.addItem(""+c.getNumero() + " - " + c.getTitular());
        }

    }

}
