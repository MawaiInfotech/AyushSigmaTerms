package com.mawai.sigmaterms.unpack.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mawai.sigmaterms.dispatch.model.DispatchModel;
import com.mawai.sigmaterms.dispatch.repo.DispatchRepo;
import com.mawai.sigmaterms.dispatch.response.DispatchResponse;
import com.mawai.sigmaterms.unpack.model.UnPackModel;
import com.mawai.sigmaterms.unpack.repo.UnPackRepo;
import com.mawai.sigmaterms.unpack.response.UnPackResponse;

public class UnPackViewModel extends ViewModel {

    private UnPackRepo unPackRepo;

    public UnPackViewModel() {
        unPackRepo = UnPackRepo.getInstance();
//        loginModelLiveData.setValue(new LoginModel());
    }

    public LiveData<UnPackResponse> callGetChkAndUnPackBarcode(UnPackModel model) {
        return unPackRepo.getChkAndUnPackBarcode(model);
    }
}
