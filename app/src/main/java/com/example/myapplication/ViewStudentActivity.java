package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class ViewStudentActivity extends AppCompatActivity {
    ListView listStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listStudent = findViewById(R.id.listStudent);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // get all documents from Students collection, create a List<>
        db.collection("Students").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<StudentModel> stdList = new ArrayList<>();
                if (task.isSuccessful()) {
                    // loop the document from task.getResult(), convert data into StudentModel object
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        StudentModel student = document.toObject(StudentModel.class);
                        stdList.add(student);
                    }
                    // connect the student list to adapter
                    StudentAdapter stdAdapter = new StudentAdapter(getApplicationContext(), stdList);
                    listStudent.setAdapter(stdAdapter);
                } else {
                    Log.d("Student Collections", "Error getting documents: ", task.getException());
                }
            }
        });

        listStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // get the selected item as StudentModel, put extras in Intent and start activity
                StudentModel selectedItem = (StudentModel) parent.getItemAtPosition(position);
                Intent studentIntent = new Intent(getApplicationContext(), StudentActivity.class);
                studentIntent.putExtra("studentId", selectedItem.getStudentId());
                studentIntent.putExtra("studentName", selectedItem.getStudentName());
                startActivity(studentIntent);
                finish();
            }
        });
    }
}
