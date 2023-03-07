package net.jordan.quran_club.model.studentLessonQuranPartSurah;

import androidx.room.PrimaryKey;

//@Entity(tableName = "quranPartS")
public class StudentLessonQuranPartSurah {
    @PrimaryKey(autoGenerate = true)
    private  int studentLessonQuranPartSurahId;
    private  int studentLessonId;
    private  int quranPartSurahId;
    private  int fromVerses;
    private  int toVerses;

    public int getStudentLessonQuranPartSurahId() {
        return studentLessonQuranPartSurahId;
    }

    public void setStudentLessonQuranPartSurahId(int studentLessonQuranPartSurahId) {
        this.studentLessonQuranPartSurahId = studentLessonQuranPartSurahId;
    }

    public int getStudentLessonId() {
        return studentLessonId;
    }

    public void setStudentLessonId(int studentLessonId) {
        this.studentLessonId = studentLessonId;
    }

    public int getQuranPartSurahId() {
        return quranPartSurahId;
    }

    public void setQuranPartSurahId(int quranPartSurahId) {
        this.quranPartSurahId = quranPartSurahId;
    }

    public int getFromVerses() {
        return fromVerses;
    }

    public void setFromVerses(int fromVerses) {
        this.fromVerses = fromVerses;
    }

    public int getToVerses() {
        return toVerses;
    }

    public void setToVerses(int toVerses) {
        this.toVerses = toVerses;
    }
}
