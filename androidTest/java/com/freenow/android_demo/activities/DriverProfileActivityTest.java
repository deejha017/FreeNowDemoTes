package com.freenow.android_demo.activities;

import android.content.res.Resources;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
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
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class DriverProfileActivityTest {

    private DriverProfileActivity mActivity = null;
    private String mEditTextUsername;
    private String mEditTextPassword;
    @Rule
    public ActivityTestRule<DriverProfileActivity> mDriverProfileActivityTestRule=new ActivityTestRule<DriverProfileActivity>(DriverProfileActivity.class);
    private int id;


    @Before
    private void setUp() throws Exception {
        mActivity = mDriverProfileActivityTestRule.getActivity();
        Intents.init();
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }
    @Test
    public void searchDriver() throws IOException{
        //Type "sr" in the search field
        Espresso.onView(withId(R.id.textSearch)).perform(typeText("sr"));
        //Check if the results are available;
        Espresso.onView(withText("Sara Christensen"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        Espresso.onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        //Select 2nd driver from the search result by name
        Espresso.onView(withText("Sarah Scott"))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(click());
        //assert
        intended(hasComponent(DriverProfileActivity.class.getName()));
        Espresso.onView(withId(R.id.textViewDriverName))
                .check(matches(withText("Sarah Scott")));
        //Click on call button
        id = R.layout.content_driver_profile;
        Espresso.onView(withId(R.id.fab)).perform(click());
        // assert
        assertTrue(mDriverProfileActivityTestRule.getActivity().isFinishing());


    }
}