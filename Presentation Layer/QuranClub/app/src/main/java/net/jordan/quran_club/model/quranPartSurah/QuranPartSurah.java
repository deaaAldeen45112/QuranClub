package net.jordan.quran_club.model.quranPartSurah;

import androidx.room.PrimaryKey;

//@Entity(tableName = "quranPartS")
public class QuranPartSurah {
    @PrimaryKey(autoGenerate = true)
    private  int quranPartSurahId;
    private  int quranPartId;
    private  int surahId;
    private  int quranPartSurahFrom;
    private  int quranPartSurahTo;

    public int getQuranPartSurahId() {
        return quranPartSurahId;
    }

    public void setQuranPartSurahId(int quranPartSurahId) {
        this.quranPartSurahId = quranPartSurahId;
    }

    public int getSurahId() {
        return surahId;
    }

    public void setSurahId(int surahId) {
        this.surahId = surahId;
    }

    public int getQuranPartSurahFrom() {
        return quranPartSurahFrom;
    }

    public void setQuranPartSurahFrom(int quranPartSurahFrom) {
        this.quranPartSurahFrom = quranPartSurahFrom;
    }

    public int getQuranPartSurahTo() {
        return quranPartSurahTo;
    }

    public void setQuranPartSurahTo(int quranPartSurahTo) {
        this.quranPartSurahTo = quranPartSurahTo;
    }

    public int getQuranPartId() {
        return quranPartId;
    }

    public void setQuranPartId(int quranPartId) {
        this.quranPartId = quranPartId;
    }
}
