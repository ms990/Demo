package com.project.fincroptask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText email_etv,mobile_number_etv;
    private Button submit_btn;
    private ArrayList<ItemModel> itemModels;

    private ItemsAdapter itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        localData();

        Set<ItemModel> set = new HashSet<ItemModel>(itemModels);
        itemModels.clear();
        itemModels.addAll(set);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        itemsAdapter = new ItemsAdapter(MainActivity.this,itemModels);
        recyclerView.setAdapter(itemsAdapter);
        itemsAdapter.notifyDataSetChanged();



        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email_value = email_etv.getText().toString();
                String number_value = mobile_number_etv.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (!email_value.isEmpty() && !number_value.isEmpty()){
                    if (email_value.matches(emailPattern)){
                        if (number_value.length() < 10){
                            mobile_number_etv.setError("fill");
                            SpannableStringBuilder builder = new SpannableStringBuilder();
                            builder.append("Please Enter Valid Number").append(" ");

                            Snackbar.make(view, builder, Snackbar.LENGTH_SHORT).show();
                        }else {
                            itemModels.add(new ItemModel(email_etv.getText().toString(), mobile_number_etv.getText().toString()));

                            itemsAdapter.notifyItemInserted(itemModels.size());
                            saveData();
                        }

                    }else {
                        email_etv.setError("fill");
                        SpannableStringBuilder builder = new SpannableStringBuilder();
                        builder.append("Please Enter Valid Mail Id").append(" ");

                        Snackbar.make(view, builder, Snackbar.LENGTH_SHORT).show();
                    }


                }else if(email_value.isEmpty()){
                    email_etv.setError("fill");
                    SpannableStringBuilder builder = new SpannableStringBuilder();
                    builder.append("Please Enter Mail Id").append(" ");

                    Snackbar.make(view, builder, Snackbar.LENGTH_SHORT).show();
                }
                else if (number_value.length() <10){

                    mobile_number_etv.setError("fill");
                    SpannableStringBuilder builder = new SpannableStringBuilder();
                    builder.append("Please Enter Valid Number").append(" ");

                    Snackbar.make(view, builder, Snackbar.LENGTH_SHORT).show();

                }else if (number_value.isEmpty()){
                    mobile_number_etv.setError("fill");
                    SpannableStringBuilder builder = new SpannableStringBuilder();
                    builder.append("Please Enter Number").append(" ");

                    Snackbar.make(view, builder, Snackbar.LENGTH_SHORT).show();
                }


            }
        });

    }



    private void localData() {


        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        Gson gson = new Gson();


        String json = sharedPreferences.getString("items", null);

        Type type = new TypeToken<ArrayList<ItemModel>>() {}.getType();


        itemModels = gson.fromJson(json, type);


        if (itemModels == null) {

            itemModels = new ArrayList<>();
        }
    }

    private void saveData() {



        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);


        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();

        String json = gson.toJson(itemModels);


        editor.putString("items", json);


        editor.apply();

    }

    private void initViews() {

        email_etv = findViewById(R.id.email_etv);
        mobile_number_etv = findViewById(R.id.number_etv);
        submit_btn = findViewById(R.id.submit_btn);
        recyclerView = findViewById(R.id.recyclerview);


    }
}
