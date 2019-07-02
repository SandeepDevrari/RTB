package in.co.halexo.angry.righttobeauty.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import in.co.halexo.angry.righttobeauty.DataRepository;

public class ParlorServicesViewModel extends AndroidViewModel {
    private DataRepository dataRepository;

    public ParlorServicesViewModel(@NonNull Application application,@NonNull DataRepository dataRepository) {
        super(application);
        this.dataRepository=dataRepository;
    }

    public LiveData<List<Integer>> getAllParlorServicesId(@NonNull int parlorId){
        return dataRepository.getParlorService(parlorId);
    }
}
