package net.jordan.quran_club.model.student;

import java.util.List;

public class ResponseStudent {
    List<Student> data;
    String status;

    public List<Student> getData() {
        return data;
    }

    public void setData(List<Student> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
