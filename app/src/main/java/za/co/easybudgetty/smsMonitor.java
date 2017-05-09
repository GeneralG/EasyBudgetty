package za.co.easybudgetty;

import android.app.IntentService;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import za.co.easybudgetty.SMSProcessing.Processor;
import za.co.easybudgetty.data.helpers.*;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class smsMonitor extends IntentService {

    public static final String ACTION_START = "za.co.easybudgetty.action.START";
    public static final String ACTION_STOP = "za.co.easybudgetty.action.STOP";

    private za.co.easybudgetty.SMSProcessing.Processor processor;
    public smsMonitor() {
        super("smsMonitor");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_START.equals(action)) {
                handleActionStart();
            } else if (ACTION_STOP.equals(action)) {
                handleActionStop();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionStart() {
        DBManager dbManager = new DBManager(this.getApplicationContext());
        SQLiteDatabase db = dbManager.getWritableDatabase();


        processor = new Processor();
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionStop() {
        processor.abortBroadcast();
        processor = null;
    }
}
