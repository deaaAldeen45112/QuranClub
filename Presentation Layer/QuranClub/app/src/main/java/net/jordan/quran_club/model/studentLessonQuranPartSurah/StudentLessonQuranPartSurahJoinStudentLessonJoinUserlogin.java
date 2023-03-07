package net.jordan.quran_club.model.studentLessonQuranPartSurah;

public class StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin {

    private  int studentLessonQuranPartSurahId;
    private  int studentLessonId;
    private  int quranPartSurahId;
    private  int fromVerses;
    private  int toVerses;
    private String note;
    private String name;
    private int student_id;
    private String lessonName;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }
}
