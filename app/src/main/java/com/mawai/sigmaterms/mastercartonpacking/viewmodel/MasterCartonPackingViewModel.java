package com.mawai.sigmaterms.mastercartonpacking.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mawai.sigmaterms.login.repo.LoginRepo;
import com.mawai.sigmaterms.login.response.UnitResponse;
import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingInsertModel;
import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingModel;
import com.mawai.sigmaterms.mastercartonpacking.repo.MasterCartonPackingRepo;
import com.mawai.sigmaterms.mastercartonpacking.response.MasterCartonPackingInsertResponse;
import com.mawai.sigmaterms.mastercartonpacking.response.MasterCartonPackingResponse;

public class MasterCartonPackingViewModel extends ViewModel {

    private MasterCartonPackingRepo masterCartonPackingRepo;

    public MasterCartonPackingViewModel() {
        masterCartonPackingRepo = MasterCartonPackingRepo.getInstance();
//        loginModelLiveData.setValue(new LoginModel());
    }

    public LiveData<MasterCartonPackingResponse> callGetScanBarCode(MasterCartonPackingModel masterCartonPackingModel) {
        return masterCartonPackingRepo.getScanBarCode(masterCartonPackingModel);
    }

    public LiveData<MasterCartonPackingResponse> callGetScanUnitBox(MasterCartonPackingModel masterCartonPackingModel) {
        return masterCartonPackingRepo.getScanUnitBox(masterCartonPackingModel);
    }

    public LiveData<MasterCartonPackingInsertResponse> callGetSaveMasterCarton(MasterCartonPackingInsertModel masterCartonPackingInsertModel) {
        return masterCartonPackingRepo.getSaveMasterCarton(masterCartonPackingInsertModel);
    }
}
