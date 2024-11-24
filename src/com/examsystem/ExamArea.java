package com.examsystem;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ExamArea {
    public JPanel panel;
    public String typedText;
    public JTextArea textArea;
    public ExamArea (String text ) {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel(text);
        panel.add(label, BorderLayout.NORTH);

        textArea = new JTextArea();;

        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateText();
            }
            public void removeUpdate(DocumentEvent e) {
                updateText();
            }
            public void insertUpdate(DocumentEvent e) {
                updateText();
            }
            public void updateText() {
                typedText = textArea.getText();
            }
        });

        panel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100)); // Left margin
    }


}
