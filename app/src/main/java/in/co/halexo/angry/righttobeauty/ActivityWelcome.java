package in.co.halexo.angry.righttobeauty;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.PermissionChecker;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;

import in.co.halexo.angry.righttobeauty.adapter.WelcomeTabAdapter;

public class ActivityWelcome extends AppCompatActivity {

    private final int TABS=3;

    @Inject
    SharedPreferences.Editor editor;

    @Inject
    Application rtbApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RTBApp)getApplication()).getAppComponentDagger().inject(this);
        setContentView(R.layout.welcome_activity);
        setUpUI();
    }

    private void setUpUI() {
        TabLayout tabLayout=findViewById(R.id.indicatorWelcomeViewPager);
        ViewPager viewPager=findViewById(R.id.welcome_view_pager);
        viewPager.setAdapter(new WelcomeTabAdapter(getSupportFragmentManager(),TABS));
        tabLayout.setupWithViewPager(viewPager);

        findViewById(R.id.welcome_explore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putBoolean(getString(R.string.preference_key_first_time),false);
                editor.apply();
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT) {
                    sBase.requestForAllPermissions(rtbApp, ActivityWelcome.this);
                }else{
                    proccedNext();
                    finish();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case sBase.sPermissionAll:{
                proccedNext();
                finish();
            }
        }
    }

    private void proccedNext(){
        startActivity(new Intent(rtbApp, ActivityLoginSignup.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        //finish();
    }
}
