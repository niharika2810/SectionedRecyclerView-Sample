package involvio.checkinapplication.application;

import android.app.Application;

import involvio.checkinapplication.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * CheckInApplication
 * Created by Niharika on 14-05-2018.
 */

public class CheckInApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/roboto.regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }

}
