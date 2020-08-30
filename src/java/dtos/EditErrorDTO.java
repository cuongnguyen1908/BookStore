/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author nguyen
 */
public class EditErrorDTO {
    private String emailError;
    private String usernameLengthError;
    private String fullNameLengthError;
    private String passwordLengthError;
    private String phoneLengthError;
    private String confirmPasswordNotMatch;
    private String usernameHasExist;
    private String fileEmpty;

    public String getFileEmpty() {
        return fileEmpty;
    }

    public void setFileEmpty(String fileEmpty) {
        this.fileEmpty = fileEmpty;
    }
    
    

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getFullNameLengthError() {
        return fullNameLengthError;
    }

    public void setFullNameLengthError(String fullNameLengthError) {
        this.fullNameLengthError = fullNameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getPhoneLengthError() {
        return phoneLengthError;
    }

    public void setPhoneLengthError(String phoneLengthError) {
        this.phoneLengthError = phoneLengthError;
    }

    public String getConfirmPasswordNotMatch() {
        return confirmPasswordNotMatch;
    }

    public void setConfirmPasswordNotMatch(String confirmPasswordNotMatch) {
        this.confirmPasswordNotMatch = confirmPasswordNotMatch;
    }

    public String getUsernameHasExist() {
        return usernameHasExist;
    }

    public void setUsernameHasExist(String usernameHasExist) {
        this.usernameHasExist = usernameHasExist;
    }
    
    
}
