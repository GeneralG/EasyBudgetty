package za.co.easybudgetty.data;

import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.HashMap;

import za.co.easybudgetty.data.helpers.DBManager;

/**
 * Created by tyler on 2016/06/27.
 */
public interface IBaseContract {
     String getTableName();
     HashMap<String,String> getColumns();
     ArrayList<Object> getEntries(DBManager db);
     Boolean addEntry(DBManager db, BaseColumns entry);

}
