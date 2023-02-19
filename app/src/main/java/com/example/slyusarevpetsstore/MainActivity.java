package com.example.slyusarevpetsstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private TextView mTextView;
    private EditText mEditText;
    private ImageView mImageView;
    private Button btnAdd;

    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mProgressBar = findViewById(R.id.progressBar);
        mTextView = findViewById(R.id.textView);
        mEditText = findViewById(R.id.etVvodId);
        mImageView = findViewById(R.id.imageView);
        btnAdd = findViewById(R.id.btnAdd);
        btnSearch = findViewById(R.id.btnSearch);

        mProgressBar.setVisibility(View.INVISIBLE);

        PetAPI petAPI = PetAPI.retrofit.create(PetAPI.class);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });


        btnSearch.setOnClickListener(v -> {

            mProgressBar.setVisibility(View.VISIBLE);

            Call<Pets> Call = petAPI.getPets(mEditText.getText().toString());

            Call.enqueue(new Callback<Pets>() {
                @Override
                public void onResponse(Call<Pets> call, Response<Pets> response) {
                    if (response.isSuccessful()) {
                        Pets pet = response.body();
                        mProgressBar.setVisibility(View.INVISIBLE);
                        Picasso.with(MainActivity.this)
                                .load(pet.getPhotoUrls().get(0))
                                .resize(600, 600)
                                .into(mImageView);
                    } else {
                        ResponseBody errorBody = response.errorBody();
                        try {
                            Toast.makeText(MainActivity.this, errorBody.string(),
                                    Toast.LENGTH_SHORT).show();
                            mProgressBar.setVisibility(View.INVISIBLE);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Pets> call, Throwable throwable) {
                    Toast.makeText(MainActivity.this, "Что-то пошло не так " + throwable.getMessage(),
                            Toast.LENGTH_SHORT).show();
                    mProgressBar.setVisibility(View.INVISIBLE);
                }
            });
        });

    }


}