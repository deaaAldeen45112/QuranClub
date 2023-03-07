package net.jordan.quran_club.model.quranPartSurah;

public class QuranPartSurahJoinQuranPartJoinSurah {

    private  int quranPartSurahId;
    private  int quranPartNumber;
    private  int surahId;
    private  int quranPartSurahFrom;
    private  int quranPartSurahTo;
    private  String surahName;
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

    public int getQuranPartNumber() {
        return quranPartNumber;
    }

    public void setQuranPartNumber(int quranPartNumber) {
        this.quranPartNumber = quranPartNumber;
    }

    public String getSurahName() {
        return surahName;
    }

    public void setSurahName(String surahName) {
        this.surahName = surahName;
    }
}
