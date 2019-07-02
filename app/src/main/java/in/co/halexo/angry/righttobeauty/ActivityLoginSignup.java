package in.co.halexo.angry.righttobeauty;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;

import in.co.halexo.angry.righttobeauty.adapter.LoginSignupTabAdapter;

public class ActivityLoginSignup extends AppCompatActivity implements View.OnClickListener {

    @Inject
    Application rtbApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RTBApp)getApplication()).getAppComponentDagger().inject(this);
        setContentView(R.layout.activity_login_signup);
        setUpUI();
    }

    private void setUpUI() {
        TabLayout tabLayout=findViewById(R.id.login_signup_tab);
        ViewPager viewPager=findViewById(R.id.login_signup_pager);
        String[] tabs=new String[]{
                getString(R.string.log_in),
                getString(R.string.sign_up)
        };
        viewPager.setAdapter(new LoginSignupTabAdapter(getSupportFragmentManager(),tabs));
        tabLayout.setupWithViewPager(viewPager);

        findViewById(R.id.login_signup_do_it_later).setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_signup_do_it_later:{
                startActivity(new Intent(rtbApp,HomeActivity.class));
            }
        }
    }
}
