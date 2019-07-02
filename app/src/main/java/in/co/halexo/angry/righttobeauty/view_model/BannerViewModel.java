package in.co.halexo.angry.righttobeauty.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import in.co.halexo.angry.righttobeauty.DataRepository;
import in.co.halexo.angry.righttobeauty.RTBApp;
import in.co.halexo.angry.righttobeauty.room.Banner;

public class BannerViewModel extends AndroidViewModel {
    //private LiveData<List<Banner>>bannerList;

    private DataRepository dataRepository;

    @Inject
    BannerViewModel(Application rtbApp, DataRepository dataRepository) {
        super(rtbApp);
        this.dataRepository=dataRepository;
    }


    public LiveData<List<Banner>> getBannerList() {
        return dataRepository.getBanners();
    }
}
