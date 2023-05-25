package com.mawai.sigmaterms.dispatchcancel.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mawai.sigmaterms.dispatchcancel.model.DispatchCancelModel;
import com.mawai.sigmaterms.dispatchcancel.repo.DispatchCancelRepo;
import com.mawai.sigmaterms.dispatchcancel.response.DispatchCancelResponse;
import com.mawai.sigmaterms.unpack.model.UnPackModel;
import com.mawai.sigmaterms.unpack.repo.UnPackRepo;
import com.mawai.sigmaterms.unpack.response.UnPackResponse;

public class DispatchCancelViewModel extends ViewModel {

    private DispatchCancelRepo dispatchCancelRepo;

    public DispatchCancelViewModel() {
        dispatchCancelRepo = DispatchCancelRepo.getInstance();
//        loginModelLiveData.setValue(new LoginModel());
    }

    public LiveData<DispatchCancelResponse> callGetDispatchCancel(DispatchCancelModel model) {
        return dispatchCancelRepo.getDispatchCancel(model);
    }


}
