package in.co.halexo.angry.righttobeauty;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

public class ActivitySplash extends AppCompatActivity {

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    Application rtbApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RTBApp)getApplication()).getAppComponentDagger().inject(this);
        if (sharedPreferences.getBoolean(getString(R.string.preference_key_first_time),true)){
            startActivity(new Intent(rtbApp, ActivityWelcome.class));
        }else {
            startActivity(new Intent(rtbApp, HomeActivity.class));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
