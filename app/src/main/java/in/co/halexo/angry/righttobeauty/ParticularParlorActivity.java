package in.co.halexo.angry.righttobeauty;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import in.co.halexo.angry.righttobeauty.adapter.HomeRecyclerOurServiceCategory;
import in.co.halexo.angry.righttobeauty.adapter.ParticularParlorRecyclerInteriarFacilities;
import in.co.halexo.angry.righttobeauty.room.Parlor;
import in.co.halexo.angry.righttobeauty.room.ParlorInfrastructure;
import in.co.halexo.angry.righttobeauty.room.ParlorServicesRelation;
import in.co.halexo.angry.righttobeauty.room.ServiceCategory;
import in.co.halexo.angry.righttobeauty.view_model.ParlorInfrastructureViewModel;
import in.co.halexo.angry.righttobeauty.view_model.ParlorInfrastructureViewModelFactory;
import in.co.halexo.angry.righttobeauty.view_model.ParlorServicesRelationViewModel;
import in.co.halexo.angry.righttobeauty.view_model.ParlorServicesRelationViewModelFactory;
import in.co.halexo.angry.righttobeauty.view_model.ParlorServicesViewModel;
import in.co.halexo.angry.righttobeauty.view_model.ParlorServicesViewModelFactory;
import in.co.halexo.angry.righttobeauty.view_model.ServiceCategoryViewModel;
import in.co.halexo.angry.righttobeauty.view_model.ServiceCategoryViewModelFactory;

public class ParticularParlorActivity extends AppCompatActivity {

    @Inject
    Application rtbApp;

    @Inject
    ParlorInfrastructureViewModelFactory parlorInfrastructureViewModelFactory;

    @Inject
    ParlorServicesViewModelFactory parlorServicesViewModelFactory;

    @Inject
    ServiceCategoryViewModelFactory serviceCategoryViewModelFactory;

//    @Inject
//    ParlorServicesRelationViewModelFactory parlorServicesRelationViewModelFactory

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RTBApp)getApplication()).getAppComponentDagger().inject(this);
        setContentView(R.layout.activity_parlor_particular);
        setUpUi();
    }

    private void setUpUi() {
        Toolbar toolbar=findViewById(R.id.particular_parlor_collapsing_toolbar_appbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Parlor parlor = (Parlor) getIntent().getSerializableExtra(sBase.PARLOR);
        ImageView imageView=findViewById(R.id.particular_parlor_parlor_image);

        final File file=new File(parlor.getLocalPath());
        if(file.exists()) {
            Picasso.Builder builder = new Picasso.Builder(this);
            builder.build().load(file)
                    .placeholder((R.drawable.shaded_image_view))
                    .error(R.drawable.beauty_icon)
                    .into(imageView);
        }else{
                imageView.setImageResource(R.drawable.shaded_image_view);
        }
//        String tempUrl=getString(R.string.website_base_url)+ parlor.getImageBaseUrl()+ parlor.getParlorImage();
//        tempUrl=tempUrl.replaceAll("\\\\","");
//
//        Picasso.Builder builder = new Picasso.Builder(this);
//        builder.downloader(new OkHttp3Downloader(this));
//        builder.build().load(tempUrl)
//                .placeholder((R.drawable.parlor))
//                .error(R.drawable.beauty_icon)
//                .into(imageView);

        ((TextView)findViewById(R.id.particular_parlor_parlor_name)).setText(parlor.getParlorName());

        RecyclerView recyclerView=findViewById(R.id.particular_parlor_recycler_our_services);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL ,false));

        ParlorServicesViewModel parlorServicesViewModel= ViewModelProviders.of(this,parlorServicesViewModelFactory).get(ParlorServicesViewModel.class);
        final ServiceCategoryViewModel serviceCategoryViewModel=ViewModelProviders.of(this,serviceCategoryViewModelFactory).get(ServiceCategoryViewModel.class);

//        final ParlorServicesRelationViewModel parlorServicesRelationViewModel=ViewModelProviders.of(this,parlorServicesRelationViewModelFactory).get(ParlorServicesRelationViewModel.class);
        final HomeRecyclerOurServiceCategory parlorRecyclerOurServiceCategory = new HomeRecyclerOurServiceCategory(this, new OnRecyclerItemClicked() {
            @Override
            public void onItemClicked(int pos) {
                Toast.makeText(ParticularParlorActivity.this, "" + pos, Toast.LENGTH_SHORT).show();
            }
        });

//        parlorServicesRelationViewModel.getParlorServicesRelationById(parlor.getId()).observe(this, new Observer<ParlorServicesRelation>() {
//            @Override
//            public void onChanged(@Nullable ParlorServicesRelation parlorServicesRelation) {
//
//            }
//        });
        parlorServicesViewModel.getAllParlorServicesId(parlor.getId()).observe(this, new Observer<List<Integer>>() {
            @Override
            public void onChanged(@Nullable List<Integer> integers) {
                if(integers!=null){
                    serviceCategoryViewModel.getServiceCategoryById(integers).observe(ParticularParlorActivity.this, new Observer<List<ServiceCategory>>() {
                        @Override
                        public void onChanged(@Nullable List<ServiceCategory> serviceCategories) {
                            if(serviceCategories!=null) {
                                parlorRecyclerOurServiceCategory.setServiceCategoryList(serviceCategories);
                            }
                        }
                    });
                }
            }
        });

        recyclerView.setAdapter(parlorRecyclerOurServiceCategory);

        RecyclerView recyclerView1=findViewById(R.id.particular_parlor_recycler_interiar_facilities);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL ,false));

        ParlorInfrastructureViewModel parlorInfrastructureViewModel=ViewModelProviders.of(this,parlorInfrastructureViewModelFactory).get(ParlorInfrastructureViewModel.class);

//        final ParlorInfrastructure parlorInfrastructure= parlor.getParlorInfrastructures();
//        if(parlorInfrastructure!=null){
//            final List<String>list= new ArrayList<>();
//            if(Integer.parseInt(parlorInfrastructure.getAc())>0){
//                list.add("AC");
//            }
//            if(Integer.parseInt(parlorInfrastructure.getAc())>0){
//                list.add("Shampoo Station");
//            }
//            if(Integer.parseInt(parlorInfrastructure.getAc())>0){
//                list.add("Chair");
//            }
//            if(Integer.parseInt(parlorInfrastructure.getAc())>0){
//                list.add("Padicure-Manicure");
//            }
//            if(Integer.parseInt(parlorInfrastructure.getAc())>0){
//                list.add("Facial-Bed");
//            }
//            if(Integer.parseInt(parlorInfrastructure.getAc())>0){
//                list.add("Employees");
//            }
//            if(Integer.parseInt(parlorInfrastructure.getAc())>0){
//                list.add("Capacity-Day");
//            }
//            if(Integer.parseInt(parlorInfrastructure.getAc())>0){
//                list.add("Capacity-Time");
//            }

        final ParticularParlorRecyclerInteriarFacilities recyclerInteriarFacilitiesAdapter=new ParticularParlorRecyclerInteriarFacilities(this, new OnRecyclerItemClicked() {
            @Override
            public void onItemClicked(int pos) {
//                    String infra=list.get(pos);
//                    String count="";
//                    if(infra.equals("AC")){
//                        count=parlorInfrastructure.getAc();
//                    }else if(infra.equals("Shampoo Station")){
//                        count=parlorInfrastructure.getShampooStation();
//                    }else if(infra.equals("Chair")){
//                        count=parlorInfrastructure.getChair();
//                    }else if(infra.equals("Padicure-Manicure")){
//                        count=parlorInfrastructure.getPadicureManicure();
//                    }else if(infra.equals("Facial-Bed")){
//                        count=parlorInfrastructure.getFacialBed();
//                    }else if(infra.equals("Employees")){
//                        count=parlorInfrastructure.getEmployees();
//                    }else if(infra.equals("Capacity-Day")){
//                        count=parlorInfrastructure.getCapacityPerDay();
//                    }else if(infra.equals("Capacity-Time")){
//                        count=parlorInfrastructure.getCapacityPerTime();
//                    }
                    Toast.makeText(ParticularParlorActivity.this,""+pos, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView1.setAdapter(recyclerInteriarFacilitiesAdapter);

        parlorInfrastructureViewModel.getParlorInfrastructureByParlor(parlor.getId()).observe(this, new Observer<List<ParlorInfrastructure>>() {
            @Override
            public void onChanged(@Nullable List<ParlorInfrastructure> parlorInfrastructures) {
                if(parlorInfrastructures!=null){
                    recyclerInteriarFacilitiesAdapter.setList(parlorInfrastructures);
                }
            }
        });

        ((TextView)findViewById(R.id.particular_parlor_contact_us_contact_number)).setText(parlor.getOwnerContact());
        ((TextView)findViewById(R.id.particular_parlor_contact_us_owner_name)).setText(parlor.getOwnerName());
        ((TextView)findViewById(R.id.particular_parlor_contact_us_email_address)).setText(parlor.getOwnerEmail());
        //}
    }
}
