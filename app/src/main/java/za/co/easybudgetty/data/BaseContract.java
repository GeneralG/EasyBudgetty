package za.co.easybudgetty.data;

import za.co.easybudgetty.data.helpers.DBHelper;
import za.co.easybudgetty.data.helpers.DBManager;
/**
 * Created by tyler on 2016/06/26.
 */
public abstract class BaseContract implements IBaseContract{

    public static final String TYPE_TEXT = "TEXT";
    public static final String TYPE_NUMERIC = "NUMERIC";
    public static final String TYPE_CHAR = "CHAR";
    public static final String TYPE_INTEGER = "INTEGER";
    public static final String AUTOINCREMENT = "AUTOINCREMENT";
    public static final String PRIMARY_KEY = "PRIMARY KEY";
    public static final String NOT_NULL = "NOT NULL";

    public static final String COMMA = " ,";
    public static final String SEMI_COLON = "; ";
    public static final String SPACE = " ";
    public static final String ID = "id";
}
