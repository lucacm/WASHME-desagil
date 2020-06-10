package br.edu.insper.al.lucacm.washme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ServicosActivity extends AppCompatActivity{

    Date date = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos);

        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY,0);
        date.set(Calendar.MINUTE,0);
        date.set(Calendar.SECOND,0);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");


        String today = formatter.format(date.getTime());

        Bundle bundle = getIntent().getExtras();


        TextView clientView = findViewById(R.id.client);
        Button   aceitarButton = findViewById(R.id.aceitar);
        TextView dataView = findViewById(R.id.data);

        assert bundle != null;
        String client = bundle.getString("client");
        String data = bundle.getString("data");

        clientView.setText(client);
        if(data.equals(today)){
            dataView.setText("Hoje");
        } else {
            dataView.setText(data);
        }
        aceitarButton.setOnClickListener((view) ->{

            if(data.equals(today)) {

//                HojeFragment Fragment = HojeFragment.newInstance(client);


                Intent intent = new Intent(ServicosActivity.this, MainActivity.class);
                intent.putExtra("todayclient",client);

                startActivity(intent);

//               HojeFragment2.setArguments(clientBundle);
//               fragmentManager.beginTransaction().replace(R.id.hojeFragment,HojeFragment2).addToBackStack(HojeFragment.class.getSimpleName()).commit();
//                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ServicosActivity.this);
//                SharedPreferences.Editor edit = settings.edit();
//                edit.putString("client",client);


            }





        });


    }
}
