package za.co.easybudgetty.helpers.regex;

import android.test.mock.MockContext;
import org.junit.*;


import java.util.logging.Logger;

/**
 * Created by tyler on 2017/05/19.
 */
public class RegexParserTest {

    private static final String TEST_DEPOSITE_MESSAGE = "Nedbank: Electronic Banking EFT deposit of R3000.00 into acc **9999 Avail bal R10000.00 at 20:01 on 01May17. Reg: Monkeys need food";
    private static final String TEST_CASHWITHDRAWAL_MESSAGE = "Nedbank: ATM Cash withdrawal of R150.00 from acc **9999 @ ABSA FRENDLY DURBANVIDU. Avail bal R10000.00 at 20:01 on 15May17. Reg: Monkeys need food";
    private static final String TEST_PURCHASE_MESSAGE = "Nedbank: R294.20 purchased @ Checkers Durbanville S from acc **9999. Avail bal R123123.33 at 08:55 on 10May16";

    private static final String ZA_NUMBER="0829999999";
    private static final String ZA_INT_NUMBER="+27829999999";
    private static final String ZA_FAKE_NUMBER="+2700000000000000";
    private static final String ZA_FAKE_NUMBER_TOLONG="+27000000000000000000";

    RegexParser reggy;
    MockContext mc;

    private static Logger logger = Logger.getLogger(RegexParserTest.class.getCanonicalName());

    @Before
    public void setup()
    {
        reggy = new RegexParser();
        mc = new MockContext();

    }

    @Test
    public void checkMessageNumber()
    {
        //this is valid should return true
        Assert.assertTrue(reggy.checkNumberValidility(ZA_NUMBER));
        Assert.assertTrue(reggy.checkNumberValidility(ZA_INT_NUMBER));
        Assert.assertFalse(reggy.checkNumberValidility(ZA_FAKE_NUMBER));
        Assert.assertFalse(reggy.checkNumberValidility(ZA_FAKE_NUMBER_TOLONG));
    }

    @Test
    public void checkMessageValidility() {
        RegexParser reggy = new RegexParser();
        Assert.assertTrue(reggy.checkMessageValidility(TEST_DEPOSITE_MESSAGE));
        Assert.assertTrue(reggy.checkMessageValidility(TEST_CASHWITHDRAWAL_MESSAGE));
        Assert.assertTrue(reggy.checkMessageValidility(TEST_PURCHASE_MESSAGE));
    }

}