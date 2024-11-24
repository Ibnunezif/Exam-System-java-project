package com.examsystem;
import javax.swing.*;
import java.io.*;

public class ExamDataSaver {
    JFrame frame;
    JButton saveButton;
    public ExamDataSaver(JFrame frame) {
        this.frame = frame;
    }

    public void examTypingArea() throws IOException {
        JPanel examAreaHolder = new JPanel();
        examAreaHolder.setLayout(new BoxLayout(examAreaHolder, BoxLayout.Y_AXIS));

        // Input fields for question and options
        ExamArea questionText = new ExamArea("Question");
        ExamArea correctAnswerText = new ExamArea("Correct Answer");
        ExamArea optionA = new ExamArea("Option A");
        ExamArea optionB = new ExamArea("Option B");
        ExamArea optionC = new ExamArea("Option C");
        ExamArea optionD = new ExamArea("Option D");

        saveButton = new JButton("Save");

        // Add fields to the panel
        examAreaHolder.add(questionText.panel);
        examAreaHolder.add(optionA.panel);
        examAreaHolder.add(optionB.panel);
        examAreaHolder.add(optionC.panel);
        examAreaHolder.add(optionD.panel);
        examAreaHolder.add(correctAnswerText.panel);
        examAreaHolder.add(saveButton);

        // Save button functionality
        saveButton.addActionListener(e -> {
            try {
                // Save question and options to a file
                FileOutputStream fileOut = new FileOutputStream("question.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fileOut);
                Questions q = new Questions(
                        questionText.textArea.getText(),
                        correctAnswerText.textArea.getText(),
                        optionA.textArea.getText(),
                        optionB.textArea.getText(),
                        optionC.textArea.getText(),
                        optionD.textArea.getText());
                oos.writeObject(q);
                oos.close();
                JOptionPane.showMessageDialog(frame, "Question saved successfully!");
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        });

        frame.setContentPane(examAreaHolder);
        frame.setSize(1000, 600);
    }
}
