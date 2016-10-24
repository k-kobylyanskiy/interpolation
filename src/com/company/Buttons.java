package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Buttons {

    PointActions field = new PointActions();
    Integer count;


    public PointActions getField(){
        return field;
    }

    // Объявление элементов управления

    JButton add;
    JButton choose;
    JButton interpolation;
    JLabel label = new JLabel("1");
    JList<String> functionsList;
    private ArrayList<JButton> buttons = new ArrayList<>();

    // Массив строк исходных функций для интерполяции

    String functions[] = {
      "sin(x)", "e^x", "x^2"
    };

    Buttons() {

        // Создание и инициализация кнопок

        add = new JButton("Добавить точки");
        add.setActionCommand("add");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ае){
            field.createPoints(count, "s");
            }
        });

        buttons.add(add);

        choose = new JButton("Очистить поле");
        choose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ае){
                label.setText("Очистка");
                field.clearField();
            }
        });

        buttons.add(choose);

        interpolation = new JButton("Интерполировать");
        interpolation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ае){
                label.setText("Интерполяция");
            }
        });

        buttons.add(interpolation);

        System.out.println("All buttons have been initialized");

        // Инициализация листа

        functionsList = new JList<>(functions);


    }

    // Методы

    public JList getList(){
        return functionsList;
    }

    public ArrayList<JButton> getListOfButtons(){
        return buttons;
    }

    public JLabel getLabel(){
        return label;
    }
}
