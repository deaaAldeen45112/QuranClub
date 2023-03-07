package net.jordan.quran_club.ui.Admin.crud.student.update;

import androidx.annotation.Nullable;

public class UpdateStudentFormState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    @Nullable
    private Integer phoneError;

    public void setUsernameError(@Nullable Integer usernameError) {
        this.usernameError = usernameError;
    }

    public void setPasswordError(@Nullable Integer passwordError) {
        this.passwordError = passwordError;
    }

    public void setPhoneError(@Nullable Integer phoneError) {
        this.phoneError = phoneError;
    }

    public void setAgeError(@Nullable Integer ageError) {
        this.ageError = ageError;
    }

    public void setEmailError(@Nullable Integer emailError) {
        this.emailError = emailError;
    }

    public void setDataValid(boolean dataValid) {
        isDataValid = dataValid;
    }

    @Nullable
    private Integer ageError;

    @Nullable
    public Integer getPhoneError() {
        return phoneError;
    }

    @Nullable
    public Integer getAgeError() {
        return ageError;
    }

    @Nullable
    public Integer getEmailError() {
        return emailError;
    }

    @Nullable
    private Integer emailError;

    private boolean isDataValid;

    UpdateStudentFormState(
              @Nullable Integer usernameError
            , @Nullable Integer passwordError
            , @Nullable Integer phoneError
            , @Nullable Integer ageError
            , @Nullable Integer emailError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.phoneError=phoneError;
        this.ageError=ageError;
        this.emailError=emailError;
        this.isDataValid = false;
    }
    UpdateStudentFormState(@Nullable Integer usernameError
            , @Nullable Integer passwordError
            ) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }

    UpdateStudentFormState(boolean isDataValid) {
        this.usernameError = null;
        this.passwordError = null;
        this.phoneError=null;
        this.ageError=null;
        this.emailError=null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}
