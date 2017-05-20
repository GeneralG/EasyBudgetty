package za.co.easybudgetty.helpers.regex;

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

    //Price Patterns
    private static Pattern pricePattern = Pattern.compile("(R(\\d+[.](\\d){0,2}))");
    private static Pattern pricePatternWithSpace = Pattern.compile("R\\s(\\d+.\\d{0,2})");
    private static Pattern pricePatternWithSpaceDelimeter = Pattern.compile("R\\s(\\d{0,3}\\s)+(\\d{0,3})[.](\\d{0,2})");

    //Receiving message Number
    private static Pattern cellNumberPattern = Pattern.compile("^([+]{0,1})\\d{10,15}$");

    //Bank Name pattern
    private static Pattern bankPattern = Pattern.compile("Nedbank");

    //The currency pattern
    private static Pattern currencyPattern = null;

    //Pattern to match the last 4 digits of a card number
    private static Pattern cardNumberPattern = Pattern.compile("([*]\\d{4,4})");

    //Matcher for regex
    private static Matcher patternMatcher;

    static {
        Currency c = Currency.getInstance(Locale.getDefault());
        currencyPattern = Pattern.compile(c.getSymbol());
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
        patternMatcher = cellNumberPattern.matcher(number);
        return patternMatcher.find();
    }

    //Validate that the message contains atleast one amount and a date
        // best would be to check if there are 2 amounts balance and transaction value
    public static boolean checkMessageValidility(String message)
    {
        int keysFound = 0;
        patternMatcher = pricePattern.matcher(message);

        //Amounts have been found
        if (patternMatcher.find())
        {
            keysFound += 1;
        }
        
        //Check for currency
        patternMatcher = currencyPattern.matcher(message);
        if (patternMatcher.find())
        {
            keysFound += 1;
        }

        //Check for Bank name
        patternMatcher = bankPattern.matcher(message);
        if (patternMatcher.find())
        {
            keysFound += 1;
        }

        //Check for last 4 digits of a card number *1234
        patternMatcher = cardNumberPattern.matcher(message);
        if (patternMatcher.find())
        {
            keysFound += 1;
        }

        if (keysFound >= 4)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
