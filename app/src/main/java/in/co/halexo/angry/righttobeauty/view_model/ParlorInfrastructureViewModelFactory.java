package in.co.halexo.angry.righttobeauty.view_model;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import in.co.halexo.angry.righttobeauty.DataRepository;

public class ParlorInfrastructureViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application rtbApp;
    private DataRepository dataRepository;

    @Inject
    public ParlorInfrastructureViewModelFactory(Application rtbApp, DataRepository dataRepository) {
        this.rtbApp = rtbApp;
        this.dataRepository = dataRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ParlorInfrastructureViewModel(rtbApp,dataRepository);
    }
}
