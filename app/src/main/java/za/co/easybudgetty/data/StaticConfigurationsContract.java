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
public final class StaticConfigurationsContract extends BaseContract {

    public StaticConfigurationsContract()
    {}

    public HashMap<String,String> getColumns() {
        HashMap<String,String> columnNames = new HashMap<String,String>();

        columnNames.put(StaticConfigurationEntry.COLUMN_NAME_ID, BaseContract.TYPE_INTEGER + " " + BaseContract.PRIMARY_KEY + " " + BaseContract.AUTOINCREMENT);
        columnNames.put(StaticConfigurationEntry.COLUMN_CONFIGURATION_NAME, BaseContract.TYPE_TEXT);
        columnNames.put(StaticConfigurationEntry.COLUMN_CONFIGURATION_VALUE, BaseContract.TYPE_TEXT);

        return columnNames;
    }

    public String getTableName() {
        return StaticConfigurationEntry.TABLE_NAME;
    }

    @Override
    public ArrayList<Object> getEntries(DBManager db)
    {
        Cursor c = db.getValues(new String[]{StaticConfigurationEntry.COLUMN_NAME_ID ,StaticConfigurationEntry.COLUMN_CONFIGURATION_NAME, StaticConfigurationEntry.COLUMN_CONFIGURATION_VALUE},
                StaticConfigurationEntry.COLUMN_CONFIGURATION_NAME , DBHelper.DESC, StaticConfigurationEntry.TABLE_NAME,null,null);
        c.moveToFirst();
        ArrayList<Object> entries = new ArrayList<Object>();
        while (!c.isAfterLast())
        {
            entries.add(new StaticConfigurationEntry(c.getInt(0),c.getString(1),c.getString(2)));
            c.moveToNext();
        }
        c.close();
        c = null;
        return entries;
    }

    @Override
    public Boolean addEntry(DBManager db, BaseColumns entry) {
        if (StaticConfigurationEntry.class == entry.getClass())
        {
            return addEntry(db,(StaticConfigurationEntry)entry);
        }
        else
        {
            return null;
        }
    }

    public Boolean addEntry(DBManager db, StaticConfigurationEntry entry)
    {
        StaticConfigurationEntry sce = entry;

        HashMap<String,String> values = new HashMap<String, String>();

        values.put(StaticConfigurationEntry.COLUMN_CONFIGURATION_NAME , sce.getConfigurationName());
        values.put(StaticConfigurationEntry.COLUMN_NAME_ID , String.valueOf(sce.getId()));
        values.put(StaticConfigurationEntry.COLUMN_CONFIGURATION_VALUE , String.valueOf(sce.getConfigurationValue()));

        db.createEntry(StaticConfigurationEntry.TABLE_NAME, values);
        return true;
    }


    public class StaticConfigurationEntry implements BaseColumns {
        public static final String TABLE_NAME = "StaticConfigurationsContract";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_CONFIGURATION_NAME = "ConfigurationsName";
        public static final String COLUMN_CONFIGURATION_VALUE = "ConfigurationsValue";

        private int id;
        private String configurationName;
        private String configurationValue;

        public StaticConfigurationEntry(int id, String configurationName, String configurationValue)
        {
            this.setId(id);
            this.setConfigurationName(configurationName);
            this.setConfigurationValue(configurationValue);
        }

        public String getConfigurationValue() {
            return configurationValue;
        }

        public void setConfigurationValue(String configurationValue) {
            this.configurationValue = configurationValue;
        }

        public String getConfigurationName() {
            return configurationName;
        }

        public void setConfigurationName(String configurationName) {
            this.configurationName = configurationName;
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
            return configurationName;
        }
    }
}
