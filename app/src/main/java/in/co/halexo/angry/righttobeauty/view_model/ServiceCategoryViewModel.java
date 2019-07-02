package in.co.halexo.angry.righttobeauty.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import in.co.halexo.angry.righttobeauty.DataRepository;
import in.co.halexo.angry.righttobeauty.room.ServiceCategory;

public class ServiceCategoryViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    ServiceCategoryViewModel(@NonNull Application rtbApp,DataRepository dataRepository) {
        super(rtbApp);
        this.dataRepository=dataRepository;
    }

    public LiveData<List<ServiceCategory>> getServiceCategories(){
        return dataRepository.getServiceCategoryList();
    }

    public LiveData<List<ServiceCategory>> getServiceCategoryById(@NonNull List<Integer> integerList){
        return dataRepository.getServiceCategeoryByCategoryId(integerList);
    }
}
