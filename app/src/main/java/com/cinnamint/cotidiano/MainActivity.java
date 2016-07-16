package com.cinnamint.cotidiano;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private static List<Words> availableWords;

    private long MILLISECONDS_PER_DAY = 24 * 60 * 60 * 1000;

    private long daysSinceCinnamintEpoch;

    private Calendar cinnamintEpoch;

    private static final int CINNAMINT_EPOCH_DAY = 15;
    private static final int CINNAMINT_EPOCH_MONTH = 7 - 1;
    private static final int CINNAMINT_EPOCH_YEAR = 2016;

    private static final String TAG = "CINNAMINT_COTIDIANO";


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // July 14, 2016: day 1 of development
        // Add the appropriate number of days
        cinnamintEpoch = Calendar.getInstance();
        cinnamintEpoch.set(Calendar.DAY_OF_MONTH, CINNAMINT_EPOCH_DAY);
        cinnamintEpoch.set(Calendar.MONTH, CINNAMINT_EPOCH_MONTH);
        cinnamintEpoch.set(Calendar.YEAR, CINNAMINT_EPOCH_YEAR);


        Calendar today = Calendar.getInstance();
        daysSinceCinnamintEpoch = (long) Math.ceil(
                                            (today.getTimeInMillis() - cinnamintEpoch.getTimeInMillis()) / MILLISECONDS_PER_DAY
                                            );

        Log.d(TAG, Long.toString(daysSinceCinnamintEpoch));


        WordDatabase wdb = new WordDatabase(getApplicationContext());
        wdb.open();
        availableWords = wdb.getEveryWordByDate(daysSinceCinnamintEpoch);
        wdb.close();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), availableWords.size());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Move to farthest right position
        mViewPager.setCurrentItem(availableWords.size() - 1);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);


            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView dayOfWeekLabel = (TextView) rootView.findViewById(R.id.day_of_week_label);
            TextView wordLabel = (TextView) rootView.findViewById(R.id.word);
            TextView definitionLabel = (TextView) rootView.findViewById(R.id.definition);
            TextView dateLabel = (TextView) rootView.findViewById(R.id.date);

            int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);


            // July 14, 2016: day 1 of development
            // Add the appropriate number of days
            Calendar fragmentDate = Calendar.getInstance();
            fragmentDate.set(Calendar.DAY_OF_MONTH, CINNAMINT_EPOCH_DAY + (sectionNumber - 1));
            fragmentDate.set(Calendar.MONTH, CINNAMINT_EPOCH_MONTH);
            fragmentDate.set(Calendar.YEAR, CINNAMINT_EPOCH_YEAR);

            // Format the day and date
            Date date = fragmentDate.getTime();
            SimpleDateFormat format = new SimpleDateFormat("d MMMM, yyyy", new Locale("es", "ES"));

            Words targetWord = MainActivity.availableWords.get(sectionNumber - 1);
            String dayOfWeekContent = (new SimpleDateFormat("EEEE", new Locale("es", "ES"))).format(date.getTime());
            String wordContent = targetWord.getText();
            String definitionContent = targetWord.getDefinition();
            String dateContent = format.format(date);



            dayOfWeekLabel.setText(dayOfWeekContent);
            wordLabel.setText(wordContent);
            definitionLabel.setText(definitionContent);
            dateLabel.setText(dateContent);

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private int items = 1;

        public SectionsPagerAdapter(FragmentManager fm, int item_count) {
            super(fm);
            items = item_count;
        }



        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return items;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
