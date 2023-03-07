package net.jordan.quran_club.model.studentQuranPart;

import java.util.List;

public class ResponseStudentJoinQuranPart {
    private List<StudentJoinQuranPart> data;
    private String status;

    public List<StudentJoinQuranPart> getData() {
        return data;
    }

    public void setData(List<StudentJoinQuranPart> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
