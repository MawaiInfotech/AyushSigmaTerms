package com.mawai.sigmaterms.mastercartonpacking.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mawai.sigmaterms.login.repo.LoginRepo;
import com.mawai.sigmaterms.login.response.UnitResponse;
import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingInsertModel;
import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingModel;
import com.mawai.sigmaterms.mastercartonpacking.response.MasterCartonPackingInsertResponse;
import com.mawai.sigmaterms.mastercartonpacking.response.MasterCartonPackingResponse;
import com.mawai.sigmaterms.retrofit.APIClient;
import com.mawai.sigmaterms.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasterCartonPackingRepo {

    private static MasterCartonPackingRepo masterCartonPackingRepo;
    private final ApiService apiServiceLogin;

    public static MasterCartonPackingRepo getInstance() {

        if (masterCartonPackingRepo == null)
            masterCartonPackingRepo = new MasterCartonPackingRepo();
        return masterCartonPackingRepo;
    }

    private MasterCartonPackingRepo() {
        apiServiceLogin = APIClient.getClientBase().create(ApiService.class);
//        apiServiceAfterLogin = APIClient.getApiCLientAfterLogin().create(ApiService.class);
    }


    public MutableLiveData<MasterCartonPackingResponse> getScanBarCode(MasterCartonPackingModel masterCartonPackingModel) {
        final MutableLiveData<MasterCartonPackingResponse> unitlist = new MutableLiveData<>();
        apiServiceLogin.getScanBarCode(masterCartonPackingModel).enqueue(new Callback<MasterCartonPackingResponse>() {
            @Override
            public void onResponse(Call<MasterCartonPackingResponse> call, Response<MasterCartonPackingResponse> response) {
                unitlist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MasterCartonPackingResponse> call, Throwable t) {
                unitlist.setValue(null);
            }
        });
        return unitlist;
    }

    public MutableLiveData<MasterCartonPackingResponse> getScanUnitBox(MasterCartonPackingModel masterCartonPackingModel) {
        final MutableLiveData<MasterCartonPackingResponse> unitlist = new MutableLiveData<>();
        apiServiceLogin.getScanUnitBox(masterCartonPackingModel).enqueue(new Callback<MasterCartonPackingResponse>() {
            @Override
            public void onResponse(Call<MasterCartonPackingResponse> call, Response<MasterCartonPackingResponse> response) {
                unitlist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MasterCartonPackingResponse> call, Throwable t) {
                unitlist.setValue(null);
                Log.e("Error",t.toString());
            }
        });
        return unitlist;
    }

    public MutableLiveData<MasterCartonPackingInsertResponse> getSaveMasterCarton(MasterCartonPackingInsertModel masterCartonPackingInsertModel) {
        final MutableLiveData<MasterCartonPackingInsertResponse> unitlist = new MutableLiveData<>();
        apiServiceLogin.getSaveMasterCarton(masterCartonPackingInsertModel).enqueue(new Callback<MasterCartonPackingInsertResponse>() {
            @Override
            public void onResponse(Call<MasterCartonPackingInsertResponse> call, Response<MasterCartonPackingInsertResponse> response) {
                unitlist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MasterCartonPackingInsertResponse> call, Throwable t) {
                unitlist.setValue(null);
            }
        });
        return unitlist;
    }
}
