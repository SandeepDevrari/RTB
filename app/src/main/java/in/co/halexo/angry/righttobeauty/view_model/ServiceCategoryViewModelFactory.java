package in.co.halexo.angry.righttobeauty.view_model;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import in.co.halexo.angry.righttobeauty.DataRepository;

public class ServiceCategoryViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private DataRepository dataRepository;
    private Application rtbApp;

    @Inject
    public ServiceCategoryViewModelFactory(Application rtbApp,DataRepository dataRepository) {
        this.dataRepository = dataRepository;
        this.rtbApp = rtbApp;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ServiceCategoryViewModel(rtbApp,dataRepository);
    }
}
