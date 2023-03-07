package net.jordan.quran_club.model.quranPart;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quranPart")
public class QuranPart {

    @PrimaryKey(autoGenerate = true)
    private  int quranPartId;
    private  int number;
    private  int numberOfVerses;

    public int getQuranPartId() {
        return quranPartId;
    }

    public void setQuranPartId(int quranPartId) {
        this.quranPartId = quranPartId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfVerses() {
        return numberOfVerses;
    }

    public void setNumberOfVerses(int numberOfVerses) {
        this.numberOfVerses = numberOfVerses;
    }
}
