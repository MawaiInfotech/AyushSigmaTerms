package com.mawai.sigmaterms.palletrepacking.repo;

import androidx.lifecycle.MutableLiveData;

import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingModel;
import com.mawai.sigmaterms.mastercartonpacking.repo.MasterCartonPackingRepo;
import com.mawai.sigmaterms.mastercartonpacking.response.MasterCartonPackingResponse;
import com.mawai.sigmaterms.palletrepacking.model.PalletRePackingModel;
import com.mawai.sigmaterms.palletrepacking.response.PalletRePackingResponse;
import com.mawai.sigmaterms.retrofit.APIClient;
import com.mawai.sigmaterms.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PalletRePackingRepo {

    private static PalletRePackingRepo palletRePackingRepo;
    private final ApiService apiServiceLogin;

    public static PalletRePackingRepo getInstance() {

        if (palletRePackingRepo == null)
            palletRePackingRepo = new PalletRePackingRepo();
        return palletRePackingRepo;
    }

    private PalletRePackingRepo() {
        apiServiceLogin = APIClient.getClientBase().create(ApiService.class);
//        apiServiceAfterLogin = APIClient.getApiCLientAfterLogin().create(ApiService.class);
    }


    public MutableLiveData<PalletRePackingResponse> getScanPalletBarCode(PalletRePackingModel palletRePackingModel) {
        final MutableLiveData<PalletRePackingResponse> unitlist = new MutableLiveData<>();
        apiServiceLogin.getScanPalletBarCode(palletRePackingModel).enqueue(new Callback<PalletRePackingResponse>() {
            @Override
            public void onResponse(Call<PalletRePackingResponse> call, Response<PalletRePackingResponse> response) {
                unitlist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PalletRePackingResponse> call, Throwable t) {
                unitlist.setValue(null);
            }
        });
        return unitlist;
    }


}
