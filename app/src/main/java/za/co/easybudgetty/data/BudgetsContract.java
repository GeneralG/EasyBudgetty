package za.co.easybudgetty.data;

import android.database.Cursor;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import za.co.easybudgetty.data.helpers.DBHelper;
import za.co.easybudgetty.data.helpers.DBManager;

/**
 * Created by tyler on 2016/07/13.
 */
public class BudgetsContract extends BaseContract{

    private static final String TAG = BudgetsContract.class.getCanonicalName();
    public BudgetsContract()
    {}

    @Override
    public Boolean addEntry(DBManager db, BaseColumns entry) {
        if (BudgetEntry.class == entry.getClass())
        {
            return addEntry(db, (BudgetEntry)entry);
        }
        else
        {
            return null;
        }
    }

    public Boolean addEntry(DBManager db, BudgetEntry be)
    {
        HashMap<String,String> values = new HashMap<String, String>();

        values.put(be.COLUMN_NAME,be.getName());
        values.put(be.COLUMN_CATEGORY_ID,String.valueOf(be.getCategroyID()));
        values.put(be.COLUMN_AMOUNT,String.valueOf(be.getAmount()));

        db.createEntry(be.TABLE_NAME, values);
        return true;
    }

    public double getSummedTransactions(int budgetId, DBManager db)
    {
        Log.d(TAG,"Calculating the sum of budget ID " + String.valueOf(budgetId));
        Cursor c = db.getValues(new String[]{"SUM("+TransactionContract.TransactionsEntry.COLUMN_AMOUNT+") AS Total"},
                TransactionContract.TransactionsEntry.COLUMN_BUDGET_ID,
                DBHelper.DESC,
                TransactionContract.TransactionsEntry.TABLE_NAME,
                TransactionContract.TransactionsEntry.COLUMN_BUDGET_ID + "= ?",
                new String[]{String.valueOf(budgetId)}
        );
        c.moveToFirst();

        Double total = 0d;

        if (c.getCount() >= 1) {
            total = c.getDouble(0);
        }

        c.close();
        c = null;

        return total;
    }

    public List<String> getNames(DBManager db)
    {
        ArrayList<String> names = new ArrayList<String>();

        Cursor c = db.getValues(new String[]{BudgetEntry.COLUMN_NAME}, BudgetEntry.COLUMN_NAME, DBHelper.DESC, BudgetEntry.TABLE_NAME, null,null);
        c.moveToFirst();
        while (!(c.isAfterLast()))
        {
            names.add(String.valueOf(c.getString(0)));
            c.moveToNext();
        }
        return names;
    }



    @Override
    public String getTableName() {
        return BudgetEntry.TABLE_NAME;
    }

    @Override
    public HashMap<String, String> getColumns() {
        HashMap<String, String>columns = new HashMap<String,String>();

        columns.put(BudgetEntry.COLUMN_ID, BaseContract.TYPE_INTEGER + " " + BaseContract.PRIMARY_KEY + " " + BaseContract.AUTOINCREMENT);
        columns.put(BudgetEntry.COLUMN_AMOUNT, BaseContract.TYPE_NUMERIC);
        columns.put(BudgetEntry.COLUMN_CATEGORY_ID, BaseContract.TYPE_INTEGER);
        columns.put(BudgetEntry.COLUMN_NAME, BaseContract.TYPE_TEXT);

        return columns;
    }

    @Override
    public ArrayList<Object> getEntries(DBManager db) {
        Cursor c = db.getValues(new String[]{BudgetEntry.COLUMN_ID,
                BudgetEntry.COLUMN_NAME,
                BudgetEntry.COLUMN_CATEGORY_ID,
                BudgetEntry.COLUMN_AMOUNT}, BudgetEntry.COLUMN_NAME, DBHelper.DESC, BudgetEntry.TABLE_NAME,null,null);

        ArrayList<Object> items = new ArrayList<>();
        if (c.getCount() >= 1 )
        {
            c.moveToFirst();
            while (!(c.isAfterLast()))
            {
                items.add(new BudgetEntry(c.getInt(0), c.getString(1), c.getInt(2), c.getDouble(3), this.getSummedTransactions(c.getInt(0), db)));
            }
        }
        c.close();
        c = null;
        return items;
    }

    public ArrayList<BudgetEntry> getEntriesAsBudgetEntry(DBManager db) {
        Cursor c = db.getValues(new String[]{BudgetEntry.COLUMN_ID,
                BudgetEntry.COLUMN_NAME,
                BudgetEntry.COLUMN_CATEGORY_ID,
                BudgetEntry.COLUMN_AMOUNT}, BudgetEntry.COLUMN_NAME, DBHelper.DESC, BudgetEntry.TABLE_NAME);

        ArrayList<BudgetEntry> items = new ArrayList<>();
        Log.d(TAG, "Number of rows(cursor . count): " + String.valueOf(c.getCount()));
        if (c.getCount() >= 1 )
        {
            c.moveToFirst();
            while (!(c.isAfterLast())) {
                items.add(new BudgetEntry(c.getInt(0), c.getString(1), c.getInt(2), c.getDouble(3), this.getSummedTransactions(c.getInt(0), db)));
                c.moveToNext();
            }
        }
        c.close();
        c = null;
        return items;
    }

    public class BudgetEntry implements BaseColumns{

        public static final String TABLE_NAME = "Budgets";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_CATEGORY_ID = "categoryID";

        private int id;
        private String name;
        private int categroyID;
        private double amount;

        public double getTransactionsSummed() {
            return transactionsSummed;
        }

        public void setTransactionsSummed(double transactionsSummed) {
            this.transactionsSummed = transactionsSummed;
        }

        private double transactionsSummed;

        public BudgetEntry(int id, String name, int categroyID, double amount, double transactionsSummed)
        {
            this.setAmount(amount);
            this.setId(id);
            this.setCategroyID(categroyID);
            this.setName(name);
            this.setTransactionsSummed(transactionsSummed);
        }

        public int getCategroyID() {
            return categroyID;
        }

        public void setCategroyID(int categroyID) {
            this.categroyID = categroyID;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString()
        {
            return name;
        }
    }
}
