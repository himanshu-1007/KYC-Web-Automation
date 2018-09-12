package org.mobikwik;

public class AlternateDoc {

    String doctype;
    String fullNameVal;
    String idNumber;

    public AlternateDoc(String doctype, String fullNameVal, String idNumber){

        this.doctype=doctype;
        this.fullNameVal=fullNameVal;
        this.idNumber=idNumber;
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    public String getFullNameVal() {
        return fullNameVal;
    }

    public void setFullNameVal(String fullNameVal) {
        this.fullNameVal = fullNameVal;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
