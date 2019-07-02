package in.co.halexo.angry.righttobeauty;

import android.app.Application;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import in.co.halexo.angry.righttobeauty.adapter.HomeRecyclerOurServiceCategory;
import in.co.halexo.angry.righttobeauty.adapter.HomeRecyclerUserReview;
import in.co.halexo.angry.righttobeauty.adapter.HomeViewPagerAdapter;
import in.co.halexo.angry.righttobeauty.restapi.RetrofitAPI;
import in.co.halexo.angry.righttobeauty.restapi.RetrofitApiInstance;
import in.co.halexo.angry.righttobeauty.room.Banner;
import in.co.halexo.angry.righttobeauty.room.ServiceCategory;
import in.co.halexo.angry.righttobeauty.room.UserReview;
import in.co.halexo.angry.righttobeauty.view_model.BannerViewModel;
import in.co.halexo.angry.righttobeauty.view_model.BannerViewModelFactory;
import in.co.halexo.angry.righttobeauty.view_model.ServiceCategoryViewModel;
import in.co.halexo.angry.righttobeauty.view_model.ServiceCategoryViewModelFactory;
import in.co.halexo.angry.righttobeauty.view_model.UserReviewViewModel;
import in.co.halexo.angry.righttobeauty.view_model.UserReviewViewModelFactory;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    BannerViewModelFactory bannerViewModelFactory;

    @Inject
    ServiceCategoryViewModelFactory serviceCategoryViewModelFactory;

    @Inject
    UserReviewViewModelFactory userReviewViewModelFactory;

    @Inject
    Application rtbApp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ((RTBApp)getApplication()).getAppComponentDagger().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpUI();
    }

    private void setUpUI() {
        Toolbar toolbar=findViewById(R.id.home_appbar_toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout=findViewById(R.id.activity_home_drawer);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
//        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView =  findViewById(R.id.home_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        setUpData();


    }

    private void setUpData() {


        //progressDialog=new ProgressDialog(this);
        //progressDialog.setMessage("Checking Internet connection...");
        //progressDialog.show();

        ViewPager viewPager = findViewById(R.id.home_view_pager);
        RecyclerView servicesCatRecycler = findViewById(R.id.home_recycler_our_services);
        servicesCatRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL ,false));
        servicesCatRecycler.setNestedScrollingEnabled(true);

        RecyclerView userReviewRecycler = findViewById(R.id.home_recycler_user_reviews);
        userReviewRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL ,false));
        //userReviewRecycler.setNestedScrollingEnabled(true);

        BannerViewModel bannerViewModel = ViewModelProviders.of(this, bannerViewModelFactory).get(BannerViewModel.class);

        final HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(HomeActivity.this);
        bannerViewModel.getBannerList().observe(this, new Observer<List<Banner>>() {
            @Override
            public void onChanged(@Nullable List<Banner> banners) {
                if(banners!=null) {
                    homeViewPagerAdapter.setList(banners);
                    //Log.v("HHHHHHH ", banners.get(0).getLocalPath());
                }else{
                    showToastForInternetConnection();
                    //Log.v("AAAAAAAA ","no data in observer");
                }
            }
        });

        //viewPager.setAdapter(homeViewPagerAdapter);
        viewPager.setAdapter(homeViewPagerAdapter);
        TabLayout indicator = findViewById(R.id.indicatorHomeViewPager);
        indicator.setupWithViewPager(viewPager);
//        if(!sBase.checkInternet(this)){
//            progressDialog.dismiss();
//            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
//        }else {
//            progressDialog.setMessage("Loding...");


//            RetrofitAPI retrofitAPI = RetrofitApiInstance.getRetrofit(this).create(RetrofitAPI.class);
//            Call<List<Banner>> call = retrofitAPI.getAllBanners();
//            call.enqueue(new Callback<List<Banner>>() {
//                @Override
//                public void onResponse(@NonNull Call<List<Banner>> call, @NonNull Response<List<Banner>> response) {
//                    progressDialog.dismiss();
//                    try {
//                        List<Banner>bannerList= response.body();
//                        //Banner banner = response.body();
////                        List<String> strings = null;
////                        String baseUrl = "";
////                        if (bannerList == null) {
////
//////                            strings = banner.getBanners();
//////                            baseUrl = banner.getBaseUrl();
////                        }
//                        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(HomeActivity.this, bannerList);
//                        viewPager.setAdapter(homeViewPagerAdapter);
//                        viewPager.setAdapter(homeViewPagerAdapter);
//                        TabLayout indicator = findViewById(R.id.indicatorHomeViewPager);
//                        indicator.setupWithViewPager(viewPager);
//                    } catch (Exception e) {
//                        Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<List<Banner>> call, @NonNull Throwable t) {
//                    progressDialog.dismiss();
//                    Toast.makeText(HomeActivity.this, "failed"+call.toString(), Toast.LENGTH_SHORT).show();
//                    HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(HomeActivity.this,null);
////                    ViewPager viewPager = findViewById(R.id.home_view_pager);
//                    viewPager.setAdapter(homeViewPagerAdapter);
//                    TabLayout indicator = findViewById(R.id.indicatorHomeViewPager);
//                    indicator.setupWithViewPager(viewPager);
//                }
//            });

        ServiceCategoryViewModel serviceCategoryViewModel=ViewModelProviders.of(this,serviceCategoryViewModelFactory).get(ServiceCategoryViewModel.class);
        final HomeRecyclerOurServiceCategory homeRecyclerOurServiceCategory = new HomeRecyclerOurServiceCategory(HomeActivity.this,new OnRecyclerItemClicked() {
            @Override
            public void onItemClicked(int pos) {
                Toast.makeText(HomeActivity.this, "" + pos, Toast.LENGTH_SHORT).show();
                }
        });
        servicesCatRecycler.setAdapter(homeRecyclerOurServiceCategory);

        serviceCategoryViewModel.getServiceCategories().observe(this, new Observer<List<ServiceCategory>>() {
            @Override
            public void onChanged(@Nullable List<ServiceCategory> serviceCategories) {
                if(serviceCategories!=null){
                    homeRecyclerOurServiceCategory.setServiceCategoryList(serviceCategories);
                }else{
                    showToastForInternetConnection();
                }
            }
        });
//            final Call<List<ServiceCategory>>service_cat_call=retrofitAPI.getAllServicesCategories();
//            service_cat_call.enqueue(new Callback<List<ServiceCategory>>() {
//                @Override
//                public void onResponse(@NonNull Call<List<ServiceCategory>> call, @NonNull Response<List<ServiceCategory>> response) {
//                    try {
//                        List<ServiceCategory> serviceCategoryList = response.body();
//                        if (serviceCategoryList != null) {
//                            HomeRecyclerOurServiceCategory homeRecyclerOurServiceCategory = new HomeRecyclerOurServiceCategory(HomeActivity.this, serviceCategoryList, new OnRecyclerItemClicked() {
//                                @Override
//                                public void onItemClicked(int pos) {
//                                    Toast.makeText(HomeActivity.this, "" + pos, Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                            servicesCatRecycler.setAdapter(homeRecyclerOurServiceCategory);
//                        }
//                    }catch (Exception e){
//                        e.printStackTrace();
//                        Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<List<ServiceCategory>> call, @NonNull Throwable t) {
//                    Toast.makeText(HomeActivity.this, "Failure", Toast.LENGTH_SHORT).show();
//                }
//            });

        UserReviewViewModel userReviewViewModel=ViewModelProviders.of(this,userReviewViewModelFactory).get(UserReviewViewModel.class);
        final HomeRecyclerUserReview homeRecyclerUserReview = new HomeRecyclerUserReview(HomeActivity.this, new OnRecyclerItemClicked() {
            @Override
            public void onItemClicked(int pos) {
                    Toast.makeText(HomeActivity.this, "" + pos, Toast.LENGTH_SHORT).show();
                }
        });
        userReviewRecycler.setAdapter(homeRecyclerUserReview);
        userReviewViewModel.getAllUserReview().observe(this, new Observer<List<UserReview>>() {
                @Override
                public void onChanged(@Nullable List<UserReview> userReviews) {
                    if(userReviews!=null){
                        homeRecyclerUserReview.setUserReviewList(userReviews);
                    }else{
                        showToastForInternetConnection();
                    }
                }
        });
//            final Call<List<UserReview>>userReviewCall=retrofitAPI.getAllUserReviews();
//            userReviewCall.enqueue(new Callback<List<UserReview>>() {
//                @Override
//                public void onResponse(@NonNull Call<List<UserReview>> call, @NonNull Response<List<UserReview>> response) {
//                    try {
//                        List<UserReview> userReviewList = response.body();
//                        if (userReviewList != null) {
//                            HomeRecyclerUserReview homeRecyclerUserReview = new HomeRecyclerUserReview(HomeActivity.this, userReviewList, new OnRecyclerItemClicked() {
//                                @Override
//                                public void onItemClicked(int pos) {
//                                    Toast.makeText(HomeActivity.this, "" + pos, Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                            userReviewRecycler.setAdapter(homeRecyclerUserReview);
//                        }
//                    }catch (Exception e){
//                        e.printStackTrace();
//                        Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<List<UserReview>> call, @NonNull Throwable t) {
//                    Toast.makeText(HomeActivity.this, "Failure", Toast.LENGTH_SHORT).show();
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_home:{
                if(this.getClass()==HomeActivity.class){
                    //do nothing
                }
                else{
                    startActivity(new Intent(rtbApp,HomeActivity.class));
                }
                break;
            }
            case R.id.nav_parlour:{
                startActivity(new Intent(rtbApp,ParlorActivity.class));
                break;
            }
            case R.id.nav_wallet:{
                break;
            }
            case R.id.nav_booking:{
                break;
            }
            case R.id.nav_settings:{
                break;
            }
            case R.id.nav_about:{
                break;
            }
            case R.id.nav_logout:{
                break;
            }
        }
        DrawerLayout drawer = findViewById(R.id.activity_home_drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
