package com.mawai.sigmaterms.dispatch.repo;

import androidx.lifecycle.MutableLiveData;

import com.mawai.sigmaterms.dispatch.model.DispatchInsertModel;
import com.mawai.sigmaterms.dispatch.model.DispatchModel;
import com.mawai.sigmaterms.dispatch.response.DispatchInsertResponse;
import com.mawai.sigmaterms.dispatch.response.DispatchResponse;
import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingModel;
import com.mawai.sigmaterms.mastercartonpacking.repo.MasterCartonPackingRepo;
import com.mawai.sigmaterms.mastercartonpacking.response.MasterCartonPackingResponse;
import com.mawai.sigmaterms.retrofit.APIClient;
import com.mawai.sigmaterms.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DispatchRepo {

    private static DispatchRepo dispatchRepo;
    private final ApiService apiServiceLogin;

    public static DispatchRepo getInstance() {

        if (dispatchRepo == null)
            dispatchRepo = new DispatchRepo();
        return dispatchRepo;
    }

    private DispatchRepo() {
        apiServiceLogin = APIClient.getClientBase().create(ApiService.class);
//        apiServiceAfterLogin = APIClient.getApiCLientAfterLogin().create(ApiService.class);
    }


    public MutableLiveData<DispatchResponse> getChkPackingDisp(DispatchModel dispatchModel) {
        final MutableLiveData<DispatchResponse> unitlist = new MutableLiveData<>();
        apiServiceLogin.getChkPackingDisp(dispatchModel).enqueue(new Callback<DispatchResponse>() {
            @Override
            public void onResponse(Call<DispatchResponse> call, Response<DispatchResponse> response) {
                unitlist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DispatchResponse> call, Throwable t) {
                unitlist.setValue(null);
            }
        });
        return unitlist;
    }

    public MutableLiveData<DispatchInsertResponse> getSavePackingDisp(DispatchInsertModel model) {
        final MutableLiveData<DispatchInsertResponse> unitlist = new MutableLiveData<>();
        apiServiceLogin.getSavePackingDisp(model).enqueue(new Callback<DispatchInsertResponse>() {
            @Override
            public void onResponse(Call<DispatchInsertResponse> call, Response<DispatchInsertResponse> response) {
                unitlist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DispatchInsertResponse> call, Throwable t) {
                unitlist.setValue(null);
            }
        });
        return unitlist;
    }


}
