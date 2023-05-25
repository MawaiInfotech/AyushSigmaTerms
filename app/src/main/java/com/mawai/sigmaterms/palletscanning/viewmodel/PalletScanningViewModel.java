package com.mawai.sigmaterms.palletscanning.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingInsertModel;
import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingModel;
import com.mawai.sigmaterms.mastercartonpacking.repo.MasterCartonPackingRepo;
import com.mawai.sigmaterms.mastercartonpacking.response.MasterCartonPackingInsertResponse;
import com.mawai.sigmaterms.mastercartonpacking.response.MasterCartonPackingResponse;
import com.mawai.sigmaterms.palletscanning.model.PalletScanningChangeModel;
import com.mawai.sigmaterms.palletscanning.model.PalletScanningInsertModel;
import com.mawai.sigmaterms.palletscanning.model.PalletScanningModel;
import com.mawai.sigmaterms.palletscanning.repo.PalletScanningRepo;
import com.mawai.sigmaterms.palletscanning.response.PalletScanningChangeResponse;
import com.mawai.sigmaterms.palletscanning.response.PalletScanningInsertResponse;
import com.mawai.sigmaterms.palletscanning.response.PalletScanningResponse;

public class PalletScanningViewModel extends ViewModel {

    private PalletScanningRepo palletScanningRepo;

    public PalletScanningViewModel() {
        palletScanningRepo = PalletScanningRepo.getInstance();
//        loginModelLiveData.setValue(new LoginModel());
    }

    public LiveData<PalletScanningChangeResponse> callGetScanPallet(PalletScanningChangeModel palletScanningChangeModel) {
        return palletScanningRepo.getScanPallet(palletScanningChangeModel);
    }

    public LiveData<PalletScanningResponse> callGetScanProduct(PalletScanningModel palletScanningModel) {
        return palletScanningRepo.getScanProduct(palletScanningModel);
    }

    public LiveData<PalletScanningInsertResponse> callGetSavePallet(PalletScanningChangeModel palletScanningChangeModel) {
        return palletScanningRepo.getSavePallet(palletScanningChangeModel);
    }

    public LiveData<PalletScanningInsertResponse> callGetClosePallet(PalletScanningChangeModel palletScanningChangeModel) {
        return palletScanningRepo.getClosePallet(palletScanningChangeModel);
    }
}
