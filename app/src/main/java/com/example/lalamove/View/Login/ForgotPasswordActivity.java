package com.example.lalamove.View.Login;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText etPhoneNumber, etNewPassword;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quenmatkhau);
        ImageView Back=findViewById(R.id.back_button);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etNewPassword = findViewById(R.id.etNewPassword);
        btnContinue = findViewById(R.id.btnContinue);

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

        etPhoneNumber.addTextChangedListener(textWatcher);
        etNewPassword.addTextChangedListener(textWatcher);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkFieldsForEmptyValues() {
        String phoneNumber = etPhoneNumber.getText().toString();
        String newPassword = etNewPassword.getText().toString();

        if (!phoneNumber.isEmpty() && !newPassword.isEmpty()) {
            btnContinue.setEnabled(true);
            btnContinue.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange_fresh)));
        } else {
            btnContinue.setEnabled(false);
            btnContinue.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.darker_gray)));
        }
    }

}
