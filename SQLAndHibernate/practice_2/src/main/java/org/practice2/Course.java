package org.practice2;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.mapping.UniqueKey;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "duration", columnDefinition = "int(10) unsigned DEFAULT NULL COMMENT '   '")
    private int duration;


    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('DESIGN','PROGRAMMING','MARKETING','MANAGEMENT','BUSINESS') NOT NULL COMMENT ' :  /  /  / '")
    private org.practice2.CourseType type;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", insertable = false, updatable = false)
    private Teacher teacherId;

    @Column(name = "students_count", columnDefinition = "int(10) unsigned DEFAULT NULL COMMENT '   '")
    private int studentsCount;

    @Column(name = "price", columnDefinition = "int(10) unsigned DEFAULT NULL COMMENT '   '")
    private int price;

    @Column(name = "price_per_hour", columnDefinition = "float DEFAULT NULL")
    private float pricePerHour;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subscriptions",
    joinColumns = {@JoinColumn(name ="course_id")},
    inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public org.practice2.CourseType getType() {
        return type;
    }

    public void setType(org.practice2.CourseType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacherId;
    }

    public void setTeacher(Teacher teacherId) {
        this.teacherId = teacherId;
    }
    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }


    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                /*", type=" + type +
                ", studentsCount=" + studentsCount +
                ", price=" + price +*/
                '}';
    }
}
