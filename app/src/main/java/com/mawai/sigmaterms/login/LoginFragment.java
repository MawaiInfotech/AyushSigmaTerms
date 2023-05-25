package com.mawai.sigmaterms.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.mawai.sigmaterms.R;
import com.mawai.sigmaterms.databinding.FragmentLoginBinding;
import com.mawai.sigmaterms.login.model.LoginModel;
import com.mawai.sigmaterms.login.model.UnitModel;
import com.mawai.sigmaterms.login.response.LoginResponse;
import com.mawai.sigmaterms.login.viewmodel.LoginViewModel;
import com.mawai.sigmaterms.utills.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;


public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }

    LoginViewModel loginViewModel;
    FragmentLoginBinding binding;
    Button btn_login;
    SessionManager sessionManager;
    ProgressDialog dialog;

    private long mLastClickTime = 0;
    SpinnerDialog spinnerDialogUnitNo;
    private Map<String, String> divisionMap = new HashMap<String, String>();
    ArrayList<String> items = new ArrayList<>();
    List<UnitModel> divisionsModelArrayList = new ArrayList<>();
    String machine_code;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_login, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        loginViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).create(LoginViewModel.class);
        binding.setLifecycleOwner(this);
//         binding.setListener(this);
        sessionManager = new SessionManager(getContext());

        getUnitList();
        binding.edtunitname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerDialogUnitNo.showSpinerDialog();
            }
        });

        if (sessionManager.getBoolean(SessionManager.CHKBOX)) {
            binding.setModel(new LoginModel(sessionManager.getString(SessionManager.USER_NAME),sessionManager.getString(SessionManager.PASSWORD),sessionManager.getString(SessionManager.UNIT_NAME)));
            binding.savedUsernamePassword.setChecked(true);
        } else {
            binding.setModel(new LoginModel());
        }


        spinnerDialogUnitNo = new SpinnerDialog(getActivity(), items, "Select or Search Unit Name", "Close");// With No Animation

        final List<String> datas = new ArrayList<>();

        spinnerDialogUnitNo.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                Toast.makeText(getContext(), item, Toast.LENGTH_SHORT).show();
                binding.edtunitname.setText(item);
                datas.add(divisionMap.get(divisionsModelArrayList.get(position).getCode()));
                Log.d("", "" + divisionsModelArrayList.get(position).getCode());
                sessionManager.save(SessionManager.UNIT_CODE,divisionsModelArrayList.get(position).getCode());
            }
        });

//        spinnerDialogUnitNo.bindOnSpinerListener(new OnSpinerItemClick() {
//            @Override
//            public void onClick(String item, int position) {
//                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
//                    return;
//                }
//                mLastClickTime = SystemClock.elapsedRealtime();
//
//                Toast.makeText(getContext(), item, Toast.LENGTH_SHORT).show();
//                binding.edtunitname.setText(item);
//                datas.add(divisionMap.get(divisionsModelArrayList.get(position).getCode()));
//                Log.d("", "" + divisionsModelArrayList.get(position).getCode());
//                sessionManager.save(SessionManager.UNIT_CODE,divisionsModelArrayList.get(position).getCode());
//            }
//        });



        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (!TextUtils.isEmpty(binding.edtUserid.getText().toString().trim()) &&
                            !TextUtils.isEmpty(binding.edtPassword.getText().toString().trim()) &&
                            !TextUtils.isEmpty(binding.edtunitname.getText().toString().trim())) {


//                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_homeFragment);
                        if (binding.savedUsernamePassword.isChecked()) {
                            sessionManager.save(SessionManager.USER_NAME, binding.edtUserid.getText().toString().trim());
                            sessionManager.save(SessionManager.PASSWORD, binding.edtPassword.getText().toString().trim());
                            sessionManager.save(SessionManager.UNIT_NAME, binding.edtunitname.getText().toString().trim());
                            sessionManager.save(SessionManager.CHKBOX, Boolean.TRUE);
                        } else {
                            sessionManager.save(SessionManager.USER_NAME, "");
                            sessionManager.save(SessionManager.PASSWORD, "");
                            sessionManager.save(SessionManager.UNIT_NAME, "");
                            sessionManager.save(SessionManager.CHKBOX, Boolean.FALSE);
                        }

                        LoginModel unitModel = new LoginModel();
                        unitModel.setUnitcode(sessionManager.getString("unit_code"));
                        unitModel.setUserid(binding.edtUserid.getText().toString().trim());
                        unitModel.setPassword(binding.edtPassword.getText().toString().trim());
                        if (unitModel != null) {
                            getLoginCall(unitModel);
                        } else {
                            Toast.makeText(getContext(), "Please Enter UserId And Password And UnitName", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getContext(), "Please Enter UserId And Password And UnitName", Toast.LENGTH_SHORT).show();
                    }

            }
        });

//        machine_code  = getUniqueIMEIId(getContext());

        return binding.getRoot();
    }

    private void getUnitList() {
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please wait");
        dialog.show();
        dialog.setCancelable(true);
        loginViewModel.callGetUnitList().observe(getViewLifecycleOwner(), unitResponse -> {
            dialog.dismiss();
            if (unitResponse != null) {
                if (unitResponse.getStatus()) {
                    divisionsModelArrayList.clear();
                    items.clear();
                    divisionMap.clear();
                    if (unitResponse.getData() != null) {
                        divisionsModelArrayList = unitResponse.getData();
                        for (UnitModel divisionsModel : divisionsModelArrayList) {
                            items.add(divisionsModel.getCode() + " (" + divisionsModel.getName() + ")");
                            divisionMap.put(divisionsModel.getCode(), divisionsModel.getName());
                        }
                    }
                } else {
                    Toast.makeText(getContext(), "Unit List Not found", Toast.LENGTH_SHORT).show();
                    Log.e("BarCode Verify Result", "Qr Code Not Available");
                }

            } else {
                Toast.makeText(getContext(), "Unit List Not Working From server Side", Toast.LENGTH_SHORT).show();
                Log.e("BarCode Verify Result", "Unit Verify Call Not Working From server Side");
            }
        });
    }

    private void getLoginCall(LoginModel loginModel) {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.show();
        loginViewModel.callGetLoginDetail(loginModel).observe(getViewLifecycleOwner(), (LoginResponse loginResponse) -> {
            progressDialog.dismiss();
            if (loginResponse!=null) {
                if (loginResponse.getStatus()) {
                    sessionManager.save(SessionManager.USER_NAME,binding.edtUserid.getText().toString().trim());
                    sessionManager.save(SessionManager.UNIT_NAME, binding.edtunitname.getText().toString().trim());
//                  sessionManager.save(SessionManager.MACHINE_CODE,machine_code);
//                  sessionManager.save("unit_code",divisioCode_Pos);
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_homeFragment);
                    Toast.makeText(getContext(), "Login Successfully" , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "" + loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(getContext(), "Server Not Respond" , Toast.LENGTH_SHORT).show();
            }
        });
    }
//
//    @SuppressLint("HardwareIds")
//    public static String getUniqueIMEIId(Context context) {
//        try {
//
//            String deviceId;
//
//            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//
//                deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
//
//            } else {
//
//                final TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//
//
//                if (mTelephony.getDeviceId() != null) {
//                    deviceId = mTelephony.getDeviceId();
//                } else {
//                    deviceId = Settings.Secure.getString(
//                            context.getContentResolver(),
//                            Settings.Secure.ANDROID_ID);
//                }
//            }
//
//            return deviceId;
//
//
//        } catch (Exception e) {
//            StringWriter errors = new StringWriter();
//            e.printStackTrace(new PrintWriter(errors));
//            return errors.toString();
//            //e.printStackTrace();
//        }
//
//    }

}