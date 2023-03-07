package net.jordan.quran_club.adapter;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.quranPartSurah.QuranPartSurah;
import net.jordan.quran_club.model.quranPartSurah.QuranPartSurahJoinQuranPartJoinSurah;
import net.jordan.quran_club.model.studentLesson.StudentLesson;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.StudentLessonQuranPartSurah;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ListTeacherStudentLessonViewHolderAdapter extends RecyclerView.Adapter<ListTeacherStudentLessonViewHolderAdapter.UserLoginViewHolder> {
    private Context context;
    private int resource;
    private HashMap<Integer, ArrayOfQuranPartSurah> quranNumbers = new HashMap<>();
    private ArrayList<QuranPartSurahJoinQuranPartJoinSurah> quranPartSurahJoinQuranPartJoinSurahs=new ArrayList<>();
    private ArrayList<StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin> studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins=new ArrayList<>();
    private ArrayList<ArrayList<StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin>> saveStudentLesson=new ArrayList<>();
    private ArrayList<Integer>arrayListGroupFromSameStudentLessonId=new ArrayList<>();
    private ArrayList<Integer>arrayListStartIndex=new ArrayList<>();
    public void setStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogins(ArrayList<StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin> studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins) {
        this.studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.clear();
        this.studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.addAll(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins);
        arrayListStartIndex.clear();
        arrayListGroupFromSameStudentLessonId.clear();
        int count=1;
        arrayListStartIndex.add(0);
        for(int j=1;j<studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.size();j++){

            if(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(j-1).getStudentLessonId()
                    ==studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(j).getStudentLessonId())
            {
                count++;

            }
            else {
                arrayListGroupFromSameStudentLessonId.add(count);
                arrayListStartIndex.add(j);
                count=1;
            }


        }
        arrayListGroupFromSameStudentLessonId.add(count);
        this.notifyDataSetChanged();

        this.saveStudentLesson.clear();;
        for (int i = 0; i <getSizeDistinctStudentLessonQuranPartSurahJoinStudentLessonJoinUserLogins(); i++) {
            this.saveStudentLesson.add(new ArrayList<>());
        }
    }
    public void setQuranPartSurahJoinQuranPartJoinSurahs(ArrayList<QuranPartSurahJoinQuranPartJoinSurah> quranPartSurahJoinQuranPartJoinSurahs) {
        this.quranPartSurahJoinQuranPartJoinSurahs = quranPartSurahJoinQuranPartJoinSurahs;
        for (QuranPartSurahJoinQuranPartJoinSurah quranPartSurahJoinQuranPartJoinSurah : this.quranPartSurahJoinQuranPartJoinSurahs) {

            if (!this.quranNumbers.containsKey(quranPartSurahJoinQuranPartJoinSurah.getQuranPartNumber()))
                this.quranNumbers.put(quranPartSurahJoinQuranPartJoinSurah.getQuranPartNumber(), new ArrayOfQuranPartSurah());

          ArrayOfQuranPartSurah quranPartSurah = this.quranNumbers.get(quranPartSurahJoinQuranPartJoinSurah.getQuranPartNumber());

            quranPartSurah.getSurahNames().add(quranPartSurahJoinQuranPartJoinSurah.getSurahName());
            quranPartSurah.getFromVerses().add(quranPartSurahJoinQuranPartJoinSurah.getQuranPartSurahFrom());
            quranPartSurah.getToVerses().add(quranPartSurahJoinQuranPartJoinSurah.getQuranPartSurahTo());
        }
    }
    //*******size Distinct important in getCount****
    public int  getSizeDistinctStudentLessonQuranPartSurahJoinStudentLessonJoinUserLogins(){

        HashSet<Integer> set=new HashSet<>();
        for (int i = 0; i < studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.size(); i++) {
            set.add(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(i).getStudentLessonId());
        }

  return set.size();


    }
    public ListTeacherStudentLessonViewHolderAdapter(Context context, int resource, ArrayList<QuranPartSurahJoinQuranPartJoinSurah> quranPartSurahJoinQuranPartJoinSurahs) {
        this.context = context;
        this.resource = resource;
        this.quranPartSurahJoinQuranPartJoinSurahs = quranPartSurahJoinQuranPartJoinSurahs;

    }

    @NonNull
    @Override
    public UserLoginViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(resource,parent,false);
        UserLoginViewHolder userLoginViewHolder=new UserLoginViewHolder(view);
        return userLoginViewHolder;
    }
    int startIndex;
    @Override
    public void onBindViewHolder(@NonNull UserLoginViewHolder holder, int position) {


        startIndex=arrayListStartIndex.get(position);
        // *********************spinner*****************
        holder.textViewStudentName.setText(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getName());
        // insert new row
        // *******************setNote********************
        holder.myCustomEditTextListener.updatePosition(holder.getAdapterPosition());
        holder.editTextNote.setText(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getNote());
        //********************setStudentName*************
        final int pos=position;
        refillOldRow(pos,holder);
        holder.textViewIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d("TAG", "onClick: "+pos);
                StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin studentLessonQuranPartSurahJoinStudentLessonJoinUserlogin=new StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin();
                studentLessonQuranPartSurahJoinStudentLessonJoinUserlogin.setQuranPartSurahId(1);
                studentLessonQuranPartSurahJoinStudentLessonJoinUserlogin.setToVerses(1);
                studentLessonQuranPartSurahJoinStudentLessonJoinUserlogin.setFromVerses(1);
                saveStudentLesson.get(pos).add(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogin);
                refillNewRow(pos,holder);
                makeVisibleSpinners(0,arrayListGroupFromSameStudentLessonId.get(pos)+saveStudentLesson.get(pos).size(),holder);
                makeGoneSpinners(arrayListGroupFromSameStudentLessonId.get(pos)+saveStudentLesson.get(pos).size(),6,holder);

            }
        });
        holder.textViewDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int size=saveStudentLesson.get(pos).size()-1;
                if (size>0) {
                    saveStudentLesson.get(pos).remove(size);
                    refillNewRow(pos, holder);
                    makeVisibleSpinners(0, arrayListGroupFromSameStudentLessonId.get(pos) + saveStudentLesson.get(pos).size(), holder);
                    makeGoneSpinners(arrayListGroupFromSameStudentLessonId.get(pos) + saveStudentLesson.get(pos).size(), 6, holder);
                }
                else
                {
                    Toast.makeText(context, "size is 0", Toast.LENGTH_SHORT).show();
                }
            }
        });
        refillNewRow(pos,holder);
        makeVisibleSpinners(0,arrayListGroupFromSameStudentLessonId.get(position)+saveStudentLesson.get(position).size(),holder);
        makeGoneSpinners(arrayListGroupFromSameStudentLessonId.get(position)+saveStudentLesson.get(position).size(),6,holder);


    }

    private void refillOldRow(int pos, UserLoginViewHolder holder) {

       int startIndex=arrayListStartIndex.get(pos);
        for (int i=0;i<arrayListGroupFromSameStudentLessonId.get(pos);i++) {

            LinearLayoutContentView linearLayoutContentView=new LinearLayoutContentView();
            int quranPartSurahId=studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getQuranPartSurahId();
            studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getQuranPartSurahId();


            Spinner spinnerQuranPartNumber = holder.arraySpinnerQuranNumber[i];
            Spinner spinnerSurahName = holder.arraySpinnerSurahName[i];
            Spinner spinnerFromVerses = holder.arraySpinnerFromVerse[i];
            Spinner spinnerToVerses = holder.arraySpinnerToVerse[i];

            linearLayoutContentView.setSpinnerQuranPartNumber(spinnerQuranPartNumber);
            linearLayoutContentView.setSpinnerSurahName(spinnerSurahName);
            linearLayoutContentView.setSpinnerFromVerses(spinnerFromVerses);
            linearLayoutContentView.setSpinnerToVerses(spinnerToVerses);

            QuranPartSurahJoinQuranPartJoinSurah quranPartSurahJoinQuranPartJoinSurah=getQuranPartNumberFromArrayAndPutInSpinnerAndDetectSurahName(quranPartSurahId);
            int quranPartNumber=quranPartSurahJoinQuranPartJoinSurah.getQuranPartNumber();
            String surahName=quranPartSurahJoinQuranPartJoinSurah.getSurahName();

            int indexSurahName=getSurahIndexFromSurahName(quranPartNumber,surahName);

            ArrayAdapter arrayAdapterSurahName;
            ArrayAdapter arrayAdapterQuranPartNumber;
            ArrayAdapter arrayAdapterVerseFrom;
            ArrayAdapter arrayAdapterVerseTo;

            arrayAdapterSurahName =holder.arrayAdaptersSurahName[i];
            arrayAdapterSurahName.clear();
            arrayAdapterQuranPartNumber =holder.arrayAdaptersQuranNumber[i];
            arrayAdapterQuranPartNumber.clear();
            arrayAdapterVerseFrom =holder.arrayAdaptersFromVerse[i];
            arrayAdapterVerseFrom.clear();
            arrayAdapterVerseTo =holder.arrayAdaptersToVerses[i];
            arrayAdapterVerseTo.clear();
            linearLayoutContentView.getSpinnerSurahName().setAdapter(arrayAdapterSurahName);
            linearLayoutContentView.getSpinnerFromVerses().setAdapter(arrayAdapterVerseFrom);
            linearLayoutContentView.getSpinnerToVerses().setAdapter(arrayAdapterVerseTo);
            arrayAdapterQuranPartNumber.addAll(quranNumbers.keySet());
            linearLayoutContentView.getSpinnerQuranPartNumber().setAdapter(arrayAdapterQuranPartNumber);
            //*********************GetQuranPartNumberFromArrayAndPutInSpinnerAndDetectSurahName******************************
            //************GetSurahIndexFromSurahName*********
            //*****************GetToAndFromFromStudentLessonJoin...**************
            int studentFromVerse =  studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getFromVerses();

            int studentToVerse =  studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getToVerses();

            //************final inside setOnItem**********
            final int[] finalIndexSurahName = {indexSurahName};
            final int[] finalFromVerse = {studentFromVerse - quranPartSurahJoinQuranPartJoinSurah.getQuranPartSurahFrom()};
            final int[] finalToVerse = {studentToVerse - quranPartSurahJoinQuranPartJoinSurah.getQuranPartSurahFrom()};
            linearLayoutContentView.getSpinnerQuranPartNumber().setSelection(quranPartNumber - 1);
            //*********************SetOnItemOnQuranPartNumberAndSelectSurahBySurahNames**************************
            linearLayoutContentView.getSpinnerQuranPartNumber().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    int quranPartNumber = position + 1;
                    arrayAdapterSurahName.clear();
                    arrayAdapterSurahName.addAll(quranNumbers.get(quranPartNumber).getSurahNames());
                    //*****swap between notif and setAdapter if you want setSelection in the same position ******
                    linearLayoutContentView.getSpinnerSurahName().setAdapter(arrayAdapterSurahName);
                    //*******setSelectionAfter*******
                    linearLayoutContentView.getSpinnerSurahName().setSelection(finalIndexSurahName[0]);
                    //*********if go to anthor quranpart and if numberofsurahName to anthor< from now  expection **
                    finalIndexSurahName[0] = 0;



                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            int finalStartIndex = startIndex;
            linearLayoutContentView.getSpinnerSurahName().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    List<Integer> orderOfVerses = new ArrayList<>();
                    int quranPartNumber = linearLayoutContentView.getSpinnerQuranPartNumber().getSelectedItemPosition() + 1;
                    int startFromVerse = quranNumbers.get(quranPartNumber).getFromVerses().get(position);
                    int finishToVerse = quranNumbers.get(quranPartNumber).getToVerses().get(position);

                    arrayAdapterVerseFrom.clear();
                    arrayAdapterVerseTo.clear();
                    orderOfVerses.clear();

                    for (int i = startFromVerse; i <= finishToVerse; i++)
                        orderOfVerses.add(i);

                    arrayAdapterVerseFrom.addAll(orderOfVerses);
                    arrayAdapterVerseTo.addAll(orderOfVerses);

                    arrayAdapterVerseFrom.notifyDataSetChanged();
                    arrayAdapterVerseTo.notifyDataSetChanged();

                    linearLayoutContentView.getSpinnerFromVerses().setSelection(finalFromVerse[0]);
                    linearLayoutContentView.getSpinnerToVerses().setSelection(finalToVerse[0]);
                    finalFromVerse[0] = 0;
                    finalToVerse[0] = 0;

                    int quranPartSurahId= getQuranPartIdBySurahNameAndQuranPartNumber(linearLayoutContentView.
                            getSpinnerSurahName().getSelectedItem()
                            .toString(),Integer.parseInt(linearLayoutContentView.
                            getSpinnerQuranPartNumber().getSelectedItem().toString()));

                    studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(finalStartIndex).setQuranPartSurahId(quranPartSurahId);




                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            linearLayoutContentView.getSpinnerFromVerses().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("TAG", "onItemSelected: "+linearLayoutContentView.getSpinnerFromVerses().getSelectedItem().toString());
                    studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(finalStartIndex).setFromVerses(Integer.parseInt(linearLayoutContentView.getSpinnerFromVerses().getSelectedItem().toString()));


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            linearLayoutContentView.getSpinnerToVerses().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(finalStartIndex).setToVerses(Integer.parseInt(linearLayoutContentView.getSpinnerToVerses().getSelectedItem().toString()));


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            startIndex++;



        }

    }

    private void makeGoneSpinners(int from, int to,UserLoginViewHolder holder) {

        for (int i=from;i<to;i++){

            Spinner spinnerQuranPartNumber = holder.arraySpinnerQuranNumber[i];
            Spinner spinnerSurahName = holder.arraySpinnerSurahName[i];
            Spinner spinnerFromVerses =holder.arraySpinnerFromVerse[i];
            Spinner spinnerToVerses = holder.arraySpinnerToVerse[i];

            spinnerQuranPartNumber.setVisibility(View.GONE);
            spinnerSurahName.setVisibility(View.GONE);
            spinnerFromVerses.setVisibility(View.GONE);
            spinnerToVerses.setVisibility(View.GONE);
        }

    }
    private void makeVisibleSpinners(int from, int to,UserLoginViewHolder holder) {

        for (int i=from;i<to;i++){

            Spinner spinnerQuranPartNumber = holder.arraySpinnerQuranNumber[i];
            Spinner spinnerSurahName = holder.arraySpinnerSurahName[i];
            Spinner spinnerFromVerses =holder.arraySpinnerFromVerse[i];
            Spinner spinnerToVerses = holder.arraySpinnerToVerse[i];
            spinnerQuranPartNumber.setVisibility(View.VISIBLE);
            spinnerSurahName.setVisibility(View.VISIBLE);
            spinnerFromVerses.setVisibility(View.VISIBLE);
            spinnerToVerses.setVisibility(View.VISIBLE);
        }
    }
    private void refillNewRow(int pos,UserLoginViewHolder holder){

        for (int i =arrayListGroupFromSameStudentLessonId.get(pos);
             i < arrayListGroupFromSameStudentLessonId.get(pos)
                     +saveStudentLesson.get(pos).size(); i++) {
            int count=i-arrayListGroupFromSameStudentLessonId.get(pos);
            LinearLayoutContentView linearLayoutContentView=new LinearLayoutContentView();
            int quranPartSurahId=saveStudentLesson.get(pos).get(count).getQuranPartSurahId();

            Spinner spinnerQuranPartNumber = holder.arraySpinnerQuranNumber[i];
            Spinner spinnerSurahName = holder.arraySpinnerSurahName[i];
            Spinner spinnerFromVerses = holder.arraySpinnerFromVerse[i];
            Spinner spinnerToVerses = holder.arraySpinnerToVerse[i];

            linearLayoutContentView.setSpinnerQuranPartNumber(spinnerQuranPartNumber);
            linearLayoutContentView.setSpinnerSurahName(spinnerSurahName);
            linearLayoutContentView.setSpinnerFromVerses(spinnerFromVerses);
            linearLayoutContentView.setSpinnerToVerses(spinnerToVerses);

            QuranPartSurahJoinQuranPartJoinSurah quranPartSurahJoinQuranPartJoinSurah=getQuranPartNumberFromArrayAndPutInSpinnerAndDetectSurahName(quranPartSurahId);
            int quranPartNumber=quranPartSurahJoinQuranPartJoinSurah.getQuranPartNumber();
            String surahName=quranPartSurahJoinQuranPartJoinSurah.getSurahName();

            int indexSurahName=getSurahIndexFromSurahName(quranPartNumber,surahName);

            ArrayAdapter arrayAdapterSurahName;
            ArrayAdapter arrayAdapterQuranPartNumber;
            ArrayAdapter arrayAdapterVerseFrom;
            ArrayAdapter arrayAdapterVerseTo;
            arrayAdapterSurahName = new ArrayAdapter(context, android.R.layout.simple_spinner_item);
            arrayAdapterQuranPartNumber = new ArrayAdapter(context, android.R.layout.simple_spinner_item);
            arrayAdapterVerseFrom = new ArrayAdapter(context, android.R.layout.simple_spinner_item);
            arrayAdapterVerseTo = new ArrayAdapter(context, android.R.layout.simple_spinner_item);
            linearLayoutContentView.getSpinnerSurahName().setAdapter(arrayAdapterSurahName);
            linearLayoutContentView.getSpinnerFromVerses().setAdapter(arrayAdapterVerseFrom);
            linearLayoutContentView.getSpinnerToVerses().setAdapter(arrayAdapterVerseTo);
            arrayAdapterQuranPartNumber.addAll(quranNumbers.keySet());
            linearLayoutContentView.getSpinnerQuranPartNumber().setAdapter(arrayAdapterQuranPartNumber);
            //*********************GetQuranPartNumberFromArrayAndPutInSpinnerAndDetectSurahName******************************
            //************GetSurahIndexFromSurahName*********
            //*****************GetToAndFromFromStudentLessonJoin...**************
            int studentFromVerse =   saveStudentLesson.get(pos).get(count).getFromVerses();
            int studentToVerse =  saveStudentLesson.get(pos).get(count).getToVerses();
            //************final inside setOnItem**********
            final int[] finalIndexSurahName = {indexSurahName};
            final int[] finalFromVerse = {studentFromVerse - quranPartSurahJoinQuranPartJoinSurah.getQuranPartSurahFrom()};
            final int[] finalToVerse = {studentToVerse - quranPartSurahJoinQuranPartJoinSurah.getQuranPartSurahFrom()};
            // Log.d("TAG", "fianl from verse: "+studentFromVerse+" : "+surahFromVerse);
            linearLayoutContentView.getSpinnerQuranPartNumber().setSelection(quranPartNumber - 1);
            //*********************SetOnItemOnQuranPartNumberAndSelectSurahBySurahNames**************************
            linearLayoutContentView.getSpinnerQuranPartNumber().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    int quranPartNumber = position + 1;
                    arrayAdapterSurahName.clear();
                    arrayAdapterSurahName.addAll(quranNumbers.get(quranPartNumber).getSurahNames());
                    //*****swap between notif and setAdapter if you want setSelection in the same position ******
                    linearLayoutContentView.getSpinnerSurahName().setAdapter(arrayAdapterSurahName);
                    //*******setSelectionAfter*******
                    linearLayoutContentView.getSpinnerSurahName().setSelection(finalIndexSurahName[0]);
                    //*********if go to anthor quranpart and if numberofsurahName to anthor< from now  expection **
                    finalIndexSurahName[0] = 0;



                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            linearLayoutContentView.getSpinnerSurahName().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    List<Integer> orderOfVerses = new ArrayList<>();
                    int quranPartNumber = linearLayoutContentView.getSpinnerQuranPartNumber().getSelectedItemPosition() + 1;
                    int startFromVerse = quranNumbers.get(quranPartNumber).getFromVerses().get(position);
                    int finishToVerse = quranNumbers.get(quranPartNumber).getToVerses().get(position);

                    arrayAdapterVerseFrom.clear();
                    arrayAdapterVerseTo.clear();
                    orderOfVerses.clear();

                    for (int i = startFromVerse; i <= finishToVerse; i++)
                        orderOfVerses.add(i);

                    arrayAdapterVerseFrom.addAll(orderOfVerses);
                    arrayAdapterVerseTo.addAll(orderOfVerses);

                    arrayAdapterVerseFrom.notifyDataSetChanged();
                    arrayAdapterVerseTo.notifyDataSetChanged();

                    linearLayoutContentView.getSpinnerFromVerses().setSelection(finalFromVerse[0]);
                    linearLayoutContentView.getSpinnerToVerses().setSelection(finalToVerse[0]);
                    finalFromVerse[0] = 0;
                    finalToVerse[0] = 0;

                    int quranPartSurahId= getQuranPartIdBySurahNameAndQuranPartNumber(linearLayoutContentView.
                            getSpinnerSurahName().getSelectedItem()
                            .toString(),Integer.parseInt(linearLayoutContentView.
                            getSpinnerQuranPartNumber().getSelectedItem().toString()));

                    saveStudentLesson.get(pos).get(count).setQuranPartSurahId(quranPartSurahId);




                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            linearLayoutContentView.getSpinnerFromVerses().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("TAG", "onItemSelected: "+linearLayoutContentView.getSpinnerFromVerses().getSelectedItem().toString());
                    saveStudentLesson.get(pos).get(count).setFromVerses(Integer.parseInt(linearLayoutContentView.getSpinnerFromVerses().getSelectedItem().toString()));


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            linearLayoutContentView.getSpinnerToVerses().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    saveStudentLesson.get(pos).get(count).setToVerses(Integer.parseInt(linearLayoutContentView.getSpinnerToVerses().getSelectedItem().toString()));


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });




        }



    }
    @Override
    public int getItemCount() {
        return getSizeDistinctStudentLessonQuranPartSurahJoinStudentLessonJoinUserLogins();
    }
    class UserLoginViewHolder extends RecyclerView.ViewHolder {

        public MyCustomEditTextListener myCustomEditTextListener;
        Spinner[] arraySpinnerQuranNumber=new Spinner[6];
        ArrayAdapter[] arrayAdaptersQuranNumber=new ArrayAdapter[6];
        ArrayAdapter[] arrayAdaptersSurahName=new ArrayAdapter[6];
        ArrayAdapter[] arrayAdaptersFromVerse=new ArrayAdapter[6];
        ArrayAdapter[] arrayAdaptersToVerses=new ArrayAdapter[6];
        int[] spinnerQuranNumberId={
                R.id.spinner_quran_part_number,
                R.id.spinner_quran_part_number_2,
                R.id.spinner_quran_part_number_3,
                R.id.spinner_quran_part_number_4,
                R.id.spinner_quran_part_number_5,
                R.id.spinner_quran_part_number_6};
        Spinner[] arraySpinnerSurahName=new Spinner[6];
        int[] arraySpinnerSurahNameId={
                R.id.spinner_surah_name,
                R.id.spinner_surah_name_2,
                R.id.spinner_surah_name_3,
                R.id.spinner_surah_name_4,
                R.id.spinner_surah_name_5,
                R.id.spinner_surah_name_6};
        Spinner[] arraySpinnerFromVerse=new Spinner[6];
        int[] arraySpinnerFromVerseId={
                R.id.spinner_from_verses,
                R.id.spinner_from_verses_2,
                R.id.spinner_from_verses_3,
                R.id.spinner_from_verses_4,
                R.id.spinner_from_verses_5,
                R.id.spinner_from_verses_6};
        Spinner[] arraySpinnerToVerse=new Spinner[6];
        int[] arraySpinnerToVerseId={
                R.id.spinner_to_verses,
                R.id.spinner_to_verses_2,
                R.id.spinner_to_verses_3,
                R.id.spinner_to_verses_4,
                R.id.spinner_to_verses_5,
                R.id.spinner_to_verses_6};

        EditText editTextNote;
        TextView textViewStudentName;

        TextView textViewIncrease;
        TextView textViewDecrease;



        public UserLoginViewHolder(@NonNull View itemView) {
            super(itemView);

            for (int i = 0; i < 6; i++) {
                arraySpinnerQuranNumber[i]=itemView.findViewById(spinnerQuranNumberId[i]);
                arrayAdaptersQuranNumber[i]=new ArrayAdapter(context, android.R.layout.simple_spinner_item);


            }
            for (int i = 0; i < 6; i++) {
                arraySpinnerSurahName[i]=itemView.findViewById(arraySpinnerSurahNameId[i]);
                arrayAdaptersSurahName[i]=new ArrayAdapter(context, android.R.layout.simple_spinner_item);

            }
            for (int i = 0; i < 6; i++) {
                arraySpinnerFromVerse[i]=itemView.findViewById(arraySpinnerFromVerseId[i]);
                arrayAdaptersFromVerse[i]=new ArrayAdapter(context, android.R.layout.simple_spinner_item);

            }
            for (int i = 0; i < 6; i++) {
                arraySpinnerToVerse[i]=itemView.findViewById(arraySpinnerToVerseId[i]);
                arrayAdaptersToVerses[i]=new ArrayAdapter(context, android.R.layout.simple_spinner_item);

            }

            editTextNote=itemView.findViewById(R.id.edit_text_multi_line_notes);
            myCustomEditTextListener=new MyCustomEditTextListener();
            editTextNote.addTextChangedListener(myCustomEditTextListener);
            textViewStudentName=itemView.findViewById(R.id.student_name);
            textViewDecrease=itemView.findViewById(R.id.decrease_linear_layout);
            textViewIncrease=itemView.findViewById(R.id.increase_linear_layout);


        }
    }
    private QuranPartSurahJoinQuranPartJoinSurah getQuranPartNumberFromArrayAndPutInSpinnerAndDetectSurahName(
            int quranPartSurahId){


        QuranPartSurahJoinQuranPartJoinSurah quranPartSurahJoinQuranPartJoinSurah=new QuranPartSurahJoinQuranPartJoinSurah();
        for (int j = 0; j < quranPartSurahJoinQuranPartJoinSurahs.size(); j++) {

            if (quranPartSurahJoinQuranPartJoinSurahs.get(j).getQuranPartSurahId() == quranPartSurahId) {

                quranPartSurahJoinQuranPartJoinSurah.setQuranPartNumber(quranPartSurahJoinQuranPartJoinSurahs.get(j).getQuranPartNumber());
                quranPartSurahJoinQuranPartJoinSurah.setSurahName(quranPartSurahJoinQuranPartJoinSurahs.get(j).getSurahName());
                quranPartSurahJoinQuranPartJoinSurah.setQuranPartSurahFrom(quranPartSurahJoinQuranPartJoinSurahs.get(j).getQuranPartSurahFrom());
                quranPartSurahJoinQuranPartJoinSurah.setQuranPartSurahTo(quranPartSurahJoinQuranPartJoinSurahs.get(j).getQuranPartSurahTo());
                break;
            }
        }



        return  quranPartSurahJoinQuranPartJoinSurah;

    }
    private void refill(int index,int indexSurahName, int quranPartNumber,
                        int surahFromVerse, QuranPartSurah quranPartSurah,
                        LinearLayoutContentView linearLayoutContentView){
        //****************ViewsChildren*********************
        //****************Adapter*******************
        ArrayAdapter arrayAdapterSurahName;
        ArrayAdapter arrayAdapterQuranPartNumber;
        ArrayAdapter arrayAdapterVerseFrom;
        ArrayAdapter arrayAdapterVerseTo;
        arrayAdapterSurahName = new ArrayAdapter(context, android.R.layout.simple_spinner_item);
        arrayAdapterQuranPartNumber = new ArrayAdapter(context, android.R.layout.simple_spinner_item);
        arrayAdapterVerseFrom = new ArrayAdapter(context, android.R.layout.simple_spinner_item);
        arrayAdapterVerseTo = new ArrayAdapter(context, android.R.layout.simple_spinner_item);
        linearLayoutContentView.getSpinnerSurahName().setAdapter(arrayAdapterSurahName);
        linearLayoutContentView.getSpinnerFromVerses().setAdapter(arrayAdapterVerseFrom);
        linearLayoutContentView.getSpinnerToVerses().setAdapter(arrayAdapterVerseTo);
        arrayAdapterQuranPartNumber.addAll(quranNumbers.keySet());
        linearLayoutContentView.getSpinnerQuranPartNumber().setAdapter(arrayAdapterQuranPartNumber);
        //*********************GetQuranPartNumberFromArrayAndPutInSpinnerAndDetectSurahName******************************
        //************GetSurahIndexFromSurahName*********
        //*****************GetToAndFromFromStudentLessonJoin...**************
        int studentFromVerse = quranPartSurah.getQuranPartSurahFrom();
        int studentToVerse = quranPartSurah.getQuranPartSurahTo();
        //************final inside setOnItem**********
        final int[] finalIndexSurahName = {indexSurahName};
        final int[] finalFromVerse = {studentFromVerse - surahFromVerse};
        final int[] finalToVerse = {studentToVerse - surahFromVerse};
        Log.d("TAG", "fianl from verse: "+studentFromVerse+" : "+surahFromVerse);
        linearLayoutContentView.getSpinnerQuranPartNumber().setSelection(quranPartNumber - 1);
        //*********************SetOnItemOnQuranPartNumberAndSelectSurahBySurahNames**************************
        linearLayoutContentView.getSpinnerQuranPartNumber().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int quranPartNumber = position + 1;
                arrayAdapterSurahName.clear();
                arrayAdapterSurahName.addAll(quranNumbers.get(quranPartNumber).getSurahNames());
                //*****swap between notif and setAdapter if you want setSelection in the same position ******
                linearLayoutContentView.getSpinnerSurahName().setAdapter(arrayAdapterSurahName);
                //*******setSelectionAfter*******
                linearLayoutContentView.getSpinnerSurahName().setSelection(finalIndexSurahName[0]);
                //*********if go to anthor quranpart and if numberofsurahName to anthor< from now  expection **
                finalIndexSurahName[0] = 0;



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        linearLayoutContentView.getSpinnerSurahName().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                List<Integer> orderOfVerses = new ArrayList<>();
                int quranPartNumber = linearLayoutContentView.getSpinnerQuranPartNumber().getSelectedItemPosition() + 1;
                int startFromVerse = quranNumbers.get(quranPartNumber).getFromVerses().get(position);
                int finishToVerse = quranNumbers.get(quranPartNumber).getToVerses().get(position);

                arrayAdapterVerseFrom.clear();
                arrayAdapterVerseTo.clear();
                orderOfVerses.clear();

                for (int i = startFromVerse; i <= finishToVerse; i++)
                    orderOfVerses.add(i);

                arrayAdapterVerseFrom.addAll(orderOfVerses);
                arrayAdapterVerseTo.addAll(orderOfVerses);

                arrayAdapterVerseFrom.notifyDataSetChanged();
                arrayAdapterVerseTo.notifyDataSetChanged();

                linearLayoutContentView.getSpinnerFromVerses().setSelection(finalFromVerse[0]);
                linearLayoutContentView.getSpinnerToVerses().setSelection(finalToVerse[0]);
                finalFromVerse[0] = 0;
                finalToVerse[0] = 0;

                int quranPartSurahId= getQuranPartIdBySurahNameAndQuranPartNumber(linearLayoutContentView.
                        getSpinnerSurahName().getSelectedItem()
                        .toString(),Integer.parseInt(linearLayoutContentView.
                        getSpinnerQuranPartNumber().getSelectedItem().toString()));

                studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(index).setQuranPartSurahId(quranPartSurahId);




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        linearLayoutContentView.getSpinnerFromVerses().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "onItemSelected: "+linearLayoutContentView.getSpinnerFromVerses().getSelectedItem().toString());
                studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(index).setFromVerses(Integer.parseInt(linearLayoutContentView.getSpinnerFromVerses().getSelectedItem().toString()));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        linearLayoutContentView.getSpinnerToVerses().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(index).setToVerses(Integer.parseInt(linearLayoutContentView.getSpinnerToVerses().getSelectedItem().toString()));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    private int getSurahIndexFromSurahName(int quranPartNumber, String surahName){

        int indexSurahName = 0;
        ArrayOfQuranPartSurah arrayOfQuranPartSurah = quranNumbers.get(quranPartNumber);
        for (int j = 0; j < arrayOfQuranPartSurah.getSurahNames().size(); j++) {
            if (arrayOfQuranPartSurah.getSurahNames().get(j).equals(surahName)) {

                indexSurahName = j;
            }
        }
        return  indexSurahName;
    }
    private int getQuranPartIdBySurahNameAndQuranPartNumber(String surahName,int quranPartNumber){
        for (int j = 0; j < quranPartSurahJoinQuranPartJoinSurahs.size(); j++) {

            if (quranPartSurahJoinQuranPartJoinSurahs.get(j).getQuranPartNumber() == quranPartNumber &&
                    quranPartSurahJoinQuranPartJoinSurahs.get(j).getSurahName().equals(surahName)) {
                return quranPartSurahJoinQuranPartJoinSurahs.get(j).getQuranPartSurahId();
            }


        }
        return  -1;
    }
    private ArrayList<StudentLessonQuranPartSurah> extractDataFromTheNewViews(){


        ArrayList<StudentLessonQuranPartSurah>arrayListSave=new ArrayList<>();
        for (int j = 0; j < getSizeDistinctStudentLessonQuranPartSurahJoinStudentLessonJoinUserLogins(); j++) {
            int startIndex=arrayListStartIndex.get(j);
            for (int i=0;i<saveStudentLesson.get(j).size();i++) {
                StudentLessonQuranPartSurah element=new StudentLessonQuranPartSurah();
                element.setStudentLessonId(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getStudentLessonId());
                element.setFromVerses((saveStudentLesson.get(j).get(i).getFromVerses()));
                element.setToVerses(saveStudentLesson.get(j).get(i).getToVerses());
                element.setQuranPartSurahId(saveStudentLesson.get(j).get(i).getQuranPartSurahId());
                arrayListSave.add(element);
            }
        }



        return arrayListSave;

    }

    private ArrayList<StudentLessonQuranPartSurah> extractDataFromTheOldViews(){


        ArrayList<StudentLessonQuranPartSurah>arrayListUpdate=new ArrayList<>();

        for (int j = 0; j < getSizeDistinctStudentLessonQuranPartSurahJoinStudentLessonJoinUserLogins(); j++) {
            int startIndex=arrayListStartIndex.get(j);
            for (int i=0;i<arrayListGroupFromSameStudentLessonId.get(j);i++) {
                StudentLessonQuranPartSurah element=new StudentLessonQuranPartSurah();

                element.setStudentLessonQuranPartSurahId(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getStudentLessonQuranPartSurahId());
                element.setStudentLessonId(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getStudentLessonId());
                element.setFromVerses((studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getFromVerses()));
                element.setToVerses(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getToVerses());
                element.setQuranPartSurahId(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getQuranPartSurahId());
                arrayListUpdate.add(element);
                startIndex++;
            }
            }



        return  arrayListUpdate;
    }
    private ArrayList<StudentLesson>extractNoteFromViews(){

        ArrayList<StudentLesson>arrayListSaveNote=new ArrayList<>();

        //save note
        for(int j=0;j<getSizeDistinctStudentLessonQuranPartSurahJoinStudentLessonJoinUserLogins();j++){
            int startIndex=arrayListStartIndex.get(j);

            StudentLesson studentLesson=new StudentLesson();
            studentLesson.setStudentLessonId(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getStudentLessonId());
            studentLesson.setNote(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getNote());

            arrayListSaveNote.add(studentLesson);

        }

        return arrayListSaveNote;
    }

    public ArrayList<StudentLessonQuranPartSurah> getDataForSaving(){
        return extractDataFromTheNewViews();
    }
    public ArrayList<StudentLessonQuranPartSurah> getDataForUpdating(){
        return extractDataFromTheOldViews();
    }
    public ArrayList<String> createStudentsMessages(){

        ArrayList<String>messages=new ArrayList<>();

        for(int j=0;j<getSizeDistinctStudentLessonQuranPartSurahJoinStudentLessonJoinUserLogins();j++){
            int startIndex=arrayListStartIndex.get(j);
            StringBuilder message=new StringBuilder();
            String string="\n";
            string+="الاسم:  "+"\u202A"+(this.studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getName());
            string+="\n";
          //  message.append("الاسم :").append(this.studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getName()).append("\n");
            for (int i=0;i<arrayListGroupFromSameStudentLessonId.get(j);i++) {

                int quranPartSurahId=studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getQuranPartSurahId();
                QuranPartSurahJoinQuranPartJoinSurah quranPartSurahJoinQuranPartJoinSurah=getQuranPartNumberFromArrayAndPutInSpinnerAndDetectSurahName(quranPartSurahId);
                int quranPartNumber=quranPartSurahJoinQuranPartJoinSurah.getQuranPartNumber();
                String surahName=quranPartSurahJoinQuranPartJoinSurah.getSurahName();

                int from=studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getFromVerses();
                int to=studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getToVerses();

                string+="الجزء "+"\u202A"+(quranPartNumber)+"\u202C"+" السورة "+"\u202A"+surahName.trim()+"\u202C"+" من :"+"\u202A"+from+"\u202C"+" الى :"+"\u202A"+to+"\n";

              //  string+=surahName+"\u202A"+" السورة : "+"\u202A"+"AAF"+"\u202E"+"الجزء: ";
//                message.append("الجزء: ").append(quranPartNumber);
//
//                message.append(" السورة: ").append(surahName);
//                message.append(" من: ").append(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getFromVerses());
//                message.append(" الى: ").append(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getToVerses()).append("\n");
                startIndex++;
            }
            for (int i=0;i<saveStudentLesson.get(j).size();i++) {

                int quranPartSurahId=saveStudentLesson.get(j).get(i).getQuranPartSurahId();
                QuranPartSurahJoinQuranPartJoinSurah quranPartSurahJoinQuranPartJoinSurah=getQuranPartNumberFromArrayAndPutInSpinnerAndDetectSurahName(quranPartSurahId);
                int quranPartNumber=quranPartSurahJoinQuranPartJoinSurah.getQuranPartNumber();
                String surahName=quranPartSurahJoinQuranPartJoinSurah.getSurahName();
                int from=saveStudentLesson.get(j).get(i).getFromVerses();
                int to=saveStudentLesson.get(j).get(i).getToVerses();

                string+="الجزء "+"\u202A"+(quranPartNumber)+"\u202C"+" السورة "+"\u202A"+surahName.trim()+"\u202C"+" من :"+"\u202A"+from+"\u202C"+" الى :"+"\u202A"+to+"\n";
//
//                message.append("الجزء: ").append(quranPartNumber);
//                message.append(" السورة: ").append(surahName);
//                message.append(" من: ").append();
//                message.append(" الى: ").append(saveStudentLesson.get(j).get(i).getToVerses()).append("\n");


            }
            startIndex=arrayListStartIndex.get(j);
            string+="الملاحظات:  "+"\u202A"+(this.studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getNote());

           // message.append("الملاحظات: ").append(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getNote()).append("\n");
            messages.add(string);
          }
        return messages;
    }

    public ArrayList<Integer>getArrayListStartIndex(){

        return arrayListStartIndex;
    }
    public ArrayList<StudentLesson> getNotes(){
        return extractNoteFromViews();
    }
    public ArrayList<String>getStudentsMessages(){
        return createStudentsMessages();

    }

    class ArrayOfQuranPartSurah {
        private ArrayList<String> surahNames = new ArrayList<>();
        private ArrayList<Integer> fromVerses = new ArrayList<>();
        private ArrayList<Integer> toVerses = new ArrayList<>();

        public ArrayList<String> getSurahNames() {
            return surahNames;
        }

        public void setSurahNames(ArrayList<String> surahNames) {
            this.surahNames = surahNames;
        }

        public ArrayList<Integer> getFromVerses() {
            return fromVerses;
        }

        public void setFromVerses(ArrayList<Integer> fromVerses) {
            this.fromVerses = fromVerses;
        }

        public ArrayList<Integer> getToVerses() {
            return toVerses;
        }

        public void setToVerses(ArrayList<Integer> toVerses) {
            this.toVerses = toVerses;
        }
    }
    class LinearLayoutContentView{
        Spinner spinnerQuranPartNumber;
        Spinner spinnerSurahName ;
        Spinner spinnerFromVerses;
        Spinner spinnerToVerses ;

        public Spinner getSpinnerQuranPartNumber() {
            return spinnerQuranPartNumber;
        }

        public void setSpinnerQuranPartNumber(Spinner spinnerQuranPartNumber) {
            this.spinnerQuranPartNumber = spinnerQuranPartNumber;
        }

        public Spinner getSpinnerSurahName() {
            return spinnerSurahName;
        }

        public void setSpinnerSurahName(Spinner spinnerSurahName) {
            this.spinnerSurahName = spinnerSurahName;
        }

        public Spinner getSpinnerFromVerses() {
            return spinnerFromVerses;
        }

        public void setSpinnerFromVerses(Spinner spinnerFromVerses) {
            this.spinnerFromVerses = spinnerFromVerses;
        }

        public Spinner getSpinnerToVerses() {
            return spinnerToVerses;
        }

        public void setSpinnerToVerses(Spinner spinnerToVerses) {
            this.spinnerToVerses = spinnerToVerses;
        }
    }
    private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            Log.d("TAG", "afterTextChanged: "+position);

        }

        @Override
        public void afterTextChanged(Editable editable) {

            int index=arrayListStartIndex.get(position);
            studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(index).setNote(editable.toString());


        }
    }

}

