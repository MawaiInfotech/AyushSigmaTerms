package com.mawai.sigmaterms.palletscanning;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextUtils;
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
import com.mawai.sigmaterms.databinding.FragmentPalletScanningBinding;
import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingInsertModel;
import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingModel;
import com.mawai.sigmaterms.mastercartonpacking.viewmodel.MasterCartonPackingViewModel;
import com.mawai.sigmaterms.palletscanning.model.PalletScanningChangeModel;
import com.mawai.sigmaterms.palletscanning.model.PalletScanningInsertModel;
import com.mawai.sigmaterms.palletscanning.model.PalletScanningModel;
import com.mawai.sigmaterms.palletscanning.viewmodel.PalletScanningViewModel;
import com.mawai.sigmaterms.utills.SessionManager;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PalletScanningFragment extends Fragment {


    public PalletScanningFragment() {
        // Required empty public constructor
    }

    FragmentPalletScanningBinding binding;
    MasterCartonPackingViewModel masterCartonPackingViewModel;
    SessionManager sessionManager;
    BottomSheetDialog bottomSheetDialog,bottomSheetDialogConfirmation;
    PalletScanningViewModel palletScanningViewModel;
    long number;
    String yy;
    int count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_pallet_scanning, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pallet_scanning, container, false);
        palletScanningViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(PalletScanningViewModel.class);
        binding.setLifecycleOwner(this);
//         binding.setListener(this);
        sessionManager = new SessionManager(getContext());
        binding.edtPalletNo.requestFocus();
//        disableSoftInputFromAppearing(binding.edtScanPallet);
        disableSoftInputFromAppearing(binding.edtScanMasterCarton);


        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yy", Locale.getDefault());
        yy = df.format(c);
        Log.e("Date", yy);

        binding.textUnitName.setText(sessionManager.getString("unit_name"));
        binding.textUsername.setText(sessionManager.getString("user_name"));

        number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
//      sessionManager.save(SessionManager.SESSION_NUMBER, String.valueOf(number));
        Log.d("Session Number", String.valueOf(number));

        binding.imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_palletScanningFragment_to_homeFragment);
            }
        });

//        binding.edtPalletNo.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if (!editable.toString().isEmpty()) {
//                    PalletScanningChangeModel palletScanningChangeModel = new PalletScanningChangeModel();
//                    palletScanningChangeModel.setPallent_no(editable.toString().trim());
//                    palletScanningChangeModel.setUnit_cd(sessionManager.getString("unit_code"));
//                    getScanPallet(palletScanningChangeModel);
//                }
//            }
//        });

        binding.btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(binding.edtPalletNo.getText().toString().trim())){
                    PalletScanningChangeModel palletScanningChangeModel = new PalletScanningChangeModel();
                    palletScanningChangeModel.setPallent_no(binding.edtPalletNo.getText().toString().trim());
                    palletScanningChangeModel.setUnit_cd(sessionManager.getString("unit_code"));
                    getScanPallet(palletScanningChangeModel);
                }else {
                    Toast.makeText(getContext(), "Please Enter Pallet No.", Toast.LENGTH_SHORT).show();
                }

            }
        });



        binding.edtScanMasterCarton.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()) {
                    if (!TextUtils.isEmpty(binding.edtPalletNo.getText().toString().trim()) &&
                            !TextUtils.isEmpty(binding.edtUniqueNo.getText().toString().trim())) {

                        getSavePallet(editable.toString().trim());
                    } else {
                        binding.edtScanMasterCarton.setText("");
                        Toast.makeText(getContext(), "Pallet No And Unique No. must be enter", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                binding.edtScanPallet.setText("");
                binding.edtPalletNo.setText("");
                binding.edtUniqueNo.setText("");
                binding.edtScanMasterCarton.setText("");
//                binding.edtProductCode.setText("");
                binding.edtPalletNo.requestFocus();
            }
        });

        binding.btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_palletScanningFragment_to_homeFragment);
            }
        });

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(binding.edtUniqueNo.getText().toString().trim())) {
                    showBottomSheetDialog();

                } else {
                    Toast.makeText(getContext(), "Pallet Unique No. must be enter", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();
    }

    private void showBottomSheetDialog() {

        bottomSheetDialogConfirmation = new BottomSheetDialog(getContext());
        bottomSheetDialogConfirmation.setContentView(R.layout.layout_dialog_unpack_and_cancel);
        bottomSheetDialogConfirmation.setCancelable(false);

        TextView datas = bottomSheetDialogConfirmation.findViewById(R.id.unpack_msg);
//        if (flag.equals("0")){
        datas.setText("Are you sure your pallet is fully Packed ?");
//        }else if (flag.equals("1")){
//            datas.setText("Are you sure you want to Dispatch Cancel The Pallet ?");
//        }


        Button no = bottomSheetDialogConfirmation.findViewById(R.id.no_btn);
        Button yes = bottomSheetDialogConfirmation.findViewById(R.id.yes_btn);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                stopDialogs();
                binding.edtScanMasterCarton.requestFocus();

//
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stopDialogs();
                PalletScanningChangeModel palletScanningChangeModel = new PalletScanningChangeModel();
                palletScanningChangeModel.setPallent_unique_no(binding.edtUniqueNo.getText().toString().trim());
                palletScanningChangeModel.setUnit_cd(sessionManager.getString("unit_code"));
                palletScanningChangeModel.setScanBy(sessionManager.getString("user_name"));
                getClosePallet(palletScanningChangeModel);
//                getDispatchCancel(data);
            }
        });

        bottomSheetDialogConfirmation.show();
    }

    public void stopDialogs() {
        bottomSheetDialogConfirmation.dismiss();
    }

    private void getScanPallet(PalletScanningChangeModel model) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        palletScanningViewModel.callGetScanPallet(model).observe(getViewLifecycleOwner(), (palletScanningChangeResponse) -> {
            progressDialog.dismiss();
            if (palletScanningChangeResponse.getStatus()) {
                PalletScanningChangeModel data = palletScanningChangeResponse.getData();
//                binding.edtPalletSize.setText(String.valueOf(data.getQty()));
                binding.edtUniqueNo.setText(data.getPallent_unique_no());
                binding.edtScanMasterCarton.requestFocus();
                Toast.makeText(getContext(), "" + palletScanningChangeResponse.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
//                binding.edtPalletNo.requestFocus();
                binding.edtPalletNo.setText("");
                Toast.makeText(getContext(), "" + palletScanningChangeResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getScanProduct(PalletScanningModel palletScanningModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        palletScanningViewModel.callGetScanProduct(palletScanningModel).observe(getViewLifecycleOwner(), (palletScanningResponse) -> {
            progressDialog.dismiss();
            if (palletScanningResponse.getStatus()) {
                PalletScanningModel data = palletScanningResponse.getData();
                binding.edtProductCode.setText(data.getProdCd());

//                if (binding.edtProductCode.getText().toString().trim().trim().equals(binding.edtProductCode2.getText().toString().trim())){
                count = Integer.parseInt(binding.edtPack.getText().toString().trim());
                int palletSize = Integer.parseInt(binding.edtPalletSize.getText().toString().trim());
                if (count <= palletSize) {
                    getSavePallet(binding.edtScanMasterCarton.getText().toString().trim());
                } else {
                    showBottomSheetDialog("Your Pallet is Full. Please Scan Other Pallet");
                }
//              Toast.makeText(getContext(), "Product Code Match", Toast.LENGTH_SHORT).show();
//                } else {
//                    binding.edtUnitBoxScan.requestFocus();
//                    binding.edtUnitBoxScan.setText("");
//                    binding.edtProductCode2.setText("");
//                    Toast.makeText(getContext(), "Product Code doesn't Matching", Toast.LENGTH_SHORT).show();
//                }
            } else {
                binding.edtScanMasterCarton.requestFocus();
                binding.edtScanMasterCarton.setText("");
                binding.edtProductCode.setText("");
                Toast.makeText(getContext(), "" + palletScanningResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSavePallet(String data) {
        PalletScanningChangeModel palletScanningChangeModel = new PalletScanningChangeModel();
        palletScanningChangeModel.setUnit_cd(sessionManager.getString("unit_code"));
        palletScanningChangeModel.setPallent_no(binding.edtPalletNo.getText().toString().trim());
        palletScanningChangeModel.setPallent_unique_no(binding.edtUniqueNo.getText().toString().trim());
        palletScanningChangeModel.setScanBarcode(data);
        palletScanningChangeModel.setPallent_Order_no(String.valueOf("MP/" + yy + "/" + number));
        palletScanningChangeModel.setScanBy(sessionManager.getString("user_name"));
        getSavePallets(palletScanningChangeModel);
    }

    private void getSavePallets(PalletScanningChangeModel palletScanningChangeModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        palletScanningViewModel.callGetSavePallet(palletScanningChangeModel).observe(getViewLifecycleOwner(), (palletScanningInsertResponse) -> {
            progressDialog.dismiss();
            if (palletScanningInsertResponse.getStatus()) {
//                count = Integer.parseInt(palletScanningInsertResponse.getData());
//                binding.edtPack.setText(String.valueOf(count));
                binding.edtScanMasterCarton.setText("");
//                binding.edtProductCode.setText("");
                binding.edtScanMasterCarton.requestFocus();
                Toast.makeText(getContext(), "" + palletScanningInsertResponse.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                binding.edtScanMasterCarton.setText("");
//                binding.edtProductCode.setText("");
                binding.edtScanMasterCarton.requestFocus();
                Toast.makeText(getContext(), "" + palletScanningInsertResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getClosePallet(PalletScanningChangeModel palletScanningChangeModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        palletScanningViewModel.callGetClosePallet(palletScanningChangeModel).observe(getViewLifecycleOwner(), (palletScanningInsertResponse) -> {
            progressDialog.dismiss();
            if (palletScanningInsertResponse.getStatus()) {

                binding.edtPalletNo.setText("");
                binding.edtUniqueNo.setText("");
//                binding.edtProductCode.setText("");
                binding.edtPalletNo.requestFocus();
                showBottomSheetDialog("Your Pallet is Full. Please Scan Other Pallet");
//                Toast.makeText(getContext(), "" + palletScanningInsertResponse.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                binding.edtPalletNo.setText("");
                binding.edtUniqueNo.setText("");
//                binding.edtProductCode.setText("");
                binding.edtPalletNo.requestFocus();
//                showBottomSheetDialog(palletScanningInsertResponse.getMessage());
                Toast.makeText(getContext(), "" + palletScanningInsertResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
//                binding.edtScanPallet.setText("");
//                binding.edtPalletSize.setText("");
//                binding.edtPack.setText("");
//                binding.edtScanMasterCarton.setText("");
//                binding.edtProductCode.setText("");
//                binding.edtScanPallet.requestFocus();

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