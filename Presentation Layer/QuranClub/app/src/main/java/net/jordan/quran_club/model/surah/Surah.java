package net.jordan.quran_club.model.surah;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import net.jordan.quran_club.model.quranPart.QuranPart;

@Entity(tableName = "surah",
        foreignKeys = {
                @ForeignKey(entity = QuranPart.class,
                        parentColumns = "quranPartId",
                        childColumns = "quranPartId")
        })
public class Surah {
    @PrimaryKey(autoGenerate = true)
    private int surahId;
    private int quranPartId;
    private int surahQuranPartNumberOfVerse;
    private String name;

    public int getSurahId() {
        return surahId;
    }

    public void setSurahId(int surahId) {
        this.surahId = surahId;
    }

    public int getQuranPartId() {
        return quranPartId;
    }

    public void setQuranPartId(int quranPartId) {
        this.quranPartId = quranPartId;
    }

    public int getSurahQuranPartNumberOfVerse() {
        return surahQuranPartNumberOfVerse;
    }

    public void setSurahQuranPartNumberOfVerse(int surahQuranPartNumberOfVerse) {
        this.surahQuranPartNumberOfVerse = surahQuranPartNumberOfVerse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
