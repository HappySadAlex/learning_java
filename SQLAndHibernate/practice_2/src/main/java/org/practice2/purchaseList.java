package org.practice2;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Table(name = "purchaselist")
public class purchaseList {

    @EmbeddedId
    private PurchaseListKey id;

    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;
    @Column(name = "course_name", insertable = false, updatable = false)
    private String name;
    @Column(name = "price")
    private int price;
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

    public class PurchaseListKey implements Serializable{

        @Column(name = "student_name")
        private String student;
        @Column(name = "course_name")
        private String course;

        public String getStudent() {
            return student;
        }

        public void setStudent(String student) {
            this.student = student;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PurchaseListKey that = (PurchaseListKey) o;
            return Objects.equals(student, that.student) && Objects.equals(course, that.course);
        }

        @Override
        public int hashCode() {
            return Objects.hash(student, course);
        }
    }
}
