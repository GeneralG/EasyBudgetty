package za.co.easybudgetty.SMSProcessing;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import za.co.easybudgetty.helpers.regex.regexParser;

/**
 * Created by tyler on 2016/05/12.
 */
public class Processor extends BroadcastReceiver {
    private static final String TAG = "SMSApp";

    static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
            StringBuilder buf = new StringBuilder();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                SmsMessage[] messages;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
                    Log.i(TAG, "onReceive: " + String.valueOf(messages.length));
                    regexParser.checkMessageValidility(messages[0].getMessageBody());
                }
                else
                {
                    try {

                        if (bundle != null) {

                            final Object[] pdusObj = (Object[]) bundle.get("pdus");

                            for (int i = 0; i < pdusObj.length; i++) {
                                SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                                String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                                Log.i(TAG, "onReceive: BELOW KIT KAT");
                                String senderNum = phoneNumber;
                                String message = currentMessage.getDisplayMessageBody();
                                regexParser.checkMessageValidility(message);
                                Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);
                            } // end for loop
                        }
                    } catch (Exception e) {
                        Log.e("SmsReceiver", "Exception smsReceiver" +e);

                    }
                }

            }
            Log.i(TAG, "[SMSApp] onReceiveIntent: " + buf);
            NotificationManager nm = (NotificationManager) context.getSystemService(
                    Context.NOTIFICATION_SERVICE);
        }
    }
}
