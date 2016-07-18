package com.cinnamint.cotidiano;


import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MainUnitTest extends Mockito {

    @Mock
    Context mMockContext;

    @Mock
    ApplicationInfo mApplicationInfo;

    @Mock
    SQLiteDatabase mSQLiteDatabase;

    @Mock
    AssetManager mAssetManager;

    @Mock
    InputStream mInputStream;

    @Mock
    MainActivity mMainActivity;

    private static final int MOCK_DAY = 20;
    private static final int MOCK_MONTH = 7 - 1;
    private static final int EXPECTED_DAYS = 4;
    private static final int CINNAMINT_EPOCH_DAY = 16;
    private static final int CINNAMINT_EPOCH_MONTH = 6;
    private static final int CINNAMINT_EPOCH_YEAR = 2016;



//    @Test
//    public void dateValidator_DaysToLoad_ReturnLong() throws Exception {
//
//        Calendar mockTodayDate = Calendar.getInstance();
//        mockTodayDate.set(Calendar.DAY_OF_MONTH, MOCK_DAY);
//        mockTodayDate.set(Calendar.MONTH, MOCK_MONTH);
//        mockTodayDate.set(Calendar.YEAR, MainActivity.CINNAMINT_EPOCH_YEAR);
//
//
//        // July 14, 2016: day 1 of development
//        // Add the appropriate number of days
//        Calendar cinnamintEpoch = Calendar.getInstance();
//        cinnamintEpoch.set(Calendar.DAY_OF_MONTH, CINNAMINT_EPOCH_DAY);
//        cinnamintEpoch.set(Calendar.MONTH, CINNAMINT_EPOCH_MONTH);
//        cinnamintEpoch.set(Calendar.YEAR, CINNAMINT_EPOCH_YEAR);
//
//
//
//        mMainActivity.instantiateCinnamintEpoch();
//
//        assertEquals(EXPECTED_DAYS, mMainActivity.getDaysSinceCinnamintEpoch(mockTodayDate));
//
//    }




//    @Test
//    public void dateValidator_CorrectNumberOfWordsGrab_ReturnWords() throws Exception {
//
//        when(mMockContext.getApplicationInfo()).thenReturn(mApplicationInfo);
//        when(mMockContext.getAssets()).thenReturn(mAssetManager);
//        when(mAssetManager.open("databases/word.db")).thenReturn(mInputStream);
//
//        WordDatabase testWordDatabase = new WordDatabase(mMockContext);
//
//        when(testWordDatabase.getWritableDatabase()).thenReturn(mSQLiteDatabase);
//
//        testWordDatabase.open();
//        List<Words> results = testWordDatabase.getEveryWordByDate(4);
//        testWordDatabase.close();
//        assertThat(results.size(), is(4));
//
//    }



}
