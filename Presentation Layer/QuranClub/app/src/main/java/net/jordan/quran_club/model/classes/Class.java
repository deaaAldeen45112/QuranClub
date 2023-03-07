package net.jordan.quran_club.model.classes;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import net.jordan.quran_club.model.teacher.Teacher;

import java.util.Date;
@Entity(tableName = "class",
foreignKeys = @ForeignKey(
        entity = Teacher.class,
        parentColumns = "teacherId",
        childColumns = "teacherId")
)
public class Class {
    @PrimaryKey(autoGenerate = true)
    private  int classId;
    private  String name;
    private  Date dateCreated;
    private  int teacherId;

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
