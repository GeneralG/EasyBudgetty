package za.co.easybudgetty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import za.co.easybudgetty.data.BanksContract;
import za.co.easybudgetty.data.helpers.DBManager;
import za.co.easybudgetty.menuHelpers.MainContentMenu;

public class BankAccounts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_accounts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DBManager db = new DBManager(findViewById(R.id.spnBanks).getContext());
        BanksContract bc = new BanksContract();
        ArrayList<Object> banks = bc.getEntries(db);

        Spinner s = (Spinner) findViewById(R.id.spnBanks);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, banks);
        s.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent intent = MainContentMenu.onOptionsItemSelected(item,getIntent(),this);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    private void bindBankAccountsList()
    {

    }


}
