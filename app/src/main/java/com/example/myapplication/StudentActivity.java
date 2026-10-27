package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class StudentActivity extends AppCompatActivity {

    private EditText etStudentId, etStudentName, etEmail, etPhone;
    private Button btnAddStudent;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        etStudentId = findViewById(R.id.etStudentId);
        etStudentName = findViewById(R.id.etStudentName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        btnAddStudent = findViewById(R.id.btnAddStudent);

        db = FirebaseFirestore.getInstance();

        btnAddStudent.setOnClickListener(v -> {

            String id = etStudentId.getText().toString().trim();
            String name = etStudentName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            StudentModel student = new StudentModel(id, name, email, phone);

            db.collection("Students").document(id)
                    .set(student)
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(this, "Student added!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

    }
}
