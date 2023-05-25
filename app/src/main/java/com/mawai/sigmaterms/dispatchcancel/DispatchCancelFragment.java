package com.mawai.sigmaterms.dispatchcancel;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.mawai.sigmaterms.R;
import com.mawai.sigmaterms.databinding.FragmentDispatchCancelBinding;
import com.mawai.sigmaterms.dispatchcancel.model.DispatchCancelModel;
import com.mawai.sigmaterms.dispatchcancel.viewmodel.DispatchCancelViewModel;
import com.mawai.sigmaterms.unpack.model.UnPackModel;
import com.mawai.sigmaterms.unpack.viewmodel.UnPackViewModel;
import com.mawai.sigmaterms.utills.SessionManager;

import java.lang.reflect.Method;


public class DispatchCancelFragment extends Fragment {



    public DispatchCancelFragment() {
        // Required empty public constructor
    }

    SessionManager sessionManager;
    FragmentDispatchCancelBinding binding;
    String flag = "0";
    DispatchCancelViewModel dispatchCancelViewModel;
    BottomSheetDialog bottomSheetDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_dispatch_cancel, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dispatch_cancel, container, false);
         dispatchCancelViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(DispatchCancelViewModel.class);
        binding.setLifecycleOwner(this);
//         binding.setListener(this);
        sessionManager = new SessionManager(getContext());
        binding.edtScanBarcode.requestFocus();

        disableSoftInputFromAppearing(binding.edtScanBarcode);
        binding.textUnitName.setText(sessionManager.getString("unit_name"));
        binding.textUsername.setText(sessionManager.getString("user_name"));

        binding.tabs.addTab(binding.tabs.newTab().setText("MasterCarton"));
        binding.tabs.addTab(binding.tabs.newTab().setText("Pallet"));

        binding.imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_dispatchCancelFragment_to_homeFragment);

            }
        });

        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.edtScanBarcode.setText("");
                binding.edtScanBarcode.requestFocus();
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

        binding.btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_dispatchCancelFragment_to_homeFragment);
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
                    DispatchCancelModel model = new DispatchCancelModel();
                    model.setPacktype(Integer.parseInt(flag));
                    model.setScanbarcode(editable.toString().trim());
                    model.setUnitCode(sessionManager.getString("unit_code"));
                    model.setScanby(sessionManager.getString("user_name"));
                    showBottomSheetDialog(model);
                }

            }
        });

        return binding.getRoot();
    }

    private void getDispatchCancel(DispatchCancelModel model) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        dispatchCancelViewModel.callGetDispatchCancel(model).observe(getViewLifecycleOwner(), (dispatchCancelResponse) -> {
            progressDialog.dismiss();
            if (dispatchCancelResponse.getStatus()) {
                DispatchCancelModel data = dispatchCancelResponse.getData();
                binding.edtScanBarcode.requestFocus();
                binding.edtScanBarcode.setText("");
                Toast.makeText(getContext(), "" + dispatchCancelResponse.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                binding.edtScanBarcode.requestFocus();
                binding.edtScanBarcode.setText("");
                Toast.makeText(getContext(), "" + dispatchCancelResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showBottomSheetDialog(DispatchCancelModel data) {

        bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(R.layout.layout_dialog_unpack_and_cancel);
        bottomSheetDialog.setCancelable(false);

        TextView datas = bottomSheetDialog.findViewById(R.id.unpack_msg);

        if (flag.equals("0")){
            datas.setText("Are you sure you want to Dispatch Cancel The MasterCarton ?");
        }else if (flag.equals("1")){
            datas.setText("Are you sure you want to Dispatch Cancel The Pallet ?");
        }


        Button no = bottomSheetDialog.findViewById(R.id.no_btn);
        Button yes = bottomSheetDialog.findViewById(R.id.yes_btn);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopDialog();
                binding.edtScanBarcode.requestFocus();
                binding.edtScanBarcode.setText("");
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stopDialog();
                getDispatchCancel(data);
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