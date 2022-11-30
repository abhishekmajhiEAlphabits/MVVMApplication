package com.example.mvvmapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    MutableLiveData<String> mLoginResultData = new MutableLiveData<>();
    MainRepository mRepository;

    public MainViewModel() {
        mRepository = new MainRepository();
    }

    public void login(String email, String password) {
        mRepository.loginRemote(new LoginBody(email, password), new MainRepository.ILoginResponse() {
            @Override
            public void onResponse(LoginResponse loginResponse) {
                mLoginResultData.postValue("Success" + " " + "Token: " + loginResponse.getToken());
            }

            @Override
            public void onFailure(Throwable t) {
                mLoginResultData.postValue("Failed");
            }
        });
    }

    public LiveData<String> getLoginResult() {
        return mLoginResultData;
    }
}
