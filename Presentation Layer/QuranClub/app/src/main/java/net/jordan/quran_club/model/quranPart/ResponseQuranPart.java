package net.jordan.quran_club.model.quranPart;

import java.util.List;

public class ResponseQuranPart {
    List<QuranPart> data;
    String status;

    public List<QuranPart> getData() {
        return data;
    }

    public void setData(List<QuranPart> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
