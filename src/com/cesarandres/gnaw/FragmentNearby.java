package com.cesarandres.gnaw;

import java.util.ArrayList;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;

import com.gnaw.discovery.event.BroadcastingEndEvent;
import com.gnaw.discovery.event.BroadcastingEndEventListener;
import com.gnaw.discovery.event.ClientFoundEvent;
import com.gnaw.discovery.event.ClientFoundEventListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link FragmentNearby.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link FragmentNearby#newInstance} factory method
 * to create an instance of this fragment.
 * 
 */
public class FragmentNearby extends Fragment implements
		BroadcastingEndEventListener, ClientFoundEventListener {

	private OnFragmentInteractionListener mListener;
	private ArrayList<String> clientsFound;
	
	public static FragmentNearby newInstance() {
		FragmentNearby fragment = new FragmentNearby();
		return fragment;
	}

	public FragmentNearby() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		clientsFound = new ArrayList<String>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_nearby, container, false);

		((CheckBox) view.findViewById(R.id.checkBox1))
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							MainActivity.application.startBroadcasting(FragmentNearby.this, 30);
						} else {
							MainActivity.application.stopBroadcasting();
						}
					}
				});

		((CheckBox) view.findViewById(R.id.checkBox2))
		.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					MainActivity.application.startListening(FragmentNearby.this);
					clientsFound.clear();
				} else {
					MainActivity.application.stopListening();
				}
			}
		});
		
		((ListView)view.findViewById(R.id.listView1)).setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, clientsFound));
		
		return view;
	}

	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	@Override
	public void BroadcastingEndEventOccurred(BroadcastingEndEvent evt) {
		((CheckBox) getView().findViewById(R.id.checkBox1)).setChecked(false);
	}

	@Override
	public void ClientFoundEventOccurred(ClientFoundEvent evt) {
		clientsFound.add((String) evt.getSource());
	}
}
