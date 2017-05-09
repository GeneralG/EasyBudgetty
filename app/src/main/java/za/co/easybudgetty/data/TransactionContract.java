package za.co.easybudgetty.data;

import android.database.Cursor;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.HashMap;

import za.co.easybudgetty.data.helpers.DBHelper;
import za.co.easybudgetty.data.helpers.DBManager;

/**
 * Created by tyler on 2016/06/26.
 */
public final class TransactionContract extends BaseContract {

    public TransactionContract()
    {}

    public HashMap<String,String> getColumns()
    {
        HashMap<String,String> columns = new HashMap<String,String>();

        columns.put(TransactionsEntry.COLUMN_ID, BaseContract.TYPE_INTEGER + " " + BaseContract.PRIMARY_KEY + " " + BaseContract.AUTOINCREMENT);
        columns.put(TransactionsEntry.COLUMN_BANK_ID, BaseContract.TYPE_INTEGER);
        columns.put(TransactionsEntry.COLUMN_BUDGET_ID, BaseContract.TYPE_INTEGER);
        columns.put(TransactionsEntry.COLUMN_AMOUNT, BaseContract.TYPE_NUMERIC);
        columns.put(TransactionsEntry.COLUMN_CURRENCY, BaseContract.TYPE_CHAR);

        return columns;
    }

    @Override
    public ArrayList<Object> getEntries(DBManager db)
    {
        Cursor c = db.getValues(new String[]{TransactionsEntry.COLUMN_ID , TransactionsEntry.COLUMN_BANK_ID,  TransactionsEntry.COLUMN_BUDGET_ID, TransactionsEntry.COLUMN_AMOUNT, TransactionsEntry.COLUMN_CURRENCY},
                TransactionsEntry.COLUMN_ID , DBHelper.DESC, TransactionsEntry.TABLE_NAME,null,null);
        c.moveToFirst();
        ArrayList<Object> entries = new ArrayList<Object>();
        while (!c.isAfterLast())
        {
            //(int id, int bankId, int categoryID, double amount, String currency)
            entries.add(new TransactionsEntry(c.getInt(0),c.getInt(1), c.getInt(2), c.getDouble(3), c.getString(4)));
            c.moveToNext();
        }
        c.close();
        c = null;
        return entries;
    }

    @Override
    public Boolean addEntry(DBManager db, BaseColumns entry) {
        if (entry.getClass() == TransactionsEntry.class)
        {
            return addEntry(db, (TransactionsEntry)entry);
        }
        else
        {
            return null;
        }
    }

    public Boolean addEntry(DBManager db, TransactionsEntry te)
    {
        HashMap<String,String> values = new HashMap<String, String>();

        values.put(TransactionsEntry.COLUMN_ID, String.valueOf(te.getId()));
        values.put(TransactionsEntry.COLUMN_AMOUNT, String.valueOf(te.getAmount()));
        values.put(TransactionsEntry.COLUMN_BANK_ID, String.valueOf(te.getBankId()));
        values.put(TransactionsEntry.COLUMN_BUDGET_ID, String.valueOf(te.getBudgetId()));
        values.put(TransactionsEntry.COLUMN_CURRENCY, String.valueOf(te.getCurrency()));

        db.createEntry(TransactionsEntry.TABLE_NAME, values);
        return true;
    }

    public String getTableName() {
        return TransactionsEntry.TABLE_NAME;
    }

     public class TransactionsEntry implements BaseColumns {
        public static final String TABLE_NAME = "Transactions";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_CURRENCY = "Currency";
        public static final String COLUMN_BANK_ID = "BankID";
        //public static final String COLUMN_CATEGORY_ID = "CategoryID";
        public static final String COLUMN_AMOUNT = "Amount";
        public static final String COLUMN_BUDGET_ID = "BudgetID";

         private int id;
         private int bankId;
         private int categoryID;
         private double amount;
         private String currency;
         private int budgetId;

         public String getCurrency() {
             return currency;
         }

         public void setCurrency(String currency) {
             this.currency = currency;
         }

         public int getId() {
             return id;
         }

         public void setId(int id) {
             this.id = id;
         }

         public int getBankId() {
             return bankId;
         }

         public void setBankId(int bankId) {
             this.bankId = bankId;
         }

         public void setBudgetId(int budgetId)
         {
             this.budgetId = budgetId;
         }

         public int getBudgetId()
         {
             return this.budgetId;
         }

         public double getAmount() {
             return amount;
         }

         public void setAmount(double amount) {
             this.amount = amount;
         }

         public TransactionsEntry(int id, int bankId, int budgetId, double amount, String currency)
         {
             this.setId(id);
             this.setAmount(amount);
             this.setBankId(bankId);
             this.setBudgetId(budgetId);
             this.setCurrency(currency);
         }

         @Override
         public String toString()
         {
             return currency + " " + String.valueOf(amount);
         }
    }
}
