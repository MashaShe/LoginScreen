package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText loginText = findViewById(R.id.editTextLogin);
        final EditText passText = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.button_login);
        Button registButton = findViewById(R.id.button_registration);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = loginText.getText().toString() + " credentials";
                try {
                    FileInputStream fileInputStream = openFileInput(filename);
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    if (reader.readLine().equals(passText.getText().toString())){
                        Toast.makeText(MainActivity.this, "Login and password are correct", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Login is incorrect", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (loginText.getText().toString().equals(null) || passText.getText().toString().equals(null)) {
                    Toast.makeText(MainActivity.this, "Please enter login and password", Toast.LENGTH_SHORT).show();
                    // в будущем можно ограничить истопльзование символов и проверять на их наличие
                }
                else {
                    String credentials = passText.getText().toString();
                    String filename = loginText.getText().toString() + " credentials";
                    try {
                        FileOutputStream fileOutputStream = openFileOutput(filename, MODE_PRIVATE);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                        BufferedWriter bw = new BufferedWriter(outputStreamWriter);
                        bw.write(credentials);
                        bw.close();
                        Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        passText.setText(null);
                        loginText.setText(null);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


            }
        });
    }
}