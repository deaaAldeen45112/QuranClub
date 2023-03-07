package net.jordan.quran_club.model.student;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import net.jordan.quran_club.model.lesson.Lesson;

@Entity(tableName = "student",
        foreignKeys = {
                @ForeignKey(entity = Lesson.class,
                        parentColumns = "lessonId",
                        childColumns = "lessonId"),
                @ForeignKey(entity = Student.class,
                        parentColumns = "studentId",
                        childColumns = "studentId")
        })
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int studentId;
    private int numberOfDauan;
    private int userloginId;
    private int classId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getNumberOfDauan() {
        return numberOfDauan;
    }

    public void setNumberOfDauan(int numberOfDauan) {
        this.numberOfDauan = numberOfDauan;
    }

    public int getUserloginId() {
        return userloginId;
    }

    public void setUserloginId(int userloginId) {
        this.userloginId = userloginId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
