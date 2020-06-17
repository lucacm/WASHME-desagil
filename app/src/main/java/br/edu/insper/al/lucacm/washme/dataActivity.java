package br.edu.insper.al.lucacm.washme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dataActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Bundle bundle = getIntent().getExtras();

        TextView clientView = findViewById(R.id.client);
        TextView dataView = findViewById(R.id.data);
        TextView serviceView = findViewById(R.id.servicetype);
        TextView carView = findViewById(R.id.carro);
        TextView placaView = findViewById(R.id.placa);
        TextView bancoView = findViewById(R.id.banco);
        TextView addressView = findViewById(R.id.endereco);
        TextView compView = findViewById(R.id.complemento);
        TextView cobertoView = findViewById(R.id.coberto);
        TextView tomadaView = findViewById(R.id.tomada);
        TextView periodoView = findViewById(R.id.periodo);
        TextView obsView = findViewById(R.id.observacoes);

        assert bundle != null;
        String client = bundle.getString("dclient");
        Integer pos = bundle.getInt("dpos");

        clientView.setText(client);

        mDatabase = FirebaseDatabase.getInstance().getReference("1Fjw9VSKV6P0esNSHQYaNvMG04O4ien29y-GpBxH0bJg/Página1/"+pos.toString());

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String servicetype = dataSnapshot.child("serviço").getValue(String.class);
                String address = dataSnapshot.child("address").getValue(String.class);
                String cel = dataSnapshot.child("celular").getValue(String.class);
                String coberto = dataSnapshot.child("coberto").getValue(String.class);
                String complemento = dataSnapshot.child("complemento").getValue(String.class);
                String placa = dataSnapshot.child("placa").getValue(String.class);
                String banco = dataSnapshot.child("bancoCarro").getValue(String.class);
                String tomada = dataSnapshot.child("tomada").getValue(String.class);
                String periodo = dataSnapshot.child("periodo").getValue(String.class);
                String obs = dataSnapshot.child("detalhes").getValue(String.class);
                String carmodel = dataSnapshot.child("modeloCarro").getValue(String.class);
                String data = dataSnapshot.child("dataAgendamento").getValue(String.class);

                serviceView.setText(servicetype);
                addressView.setText(address);
                carView.setText(carmodel);
                placaView.setText(placa);
                bancoView.setText(banco);
                addressView.setText(address);
                compView.setText(complemento);
                cobertoView.setText(coberto);
                tomadaView.setText(tomada);
                periodoView.setText(periodo);
                obsView.setText(obs);
                dataView.setText(data);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();

            }
        });


    }
    }

