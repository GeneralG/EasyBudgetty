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
public final class BanksContract extends BaseContract{

    public BanksContract()
    {}

    public HashMap<String,String> getColumns()
    {
        HashMap<String,String> Columns = new HashMap<String,String>();

        Columns.put(BanksEntry.COLUMN_NAME_ID,BaseContract.TYPE_INTEGER + " " + BaseContract.PRIMARY_KEY + " " + BaseContract.AUTOINCREMENT + " ");
        Columns.put(BanksEntry.COLUMN_BANK_TEXT,BaseContract.TYPE_TEXT);//The text to search for in the SMS
        Columns.put(BanksEntry.COLUMN_BANK_NAME,BaseContract.TYPE_TEXT);//The Name of the bank displayed by the user

        return Columns;
    }

    public String getTableName()
    {
        return BanksEntry.TABLE_NAME;
    }

    @Override
    public ArrayList<Object> getEntries(DBManager db)
    {
        Cursor c = db.getValues(new String[]{BanksEntry.COLUMN_NAME_ID , BanksEntry.COLUMN_BANK_NAME, BanksEntry.COLUMN_BANK_TEXT},
                BanksEntry.COLUMN_NAME_ID , DBHelper.DESC, BanksEntry.TABLE_NAME);

        c.moveToFirst();
        ArrayList<Object> entries = new ArrayList<>();
        while (!c.isAfterLast())
        {
            //int id, String name, String text
            entries.add(new BanksEntry(c.getInt(0), c.getString(1), c.getString(2)));
            c.moveToNext();
        }
        c.close();
        c = null;
        return entries;
    }

    @Override
    public Boolean addEntry(DBManager db, BaseColumns entry) {
        if (entry.getClass() == BanksEntry.class)
        {
            BanksEntry be = (BanksEntry)entry;

            HashMap<String,String> values = new HashMap<String, String>();

            values.put(be.COLUMN_BANK_NAME,be.getName());
            values.put(be.COLUMN_BANK_TEXT,be.getText());

            db.createEntry(be.TABLE_NAME, values);

            return true;
        }
        else
        {
            return null;
        }
    }

    public class BanksEntry implements BaseColumns {
        public static final String TABLE_NAME = "Banks";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_BANK_NAME = "bankName";
        public static final String COLUMN_BANK_TEXT = "bankNameText";

        private int id;
        private String name;
        private String text;

        public BanksEntry(int id, String name, String text)
        {
            this.setId(id);
            this.setName(name);
            this.setText(text);
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
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
