package com.example.helloworld;

import android.content.Context;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private Context context = getInstrumentation().getTargetContext();

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void hasTextOnScreen() {
        onView(withId(R.id.textView)).check(matches(withText(R.string.get_started)));
        onView(withId(R.id.textView1)).check(matches(withText(R.string.signup_to_continue)));
        onView(withId(R.id.signup_with)).check(matches(withText(R.string.sign_up_with)));
        onView(withId(R.id.have_account)).check(matches(withText(R.string.have_account_login)));
        onView(withId(R.id.jak_ashuraliev)).check(matches(withText(R.string.jak_ashuraliev)));
    }

    @Test
    public void hasValidUsername() {
        onView(withId(R.id.etUsername)).check(matches(isDisplayed()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signup_btn)).perform(click());
        onView(withId(R.id.btnSelectDate)).perform(click());
    }

    @Test
    public void hasValidFullname() {
        onView(withId(R.id.etUsername)).check(matches(isDisplayed()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signup_btn)).perform(click());
        onView(withId(R.id.btnSelectDate)).perform(click());
    }

    @Test
    public void hasValidEmail() {
        onView(withId(R.id.etEmail)).check(matches(isDisplayed()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signup_btn)).perform(click());
        onView(withId(R.id.btnSelectDate)).perform(click());
    }

    @Test
    public void hasButtonSelectDate() {
        onView(withId(R.id.btnSelectDate)).perform(ViewActions.scrollTo()).perform(click());
    }

    @Test
    public void hasValidPassword() {
        onView(withId(R.id.etPassword)).check(matches(isDisplayed()));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signup_btn)).perform(click());
        onView(withId(R.id.btnSelectDate)).perform(click());
    }

    @Test
    public void hasButtonSignup() {
        onView(withId(R.id.signup_btn)).perform(click());
    }

}
