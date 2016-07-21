package com.cinnamint.cotidiano;


import android.icu.util.Calendar;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)


@LargeTest
public class UiTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
    private String dayOfWeekContent;
    private UiDevice mDevice;

    @Before
    public void setUp() {
    }

    @Test
    public void labelDisplaysToday() {

        if (android.os.Build.VERSION.SDK_INT >= 24) {
            Calendar date = Calendar.getInstance();
            dayOfWeekContent = (new SimpleDateFormat("EEEE", new Locale("es", "ES"))).format(date.getTime());
            onView(allOf(withId(R.id.day_of_week_label), isDisplayed()))
                    .check(matches(withText(dayOfWeekContent)));
        }

    }


    @Test
    public void widgetTextMatches() {

        onView(withId(R.id.spanish_word))
                .check(matches(withText("llevar")));

    }





}
