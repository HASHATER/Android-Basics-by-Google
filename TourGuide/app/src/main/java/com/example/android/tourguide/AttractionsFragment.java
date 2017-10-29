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
public class AttractionsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_page, container, false);

        final ArrayList<Locations> locations = new ArrayList<Locations>();

        locations.add(new Locations(R.string.attraction_Kyrenia_Harbour, R.string.attraction_location_Kyrenia_Harbour, R.drawable.kyrenia_harbour));
        locations.add(new Locations(R.string.attraction_Kyrenia_Castle, R.string.attraction_location_Kyrenia_Harbour, R.drawable.kyrenia_castle));
        locations.add(new Locations(R.string.attraction_Alagadi_Turtle_Beach, R.string.attraction_location_Turtle_Beach, R.drawable.turtle_beach));
        locations.add(new Locations(R.string.attraction_Bellapais_Monastery, R.string.attraction_location_Bellapais_Monastery, R.drawable.bellapais_abbey));

        LocationsAdapter adapter = new LocationsAdapter(getActivity(), locations, R.color.category_attractions);

        ListView listView = (ListView) rootView.findViewById(R.id.List);

        listView.setAdapter(adapter);
        return rootView;


    }
}
