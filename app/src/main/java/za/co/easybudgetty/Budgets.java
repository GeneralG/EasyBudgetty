package za.co.easybudgetty;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;

import za.co.easybudgetty.data.BudgetsContract;
import za.co.easybudgetty.data.CategoriesContract;
import za.co.easybudgetty.data.helpers.DBManager;
import za.co.easybudgetty.menuHelpers.MainContentMenu;

public class Budgets extends AppCompatActivity {

    private static final String TAG = Budgets.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budgets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button btn = (Button)findViewById(R.id.btnAdd);
        btn.setOnClickListener(btnAdd_OnClick);
        Button btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(btnUpdate_OnClick);
    }

    View.OnClickListener btnAdd_OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DBManager db = new DBManager(view.getContext());
            BudgetsContract bc = new BudgetsContract();

            EditText edtAddName = (EditText)findViewById(R.id.edtAdd);
            EditText edtAddAmount = (EditText)findViewById(R.id.edtAmount);
            if (!((edtAddAmount.getText().toString().trim().equals("")) & (edtAddName.getText().toString().equals(""))))
            {
                HashMap<String,String> values = new HashMap<String, String>();

                values.put(BudgetsContract.BudgetEntry.COLUMN_NAME, edtAddName.getText().toString());
                values.put(BudgetsContract.BudgetEntry.COLUMN_AMOUNT, edtAddAmount.getText().toString());

                db.createEntry(bc.getTableName(),values);
                bindDropDownCategories();
            }
        }
    };

    View.OnClickListener btnUpdate_OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DBManager db = new DBManager(view.getContext());
            BudgetsContract bc = new BudgetsContract();

            EditText editAddName = (EditText)findViewById(R.id.edtAdd);
            EditText edtUpdateName = (EditText)findViewById(R.id.edtUdpate);
            EditText edtAddAmount = (EditText)findViewById(R.id.edtAmount);

            Log.d(TAG, "OnClick Update Budgets about to check");

            Log.d(TAG, "Values to be checked on update");
            Log.d(TAG, "Amount update value: " + edtAddAmount.getText().toString());
            Log.d(TAG, "Name update value: " + edtUpdateName.getText().toString());

            if (!((edtAddAmount.getText().toString().trim().equals("")) & (edtUpdateName.getText().toString().equals(""))))
            {
                HashMap<String,String> values = new HashMap<String, String>();

                values.put(BudgetsContract.BudgetEntry.COLUMN_NAME, edtUpdateName.getText().toString());
                values.put(BudgetsContract.BudgetEntry.COLUMN_AMOUNT, edtAddAmount.getText().toString());

                HashMap<String,String> where = new HashMap<String,String>();

                where.put(BudgetsContract.BudgetEntry.COLUMN_NAME, editAddName.getText().toString());

                db.updateEntry(bc.getTableName(),values,where);
                bindDropDownCategories();
            }
        }
    };


    private void bindDropDownCategories()
    {
        DBManager db = new DBManager(this.getApplicationContext());

        Spinner spn = (Spinner)findViewById(R.id.spnBudgets);
        BudgetsContract cc = new BudgetsContract();

        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cc.getNames(db));
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(a);
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
        //noinspection SimplifiableIfStatement
        Intent intent = MainContentMenu.onOptionsItemSelected(item,getIntent(),this);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}