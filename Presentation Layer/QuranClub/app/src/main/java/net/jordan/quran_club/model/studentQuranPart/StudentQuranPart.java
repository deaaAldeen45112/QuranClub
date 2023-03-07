package net.jordan.quran_club.model.studentQuranPart;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import net.jordan.quran_club.model.student.Student;
import net.jordan.quran_club.model.quranPart.QuranPart;

@Entity(tableName = "studentQuranPart",
        foreignKeys = {
                @ForeignKey(entity = QuranPart.class,
                        parentColumns = "quranPartId",
                        childColumns = "quranPartId"),
                @ForeignKey(entity = Student.class,
                        parentColumns = "studentId",
                        childColumns = "studentId")
        })

public class StudentQuranPart {
    @PrimaryKey(autoGenerate = true)
    private int studentQuranPartId;
    private int studentId;
    private int quranPartId;

    public int getStudentQuranPartId() {
        return studentQuranPartId;
    }

    public void setStudentQuranPartId(int studentQuranPartId) {
        this.studentQuranPartId = studentQuranPartId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getQuranPartId() {
        return quranPartId;
    }

    public void setQuranPartId(int quranPartId) {
        this.quranPartId = quranPartId;
    }
}
