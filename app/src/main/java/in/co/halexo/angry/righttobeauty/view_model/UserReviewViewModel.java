package in.co.halexo.angry.righttobeauty.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import in.co.halexo.angry.righttobeauty.DataRepository;
import in.co.halexo.angry.righttobeauty.room.UserReview;

public class UserReviewViewModel extends AndroidViewModel {
    private DataRepository dataRepository;

    UserReviewViewModel(@NonNull Application application, DataRepository dataRepository) {
        super(application);
        this.dataRepository=dataRepository;
    }

    public LiveData<List<UserReview>> getAllUserReview(){
        return dataRepository.getUserReiviewList();
    }
}
