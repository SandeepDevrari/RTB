package in.co.halexo.angry.righttobeauty.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.co.halexo.angry.righttobeauty.fragments.FragmentLogin;
import in.co.halexo.angry.righttobeauty.fragments.FragmentOneWelcome;
import in.co.halexo.angry.righttobeauty.fragments.FragmentSignUp;
import in.co.halexo.angry.righttobeauty.fragments.FragmentThreeWelcome;
import in.co.halexo.angry.righttobeauty.fragments.FragmentTwoWelcome;

public class WelcomeTabAdapter extends FragmentPagerAdapter {
    private int tabs;
    public WelcomeTabAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.tabs=tabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case 0:{
                fragment=new FragmentOneWelcome();
                break;
            }
            case 1:{
                fragment=new FragmentTwoWelcome();
                break;
            }
            case 2:{
                fragment=new FragmentThreeWelcome();
                break;
            }
            default:{
                fragment=null;
            }
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabs;
    }

}
