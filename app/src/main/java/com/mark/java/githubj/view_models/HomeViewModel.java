package com.mark.java.githubj.view_models;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.mark.java.githubj.base.IBaseListener;
import com.mark.java.githubj.data.LoginUser;
import com.mark.java.githubj.data.ReceivedEvent;
import com.mark.java.githubj.data.UserManasger;
import com.mark.java.githubj.repository.HomeRepository;
import com.mark.java.githubj.repository.LoginRepository;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/12/05
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
public class HomeViewModel extends ViewModel {

    private HomeRepository mHomeRepository;
    public MutableLiveData<List<ReceivedEvent>> events = new MutableLiveData<>();
    public MutableLiveData<Boolean> refreshing = new MutableLiveData<>();

    public HomeViewModel(HomeRepository homeRepository) {
        mHomeRepository = homeRepository;
        initReceivedEvents();
    }

    public void initReceivedEvents() {
        refreshing.setValue(true);
        queryReceivedEvents(1);
    }

    public void queryReceivedEvents(int pageIndex){
        mHomeRepository.queryReceivedEvents(pageIndex,new IBaseListener<List<ReceivedEvent>>() {
            @Override
            public void onSuccess(List<ReceivedEvent> receivedEvents) {
                refreshing.postValue(false);
            }

            @Override
            public void onError(Throwable exception) {
                refreshing.postValue(false);
            }
        });
    }
}
