package org.injustice.revision.ui;

import org.injustice.revision.Main;
import org.injustice.revision.data.History;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * @author Azmat Habibullah
 */
public class MainFrame extends JFrame{

    private Main m;
    private History h;
    private JLabel questionLabel;
    private JLabel verdictLabel;
    private JTextField answerField;
    private JButton checkButton;
    private JLabel questionNumber;
    private JLabel attemptLabel;
    private int attempt = 1;

    public MainFrame(History h, Main m) {
        System.out.println(h);
        this.h = h;
        this.m = m;
        initComponents();
    }

    private void initComponents() {
        questionLabel = new JLabel("", SwingConstants.CENTER);
        answerField = new JTextField();
        checkButton = new JButton();
        verdictLabel = new JLabel("", SwingConstants.CENTER);
        questionNumber = new JLabel("");
        attemptLabel = new JLabel("" + attempt);

        setTitle("History Test");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        setQuestion(h);
        checkButton.setText("Check");
        verdictLabel.setText("");

        questionLabel.setBounds(5, 5, 345, 25);
        answerField.setBounds(148, 35, 55, 25);
        checkButton.setBounds(138, 65, 75, 25);
        verdictLabel.setBounds(5, 90, 345, 25);
        questionNumber.setBounds(5, 90, 50, 25);
        attemptLabel.setBounds(320, 90, 15, 25);

        answerField.addActionListener(ae -> checkSolution());
        checkButton.addActionListener(ae -> checkSolution());

        contentPane.add(questionLabel);
        contentPane.add(answerField);
        contentPane.add(checkButton);
        contentPane.add(verdictLabel);
        contentPane.add(questionNumber);
        contentPane.add(attemptLabel);

        setResizable(false);
        setLocationRelativeTo(null);
        pack();
        setSize(350, 140);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
  //          UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setQuestion(History h) {
        this.h = h;
        String question = "What year was the " + h + "?";
        questionLabel.setText(question);
        answerField.setText("");
        m.list.add(h);
        questionNumber.setText("" + m.generated.size() + "/" + History.values().length);
    }

    private void checkSolution() {
        answerField.requestFocus();
        if (answerField.getText().equals(h.answer())) {
            verdictLabel.setText("Correct!");
            m.setCorrect(true, attempt);
            attemptLabel.setText((attempt = 1) + "");
        } else {
            verdictLabel.setText("Try again!");
            m.setCorrect(false, attempt);
            attempt++;
            attemptLabel.setText(attempt + "");
        }
    }
}
