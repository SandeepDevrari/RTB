package in.co.halexo.angry.righttobeauty.restapi;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Provides;
import in.co.halexo.angry.righttobeauty.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiInstance {
//    private static Retrofit retrofit;
//
//    @Singleton
//    @Provides
//    public static Retrofit getRetrofit(Context context){
//        if(retrofit==null){
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            OkHttpClient client= new OkHttpClient
//                    .Builder()
//                    .connectTimeout(30, TimeUnit.SECONDS)
//                    .readTimeout(30, TimeUnit.SECONDS)
//                    .addInterceptor(interceptor).build();
//            Gson gson=new GsonBuilder()
//                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
//                    .create();
//            retrofit=new Retrofit.Builder()
//                    .baseUrl(context.getString(R.string.api_base_url))
//                    .client(client)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build();
//        }
//        return retrofit;
//    }
}
