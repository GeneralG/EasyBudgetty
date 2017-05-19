package za.co.easybudgetty.helpers.regex;

import android.content.res.Configuration;
import android.util.Log;

import java.util.Currency;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tyler on 2016/05/12.
 * This is to simple More banks? More amounts? Currency?
 *
 *
 * IDEA!!
 * Use classes to store the data in memory.
 * initialize 1 regex parser at startup and use this object to parse the data.
 *  thread safety?
 *      ? service vs front end
 *      methods or functions on the service to allow for database reload
 *  Will have to use syncronize methods to make sure thread safety is performed.
 * question, how will i update the static objects with new custom configurations?
 *      Events ?? negative.
 *      User interface to reload from db?
 *          use an even on the interface to relaod from database.
 */
public class RegexParser {
    private static String TAG = RegexParser.class.getCanonicalName();

    private static Pattern amountPattern = Pattern.compile("[0-9].2[0-9]");
    private static Pattern bankPattern = Pattern.compile("Nedbank");
    private static Pattern bankCurrency = null;

    private static Matcher patternMatcher;

    static {
        //Currency c = Currency.getInstance(Locale.getAvailableLocales()[0]);
        //bankCurrency = Pattern.compile(c.getSymbol());
        //load the currency from the database or logic(use the location of the phone and get the currency of the country).
    }

    public RegexParser()
    {
        //Load currency logic should go here
    }

    //validate the number that the SMS is coming from
        //this is to check if a message coming into the app or phone should be taken into the applications logic
    public static boolean checkNumberValidility(String number)
    {
        patternMatcher = amountPattern.matcher(number);
        Log.i(TAG, "checkNumberValidility: RUN");
        return patternMatcher.find();
    }

    //Validate that the message contains atleast one amount and a date
        // best would be to check if there are 2 amounts balance and transaction value
    public static boolean checkMessageValidility(String message)
    {
        Log.i(TAG, "checkMessageValidility: RUN");

        int amountsFound = 0;
        patternMatcher.usePattern(amountPattern);
        if (patternMatcher.matches())
        {
            amountsFound = patternMatcher.groupCount();
        }


        Log.i(TAG, "checkMessageValidility: Groups Found: " + String.valueOf(amountsFound));

        return false;
    }

}
