package com.example.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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

    private int matchDistance = 10;
    LocationManager locationManager;
    double longitudeGPS;
    double latitudeGPS;
    private Bundle userInfo;
    private int LOCATION_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_layout);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

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
        requestUpdateGPS();
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText(R.string.fragTitlePROFILE);
            } else if (position == 1) {
                tab.setText(R.string.fragTitleMATCHES);
            } else if (position == 2) {
                tab.setText(R.string.fragTitleSETTINGS);
            }
        }).attach();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(Constants.LOCATION_ENABLE)
                .setMessage(Constants.LOCATION_ON)
                .setPositiveButton(Constants.LOCATION_SETTINGS, (paramDialogInterface, paramInt) -> {
                    Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(i, LOCATION_PERMISSION_CODE);
                })
                .setNegativeButton(Constants.LOCATION_OFF, (paramDialogInterface, paramInt) -> {
                });
        dialog.show();
    }

    public void requestUpdateGPS() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60 * 1000, 10, locationListenerGPS);
        } else {
            showAlert();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestUpdateGPS();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOCATION_PERMISSION_CODE)
            requestUpdateGPS();
    }

    private final LocationListener locationListenerGPS = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeGPS = location.getLongitude();
            latitudeGPS = location.getLatitude();
            userInfo.putDouble(Constants.collection_latitude, latitudeGPS);
            userInfo.putDouble(Constants.collection_longitude, longitudeGPS);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}

        @Override
        public void onProviderEnabled(String s) {}

        @Override
        public void onProviderDisabled(String s) {}
    };

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
