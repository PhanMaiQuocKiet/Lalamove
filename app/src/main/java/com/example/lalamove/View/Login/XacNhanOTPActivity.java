package com.example.lalamove.View.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chaos.view.PinView;
import com.example.lalamove.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class XacNhanOTPActivity extends AppCompatActivity {
    private PinView pinView;
    private Button btn_xacnhan_OTP;
    private String IdOTP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.layout_xacnhanotp);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        IdOTP = getIntent().getStringExtra("verificationId");
        btn_xacnhan_OTP.setOnClickListener(v ->
        {
            String inputOTP = pinView.getText().toString();
            if (inputOTP.length() == 6) {
                XacNhanOTP(inputOTP);
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ 6 số OTP", Toast.LENGTH_SHORT).show();
                }
            });
    }
    private void XacNhanOTP(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(IdOTP, code);
        DangKyVoiOTP(credential);
    }

    private void DangKyVoiOTP(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Xác thực thành công", Toast.LENGTH_SHORT).show();
                        Intent KQIntent = new Intent();
                        KQIntent.putExtra("KQotp", true);
                        setResult(RESULT_OK, KQIntent);
                        finish();
                        // Chuyển đến màn hình chính của ứng dụng
                    } else {
                        Toast.makeText(this, "Xác thực thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
            }
    void init()
    {
        pinView = findViewById(R.id.pinView);
        btn_xacnhan_OTP= findViewById(R.id.btn_xacnhan);
    }
}