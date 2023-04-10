package com.example.list_view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
     EditText nameEditText;
     EditText phoneEditText;
     Button addButton;
     Button updateButton;
     Button deleteButton;
     Button searchButton;
     ListView listView;
     ArrayAdapter<String> adapter;
     ArrayList<String> userList;
     Map<String, String> userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        addButton = findViewById(R.id.addButton);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        searchButton = findViewById(R.id.searchButton);
        listView = findViewById(R.id.listView);

        userList = new ArrayList<>();
        userData = new HashMap<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                if (!name.isEmpty() && !phone.isEmpty()) {
                    if (userData.containsKey(name)) {
                        Toast.makeText(MainActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                    } else {
                        userList.add(name);
                        userData.put(name, phone);
                        adapter.notifyDataSetChanged();
                        clearEditText();
                        Toast.makeText(MainActivity.this, "User added successfully!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter name and phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                if (!name.isEmpty() && !phone.isEmpty()) {
                    if (userData.containsKey(name)) {
                        userList.remove(name);
                        userList.add(name);
                        userData.put(name, phone);
                        adapter.notifyDataSetChanged();
                        clearEditText();
                        Toast.makeText(MainActivity.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "User not found!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter name and phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                if (!name.isEmpty()) {
                    if (userData.containsKey(name)) {
                        userList.remove(name);
                        userData.remove(name);
                        adapter.notifyDataSetChanged();
                        clearEditText();
                        Toast.makeText(MainActivity.this, "User deleted successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "User not found!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                if (!name.isEmpty()) {
                    if (userData.containsKey(name)) {
                        String phone = userData.get(name);
                        phoneEditText.setText(phone);
                        Toast.makeText(MainActivity.this, "User found!", Toast.LENGTH_SHORT).show();
                    } else {
                        phoneEditText.setText("");
                        Toast.makeText(MainActivity.this, "User not found!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = userList.get(position);
                String phone = userData.get(name);
                nameEditText.setText(name);
                phoneEditText.setText(phone);
            }
        });
    }

    private void clearEditText() {
        nameEditText.setText("");
        phoneEditText.setText("");
    }
}

