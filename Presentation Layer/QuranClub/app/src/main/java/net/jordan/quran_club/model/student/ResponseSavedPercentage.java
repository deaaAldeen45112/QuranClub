package net.jordan.quran_club.model.student;

import java.util.List;

public class ResponseSavedPercentage {
    List<SavedPercentage> data;
    String status;

    public List<SavedPercentage> getData() {
        return data;
    }

    public void setData(List<SavedPercentage> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
