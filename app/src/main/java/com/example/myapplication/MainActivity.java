package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd, btnView;
    private EditText txtStudentId, txtStudentName, txtEmail, txtPhone;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 🔹 Connect UI to Java
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        txtStudentId = findViewById(R.id.txtStudentId);
        txtStudentName = findViewById(R.id.txtStudentName);
        txtEmail = findViewById(R.id.etEmail);
        txtPhone = findViewById(R.id.etPhone);

        db = FirebaseFirestore.getInstance();

        btnAdd.setOnClickListener(v -> {
            String studentId = txtStudentId.getText().toString();
            String studentName = txtStudentName.getText().toString();
            String email = txtEmail.getText().toString();
            String phone = txtPhone.getText().toString();

            if (TextUtils.isEmpty(studentId) || TextUtils.isEmpty(studentName)) {
                Toast.makeText(MainActivity.this, "Student ID and Name are required", Toast.LENGTH_SHORT).show();
            } else {
                addStudent(studentId, studentName, email, phone);

                txtStudentId.setText("");
                txtStudentName.setText("");
                txtEmail.setText("");
                txtPhone.setText("");
            }
        });

        btnView.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ViewStudentActivity.class));
        });
    }

    private void addStudent(String studentId, String studentName, String email, String phone) {
        StudentModel student = new StudentModel(studentId, studentName, email, phone);

        db.collection("Students")
                .document(studentId)
                .set(student)
                .addOnSuccessListener(aVoid ->
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show()
                )
                .addOnFailureListener(e ->
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show()
                );
    }
}
