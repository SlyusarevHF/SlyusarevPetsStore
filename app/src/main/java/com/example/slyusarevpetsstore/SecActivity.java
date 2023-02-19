package com.example.slyusarevpetsstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private EditText mEditTextID;
    private EditText mEditTextName;
    private EditText mEditTextPhoto;
    private Button btnWrite;
    private Button btnSear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sozdanie);

        mProgressBar = findViewById(R.id.Progressbar);
        mEditTextID = findViewById(R.id.etId);
        mEditTextName = findViewById(R.id.etName);
        mEditTextPhoto = findViewById(R.id.etPhoto);

        btnWrite = findViewById(R.id.btnWrite);
        btnSear = findViewById(R.id.btnSear);

        mProgressBar.setVisibility(View.INVISIBLE);

        PetAPI petAPI = PetAPI.retrofit.create(PetAPI.class);

        btnSear.setOnClickListener(v -> {
            Intent intent = new Intent(SecActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        btnWrite.setOnClickListener(v -> {
            mProgressBar.setVisibility(View.VISIBLE);

            String IdInput = mEditTextID.getText().toString();
            String Name = mEditTextName.getText().toString();
            String photoUrl = mEditTextPhoto.getText().toString();
            List<String> PhotoUrls = new ArrayList<>();
            PhotoUrls.add(photoUrl);
            int ID = Integer.parseInt(IdInput);
//            Pet newPet = new Pet(ID, Name, PhotoUrls);
            Pets newPet = new Pets();
            newPet.setId(ID);
            newPet.setName(Name);
            newPet.setPhotoUrls(PhotoUrls);
            Call<Pets> Call = petAPI.createPets(newPet);

            Call.enqueue(new Callback<Pets>() {
                @Override
                public void onResponse(Call<Pets> call, Response<Pets> response) {
                    if(response.isSuccessful()) {
                        Pets createdPet = response.body();
                        mProgressBar.setVisibility(View.INVISIBLE);
                    } else {
                        ResponseBody errorBody = response.errorBody();
                        try {
                            Toast.makeText(SecActivity.this, errorBody.string(),
                                    Toast.LENGTH_SHORT).show();
                            mProgressBar.setVisibility(View.INVISIBLE);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<Pets> call, Throwable throwable) {
                    Toast.makeText(SecActivity.this, "Что-то пошло не так " + throwable.getMessage(),
                            Toast.LENGTH_SHORT).show();
                    mProgressBar.setVisibility(View.INVISIBLE);
                }
            });
        });
    }
}
