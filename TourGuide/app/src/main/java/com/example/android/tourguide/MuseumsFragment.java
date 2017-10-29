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
public class MuseumsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page, container, false);
        final ArrayList<Locations> locations = new ArrayList<Locations>();

        locations.add(new Locations(R.string.museums_Girne_Kalesi, R.string.museums_location_Girne_Kalesi));
        locations.add(new Locations(R.string.museums_Shipwreck_Museum, R.string.museums_location_Shipwreck_Museum));
        locations.add(new Locations(R.string.museums_St_Barnabas_Monastery_and_Icon_Museum, R.string.museums_location_St_Barnabas_Monastery_and_Icon_Museum));
        locations.add(new Locations(R.string.museums_The_Mevlevi_Tekke_Museum, R.string.museums_location_The_Mevlevi_Tekke_Museum));

        LocationsAdapter adapter = new LocationsAdapter(getActivity(), locations, R.color.category_attractions);
        ListView listView = (ListView) rootView.findViewById(R.id.List);
        listView.setAdapter(adapter);
        return rootView;
    }
}
