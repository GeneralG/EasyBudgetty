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
public final class CategoriesContract extends BaseContract {

    public CategoriesContract()
    {}

    public HashMap<String,String> getColumns() {
        HashMap<String,String> columns = new HashMap<String,String>();

        columns.put(CategoriesEntry.COLUMN_ID, BaseContract.TYPE_INTEGER + " " + BaseContract.PRIMARY_KEY + " " + BaseContract.AUTOINCREMENT);
        columns.put(CategoriesEntry.COLUMN_NAME,BaseContract.TYPE_TEXT);

        return columns;
    }

    public String getTableName() {
        return CategoriesEntry.TABLE_NAME;
    }

    public int getID(DBManager db, String name)
    {
        Cursor c = db.getValues(new String[]{CategoriesEntry.COLUMN_ID},
                CategoriesEntry.COLUMN_NAME,
                DBHelper.DESC,
                CategoriesEntry.TABLE_NAME,
                CategoriesEntry.COLUMN_NAME,
                new String[]{name});

        c.moveToFirst();
        return c.getInt(0);
    }

    public ArrayList<String> getNames(DBManager db)
    {
        Cursor c = db.getValues(new String[]{CategoriesEntry.COLUMN_NAME}, CategoriesEntry.COLUMN_NAME, DBHelper.DESC, CategoriesEntry.TABLE_NAME);
        c.moveToFirst();
        ArrayList<String> categories = new ArrayList<String>();
        while (!c.isAfterLast())
        {
            categories.add(c.getString(0));
            c.moveToNext();
        }
        c.close();
        c = null;
        return categories;
    }

    @Override
    public ArrayList<Object> getEntries(DBManager db)
    {
        Cursor c = db.getValues(new String[]{CategoriesEntry.COLUMN_ID, CategoriesEntry.COLUMN_NAME}, CategoriesEntry.COLUMN_NAME, DBHelper.DESC, CategoriesEntry.TABLE_NAME,null,null);
        c.moveToFirst();

        ArrayList<Object> items = new ArrayList<>();
        while (!(c.isAfterLast()))
        {
            items.add(new CategoriesEntry(c.getInt(0), c.getString(1)));
        }
        return items;
    }

    @Override
    public Boolean addEntry(DBManager db, BaseColumns entry) {
        if (entry.getClass()== CategoriesEntry.class)
        {
            CategoriesEntry ce = (CategoriesEntry)entry;

            HashMap<String,String> values = new HashMap<String,String>();

            values.put(ce.COLUMN_NAME, ce.getName());

            db.createEntry(ce.TABLE_NAME, values);

            return true;
        }
        else
        {
            return null;
        }
    }

    public void setName(DBManager db, String fromName, String toName)
    {
        HashMap<String, String> values = new HashMap<String, String>();
        HashMap<String, String> where = new HashMap<String,String>();
        values.put(CategoriesEntry.COLUMN_NAME, toName);
        where.put(CategoriesEntry.COLUMN_NAME, fromName);
        db.updateEntry(CategoriesEntry.TABLE_NAME, where,values);
    }

    public void addName(DBManager db, String name)
    {
        HashMap<String, String> values = new HashMap<String, String>();
        values.put(CategoriesEntry.COLUMN_NAME,name);
        db.createEntry(CategoriesEntry.TABLE_NAME, values);
    }

    public class CategoriesEntry implements BaseColumns {
        public static final String TABLE_NAME = "Category";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "categoryName";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public CategoriesEntry(int id, String name)
        {
            this.setName(name);
            this.setId(id);
        }

        @Override
        public String toString()
        {
            return name;
        }
    }
}
