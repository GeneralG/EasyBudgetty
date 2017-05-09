package za.co.easybudgetty;

import android.content.Intent;
import android.os.Bundle;
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

import za.co.easybudgetty.data.CategoriesContract;
import za.co.easybudgetty.data.helpers.DBManager;
import za.co.easybudgetty.menuHelpers.MainContentMenu;

public class Categories extends AppCompatActivity {

    public static final String TAG = Categories.class.getCanonicalName();

    View.OnClickListener btnAdd_OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DBManager db = new DBManager(view.getContext());
            CategoriesContract cc = new CategoriesContract();

            EditText et = (EditText)findViewById(R.id.txtAdd);
            if (!(et.getText().toString().trim().equals("")))
            {
                HashMap<String,String> values = new HashMap<String, String>();

                values.put(CategoriesContract.CategoriesEntry.COLUMN_NAME, et.getText().toString());

                db.createEntry(cc.getTableName(),values);
                bindDropDownCategories();
            }
        }
    };

    View.OnClickListener btnUpdate_onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DBManager db = new DBManager(view.getContext());
            CategoriesContract cc = new CategoriesContract();

            Spinner spn = (Spinner)findViewById(R.id.spnCategories);
            EditText et = (EditText)findViewById(R.id.txtUpdate);

            cc.setName(db,spn.getSelectedItem().toString(),et.getText().toString());
            bindDropDownCategories();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bindDropDownCategories();
        Button btn = (Button)findViewById(R.id.btnAdd);
        btn.setOnClickListener(btnAdd_OnClick);
        btn = (Button)findViewById(R.id.btnUpdate);
        btn.setOnClickListener(btnUpdate_onClick);
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

    private void bindDropDownCategories()
    {
        DBManager db = new DBManager(this.getApplicationContext());

        Spinner spn = (Spinner)findViewById(R.id.spnCategories);
        CategoriesContract cc = new CategoriesContract();

        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cc.getNames(db));
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(a);
    }
}
