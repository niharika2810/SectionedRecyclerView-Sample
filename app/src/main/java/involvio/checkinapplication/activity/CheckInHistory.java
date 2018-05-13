package involvio.checkinapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import involvio.checkinapplication.R;
import involvio.checkinapplication.adapter.RecyclerAdapter;
import involvio.checkinapplication.pojo.Order;
import involvio.checkinapplication.utility.CheckoutHistory;

/**
 * CheckInHistory class
 * Created by Niharika on 13-05-2018.
 */

public class CheckInHistory extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        // bind the view using butterknife
        ButterKnife.bind(this);
        initToolbar();
        setAdapter();

    }

    void setAdapter() {
        // Setting up the recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Parsing Json using GSOn
        String orderJson = inputStreamToString(getResources().openRawResource(R.raw.events));
        Type collectionType = new TypeToken<ArrayList<Order>>() {
        }.getType();

        ArrayList<Order> sections = new Gson().fromJson(orderJson, collectionType);

        ArrayList<CheckoutHistory> checkoutHistories = new ArrayList<>();

        setSections(sections, checkoutHistories);

        //Setting up the adapter with Section and cell values
        recyclerView.setAdapter(new RecyclerAdapter(checkoutHistories, this));
    }

    public void initToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Check In History");
        }
    }

    //Setting up the sections according to date
    private void setSections(ArrayList<Order> sections, ArrayList<CheckoutHistory> checkoutHistories) {
        for (int i = 0; i < sections.size(); i++) {
            CheckoutHistory checkoutHistory = new CheckoutHistory(sections.get(i).getCreatedAt(), sections.get(i));
            int index = checkoutHistories.indexOf(checkoutHistory);
            if (index < 0) {
                checkoutHistories.add(checkoutHistory);
            } else {
                checkoutHistories.get(index).getOrders().add(sections.get(i));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }


    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            return new String(bytes);
        } catch (IOException e) {
            return null;
        }
    }
}
