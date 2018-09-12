package org.mobikwik;

public class LoginCredentials
{
    String emailId;
    String password;
    String phoneNumber;

    public LoginCredentials(String emailId, String password, String phoneNumber){

        this.password=password;
        this.emailId=emailId;
        this.phoneNumber=phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
