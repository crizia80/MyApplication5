package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<StudentModel> {

    private Context context;
    private List<StudentModel> studentList;

    public StudentAdapter(Context context, List<StudentModel> studentList) {
        super(context, 0, studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        TextView tvStudentId = convertView.findViewById(R.id.tvStudentId);
        TextView tvStudentName = convertView.findViewById(R.id.tvStudentName);
        TextView tvEmail = convertView.findViewById(R.id.tvEmail);
        TextView tvPhone = convertView.findViewById(R.id.tvPhone);

        StudentModel student = studentList.get(position);

        tvStudentId.setText("ID: " + student.getStudentId());
        tvStudentName.setText("Name: " + student.getStudentName());
        tvEmail.setText("Email: " + student.getEmail());
        tvPhone.setText("Phone: " + student.getPhone());

        return convertView;
    }
}
