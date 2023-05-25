package com.mawai.sigmaterms.dispatchcancel.repo;

import androidx.lifecycle.MutableLiveData;

import com.mawai.sigmaterms.dispatchcancel.model.DispatchCancelModel;
import com.mawai.sigmaterms.dispatchcancel.response.DispatchCancelResponse;
import com.mawai.sigmaterms.retrofit.APIClient;
import com.mawai.sigmaterms.retrofit.ApiService;
import com.mawai.sigmaterms.unpack.model.UnPackModel;
import com.mawai.sigmaterms.unpack.repo.UnPackRepo;
import com.mawai.sigmaterms.unpack.response.UnPackResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DispatchCancelRepo {

    private static DispatchCancelRepo dispatchCancelRepo;
    private final ApiService apiServiceLogin;

    public static DispatchCancelRepo getInstance() {
        if (dispatchCancelRepo == null)
            dispatchCancelRepo = new DispatchCancelRepo();
        return dispatchCancelRepo;
    }

    private DispatchCancelRepo() {
        apiServiceLogin = APIClient.getClientBase().create(ApiService.class);
//        apiServiceAfterLogin = APIClient.getApiCLientAfterLogin().create(ApiService.class);
    }


    public MutableLiveData<DispatchCancelResponse> getDispatchCancel(DispatchCancelModel model) {
        final MutableLiveData<DispatchCancelResponse> unitlist = new MutableLiveData<>();
        apiServiceLogin.getDispatchCancel(model).enqueue(new Callback<DispatchCancelResponse>() {
            @Override
            public void onResponse(Call<DispatchCancelResponse> call, Response<DispatchCancelResponse> response) {
                unitlist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DispatchCancelResponse> call, Throwable t) {
                unitlist.setValue(null);
            }
        });
        return unitlist;
    }

}
