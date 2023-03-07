package net.jordan.quran_club.ui.Admin.crud.classes.delete;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.Status;
import net.jordan.quran_club.model.classes.Class;
import net.jordan.quran_club.model.classes.ResponseClases;
import net.jordan.quran_club.resourses.Constant;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DeleteClass extends Fragment {

    private DeleteClassViewModel mViewModel;
    private ArrayList<Class>classes;
    public static DeleteClass newInstance() {
        return new DeleteClass();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(DeleteClassViewModel.class);
        return inflater.inflate(R.layout.fragment_delete_class, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        Button delete =view.findViewById(R.id.delete);
        AppCompatSpinner classSpinner =view.findViewById(R.id.classSpinner);
        ArrayAdapter classArrayAdapter=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item);


          delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mViewModel.deleteClass(classes.get(classSpinner.getSelectedItemPosition()).getClassId());

            }
        });

          mViewModel.deleteClassLiveData().observe(getViewLifecycleOwner(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {

                Toast.makeText(getContext(), Constant.detectStatus(status.getStatus()), Toast.LENGTH_SHORT).show();
                mViewModel.getClasses();

            }
        });
          mViewModel.getClassesLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseClases>() {
            @Override
            public void onChanged(ResponseClases responseClases) {
                classArrayAdapter.clear();
                classes=(ArrayList<Class>) responseClases.getData();
                ArrayList<String>classesName=new ArrayList<>();
                for(Class clas:classes){

                    classesName.add(clas.getName());

                }

                classArrayAdapter.addAll(classesName);
                classSpinner.setAdapter(classArrayAdapter);



            }
        });

        mViewModel.getClasses();





    }
}