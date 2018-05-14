package involvio.checkinapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import involvio.checkinapplication.R;
import involvio.checkinapplication.model.Event;
import involvio.checkinapplication.model.Order;
import involvio.checkinapplication.utility.CheckoutHistory;
import involvio.checkinapplication.utility.Util;


/**
 * RecyclerAdapter class
 * Created by Niharika on 13-05-2018.
 */
public class CheckInHistoryAdapter extends SectionedRecyclerViewAdapter<RecyclerView.ViewHolder> {

    private List<CheckoutHistory> checkoutHistories;

    private Context mContext;

    public CheckInHistoryAdapter(List<CheckoutHistory> checkoutHistories, Context mContext) {
        this.checkoutHistories = checkoutHistories;
        this.mContext = mContext;
    }

    @Override
    public int getSectionCount() {
        return checkoutHistories.size();
    }

    @Override
    public int getItemCount(int section) {
        return checkoutHistories.get(section).getOrders().size();
    }

    //Inflating views
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, boolean header) {
        if (header) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_recycler_section, parent, false);
            return new SectionViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_recycler_list_new, parent, false);
            return new ViewHolder(view);
        }
    }

    // Bind values on Header
    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int section) {
        String sectionName = Util.getDateAndTime(checkoutHistories.get(section));
        SectionViewHolder sectionViewHolder = (SectionViewHolder) holder;
        sectionViewHolder.txtHeader.setText(sectionName);
    }

    // Bind Value in Cells
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int section, int relativePosition, int absolutePosition) {
        final Order order = checkoutHistories.get(section).getOrders().get(relativePosition);
        String itemCheckedIn = Util.getFormattedTime(order.getCreatedAt());
        String itemEventName = order.getEvent().getName();
        String itemLocation = getEventLocation(order);

        final ViewHolder itemViewHolder = (ViewHolder) holder;
        itemViewHolder.textCheckedIn.setText(itemCheckedIn);

        //If Checked out time is there,then only show Check out time else View won't be visible
        if (order.getCheckedOutAt() != null) {
            itemViewHolder.linCheckedOut.setVisibility(View.VISIBLE);
            String itemCheckedOut = order.getCheckedOutAt();
            itemViewHolder.textCheckedOut.setText(Util.getFormattedTime(itemCheckedOut));
        }

        itemViewHolder.textEventName.setText(itemEventName);

        if (!TextUtils.isEmpty(itemLocation)) {
            itemViewHolder.textLocationName.setText(itemLocation);
        } else {
            itemViewHolder.linLocation.setVisibility(View.GONE);
        }

        //Cell tap
        itemViewHolder.linCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (order.getCanCheckOut()) {
                    toggleCheckInButtonVisibility(itemViewHolder.linDoCheckIn);
                }
            }
        });

        //Do check in click
        itemViewHolder.textDoCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCheckInAndDisplayCheckedOutTime(order, itemViewHolder);
            }
        });

    }

    /**
     * Method to return event location
     *
     * @param order
     * @return event location
     */
    private String getEventLocation(Order order) {
        Event event = order.getEvent();
        String name = null, roomName = null, address = null;
        if (event != null && event.getPlace() != null) {
            if (!TextUtils.isEmpty(event.getPlace().getName())) {
                name = event.getPlace().getName();
            }
            if (!TextUtils.isEmpty(event.getPlace().getRoomName())) {
                roomName = event.getPlace().getRoomName();
                name = roomName + "," + name;
            }
            if (!TextUtils.isEmpty(event.getPlace().getAddress())) {
                address = event.getPlace().getAddress();
                name = name + "\n" + address;

            }
            return name;

        }
        return null;
    }

    /**
     * Method to do check-in and display checked out time making Cell clickable false
     *
     * @param order
     * @param itemViewHolder
     */
    private void doCheckInAndDisplayCheckedOutTime(Order order, ViewHolder itemViewHolder) {

        // Get current time stamp
        DateFormat.getDateTimeInstance().format(new Date());
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        order.setCheckedOutAt(currentDateTimeString);
        //Show checked out view to the user
        itemViewHolder.linCheckedOut.setVisibility(View.VISIBLE);
        itemViewHolder.textCheckedOut.setText(Util.getFormattedTime(currentDateTimeString));

        //Hide Checkgit commit -m "first commit" in button as we have already done check in
        itemViewHolder.linDoCheckIn.setVisibility(View.GONE);
        order.setCanCheckOut(false);

        //Reload
        notifyDataSetChanged();
    }

    /**
     * Method to toggle visibility for Check in on cell click
     *
     * @param linDoCheckIn -linearlayout for Checkin button
     */
    private void toggleCheckInButtonVisibility(LinearLayout linDoCheckIn) {
        linDoCheckIn.setVisibility(linDoCheckIn.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    // SectionViewHolder Class for Sections
    class SectionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_header)
        TextView txtHeader;

        SectionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    // ItemViewHolder Class for Items in each Section
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_txt_chk_in_at)
        TextView textCheckedIn;
        @BindView(R.id.item_txt_chk_out_at)
        TextView textCheckedOut;
        @BindView(R.id.item_txt_event_name)
        TextView textEventName;
        @BindView(R.id.item_txt_loc)
        TextView textLocationName;
        @BindView(R.id.lin_chk_out)
        LinearLayout linCheckedOut;
        @BindView(R.id.lin_do_check_in)
        LinearLayout linDoCheckIn;
        @BindView(R.id.item_check_in)
        TextView textDoCheckIn;
        @BindView(R.id.lin_loc)
        LinearLayout linLocation;
        @BindView(R.id.lin_cell)
        LinearLayout linCard;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
