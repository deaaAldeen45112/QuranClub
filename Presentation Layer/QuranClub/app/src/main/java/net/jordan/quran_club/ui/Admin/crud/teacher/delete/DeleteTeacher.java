package net.jordan.quran_club.ui.Admin.crud.teacher.delete;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.userlogin.ResponseUserlogin;
import net.jordan.quran_club.model.userlogin.UserLogin;
import net.jordan.quran_club.resourses.Constant;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DeleteTeacher extends Fragment {

    private DeleteTeacherViewModel mViewModel;
    private ArrayList<UserLogin>teachers;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static DeleteTeacher newInstance() {
        return new DeleteTeacher();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(DeleteTeacherViewModel.class);

        return inflater.inflate(R.layout.fragment_delete_teacher, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Spinner spinner = view.findViewById(R.id.spinner);
        Button   delete=view.findViewById(R.id.delete);
       ArrayAdapter arrayAdapter=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);
       ArrayList<String>nameOfTeachers=new ArrayList<>();

        mViewModel.getTeachersLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseUserlogin>() {
            @Override
            public void onChanged(ResponseUserlogin responseUserlogin) {
                arrayAdapter.clear();
                teachers= (ArrayList<UserLogin>) responseUserlogin.getData();

                for(UserLogin userLogin:teachers){

                    nameOfTeachers.add(userLogin.getFullName());

                }

                arrayAdapter.addAll(nameOfTeachers);
                spinner.setAdapter(arrayAdapter);

            }
        });
        mViewModel.getTeachers("Teacher");
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int idUserlogin=teachers.get(spinner.getSelectedItemPosition()).getUserloginId();
                mViewModel.deleteTeacher(idUserlogin);



            }
        });
        mViewModel.getDeleteTeacherMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                Toast.makeText(getContext(), Constant.detectStatus(status.getStatus()), Toast.LENGTH_SHORT).show();
                mViewModel.getTeachers("Teacher");
            }
        });


    }


}