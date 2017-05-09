package za.co.easybudgetty.data.helpers;

import android.util.Log;

import java.util.HashMap;

import za.co.easybudgetty.data.BaseContract;

/**
 * Created by tyler on 2016/06/26.
 */
public class DBHelper {

    private static final String TAG = DBHelper.class.getCanonicalName();

    public static final String DESC = "DESC";
    public static final String ASC = "";

    public static String CreateTable(String TableName, HashMap<String,String> Columns)
    {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("CREATE TABLE " + TableName + " (");

        //Loop to create more of the query by appending columnns name and types
        boolean isFirstLoop = true;
        for (String s: Columns.keySet())
        {
            if (isFirstLoop) {
                sbQuery.append(s + BaseContract.SPACE + String.valueOf(Columns.get(s)));
                isFirstLoop = false;
            }
            else
                sbQuery.append(BaseContract.COMMA + s + BaseContract.SPACE+ String.valueOf(Columns.get(s)));

        }
        sbQuery.append(" )"+ BaseContract.SEMI_COLON);
        Log.d(TAG, "CreateTable: "+ sbQuery.toString());
        return sbQuery.toString();
    }

    public static String CreateEntry(String TableName, HashMap<String,String> Values)
    {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("INSERT INTO " + TableName + " (");

        StringBuilder sbValues = new StringBuilder();

        //Use one loop to populate 2 string builders
            //one string builder to append to the primary with column names
            //two string builder to append to the secondary sb with values

        boolean isFirstLoop = true;
        for (String s: Values.keySet())
        {
            if (isFirstLoop) {
                sbQuery.append(s + BaseContract.SPACE);
                sbValues.append("\"" + String.valueOf(Values.get(s)) + "\"");
                isFirstLoop = false;
            }
            else
            {
                sbQuery.append(BaseContract.COMMA + s + BaseContract.SPACE);
                sbValues.append(BaseContract.COMMA + "\"" + String.valueOf(Values.get(s)) + "\"");
            }
        }

        sbQuery.append(") VALUES (");
        sbQuery.append(sbValues.toString());
        sbQuery.append(")" + BaseContract.SEMI_COLON);

        Log.d(TAG, "CreateEntry: " + sbQuery.toString());

        return sbQuery.toString();
    }

    public static String UpdateEntry(String TableName, int ID, HashMap<String, String> Values)
    {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append("UPDATE " + TableName + " SET ");

        for (String s: Values.keySet())
        {
            sbQuery.append(s + "=" + Values.get(s) + BaseContract.COMMA);
        }

        sbQuery.append("WHERE " + BaseContract.ID + " = " + String.valueOf(ID) + BaseContract.SEMI_COLON);
        Log.d(TAG, sbQuery.toString());
        return sbQuery.toString();
    }

    public static String UpdateEntry(String TableName, HashMap<String,String> Where, HashMap<String, String> Values)
    {
        StringBuilder sbQuery = new StringBuilder();
        StringBuilder sbWhere = new StringBuilder();
        Boolean firstLoop = true;

        sbQuery.append("UPDATE " + TableName + " SET ");
        for (String s: Values.keySet())
        {
            if (firstLoop)
            {
                sbQuery.append(s + " = \'" + String.valueOf(Values.get(s)) + "\'");
                firstLoop = false;
            }
            else
            {
                sbQuery.append(BaseContract.COMMA + s + " = \'" + String.valueOf(Values.get(s))+"\'");
            }
        }

        firstLoop = true;

        sbWhere.append(" WHERE ");
        for (String s: Where.keySet())
        {
            if (firstLoop)
            {
                sbWhere.append("(" + s + " = \'" + String.valueOf(Where.get(s)) + "\')");
                firstLoop = false;
            }
            else
            {
                sbWhere.append(" AND " + "(" + s + " = \'" + String.valueOf(Where.get(s)) + "\')");
                sbQuery.append(BaseContract.COMMA + s + " = \'" + Values.get(s)+"\'");
            }


        }

        sbQuery.append(sbWhere);

        Log.d(TAG,sbQuery.toString());
        return sbQuery.toString();
    }

    public static String DeleteEntry(String TableName, int ID)
    {
        Log.d(TAG, "DeleteEntry: " + "DELETE " + TableName + " WHERE ID = " + String.valueOf(ID) + BaseContract.SEMI_COLON);
        return "DELETE " + TableName + " WHERE ID = " + String.valueOf(ID) + BaseContract.SEMI_COLON;
    }

    public static String DropTable(String TableName)
    {
        Log.d(TAG, "Drop Table: " + "DROP TABLE IF EXISTS " + TableName + BaseContract.SEMI_COLON);
        return "DROP TABLE IF EXISTS " + TableName + BaseContract.SEMI_COLON;
    }
}
