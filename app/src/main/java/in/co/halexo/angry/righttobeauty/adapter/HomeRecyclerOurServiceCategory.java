package in.co.halexo.angry.righttobeauty.adapter;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import in.co.halexo.angry.righttobeauty.OnRecyclerItemClicked;
import in.co.halexo.angry.righttobeauty.R;
import in.co.halexo.angry.righttobeauty.room.ServiceCategory;
import in.co.halexo.angry.righttobeauty.sBase;

public class HomeRecyclerOurServiceCategory extends RecyclerView.Adapter<HomeRecyclerOurServiceCategory.HolderViewClass> {
    private Context context;
    private List<ServiceCategory>serviceCategoryList;
    private OnRecyclerItemClicked onRecyclerItemClicked;

    public HomeRecyclerOurServiceCategory(Context context, OnRecyclerItemClicked onRecyclerItemClicked) {
        this.context = context;
        //this.serviceCategoryList = serviceCategoryList;
        this.onRecyclerItemClicked = onRecyclerItemClicked;
    }


    @NonNull
    @Override
    public HolderViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_view_home_recycler_our_service,parent,false);
        return new HolderViewClass(view,onRecyclerItemClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderViewClass holder, int position) {
        if(serviceCategoryList.size()>0) {
            ServiceCategory serviceCategory = serviceCategoryList.get(position);
            final File file=new File(serviceCategory.getLocalPath());
            if(sBase.checkInternet((Application) context.getApplicationContext())){
                String tempUrl = context.getString(R.string.website_base_url) + serviceCategory.getBaseUrl() + serviceCategory.getImageUrl();
                tempUrl = tempUrl.replaceAll("\\\\", "");

                Picasso.Builder builder = new Picasso.Builder(context);
                builder.downloader(new OkHttp3Downloader(context));
                builder.build().load(tempUrl)
                        .placeholder((R.drawable.shaded_image_view))
                        .error(R.drawable.beauty_icon)
                        .into(holder.imageView);
            }else {
              holder.imageView.setImageResource(R.drawable.shaded_image_view);
            }
            holder.header.setText(serviceCategory.getCategoryHeading());
            holder.description.setText(serviceCategory.getCategoryDescription());
//            if(file.exists()){
//                holder.imageView.setImageURI(Uri.fromFile(file));
//                holder.header.setText(serviceCategory.getCategoryHeading());
//                holder.description.setText(serviceCategory.getCategoryDescription());
//            }

        }else{
            holder.imageView.setImageResource(R.drawable.shaded_image_view);
            holder.description.setBackgroundResource(R.drawable.shaded_image_view);
            holder.header.setBackgroundResource(R.drawable.shaded_image_view);
        }
    }

    @Override
    public int getItemCount() {
        if(serviceCategoryList!=null){
            return serviceCategoryList.size();
        }
        return 0;
    }

    public void setServiceCategoryList(List<ServiceCategory> serviceCategoryList) {
        this.serviceCategoryList = serviceCategoryList;
        notifyDataSetChanged();
    }

    public class HolderViewClass extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView header,description;
        private OnRecyclerItemClicked onRecyclerItemClicked;

        public HolderViewClass(View itemView,OnRecyclerItemClicked onRecyclerItemClicked) {
            super(itemView);
            this.onRecyclerItemClicked=onRecyclerItemClicked;
            imageView=itemView.findViewById(R.id.service_icon_home_recycler_our_service);
            header=itemView.findViewById(R.id.service_text_home_recycler_our_service);
            description=itemView.findViewById(R.id.service_description_home_recycler_our_service);
            description.setMovementMethod(new ScrollingMovementMethod());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRecyclerItemClicked.onItemClicked(getAdapterPosition());
        }
    }

}
