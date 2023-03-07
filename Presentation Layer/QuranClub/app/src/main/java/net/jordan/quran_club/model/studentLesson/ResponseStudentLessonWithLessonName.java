package net.jordan.quran_club.model.studentLesson;

import java.util.List;

public class ResponseStudentLessonWithLessonName {
List<StudentLessonWithLessonName>data;
String status;

    public List<StudentLessonWithLessonName> getData() {
        return data;
    }

    public void setData(List<StudentLessonWithLessonName> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
