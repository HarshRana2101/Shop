package com.example.iteration1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class AddNewProduct extends AppCompatActivity implements View.OnClickListener {
    private Button AddProductbtn;
    private ImageView InputProductImage;
    private EditText InputProductName, InputProductDesc, InputProductPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        InputProductName = findViewById(R.id.product_name);
        InputProductDesc = findViewById(R.id.product_description);
        InputProductPrice = findViewById(R.id.product_price);

        AddProductbtn = findViewById(R.id.add_new_product);
        InputProductImage = findViewById(R.id.uploadImage);

        AddProductbtn.setOnClickListener(this);

        InputProductImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.add_new_product:
                sendData();
                break;
        }
    }

    public void sendData() {
        String name = InputProductName.getText().toString();
        String desc = InputProductDesc.getText().toString();
        String price = InputProductPrice.getText().toString();

        HashMap<String, String> NewProduct = new HashMap<>();
        NewProduct.put("Product Name", name);
        NewProduct.put("Description", desc);
        NewProduct.put("product price", price);

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(date).add(NewProduct).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Intent i = new Intent(AddNewProduct.this, DisplayProduct.class);
                getApplicationContext().startActivity(i);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddNewProduct.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }
}
