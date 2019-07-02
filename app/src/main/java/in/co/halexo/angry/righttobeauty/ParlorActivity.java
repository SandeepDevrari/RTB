package in.co.halexo.angry.righttobeauty;

import android.app.Application;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import in.co.halexo.angry.righttobeauty.adapter.ParlorRecycler;
import in.co.halexo.angry.righttobeauty.restapi.RetrofitAPI;
import in.co.halexo.angry.righttobeauty.restapi.RetrofitApiInstance;
import in.co.halexo.angry.righttobeauty.room.Parlor;
import in.co.halexo.angry.righttobeauty.room.ParlorByArea;
import in.co.halexo.angry.righttobeauty.room.ParlorByCity;
import in.co.halexo.angry.righttobeauty.room.ParlorByState;
import in.co.halexo.angry.righttobeauty.view_model.ParlorViewModel;
import in.co.halexo.angry.righttobeauty.view_model.ParlorViewModelFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParlorActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner state,city,area;
    private boolean isFilter=false;
    private List<Parlor> parlorsAll;
    //private ProgressDialog progressDialog;

    @Inject
    Application rtbApp;

    @Inject
    ParlorViewModelFactory parlorViewModelFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RTBApp)getApplication()).getAppComponentDagger().inject(this);
        setContentView(R.layout.activity_parlor);
        setUpUI();
    }

    private void setUpUI() {
//        Toolbar toolbar=findViewById(R.id.home_appbar_toolbar);
//        setSupportActionBar(toolbar);
//        if(getSupportActionBar()!=null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//        }

        findViewById(R.id.parlor_filter_button).setOnClickListener(this);
        state=findViewById(R.id.parlor_spinner_state);
        city=findViewById(R.id.parlor_spinner_city);
        area=findViewById(R.id.parlor_spinner_area);


        setUpData();
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }

    private void setUpData() {
//        progressDialog=new ProgressDialog(this);
//        progressDialog.setMessage("Checking Internet connection...");
//        progressDialog.show();
//
//        if(!sBase.checkInternet(rtbApp)){
//            progressDialog.dismiss();
//            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
//        }else {
//            progressDialog.setMessage("Loding...");

        RecyclerView recyclerView = findViewById(R.id.parlor_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        ParlorViewModel parlorViewModel = ViewModelProviders.of(this, parlorViewModelFactory).get(ParlorViewModel.class);

        final ParlorRecycler parlorRecyclerAdapter=new ParlorRecycler(ParlorActivity.this,new OnRecyclerItemClicked() {
            @Override
            public void onItemClicked(int pos) {
                //Toast.makeText(ParlorActivity.this, ""+pos, Toast.LENGTH_SHORT).show();
                if(parlorsAll!=null) {
                    if(parlorsAll.size()>0) {
                        Parlor parlor = parlorsAll.get(pos);
                        if (parlor != null) {
                            Intent intent = new Intent(getApplicationContext(), ParticularParlorActivity.class);
                            intent.putExtra(sBase.PARLOR, parlor);
                            startActivity(intent);
                            Toast.makeText(ParlorActivity.this, parlor.getParlorName(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ParlorActivity.this, "Try Again!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        recyclerView.setAdapter(parlorRecyclerAdapter);

        parlorViewModel.getAllParlors().observe(this, new Observer<List<Parlor>>() {
            @Override
            public void onChanged(@Nullable List<Parlor> parlors) {
                if(parlors!=null){
                    parlorsAll=parlors;
                    parlorRecyclerAdapter.setParlorList(parlors);
                }else{
                    showToastForInternetConnection();
                }
            }
        });
//            RetrofitAPI retrofitAPI = RetrofitApiInstance.getRetrofit(this).create(RetrofitAPI.class);
//            Call<List<ParlorByState>> call = retrofitAPI.getAllParlors();
//            call.enqueue(new Callback<List<ParlorByState>>() {
//                @Override
//                public void onResponse(@NonNull Call<List<ParlorByState>> call, @NonNull Response<List<ParlorByState>> response) {
//                    try {
//                        List<ParlorByState>stateList=response.body();
//                        if(stateList!=null){
//                            progressDialog.setMessage("Arranging parlors..");
//                            final List<Parlor>parlorList=new ArrayList<>();
//                            for(ParlorByState state:stateList){
//                                List<ParlorByCity> parlorByCityTemp=state.getParlorByCity();
//                                for (ParlorByCity city:parlorByCityTemp){
//                                    List<ParlorByArea>parlorByAreasTemp= city.getParlorByArea();
//                                    for (ParlorByArea area:parlorByAreasTemp){
//                                        Parlor parlor=area.getParlor();
//                                        parlor.setState(state.getState());
//                                        parlor.setCity(city.getCity());
//                                        parlor.setArea(area.getArea());
//                                        parlorList.add(parlor);
//                                    }
//                                }
//                            }
//                            ParlorRecycler parlorRecyclerAdapter=new ParlorRecycler(ParlorActivity.this, parlorList, new OnRecyclerItemClicked() {
//                                @Override
//                                public void onItemClicked(int pos) {
//                                    //Toast.makeText(ParlorActivity.this, ""+pos, Toast.LENGTH_SHORT).show();
//                                    Parlor parlor=parlorList.get(pos);
//                                    if(parlor!=null){
//                                        Intent intent=new Intent(getApplicationContext(),ParticularParlorActivity.class);
//                                        intent.putExtra(sBase.PARLOR,parlor);
//                                        startActivity(intent);
//                                    }else{
//                                        Toast.makeText(ParlorActivity.this, "Try Again!!", Toast.LENGTH_SHORT).show();
//                                    }
//
//                                }
//                            });
//                            recyclerView.setAdapter(parlorRecyclerAdapter);
//                        }else{
//                            Toast.makeText(ParlorActivity.this, "No Parlor Found!!!", Toast.LENGTH_SHORT).show();
//                        }
//
//                        progressDialog.dismiss();
//                    } catch (Exception e) {
//                        Toast.makeText(ParlorActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                        if (progressDialog.isShowing()){
//                            progressDialog.dismiss();
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<List<ParlorByState>> call, @NonNull Throwable t) {
//                    progressDialog.dismiss();
//                    Toast.makeText(ParlorActivity.this, "failed" + call.toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
        //}
    }

    private void showToastForInternetConnection(){
        if(!sBase.checkInternet(rtbApp)){
            Toast.makeText(rtbApp, getResources().getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.parlor_filter_button:{
                if(isFilter){
                    isFilter=false;
                    state.setVisibility(View.GONE);
                    city.setVisibility(View.GONE);
                    area.setVisibility(View.GONE);
                }else{
                    isFilter=true;
                    state.setVisibility(View.VISIBLE);
                    city.setVisibility(View.VISIBLE);
                    area.setVisibility(View.VISIBLE);
                    //findViewById(R.id.parlor_filter_button).setVisibility(View.INVISIBLE);
                }
                break;
            }
        }
    }
}
