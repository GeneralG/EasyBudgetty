package za.co.easybudgetty.data;

import android.database.Cursor;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.HashMap;

import za.co.easybudgetty.data.helpers.DBHelper;
import za.co.easybudgetty.data.helpers.DBManager;

/**
 * Created by tyler on 2016/06/14.
 */
public final class DebitCreditorContract extends BaseContract {

    public DebitCreditorContract()
    {}

    public HashMap<String,String> getColumns() {
        HashMap<String,String> Columns = new HashMap<String,String>();

        Columns.put(DebitCreditorEntry.COLUMN_ID, BaseContract.TYPE_INTEGER + " " + BaseContract.PRIMARY_KEY + " " + BaseContract.AUTOINCREMENT);
        Columns.put(DebitCreditorEntry.COLUMN_DEBIT_CREDITOR_NAME, BaseContract.TYPE_TEXT);
        Columns.put(DebitCreditorEntry.COLUMN_DEFAULT_CATEGORY_ID, BaseContract.TYPE_INTEGER);

        return Columns;
    }

    @Override
    public ArrayList<Object> getEntries(DBManager db)
    {
        Cursor c = db.getValues(new String[]{DebitCreditorEntry.COLUMN_ID ,DebitCreditorEntry.COLUMN_DEBIT_CREDITOR_NAME, DebitCreditorEntry.COLUMN_DEFAULT_CATEGORY_ID},
                                    DebitCreditorContract.DebitCreditorEntry.COLUMN_DEBIT_CREDITOR_NAME , DBHelper.DESC, DebitCreditorEntry.TABLE_NAME,null,null);
        c.moveToFirst();
        ArrayList<Object> entries = new ArrayList<Object>();
        while (!c.isAfterLast())
        {
            entries.add(new DebitCreditorEntry(c.getString(0),c.getString(1),c.getString(2)));
            c.moveToNext();
        }
        c.close();
        c = null;
        return entries;
    }

    @Override
    public Boolean addEntry(DBManager db, BaseColumns entry) {

        if (entry.getClass()== DebitCreditorEntry.class) {
            return addEntry(db, (DebitCreditorEntry)entry);
        }
        else
        {
            return null;
        }
    }

    public Boolean addEntry(DBManager db, DebitCreditorEntry dce)
    {
        HashMap<String,String> values = new HashMap<String,String>();
        values.put(DebitCreditorEntry.COLUMN_DEBIT_CREDITOR_NAME, dce.getDebitCreditorName());
        values.put(DebitCreditorEntry.COLUMN_DEFAULT_CATEGORY_ID, dce.getDefaultCategoryId());
        values.put(DebitCreditorEntry.COLUMN_ID, dce.getId());
        db.createEntry(DebitCreditorEntry.TABLE_NAME, values);
        return true;
    }

    public String getTableName() {
        return DebitCreditorEntry.TABLE_NAME;
    }

    public class DebitCreditorEntry implements BaseColumns {
        public static final String TABLE_NAME = "DebitCreditor";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_DEBIT_CREDITOR_NAME = "DebitCreditorName";
        public static final String COLUMN_DEFAULT_CATEGORY_ID = "DefaultCategoryID";

        private String id;
        private String debitCreditorName;
        private String defaultCategoryId;

        public DebitCreditorEntry(String id, String debitCreditorName, String defaultCategoryId)
        {
            this.setId(id);
            this.setDebitCreditorName(debitCreditorName);
            this.setDefaultCategoryId(defaultCategoryId);
        }

        public String getDefaultCategoryId() {
            return defaultCategoryId;
        }

        public void setDefaultCategoryId(String defaultCategoryId) {
            this.defaultCategoryId = defaultCategoryId;
        }

        public String getDebitCreditorName() {
            return debitCreditorName;
        }

        public void setDebitCreditorName(String debitCreditorName) {
            this.debitCreditorName = debitCreditorName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString()
        {
            return debitCreditorName;
        }
    }
}
