package net.jordan.quran_club.model.teacher;


import java.util.ArrayList;

public class ResponseTeacher {
    private ArrayList<Teacher> data;
    private String status;

    public ArrayList<Teacher> getData() {
        return data;
    }

    public void setData(ArrayList<Teacher> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
