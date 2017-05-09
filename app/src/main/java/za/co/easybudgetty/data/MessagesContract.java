package za.co.easybudgetty.data;

import android.database.Cursor;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.HashMap;

import za.co.easybudgetty.data.helpers.DBHelper;
import za.co.easybudgetty.data.helpers.DBManager;

/**
 * Created by tyler on 2016/11/01.
 */

public class MessagesContract extends BaseContract {

    @Override
    public String getTableName() {
        return MessagesEntry.TABLE_NAME;
    }

    @Override
    public HashMap<String, String> getColumns() {
        HashMap<String, String> columns = new HashMap<String, String>();

        columns.put(MessagesEntry.COLUMN_ID, BaseContract.TYPE_INTEGER + " " + BaseContract.PRIMARY_KEY + " " + BaseContract.AUTOINCREMENT);
        columns.put(MessagesEntry.COLUMN_MESSAGE_NUMBER, BaseContract.TYPE_TEXT);
        columns.put(MessagesEntry.COLUMN_MESSAGE_TEXT, BaseContract.TYPE_TEXT);
        columns.put(MessagesEntry.COLUMN_AMOUNT, BaseContract.TYPE_NUMERIC);
        columns.put(MessagesEntry.COLUMN_DEBITORID, BaseContract.TYPE_INTEGER);
        columns.put(MessagesEntry.COLUMN_BUDGETID, BaseContract.TYPE_INTEGER);


        return columns;
    }

    @Override
    public ArrayList<Object> getEntries(DBManager db) {
        Cursor c = db.getValues(new String[]{MessagesEntry.COLUMN_ID, MessagesEntry.COLUMN_MESSAGE_NUMBER, MessagesEntry.COLUMN_MESSAGE_TEXT}, MessagesEntry.COLUMN_ID, DBHelper.DESC, MessagesEntry.TABLE_NAME,null,null);
        c.moveToFirst();

        ArrayList<Object> items = new ArrayList<>();
        while (!(c.isAfterLast()))
        {
            items.add(new MessagesContract.MessagesEntry(c.getInt(0), c.getString(1), c.getString(2)));
        }
        return items;
    }

    @Override
    public Boolean addEntry(DBManager db, BaseColumns entry) {
        if (entry.getClass()== CategoriesContract.CategoriesEntry.class)
        {
            MessagesEntry me = (MessagesEntry) entry;

            HashMap<String,String> values = new HashMap<String,String>();

            values.put(MessagesEntry.COLUMN_MESSAGE_TEXT, me.getText());
            values.put(MessagesEntry.COLUMN_MESSAGE_NUMBER, me.getNumber());

            db.createEntry(me.TABLE_NAME, values);

            return true;
        }
        else
        {
            return null;
        }
    }

    public class MessagesEntry implements BaseColumns {
        public static final String TABLE_NAME = "Messages";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_MESSAGE_TEXT = "MessageText";
        public static final String COLUMN_MESSAGE_NUMBER = "MessageNumber";
        //These values are for the automation to insert into. They will be updated when the user see's the message and allocates it to a budget.
        public static final String COLUMN_BUDGETID = "BudgetID"; //this is the budget ID that the system detected while performing regex.
        public static final String COLUMN_AMOUNT = "TransactionAmount"; //this is the automated, detected budget.
        public static final String COLUMN_DEBITORID = "Debitor"; //the detected debiter from the message.


        private String number;
        private String text;
        private int id;
        private double amount;
        private int debitorID;
        private int budgetID;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public MessagesEntry(int id, String number, String Text) {
            this.setNumber(number);
            this.setId(id);
            this.setText(Text);
        }

        @Override
        public String toString()
        {
            return text;
        }
    }
}
