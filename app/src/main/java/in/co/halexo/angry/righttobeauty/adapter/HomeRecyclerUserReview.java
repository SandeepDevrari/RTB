package in.co.halexo.angry.righttobeauty.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import in.co.halexo.angry.righttobeauty.OnRecyclerItemClicked;
import in.co.halexo.angry.righttobeauty.R;
import in.co.halexo.angry.righttobeauty.room.UserReview;

public class HomeRecyclerUserReview extends RecyclerView.Adapter<HomeRecyclerUserReview.HolderViewClass>{
    private Context context;
    private List<UserReview> userReviewList;
    private OnRecyclerItemClicked onRecyclerItemClicked;

    public HomeRecyclerUserReview(Context context, OnRecyclerItemClicked onRecyclerItemClicked) {
        this.context = context;
        //this.userReviewList = userReviewList;
        this.onRecyclerItemClicked = onRecyclerItemClicked;
    }

    @NonNull
    @Override
    public HomeRecyclerUserReview.HolderViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_view_home_user_review,parent,false);
        return new HomeRecyclerUserReview.HolderViewClass(view,onRecyclerItemClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerUserReview.HolderViewClass holder, int position) {
        if(userReviewList!=null) {
            UserReview userReview = userReviewList.get(position);
//        String tempUrl=context.getString(R.string.website_base_url)+serviceCategory.getBaseUrl()+serviceCategory.getImageUrl();
//        tempUrl=tempUrl.replaceAll("\\\\","");

//        Picasso.Builder builder = new Picasso.Builder(context);
//        builder.downloader(new OkHttp3Downloader(context));
//        builder.build().load(tempUrl)
//                .placeholder((R.drawable.logo))
//                .error(R.drawable.beauty_icon)
//                .into(holder.imageView);

            holder.name.setText(userReview.getUserName());
            holder.review.setText(userReview.getUserReview());
        }
    }

    @Override
    public int getItemCount() {
        if(userReviewList!=null) {
            return userReviewList.size();
        }
        return 0;
    }

    public void setUserReviewList(List<UserReview> userReviewList) {
        this.userReviewList = userReviewList;
        notifyDataSetChanged();
    }

    public class HolderViewClass extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView review,name;
        private OnRecyclerItemClicked onRecyclerItemClicked;

        HolderViewClass(View itemView,OnRecyclerItemClicked onRecyclerItemClicked) {
            super(itemView);
            this.onRecyclerItemClicked=onRecyclerItemClicked;
            name=itemView.findViewById(R.id.home_user_review_name);
            review=itemView.findViewById(R.id.home_user_review_text);
            review.setMovementMethod(new ScrollingMovementMethod());
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRecyclerItemClicked.onItemClicked(getAdapterPosition());
        }
    }
}
