package net.jordan.quran_club.model.studentQuranPart;

import java.util.List;

public class ResponseStudentQuranPart {
    List<StudentQuranPart> data;
    String status;

    public List<StudentQuranPart> getData() {
        return data;
    }

    public void setData(List<StudentQuranPart> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
