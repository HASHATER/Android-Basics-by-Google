package com.example.android.tourguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by hp on 9/13/2016.
 */
public class CasinosFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_page, container, false);

        final ArrayList<Locations> locations = new ArrayList<Locations>();

        locations.add(new Locations(R.string.casinos_Denizkizi_Casino, R.string.casinos_location_Denizkizi_Casino));
        locations.add(new Locations(R.string.casinos_Jasmine_Court_Casino, R.string.casinos_location_Jasmine_Court_Casino));
        locations.add(new Locations(R.string.casinos_Palm_Beach_Hotel_Casino, R.string.casinos_location_Palm_Beach_Hotel_Casino));
        locations.add(new Locations(R.string.casinos_Salamis_Bay_Conti, R.string.casinos_location_Salamis_Bay_Conti));

        LocationsAdapter adapter = new LocationsAdapter(getActivity(), locations, R.color.category_attractions);
        ListView listView = (ListView) rootView.findViewById(R.id.List);
        listView.setAdapter(adapter);

        return rootView;
    }
}
