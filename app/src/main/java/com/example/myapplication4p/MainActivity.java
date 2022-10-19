package com.example.myapplication4p;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText numberEditText;
    ImageView wybranyImageView;
    ArrayList<Integer> obrazki = new ArrayList<>();
    int indeks = 0 ;
    Button wsteczButton;
    Button dalejButton;
    Spinner numerSpinner;
    Spinner numerSpinnerPelny;
    ArrayList<Integer> wartosci = new ArrayList<>();

    private void wypelnijObrazkami(){
        obrazki.add(R.drawable.rys1);
        obrazki.add(R.drawable.rys2);
        obrazki.add(R.drawable.rys3);
        obrazki.add(R.drawable.rys4);
    }
    private void wypelnijWartosci(){
        for(int i=1;i<=obrazki.size();i++){
            wartosci.add(i);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberEditText = findViewById(R.id.editTextNumber);
        wybranyImageView = findViewById(R.id.imageView);
        wypelnijObrazkami();
        wypelnijWartosci();
        wsteczButton =findViewById(R.id.button);
        dalejButton = findViewById(R.id.button2);
        numerSpinner = findViewById(R.id.spinner);
        numerSpinnerPelny = findViewById(R.id.spinner2);
        ArrayAdapter<Integer> adapterSpinnera = new ArrayAdapter<Integer>(
                MainActivity.this,
                android.R.layout.simple_spinner_item,
                wartosci
        );
        numerSpinnerPelny.setAdapter(adapterSpinnera);
        numberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /*Toast.makeText(MainActivity.this,
                        charSequence,
                        Toast.LENGTH_SHORT)
                        .show();

                 */
                if(!charSequence.toString().isEmpty()) {
                    indeks = Integer.valueOf(charSequence.toString());
                    if (indeks < 0 || indeks >= obrazki.size())
                        indeks = 1;
                    wybranyImageView.setImageResource(obrazki.get(indeks));
                }
                }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        wsteczButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indeks--;
                if(indeks<0){
                    indeks = obrazki.size()-1;
                }
                wybranyImageView.setImageResource(obrazki.get(indeks));

            }
        });
        dalejButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indeks++;
                if(indeks>obrazki.size()-1){
                    indeks = 0;
                }
                wybranyImageView.setImageResource(obrazki.get(indeks));

            }
        });
        numerSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView,
                                               View view, int i, long l) {
                        String wybranyIndeksS = adapterView
                                        .getItemAtPosition(i)
                                        .toString();

                        Toast.makeText(MainActivity.this,
                                wybranyIndeksS,
                                Toast.LENGTH_SHORT)
                                .show();
                        wybranyImageView.setImageResource(obrazki.get(i));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );
    }
}