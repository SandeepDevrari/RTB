package in.co.halexo.angry.righttobeauty;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import in.co.halexo.angry.righttobeauty.restapi.RetrofitAPI;
import in.co.halexo.angry.righttobeauty.room.Banner;
import in.co.halexo.angry.righttobeauty.room.BannerDao;
import in.co.halexo.angry.righttobeauty.room.Parlor;
import in.co.halexo.angry.righttobeauty.room.ParlorByArea;
import in.co.halexo.angry.righttobeauty.room.ParlorByCity;
import in.co.halexo.angry.righttobeauty.room.ParlorByState;
import in.co.halexo.angry.righttobeauty.room.ParlorDao;
import in.co.halexo.angry.righttobeauty.room.ParlorInfrastructure;
import in.co.halexo.angry.righttobeauty.room.ParlorInfrastructureDao;
import in.co.halexo.angry.righttobeauty.room.ParlorService;
import in.co.halexo.angry.righttobeauty.room.ParlorServiceDao;
import in.co.halexo.angry.righttobeauty.room.ParlorServicesRelation;
import in.co.halexo.angry.righttobeauty.room.ParlorServicesRelationDao;
import in.co.halexo.angry.righttobeauty.room.ServiceCategory;
import in.co.halexo.angry.righttobeauty.room.ServiceCategoryDao;
import in.co.halexo.angry.righttobeauty.room.UserReview;
import in.co.halexo.angry.righttobeauty.room.UserReviewDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class DataRepository {
//    private final ParlorServicesRelationDao parlorServicesRelationDao;
    private final ParlorServiceDao parlorServiceDao;
    private final ParlorInfrastructureDao parlorInfrastructureDao;
    private final ParlorDao parlorDao;
    private final UserReviewDao userReviewDao;
    private final ServiceCategoryDao serviceCategoryDao;
    private final BannerDao bannerDao;
    private final RetrofitAPI retrofitAPI;
    private final Application rtbApp;

    @Inject
    public DataRepository(RetrofitAPI retrofitAPI,BannerDao bannerDao,Application rtbApp,
                          ServiceCategoryDao serviceCategoryDao,UserReviewDao userReviewDao,ParlorDao parlorDao,
                          ParlorInfrastructureDao parlorInfrastructureDao,ParlorServiceDao parlorServiceDao
                          ){//ParlorServicesRelationDao parlorServicesRelationDao
        this.bannerDao=bannerDao;
        this.retrofitAPI=retrofitAPI;
        this.rtbApp=rtbApp;
        this.serviceCategoryDao=serviceCategoryDao;
        this.userReviewDao=userReviewDao;
        this.parlorDao=parlorDao;
        this.parlorInfrastructureDao=parlorInfrastructureDao;
        this.parlorServiceDao=parlorServiceDao;
        //this.parlorServicesRelationDao=parlorServicesRelationDao;
    }

    public LiveData<List<Banner>>getBanners(){
        refreshBanners();
        return bannerDao.getAllBanner();
    }

    public LiveData<List<ServiceCategory>> getServiceCategoryList(){
        refreshServicesCategory();
        return serviceCategoryDao.getAllServicesCategories();
    }

    public LiveData<List<UserReview>> getUserReiviewList(){
        refreshUserReview();
        return userReviewDao.getAllUsersReview();
    }

    public LiveData<List<Parlor>>getParlorList(){
        refreshParlor();
        return parlorDao.getAllParlors();
    }

    public LiveData<List<ParlorInfrastructure>>getParlorInfrastructure(int parlorId){
        return parlorInfrastructureDao.getParlorInfrastructureByParlorId(parlorId);
    }

    public LiveData<List<Integer>>getParlorService(@NonNull int parlor_id){
        return parlorServiceDao.getParlorService(parlor_id);
    }

    public ServiceCategory getServiceCategeoryByCategoryName(@NonNull String name){
        return serviceCategoryDao.getServiceCategoryByName(name);
    }

//    public LiveData<ParlorServicesRelation>getParlorWithAllServices(@NonNull Integer id){
//        return parlorServicesRelationDao.getParlorWithServices(id);
//    }

    @SuppressLint("StaticFieldLeak")
    @SuppressWarnings("unchecked")
    public LiveData<List<ServiceCategory>> getServiceCategeoryByCategoryId(@NonNull List<Integer> integerList){
        final MutableLiveData<List<ServiceCategory>>list=new MutableLiveData<>();
        final List<ServiceCategory> categoryList=new ArrayList<>();
        list.setValue(categoryList);
        new AsyncTask<List<Integer>,Void,Void>(){
            @Override
            protected Void doInBackground(List<Integer>... lists) {
                for(Integer integer:lists[0]){
                    ServiceCategory serviceCategory=serviceCategoryDao.getServiceCategoryById(integer);
                    if(serviceCategory!=null) {
                        categoryList.add(serviceCategory);
                    }
                }
                return null;
            }
        }.execute(integerList);

        return list;
    }

    private void refreshParlor() {
        Call<List<ParlorByState>>call=retrofitAPI.getAllParlors();
        call.enqueue(new Callback<List<ParlorByState>>() {
            @SuppressWarnings("unchecked")
            @Override
            public void onResponse(@NonNull Call<List<ParlorByState>> call, @NonNull Response<List<ParlorByState>> response) {
                try {
                    if (response.body() != null) {
                        new InsertParlor().execute(response.body());
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ParlorByState>> call, @NonNull Throwable t) {

            }
        });
    }

    private void refreshUserReview() {
        Call<List<UserReview>>call=retrofitAPI.getAllUserReviews();
        call.enqueue(new Callback<List<UserReview>>() {
            @SuppressWarnings("unchecked")
            @Override
            public void onResponse(@NonNull Call<List<UserReview>> call, @NonNull Response<List<UserReview>> response) {
                try {
                    if (response.body() != null) {
                        new InsertUserReview().execute(response.body());
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<UserReview>> call, @NonNull Throwable t) {

            }
        });
    }

    private void refreshServicesCategory() {
        Call<List<ServiceCategory>>call=retrofitAPI.getAllServicesCategories();
        call.enqueue(new Callback<List<ServiceCategory>>() {
            @SuppressWarnings("unchecked")
            @Override
            public void onResponse(@NonNull Call<List<ServiceCategory>> call, @NonNull Response<List<ServiceCategory>> response) {
                try{
                    if(response.body()!=null){
                        new InsertServiceCategory().execute(response.body());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ServiceCategory>> call, @NonNull Throwable t) {

            }
        });
    }

    private void refreshBanners() {
                Call<List<Banner>>call=retrofitAPI.getAllBanners();
                call.enqueue(new Callback<List<Banner>>() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void onResponse(@NonNull Call<List<Banner>> call, @NonNull Response<List<Banner>> response) {
                        try {
                            if (response.body() != null) {
                                new InsertBannersTask().execute(response.body());
                                //bannerDao.insertBanners(response.body());
                            }
                        }catch (Exception e){
                            e.printStackTrace();//should be timber
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Banner>> call, @NonNull Throwable t) {
                        //logic for handle the failure
                    }
                });
    }

    @SuppressLint("StaticFieldLeak")
    class InsertBannersTask extends AsyncTask<List<Banner>,Void,Void>{
//        private BannerDao dao;
//        InsertBannersTask(BannerDao dao){
//            this.dao=dao;
//        }

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<Banner>... lists) {
            //dao.insertBanners(lists[0]);
            //List<Banner>tempListBanners=new ArrayList<>();
            if(sBase.isWritablePermission(rtbApp)){
                if(sBase.isStorageWritalbe()){
                    File file=sBase.getPrivateFilePath(rtbApp,".banner");
                    if(file!=null) {
                        boolean isfile;
                        for (final Banner banner : lists[0]) {
                            isfile = true;
                            File tempFile=new File(file, "." + banner.getImage());
                            if(bannerDao.isBannerExist(banner.getImage())>0){
                                if(tempFile.exists()) {
                                    continue;
                                }
                                isfile=false;
                            }
                            String tempUrl = rtbApp.getString(R.string.website_base_url) + banner.getBaseUrl() + banner.getImage();
                            tempUrl = tempUrl.replaceAll("\\\\", "");

                            File outputFile = new File(file, "." + banner.getImage());

                            Bitmap bitmap ;
//                            HttpURLConnection connection = null;
//                            InputStream is = null;
//                            ByteArrayOutputStream out = null;
//                            try{
//                                connection= (HttpURLConnection) new URL(tempUrl).openConnection();
//                                connection.connect();
//                                final int len=connection.getContentLength();
//                                if(len<=0){
//                                    this.cancel(true);
//                                }
//                                is=new BufferedInputStream(connection.getInputStream(),len);
//                                out=new ByteArrayOutputStream();
//                                byte[] bytes=new byte[len];
//                                int count=0;
//                                //long read=0;
//                                while((count=is.read(bytes))!=-1){
//                                    //read+=count;
//                                    out.write(bytes,0,count);
//                                }
//                                bitmap=BitmapFactory.decodeByteArray(out.toByteArray(),0,out.size());
                                bitmap=getBitmapFromUrl(tempUrl);
                                sBase.storeImage(bitmap, outputFile);
//                            }catch(Exception e){
//                                e.printStackTrace();
//                            }
//                            finally {
//                                try {
//                                    if (connection != null)
//                                        connection.disconnect();
//                                    if (out != null) {
//                                        out.flush();
//                                        out.close();
//                                    }
//                                    if (is != null)
//                                        is.close();
//
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }


//                            OkHttpClient httpClient=new OkHttpClient();
//
//                            Request request=new Request.Builder()
//                                            .url(tempUrl)
//                                            .build();
//
//                            httpClient.newCall(request).enqueue(new okhttp3.Callback() {
//                                @Override
//                                public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
//                                    Log.v("######","failed");
//                                }
//
//                                @Override
//                                public void onResponse(@NonNull okhttp3.Call call, @NonNull okhttp3.Response response) throws IOException {
//                                    ResponseBody responseBody = response.body();
//                                    if (responseBody != null) {
//                                        InputStream inputStream = null;
//                                        FileOutputStream fileOutputStream = null;
//                                        try {
//                                            inputStream = responseBody.byteStream();
//                                            fileOutputStream = new FileOutputStream(outputFile);
//                                            int c;
//                                            while ((c = inputStream.read()) != -1) {
//                                                fileOutputStream.write(c);
//                                            }
//                                        } catch (IOException e) {
//                                            e.printStackTrace();
//                                        } finally {
//                                            if (inputStream != null) {
//                                                inputStream.close();
//                                            }
//                                            if (fileOutputStream != null) {
//                                                fileOutputStream.close();
//                                            }
//                                        }
//                                        Log.v("$$$$$$$$$$", "here");
//                                        //sBase.storeImage(bitmap, outputFile);
//                                    }
//                                }
//                            });
                            if (isfile) {
                                banner.setLocalPath(outputFile.getAbsolutePath());
                                Log.v("%%%% ",""+bannerDao.insertBanners(banner));
                                //List<Banner>ll=bannerDao.getAllBannerList();
                                //Log.v("%%%",""+ll.size());
                                //tempListBanners.add(banner);
                                //Log.v("DDDDDD ",banner.getLocalPath());
                            }
                        }
                    }
                        //dao.updateBanner(banner);
                }else {
                    //no storage
                 }
            }else{
                // no write permission
            }
            //dao.insertBanners(tempListBanners);

            return null;//bannerDao.getAllBannerList();
        }

//        @Override
//        protected void onPostExecute(List<Banner> list) {
//            //super.onPostExecute(list);
//
//            if(list.size()>0) {
//                Log.v("$$$$$$$$$$", "damm!!");
//                for (Banner b : list) {
//                    Log.v("################", b.getLocalPath());
//                }
//            }else {
//                Log.v("NONONONON","NOOOOOO");
//            }
//            Log.v("$$$$$$$$$$", "damm!!"+list.size());
//        }
    }

    private Bitmap getBitmapFromUrl(String tempUrl) {
        Bitmap bitmap = null;
        HttpURLConnection connection = null;
        InputStream is = null;
        ByteArrayOutputStream out = null;
        try{
            connection= (HttpURLConnection) new URL(tempUrl).openConnection();
            connection.connect();
            final int len=connection.getContentLength();
            if(len>0) {
                is = new BufferedInputStream(connection.getInputStream(), len);
                out = new ByteArrayOutputStream();
                byte[] bytes = new byte[len];
                int count ;
                //long read=0;
                while ((count = is.read(bytes)) != -1) {
                    //read+=count;
                    out.write(bytes, 0, count);
                }
                bitmap = BitmapFactory.decodeByteArray(out.toByteArray(), 0, out.size());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                if (connection != null)
                    connection.disconnect();
                if (out != null) {
                    out.flush();
                    out.close();
                }
                if (is != null)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    @SuppressLint("StaticFieldLeak")
    class InsertServiceCategory extends AsyncTask<List<ServiceCategory>,Void,Void>{

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<ServiceCategory>... lists) {
            if(sBase.isWritablePermission(rtbApp)) {
                if (sBase.isStorageWritalbe()) {
                    File file = sBase.getPrivateFilePath(rtbApp, ".service_category");
                    if (file != null) {
                        boolean isfile ;
                        for(ServiceCategory serviceCategory:lists[0]){
                            isfile = true;
                            File tempFile=new File(file, "." + serviceCategory.getImageUrl());
                            if(serviceCategoryDao.isServiceCategoryExist(serviceCategory.getImageUrl())>0){
                                if(tempFile.exists()) {
                                    continue;
                                }
                                isfile=false;
                            }
                            String tempUrl = rtbApp.getString(R.string.website_base_url) + serviceCategory.getBaseUrl() + serviceCategory.getImageUrl();
                            tempUrl = tempUrl.replaceAll("\\\\", "");

                            File outputFile = new File(file, "." + serviceCategory.getImageUrl());

                            Bitmap bitmap = null;
                            bitmap=getBitmapFromUrl(tempUrl);
                            sBase.storeImage(bitmap, outputFile);
                            if (isfile) {
                                serviceCategory.setLocalPath(outputFile.getAbsolutePath());
                                Log.v("%%%% ", "" + serviceCategoryDao.insertServiceCategory(serviceCategory));
                            }
                        }
                    }
                }else{
                    //storage not writeable
                }
            }else {
                //no write permission
            }
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    class InsertUserReview extends AsyncTask<List<UserReview>,Void,Void>{

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<UserReview>... lists) {
            for(UserReview userReview:lists[0]){
                if(userReviewDao.isUserReviewExist(userReview.getUserName())>0){
                    continue;
                }
                Log.v("%%%% ", "" + userReviewDao.insertUserReview(userReview));
            }
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    class InsertParlor extends AsyncTask<List<ParlorByState>,Void,Void>{

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<ParlorByState>... lists) {
            if(sBase.isWritablePermission(rtbApp)) {
                if (sBase.isStorageWritalbe()) {
                    File file = sBase.getPrivateFilePath(rtbApp, ".service_category");
                    if (file != null) {
                        boolean isfile;
                        for (ParlorByState state : lists[0]) {
                            List<ParlorByCity> parlorByCityTemp = state.getParlorByCity();
                            for (ParlorByCity city : parlorByCityTemp) {
                                List<ParlorByArea> parlorByAreasTemp = city.getParlorByArea();
                                for (ParlorByArea area : parlorByAreasTemp) {
                                    Parlor parlor = area.getParlor();
                                    isfile = true;
                                    File tempFile=new File(file, "." + parlor.getImageBaseUrl());
                                    if (parlorDao.isParlorExist(parlor.getParlorName()) > 0) {
                                        if(tempFile.exists()) {
                                            continue;
                                        }
                                        isfile=false;
                                    }

                                    parlor.setState(state.getState());
                                    parlor.setCity(city.getCity());
                                    parlor.setArea(area.getArea());

                                    String tempUrl = rtbApp.getString(R.string.website_base_url) + parlor.getImageBaseUrl() + parlor.getParlorImage();
                                    tempUrl = tempUrl.replaceAll("\\\\", "");

                                    File outputFile = new File(file, "." + parlor.getParlorImage());

                                    Bitmap bitmap = null;
                                    bitmap=getBitmapFromUrl(tempUrl);
                                    sBase.storeImage(bitmap, outputFile);
                                    if (isfile) {
                                        parlor.setLocalPath(outputFile.getAbsolutePath());
                                        Log.v("%%%% ", "" + parlorDao.insertParlor(parlor));
                                        for(ParlorInfrastructure parlorInfrastructure:parlor.getParlorInfrastructures()) {
                                            parlorInfrastructure.setParlorId(parlor.getId());
                                            parlorInfrastructureDao.insertParlorInfrastructure(parlorInfrastructure);
                                        }
                                        for (ServiceCategory serviceCategory:parlor.getParlorServices()){
                                            ParlorService parlorService=new ParlorService(parlor.getId(),(getServiceCategeoryByCategoryName(serviceCategory.getCategoryHeading())).getCategoryId());
                                            parlorServiceDao.insertParlorService(parlorService);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }else{
                    //storage not writeable
                }
            }else{
                //no write permission
            }
            return null;
        }
    }
}
