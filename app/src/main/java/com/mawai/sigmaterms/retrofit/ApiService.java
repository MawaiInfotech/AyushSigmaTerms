package com.mawai.sigmaterms.retrofit;



import com.mawai.sigmaterms.dispatch.model.DispatchInsertModel;
import com.mawai.sigmaterms.dispatch.model.DispatchModel;
import com.mawai.sigmaterms.dispatch.response.DispatchInsertResponse;
import com.mawai.sigmaterms.dispatch.response.DispatchResponse;
import com.mawai.sigmaterms.dispatchcancel.model.DispatchCancelModel;
import com.mawai.sigmaterms.dispatchcancel.response.DispatchCancelResponse;
import com.mawai.sigmaterms.login.model.LoginModel;
import com.mawai.sigmaterms.login.response.LoginResponse;
import com.mawai.sigmaterms.login.response.UnitResponse;
import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingInsertModel;
import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingModel;
import com.mawai.sigmaterms.mastercartonpacking.response.MasterCartonPackingInsertResponse;
import com.mawai.sigmaterms.mastercartonpacking.response.MasterCartonPackingResponse;
import com.mawai.sigmaterms.palletrepacking.model.PalletRePackingModel;
import com.mawai.sigmaterms.palletrepacking.response.PalletRePackingResponse;
import com.mawai.sigmaterms.palletscanning.model.PalletScanningChangeModel;
import com.mawai.sigmaterms.palletscanning.model.PalletScanningInsertModel;
import com.mawai.sigmaterms.palletscanning.model.PalletScanningModel;
import com.mawai.sigmaterms.palletscanning.response.PalletScanningChangeResponse;
import com.mawai.sigmaterms.palletscanning.response.PalletScanningInsertResponse;
import com.mawai.sigmaterms.palletscanning.response.PalletScanningResponse;
import com.mawai.sigmaterms.unpack.model.UnPackModel;
import com.mawai.sigmaterms.unpack.response.UnPackResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("api/Account/unitdetails")
    Call<UnitResponse> unitList();

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/Account/login")
    Call<LoginResponse> getLogin(@Body LoginModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/MasterCarton/scanbarcode")
    Call<MasterCartonPackingResponse> getScanBarCode(@Body MasterCartonPackingModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/MasterCarton/scanunitbox")
    Call<MasterCartonPackingResponse> getScanUnitBox(@Body MasterCartonPackingModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/MasterCarton/savemastercarton")
    Call<MasterCartonPackingInsertResponse> getSaveMasterCarton(@Body MasterCartonPackingInsertModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/PalletV2/scanpallet")
    Call<PalletScanningChangeResponse> getScanPallet(@Body PalletScanningChangeModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/Pallet/scanproduct")
    Call<PalletScanningResponse> getScanProduct(@Body PalletScanningModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/PalletV2/scanpalletbarcode")
    Call<PalletRePackingResponse> getScanPalletBarCode(@Body PalletRePackingModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/PalletV2/scanproductunit_bcode")
    Call<PalletScanningInsertResponse> getSavePallet(@Body PalletScanningChangeModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/PalletV2/closepallet")
    Call<PalletScanningInsertResponse> getClosePallet(@Body PalletScanningChangeModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/MasterCarton/chkpackingdisp")
    Call<DispatchResponse> getChkPackingDisp(@Body DispatchModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/MasterCarton/savepackingdisp")
    Call<DispatchInsertResponse> getSavePackingDisp(@Body DispatchInsertModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/MasterCarton/chkandunpackbarcode")
    Call<UnPackResponse> getChkAndUnPackBarcode(@Body UnPackModel model);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("api/MasterCarton/dispatchcancel")
    Call<DispatchCancelResponse> getDispatchCancel(@Body DispatchCancelModel model);

//    218072022MC132
//    218072022UB1281

//218072022MC128
//218072022PL35


//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("rack-loading-details")
//    Call<RackLoadingResponse> getRackLoadDetail(@Body RackLoadingModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("rack-loding")
//    Call<RackLoadingResponse> getRackLoadInsert(@Body RackLoadingModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("rack-transfer-details")
//    Call<RackTransferResponse> getRackTransferDetail(@Body RackTransferModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("rack-transfer")
//    Call<RackTransferResponse> getRackTransferInsert(@Body RackTransferModel model);

//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("findBarCodeRackTransferList")
//    Call<RackTransferListResponse> getfindBarCodeRackTransferList(@Body RackTransferModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("updateRackTransfer")
//    Call<RackTransferToResponse> getUpdateRackTransfer(@Body List<RackTransferToModel> model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("updateChemicalContLoading")
//    Call<ChemContainerLoadingResponse> getUpdateChemicalContLoading(@Body ChemContainerLoadingModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("getEvaBagDetail")
//    Call<ChemBagScanningResponse> getEvaBagDetail(@Body ChemBagScanningModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("insertEvaBag")
//    Call<ResponseResult> getInsertEvaBag(@Body ChemBagScanningResponse model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("getEvaBagDetailContainerCodeCheck")
//    Call<ContainerCodeCheckResponse> getEvaBagDetailContainerCodeCheck(@Body ChemBagScanningModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("getBatchCodeListChemicalMix")
//    Call<ChemMixingResponse> getBatchCodeListChemicalMix(@Body ChemMixingModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("getReviewDetail")
//    Call<ReviewResponse> getReviewDetail(@Body ReviewModel model);
//
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("getJcNosChemicalMix")
//    Call<ChemMixingJCNoResponse> getJcNosChemicalMix(@Body ChemMixingModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("getWeightScal")
//    Call<ChemMixingJCNoResponse> getWeightScal(@Body ChemMixingModel model);
//
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("getRmDetailWithReqQty")
//    Call<ChemMixingJCNoResponse> getRmDetailWithReqQty(@Body ChemMixingModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("getEvaBagDetailCall")
//    Call<ChemMixingJCNoResponse> getEvaBagDetailCall(@Body ChemMixingModel model);
//
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("insertRmEvaBag")
//    Call<ChemMixingInsertResponse> insertRmEvaBag(@Body ChemMixingModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("getBatchCodeListProductionRecording")
//    Call<ProductionRecordingResponse> getBatchCodeListProductionRecording(@Body ProductionRecordingModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("getAllDetailProductionRecording")
//    Call<ProductionRecModelRespo> getAllDetailProductionRecording(@Body ProductionRecordingModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("getPrinter_NAME_Models")
//    Call<PrinterResponse> getPrinterNameList();
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("insertProdcutionRecording")
//    Call<InsertProductionRecordingRespo> insertProdcutionRecording(@Body InsertProductionRecordingModel model);
//


//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("submitData")
//    Call<ResponseResult> submitData(@Body APP_DATA_SUBMIT_MODEL model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("getBarCodeFetch")
//    Call<UnPackResponse> getBarCodeFetch(@Body UnPackModel model);
//
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("getBarcodeAndDeleteCode")
//    Call<UnPackResponse> getBarcodeAndDeleteCode(@Body UnPackModel model);


}
