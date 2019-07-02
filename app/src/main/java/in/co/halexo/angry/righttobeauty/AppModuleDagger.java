package in.co.halexo.angry.righttobeauty;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.co.halexo.angry.righttobeauty.restapi.RetrofitAPI;
import in.co.halexo.angry.righttobeauty.room.BannerDao;
import in.co.halexo.angry.righttobeauty.room.ParlorDao;
import in.co.halexo.angry.righttobeauty.room.ParlorInfrastructureDao;
import in.co.halexo.angry.righttobeauty.room.ParlorServiceDao;
import in.co.halexo.angry.righttobeauty.room.ParlorServicesRelation;
import in.co.halexo.angry.righttobeauty.room.ParlorServicesRelationDao;
import in.co.halexo.angry.righttobeauty.room.RTBRoomDB;
import in.co.halexo.angry.righttobeauty.room.ServiceCategoryDao;
import in.co.halexo.angry.righttobeauty.room.UserReviewDao;
import in.co.halexo.angry.righttobeauty.view_model.BannerViewModelFactory;
import in.co.halexo.angry.righttobeauty.view_model.ParlorInfrastructureViewModelFactory;
import in.co.halexo.angry.righttobeauty.view_model.ParlorServicesRelationViewModelFactory;
import in.co.halexo.angry.righttobeauty.view_model.ParlorServicesViewModelFactory;
import in.co.halexo.angry.righttobeauty.view_model.ParlorViewModelFactory;
import in.co.halexo.angry.righttobeauty.view_model.ServiceCategoryViewModelFactory;
import in.co.halexo.angry.righttobeauty.view_model.UserReviewViewModel;
import in.co.halexo.angry.righttobeauty.view_model.UserReviewViewModelFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModuleDagger {

    private final Application rtbApp;

    @Inject
    public AppModuleDagger(Application rtbApp){
        this.rtbApp=rtbApp;
    }


    //Application
    @Singleton
    @Provides
    public Application provideRtbApp() {
        return rtbApp;
    }

    //Database
    @Singleton
    @Provides
    public RTBRoomDB provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                RTBRoomDB.class,"rtb_db")
                .build();
    }

    //webservice
    @Singleton
    @Provides
    public RetrofitAPI provideRetrofit(Application application){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client= new OkHttpClient
                    .Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(interceptor).build();
            Gson gson=new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();
            return (new Retrofit.Builder()
                    .baseUrl(application.getString(R.string.api_base_url))
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()).create(RetrofitAPI.class);
    }

    //Dao's
    @Provides
    public BannerDao provideBannerDao(RTBRoomDB roomDB){
        return roomDB.getBanner();
    }

    @Provides
    public ServiceCategoryDao provideServiceCategory(RTBRoomDB roomDB){
        return roomDB.getServiceCategory();
    }

    @Provides
    public UserReviewDao provideUserReview(RTBRoomDB rtbRoomDB){
        return rtbRoomDB.getUserReview();
    }

    @Provides
    public ParlorDao provideParlor(RTBRoomDB rtbRoomDB){
        return rtbRoomDB.getParlor();
    }

    @Provides
    public ParlorInfrastructureDao provideParlorInfrastructure(RTBRoomDB rtbRoomDB){
        return rtbRoomDB.getParlorInfrastructure();
    }

    @Provides
    public ParlorServiceDao provideParlorService(RTBRoomDB rtbRoomDB){
        return rtbRoomDB.getParlorService();
    }

//    @Provides
//    public ParlorServicesRelationDao provideParlorServicesRelation(RTBRoomDB rtbRoomDB){
//        return rtbRoomDB.parlorServicesRelationDao();
//    }

//    @Singleton
//    @Provides
//    public Executor provideExecutor(){
//        return Executors.newSingleThreadExecutor();
//    }


    //datarepository
    @Provides
    public DataRepository provideDataRepository(RetrofitAPI retrofitAPI, BannerDao bannerDao,Application rtbApp,
                                                ServiceCategoryDao serviceCategoryDao,UserReviewDao userReviewDao,
                                                ParlorDao parlorDao,ParlorInfrastructureDao parlorInfrastructureDao,
                                                ParlorServiceDao parlorServiceDao){//},ParlorServicesRelationDao parlorServicesRelationDao){
        return new DataRepository(retrofitAPI,bannerDao,rtbApp,serviceCategoryDao,userReviewDao,parlorDao,
                parlorInfrastructureDao,parlorServiceDao);//,parlorServicesRelationDao);
    }

    //factory view model
    @Provides
    public BannerViewModelFactory provideBannerViewModelFactory(Application rtbApp,DataRepository dataRepository){
        return new BannerViewModelFactory(rtbApp,dataRepository);
    }

    @Provides
    public ServiceCategoryViewModelFactory provideServiceCategoryViewModelFactory(Application rtbApp,DataRepository dataRepository){
        return  new ServiceCategoryViewModelFactory(rtbApp,dataRepository);
    }

    @Provides
    public UserReviewViewModelFactory provideUserReviewViewModelFactory(Application rtbApp, DataRepository dataRepository){
        return new UserReviewViewModelFactory(rtbApp,dataRepository);
    }

    @Provides
    public ParlorViewModelFactory provideParlorViewModelFactory(Application rtbApp, DataRepository dataRepository){
        return new ParlorViewModelFactory(rtbApp,dataRepository);
    }

    @Provides
    public ParlorInfrastructureViewModelFactory provideParlorInfrastructureViewModelFactory(Application rtbApp,DataRepository dataRepository){
        return new ParlorInfrastructureViewModelFactory(rtbApp,dataRepository);
    }

    @Provides
    public ParlorServicesViewModelFactory provideParlorServicesViewModelFactory(Application rtbApp,DataRepository dataRepository){
        return new ParlorServicesViewModelFactory(rtbApp,dataRepository);
    }

//    @Provides
//    public ParlorServicesRelationViewModelFactory provideParlorServicesRelationViewModelFactory(Application rtbApp, DataRepository dataRepository){
//        return new ParlorServicesRelationViewModelFactory(rtbApp,dataRepository);
//    }

    //sharedpreferences
    @Provides
    public SharedPreferences provideSharedPreference(Application rtbApp){
        return rtbApp.getSharedPreferences(rtbApp.getString(R.string.preference_file_name),Context.MODE_PRIVATE);
    }

    //sharedpreferences editor
    @Provides
    public SharedPreferences.Editor provideSharedPreferenceEditor(SharedPreferences sharedPreferences){
        return sharedPreferences.edit();
    }
}
