package com.examsystem;
import javax.swing.*;
import java.io.*;

public class TakeExam {
    Questions question;
    JFrame frame;

    public TakeExam(JFrame frame) throws IOException, ClassNotFoundException {
        this.frame = frame;
        loadQuestion();
    }

    // Load the question from the file
    private void loadQuestion() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("question.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        question = (Questions) ois.readObject();
        ois.close();
    }

    // Display the question and options
    public void takeExamField() {
        JPanel examPanel = new JPanel();
        examPanel.setLayout(new BoxLayout(examPanel, BoxLayout.Y_AXIS));

        JLabel questionLabel = new JLabel(question.question);
        JRadioButton optionA = new JRadioButton("A) " + question.a);
        JRadioButton optionB = new JRadioButton("B) " + question.b);
        JRadioButton optionC = new JRadioButton("C) " + question.c);
        JRadioButton optionD = new JRadioButton("D) " + question.d);

        ButtonGroup group = new ButtonGroup();
        group.add(optionA);
        group.add(optionB);
        group.add(optionC);
        group.add(optionD);

        JButton submitButton = new JButton("Submit Answer");
        examPanel.add(questionLabel);
        examPanel.add(optionA);
        examPanel.add(optionB);
        examPanel.add(optionC);
        examPanel.add(optionD);
        examPanel.add(submitButton);

        submitButton.addActionListener(e -> {
            String selectedAnswer = null;
            if (optionA.isSelected()) selectedAnswer = question.a;
            else if (optionB.isSelected()) selectedAnswer = question.b;
            else if (optionC.isSelected()) selectedAnswer = question.c;
            else if (optionD.isSelected()) selectedAnswer = question.d;

            // Check if the selected answer is correct
            if (selectedAnswer != null) {
                if (selectedAnswer.equals(question.correctAnswer)) {
                    JOptionPane.showMessageDialog(frame, "Correct Answer!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect. The correct answer is: " + question.correctAnswer);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select an answer.");
            }
        });

        frame.setContentPane(examPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
