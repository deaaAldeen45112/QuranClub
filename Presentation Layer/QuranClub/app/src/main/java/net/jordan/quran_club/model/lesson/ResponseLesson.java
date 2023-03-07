package net.jordan.quran_club.model.lesson;

import java.util.List;

public class ResponseLesson {
    List<Lesson> data;
    String status;

    public List<Lesson> getData() {
        return data;
    }

    public void setData(List<Lesson> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
