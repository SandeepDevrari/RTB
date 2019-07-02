package in.co.halexo.angry.righttobeauty;

import android.app.Application;

public class RTBApp extends Application{
    AppComponentDagger appComponentDagger;
    //AppModuleDagger appModuleDagger;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponentDagger=DaggerAppComponentDagger.builder().appModuleDagger(new AppModuleDagger(this)).build();
        appComponentDagger.inject(this);
    }

    public AppComponentDagger getAppComponentDagger() {
        return appComponentDagger;
    }
}
