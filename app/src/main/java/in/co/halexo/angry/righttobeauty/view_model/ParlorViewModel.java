package in.co.halexo.angry.righttobeauty.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import in.co.halexo.angry.righttobeauty.DataRepository;
import in.co.halexo.angry.righttobeauty.room.Parlor;

public class ParlorViewModel extends AndroidViewModel {
    private DataRepository dataRepository;

    ParlorViewModel(@NonNull Application application,@NonNull DataRepository dataRepository) {
        super(application);
        this.dataRepository=dataRepository;
    }

    public LiveData<List<Parlor>> getAllParlors(){
        return dataRepository.getParlorList();
    }
}
