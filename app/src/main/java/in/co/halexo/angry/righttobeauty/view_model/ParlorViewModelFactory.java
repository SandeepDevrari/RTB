package in.co.halexo.angry.righttobeauty.view_model;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import in.co.halexo.angry.righttobeauty.DataRepository;

public class ParlorViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application rtbApp;
    private DataRepository dataRepository;

    public ParlorViewModelFactory(Application rtbApp, DataRepository dataRepository) {
        this.rtbApp = rtbApp;
        this.dataRepository = dataRepository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ParlorViewModel(rtbApp,dataRepository);
    }
}
