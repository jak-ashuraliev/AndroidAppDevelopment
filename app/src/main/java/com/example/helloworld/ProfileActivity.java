package com.example.helloworld;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements MatchesFragment.LikedClickListener {

    private MatchViewModel viewModel;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_layout);

        Bundle userInfo = getIntent().getExtras();

        ProfileFragment profileFragment = new ProfileFragment();
        MatchesFragment matchesFragment = new MatchesFragment();
        SettingsFragment settingsFragment = new SettingsFragment();

        profileFragment.setArguments(userInfo);
        settingsFragment.setArguments(userInfo);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);

        Adapter adapter = new Adapter(this);
        adapter.addFragment(profileFragment);
        adapter.addFragment(matchesFragment);
        adapter.addFragment(settingsFragment);

        viewModel = new MatchViewModel();

        viewModel.getMatchItems(
                (ArrayList<MatchItem> matchItems) -> {
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(Constants.COLLECTION_MATCHES, matchItems);
                    matchesFragment.setArguments(bundle);
                }
        );

        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0)
            {
                tab.setText(R.string.fragTitlePROFILE);
            }
            else if (position == 1)
            {
                tab.setText(R.string.fragTitleMATCHES);
            }
            else if (position == 2)
            {
                tab.setText(R.string.fragTitleSETTINGS);
            }
        }).attach();
    }

    @Override
    public void LikedClickListener(MatchItem item) {
        item.liked = !item.liked;
        viewModel.updateMatchItem(item);
    }

    @Override
    protected void onPause() {
        viewModel.clear();
        super.onPause();
    }

    public static class Adapter extends FragmentStateAdapter {

        private List<Fragment> fragments = new ArrayList<>();

        Adapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        public void addFragment(Fragment fragment) {
            fragments.add(fragment);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }

    }

    public void goToMainActivity(View view) {
        finish();
    }

}
