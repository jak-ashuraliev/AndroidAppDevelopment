package com.example.helloworld;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ProfileActivityTest {

    @Rule
    public ActivityScenarioRule<ProfileActivity> activityScenarioRule
            = new ActivityScenarioRule<>(ProfileActivity.class);

    @Test
    public void hasPhotoOnProfileScreen() {
        onView(withId(R.id.profile_photo)).check(matches(isDisplayed()));
    }

    @Test
    public void hasNameOnProfileScreen() {
        onView(withId(R.id.tvName)).check(matches(withText(Constants.KEY_EMPTY)));
    }

    @Test
    public void nameOnProfileScreenIsVisible() {
        onView(withId(R.id.tvName)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void ageOnProfileScreenIsVisible() {
        onView(withId(R.id.tvAge)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void occupationOnProfileScreenIsVisible() {
        onView(withId(R.id.tvOccupation)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void hasButtonSignout() {
        onView(withId(R.id.signout_btn)).perform(scrollTo());
        onView(withId(R.id.signout_btn)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.signout_btn)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.signout_btn)).perform(scrollTo()).perform(click());
    }

    @Test
    public void checktMatchesTab(){
        onView(withText("MATCHES")).perform(click());
        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition(1, click()));
        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition(2, click()));
        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition(3, click()));
        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition(4, click()));
        onView(withId(R.id.recycler_view)).perform(actionOnItemAtPosition(5, click()));
    }

}