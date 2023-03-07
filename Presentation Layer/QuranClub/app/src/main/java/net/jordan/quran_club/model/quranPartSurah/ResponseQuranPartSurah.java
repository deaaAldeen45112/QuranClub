package net.jordan.quran_club.model.quranPartSurah;

import java.util.List;

public class ResponseQuranPartSurah {
    List<QuranPartSurah> data;
    String status;

    public List<QuranPartSurah> getData() {
        return data;
    }

    public void setData(List<QuranPartSurah> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
