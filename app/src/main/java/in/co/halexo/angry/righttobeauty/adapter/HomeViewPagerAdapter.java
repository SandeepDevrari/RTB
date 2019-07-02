package in.co.halexo.angry.righttobeauty.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import java.io.File;
import java.util.List;

import in.co.halexo.angry.righttobeauty.R;
import in.co.halexo.angry.righttobeauty.room.Banner;
import in.co.halexo.angry.righttobeauty.sBase;

public class HomeViewPagerAdapter extends PagerAdapter{
    private Context context;
    private List<Banner> list;
    //private String baseUrl;

    public HomeViewPagerAdapter(Context context) {
        this.context = context;
        //this.list = list;
        //this.baseUrl=baseUrl;
    }

    @Override
    public int getCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull View container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_view_home_pager, null);
        final ImageView slide=view.findViewById(R.id.imageView_home_pager_custom_view);

//        StringBuilder stringBuilder=new StringBuilder(baseUrl);
//        stringBuilder.replace(0,)
        if(list.size()>0) {
            Banner banner = list.get(position);
            //Log.v("$$%%%%% ",banner.getLocalPath());
            final File file=new File(banner.getLocalPath());
            //Log.v("$$$$$$$$$ ",file.getAbsolutePath());
            if(file.exists()){
                slide.setImageURI(Uri.fromFile(file));
//                Picasso.Builder builder = new Picasso.Builder(context);
//                //Log.v("TTTTTTT ",file.getAbsolutePath());
//                builder.build().load(file)
//                        .placeholder((R.drawable.shaded_image_view))
//                        .error(R.drawable.beauty_icon)
//                        .into(slide);
            }else{
                slide.setImageResource(R.drawable.shaded_image_view);
                //Log.v("YYYYYYY ","no file");
            }
//            else {
//                String tempUrl = context.getString(R.string.website_base_url) + banner.getBaseUrl() + banner.getImage();
//                tempUrl = tempUrl.replaceAll("\\\\", "");
//                //Toast.makeText(context, ""+tempUrl, Toast.LENGTH_LONG).show();
//                Log.e("%%%%%%%%%", "  " + tempUrl);
//
//                Target target= new Target() {
//                    @Override
//                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                        slide.setImageBitmap(bitmap);
//                        sBase.storeImage(bitmap,file);
//                    }
//
//                    @Override
//                    public void onBitmapFailed(Drawable errorDrawable) {
//
//                    }
//
//                    @Override
//                    public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                    }
//                };
//                Picasso.Builder builder = new Picasso.Builder(context);
//                builder.downloader(new OkHttp3Downloader(context));
//                builder.build().load(tempUrl)
//                        .placeholder((R.drawable.logo))
//                        .error(R.drawable.beauty_icon)
//                        .into(target);
//            }

        }
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }

    public void setList(List<Banner>list){
        this.list=list;
        notifyDataSetChanged();
    }
}
