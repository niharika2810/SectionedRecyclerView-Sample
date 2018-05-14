package involvio.checkinapplication.application;
/*
 *
 * EDFORA CONFIDENTIAL
 * __________________
 *
 *  [2017] - [2020] Edfora Infotech Private Ltd
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Edfora Infotech Private Ltd and its subsidiaries,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Edfora Infotech Private Ltd
 * and its subsidiaries and may be covered by India and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Edfora Infotech Private Ltd.
 */

import android.app.Application;
import android.content.Context;

import involvio.checkinapplication.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * CheckInApplication
 * Created by Niharika on 14-05-2018.
 */

public class CheckInApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("roboto.regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
