package za.co.easybudgetty.menuHelpers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import za.co.easybudgetty.Budgets;
import za.co.easybudgetty.MainActivity;
import za.co.easybudgetty.Messages;
import za.co.easybudgetty.R;
import za.co.easybudgetty.BankAccounts;
import za.co.easybudgetty.Categories;
import za.co.easybudgetty.DebitCreditors;

/**
 * Created by tyler on 2016/11/30.
 */

public class MainContentMenu {

    public static final String TAG = MainContentMenu.class.getCanonicalName();

    //TODO investigate for possible memory leak
    public static Intent onOptionsItemSelected(MenuItem item, Intent intent, AppCompatActivity aca) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        switch (id) {
            case R.id.mnBanks:
                intent = new Intent(aca, BankAccounts.class);
                return intent;
            case R.id.mnCategories:
                intent = new Intent(aca, Categories.class);
                return intent;
            case R.id.mnDebitCreditors:
                intent = new Intent(aca, DebitCreditors.class);
                return intent;
            case R.id.mnMainActivity:
                intent = new Intent(aca, MainActivity.class);
                return intent;
            case R.id.mnBudgets:
                intent = new Intent(aca, Budgets.class);
                return intent;
            case R.id.mnMessages:
                intent = new Intent(aca, Messages.class);
                return intent;
        }
        return null;
    }
}
