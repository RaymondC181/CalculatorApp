import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorGui implements ActionListener
{
    private JTextField resultField;
    private String num = "";
    GridLayout layout;

    public CalculatorGui (){
        resultField = new JTextField();
        setUp();
    }

    public void setUp()
    {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultField.setEditable(false);


        frame.add(resultField);
        JButton addButton = new JButton("+");
        JButton minusButton = new JButton("-");
        JButton divideButton = new JButton("/");
        JButton mutiplyButton = new JButton("*");
        JButton enterButton = new JButton("=");
        JButton clearButton = new JButton("C");
        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        JButton b7 = new JButton("7");
        JButton b8 = new JButton("8");
        JButton b9 = new JButton("9");
        JButton b0 = new JButton("0");


        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b0.addActionListener(this);
        clearButton.addActionListener(this);
        addButton.addActionListener(this);
        minusButton.addActionListener(this);
        divideButton.addActionListener(this);
        mutiplyButton.addActionListener(this);
        enterButton.addActionListener(this);

        JPanel numPanel = new JPanel();
        numPanel.add(b1);
        numPanel.add(b2);
        numPanel.add(b3);
        numPanel.add(b4);
        numPanel.add(b5);
        numPanel.add(b6);
        numPanel.add(b7);
        numPanel.add(b8);
        numPanel.add(b9);
        numPanel.add(b0);

        JPanel entryPanel = new JPanel();
        entryPanel.add(addButton);
        entryPanel.add(minusButton);
        entryPanel.add(divideButton);
        entryPanel.add(mutiplyButton);
        entryPanel.add(enterButton);
        entryPanel.add(clearButton);

        frame.add(resultField,BorderLayout.NORTH);
        frame.add(entryPanel, BorderLayout.SOUTH);
        frame.add(numPanel, BorderLayout.CENTER);


        layout = new GridLayout(4, 4);
        frame.setLayout(layout);

        frame.setSize(200, 450);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) (e.getSource());
        String text = button.getText();

        if ((text.equals("1"))|| (text.equals("2"))|| (text.equals("3"))|| (text.equals("4"))||
          (text.equals("5"))|| (text.equals("6"))||(text.equals("7"))|| (text.equals("8"))||
        (text.equals("9"))||(text.equals("0")));
        {
            num = num + text;
            resultField.setText(num);
        }

        if(text.equals("C"))
        {
            num = "";
            resultField.setText(num);
        }

        if(text.equals("="))
        {
            resultField.setText("");
            System.out.println(num.replace("=", ""));
            String numExpression = num.replace("=", "");

        }

    }
}