package in.co.halexo.angry.righttobeauty.restapi;

import java.util.List;

import in.co.halexo.angry.righttobeauty.room.Banner;
import in.co.halexo.angry.righttobeauty.room.ParlorByState;
import in.co.halexo.angry.righttobeauty.room.ServiceCategory;
import in.co.halexo.angry.righttobeauty.room.UserReview;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {

    @GET("right_to_beauty/api/banner_endpoint.php")
    Call<List<Banner>>getAllBanners();

    @GET("right_to_beauty/api/services_category_endpoint.php")
    Call<List<ServiceCategory>>getAllServicesCategories();

    @GET("right_to_beauty/api/user_review_endpoint.php")
    Call<List<UserReview>>getAllUserReviews();

    @GET("right_to_beauty/api/parlor_endpoint.php")
    Call<List<ParlorByState>>getAllParlors();
}
