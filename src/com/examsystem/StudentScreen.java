package com.examsystem;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class StudentScreen implements Serializable {
    String name;
    int age;
    String gender;
    String course;
    String id ;
    String department;
    public StudentScreen(String name, int age, String gender, String course, String id, String department) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.course = course;
        this.id = id;
        this.department = department;
    }}


