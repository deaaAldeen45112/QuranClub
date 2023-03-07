package net.jordan.quran_club.model.admin;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import net.jordan.quran_club.model.userlogin.UserLogin;

@Entity(tableName = "admin",
        foreignKeys = @ForeignKey(
                entity = UserLogin.class,
                parentColumns = "userloginId",
                childColumns = "userloginId")
)
public class Admin {
    @PrimaryKey(autoGenerate = true)
    private  int adminId;
    private  int userloginId;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getUserloginId() {
        return userloginId;
    }

    public void setUserloginId(int userloginId) {
        this.userloginId = userloginId;
    }
}
