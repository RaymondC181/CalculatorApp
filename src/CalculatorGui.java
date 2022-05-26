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
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

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

        JLabel welcomeLabel = new JLabel("Calculator App");  //<--
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.black);

        ImageIcon image = new ImageIcon("src/calc.jpg"); //<--
        Image imageData = image.getImage();
        Image scaledImage = imageData.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(scaledImage);
        JLabel pictureLabel = new JLabel(image);


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

        JPanel welcomePanel = new JPanel();  //<--
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(pictureLabel);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 5);  //<--
        resultField.setBorder(border);
        

        frame.add(resultField,BorderLayout.NORTH);
        frame.add(entryPanel, BorderLayout.SOUTH);
        frame.add(numPanel, BorderLayout.CENTER);
        frame.add(welcomePanel, BorderLayout.SOUTH); //<--

        layout = new GridLayout(4, 4);
        frame.setLayout(layout);

        frame.setSize(200, 550);
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

        if(text.equals("=")) {
            String numExpression = num.replace("=", "");
            String str = numExpression.substring(numExpression.length()-1, numExpression.length());
            if (str.equals("*")||str.equals("/")||str.equals("+")||str.equals("-"))
            {
                num = "ERROR";
                resultField.setText(num);
                num = "";
            }
            else
            {
                num = "";
                resultField.setText(num);
                //evaluate the math expression in string form and set the result field to the result

                resultField.setText("Answer: " + eval(numExpression));
            }
            System.out.println(numExpression);
        }

    }


    //--------------------------------------------------------------------------------------------------
    //https://stackoverflow.com/questions/3422673/how-to-evaluate-a-math-expression-given-in-string-form

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName `(` expression `)` | functionName factor
            //        | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
    //--------------------------------------------------------------------------------------------------


}