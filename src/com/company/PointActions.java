package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PointActions extends JPanel {

    public JLabel coordinates = new JLabel("(0;0)");

    int x = 20, y = 20;
    ArrayList<Points> listOfPoints;
    ArrayList<Points> interpolatedPoints;
    ArrayList<Points> functionsPoints;

    PointActions() {
        listOfPoints = new ArrayList<>();
        functionsPoints = new ArrayList<>();
        interpolatedPoints = new ArrayList<>();
        this.setPreferredSize(new Dimension(1300, 630));
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                coordinates.setText("(" + (x) + ";" + (-y) + ")");
                for (Points point : listOfPoints) {
                    if (((x > point.getX()) && x < (point.getX() + 10)) && ((-y < point.getY()) && (-y > (point.getY() - 10)))) {
                        listOfPoints.remove(point);
                        repaint();
                        return;
                    }
                }

                listOfPoints.add(new Points(x, (-y)));
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    // метод создает на экране точки после нажатия на кнопку Добавить

    public void createPoints(Integer count, String choice) {
        System.out.println("Creating " + count + " points");

        // Добавление точек

        int d = 1300 / count;
        for (int i = 0; i < count; i++) {
            if (choice.equals("x")) {
                listOfPoints.add(new Points((i * d + d / 2), i * d / 2));
            } else if (choice.equals("sin(x)")) {
                listOfPoints.add(new Points(i * d + d / 2, - 300 - (int) (Math.sin(i * d + d / 2) * 200)));
            } else {
                listOfPoints.add(new Points(i * d + d / 2,  -(int) (Math.sqrt(i * d + d / 2) * 17)));
            }
        }

        // Высчитывание координат точек для построения графика исходной функции

        for (int i = 0; i < 1300; i++){
            if (choice.equals("x")){
                functionsPoints.add(new Points((i + d / 2), i/2));
            } else if (choice.equals("sin(x)")){
                functionsPoints.add(new Points(i,  - 300 - (int) (Math.sin(i) * 200)));
            } else {
                functionsPoints.add(new Points(i, -(int) (Math.sqrt(i) * 17)));

            }

        }
        repaint();
    }

    public void paintComponent(Graphics g) {
        printList();
        super.paintComponent(g);

        // График исходной функции

        for (Points i : functionsPoints) {
            g.setColor(Color.orange);
            g.fillOval(i.getX(), -i.getY(), 3, 3);
        }

        // Результат интерполяции

        for (Points i : interpolatedPoints) {
            g.setColor(Color.black);
            g.fillOval(i.getX(), - i.getY(), 10, 10);
        }

        // Исходные точки

        for (Points point : listOfPoints) {
            g.setColor(Color.blue);
            g.fillOval(point.getX()-5, - point.getY()-5, 10, 10);
        }
    }

    public void printList() {
        System.out.println("Printing all points");
        for (Points point : listOfPoints) {
            System.out.println(point.getX() + ";" + point.getY());
        }
    }

    public void clearField() {
        listOfPoints.removeAll(listOfPoints);
        functionsPoints.removeAll(functionsPoints);
        interpolatedPoints.removeAll(interpolatedPoints);
        repaint();
    }

    public void interpolate() {
        System.out.println("Starting interpolation");
        interpolatedPoints.removeAll(interpolatedPoints);
        calculateLagrange();
        repaint();
    }

    public void calculateLagrange() {
        for (double i = 0; i < 1300; i++) {
            double lagrangePol = 0.0;
            double basicsPol;

            for (int j = 0; j < listOfPoints.size(); j++) {
                basicsPol = 1.0;
                for (int k = 0; k < listOfPoints.size(); k++) {
                    if (k == j) continue;
                    basicsPol *= ((i - listOfPoints.get(k).getX()) / (listOfPoints.get(j).getX() - listOfPoints.get(k).getX()));
                }
                lagrangePol += (basicsPol * (listOfPoints.get(j).getY()));
            }
            System.out.println("pixel: " + i + " function: " + lagrangePol);
            interpolatedPoints.add(new Points((int)i, (int)lagrangePol));
        }
    }
}