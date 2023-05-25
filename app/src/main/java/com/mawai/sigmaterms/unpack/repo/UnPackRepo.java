package com.mawai.sigmaterms.unpack.repo;

import androidx.lifecycle.MutableLiveData;

import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingModel;
import com.mawai.sigmaterms.mastercartonpacking.repo.MasterCartonPackingRepo;
import com.mawai.sigmaterms.mastercartonpacking.response.MasterCartonPackingResponse;
import com.mawai.sigmaterms.retrofit.APIClient;
import com.mawai.sigmaterms.retrofit.ApiService;
import com.mawai.sigmaterms.unpack.model.UnPackModel;
import com.mawai.sigmaterms.unpack.response.UnPackResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnPackRepo {

    private static UnPackRepo unPackRepo;
    private final ApiService apiServiceLogin;

    public static UnPackRepo getInstance() {
        if (unPackRepo == null)
            unPackRepo = new UnPackRepo();
        return unPackRepo;
    }

    private UnPackRepo() {
        apiServiceLogin = APIClient.getClientBase().create(ApiService.class);
//        apiServiceAfterLogin = APIClient.getApiCLientAfterLogin().create(ApiService.class);
    }


    public MutableLiveData<UnPackResponse> getChkAndUnPackBarcode(UnPackModel model) {
        final MutableLiveData<UnPackResponse> unitlist = new MutableLiveData<>();
        apiServiceLogin.getChkAndUnPackBarcode(model).enqueue(new Callback<UnPackResponse>() {
            @Override
            public void onResponse(Call<UnPackResponse> call, Response<UnPackResponse> response) {
                unitlist.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UnPackResponse> call, Throwable t) {
                unitlist.setValue(null);
            }
        });
        return unitlist;
    }

}
