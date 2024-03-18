package org.example;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Table(name = "purchaselist")
public class purchaseList {
    @Id
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "course_name")
    private String name;
    private int price;
    @OrderBy
    @Column(name = "subscription_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp date;


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return name;
    }

    public void setCourseName(String courseName) {
        this.name = courseName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "purchaseList{" +
                "studentName='" + studentName + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", subDate=" + date +
                '}';
    }
}
