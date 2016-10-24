package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class MainWindow {

    // Инициализация графических компонентов
    public JFrame mainWindow;
    public ArrayList<JButton> listOfButtons;
    public JLabel label;
    public JLabel chooseFunction;
    public JLabel pointsCountMsg;
    public JLabel pointsCount;
    public JPanel menu;
    public PointActions field;
    public JPanel graph;
    public JSlider slider;
    public JList<String> functionList;

    MainWindow() {
        buildGUI();
    }

    // метод, добавляющий все кнопки из коллекции в mainWindow
    public void addButton(JButton button){
        menu.add(button);
    }

    // установка параметров и расположение элементов в mainWindow
    private void buildGUI(){
        mainWindow = new JFrame("Lagrange Interpolation");
        mainWindow.setLayout(new FlowLayout());
        mainWindow.setSize(1300, 715);
        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание меню
        menu = new JPanel();
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        menu.setPreferredSize(new Dimension(1300, 70));

        // Создание панели для графика
        graph = new JPanel();
        graph.setPreferredSize(new Dimension(1300, 630));


        Buttons buttons = new Buttons();

        field = buttons.getField();

        // Инициализация label

        label = buttons.getLabel();
        chooseFunction = new JLabel("Выберите функцию для выбора точек");
        pointsCount = new JLabel("..");
        pointsCountMsg = new JLabel("Количество точек:");
        Font font = new Font("Colibri", Font.PLAIN, 17);

        listOfButtons = buttons.getListOfButtons();

        // Listener для слайдера

        slider = new JSlider();
        slider.setMaximum(15);
        slider.setMinimum(2);
        slider.setValue(2);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Integer value = slider.getValue();
                buttons.count = value;
                pointsCount.setText(value.toString());
            }
        });

        // Создание листа функций
        functionList = buttons.getList();

        // Добавление компонентов в панели

        menu.add(chooseFunction);
        menu.add(functionList);
        menu.add(pointsCountMsg);
        menu.add(slider);
        menu.add(pointsCount);

        graph.add(label);

        field.add(field.coordinates);

        mainWindow.add(field);
        mainWindow.add(menu);

        /*
         * Эта функциональная часть печатает в stdout список кнопок,
         * пришедших из коллекции listOfButtons и вызывает метод addButton
         * для добавления в mainWindow всех кнопок
         */

        listOfButtons.stream().map(button -> {
            System.out.println(button.getActionCommand() + " button have been added to main window");
            button.setFont(font);
            return button;
        })
        .forEach(button -> addButton(button));

        mainWindow.setVisible(true);
    }
}
