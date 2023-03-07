package net.jordan.quran_club.model.admin;

import java.util.List;

public class ResponseAdmin {
    private List<Admin> data;
    private String status;

    public List<Admin> getData() {
        return data;
    }

    public void setData(List<Admin> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
