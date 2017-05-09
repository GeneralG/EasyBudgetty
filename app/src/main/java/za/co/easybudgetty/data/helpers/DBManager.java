package za.co.easybudgetty.data.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Paint;
import android.util.Log;

import java.util.HashMap;

import za.co.easybudgetty.R;
import za.co.easybudgetty.data.BanksContract;
import za.co.easybudgetty.data.BudgetsContract;
import za.co.easybudgetty.data.CategoriesContract;
import za.co.easybudgetty.data.DebitCreditorContract;
import za.co.easybudgetty.data.StaticConfigurationsContract;
import za.co.easybudgetty.data.TransactionContract;

/**
 * Created by tyler on 2016/06/26.
 */
public class DBManager extends SQLiteOpenHelper{

    private String TAG = this.getClass().getCanonicalName();
    public static final int DATABASEVERSION=1;
    public static final String DatabaseName="Transactions.db";
    private Context initializedContext;

    public DBManager(Context context)
    {
        super(context, DatabaseName, null, DATABASEVERSION);
        initializedContext = context;
    }

    public void createEntry(String tableName, HashMap<String, String> values)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DBHelper.CreateEntry(tableName, values));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            //Comprae the database version with the requested database version in the source
            // if they differ re-initialize the database
            Log.d(TAG, "The current databae version is: " + this.getReadableDatabase().getVersion());
            if (this.getReadableDatabase().getVersion() != DATABASEVERSION)
            {

                BanksContract bc = new BanksContract();
                CategoriesContract cc = new CategoriesContract();
                DebitCreditorContract dcc = new DebitCreditorContract();
                StaticConfigurationsContract scc = new StaticConfigurationsContract();
                TransactionContract tc = new TransactionContract();
                BudgetsContract bdgtc = new BudgetsContract();

                //Initialization of the database tables
                db.execSQL(DBHelper.CreateTable(bc.getTableName(), bc.getColumns()));
                db.execSQL(DBHelper.CreateTable(cc.getTableName(), cc.getColumns()));
                db.execSQL(DBHelper.CreateTable(dcc.getTableName(), dcc.getColumns()));
                db.execSQL(DBHelper.CreateTable(scc.getTableName(), scc.getColumns()));
                db.execSQL(DBHelper.CreateTable(tc.getTableName(), tc.getColumns()));
                db.execSQL(DBHelper.CreateTable(bdgtc.getTableName(), bdgtc.getColumns()));

                //Initilization of the data
                //Adding categories to the database
                //The categories of the transaction
                HashMap<String, String> values;
                String[] categories = initializedContext.getResources().getStringArray(R.array.Categories);
                for (String s : categories) {
                    values = new HashMap<String, String>();
                    values.put(CategoriesContract.CategoriesEntry.COLUMN_NAME, s);
                    db.execSQL(DBHelper.CreateEntry(cc.getTableName(), values));
                }
                categories = null;

                //Adding banks to the database
                String[] banks = initializedContext.getResources().getStringArray(R.array.BankNames);
                for (String s : banks) {
                    values = new HashMap<String, String>();

                    values.put(BanksContract.BanksEntry.COLUMN_BANK_NAME, s);
                    values.put(BanksContract.BanksEntry.COLUMN_BANK_TEXT, s);

                    db.execSQL(DBHelper.CreateEntry(bc.getTableName(), values));
                }
                banks = null;

                //Adding Debit creditors to the database
                String[] debitCreditors = initializedContext.getResources().getStringArray(R.array.Categories);
                for (String s : debitCreditors) {
                    values = new HashMap<String, String>();

                    values.put(DebitCreditorContract.DebitCreditorEntry.COLUMN_DEBIT_CREDITOR_NAME, s);

                    db.execSQL(DBHelper.CreateEntry(dcc.getTableName(), values));
                }
                debitCreditors = null;
            }

        }
        catch (Exception ex)
        {
            Log.e(TAG, "onCreate: ", ex);
        }
    }

    public Cursor getValues(String[] columns, String columnToSortOn, String sortOrder, String tableName, String whereColumns, String[] whereValues)
    {

        // Define a projection that specifies which columns from the database
        Log.d(TAG, "SELECT " + tableName + " WHERE " + whereColumns + " VALUES " + android.text.TextUtils.join(",",whereValues));
        Log.d(TAG, "COLUMNS " + android.text.TextUtils.join(",",columns));

        Cursor c = this.getReadableDatabase().query(
                tableName,  // The table to query
                columns,                               // The columns to return
                whereColumns,                                // The columns for the WHERE clause
                whereValues,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        return c;
    }

    public Cursor getValues(String[] columns, String columnToSortOn, String sortOrder, String tableName)
    {

        // Define a projection that specifies which columns from the database
        String[] projection = columns;
        Log.d(TAG, "SELECT " + tableName);

        Cursor c = this.getReadableDatabase().query(
                tableName,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                columnToSortOn + " " + sortOrder                                 // The sort order
        );

        return c;
    }

    public void updateEntry(String tableName, int ID, HashMap<String, String> values)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DBHelper.UpdateEntry(tableName,ID,values));
    }

    public void updateEntry(String tableName, HashMap<String, String> values, HashMap<String, String> where)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DBHelper.UpdateEntry(tableName, where, values));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(DBHelper.DropTable(BanksContract.BanksEntry.TABLE_NAME));
            db.execSQL(DBHelper.DropTable(CategoriesContract.CategoriesEntry.TABLE_NAME));
            db.execSQL(DBHelper.DropTable(DebitCreditorContract.DebitCreditorEntry.TABLE_NAME));
            db.execSQL(DBHelper.DropTable(StaticConfigurationsContract.StaticConfigurationEntry.TABLE_NAME));
            db.execSQL(DBHelper.DropTable(TransactionContract.TransactionsEntry.TABLE_NAME));
            db.execSQL(DBHelper.DropTable(BudgetsContract.BudgetEntry.TABLE_NAME));
        }
        else
        this.onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db,oldVersion,newVersion);
    }
}
