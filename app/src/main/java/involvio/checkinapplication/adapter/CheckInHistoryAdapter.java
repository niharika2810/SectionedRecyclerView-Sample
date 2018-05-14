package involvio.checkinapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import involvio.checkinapplication.R;
import involvio.checkinapplication.helper.CustomTextView;
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
                    .inflate(R.layout.item_check_in_history_section, parent, false);
            return new SectionViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_check_in_history, parent, false);
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
        String itemEventName = order.getEvent().getName();
        String itemLocation = Util.getLocationInFormat(order);

        final ViewHolder itemViewHolder = (ViewHolder) holder;

        displayCheckInAndCheckoutTime(order, itemViewHolder);

        itemViewHolder.textEventName.setText(itemEventName);

        if (!TextUtils.isEmpty(itemLocation)) {
            itemViewHolder.textLocationName.setText(itemLocation);
        } else {
            itemViewHolder.textLocationName.setVisibility(View.GONE);
        }

        //Cell tap
        itemViewHolder.llCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (order.getCanCheckOut()) {
                    toggleCheckInButtonVisibility(itemViewHolder.llDoCheckIn);
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

    private void displayCheckInAndCheckoutTime(Order order, ViewHolder itemViewHolder) {
        String orderFormattedTime = Util.getCheckInCheckoutTimeInFormat(this.mContext, order.getCreatedAt(), order.getCheckedOutAt());
        itemViewHolder.textCheckedIn.setText(orderFormattedTime);
    }


    /**
     * Method to do check-in and display checked out time making Cell clickable false
     *
     * @param order          -Particular Cell order
     * @param itemViewHolder -View
     */
    private void doCheckInAndDisplayCheckedOutTime(Order order, ViewHolder itemViewHolder) {

        // Get current time stamp
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        String checkoutTime = simpleDateFormat.format(new Date());
        order.setCheckedOutAt(checkoutTime);

        //Hide button as we have already done check in
        itemViewHolder.llDoCheckIn.setVisibility(View.GONE);
        order.setCanCheckOut(false);

        //Reload
        notifyDataSetChanged();
    }

    /**
     * Method to toggle visibility for Check in on cell click
     *
     * @param llDoCheckIn -linearLayout for CheckIn button
     */
    private void toggleCheckInButtonVisibility(LinearLayout llDoCheckIn) {
        llDoCheckIn.setVisibility(llDoCheckIn.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
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
        @BindView(R.id.item_txt_check_in)
        CustomTextView textCheckedIn;
        @BindView(R.id.item_txt_event_name)
        CustomTextView textEventName;
        @BindView(R.id.item_txt_loc)
        CustomTextView textLocationName;
        @BindView(R.id.ll_do_check_in)
        LinearLayout llDoCheckIn;
        @BindView(R.id.item_check_in)
        CustomTextView textDoCheckIn;
        @BindView(R.id.ll_cell)
        LinearLayout llCard;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
