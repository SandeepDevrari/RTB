package in.co.halexo.angry.righttobeauty;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import in.co.halexo.angry.righttobeauty.view_model.BannerViewModel;
import in.co.halexo.angry.righttobeauty.view_model.BannerViewModelFactory;

//@Module
public abstract class ViewModuleDagger {
    //@Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(BannerViewModelFactory viewModelFactory);

   // @Binds
    //@IntoMap
    //@ViewModuleKeyDagger(BannerViewModel.class)
    abstract ViewModel userViewModel(BannerViewModel userViewModel);
}
