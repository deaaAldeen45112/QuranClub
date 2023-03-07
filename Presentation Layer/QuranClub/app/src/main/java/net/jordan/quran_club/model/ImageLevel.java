package net.jordan.quran_club.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "imageLevel")
public class ImageLevel {
@PrimaryKey(autoGenerate = true)
    private  int imageLevelId;
    private  String path;
    private  int  number;

    public int getImageLevelId() {
        return imageLevelId;
    }

    public void setImageLevelId(int imageLevelId) {
        this.imageLevelId = imageLevelId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
