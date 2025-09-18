package main.bancaria.view.panels.funcionalidades;


import main.bancaria.model.ContaCorrente;

import javax.swing.*;
import java.util.List;

public class Util {
    public static JComboBox<String>  preencherComboBox(List<ContaCorrente> dados){
        JComboBox<String> j = new JComboBox<>();
        int limite = 15;
        j.removeAllItems();
        j.addItem("Escolha uma conta");
        for (ContaCorrente c : dados) {
            j.addItem(
                    c.getNumero() + " - " +
                            c.getTitular().substring(0,
                                    Math.min(c.getTitular().length(), limite)));
        }
        return j;
    }
    public static void atualizarComboBox(List<ContaCorrente> dados, JComboBox<String> j){
        int limite = 15;
        j.removeAllItems();
        j.addItem("Escolha uma conta");
        for (ContaCorrente c : dados) {
            j.addItem(
                    c.getNumero() + " - " +
                            c.getTitular().substring(0,
                                    Math.min(c.getTitular().length(), limite)));
        }

    }

}
