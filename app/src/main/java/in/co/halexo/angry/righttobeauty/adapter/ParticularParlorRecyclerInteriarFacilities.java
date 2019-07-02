package in.co.halexo.angry.righttobeauty.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.co.halexo.angry.righttobeauty.OnRecyclerItemClicked;
import in.co.halexo.angry.righttobeauty.R;
import in.co.halexo.angry.righttobeauty.room.ParlorInfrastructure;

public class ParticularParlorRecyclerInteriarFacilities extends RecyclerView.Adapter<ParticularParlorRecyclerInteriarFacilities.HolderViewClass> {
    private Context context;
    private List<ParlorInfrastructure>list;
    private OnRecyclerItemClicked onRecyclerItemClicked;

    public ParticularParlorRecyclerInteriarFacilities(Context context, OnRecyclerItemClicked onRecyclerItemClicked) {
        this.context = context;
        //this.list = list;
        this.onRecyclerItemClicked = onRecyclerItemClicked;
    }

    @NonNull
    @Override
    public HolderViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_view_particular_parlor_interiar_facilities,parent,false);
        return new HolderViewClass(view,onRecyclerItemClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderViewClass holder, int position) {
        if(list!=null) {
            ParlorInfrastructure parlorInfrastructure=list.get(position);
            holder.textView.setText(parlorInfrastructure.getItemName());
        }
    }

    @Override
    public int getItemCount() {
        if(list!=null) {
            return list.size();
        }
        return 0;
    }

    public void setList(List<ParlorInfrastructure> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class HolderViewClass extends RecyclerView.ViewHolder {
        private TextView textView;

        public HolderViewClass(View itemView, final OnRecyclerItemClicked onRecyclerItemClicked) {
            super(itemView);
            textView=itemView.findViewById(R.id.custom_view_particular_parlor_interiar_facilities);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecyclerItemClicked.onItemClicked(getAdapterPosition());
                }
            });
        }
    }
}
