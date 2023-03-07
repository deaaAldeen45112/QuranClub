package net.jordan.quran_club.model.student;

import java.util.List;

public class ResponseStudentName {
    List<StudentWithName> data;
    String status;

    public List<StudentWithName> getData() {
        return data;
    }

    public void setData(List<StudentWithName> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
