package com.mawai.sigmaterms.dispatch;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
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
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.mawai.sigmaterms.R;
import com.mawai.sigmaterms.databinding.FragmentDispatchBinding;
import com.mawai.sigmaterms.dispatch.model.DispatchInsertModel;
import com.mawai.sigmaterms.dispatch.model.DispatchModel;
import com.mawai.sigmaterms.dispatch.viewmodel.DispatchViewModel;
import com.mawai.sigmaterms.dispatchcancel.model.DispatchCancelModel;
import com.mawai.sigmaterms.mastercartonpacking.model.MasterCartonPackingModel;
import com.mawai.sigmaterms.palletscanning.viewmodel.PalletScanningViewModel;
import com.mawai.sigmaterms.utills.SessionManager;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DispatchFragment extends Fragment {


    public DispatchFragment() {
        // Required empty public constructor
    }

    FragmentDispatchBinding binding;
    DispatchViewModel dispatchViewModel;
    SessionManager sessionManager;
    BottomSheetDialog bottomSheetDialog;
    String flag = "0";
    long number;
    String yy;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_dispatch, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dispatch, container, false);
        dispatchViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(DispatchViewModel.class);
        binding.setLifecycleOwner(this);
//         binding.setListener(this);
        sessionManager = new SessionManager(getContext());
        binding.edtScanBarcode.requestFocus();

        disableSoftInputFromAppearing(binding.edtScanBarcode);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yy", Locale.getDefault());
        yy = df.format(c);
        Log.e("Date", yy);

        number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
//      sessionManager.save(SessionManager.SESSION_NUMBER, String.valueOf(number));
        Log.d("Session Number", String.valueOf(number));

        binding.textUnitName.setText(sessionManager.getString("unit_name"));
        binding.textUsername.setText(sessionManager.getString("user_name"));

        binding.tabs.addTab(binding.tabs.newTab().setText("MasterCarton"));
        binding.tabs.addTab(binding.tabs.newTab().setText("Pallet"));

        binding.imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_dispatchFragment_to_homeFragment);

            }
        });

        binding.tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (binding.tabs.getSelectedTabPosition() == 0) {
                    flag = "0";
                    Toast.makeText(getContext(), "" + flag, Toast.LENGTH_LONG).show();
                } else if (binding.tabs.getSelectedTabPosition() == 1) {
                    flag = "1";
                    Toast.makeText(getContext(), "" + flag, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.edtQty.setText("");
                binding.edtSrNo.setText("");
                binding.edtUnitBoxScan.setText("");
                binding.edtScanBarcode.setText("");
                binding.edtScanBarcode.requestFocus();
            }
        });

        binding.btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_dispatchFragment_to_homeFragment);
            }
        });

        binding.edtScanBarcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()){
                    DispatchModel dispatchModel = new DispatchModel();
                    dispatchModel.setPacktype(Integer.parseInt(flag));
                    dispatchModel.setScanbarcode(editable.toString().trim());
                    getChkPackingDisp(dispatchModel);
                }

            }
        });


        return binding.getRoot();
    }

    private void getChkPackingDisp(DispatchModel dispatchModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        dispatchViewModel.callGetChkPackingDisp(dispatchModel).observe(getViewLifecycleOwner(), (dispatchResponse) -> {
            progressDialog.dismiss();
            if (dispatchResponse.getStatus()) {
                DispatchModel data = dispatchResponse.getData();
                binding.edtQty.setText(String.valueOf(data.getQty()));
                binding.edtSrNo.setText(String.valueOf(data.getSlno()));

                showBottomSheetDialog(data);
                Toast.makeText(getContext(), "" + dispatchResponse.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                binding.edtScanBarcode.requestFocus();
                binding.edtScanBarcode.setText("");
                Toast.makeText(getContext(), "" + dispatchResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSaveData(int qty, int slno) {
        DispatchInsertModel model = new DispatchInsertModel();
        model.setUnitCd(sessionManager.getString("unit_code"));
        model.setDispBarcode(binding.edtScanBarcode.getText().toString().trim());
        model.setPalletNo(slno);
        model.setRefDocNo("");
        model.setScanQty(qty);
        model.setBarcodeType(flag);
        model.setSession_no(String.valueOf("MP/" + yy + "/" + number));
        model.setScanBy(sessionManager.getString("user_name"));
        getSavePackingDisp(model);
    }

    private void getSavePackingDisp(DispatchInsertModel dispatchInsertModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        dispatchViewModel.callGetSavePackingDisp(dispatchInsertModel).observe(getViewLifecycleOwner(), (dispatchInsertResponse) -> {
            progressDialog.dismiss();
            if (dispatchInsertResponse.getStatus()) {
                DispatchInsertModel data = dispatchInsertResponse.getData();
//                binding.edtProductCode.setText(data.getProdCd());
//                binding.edtCartonSize.setText(String.valueOf(data.getQty()));
//                binding.edtPackQty.setText(masterCartonPackingResponse.getMessage());
                binding.edtQty.setText("");
                binding.edtSrNo.setText("");
                binding.edtUnitBoxScan.setText("");
                binding.edtScanBarcode.setText("");
                binding.edtScanBarcode.requestFocus();
                Toast.makeText(getContext(), "" + dispatchInsertResponse.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                binding.edtQty.setText("");
                binding.edtSrNo.setText("");
                binding.edtUnitBoxScan.setText("");
                binding.edtScanBarcode.setText("");
                binding.edtScanBarcode.requestFocus();
                Toast.makeText(getContext(), "" + dispatchInsertResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showBottomSheetDialog(DispatchModel data) {

        bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.layout_dialog_unpack_and_cancel);
        bottomSheetDialog.setCancelable(false);

        TextView datas = bottomSheetDialog.findViewById(R.id.unpack_msg);
        if (flag.equals("0")){
            datas.setText("Are you sure you want to Dispatch The MasterCarton ?");
        }else if (flag.equals("1")){
            datas.setText("Are you sure you want to Dispatch The Pallet ?");
        }


        Button no = bottomSheetDialog.findViewById(R.id.no_btn);
        Button yes = bottomSheetDialog.findViewById(R.id.yes_btn);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                stopDialog();
                binding.edtQty.setText("");
                binding.edtSrNo.setText("");
                binding.edtUnitBoxScan.setText("");
                binding.edtScanBarcode.setText("");
                binding.edtScanBarcode.requestFocus();

//
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stopDialog();
                getSaveData(data.getQty(),data.getSlno());
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