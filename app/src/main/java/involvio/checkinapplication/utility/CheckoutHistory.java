package involvio.checkinapplication.utility;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import involvio.checkinapplication.model.Order;

public class CheckoutHistory {

    private Calendar calendar;

    private ArrayList<Order> orders = new ArrayList<>();

    public CheckoutHistory(String date, Order order) {

        calendar = Calendar.getInstance();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            calendar.setTime(simpleDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        orders.add(order);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckoutHistory that = (CheckoutHistory) o;
        return calendar.get(Calendar.YEAR) == that.calendar.get(Calendar.YEAR) &&
                calendar.get(Calendar.MONTH) == that.calendar.get(Calendar.MONTH) &&
                calendar.get(Calendar.DAY_OF_MONTH) == that.calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int hashCode() {
        return calendar.get(Calendar.YEAR) + calendar.get(Calendar.MONTH) + calendar.get(Calendar.DAY_OF_MONTH
        );
    }

    String getMonthName() {
        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }

    String getDayName() {
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }

    String getDate() {
        return calendar.get(Calendar.DAY_OF_MONTH) + getDaySuffix(calendar.get(Calendar.DAY_OF_MONTH));
    }

    private String getDaySuffix(final int n) {
        if (n < 1 || n > 31)
            return "Invalid date";
        if (n >= 11 && n <= 13)
            return "th";

        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }


}
