package com.cinnamint.cotidiano;

import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)

@SmallTest
public class ApplicationTest extends InstrumentationTestCase {


    private Context context;
    private WordDatabase testWordDatabase;

    @Before
    public void setUp() throws Exception {

        context = InstrumentationRegistry.getContext();

        testWordDatabase = new WordDatabase(context);
        testWordDatabase.open();

    }

    @Test
    public void dateValidator_CorrectNumberOfWordsGrab_ReturnWords() throws Exception {

        List<Words> results = testWordDatabase.getEveryWordByDate(4);
        assertEquals(results.size(), 4);

    }

    @After
    public void tearDown() throws Exception {

        testWordDatabase.close();

    }

}