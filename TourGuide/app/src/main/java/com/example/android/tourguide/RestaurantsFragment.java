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

public class RestaurantsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_page, container, false);
        final ArrayList<Locations> locations = new ArrayList<Locations>();

        locations.add(new Locations(R.string.restaurants_Burgys, R.string.restaurants_location_Burgys));
        locations.add(new Locations(R.string.restaurants_D_and_B_Caf, R.string.restaurants_location_D_and_B_Caf));
        locations.add(new Locations(R.string.restaurants_GUIDOS, R.string.restaurants_location_GUIDOS));
        locations.add(new Locations(R.string.restaurants_Virage_Take_Away, R.string.restaurants_location_Virage_Take_Away));

        LocationsAdapter adapter = new LocationsAdapter(getActivity(), locations, R.color.category_attractions);
        ListView listView = (ListView) rootView.findViewById(R.id.List);
        listView.setAdapter(adapter);
        return rootView;
    }
}
