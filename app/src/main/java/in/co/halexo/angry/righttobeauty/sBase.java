package in.co.halexo.angry.righttobeauty;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Locale;

public class sBase {
    static final int sPermissionAll=100,
            sPermissionCamera = 101,
            sPermissionCamera2 = 102,
            sPermissionReadPhoneState = 103,
            sPermissionReadExternalStorage = 104,
            sPermissionWriteExternalStorage = 105,
            sPermissionFineLocation=106;
    static final String LATITUDE="latitude",LONGITUDE="longitude",ADDRESS="address",PARLOR="parlor";

    public static boolean checkInternet(Application context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected();
    }

    static void requestForAllPermissions(Application rtbApp,Activity activity){
        ArrayList<String>allPermissions=new ArrayList<>();
        if(ActivityCompat.checkSelfPermission(rtbApp,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            allPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if(ActivityCompat.checkSelfPermission(rtbApp,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            allPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(ActivityCompat.checkSelfPermission(rtbApp,Manifest.permission.ACCESS_NETWORK_STATE)!=PackageManager.PERMISSION_GRANTED){
            allPermissions.add(Manifest.permission.ACCESS_NETWORK_STATE);
        }
        if(ActivityCompat.checkSelfPermission(rtbApp,Manifest.permission.INTERNET)!=PackageManager.PERMISSION_GRANTED){
            allPermissions.add(Manifest.permission.INTERNET);
        }
        if(allPermissions.size()>0){
            String[] permissions=new String[allPermissions.size()];
            for (int i=0;i<allPermissions.size();i++){
                permissions[i]=allPermissions.get(i);
            }
            //String[] permissions= (String[]) allPermissions.toArray();
            ActivityCompat.requestPermissions(activity,permissions,sPermissionAll);
        }
    }

    static boolean isReadablePermission(Application rtbApp){
        if(ActivityCompat.checkSelfPermission(rtbApp,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            return false;
        }
        return true;
    }

    static boolean isWritablePermission(Application rtbApp){
        if(ActivityCompat.checkSelfPermission(rtbApp,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            return false;
        }
        return true;
    }

    static boolean isStorageWritalbe(){
        String state= Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }

    static boolean isStorageReadable(){
        String state=Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)||Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true;
        }
        return false;
    }

    static File getPrivateFilePath(Application rtbApp,String folder){
        File file=new File(rtbApp.getExternalFilesDir(Environment.DIRECTORY_PICTURES),folder);
        if(file.exists()){
            return file;
        }else{
            if(!file.mkdir()){
                return null;
            }
            return file;
        }
    }
    public static void storeImage(final Bitmap bm, final File imageDir) {
        try {
            FileOutputStream out = new FileOutputStream(imageDir, false);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
            bm.recycle();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    public static void setLanguage(Context context) {
//        SharedPreferences sharedPreferences=context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
//        setLocale(sharedPreferences.getString("Language","en"),context);
//    }
//    private static void setLocale(String lang, Context context) {
//        Locale myLocale = new Locale(lang);
//        Resources res =context.getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        Configuration conf = res.getConfiguration();
//        conf.locale = myLocale;
//        res.updateConfiguration(conf, dm);
//    }
//    public static boolean checkLocationPermission(Context context) {
//        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED ){
//            return false;
//            //&& ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
//        }
//        return true;
//    }
//    public static void requestForPermission(Activity activity){
//        ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},sBase.sPermissionFineLocation);
//    }
    //Manifest.permission.ACCESS_COARSE_LOCATION,

//    public static boolean isGPSEnabled(Context context){
//        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE );
//        if (manager != null) {
//            return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        }
//        return false;
//    }
}
