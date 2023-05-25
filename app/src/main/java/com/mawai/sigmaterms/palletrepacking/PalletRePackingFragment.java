package com.mawai.sigmaterms.palletrepacking;

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
import com.mawai.sigmaterms.databinding.FragmentPalletRePackingBinding;
import com.mawai.sigmaterms.databinding.FragmentPalletScanningBinding;
import com.mawai.sigmaterms.dispatchcancel.model.DispatchCancelModel;
import com.mawai.sigmaterms.palletrepacking.model.PalletRePackingModel;
import com.mawai.sigmaterms.palletrepacking.viewmodel.PalletRePackingViewModel;
import com.mawai.sigmaterms.palletscanning.model.PalletScanningChangeModel;
import com.mawai.sigmaterms.palletscanning.viewmodel.PalletScanningViewModel;
import com.mawai.sigmaterms.utills.SessionManager;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class PalletRePackingFragment extends Fragment {



    public PalletRePackingFragment() {
        // Required empty public constructor
    }
    FragmentPalletRePackingBinding binding;
    SessionManager sessionManager;
    BottomSheetDialog bottomSheetDialog,bottomSheetDialogConfirmation;
    long number;
    String yy;
    int count;
    PalletScanningViewModel palletScanningViewModel;
    PalletRePackingViewModel palletRePackingViewModel;
    String pallent_Order_no;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_pallet_re_packing, container, false);
          binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pallet_re_packing, container, false);
        palletScanningViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(PalletScanningViewModel.class);
        palletRePackingViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(PalletRePackingViewModel.class);
        binding.setLifecycleOwner(this);
//         binding.setListener(this);
        sessionManager = new SessionManager(getContext());
        binding.edtUniqueNo.requestFocus();
        disableSoftInputFromAppearing(binding.edtUniqueNo);
        disableSoftInputFromAppearing(binding.edtScanMasterCarton);

//        Date c = Calendar.getInstance().getTime();
//        System.out.println("Current time => " + c);
//
//        SimpleDateFormat df = new SimpleDateFormat("yy", Locale.getDefault());
//        yy = df.format(c);
//        Log.e("Date", yy);

        binding.textUnitName.setText(sessionManager.getString("unit_name"));
        binding.textUsername.setText(sessionManager.getString("user_name"));

//        number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
////      sessionManager.save(SessionManager.SESSION_NUMBER, String.valueOf(number));
//        Log.d("Session Number", String.valueOf(number));

        binding.imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_palletRePackingFragment_to_homeFragment);
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

        binding.edtUniqueNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()) {
                    PalletRePackingModel palletRePackingModel = new PalletRePackingModel();
                    palletRePackingModel.setPallent_unique_no(editable.toString().trim());
                    palletRePackingModel.setUnit_cd(sessionManager.getString("unit_code"));
                    getScanPallet(palletRePackingModel);
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
                pallent_Order_no ="";
//                binding.edtProductCode.setText("");
                binding.edtUniqueNo.requestFocus();
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


        binding.btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_palletRePackingFragment_to_homeFragment);
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
                palletScanningChangeModel.setUnit_cd(sessionManager.getString("unit_code"));
                palletScanningChangeModel.setScanBy(sessionManager.getString("user_name"));
                palletScanningChangeModel.setPallent_unique_no(binding.edtUniqueNo.getText().toString().trim());
                getClosePallet(palletScanningChangeModel);
//                getDispatchCancel(data);
            }
        });

        bottomSheetDialogConfirmation.show();
    }




    private void getScanPallet(PalletRePackingModel model) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        palletRePackingViewModel.callGetScanPalletBarCode(model).observe(getViewLifecycleOwner(), (palletRePackingResponse) -> {
            progressDialog.dismiss();
            if (palletRePackingResponse.isStatus()) {
                PalletRePackingModel data = palletRePackingResponse.getData();
//                binding.edtPalletSize.setText(String.valueOf(data.getQty()));
                binding.edtPalletNo.setText(data.getPallent_no());
                pallent_Order_no = data.getPallent_Order_no();
                binding.edtScanMasterCarton.requestFocus();
                Toast.makeText(getContext(), "" + palletRePackingResponse.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                binding.edtUniqueNo.setText("");
                binding.edtUniqueNo.requestFocus();
                Toast.makeText(getContext(), "" + palletRePackingResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSavePallet(String data) {
        PalletScanningChangeModel palletScanningChangeModel = new PalletScanningChangeModel();
        palletScanningChangeModel.setUnit_cd(sessionManager.getString("unit_code"));
        palletScanningChangeModel.setPallent_no(binding.edtPalletNo.getText().toString().trim());
        palletScanningChangeModel.setPallent_unique_no(binding.edtUniqueNo.getText().toString().trim());
        palletScanningChangeModel.setScanBarcode(data);
        palletScanningChangeModel.setPallent_Order_no(pallent_Order_no);
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
//              binding.edtProductCode.setText("");
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


                showBottomSheetDialog("Your Pallet is Full. Please Scan Other Pallet");
//                Toast.makeText(getContext(), "" + palletScanningInsertResponse.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                binding.edtPalletNo.setText("");
                binding.edtUniqueNo.setText("");
//                binding.edtProductCode.setText("");
                binding.edtUniqueNo.requestFocus();
//              showBottomSheetDialog(palletScanningInsertResponse.getMessage());
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

                binding.edtPalletNo.setText("");
                binding.edtUniqueNo.setText("");
//                binding.edtProductCode.setText("");
                binding.edtUniqueNo.requestFocus();

            }
        });

        bottomSheetDialog.show();
    }

    public void stopDialog() {
        bottomSheetDialog.dismiss();
    }

    public void stopDialogs() {
        bottomSheetDialogConfirmation.dismiss();
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