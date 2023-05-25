package com.mawai.sigmaterms.palletscanning.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingInsertModel;
import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingModel;
import com.mawai.sigmaterms.mastercartonpacking.repo.MasterCartonPackingRepo;
import com.mawai.sigmaterms.mastercartonpacking.response.MasterCartonPackingInsertResponse;
import com.mawai.sigmaterms.mastercartonpacking.response.MasterCartonPackingResponse;
import com.mawai.sigmaterms.palletscanning.model.PalletScanningChangeModel;
import com.mawai.sigmaterms.palletscanning.model.PalletScanningInsertModel;
import com.mawai.sigmaterms.palletscanning.model.PalletScanningModel;
import com.mawai.sigmaterms.palletscanning.response.PalletScanningChangeResponse;
import com.mawai.sigmaterms.palletscanning.response.PalletScanningInsertResponse;
import com.mawai.sigmaterms.palletscanning.response.PalletScanningResponse;
import com.mawai.sigmaterms.retrofit.APIClient;
import com.mawai.sigmaterms.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PalletScanningRepo {

    private static PalletScanningRepo palletScanningRepo;
    private final ApiService apiServiceLogin;

    public static PalletScanningRepo getInstance() {

        if (palletScanningRepo == null)
            palletScanningRepo = new PalletScanningRepo();
        return palletScanningRepo;
    }

    private PalletScanningRepo() {
        apiServiceLogin = APIClient.getClientBase().create(ApiService.class);
//        apiServiceAfterLogin = APIClient.getApiCLientAfterLogin().create(ApiService.class);
    }


    public MutableLiveData<PalletScanningChangeResponse> getScanPallet(PalletScanningChangeModel palletScanningChangeModel) {
        final MutableLiveData<PalletScanningChangeResponse> unitlist = new MutableLiveData<>();
        apiServiceLogin.getScanPallet(palletScanningChangeModel).enqueue(new Callback<PalletScanningChangeResponse>() {
            @Override
            public void onResponse(Call<PalletScanningChangeResponse> call, Response<PalletScanningChangeResponse> response) {
                unitlist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PalletScanningChangeResponse> call, Throwable t) {
                unitlist.setValue(null);
            }
        });
        return unitlist;
    }

    public MutableLiveData<PalletScanningResponse> getScanProduct(PalletScanningModel palletScanningModel) {
        final MutableLiveData<PalletScanningResponse> unitlist = new MutableLiveData<>();
        apiServiceLogin.getScanProduct(palletScanningModel).enqueue(new Callback<PalletScanningResponse>() {
            @Override
            public void onResponse(Call<PalletScanningResponse> call, Response<PalletScanningResponse> response) {
                unitlist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PalletScanningResponse> call, Throwable t) {
                unitlist.setValue(null);
                Log.e("Error",t.toString());
            }
        });
        return unitlist;
    }

    public MutableLiveData<PalletScanningInsertResponse> getSavePallet(PalletScanningChangeModel palletScanningChangeModel) {
        final MutableLiveData<PalletScanningInsertResponse> unitlist = new MutableLiveData<>();
        apiServiceLogin.getSavePallet(palletScanningChangeModel).enqueue(new Callback<PalletScanningInsertResponse>() {
            @Override
            public void onResponse(Call<PalletScanningInsertResponse> call, Response<PalletScanningInsertResponse> response) {
                unitlist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PalletScanningInsertResponse> call, Throwable t) {
                unitlist.setValue(null);
            }
        });
        return unitlist;
    }

    public MutableLiveData<PalletScanningInsertResponse> getClosePallet(PalletScanningChangeModel palletScanningChangeModel) {
        final MutableLiveData<PalletScanningInsertResponse> unitlist = new MutableLiveData<>();
        apiServiceLogin.getClosePallet(palletScanningChangeModel).enqueue(new Callback<PalletScanningInsertResponse>() {
            @Override
            public void onResponse(Call<PalletScanningInsertResponse> call, Response<PalletScanningInsertResponse> response) {
                unitlist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PalletScanningInsertResponse> call, Throwable t) {
                unitlist.setValue(null);
            }
        });
        return unitlist;
    }
}
