package com.examsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class OpenFrame implements ActionListener {
    JFrame frame = new JFrame("Exam System");
    JButton studentButton, teacherButton, studentLogin, teacherLogin;
    JLabel header;
    JPanel studentLoginForm;


    public OpenFrame() {
        // Create and style the header label
        header = new JLabel("ARE YOU STUDENT OR TEACHER");
        header.setForeground(Color.WHITE);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setBounds(20, 80, 1000, 50);
        header.setFont(new Font("Serif", Font.BOLD, 40));
        header.setSize(1000, 60);

        // student and teacher buttons
        studentLoginForm = new JPanel();
        studentButton = buttonCreator("STUDENT", 250);
        teacherButton = buttonCreator("TEACHER", 550);
        studentLogin = buttonCreator("Login", 380);
        teacherLogin = buttonCreator("Login", 380);

        studentLogin.setVisible(false);
        teacherLogin.setVisible(false);

        // Frame setup
        ImageIcon image = new ImageIcon("astulogo.png");
        frame.setBounds(10, 10, 1000, 600);
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(header);
        frame.add(studentButton);
        frame.add(teacherButton);
        frame.add(studentLogin);
        frame.add(teacherLogin);
        frame.setVisible(true);
    }

    public JButton buttonCreator(String toWho, int xPosition) {
        JButton button = new JButton(toWho);
        button.setForeground(Color.WHITE);
        button.setFocusable(false);
        button.setBounds(xPosition, 250, 200, 100);
        button.setBackground(Color.BLACK);
        button.addActionListener(this);
        button.setFont(new Font("Serif", Font.BOLD, 30));
        return button;
    }

    public JPanel LoginCreator() {
        JPanel panel = new JPanel (new FlowLayout(FlowLayout.CENTER));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);


        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!usernameField.getText().equals("") && !passwordField.getPassword().equals("")) {
                panel.setVisible(false);
                    try {
                        FileInputStream fis=new FileInputStream("question.txt");
                        ObjectInputStream ois=new ObjectInputStream(fis);
                       Questions q= (Questions) ois.readObject();
                        try {
                            TakeExam tekexam=new TakeExam(frame);
                            tekexam.takeExamField();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    } catch (IOException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        });
        panel.add(loginButton);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == studentButton) {
            teacherButton.setVisible(false);
            studentButton.setVisible(false);
            header.setVisible(false);
            studentLogin.setVisible(true);



        } else if (e.getSource() == teacherButton) {
            studentButton.setVisible(false);
            teacherButton.setVisible(false);
            header.setVisible(false);
            teacherLogin.setVisible(true);
            // Handle teacher button click here

        } else if (e.getSource() == studentLogin) {
            studentLoginForm = LoginCreator();
            studentLoginForm.setBounds(400, 100, 300, 350);

            frame.add(studentLoginForm);
            frame.repaint();
            studentLogin.setVisible(false);

        }  else if (e.getSource() == teacherLogin) {
            teacherLogin.setVisible(false);
            try {
                new ExamDataSaver(frame).examTypingArea();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }}


