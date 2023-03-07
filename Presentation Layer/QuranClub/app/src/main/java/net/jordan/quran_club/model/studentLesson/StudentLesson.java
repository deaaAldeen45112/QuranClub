package net.jordan.quran_club.model.studentLesson;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import net.jordan.quran_club.model.lesson.Lesson;
import net.jordan.quran_club.model.student.Student;

@Entity(tableName = "studentLesson",
        foreignKeys = {
                @ForeignKey(entity = Lesson.class,
                        parentColumns = "lessonId",
                        childColumns = "lessonId"),
                @ForeignKey(entity = Student.class,
                        parentColumns = "studentId",
                        childColumns = "studentId")
        })
public class StudentLesson {
    @PrimaryKey(autoGenerate = true)
    private int studentLessonId;
    private int quranPartId;
    private int surahId;
    private int fromVerse;
    private int toVerse;
    private String note;
    private int lessonId;
    private int studentId;

    public int getStudentLessonId() {
        return studentLessonId;
    }

    public void setStudentLessonId(int studentLessonId) {
        this.studentLessonId = studentLessonId;
    }

    public int getQuranPartId() {
        return quranPartId;
    }

    public void setQuranPartId(int quranPartId) {
        this.quranPartId = quranPartId;
    }

    public int getSurahId() {
        return surahId;
    }

    public void setSurahId(int surahId) {
        this.surahId = surahId;
    }

    public int getFromVerse() {
        return fromVerse;
    }

    public void setFromVerse(int fromVerse) {
        this.fromVerse = fromVerse;
    }

    public int getToVerse() {
        return toVerse;
    }

    public void setToVerse(int toVerse) {
        this.toVerse = toVerse;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
