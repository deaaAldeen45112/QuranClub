package net.jordan.quran_club.ui.Admin.crud.teacher.update;

import androidx.annotation.Nullable;

public class UpdateTeacherFormState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    @Nullable
    private Integer phoneError;
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

    UpdateTeacherFormState(@Nullable Integer usernameError
            , @Nullable Integer passwordError
            , @Nullable Integer phoneError
            , @Nullable  Integer ageError
            , @Nullable Integer emailError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.phoneError=phoneError;
        this.ageError=ageError;
        this.emailError=emailError;
        this.isDataValid = false;
    }
    UpdateTeacherFormState(@Nullable Integer usernameError
            , @Nullable Integer passwordError
            ) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.isDataValid = false;
    }

    UpdateTeacherFormState(boolean isDataValid) {
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
