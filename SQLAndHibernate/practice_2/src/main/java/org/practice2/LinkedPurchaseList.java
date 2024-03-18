package org.practice2;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {

    @EmbeddedId
    private LinkedPurchaseListKey id;

    public LinkedPurchaseList() {
    }


    public LinkedPurchaseListKey getId() {
        return id;
    }

    public void setId(LinkedPurchaseListKey id) {
        this.id = id;
    }

    public static class LinkedPurchaseListKey implements Serializable{


        @Column(name = "student_id")
        private int studentId;
        @Column(name = "course_id")
        private int courseId;

        public LinkedPurchaseListKey() {
        }

        public LinkedPurchaseListKey(int studentId, int courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }


        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getStudentId() {
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LinkedPurchaseListKey that = (LinkedPurchaseListKey) o;
            return courseId == that.courseId && studentId == that.studentId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseId);
        }

        @Override
        public String toString() {
            return "LinkedPurchaseListKey{" +
                    "studentId=" + studentId +
                    ", courseId=" + courseId +
                    '}';
        }
    }

}
