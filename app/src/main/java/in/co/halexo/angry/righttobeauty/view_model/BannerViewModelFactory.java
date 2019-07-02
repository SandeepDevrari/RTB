package in.co.halexo.angry.righttobeauty.view_model;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import in.co.halexo.angry.righttobeauty.DataRepository;
import in.co.halexo.angry.righttobeauty.RTBApp;

public class BannerViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private DataRepository dataRepository;
    private Application rtbApp;
    //private final Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModels;


    @Inject
    public BannerViewModelFactory(Application rtbApp,DataRepository dataRepository){
        this.rtbApp=rtbApp;
        this.dataRepository=dataRepository;
    }

    @NonNull
    @Override

    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new BannerViewModel(rtbApp,dataRepository);
//        if(modelClass.isAssignableFrom(BannerViewModel.class)){
//            try {
//                return  modelClass.getConstructor(Application.class).newInstance(rtbApp);
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//        }
//        throw new IllegalArgumentException("unknown viewmodel class");
//        Provider<ViewModel> viewModelProvider = viewModels.get(modelClass);
//
//        if (viewModelProvider == null) {
//            throw new IllegalArgumentException("model class " + modelClass + " not found");
//        }
//
//        return (T) viewModelProvider.get();
    }
}
