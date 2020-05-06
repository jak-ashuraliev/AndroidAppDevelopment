package com.example.helloworld;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ProfileActivityTest {

    @Rule
    public ActivityScenarioRule<ProfileActivity> activityScenarioRule
            = new ActivityScenarioRule<>(ProfileActivity.class);

    @Test
    public void hasLogo() {
        onView(withId(R.id.app_logo)).check(matches(isDisplayed()));
    }

    @Test
    public void hasWelcomeMsgScreen() {
        onView(withId(R.id.tvReceiveData2)).check(matches(withText(R.string.welcomeMsg)));
    }

    @Test
    public void hasButtonSignout() {
        onView(withId(R.id.signout_btn)).check(matches(withText(R.string.sign_out_btn)));
    }


}