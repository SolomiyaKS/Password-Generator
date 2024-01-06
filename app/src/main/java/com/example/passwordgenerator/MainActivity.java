package com.example.passwordgenerator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {

    public TextView zag, zgenpass, setting, a_z, A_Z, nd, symb, znach;
    public Button generatepassword, copy;
    public Switch switch1, switch2, switch3, switch4;

    public SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textview
        zag = findViewById(R.id.zag);
        zgenpass = findViewById(R.id.zgenpass);
        setting = findViewById(R.id.setting);
        a_z = findViewById(R.id.a_z);
        A_Z = findViewById(R.id.A_Z);
        nd = findViewById(R.id.nd);
        znach = findViewById(R.id.znach);

        //button
        generatepassword = findViewById(R.id.generatepassword);
        copy = findViewById(R.id.copy);

        //switch
        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        switch3 = findViewById(R.id.switch3);
        switch4 = findViewById(R.id.switch4);

        //seekbar
        seekBar = findViewById(R.id.seekBar);
        seekBar.setProgress(12);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                znach.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        generatepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                generatePassword();
            }
        });
    }

    private void generatePassword() {
        int length = seekBar.getProgress(); //отримання значення довжиин пароля з повзунка
        String charset = ""; // charset міститиме всі можливі значення для генерації паролю

        if (!switch1.isChecked() && !switch2.isChecked() && !switch3.isChecked() && !switch4.isChecked()) {
            Toast.makeText(this, "Please select at least one character category", Toast.LENGTH_SHORT).show();
            return; // Виходимо з методу, щоб не генерувати пароль
        }

        if (switch1.isChecked()) {
            charset += "abcdefghijklmnopqrstuvwxyz";

        }
        if (switch2.isChecked()) {
            charset += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        }
        if (switch3.isChecked()) {
            charset += "0123456789";

        }
        if (switch4.isChecked()) {
            charset += "#$%&*^_?!";

        }

        StringBuilder radok = new StringBuilder(); //ініціалізація stringbuilder для збереження зген. пар.
        SecureRandom random = new SecureRandom(); // creation a random for generate a random number

        // Генерація пароля шляхом вибору випадкового символу з charset
        for (int i = 0; i < length; i++) {
            radok.append(charset.charAt(random.nextInt(charset.length())));
        }
        zgenpass.setText(radok.toString());

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clipData = ClipData.newPlainText("Generate Password", radok.toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(MainActivity.this, "Password copied", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

















