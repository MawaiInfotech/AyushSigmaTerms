package com.mawai.sigmaterms.home;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.mawai.sigmaterms.R;
import com.mawai.sigmaterms.utills.SessionManager;


public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }

    CardView fg_receive,fg_dispatch,dispatch,unpack,dispatchcancel,pallet_repacking;
    SessionManager sessionManager;
    TextView text_unit_name,text_username;
    Toolbar toolbar;
    ImageView img_arrow,img_logout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        fg_receive = view.findViewById(R.id.fg_receive_camera);
        fg_dispatch = view.findViewById(R.id.fg_dispatch_camera);
        pallet_repacking = view.findViewById(R.id.pallet_repacking);
        dispatch = view.findViewById(R.id.dispatch);
        unpack = view.findViewById(R.id.unpack);
        dispatchcancel = view.findViewById(R.id.dispatchcancel);
        text_username = view.findViewById(R.id.text_username);
        text_unit_name = view.findViewById(R.id.text_unit_name);
        img_arrow = view.findViewById(R.id.img_arrow);
        img_logout = view.findViewById(R.id.img_logout);

        toolbar = view.findViewById(R.id.toolbar);


//DSFASDFASDFSDAFSDFDSFASD
//        toolbar.setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(getContext());

        text_unit_name.setText(sessionManager.getString("unit_name"));
        text_username.setText(sessionManager.getString("user_name"));

        img_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment);
            }
        });

        fg_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_masterCartonPackingFragment);
            }
        });
        fg_dispatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_palletScanningFragment);
            }
        });

        dispatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_dispatchFragment);
            }
        });

        unpack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_unPackFragment);
            }
        });

        dispatchcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_dispatchCancelFragment);
            }
        });

        pallet_repacking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_palletRePackingFragment);
            }
        });

        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });
        onBackPress();
        return view;
    }

    private void onBackPress() {
        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                showAlertDialog();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }

    private void showAlertDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext(), R.style.MyDialogTheme);
        alertDialog.setTitle("Sign out Confirmation");
        alertDialog.setMessage("Are you sure you want to sign out ?");
        LinearLayout container = new LinearLayout(getContext());
        container.setOrientation(LinearLayout.VERTICAL);
//        alertDialog.setIcon(R.drawable.eloficone);

        alertDialog.setPositiveButton("YES", (dialogInterface, i) -> {
//            Intent intent = new Intent(getContext(), LoginActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
            Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_loginFragment);

        });

        alertDialog.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());

        alertDialog.show();
    }
}