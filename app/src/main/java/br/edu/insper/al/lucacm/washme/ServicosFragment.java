package br.edu.insper.al.lucacm.washme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.AdapterView.OnItemClickListener;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServicosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList mClients = new ArrayList<>();
    private DatabaseReference mDatabase;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ServicosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServicosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicosFragment newInstance(String param1, String param2) {
        ServicosFragment fragment = new ServicosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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

        View View = inflater.inflate(R.layout.fragment_servicos, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference("1Fjw9VSKV6P0esNSHQYaNvMG04O4ien29y-GpBxH0bJg/Página1/");



        String[] data = {"06/10/2020",
                        "06/09/2020",
                        "06/09/2020",
                        "06/10/2020"};

        List<String> dataList = Arrays.asList(data);

        ListView ListView = (ListView) View.findViewById(R.id.listview);

        ArrayAdapter<String> ListViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mClients
        ){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
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
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    String value = ds.child("nomeCompleto").getValue(String.class);

                    mClients.add(value);
                }


                ListViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();

            }
        });


        ListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getActivity(), ServicosActivity.class);
            String client =(ListView.getItemAtPosition(position).toString());
            String dia = (dataList.get(position));
            Integer pos = (position + 1);

            intent.putExtra("client", client);
            intent.putExtra("data",dia);
            intent.putExtra("position", pos);
            startActivityForResult(intent,1001);
        });


        // Inflate the layout for this fragment
        return View;
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1001 && resultCode == Activity.RESULT_OK && data != null) {
            String myData = data.getStringExtra("client");

        }
    }

}
