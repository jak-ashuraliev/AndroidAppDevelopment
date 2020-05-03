package com.example.helloworld;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

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
        onView(withId(R.id.etUsername)).perform(typeText(Constants.TEST_KEY_USERNAME), closeSoftKeyboard());
        onView(withId(R.id.etUsername)).check(matches(withText(Constants.TEST_KEY_USERNAME)));
    }

    @Test
    public void hasValidFullname() {
        onView(withId(R.id.etFullname)).perform(typeText(Constants.TEST_KEY_FULLNAME), closeSoftKeyboard());
        onView(withId(R.id.etFullname)).check(matches(withText(Constants.TEST_KEY_FULLNAME)));
    }

    @Test
    public void hasValidEmail() {
        onView(withId(R.id.etEmail)).perform(typeText(Constants.TEST_KEY_EMAIL), closeSoftKeyboard());
        onView(withId(R.id.etEmail)).check(matches(withText(Constants.TEST_KEY_EMAIL)));
    }

    @Test
    public void hasButtonSelectDate() {
        onView(withId(R.id.btnSelectDate)).perform(ViewActions.scrollTo()).perform(click());
    }

    @Test
    public void hasValidPassword() {
        onView(withId(R.id.etPassword)).perform(typeText(Constants.TEST_KEY_PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).check(matches(withText(Constants.TEST_KEY_PASSWORD)));
    }

    @Test
    public void hasButtonSignup() {
        onView(withId(R.id.signup_btn)).perform(click());
    }

}
