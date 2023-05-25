package com.mawai.sigmaterms.mastercartonpacking;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mawai.sigmaterms.R;
import com.mawai.sigmaterms.databinding.FragmentMasterCartonPackingBinding;
import com.mawai.sigmaterms.login.viewmodel.LoginViewModel;
import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingInsertModel;
import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingModel;
import com.mawai.sigmaterms.mastercartonpacking.viewmodel.MasterCartonPackingViewModel;
import com.mawai.sigmaterms.utills.SessionManager;

import java.lang.reflect.Method;


public class MasterCartonPackingFragment extends Fragment {



    public MasterCartonPackingFragment() {
        // Required empty public constructor
    }
    FragmentMasterCartonPackingBinding binding;
    MasterCartonPackingViewModel masterCartonPackingViewModel;
    SessionManager sessionManager;
    BottomSheetDialog bottomSheetDialog;
    int count;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_master_carton_packing, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_master_carton_packing, container, false);
        masterCartonPackingViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(MasterCartonPackingViewModel.class);
        binding.setLifecycleOwner(this);
//         binding.setListener(this);
        sessionManager = new SessionManager(getContext());
        binding.edtCartonBarcode.requestFocus();
        disableSoftInputFromAppearing(binding.edtCartonBarcode);
        disableSoftInputFromAppearing(binding.edtUnitBoxScan);

        binding.textUnitName.setText(sessionManager.getString("unit_name"));
        binding.textUsername.setText(sessionManager.getString("user_name"));

        binding.imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_masterCartonPackingFragment_to_homeFragment);
            }
        });

        binding.edtCartonBarcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()){
                    MasterCartonPackingModel masterCartonPackingModel = new MasterCartonPackingModel();
                    masterCartonPackingModel.setMastCartBcodeNo(editable.toString().trim());
                    getScanBarCode(masterCartonPackingModel);
                }

            }
        });

        binding.edtUnitBoxScan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()){
                    MasterCartonPackingModel masterCartonPackingModel = new MasterCartonPackingModel();
                    masterCartonPackingModel.setProdBcodeNo(editable.toString().trim());
                    getScanUnitBox(masterCartonPackingModel);
                }

            }
        });

        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.edtCartonBarcode.setText("");
                binding.edtProductCode.setText("");
                binding.edtProductDesc.setText("");
                binding.edtCartonSize.setText("");
                binding.edtPackQty.setText("");
                binding.edtProductCode2.setText("");
                binding.edtUnitBoxScan.setText("");
                binding.edtCartonBarcode.requestFocus();
            }
        });

        binding.btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_masterCartonPackingFragment_to_homeFragment);
            }
        });

        return binding.getRoot();
    }


//    218072022MC133
//    218072022UB1281
//    DH120042022MC18
//    DH120042022UB65

//    DH120042022MC9
//    DH120042022MC9
//    DH120042022UB385
    private void getScanBarCode(MasterCartonPackingModel masterCartonPackingModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        masterCartonPackingViewModel.callGetScanBarCode(masterCartonPackingModel).observe(getViewLifecycleOwner(), (masterCartonPackingResponse) -> {
            progressDialog.dismiss();
                if (masterCartonPackingResponse.getStatus()) {
                    MasterCartonPackingModel data = masterCartonPackingResponse.getData();
                    binding.edtProductDesc.setText(data.getProd_desc());
                    binding.edtProductCode.setText(data.getProdCd());
                    binding.edtCartonSize.setText(String.valueOf(data.getQty()));
                    binding.edtPackQty.setText(masterCartonPackingResponse.getMessage());
                    binding.edtUnitBoxScan.requestFocus();
                    Toast.makeText(getContext(), "" + masterCartonPackingResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    showBottomSheetDialogMasterError(masterCartonPackingResponse.getMessage());
//                  Toast.makeText(getContext(), "" + masterCartonPackingResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
        });
    }

    private void getScanUnitBox(MasterCartonPackingModel masterCartonPackingModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        masterCartonPackingViewModel.callGetScanUnitBox(masterCartonPackingModel).observe(getViewLifecycleOwner(), (masterCartonPackingResponse) -> {
            progressDialog.dismiss();
            if (masterCartonPackingResponse.getStatus()) {
                MasterCartonPackingModel data = masterCartonPackingResponse.getData();
                binding.edtProductCode2.setText(data.getProdCd());

                if (binding.edtProductCode.getText().toString().trim().trim().equals(binding.edtProductCode2.getText().toString().trim())){
                    count = Integer.parseInt(binding.edtPackQty.getText().toString().trim());
                   int cartonSize = Integer.parseInt(binding.edtCartonSize.getText().toString().trim());
                    if (count < cartonSize){
                        getSaveMasterCartons(binding.edtUnitBoxScan.getText().toString().trim());
                    }else {
                        showBottomSheetDialog("Your Carton is Full. Please Scan Other Carton");
                    }
//                    Toast.makeText(getContext(), "Product Code Match", Toast.LENGTH_SHORT).show();
                } else {
                    binding.edtUnitBoxScan.requestFocus();
                    binding.edtUnitBoxScan.setText("");
                    binding.edtProductCode2.setText("");
                    Toast.makeText(getContext(), "Product Code doesn't Matching", Toast.LENGTH_SHORT).show();
                }
            } else {
                showBottomSheetDialogUnitError(binding.edtUnitBoxScan.getText().toString(),masterCartonPackingResponse.getMessage());
//                Toast.makeText(getContext(), "" + masterCartonPackingResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSaveMasterCartons(String data) {
        MasterCartonPackingInsertModel masterCartonPackingInsertModel = new MasterCartonPackingInsertModel();
        masterCartonPackingInsertModel.setUnitCd(sessionManager.getString("unit_code"));
        masterCartonPackingInsertModel.setMasterCartonBarcode(binding.edtCartonBarcode.getText().toString().trim());
        masterCartonPackingInsertModel.setProdCode(binding.edtProductCode.getText().toString().trim());
        masterCartonPackingInsertModel.setCartonSize(Integer.parseInt(binding.edtCartonSize.getText().toString().trim()));
        masterCartonPackingInsertModel.setPackCarton(Integer.parseInt(binding.edtPackQty.getText().toString().trim()));
        masterCartonPackingInsertModel.setProdBarcode(binding.edtProductCode2.getText().toString().trim());
        masterCartonPackingInsertModel.setScanProd(data);
        masterCartonPackingInsertModel.setScanQty(1);
        masterCartonPackingInsertModel.setScanBy(sessionManager.getString("user_name"));
        getSaveMasterCarton(masterCartonPackingInsertModel);
    }

    private void getSaveMasterCarton(MasterCartonPackingInsertModel masterCartonPackingInsertModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        masterCartonPackingViewModel.callGetSaveMasterCarton(masterCartonPackingInsertModel).observe(getViewLifecycleOwner(), (masterCartonPackingInsertResponse) -> {
            progressDialog.dismiss();
            if (masterCartonPackingInsertResponse.getStatus()) {
                count = Integer.parseInt(masterCartonPackingInsertResponse.getData());
                binding.edtPackQty.setText(String.valueOf(count));
                binding.edtUnitBoxScan.setText("");
                binding.edtProductCode2.setText("");
                binding.edtUnitBoxScan.requestFocus();

                Toast.makeText(getContext(), "" + masterCartonPackingInsertResponse.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                binding.edtUnitBoxScan.setText("");
                binding.edtProductCode2.setText("");
                binding.edtUnitBoxScan.requestFocus();
                Toast.makeText(getContext(), "" + masterCartonPackingInsertResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showBottomSheetDialogMasterError(String data) {

        bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.layout_dialog);
        bottomSheetDialog.setCancelable(false);


            TextView datas = bottomSheetDialog.findViewById(R.id.data_show);
            datas.setText(data);


//        Button no = bottomSheetDialog.findViewById(R.id.no_btn);
        Button yes = bottomSheetDialog.findViewById(R.id.yes_btn);

//        no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                bottomSheetDialog.dismiss();
//                stopDialog();
////                visionScannerBinding.edtMachine.setText("");
//            }
//        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                bottomSheetDialog.dismiss();
//                binding.edtMachine.setText("");
                stopDialog();

//                getInsertData(data);
                binding.edtCartonBarcode.requestFocus();
                binding.edtCartonBarcode.setText("");

            }
        });

        bottomSheetDialog.show();
    }

    private void showBottomSheetDialogUnitError(String data, String message) {

        bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.layout_dialog);
        bottomSheetDialog.setCancelable(false);

        TextView datass = bottomSheetDialog.findViewById(R.id.unit_barcode);
        TextView datas = bottomSheetDialog.findViewById(R.id.data_show);
        datas.setText(data);
        datass.setText(message);
//        Button no = bottomSheetDialog.findViewById(R.id.no_btn);
        Button yes = bottomSheetDialog.findViewById(R.id.yes_btn);

//        no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                bottomSheetDialog.dismiss();
//                stopDialog();
////                visionScannerBinding.edtMachine.setText("");
//            }
//        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                bottomSheetDialog.dismiss();
//                binding.edtMachine.setText("");
                stopDialog();

//                getInsertData(data);
                binding.edtUnitBoxScan.requestFocus();
                binding.edtUnitBoxScan.setText("");
                binding.edtProductCode2.setText("");

            }
        });

        bottomSheetDialog.show();
    }

    private void showBottomSheetDialog(String data) {

        bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.layout_dialog);
        bottomSheetDialog.setCancelable(false);

        TextView datas = bottomSheetDialog.findViewById(R.id.data_show);
        datas.setText(data);
//        Button no = bottomSheetDialog.findViewById(R.id.no_btn);
        Button yes = bottomSheetDialog.findViewById(R.id.yes_btn);

//        no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                bottomSheetDialog.dismiss();
//                  stopDialog();
////                visionScannerBinding.edtMachine.setText("");
//            }
//        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                bottomSheetDialog.dismiss();
//                binding.edtMachine.setText("");
                stopDialog();

//                getInsertData(data);
                binding.edtCartonBarcode.setText("");
                binding.edtProductCode.setText("");
                binding.edtProductDesc.setText("");
                binding.edtCartonSize.setText("");
                binding.edtPackQty.setText("");
                binding.edtProductCode2.setText("");
                binding.edtUnitBoxScan.setText("");
                binding.edtCartonBarcode.requestFocus();

            }
        });

        bottomSheetDialog.show();
    }

    public void stopDialog() {
        bottomSheetDialog.dismiss();
    }

    public static void disableSoftInputFromAppearing(EditText editText) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            editText.setShowSoftInputOnFocus(false);
        } else {
            try {
                final Method method = EditText.class.getMethod(
                        "setShowSoftInputOnFocus"
                        , new Class[]{boolean.class});
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
                // ignore
            }
        }
    }


}