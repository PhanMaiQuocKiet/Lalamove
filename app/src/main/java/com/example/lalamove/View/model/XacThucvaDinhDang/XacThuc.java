package com.example.lalamove.View.model.XacThucvaDinhDang;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;

import com.example.lalamove.View.Login.XacNhanOTPActivity;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class XacThuc {
    private static XacThuc instance;
    private FirebaseAuth mAuth;
    private Activity activity;
    private ActivityResultLauncher<Intent> IntentOTP;
    private XacThuc(Activity activity, ActivityResultLauncher<Intent> IntentOTP)
    {
        this.activity=activity;
        this.IntentOTP=IntentOTP;
        this.mAuth=FirebaseAuth.getInstance();
    }
    public static synchronized XacThuc getInstance(Activity activity, ActivityResultLauncher<Intent> IntentOTP) {
        if (instance == null) {
            instance = new XacThuc(activity, IntentOTP);
        } else {
            // Cập nhật thông tin Activity
            instance.setActivity(activity);
            instance.setOtpIntentLauncher(IntentOTP);
        }
        return instance;
    }
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setOtpIntentLauncher(ActivityResultLauncher<Intent> IntentOTP) {
        this.IntentOTP = IntentOTP;
    }
    public void guiYeuCauOTP(String phoneNumber) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this.activity)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                        Toast.makeText(activity, "Hoàn thành xác thực OTP", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(activity, "Xác thực thất bại: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                        Toast.makeText(activity, "Mã OTP đã được gửi", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity, XacNhanOTPActivity.class);
                        intent.putExtra("verificationId", verificationId);
                        IntentOTP.launch(intent);
                    }
                })
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}
