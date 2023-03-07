package net.jordan.quran_club.model.studentLessonQuranPartSurah;

import java.util.List;

public class ResponseStudentLessonQuranPartSurah {
    List<StudentLessonQuranPartSurah> data;
    String status;

    public List<StudentLessonQuranPartSurah> getData() {
        return data;
    }

    public void setData(List<StudentLessonQuranPartSurah> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
