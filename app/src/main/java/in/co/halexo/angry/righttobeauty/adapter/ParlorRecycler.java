package in.co.halexo.angry.righttobeauty.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
import in.co.halexo.angry.righttobeauty.room.Parlor;

public class ParlorRecycler extends RecyclerView.Adapter<ParlorRecycler.HolderViewClass> {
    private Context context;
    private List<Parlor> parlorList;
    private OnRecyclerItemClicked onRecyclerItemClicked;

    public ParlorRecycler(Context context, OnRecyclerItemClicked onRecyclerItemClicked) {
        this.context = context;
        //this.parlorList = parlorList;
        this.onRecyclerItemClicked = onRecyclerItemClicked;
    }

    @NonNull
    @Override
    public HolderViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_view_parlor_recycler,parent,false);
        return new HolderViewClass(view,onRecyclerItemClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderViewClass holder, int position) {
        if(parlorList!=null) {
            Parlor parlor = parlorList.get(position);
            final File file=new File(parlor.getLocalPath());
            if(file.exists()) {
//            String tempUrl = context.getString(R.string.website_base_url) + parlor.getImageBaseUrl() + parlor.getParlorImage();
//            tempUrl = tempUrl.replaceAll("\\\\", "");
//
            Picasso.Builder builder = new Picasso.Builder(context);
//            builder.downloader(new OkHttp3Downloader(context));
            builder.build().load(file)
                    .placeholder((R.drawable.shaded_image_view))
                    .error(R.drawable.beauty_icon)
                    .into(holder.imageView);
            }else{
                holder.imageView.setImageResource(R.drawable.shaded_image_view);
            }
            holder.name.setText(parlor.getParlorName());
            String tempAddress = parlor.getArea() + "," + parlor.getCity() + "," + parlor.getState();
            holder.address.setText(tempAddress);
        }
    }

    @Override
    public int getItemCount() {
        if(parlorList!=null) {
            return parlorList.size();
        }
        return 0;
    }

    public void setParlorList(List<Parlor> parlorList) {
        this.parlorList = parlorList;
        notifyDataSetChanged();
    }

    public class HolderViewClass extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView name,address;
        private OnRecyclerItemClicked onRecyclerItemClicked;
        public HolderViewClass(View itemView, OnRecyclerItemClicked onRecyclerItemClicked) {
            super(itemView);
            this.onRecyclerItemClicked=onRecyclerItemClicked;
            imageView=itemView.findViewById(R.id.parlor_image);
            name=itemView.findViewById(R.id.parlor_name_text);
            address=itemView.findViewById(R.id.parlor_address_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRecyclerItemClicked.onItemClicked(getAdapterPosition());
        }
    }
}
