package za.co.easybudgetty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import za.co.easybudgetty.data.adapters.BudgetItemsAdapter;
import za.co.easybudgetty.data.BudgetsContract;
import za.co.easybudgetty.menuHelpers.MainContentMenu;

import za.co.easybudgetty.data.helpers.DBManager;

//FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    public class MainActivity extends AppCompatActivity {

        public static final String TAG = MainActivity.class.getCanonicalName();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getString(R.string.app_name);
            DBManager db = new DBManager(this.getApplicationContext());
            db.onUpgrade(db.getWritableDatabase(),1,1);

            Log.d(TAG, "Initializing recycler view");


            RecyclerView recyclerView;
            recyclerView = (RecyclerView) findViewById(R.id.RecyclerMessages);
            BudgetsContract bc = new BudgetsContract();
            BudgetItemsAdapter mAdapter = new BudgetItemsAdapter(db);
            mAdapter = new BudgetItemsAdapter(db);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);


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
            //noinspection SimplifiableIfStatement
            Intent intent = MainContentMenu.onOptionsItemSelected(item,getIntent(),this);
            startActivity(intent);
            return super.onOptionsItemSelected(item);
        }
}
