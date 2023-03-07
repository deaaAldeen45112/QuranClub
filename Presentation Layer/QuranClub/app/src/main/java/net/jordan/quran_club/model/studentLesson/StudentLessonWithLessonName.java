package net.jordan.quran_club.model.studentLesson;

public class StudentLessonWithLessonName {
    private int studentLessonId;
    private int quranPartNumber;
    private String surahName;
    private int fromVerse;
    private int toVerse;
    private String note;
    private int lessonName;

    public int getStudentLessonId() {
        return studentLessonId;
    }

    public void setStudentLessonId(int studentLessonId) {
        this.studentLessonId = studentLessonId;
    }

    public int getQuranPartNumber() {
        return quranPartNumber;
    }

    public void setQuranPartNumber(int quranPartNumber) {
        this.quranPartNumber = quranPartNumber;
    }

    public String getSurahName() {
        return surahName;
    }

    public void setSurahName(String surahName) {
        this.surahName = surahName;
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

    public int getLessonName() {
        return lessonName;
    }

    public void setLessonName(int lessonName) {
        this.lessonName = lessonName;
    }
}
