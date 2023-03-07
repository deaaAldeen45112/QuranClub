package net.jordan.quran_club.model.quranPartSurah;

import java.util.List;

public class ResponseQuranPartSurahJoinQuranPartJoinSurah {
    List<QuranPartSurahJoinQuranPartJoinSurah> data;
    String status;

    public List<QuranPartSurahJoinQuranPartJoinSurah> getData() {
        return data;
    }

    public void setData(List<QuranPartSurahJoinQuranPartJoinSurah> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
