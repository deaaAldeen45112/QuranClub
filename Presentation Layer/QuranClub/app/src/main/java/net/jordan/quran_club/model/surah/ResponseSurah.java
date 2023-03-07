package net.jordan.quran_club.model.surah;


import java.util.List;

public class ResponseSurah {
    private List<Surah> data;
    private String status;

    public List<Surah> getData() {
        return data;
    }

    public void setData(List<Surah> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

