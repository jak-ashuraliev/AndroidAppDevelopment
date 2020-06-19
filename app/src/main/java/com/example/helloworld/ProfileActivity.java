package com.example.helloworld;

import android.Manifest;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements MatchesFragment.LikedClickListener {
    private MatchViewModel viewModel;
    private static final String TAG = ProfileActivity.class.getName();
    private Bundle matchesBundle;
    private boolean matchesRetrieved = false;
    private ArrayList<MatchItem> matches;
    private SettingsViewModel mSettingsViewModel;
    private int matchDistance = 10;
    LocationManager locationManager;
    double longitude, latitude;
    private int LOCATION_PERMISSION_CODE = 1;

    public boolean calculatedistance(double latitude, double longitude, MatchItem match) {
        boolean result = false;

        float[] mDistanceResults = new float[3];

        Location.distanceBetween(latitude, longitude, Float.parseFloat(match.lat),
                Float.parseFloat(match.longitude), mDistanceResults);

        if (mDistanceResults[0] / 1600 > 10) {
            result = true;
        }

        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_layout);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        mSettingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        viewModel = new MatchViewModel();
        Intent mainIntent = getIntent();
        Bundle bundleIntent = mainIntent.getExtras();

        matchesBundle = new Bundle();
        viewModel.getMatchItems((ArrayList<MatchItem> matchItems) -> {
            matches = matchItems;

            Iterator<MatchItem> matchItemIterator = matches.iterator();

            if (longitude != 0.0) {
                while (matchItemIterator.hasNext()) {
                    MatchItem match = matchItemIterator.next();

                    if (calculatedistance(latitude, longitude, match)) {
                        matchItemIterator.remove();
                    }
                }
            }

            matchesBundle.putParcelableArrayList(Constants.COLLECTION_MATCHES, matches);
            matchesRetrieved = true;
        });
        toggleNetworkUpdates();
        MatchesFragment matchesFragment = new MatchesFragment();
        ProfileFragment profileFragment = new ProfileFragment();
        SettingsFragment settingsFragment = new SettingsFragment();

        profileFragment.setArguments(bundleIntent);
        settingsFragment.setArguments(bundleIntent);

        ViewPager2 viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.tablayout);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        matchesFragment.setArguments(matchesBundle);
        viewPagerAdapter.addFragment(profileFragment);
        viewPagerAdapter.addFragment(matchesFragment);
        viewPagerAdapter.addFragment(settingsFragment);

        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText(R.string.fragTitlePROFILE);
                    } else if (position == 1) {
                        tab.setText(R.string.fragTitleMATCHES);
                    } else {
                        tab.setText(R.string.fragTitleSETTINGS);
                    }
                }
        ).attach();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(Constants.LOCATION_ENABLE)
                .setMessage(Constants.LOCATION_MSG)
                .setPositiveButton(Constants.LOCATION_SETTINGS, (paramDialogInterface, paramInt) -> {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivityForResult(myIntent, Constants.GET_LOCATION_SETTINGS);
                })
                .setNegativeButton(Constants.LOCATION_CANCEL, (paramDialogInterface, paramInt) -> {});
        dialog.show();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        toggleNetworkUpdates();
//        viewModel.getMatchItems((ArrayList<MatchItem> matchItems) -> {
//            matches = matchItems;
//            Iterator<MatchItem> matchItemIterator = matches.iterator();
//            while (matchItemIterator.hasNext()) {
//                MatchItem match = matchItemIterator.next();
//                float[] mDistanceResults = new float[3];
//                Location.distanceBetween(latitude, longitude, Float.parseFloat(match.lat),
//                        Float.parseFloat(match.longitude), mDistanceResults);
//                double mDistanceMiles = mDistanceResults[0] / 1609.34;
//                if (mDistanceMiles > 10) {
//                    matchItemIterator.remove();
//                }
//            }
//            matchesBundle.putParcelableArrayList(Constants.COLLECTION_MATCHES, matches);
//            matchesRetrieved = true;
//        });
//    }

//    public void requestUpdateGPS() {
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
//                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60 * 1000, 10, locationListenerGPS);
//        } else {
//            showAlert();
//        }
//    }


    public void toggleNetworkUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60 * 1000, 10, locationListenerGPS);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                showAlert();
            } else {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle(Constants.LOCATION_ENABLE)
                        .setMessage(Constants.LOCATION_MSG)
                        .setPositiveButton(Constants.LOCATION_ENABLE, (paramDialogInterface, paramInt) -> {
                            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
                        })
                        .setNegativeButton(Constants.LOCATION_CANCEL, (paramDialogInterface, paramInt) -> {
                        });
                dialog.show();
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toggleNetworkUpdates();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOCATION_PERMISSION_CODE)
            toggleNetworkUpdates();
    }


    private final LocationListener locationListenerGPS = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}

        @Override
        public void onProviderEnabled(String s) {}

        @Override
        public void onProviderDisabled(String s) {}
    };

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.clear();
    }

    @Override
    public void LikedClickListener(MatchItem item) {
        item.liked = !item.liked;
        viewModel.updateMatchItem(item);
    }

    public static class ViewPagerAdapter extends FragmentStateAdapter {

        private List<Fragment> fragments = new ArrayList<>();

        ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        void addFragment(Fragment fragment) {
            fragments.add(fragment);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }
    }
}











