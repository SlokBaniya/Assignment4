package com.assignment4;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class DescriptionActivity extends AppCompatActivity {
    private CircleImageView circleImage;
    TextView tvItemName, tvItemPrice, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        tvItemName = findViewById(R.id.tvItemName);
        tvItemPrice = findViewById(R.id.tvItemPrice);
        tvDescription = findViewById(R.id.tvDescription);
        circleImage = findViewById(R.id.circleImage);

        StrictMode();
        URL url = null;

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            circleImage.setImageResource(bundle.getInt("image"));

            try {
                url = new URL("http://10.0.2.2:3000/uploads/" + bundle.getString("image"));
                circleImage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            tvItemName.setText(bundle.getString("name"));
            tvItemPrice.setText(bundle.getString("price"));
            tvDescription.setText(bundle.getString("description"));
        }
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
