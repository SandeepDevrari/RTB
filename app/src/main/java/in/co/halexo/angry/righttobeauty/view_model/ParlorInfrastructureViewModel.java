package in.co.halexo.angry.righttobeauty.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import in.co.halexo.angry.righttobeauty.DataRepository;
import in.co.halexo.angry.righttobeauty.room.ParlorInfrastructure;

public class ParlorInfrastructureViewModel extends AndroidViewModel {
    private DataRepository dataRepository;

    ParlorInfrastructureViewModel(@NonNull Application application,@NonNull DataRepository dataRepository) {
        super(application);
        this.dataRepository=dataRepository;
    }

    public LiveData<List<ParlorInfrastructure>> getParlorInfrastructureByParlor(int parlorId){
        return dataRepository.getParlorInfrastructure(parlorId);
    }
}
