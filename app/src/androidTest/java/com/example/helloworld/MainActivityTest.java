package com.example.helloworld;

import android.os.RemoteException;
import android.widget.DatePicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void hasLogo() {
        onView(withId(R.id.app_logo)).check(matches(isDisplayed()));
    }

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
        onView(withId(R.id.etUsername)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.etUsername)).perform(typeText(Constants.TEST_KEY_USERNAME));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etUsername)).check(matches(withText(Constants.TEST_KEY_USERNAME)));
    }

    @Test
    public void hasValidFirstName() {
        onView(withId(R.id.etFirstname)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.etFirstname)).perform(typeText(Constants.TEST_KEY_FIRSTNAME));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etFirstname)).check(matches(withText(Constants.TEST_KEY_FIRSTNAME)));
    }

    @Test
    public void hasValidLastName() {
        onView(withId(R.id.etLastname)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.etLastname)).perform(typeText(Constants.TEST_KEY_FIRSTNAME));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etLastname)).check(matches(withText(Constants.TEST_KEY_FIRSTNAME)));
    }

    @Test
    public void hasValidEmail() {
        onView(withId(R.id.etEmail)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.etEmail)).perform(typeText(Constants.TEST_KEY_EMAIL));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.etEmail)).check(matches(withText(Constants.TEST_KEY_EMAIL)));
    }

    @Test
    public void btnScrollView() {
        onView(withId(R.id.etUsername)).perform(typeText(Constants.TEST_KEY_USERNAME));
        onView(withId(R.id.etFirstname)).perform(typeText(Constants.TEST_KEY_FIRSTNAME));
        onView(withId(R.id.etLastname)).perform(typeText(Constants.TEST_KEY_LASTNAME));
        onView(withId(R.id.etEmail)).perform(typeText(Constants.TEST_KEY_EMAIL));
        onView(withId(R.id.btnSelectDate)).perform(scrollTo()).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(setDate(Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btnSelectDate)).perform(scrollTo());
        onView(withId(R.id.signup_btn)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.signup_btn)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.signup_btn)).perform(scrollTo()).perform(click());
    }

    @Test
    public void userCanEnterUsername() {
        onView(withId(R.id.etUsername)).check(matches(isDisplayed()));
        onView(withId(R.id.etUsername)).perform(clearText());
        onView(withId(R.id.etFirstname)).perform(typeText(Constants.TEST_KEY_FIRSTNAME));
        onView(withId(R.id.etLastname)).perform(typeText(Constants.TEST_KEY_LASTNAME));
        onView(withId(R.id.etEmail)).perform(typeText(Constants.TEST_KEY_EMAIL));
        onView(withId(R.id.btnSelectDate)).perform(scrollTo()).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(setDate(Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btnSelectDate)).perform(scrollTo());
        onView(withId(R.id.signup_btn)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.signup_btn)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.signup_btn)).perform(scrollTo()).perform(click());
    }

    @Test
    public void userCanEnterFirstName() {
        onView(withId(R.id.etUsername)).perform(typeText(Constants.TEST_KEY_USERNAME));
        onView(withId(R.id.etFirstname)).perform(typeText(Constants.TEST_KEY_FIRSTNAME));
        onView(withId(R.id.etFirstname)).perform(clearText());
        onView(withId(R.id.etLastname)).perform(typeText(Constants.TEST_KEY_LASTNAME));
        onView(withId(R.id.etEmail)).perform(typeText(Constants.TEST_KEY_EMAIL));
        onView(withId(R.id.btnSelectDate)).perform(scrollTo()).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(setDate(Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btnSelectDate)).perform(scrollTo());
        onView(withId(R.id.signup_btn)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.signup_btn)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.signup_btn)).perform(scrollTo()).perform(click());
    }


    @Test
    public void userCanEnterLastName() {
        onView(withId(R.id.etUsername)).perform(typeText(Constants.TEST_KEY_USERNAME));
        onView(withId(R.id.etFirstname)).perform(typeText(Constants.TEST_KEY_FIRSTNAME));
        onView(withId(R.id.etLastname)).perform(typeText(Constants.TEST_KEY_LASTNAME));
        onView(withId(R.id.etLastname)).perform(clearText());
        onView(withId(R.id.etEmail)).perform(typeText(Constants.TEST_KEY_EMAIL));
        onView(withId(R.id.btnSelectDate)).perform(scrollTo()).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(setDate(Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btnSelectDate)).perform(scrollTo());
        onView(withId(R.id.signup_btn)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.signup_btn)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.signup_btn)).perform(scrollTo()).perform(click());
    }

    @Test
    public void userCanEnterEmail() {
        onView(withId(R.id.etUsername)).perform(typeText(Constants.TEST_KEY_USERNAME));
        onView(withId(R.id.etFirstname)).perform(typeText(Constants.TEST_KEY_FIRSTNAME));
        onView(withId(R.id.etLastname)).perform(typeText(Constants.TEST_KEY_LASTNAME));
        onView(withId(R.id.etEmail)).perform(typeText(Constants.TEST_KEY_EMAIL));
        onView(withId(R.id.etEmail)).perform(clearText());
        onView(withId(R.id.btnSelectDate)).perform(scrollTo()).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(setDate(Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btnSelectDate)).perform(scrollTo());
        onView(withId(R.id.signup_btn)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.signup_btn)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.signup_btn)).perform(scrollTo()).perform(click());
    }

    @Test
    public void userCanEnterBirthDate() {
        onView(withId(R.id.etUsername)).perform(typeText(Constants.TEST_KEY_USERNAME));
        onView(withId(R.id.etFirstname)).perform(typeText(Constants.TEST_KEY_FIRSTNAME));
        onView(withId(R.id.etLastname)).perform(typeText(Constants.TEST_KEY_LASTNAME));
        onView(withId(R.id.etEmail)).perform(typeText(Constants.TEST_KEY_EMAIL));
        onView(withId(R.id.btnSelectDate)).perform(scrollTo()).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(setDate(Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btnSelectDate)).perform(scrollTo());
        onView(withId(R.id.signup_btn)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.signup_btn)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.signup_btn)).perform(scrollTo()).perform(click());
    }

    @Test
    public void hasValidBirthDate() {
        onView(withId(R.id.btnSelectDate)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.btnSelectDate)).perform(scrollTo()).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(setDate(Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));

    }

    @Test
    public void onRotateCheckFields() throws RemoteException {
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.setOrientationRight();
        onView(withId(R.id.errorMsg)).check(matches(withText(Constants.KEY_EMPTY)));
        onView(withId(R.id.etUsername)).check(matches(withText(Constants.KEY_EMPTY)));
        onView(withId(R.id.etFirstname)).check(matches(withText(Constants.KEY_EMPTY)));
        onView(withId(R.id.etLastname)).check(matches(withText(Constants.KEY_EMPTY)));
        onView(withId(R.id.etEmail)).check(matches(withText(Constants.KEY_EMPTY)));
        onView(withId(R.id.tvBirthdate)).check(matches(withText(Constants.KEY_EMPTY)));
        onView(withId(R.id.etOccupation)).check(matches(withText(Constants.KEY_EMPTY)));
        onView(withId(R.id.etDescription)).check(matches(withText(Constants.KEY_EMPTY)));
        device.setOrientationNatural();
    }

    @Test
    public void hasHintTextInEachEditText() {
        onView(withId(R.id.etUsername)).check(matches(withHint(R.string.testUsername)));
        onView(withId(R.id.etFirstname)).check(matches(withHint(R.string.testFirstname)));
        onView(withId(R.id.etLastname)).check(matches(withHint(R.string.testLastname)));
        onView(withId(R.id.etEmail)).check(matches(withHint(R.string.testEmail)));
        onView(withId(R.id.tvBirthdate)).check(matches(withHint(R.string.testDOB)));
        onView(withId(R.id.etOccupation)).check(matches(withHint(R.string.testOccupation)));
        onView(withId(R.id.etDescription)).check(matches(withHint(R.string.testDescription)));
    }

    @Test
    public void onRestoreInstanceState() throws RemoteException {
        onView(withId(R.id.btnSelectDate)).perform(ViewActions.scrollTo()).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(setDate(Constants.TEST_YEAR, Constants.TEST_MONTH, Constants.TEST_DAY));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.btnSelectDate)).perform(scrollTo());
        onView(withId(R.id.signup_btn)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.signup_btn)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.signup_btn)).perform(scrollTo()).perform(click());
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.setOrientationRight();
        device.setOrientationNatural();

    }


}
