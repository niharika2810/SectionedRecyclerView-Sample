package involvio.checkinapplication.utility;

import android.content.Context;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import involvio.checkinapplication.R;
import involvio.checkinapplication.model.Event;
import involvio.checkinapplication.model.Order;

/**
 * Utility class
 * Created by Niharika on 13-05-2018.
 */

public class Util {


    private static Util util;

    private Util() {
    }

    private static void createInstance() {
        if (util == null)
            util = new Util();
    }

    public static Util getInstance() {
        if (util == null)
            createInstance();
        return util;
    }
    /**
     * Get time with am/pm marker
     *
     * @param date -Date and time on which event done
     * @return formatted time string
     */
    public String getFormattedTime(String date) {
        try {

            Date parse = this.getSimpleDateFormat().parse(date);
            return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String formatCheckoutTime(Date date) {
        return this.getSimpleDateFormat().format(date);
    }

    public SimpleDateFormat getSimpleDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat;
    }
}
