package net.jordan.quran_club.model.teacher;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import net.jordan.quran_club.model.userlogin.UserLogin;

@Entity(tableName = "teacher",
        foreignKeys = {
                @ForeignKey(entity = UserLogin.class,
                        parentColumns = "userloginId",
                        childColumns = "userloginId",
                        onDelete = CASCADE
                )
        })
public class Teacher {
    @PrimaryKey(autoGenerate = true)
    private int teacherId;
    private int userloginId;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getUserloginId() {
        return userloginId;
    }

    public void setUserloginId(int userloginId) {
        this.userloginId = userloginId;
    }
}
