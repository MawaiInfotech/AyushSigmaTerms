package com.mawai.sigmaterms.login.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mawai.sigmaterms.login.model.LoginModel;
import com.mawai.sigmaterms.login.repo.LoginRepo;
import com.mawai.sigmaterms.login.response.LoginResponse;
import com.mawai.sigmaterms.login.response.UnitResponse;


public class LoginViewModel extends ViewModel  {

    private LoginRepo loginRepo;

    public LoginViewModel() {
        loginRepo = LoginRepo.getInstance();
//        loginModelLiveData.setValue(new LoginModel());
    }

    public LiveData<UnitResponse> callGetUnitList() {
        return loginRepo.getUnitList();
    }

    public LiveData<LoginResponse> callGetLoginDetail(LoginModel loginModel) {
        return loginRepo.getLoginDetail(loginModel);
    }


}
