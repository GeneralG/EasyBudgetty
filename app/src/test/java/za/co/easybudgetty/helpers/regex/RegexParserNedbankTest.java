package za.co.easybudgetty.helpers.regex;

import org.junit.Test;

/**
 * Created by tyler on 2016/06/26.
 */
public class RegexParserNedbankTest {

    private static final String TEST_DEPOSITE_MESSAGE = "Nedbank: Electronic Banking EFT deposit of R3000.00 into acc **9999 Avail bal R10000.00 at 20:01 on 01May17. Reg: Monkeys need food";
    private static final String TEST_CASHWITHDRAWAL_MESSAGE = "Nedbank: ATM Cash withdrawal of R150.00 from acc **9999 @ ABSA FRENDLY DURBANVIDU. Avail bal R10000.00 at 20:01 on 15May17. Reg: Monkeys need food";
    private static final String TEST_PURCHASE_MESSAGE = "Nedbank: R294.20 purchased @ Checkers Durbanville S from acc **9999. Avail bal R123123.33 at 08:55 on 10May16";

    private static final String ZA_NUMBER="0829999999";
    private static final String ZA_INT_NUMBER="+27829999999";
    private static final String ZA_FAKE_NUMBER="+2700000000000000";
    private static final String ZA_FAKE_NUMBER_TOLONG="+27000000000000000000";

    @Test
    public void checkNumberValidility()
    {
        RegexParser reggy = new RegexParser();
        assert(true ==(reggy.checkNumberValidility(ZA_NUMBER)));
        assert(true ==(reggy.checkNumberValidility(ZA_INT_NUMBER)));
        assert(true ==(reggy.checkNumberValidility(ZA_FAKE_NUMBER)));
        assert(true ==(reggy.checkNumberValidility(ZA_FAKE_NUMBER_TOLONG)));
    }

    @Test
    public void checkMessageValidility()
    {
        RegexParser reggy = new RegexParser();
        assert(true ==(reggy.checkMessageValidility(TEST_DEPOSITE_MESSAGE)));
        assert(true ==(reggy.checkMessageValidility(TEST_CASHWITHDRAWAL_MESSAGE)));
        assert(true ==(reggy.checkMessageValidility(TEST_PURCHASE_MESSAGE)));
    }
}
