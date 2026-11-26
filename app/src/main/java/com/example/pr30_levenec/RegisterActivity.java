package com.example.pr30_levenec;

import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText etPassword, etConfirmPassword;
    private boolean isPassVisible = false;
    private boolean isConfirmPassVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        // Глазик для основного пароля
        etPassword.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (etPassword.getCompoundDrawables()[2] != null &&
                        event.getRawX() >= (etPassword.getRight() - etPassword.getCompoundDrawables()[2].getBounds().width() - 50)) {
                    isPassVisible = !isPassVisible;
                    togglePassword(etPassword, isPassVisible);
                    return true;
                }
            }
            return false;
        });

        // Глазик для подтверждения пароля
        etConfirmPassword.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (etConfirmPassword.getCompoundDrawables()[2] != null &&
                        event.getRawX() >= (etConfirmPassword.getRight() - etConfirmPassword.getCompoundDrawables()[2].getBounds().width() - 50)) {
                    isConfirmPassVisible = !isConfirmPassVisible;
                    togglePassword(etConfirmPassword, isConfirmPassVisible);
                    return true;
                }
            }
            return false;
        });

        // Кнопка регистрации
        findViewById(R.id.btnRegister).setOnClickListener(v -> {
            Toast.makeText(this, "Account Created!", Toast.LENGTH_SHORT).show();
            finish(); // Закрываем экран и возвращаемся на Логин
        });

        // Кнопка "Login" снизу
        findViewById(R.id.tvAlreadyHave).setOnClickListener(v -> {
            finish(); // Закрываем этот экран, возвращаемся на MainActivity (Логин)
        });
    }

    private void togglePassword(EditText editText, boolean visible) {
        if (visible) {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        editText.setSelection(editText.getText().length());
    }
}