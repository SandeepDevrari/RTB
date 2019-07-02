package in.co.halexo.angry.righttobeauty;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModuleDagger.class})
public interface AppComponentDagger {
    void inject(RTBApp app);
    void inject(HomeActivity homeActivity);
    void inject(ActivitySplash activitySplash);
    void inject(ActivityWelcome activityWelcome);
    void inject(ParlorActivity parlorActivity);
    void inject(ActivityLoginSignup activityLoginSignup);
    void inject(ParticularParlorActivity particularParlorActivity);
}
