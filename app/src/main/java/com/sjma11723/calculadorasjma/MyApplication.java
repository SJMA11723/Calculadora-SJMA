package com.sjma11723.calculadorasjma;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by SJMA 11723 on 08/04/2018.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(getApplicationContext());

        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();

        Realm.setDefaultConfiguration(config);

    }
}
