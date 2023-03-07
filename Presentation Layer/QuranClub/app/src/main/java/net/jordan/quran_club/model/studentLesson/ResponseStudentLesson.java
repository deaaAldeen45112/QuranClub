package net.jordan.quran_club.model.studentLesson;

import java.util.List;

public class ResponseStudentLesson {
    List<StudentLesson> data;
    String status;

    public List<StudentLesson> getData() {
        return data;
    }

    public void setData(List<StudentLesson> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
