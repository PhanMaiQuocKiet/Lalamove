package com.example.lalamove.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.lalamove.R;
import com.example.lalamove.View.model.TableTaiKhoan.TaiKhoanSQL;
import com.example.lalamove.View.model.XacThucvaDinhDang.DinhDang;

public class FragmentMKmoi extends Fragment {

    private EditText edt_mkmoi,edt_xacnhanmk;
    private Button btn_datmkmoi;
    private MKmoiListener listener;
    private String sdt;

    TaiKhoanSQL sql = new TaiKhoanSQL();


    public FragmentMKmoi() {
        // Required empty public constructor
    }
    public interface MKmoiListener {
        void CapNhatMK(String sdt , String mkmoi);
    }

    public static FragmentMKmoi newInstance(String param1, String param2) {
        FragmentMKmoi fragment = new FragmentMKmoi();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null)
        {
            sdt = getArguments().getString("sdt");
        }
    }
    public void setMKmoiListener(MKmoiListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mk_moi, container, false);
        edt_mkmoi = view.findViewById(R.id.edt_mkmoi_quenmk);
        edt_xacnhanmk=view.findViewById(R.id.edt_xacnhanmk_quenmk);
        btn_datmkmoi=view.findViewById(R.id.btn_datmkmoi_quenmk);
        btn_datmkmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KiemtraMK();
            }
        });
        return view;
    }
    void KiemtraMK()
    {
        String newPassword= edt_mkmoi.getText().toString();
        String confirmPassword=edt_xacnhanmk.getText().toString();
        if(!DinhDang.isDinhDangMatKhau(newPassword))
        {
            edt_mkmoi.setError("Mật khẩu phải có ít nhất 1 kí tự đặc biệt và in hoa");
        }
        else {
            if (newPassword.compareTo(confirmPassword)==0)
            {
                if(listener!=null)
                {
                    listener.CapNhatMK(sdt,newPassword);
                }
            }
            else {
                edt_xacnhanmk.setError("Mật khẩu chưa trùng ");
            }
        }
    }
}