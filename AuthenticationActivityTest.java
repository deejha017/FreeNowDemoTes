package com.freenow.android_demo.activities;

import android.app.Activity;
import android.content.res.Resources;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.freenow.android_demo.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

public class AuthenticationActivityTest extends Activity {

    private Resources resources;
    private String mEditTextUsername;
    private String mEditTextPassword;
    @Rule
    public ActivityTestRule<AuthenticationActivityTest>mAuthenticationTestRule=new ActivityTestRule<AuthenticationActivityTest>(AuthenticationActivityTest.class);


    public AuthenticationActivityTest(Resources resources) {
        this.resources = resources;
    }

    @Before
    private void setUp() throws NullPointerException {
        resources = mAuthenticationTestRule.getActivity().getResources();
        Intents.init();
    }

    @After
    public void tearDown() throws NullPointerException {
        Intents.release();
    }

    @Test
    public void testSuccessfulLOGIN() throws IOException,NullPointerException {
        AuthenticationActivity act=new AuthenticationActivity();
        mEditTextUsername = "crazydog335";
        mEditTextPassword = "venture";

            // Input Correct Username as 'crazydog335'
            onView(withId((R.id.edt_username))).perform(ViewActions.clearText()).perform(typeText(mEditTextUsername));
            // Input Correct Password as 'venture'
            onView(withId((R.id.edt_password))).perform(ViewActions.clearText()).perform(typeText(mEditTextPassword));
            // Click Login Button
            onView(withId(R.id.btn_login)).perform(click());
            // Assert if User Logged in.
            assertTrue(mAuthenticationTestRule.getActivity().isFinishing());
        intended(hasComponent(MainActivity.class.getName()));
            Espresso.onView(ViewMatchers.withId(R.id.textSearch))
                    .check(ViewAssertions.matches((isDisplayed())));


    }

    @Test
    public void testLOGIN_InvalidPassword() throws IOException,NullPointerException {
        AuthenticationActivity act=new AuthenticationActivity();
        mEditTextUsername = "crazydog335";
        mEditTextPassword = "venturee";
        // Input Correct Username as 'crazydog335'
        onView(withId((R.id.edt_username))).perform(ViewActions.clearText()).perform(typeText(mEditTextUsername));
        // Input Invalid Password as 'venturee'
        onView(withId((R.id.edt_password))).perform(ViewActions.clearText()).perform(typeText(mEditTextPassword));
        // Click Login Button
        onView(withId(R.id.btn_login)).perform(click());
        // Assert User not Logged in.
        assertFalse(mAuthenticationTestRule.getActivity().isFinishing());
        intended(not(hasComponent(MainActivity.class.getName())));
        Espresso.onView(ViewMatchers.withId(R.id.textSearch))
                .check(ViewAssertions.matches(not(isDisplayed())));
    }

    @Test
public void testLOGIN_BlankUserPassword() throws IOException,NullPointerException{
    AuthenticationActivity act=new AuthenticationActivity();
    mEditTextUsername = "";
    mEditTextPassword = "";
    // Input Correct Username as 'crazydog335'
    onView(withId((R.id.edt_username))).perform(ViewActions.clearText()).perform(typeText(mEditTextUsername));
    // Input Invalid Password as 'venturee'
    onView(withId((R.id.edt_password))).perform(ViewActions.clearText()).perform(typeText(mEditTextPassword));
    // Click Login Button
    onView(withId(R.id.btn_login)).perform(click());
    // Assert User not Logged in.
    assertFalse(mAuthenticationTestRule.getActivity().isFinishing());
        intended(not(hasComponent(MainActivity.class.getName())));
    Espresso.onView(ViewMatchers.withId(R.id.textSearch))
            .check(ViewAssertions.matches(not(isDisplayed())));

}
}