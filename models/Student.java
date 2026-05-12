package models;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    private int id;
    private String name;
    private String major;
    private double grade;

    public Student() {}

    public Student(int id, String name, String major, double grade) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.grade = grade;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public double getGrade() { return grade; }
    public void setGrade(double grade) { this.grade = grade; }
}