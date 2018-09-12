package org.mobikwik;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.junit.Assume;

public class KYCWebAuto {

    //LoginCredentials lgcdr = new LoginCredentials("electronicshbti@gmail.com","qwerty54321","9818823461");
    LoginCredentials lgcdr = new LoginCredentials("vsgtm9@gmail.com","qwerty54321","7895836127");
    OtpInitiate oi = new OtpInitiate("Shashwat","632245929285" );//632245929285
    AlternateDoc ad = new AlternateDoc("PAN", "Shashwat Gautam", "DMN9876523");
    BiometricInitiate bi = new BiometricInitiate("122003", "Woodstock B&G P.G., Sector-52", "Tubewell No. 8");

    public void sleep(int milli) {
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebDriver fr = new ChromeDriver();
    public String val="", val2="", val3="";
    int flag=1, p=4;

    @Test(priority = 1)
    public void Login() {

        fr.get("https://pymtstg.mobikwik.com/");

        fr.manage().window().maximize();
        sleep(1000);
        WebElement ele = fr.findElement(By.id("qa-mbkLogin"));
        ele.click();

        sleep(5000);

        /*ele = ((ChromeDriver) fr).findElementByXPath(".//*[contains(@type,'text') and contains(@placeholder,'Enter here')]");
        ele.sendKeys(lgcdr.getEmailId());
        ele = ((ChromeDriver) fr).findElementByXPath(".//*[contains(@type,'password') and contains(@placeholder,'Enter Password')]");
        ele.sendKeys(lgcdr.getPassword());*/
        ele = ((ChromeDriver) fr).findElementByXPath(".//*[contains(@type,'text') and contains(@placeholder,'Enter here')]");
        ele.sendKeys(lgcdr.getPhoneNumber());

        sleep(2000);
        ele.submit();

        int flag=1;

        while(flag==1) {
            String val = ((ChromeDriver) fr).findElementByXPath(".//*[contains(@type,'password') and contains(@placeholder,'Enter OTP')]").getAttribute("value");
            sleep(1000);
            if (val.length()==6){
                ele = ((ChromeDriver) fr).findElementByXPath(".//*[contains(@type,'submit') and contains(text(),'Submit OTP')]");
                ele.click();
                flag=0;
            }
        }

    }

    @Test(priority = 3)
    public void ekycInitiate(){


        sleep(2000);
        WebElement ele;


        ele=fr.findElement(By.id("qa-mbkHamburger"));
        ele.click();
        sleep(1000);
        ele=((ChromeDriver) fr).findElementByXPath(".//*[contains(text(),'Upgrade wallet')]");
        ele.click();
        sleep(2500);
        ele=fr.findElement(By.id("fullName"));
        ele.sendKeys(oi.getName());
        ele = fr.findElement(By.id("aadhaarNumber"));
        ele.sendKeys(oi.getAadhaarNumber());

        sleep(2000);
        try {
            ele = ((ChromeDriver) fr).findElementByXPath("//*[@id=\"view1\"]/div/div[2]/div[3]/button");
            ele.click();
        }catch (Exception e){
            System.out.println("Initiate Exception: "+e);
        }
        sleep(15000);
        try
        {
            val = ((ChromeDriver) fr).findElementByXPath(".//*[contains(text(),'Please confirm the below address where the MobiKwik agent can come for verification.')]").getText();
        }catch(Exception e){
            System.out.print("This is the exception to be resolved for val: "+ e);
        }
        sleep(1000);
    }

    @Test(dependsOnMethods = {"ekycInitiate"}, priority = 4)
    public void ekycReinitiate(){

        try {
            Assume.assumeTrue(val.length() == 0);
            sleep(20000);
            WebElement ele;

            ele = ((ChromeDriver) fr).findElementByXPath(".//*[contains(text(),'Resend OTP')]");
            ele.click();
        }catch (Exception e){
        }

    }


    @Test(priority = 5, dependsOnMethods = {"ekycInitiate"})
    public void ekycVerify(){

        try{
            Assume.assumeTrue((val.length() == 0||false));

            sleep(10000);
            WebElement ele;

            try {
                flag = 0;
                while (flag <= 2) {
                    val3 = ((ChromeDriver) fr).findElementByXPath(".//*[contains(@type,'text') and contains(@placeholder,'Enter OTP')]").getAttribute("value");
                    sleep(10000);
                    if (val3.length() == 6) {
                        ele = ((ChromeDriver) fr).findElementByXPath("//*[@id=\"view2\"]/div/div[2]/div[2]/button");
                        ele.click();
                        break;
                    }
                    flag++;
                }
       /* ele=((ChromeDriver) fr).findElementByXPath("//*[@id="view3_1"]/div/div[2]/div[4]/button");
        ele.click();*/
                Assume.assumeTrue(val3.length()==6);
                sleep(50000);

                ele = fr.findElement(By.cssSelector("#view3_1 > div > div.dpTbCel.vtop.pad30.ptop.pbottom.col-md-10.col-md-push-1 > div.form-group.tcenter.mar30.mbottom > button"));
                ele.click();

                sleep(1000);
                ele=fr.findElement(By.cssSelector("#view4 > div > div > div.form-group.tcenter.mar30.mbottom > button"));
                ele.click();
                //ele = ((ChromeDriver) fr).findElementByXPath("//*[@id=\"view3_1\"]/div/div[2]/p[3]/a");
                //ele=fr.findElement(By.cssSelector("#view3_1 > div > div.dpTbCel.vtop.pad30.ptop.pbottom.col-md-10.col-md-push-1 > p.mar30.mtop.mbottom.tcenter > a"));
                //ele.click();
                //val2 = ((ChromeDriver) fr).findElementByXPath(".//*[contains(text(),'Please confirm the below address where the MobiKwik agent can come for verification.')]").getText();

            }catch (Exception e){

                ele=fr.findElement(By.cssSelector("#view2 > div > div.dpTbCel.vtop.pad30.ptop.pbottom.col-md-10.col-md-push-1 > p:nth-child(6) > a"));
                ele.click();
                val2 = ((ChromeDriver) fr).findElementByXPath(".//*[contains(text(),'Please confirm the below address where the MobiKwik agent can come for verification.')]").getText();

            }
            sleep(2000); }
        catch (Exception e){
        }

            /*ele = ((ChromeDriver) fr).findElementByXPath(".//*[contains(text(),'Back To Home')]");
            ele.click();*/
    }

    @Test(priority = 6, dependsOnMethods = {"ekycInitiate","ekycVerify"})
    public void biometricInitiate(){

        sleep(2000);

        WebElement ele;

        try {
            Assume.assumeTrue((val.length() == 84||val2.length() == 84));

            ele = fr.findElement(By.id("kycPincode"));
            ele.sendKeys(bi.getPincode());

            ele = fr.findElement(By.id("kycAddress"));
            ele.sendKeys(bi.getAddress());

            ele = fr.findElement(By.id("kycLandmark"));
            ele.sendKeys(bi.getLandmark());

            ele = fr.findElement(By.xpath(".//*[contains(@type,'submit') and contains(text(),'Confirm Address')]"));
            ele.click();

            sleep(2000);
            /*if(val.length()==84){
                ele=fr.findElement(By.cssSelector("#view4 > div > div > div.form-group.tcenter.mar30.mbottom.ng-scope > button"));
                //ele = ((ChromeDriver) fr).findElementByXPath(".//*[contains(text(),'Back To Home')]");
                ele.click();
            }
            else if(val2.length()==84){
                ele=fr.findElement(By.cssSelector("#view4 > div > div > div.form-group.tcenter.mar30.mbottom.ng-scope > button"));
                //ele=fr.findElement(By.xpath("//*[@id=\"view4\"]/div/div/div[2]/button"));
                ele.click();
            }*/
            ele=fr.findElement(By.cssSelector("#view4 > div > div > div.form-group.tcenter.mar30.mbottom.ng-scope > button"));
            ele.click();
        }catch (Exception e){
            System.out.println("This is exception to be resolved for Biometric Initiate: "+e);
        }

    }


    @Test(priority = 2)
    public void alternateKyc() {

        WebElement ele, ele1;
        sleep(2000);
        ele=fr.findElement(By.xpath("//*[@id=\"view1\"]/div/div[2]/p[1]/a"));
        ele.click();

        Select drop=new Select(fr.findElement(By.id("doctype")));
        drop.selectByVisibleText(ad.getDoctype());

        ele=fr.findElement(By.id("fullNameVal"));
        ele.clear();
        ele.sendKeys(ad.getFullNameVal());

        ele=fr.findElement(By.id("idNumber"));
        ele.sendKeys(ad.getIdNumber());

        ele=fr.findElement(By.xpath("//*[@id=\"view1\"]/div/div/div[4]/button"));
        sleep(2000);
        ele.click();

        sleep(1500);
        try {
            ele1=fr.findElement(By.cssSelector("#view4 > div > div > div.form-group.tcenter.mar30.mbottom.ng-scope > button"));
            sleep(3000);

            ele1.click();
        }catch (Exception e){
            System.out.println("This is exception: "+e);
        }

    }

    @Test(enabled = false, priority = 2)
    public void cross(){

        WebElement ele;
        ele=fr.findElement(By.xpath(".//*[contains(@class,'icocross')]"));
        ele.click();

        ele = fr.findElement(By.xpath(".//*[contains(@type,'submit') and contains(text(),'Continue')]"));
        ele.click();

        Select drop=new Select(fr.findElement(By.id("doctype")));
        drop.selectByVisibleText(ad.getDoctype());

        ele=fr.findElement(By.id("fullNameVal"));
        ele.sendKeys(ad.getFullNameVal());

        ele=fr.findElement(By.id("idNumber"));
        ele.sendKeys(ad.getIdNumber());

        ele = fr.findElement(By.xpath(".//*[contains(@type,'submit') and contains(text(),'Continue')]"));
        ele.click();

    }

    @Test(priority = 7)
    public void Logout() {

        sleep(2000);

        WebElement ele;

        ele=fr.findElement(By.id("qa-mbkHamburger"));
        ele.click();
        sleep(1000);
        ele=((ChromeDriver) fr).findElementByXPath(".//*[contains(text(),'Logout')]");
        ele.click();
        sleep(5000);

        fr.close();

    }
}
