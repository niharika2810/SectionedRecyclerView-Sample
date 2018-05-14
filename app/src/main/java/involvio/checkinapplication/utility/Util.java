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


    /**
     * @param section -Particular header
     * @return Date and Week day in proper format
     */
    // Method to set date and time in format
    public static String getDateAndTime(CheckoutHistory section) {
        return section.getMonthName() + " " +
                section.getDate() + " - " + section.getDayName();
    }


    /**
     * Get time with am/pm marker
     *
     * @param date -Date and time on which event done
     * @return formatted time string
     */
    public static String getFormattedTime(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            Date parse = simpleDateFormat.parse(date);
            return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(parse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Method to return Time for check in and Checkout in Format
     *
     * @param context      -Current this
     * @param checkInTime  -when event created
     * @param checkoutTime -Even checkout time
     * @return Formatted string for Check In as well as Checkout time
     */
    public static String getCheckInCheckoutTimeInFormat(Context context, String checkInTime, String checkoutTime) {
        String itemCheckedIn = Util.getFormattedTime(checkInTime);
        if (!TextUtils.isEmpty(checkInTime) && !TextUtils.isEmpty(checkoutTime)) {
            String itemCheckedOut = Util.getFormattedTime(checkoutTime);
            return context.getResources().getString(R.string.checked_in_at) +
                    itemCheckedIn + " | " + context.getString(R.string.checked_out_at) + itemCheckedOut;
        }
        if (!TextUtils.isEmpty(checkInTime) && TextUtils.isEmpty(checkoutTime)) {
            return context.getResources().getString(R.string.checked_in_at) +
                    itemCheckedIn;
        }
        return null;
    }

    /**
     * Method to return event location
     *
     * @param order -particular order for a cell
     * @return event location
     */
    public static String getLocationInFormat(Order order) {
        Event event = order.getEvent();
        String name = event.getPlace().getName();
        String roomName = event.getPlace().getRoomName();
        String address = event.getPlace().getAddress();
        address = address.replaceAll("(\\r\\n)", " ");
        if (event.getPlace() != null) {
            StringBuilder sb = new StringBuilder();
            for (String s : Arrays.asList(roomName, name, address)) {
                if (!TextUtils.isEmpty(s)) {
                    sb.append(s).append(",");
                }
            }
            return sb.toString().replaceAll(",$", "");
        }
        return null;
    }

    public static String formatCheckoutTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(date);

    }
}
