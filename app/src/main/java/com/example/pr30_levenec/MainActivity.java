package com.example.pr30_levenec;

import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etPassword;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPassword = findViewById(R.id.etPassword);

        // --- ЛОГИКА ГЛАЗИКА ДЛЯ ПАРОЛЯ ---
        etPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2; // Индекс иконки справа
            if (event.getAction() == MotionEvent.ACTION_UP) {
                // Если нажали именно на иконку справа (учитываем отступы)
                if (event.getRawX() >= (etPassword.getRight() - etPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width() - 50)) {
                    togglePasswordVisibility();
                    return true;
                }
            }
            return false;
        });

        // --- ОБРАБОТКА КНОПОК ---
        findViewById(R.id.btnLogin).setOnClickListener(v ->
                Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show()
        );

        findViewById(R.id.btnGoogle).setOnClickListener(v ->
                Toast.makeText(this, "Google Login", Toast.LENGTH_SHORT).show()
        );

        findViewById(R.id.btnFacebook).setOnClickListener(v ->
                Toast.makeText(this, "Facebook Login", Toast.LENGTH_SHORT).show()
        );

        findViewById(R.id.tvNoAccount).setOnClickListener(v -> {
            // Переходим на экран регистрации
            startActivity(new android.content.Intent(MainActivity.this, RegisterActivity.class));
        });

        findViewById(R.id.tvForgotPass).setOnClickListener(v ->
                Toast.makeText(this, "Reset Password", Toast.LENGTH_SHORT).show()
        );
    }

    // Метод переключения видимости пароля
    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            isPasswordVisible = false;
        } else {
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            isPasswordVisible = true;
        }
        // Ставим курсор в конец текста после смены типа
        etPassword.setSelection(etPassword.getText().length());
    }
}