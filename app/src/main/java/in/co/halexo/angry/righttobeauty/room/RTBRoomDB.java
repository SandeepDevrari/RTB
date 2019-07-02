package in.co.halexo.angry.righttobeauty.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities={Banner.class,Parlor.class,User.class,
        UserReview.class,ServiceCategory.class,Services.class,ParlorService.class,
        ParlorInfrastructure.class},version = 1,exportSchema = false)
public abstract class RTBRoomDB extends RoomDatabase {
    //private static RTBRoomDB INSTANCE;

    public abstract BannerDao getBanner();
    public abstract ParlorDao getParlor();
    public abstract ServicesDao getServices();
    public abstract UserDao getUser();
    public abstract UserReviewDao getUserReview();
    public abstract ServiceCategoryDao getServiceCategory();
    public abstract ParlorInfrastructureDao getParlorInfrastructure();
    public abstract ParlorServiceDao getParlorService();
//    public abstract ParlorServicesRelationDao parlorServicesRelationDao();

//    public static RTBRoomDB getDatabase(Application application) {
//        if(INSTANCE==null){
//            synchronized (RTBRoomDB.class){
//                INSTANCE= Room.databaseBuilder(application,
//                        RTBRoomDB.class,"rtb_db")
//                        .build();
//            }
//        }
//        return INSTANCE;
//    }
}
