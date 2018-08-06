package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    TextView textViewCategory;
    TextView textViewSubCategory;
    Spinner spn1;
    Spinner spn2;
    Button buttonGo;

    ArrayList<String> alSubCategory;
    ArrayAdapter<String> aaSubCategory;
    int catposition;
    int subcatposition;

    String[][] sites = {
            {
                    "https://www.rp.edu.sg/",
                    "https://www.rp.edu.sg/student-life"
            },
            {
                    "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                    "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12"
            }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCategory = findViewById(R.id.textViewCat);
        textViewSubCategory = findViewById(R.id.textViewSub);
        spn1 = findViewById(R.id.spinner1);
        spn2 = findViewById(R.id.spinner2);
        buttonGo = findViewById(R.id.buttonGo);



        alSubCategory = new ArrayList<>();
//        String[] strSub = getResources().getStringArray(R.array.rp);
//        alSubCategory.addAll(Arrays.asList(strSub));
        aaSubCategory = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,alSubCategory);
        spn2.setAdapter(aaSubCategory);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        catposition = prefs.getInt("catposition",0);
        subcatposition = prefs.getInt("subcatposition",0);
        spn1.setSelection(catposition);
        spn2.setSelection(subcatposition);


        int pos = spn1.getSelectedItemPosition();
        alSubCategory.clear();
        if(pos == 0){
            String[] strNumbers = getResources().getStringArray(R.array.rp);
            alSubCategory.addAll(Arrays.asList(strNumbers));
        }
        else{
            String[] strNumbers = getResources().getStringArray(R.array.soi);
            alSubCategory.addAll(Arrays.asList(strNumbers));
        }
//        aaSubCategory.notifyDataSetChanged();

        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putInt("catposition",pos);
                prefEdit.commit();

                alSubCategory.clear();
                if(pos ==0){
                    String[] strNumbers = getResources().getStringArray(R.array.rp);
                    alSubCategory.addAll(Arrays.asList(strNumbers));
                }
                else{
                    String[] strNumbers = getResources().getStringArray(R.array.soi);
                    alSubCategory.addAll(Arrays.asList(strNumbers));
                }
                aaSubCategory.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        spn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putInt("subcatposition",position);
                prefEdit.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String url = "";
//                if(spn1.getSelectedItemPosition() == 0){
//                    if(spn2.getSelectedItemPosition() ==0){
//                        url = "https://www.rp.edu.sg/";
//                    }
//                    else if(spn2.getSelectedItemPosition() ==1){
//                        url = "https://www.rp.edu.sg/student-life";
//                    }
//                }
//                else if(spn1.getSelectedItemPosition() == 1){
//                   if(spn2.getSelectedItemPosition() ==0){
//                       url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47";
//                   }
//                   else if(spn2.getSelectedItemPosition()==1){
//                       url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12";
//                   }
//                }
                String weburl = sites[spn1.getSelectedItemPosition()][spn2.getSelectedItemPosition()];

                Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
                intent.putExtra("url",weburl);
                startActivity(intent);

            }
        });


    }
}
