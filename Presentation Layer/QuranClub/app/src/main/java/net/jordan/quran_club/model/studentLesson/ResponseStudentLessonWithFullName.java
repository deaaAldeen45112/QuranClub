package net.jordan.quran_club.model.studentLesson;

import java.util.List;

public class ResponseStudentLessonWithFullName {
    List<StudentLessonWithFullName> data;
    String status;

    public List<StudentLessonWithFullName> getData() {
        return data;
    }

    public void setData(List<StudentLessonWithFullName> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
