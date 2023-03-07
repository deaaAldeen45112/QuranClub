package net.jordan.quran_club.model.student;

public class StudentWithName {
    private int studentId;
    private int userloginId;
    private String fullName;
    private int numberOfDauan;

    public int getUserloginId() {
        return userloginId;
    }


    public void setUserloginId(int userloginId) {
        this.userloginId = userloginId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getNumberOfDauan() {
        return numberOfDauan;
    }

    public void setNumberOfDauan(int numberOfDauan) {
        this.numberOfDauan = numberOfDauan;
    }
}
