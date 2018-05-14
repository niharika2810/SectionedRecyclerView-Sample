
package involvio.checkinapplication.model;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

import involvio.checkinapplication.R;
import involvio.checkinapplication.utility.Util;


/**
 * Order class
 * Created by Niharika on 13-05-2018.
 */
public class Order {

    @SerializedName("can_check_out")
    private Boolean canCheckOut;
    @SerializedName("checked_out_at")
    private String checkedOutAt;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("event")
    private Event event;
    @SerializedName("id")
    private String id;

    public Boolean getCanCheckOut() {
        return canCheckOut;
    }

    public void setCanCheckOut(Boolean canCheckOut) {
        this.canCheckOut = canCheckOut;
    }

    public String getCheckedOutAt() {
        return checkedOutAt;
    }

    public void setCheckedOutAt(String checkedOutAt) {
        this.checkedOutAt = checkedOutAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    /**
     * Method to return Time for check in and Checkout in Format
     *
     * @param context -Current this
     * @return Formatted string for Check In as well as Checkout time
     */
    public String getCheckInCheckoutTimeInFormat(Context context) {
        String itemCheckedIn = Util.getInstance().getFormattedTime(this.createdAt);
        if (!TextUtils.isEmpty(this.createdAt) && !TextUtils.isEmpty(this.checkedOutAt)) {
            String itemCheckedOut = Util.getInstance().getFormattedTime(this.checkedOutAt);
            return context.getResources().getString(R.string.checked_in_at) +
                    itemCheckedIn + " | " + context.getString(R.string.checked_out_at) + itemCheckedOut;
        }
        if (!TextUtils.isEmpty(this.createdAt) && TextUtils.isEmpty(this.checkedOutAt)) {
            return context.getResources().getString(R.string.checked_in_at) +
                    itemCheckedIn;
        }
        return null;
    }

    /**
     * Method to return event location
     *
     * @return event location
     */
    public String getLocationInFormat() {
        Event event = this.getEvent();
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
}
