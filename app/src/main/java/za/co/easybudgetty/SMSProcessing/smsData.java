package za.co.easybudgetty.SMSProcessing;

import java.util.ArrayList;

/**
 * Created by tyler on 2016/06/13.
 * Model object to store the core data of the sms, objects of this class
 * should be passed through the application
 */
public class smsData
{

    /*
    *The strings are used in the mean time to make sure the data can be stored.
    * String trimming to be done by the parser and type conversion
     */
    private String smsNumber;
    private String smsBank;
    private ArrayList<String> amounts;
    private String balance;
    private String transaction;

}
