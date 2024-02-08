import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

import static java.awt.SystemColor.info;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[11];
    JButton addButton,subButton,mulButton,divButton,decButton,equButton,delButton,clrButton, perceButton, sqrButton;
    JPanel panel;
    Font myFont = new Font("Modern No. 20",Font.BOLD,30);
    BigDecimal num1 = BigDecimal.ZERO, num2= BigDecimal.ZERO;
    char operator = ' ';
    boolean startNew = true;


    Calculator(){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


        frame = new JFrame("calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,550);
        frame.setLayout(null);
        frame.setResizable(false);
        ImageIcon icon = new ImageIcon("icons8-calculator-48.png");
        frame.setIconImage(icon.getImage());

        textField = new JTextField();
        textField.setBounds(50,25,300,50);
        textField.setFont(myFont);
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setBorder(BorderFactory.createLoweredBevelBorder());
        textField.setBackground(new Color(212, 212, 210));

        addButton = new JButton("+");
        addButton.setBackground(new Color(255, 149, 0));
        subButton = new JButton("-");
        subButton.setBackground(new Color(255, 149, 0));
        mulButton = new JButton("x");
        mulButton.setBackground(new Color(255, 149, 0));
        divButton = new JButton("\u00F7");
        divButton.setBackground(new Color(255, 149, 0));
        decButton = new JButton(".");
        decButton.setBackground(new Color(212, 212, 210));
        equButton = new JButton("=");
        equButton.setBackground(new Color(212, 212, 210));
        perceButton = new JButton("%");
        perceButton.setBackground(new Color(212, 212, 210));
        sqrButton = new JButton("\u221A");
        sqrButton.setBackground(new Color(212, 212, 210));
        delButton = new JButton("DEL");
        delButton.setBackground(new Color(212, 212, 210));
        clrButton = new JButton("C");
        clrButton.setBackground(new Color(212, 212, 210));

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = perceButton;
        functionButtons[9] = sqrButton;



        for(int i =0; i<10; i++){
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);

        }

        delButton.setFont(new Font("Modern No. 20",Font.BOLD,15));

        for(int i =0; i<10; i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(new Color(80, 80, 80));
            numberButtons[i].setForeground(Color.white);
        }


        panel = new JPanel();
        panel.setBounds(50,100,300,350);
        panel.setLayout(new GridLayout(5,4,10,10));

        panel.add(clrButton);
        panel.add(sqrButton);
        panel.add(perceButton);
        panel.add(delButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(divButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(mulButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);

        panel.add(subButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(addButton);

        frame.add(panel);
        frame.add(textField);
        frame.setVisible(true);


    }
    public static void main(String[] args) {
        new Calculator();
    }

    @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 10; i++) {
                if (e.getSource() == numberButtons[i]) {
                    if (startNew) {
                        textField.setText("");
                        startNew = false;
                    }
                    textField.setText(textField.getText().concat(String.valueOf(i)));
                }
            }

            if (e.getSource() == decButton && !textField.getText().contains(".")) {
                textField.setText(textField.getText().concat("."));
            }

            if (e.getSource() == clrButton) {
                textField.setText("");
                num1 = BigDecimal.ZERO;
                operator = ' ';
            }

            if (e.getSource() == addButton || e.getSource() == subButton ||
                    e.getSource() == mulButton || e.getSource() == divButton) {

                if (!startNew) {
                    BigDecimal num2 = new BigDecimal(textField.getText());
                    calculateAndDisplay(num1, num2, operator);
                }

                operator = e.getSource() == addButton ? '+' : e.getSource() == subButton ? '-' :
                        e.getSource() == mulButton ? '*' : '/';
                startNew = true;
            }

            if (e.getSource() == equButton) {
                BigDecimal num2 = new BigDecimal(textField.getText());
                calculateAndDisplay(num1, num2, operator);
                operator = ' ';
                startNew = true;
            }

        if (e.getSource() == perceButton){
            BigDecimal num3 = new BigDecimal(textField.getText());
            num2 = num3.divide(BigDecimal.valueOf(100),MathContext.DECIMAL32);
            textField.setText(num2.toEngineeringString());

        }
        if (e.getSource() == sqrButton){
            BigDecimal num3 = new BigDecimal (textField.getText());
            num2 = num3.sqrt(MathContext.DECIMAL128);
            textField.setText(num2.toPlainString());
        }

        if (e.getSource() == clrButton){
            textField.setText("");
            num1 = BigDecimal.ZERO;
            num2 = BigDecimal.ZERO;
        }
        if (e.getSource() == delButton){
            if (!textField.getText().isEmpty()){
                textField.setText(textField.getText().substring(0,textField.getText().length()-1));
                num1 = new BigDecimal(textField.getText());
            }
        }
    }

        protected BigDecimal calculateAndDisplay(BigDecimal num1, BigDecimal num2, char operator) {
            switch (operator) {
                case '+' -> this.num1 = num1.add(num2);
                case '-' -> this.num1 = num1.subtract(num2);
                case '*' -> this.num1 = num1.multiply(num2);
                case '/' -> {
                    if (num2.compareTo(BigDecimal.ZERO) != 0) {
                        this.num1 = num1.divide(num2, 10, RoundingMode.HALF_UP);
                    } else {
                        textField.setText("Error");
                        return num1;
                    }
                }
                case ' ' -> {
                    this.num1 = num2;
                    return num1;
                }
            }
            textField.setText(this.num1.stripTrailingZeros().toPlainString());
            return num1;
        }

}