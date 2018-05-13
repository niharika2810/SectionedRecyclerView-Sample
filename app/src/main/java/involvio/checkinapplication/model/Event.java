
package involvio.checkinapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Event class
 * Created by Niharika on 13-05-2018.
 */
public class Event {

    @SerializedName("all_day")
    private Boolean AllDay;
    @SerializedName("attending_status")
    private Object AttendingStatus;
    @SerializedName("attendings_count")
    private Long AttendingsCount;
    @SerializedName("cancelled")
    private Boolean Cancelled;
    @SerializedName("color")
    private String Color;
    @SerializedName("created_at")
    private String CreatedAt;
    @SerializedName("description")
    private String Description;
    @SerializedName("done")
    private Object Done;
    @SerializedName("done_at")
    private Object DoneAt;
    @SerializedName("end_time")
    private String EndTime;
    @SerializedName("friends_attending")
    private Boolean FriendsAttending;
    @SerializedName("has_all_tickets_refunded")
    private Boolean HasAllTicketsRefunded;
    @SerializedName("is_class_event")
    private Boolean IsClassEvent;
    @SerializedName("is_default_image")
    private Boolean IsDefaultImage;
    @SerializedName("is_involvio_personal_event")
    private Boolean IsInvolvioPersonalEvent;
    @SerializedName("is_ticket_event")
    private Boolean IsTicketEvent;
    @SerializedName("name")
    private String Name;
    @SerializedName("number_of_tickets_purchased")
    private Long NumberOfTicketsPurchased;
    @SerializedName("place")
    private Place Place;
    @SerializedName("recurrence_rule")
    private Object RecurrenceRule;
    @SerializedName("start_time")
    private String StartTime;
    @SerializedName("ticket_min_price")
    private Long TicketMinPrice;
    @SerializedName("time_zone")
    private String TimeZone;
    @SerializedName("type")
    private String Type;
    @SerializedName("updated_at")
    private String UpdatedAt;
    @Expose
    private String _id;

    public Boolean getAllDay() {
        return AllDay;
    }

    public void setAllDay(Boolean allDay) {
        AllDay = allDay;
    }

    public Object getAttendingStatus() {
        return AttendingStatus;
    }

    public void setAttendingStatus(Object attendingStatus) {
        AttendingStatus = attendingStatus;
    }

    public Long getAttendingsCount() {
        return AttendingsCount;
    }

    public void setAttendingsCount(Long attendingsCount) {
        AttendingsCount = attendingsCount;
    }

    public Boolean getCancelled() {
        return Cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        Cancelled = cancelled;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Object getDone() {
        return Done;
    }

    public void setDone(Object done) {
        Done = done;
    }

    public Object getDoneAt() {
        return DoneAt;
    }

    public void setDoneAt(Object doneAt) {
        DoneAt = doneAt;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public Boolean getFriendsAttending() {
        return FriendsAttending;
    }

    public void setFriendsAttending(Boolean friendsAttending) {
        FriendsAttending = friendsAttending;
    }

    public Boolean getHasAllTicketsRefunded() {
        return HasAllTicketsRefunded;
    }

    public void setHasAllTicketsRefunded(Boolean hasAllTicketsRefunded) {
        HasAllTicketsRefunded = hasAllTicketsRefunded;
    }

    public Boolean getIsClassEvent() {
        return IsClassEvent;
    }

    public void setIsClassEvent(Boolean isClassEvent) {
        IsClassEvent = isClassEvent;
    }

    public Boolean getIsDefaultImage() {
        return IsDefaultImage;
    }

    public void setIsDefaultImage(Boolean isDefaultImage) {
        IsDefaultImage = isDefaultImage;
    }

    public Boolean getIsInvolvioPersonalEvent() {
        return IsInvolvioPersonalEvent;
    }

    public void setIsInvolvioPersonalEvent(Boolean isInvolvioPersonalEvent) {
        IsInvolvioPersonalEvent = isInvolvioPersonalEvent;
    }

    public Boolean getIsTicketEvent() {
        return IsTicketEvent;
    }

    public void setIsTicketEvent(Boolean isTicketEvent) {
        IsTicketEvent = isTicketEvent;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getNumberOfTicketsPurchased() {
        return NumberOfTicketsPurchased;
    }

    public void setNumberOfTicketsPurchased(Long numberOfTicketsPurchased) {
        NumberOfTicketsPurchased = numberOfTicketsPurchased;
    }

    public Place getPlace() {
        return Place;
    }

    public void setPlace(Place place) {
        Place = place;
    }

    public Object getRecurrenceRule() {
        return RecurrenceRule;
    }

    public void setRecurrenceRule(Object recurrenceRule) {
        RecurrenceRule = recurrenceRule;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public Long getTicketMinPrice() {
        return TicketMinPrice;
    }

    public void setTicketMinPrice(Long ticketMinPrice) {
        TicketMinPrice = ticketMinPrice;
    }

    public String getTimeZone() {
        return TimeZone;
    }

    public void setTimeZone(String timeZone) {
        TimeZone = timeZone;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

}
