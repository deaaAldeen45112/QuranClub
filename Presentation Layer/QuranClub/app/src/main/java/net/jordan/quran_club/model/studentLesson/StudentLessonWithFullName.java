package net.jordan.quran_club.model.studentLesson;

public class StudentLessonWithFullName {
    private int studentLessonId;
    private String fullNam;
    private int quranPartId;
    private int surahId;
    private int fromVerse;
    private int toVerse;
    private String note;

    public int getStudentLessonId() {
        return studentLessonId;
    }

    public void setStudentLessonId(int studentLessonId) {
        this.studentLessonId = studentLessonId;
    }

    public String getFullNam() {
        return fullNam;
    }

    public void setFullNam(String fullNam) {
        this.fullNam = fullNam;
    }

    public int getSurahId() {
        return surahId;
    }

    public void setSurahId(int surahId) {
        this.surahId = surahId;
    }

    public int getQuranPartId() {
        return quranPartId;
    }

    public void setQuranPartId(int quranPartId) {
        this.quranPartId = quranPartId;
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
}
