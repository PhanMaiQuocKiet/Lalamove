package com.example.lalamove.View.Login;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.Fragment.FragmentMKmoi;
import com.example.lalamove.R;
import com.example.lalamove.View.model.TableTaiKhoan.TaiKhoanSQL;
import com.example.lalamove.View.model.XacThucvaDinhDang.DinhDang;
import com.example.lalamove.View.model.XacThucvaDinhDang.XacThuc;

public class QuenmkActivity extends AppCompatActivity implements FragmentMKmoi.MKmoiListener {
    private EditText edt_sdt;
    private Button btn_tieptuc;
    private ImageView btn_back;
    private RelativeLayout layout_quenmk;
    private FrameLayout layout_mkmoi;
    private ActivityResultLauncher<Intent> otpIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quenmatkhau);
         init();
         taoIntent();
        XacThuc xacthuc = XacThuc.getInstance(QuenmkActivity.this,otpIntent);
        btn_tieptuc.setOnClickListener(click->{
            xacthuc.guiYeuCauOTP(DinhDang.dinhDangSDT(edt_sdt.getText().toString()));
        });
         themEvent();


    }
    void themEvent()
    {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFieldsForEmptyValues();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action needed
            }

        };

        edt_sdt.addTextChangedListener(textWatcher);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    void taoIntent()
    {
        otpIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK)
                    {
                        Intent data = result.getData();
                        if(data!=null&&data.getBooleanExtra("kq",false)==true)
                        {
                            try {
                                showNewPasswordFragment();
                            } catch (Exception e) {
                                Log.e(TAG, "Lỗi khi đăng ký tài khoản: " + e.getMessage());
                            }
                        }
                        else
                        {
                            Toast.makeText(QuenmkActivity.this,"Xac thuc OTP khong thanh cong",Toast.LENGTH_SHORT);
                        }
                    }
                });//
    }
    void init()
{
    layout_mkmoi=findViewById(R.id.mkmoi_quenmk);
    layout_quenmk=findViewById(R.id.quenmk_layout);
edt_sdt=findViewById(R.id.edt_sdt_quenmk);
btn_tieptuc=findViewById(R.id.btn_tieptuc_quenmk);
btn_back=findViewById(R.id.btn_back_quenmk);
}
    private void checkFieldsForEmptyValues() {
        String phoneNumber = edt_sdt.getText().toString();

        if (!phoneNumber.isEmpty()) {
            btn_tieptuc.setEnabled(true);
            btn_tieptuc.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange_fresh)));
        } else {
            btn_tieptuc.setEnabled(false);
            btn_tieptuc.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.darker_gray)));
        }
    }
    private void showNewPasswordFragment() {
        layout_quenmk.setVisibility(View.GONE);
        layout_mkmoi.setVisibility(View.VISIBLE);
        FragmentMKmoi MKmoiFragment = new FragmentMKmoi();
        Bundle args = new Bundle();
        args.putString("sdt",edt_sdt.getText().toString());
        MKmoiFragment.setArguments(args);
        MKmoiFragment.setMKmoiListener(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mkmoi_quenmk, MKmoiFragment)
                .commit();

    }
    @Override
    public void CapNhatMK(String sdt, String mkmoi) {
        TaiKhoanSQL sql = new TaiKhoanSQL();
        sql.sp_update_mkTaiKhoan(sdt,mkmoi,QuenmkActivity.this);
        finish();
    }
}
