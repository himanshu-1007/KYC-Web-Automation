package org.mobikwik;

public class OtpInitiate {

    String name;
    String aadhaarNumber;

    public OtpInitiate(String name, String aadhaarNumber){
        this.aadhaarNumber=aadhaarNumber;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }
}
