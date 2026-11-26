package com.example.pr30_levenec;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private OnboardingAdapter adapter;
    private TextView tvButton; // Наша кнопка/текст

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tvButton = findViewById(R.id.tvSkip); // Находим нашу кнопку

        adapter = new OnboardingAdapter();
        viewPager.setAdapter(adapter);

        // Отключаем встроенный свайп, работает только твой OnSwipeTouchListener
        viewPager.setUserInputEnabled(false);

        // Связываем точки с ViewPager
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {}).attach();

        // Обработка нажатия на кнопку
        tvButton.setOnClickListener(v -> goToNextScreen());

        // Слушатель свайпов
        View rootLayout = findViewById(R.id.rootLayout);
        rootLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                goToNextScreen();
            }

            @Override
            public void onSwipeRight() {
                int currentItem = viewPager.getCurrentItem();
                if (currentItem > 0) {
                    viewPager.setCurrentItem(currentItem - 1, true);
                }
            }
        });

        // МАГИЯ: Следим за перелистыванием страниц, чтобы менять кнопку
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateButtonStyle(position);
            }
        });
    }

    // Метод, который делает кнопку обычной надписью или розовой кнопкой
    private void updateButtonStyle(int position) {
        if (position == 2) { // Если мы на 3-м экране (индекс 2)
            tvButton.setText(R.string.lets_connect);
            tvButton.setTextColor(Color.WHITE);
            tvButton.setBackgroundResource(R.drawable.bg_button_pink);
            // Чуть увеличиваем отступы, чтобы кнопка была шире
            tvButton.setPadding(64, 32, 64, 32);
        } else { // Если на 1 или 2 экране
            tvButton.setText(R.string.skip);
            tvButton.setTextColor(getResources().getColor(R.color.splash_bg));
            tvButton.setBackground(null); // Убираем фон
            tvButton.setPadding(32, 32, 32, 32); // Стандартные отступы
        }
    }

    // Переход дальше
    private void goToNextScreen() {
        int currentItem = viewPager.getCurrentItem();
        if (currentItem < adapter.getItemCount() - 1) {
            viewPager.setCurrentItem(currentItem + 1, true);
        } else {
            goToMainActivity();
        }
    }

    private void goToMainActivity() {
        startActivity(new Intent(OnboardingActivity.this, MainActivity.class));
        finish();
    }
}