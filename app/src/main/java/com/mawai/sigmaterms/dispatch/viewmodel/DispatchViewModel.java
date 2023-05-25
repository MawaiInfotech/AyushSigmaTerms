package com.mawai.sigmaterms.dispatch.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mawai.sigmaterms.dispatch.model.DispatchInsertModel;
import com.mawai.sigmaterms.dispatch.model.DispatchModel;
import com.mawai.sigmaterms.dispatch.repo.DispatchRepo;
import com.mawai.sigmaterms.dispatch.response.DispatchInsertResponse;
import com.mawai.sigmaterms.dispatch.response.DispatchResponse;
import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingModel;
import com.mawai.sigmaterms.mastercartonpacking.repo.MasterCartonPackingRepo;
import com.mawai.sigmaterms.mastercartonpacking.response.MasterCartonPackingResponse;

public class DispatchViewModel extends ViewModel {

    private DispatchRepo dispatchRepo;

    public DispatchViewModel() {
        dispatchRepo = DispatchRepo.getInstance();
//        loginModelLiveData.setValue(new LoginModel());
    }

    public LiveData<DispatchResponse> callGetChkPackingDisp(DispatchModel dispatchModel) {
        return dispatchRepo.getChkPackingDisp(dispatchModel);
    }

    public LiveData<DispatchInsertResponse> callGetSavePackingDisp(DispatchInsertModel model) {
        return dispatchRepo.getSavePackingDisp(model);
    }
}
