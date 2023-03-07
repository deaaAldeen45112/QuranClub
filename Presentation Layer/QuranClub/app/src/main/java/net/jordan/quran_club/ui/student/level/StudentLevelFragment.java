package net.jordan.quran_club.ui.student.level;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.jordan.quran_club.R;
import net.jordan.quran_club.adapter.ListNumberPart;
import net.jordan.quran_club.model.student.ResponseSavedPercentage;
import net.jordan.quran_club.model.student.SavedPercentage;
import net.jordan.quran_club.model.studentQuranPart.ResponseStudentJoinQuranPart;
import net.jordan.quran_club.model.studentQuranPart.StudentJoinQuranPart;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class StudentLevelFragment extends Fragment{

    private StudentLevelViewModel mViewModel;
    private ArrayList<String>memorizedPartsOfTheQuran=new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static StudentLevelFragment newInstance(int studentId) {
        StudentLevelFragment studentLevelFragment=new StudentLevelFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("student_id",studentId);
        studentLevelFragment.setArguments(bundle);
        return studentLevelFragment;
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(StudentLevelViewModel.class);

        return inflater.inflate(R.layout.fragment_student_show_level, container, false);
    }

    ArrayList<SavedPercentage>savedPercentages;
    ArrayList<StudentJoinQuranPart>studentQuranParts;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProgressBar progressBarLevel=view.findViewById(R.id.progress_bar_level);
        TextView textViewProgress=view.findViewById(R.id.text_view_progress);

        int studentId=getArguments().getInt("student_id");

        RecyclerView recyclerViewQuranPart = view.findViewById(R.id.recyclerview_quran_part);
        ListNumberPart listNumberPart = new ListNumberPart(getContext(), R.layout.list_view_part_number);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());

        recyclerViewQuranPart.setHasFixedSize(false);
        recyclerViewQuranPart.setLayoutManager(layoutManager);
        recyclerViewQuranPart.setAdapter(listNumberPart);


        mViewModel.getSavedPercentageForEachQuranPartByStudentId(studentId);
        mViewModel.getSavedPercentageForEachQuranPartByStudentIdLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseSavedPercentage>() {
            @Override
            public void onChanged(ResponseSavedPercentage responseSavedPercentage) {
                savedPercentages= (ArrayList<SavedPercentage>) responseSavedPercentage.getData();
                mViewModel.getStudentQuranPartsJoinQuranPart(studentId);
            }
        });
        mViewModel.getStudentQuranPartsJoinQuranPartLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseStudentJoinQuranPart>() {
            @Override
            public void onChanged(ResponseStudentJoinQuranPart responseStudentJoinQuranPart) {
                studentQuranParts= (ArrayList<StudentJoinQuranPart>) responseStudentJoinQuranPart.getData();
                for(StudentJoinQuranPart studentQuranPart:studentQuranParts)
                {memorizedPartsOfTheQuran.add(""+studentQuranPart.getQuranPartNumber());}

                int numberOfSavedQuranPart= CalculateTheNumberOfSavedQuranPart();
                int percentageOfSavedQuranPart=CalculatePercentageOfSavedQuranPart();
                textViewProgress.setText(""+numberOfSavedQuranPart);
                progressBarLevel.setProgress(percentageOfSavedQuranPart);
                listNumberPart.setNumberParts(memorizedPartsOfTheQuran);


            }
        });





    }


    int CalculateTheNumberOfSavedQuranPart(){
        int numberOfSavedQuranPart=studentQuranParts.size();
        for (int i = 0; i <savedPercentages.size() ; i++) {
            if (savedPercentages.get(i).getSavedPercentageOfVerses().equals("1.0000")){
                numberOfSavedQuranPart++;
                memorizedPartsOfTheQuran.add(""+savedPercentages.get(i).getQuranPartNumber());
            }
        }

        return numberOfSavedQuranPart;
    }

    int CalculatePercentageOfSavedQuranPart(){
        double maxValue=-1;
        for (int i = 0; i <savedPercentages.size() ; i++) {
            double currentValue=Double.parseDouble(savedPercentages.get(i).getSavedPercentageOfVerses());
           if(currentValue!=1){
            maxValue=Math.max(maxValue,currentValue);
           }
        }
        int percentageOfSavedQuranPart= (int) (maxValue*100);
        return percentageOfSavedQuranPart;
    }


}