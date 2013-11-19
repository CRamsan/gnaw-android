package com.cesarandres.gnaw;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.gnaw.GnawApplication;
import com.gnaw.Profile;
import com.gnaw.interfaces.DataSourceInterface;
import com.gnaw.models.SharedFile;
import com.gnaw.request.Request;

public class MainActivity extends FragmentActivity implements
		OnFragmentInteractionListener, DataSourceInterface {

	public static GnawApplication application;
	private SharedFile sharedFiles = new SharedFile();

	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;
	private static final int SEARCH = 0;
	private static final int NEARBY = 1;
	private static final int PROFILE = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.viewPager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		Bundle extras = getIntent().getExtras();
		String activityMode = "";
		if (extras != null) {

		}

		this.application = new GnawApplication(this);
		//this.application.init();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.application.stopBroadcasting();
		this.application.stopListening();
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			switch (position) {
			case SEARCH:
				fragment = new FragmentSearch();
				break;
			case NEARBY:
				fragment = new FragmentNearby();
				break;
			case PROFILE:
				fragment = new FragmentProfile();
				break;
			default:
				break;
			}
			Bundle args = new Bundle();
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case SEARCH:
				return "Search";
			case NEARBY:
				return "Nearby";
			case PROFILE:
				return "Profile";
			default:
				return "Profile";
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub

	}

	@Override
	public Profile getProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SharedFile getSharedFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean postMessage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postOffer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postOfferResponse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postSearchRequest() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postSearchResult() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deliverMessage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deliverOffer(Request request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deliverOfferResponse(Request request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deliverSearchRequest() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deliverPushRequest(Request request) {
		// TODO Auto-generated method stub
		return false;
	}
}
