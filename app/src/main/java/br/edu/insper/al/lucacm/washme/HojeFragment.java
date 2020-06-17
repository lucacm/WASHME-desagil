package br.edu.insper.al.lucacm.washme;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HojeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HojeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String Client;

    public HojeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HojeFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static HojeFragment newInstance(String param1, String param2) {
//        HojeFragment fragment = new HojeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//}
    public static HojeFragment newInstance(String Client) {

        HojeFragment fragment = new HojeFragment();
        Bundle args = new Bundle();
        args.putString("client", Client);
        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View View = inflater.inflate(R.layout.fragment_hoje, container, false);


        ListView ListView = (ListView) View.findViewById(R.id.listview);
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            String Client = bundle.getString("todayclient");
            Integer pos = bundle.getInt("todaypos");

            if ((Client != null) && (pos != null)) {

                ArrayAdapter<String> ListViewAdapter = new ArrayAdapter<String>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        Collections.singletonList(Client)) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        // Get the Item from ListView
                        View view = super.getView(position, convertView, parent);

                        // Initialize a TextView for ListView each Item
                        TextView tv = (TextView) view.findViewById(android.R.id.text1);

                        // Set the text color of TextView (ListView Item)
                        tv.setTextColor(Color.WHITE);

                        // Generate ListView Item using TextView
                        return view;
                    }
                };
                ListView.setAdapter(ListViewAdapter);
                ListView.setOnItemClickListener((parent, view, position, id) -> {
                    Intent intent = new Intent(getActivity(), HojeActivity.class);
                    String client = (ListView.getItemAtPosition(position).toString());


                    intent.putExtra("client", client);
                    intent.putExtra("position", pos);
                    startActivity(intent);

                });


                // Inflate the layout for this fragment


            }
        }
        return View;
    }

}

