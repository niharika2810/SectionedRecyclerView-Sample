package involvio.checkinapplication.utility;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Utility class
 * Created by Niharika on 13-05-2018.
 */

public class Util {

    // Method to set date and time in format
    public static String getDateAndTime(CheckoutHistory section) {
        return section.getMonthName() + " " +
                section.getDate() + " - " + section.getDayName();
    }


    //Get time with am/pm marker
    public static String getFormattedTime(String date) {
        Log.d("Date", date);
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            Date parse = simpleDateFormat.parse(date);
            return new SimpleDateFormat("H:mm a", Locale.getDefault()).format(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //Get time with am/pm marker
    public static String getFormattedTimedddd(String date) {
        Log.d("Date", date);
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM dd, yyyy HH:mm:ss a", Locale.getDefault());
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            Date parse = simpleDateFormat.parse(date);
            return new SimpleDateFormat("H:mm a", Locale.getDefault()).format(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
