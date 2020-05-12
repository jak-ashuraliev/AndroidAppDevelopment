package com.example.helloworld;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ProfileFragment profileFragment;
    private MatchesFragment matchesFragment;
    private SettingsFragment settingsFragment;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_layout);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);

        Bundle userInfo = getIntent().getExtras();

        profileFragment = new ProfileFragment();
        matchesFragment = new MatchesFragment();
        settingsFragment = new SettingsFragment();
        profileFragment.setArguments(userInfo);

        Adapter adapter = new Adapter(getSupportFragmentManager(), getLifecycle());
        adapter.addFragment(profileFragment, "PROFILE");
        adapter.addFragment(new MatchesFragment(), "MATCHES");
        adapter.addFragment(new SettingsFragment(), "SETTINGS");
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0)
            {
                tab.setText("PROFILE");
            }
            else if (position == 1)
            {
                tab.setText("MATCHES");
            }
            else if (position == 2)
            {
                tab.setText("SETTINGS");
            }
        }).attach();

    }

    public void goToMainActivity(View view) {
        finish();
    }

    public static class Adapter extends FragmentStateAdapter {

        private final List<Fragment> fragments = new ArrayList<>();

        Adapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }

    }

}
