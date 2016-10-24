package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

public class PointActions extends JPanel {

    public JLabel coordinates = new JLabel("(0;0)");

    int x = 20, y = 20;
    ArrayList<Points> listOfPoints;

    PointActions(){
        listOfPoints = new ArrayList<>();
        this.setPreferredSize(new Dimension(1300, 630));
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                coordinates.setText("(" + (x - 650) + ";" + (315 - y) + ")" );
                for(Points point: listOfPoints){
                    if ((x > point.getX() && x < (point.getX()+15)) && (y > point.getY() && y < (point.getY()+15))){
                        listOfPoints.remove(point);
                        repaint();
                        return;
                    }
                }

                listOfPoints.add(new Points(x, y));
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

    public void createPoints(Integer count, String choice){
        System.out.println("Creating " + count + " points");
        for (int i = 0; i < count; i++){
            int d = 1300/count;
            if(choice.equals("x")) {
                listOfPoints.add(new Points((i * d + d / 2), 600 - i * d / 2));
            } else if (choice.equals("sin(x)")){
                listOfPoints.add(new Points(i * d + d/2, (400 - (int)(Math.sin(i * d + d/2)*200))));
            } else {
                listOfPoints.add(new Points(i * d + d/2, (630 - (int)(Math.sqrt(i * d + d/2)*17))));
            }
        }
        printList();
        repaint();
    }

    public void paintComponent(Graphics g){
        printList();
        super.paintComponent(g);
        for (Points point : listOfPoints){
            g.setColor(Color.blue);
            g.fillOval(point.getX(), point.getY(), 10, 10);
            System.out.println("printing point with x = " + point.getX());
        }
    }

    public void printList(){
        System.out.println("Printing all points");
        for(Points point: listOfPoints){
            System.out.println(point.getX()+ ";" + point.getY());
        }
    }

    public void clearField(){
        listOfPoints.removeAll(listOfPoints);
        repaint();
    }

}
