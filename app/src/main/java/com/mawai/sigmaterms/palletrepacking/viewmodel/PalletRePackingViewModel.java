package com.mawai.sigmaterms.palletrepacking.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingModel;
import com.mawai.sigmaterms.mastercartonpacking.repo.MasterCartonPackingRepo;
import com.mawai.sigmaterms.mastercartonpacking.response.MasterCartonPackingResponse;
import com.mawai.sigmaterms.palletrepacking.model.PalletRePackingModel;
import com.mawai.sigmaterms.palletrepacking.repo.PalletRePackingRepo;
import com.mawai.sigmaterms.palletrepacking.response.PalletRePackingResponse;

public class PalletRePackingViewModel extends ViewModel {

    private PalletRePackingRepo palletRePackingRepo;

    public PalletRePackingViewModel() {
        palletRePackingRepo = PalletRePackingRepo.getInstance();
//        loginModelLiveData.setValue(new LoginModel());
    }

    public LiveData<PalletRePackingResponse> callGetScanPalletBarCode(PalletRePackingModel palletRePackingModel) {
        return palletRePackingRepo.getScanPalletBarCode(palletRePackingModel);
    }

}
