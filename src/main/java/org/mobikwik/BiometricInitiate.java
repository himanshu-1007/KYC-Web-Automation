package org.mobikwik;

public class BiometricInitiate {

    String pincode;
    String address;
    String landmark;

    public BiometricInitiate(String pincode, String address, String landmark){

        this.pincode=pincode;
        this.address=address;
        this.landmark=landmark;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}
