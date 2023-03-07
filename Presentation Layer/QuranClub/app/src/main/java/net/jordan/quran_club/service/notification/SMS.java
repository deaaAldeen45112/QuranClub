package net.jordan.quran_club.service.notification;

import android.telephony.SmsManager;

import java.util.ArrayList;

public class SMS extends IMessage{
    @Override
    protected void send(String message) {
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> parts = smsManager.divideMessage(message);
        smsManager.sendMultipartTextMessage(getTo(), null, parts, null, null);
        }
}
