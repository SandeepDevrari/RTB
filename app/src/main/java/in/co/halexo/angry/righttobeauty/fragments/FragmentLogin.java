package in.co.halexo.angry.righttobeauty.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.Objects;

import in.co.halexo.angry.righttobeauty.HomeActivity;
import in.co.halexo.angry.righttobeauty.R;


public class FragmentLogin extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login,container,false);
        setUpUI(view);
        return view;
    }

    private void setUpUI(View view) {
        view.findViewById(R.id.login_Login).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_Login:{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Objects.requireNonNull(getContext()).startActivity(new Intent(getContext(), HomeActivity.class));
                }else{
                    (getContext()).startActivity(new Intent(getContext(), HomeActivity.class));
                }
                break;
            }
        }
    }
}
