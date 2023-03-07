package net.jordan.quran_club.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

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

public class ListStudentLessonAdapter extends BaseAdapter {
    private Context context;
    private int resource;
    private HashMap<Integer, ArrayOfQuranPartSurah> quranNumbers = new HashMap<>();
    private ArrayList<QuranPartSurahJoinQuranPartJoinSurah> quranPartSurahJoinQuranPartJoinSurahs=new ArrayList<>();
    private ArrayList<StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin> studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins=new ArrayList<>();
    public ListStudentLessonAdapter(Context context, int resource, ArrayList<QuranPartSurahJoinQuranPartJoinSurah> quranPartSurahJoinQuranPartJoinSurahs) {
        this.context = context;
        this.resource = resource;
        this.quranPartSurahJoinQuranPartJoinSurahs = quranPartSurahJoinQuranPartJoinSurahs;

    }

    ArrayList<Integer>arrayListGroupFromSameStudentLessonId=new ArrayList<>();
    ArrayList<Integer>arrayListStartIndex=new ArrayList<>();
    public void setStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogins(ArrayList<StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin> studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins) {

        this.studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.clear();
        this.studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.addAll(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins);
        arrayListStartIndex.clear();
        arrayListGroupFromSameStudentLessonId.clear();
        Log.d("TAG", "setStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogins: ");
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
   //*******sizeDistinct important in getCount****
    public int  getSizeDistinctStudentLessonQuranPartSurahJoinStudentLessonJoinUserLogins(){
        Log.d("TAG", "size: ");

        HashSet<Integer>set=new HashSet<>();
        for (int i = 0; i < studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.size(); i++) {
            set.add(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(i).getStudentLessonId());
        }



        return set.size();


    }
    @Override
    public int getCount() {
        return getSizeDistinctStudentLessonQuranPartSurahJoinStudentLessonJoinUserLogins();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return 100;
    }
    private  int linearLayoutId=50000;
    private  int spinnerId=25000;
    ArrayList<Integer>arrayListLinearLayoutParentIds=new ArrayList<>();
    ArrayList<Integer>arrayListSpinnerQuranPartNumberIds=new ArrayList<>();
    ArrayList<Integer>arrayListSpinnerSurahNameIds=new ArrayList<>();
    ArrayList<Integer>arrayListSpinnerFromVersesIds=new ArrayList<>();
    ArrayList<Integer>arrayListSpinnerToVersesIds=new ArrayList<>();
    List<View>viewList=new ArrayList<>();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("TAG", "getView: "+position);
        if (convertView == null) {


            convertView = LayoutInflater.from(context).inflate(resource, null, false);
            View view = convertView;


            //****************ViewsParentLinearLayout*********************
            //LinearLayout linearLayoutListAdapterParent=view.findViewById(R.id.linear_layout_list_adapter);

            //****************SaveIds********************

             arrayListLinearLayoutParentIds=new ArrayList<>();
             arrayListSpinnerQuranPartNumberIds=new ArrayList<>();
             arrayListSpinnerSurahNameIds=new ArrayList<>();
             arrayListSpinnerFromVersesIds=new ArrayList<>();
             arrayListSpinnerToVersesIds=new ArrayList<>();


            arrayListSpinnerQuranPartNumberIds.add(R.id.spinner_quran_part_number);
            arrayListSpinnerSurahNameIds.add(R.id.spinner_surah_name);
            arrayListSpinnerFromVersesIds.add(R.id.spinner_from_verses);
            arrayListSpinnerToVersesIds.add(R.id.spinner_to_verses);

            //*****************Create new Views inside LinearLayoutListAdapter ********************
            for (int i=0;i<arrayListGroupFromSameStudentLessonId.get(position)-1;i++){
                createNewViews(view);
            }


            int startIndex=arrayListStartIndex.get(position);
           // *********************textView*****************
            TextView textViewStudentName = view.findViewById(R.id.student_name);
            TextView increaseLinearLayout=view.findViewById(R.id.increase_linear_layout);
            TextView decreaseLinearLayout=view.findViewById(R.id.decrease_linear_layout);
            EditText editTextNote=view.findViewById(R.id.edit_text_multi_line_notes);

            // *******************setNote********************
            editTextNote.setText(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getNote());
            //********************setStudentName*************

            QuranPartSurah quranPartSurah=new QuranPartSurah();

            textViewStudentName.setText(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getName());
            increaseLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


//                    extractDataFromViews(parent);

                    LinearLayoutContentView linearLayoutContentView=new LinearLayoutContentView();
                    int quranPartSurahId=0;
                    quranPartSurah.setQuranPartSurahId(0);
                    quranPartSurah.setQuranPartSurahFrom(0);
                    quranPartSurah.setQuranPartSurahTo(0);

                    createNewViews(view);


                    int lastIndex=arrayListSpinnerQuranPartNumberIds.size()-1;
                    Spinner spinnerQuranPartNumber = view.findViewById(arrayListSpinnerQuranPartNumberIds.get(lastIndex));
                    Spinner spinnerSurahName = view.findViewById(arrayListSpinnerSurahNameIds.get(lastIndex));
                    Spinner spinnerFromVerses = view.findViewById(arrayListSpinnerFromVersesIds.get(lastIndex));
                    Spinner spinnerToVerses = view.findViewById(arrayListSpinnerToVersesIds.get(lastIndex));


                    linearLayoutContentView.setSpinnerQuranPartNumber(spinnerQuranPartNumber);
                    linearLayoutContentView.setSpinnerSurahName(spinnerSurahName);
                    linearLayoutContentView.setSpinnerFromVerses(spinnerFromVerses);
                    linearLayoutContentView.setSpinnerToVerses(spinnerToVerses);


                    refill(0,0,0,quranPartSurah,linearLayoutContentView);





                }
            });
            decreaseLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LinearLayout linearLayoutListAdapterParent=view.findViewById(R.id.linear_layout_list_adapter);
                    linearLayoutListAdapterParent.removeViewAt(linearLayoutListAdapterParent.getChildCount()-2);

                }

            });


            //*********************Refill********************
            for (int i=0;i<arrayListGroupFromSameStudentLessonId.get(position);i++) {

                LinearLayoutContentView linearLayoutContentView=new LinearLayoutContentView();
                int quranPartSurahId=studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getQuranPartSurahId();
                quranPartSurah.setQuranPartSurahId(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getQuranPartSurahId());
                quranPartSurah.setQuranPartSurahFrom(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getFromVerses());
                quranPartSurah.setQuranPartSurahTo( studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getToVerses());

                Spinner spinnerQuranPartNumber = view.findViewById(arrayListSpinnerQuranPartNumberIds.get(i));
                Spinner spinnerSurahName = view.findViewById(arrayListSpinnerSurahNameIds.get(i));
                Spinner spinnerFromVerses = view.findViewById(arrayListSpinnerFromVersesIds.get(i));
                Spinner spinnerToVerses = view.findViewById(arrayListSpinnerToVersesIds.get(i));

                linearLayoutContentView.setSpinnerQuranPartNumber(spinnerQuranPartNumber);
                linearLayoutContentView.setSpinnerSurahName(spinnerSurahName);
                linearLayoutContentView.setSpinnerFromVerses(spinnerFromVerses);
                linearLayoutContentView.setSpinnerToVerses(spinnerToVerses);

                QuranPartSurahJoinQuranPartJoinSurah quranPartSurahJoinQuranPartJoinSurah=getQuranPartNumberFromArrayAndPutInSpinnerAndDetectSurahName(quranPartSurahId);
                int quranPartNumber=quranPartSurahJoinQuranPartJoinSurah.getQuranPartNumber();
                String surahName=quranPartSurahJoinQuranPartJoinSurah.getSurahName();

                int indexSurahName=getSurahIndexFromSurahName(quranPartNumber,surahName);

                refill(indexSurahName,quranPartSurahJoinQuranPartJoinSurah.getQuranPartNumber(),quranPartSurahJoinQuranPartJoinSurah.getQuranPartSurahFrom(),quranPartSurah,linearLayoutContentView);
                startIndex++;


            }

            viewList.add(view);

        }
        return convertView;
    }
    private void refill(int indexSurahName,int quranPartNumber,int surahFromVerse,QuranPartSurah quranPartSurah,LinearLayoutContentView linearLayoutContentView){
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


        linearLayoutContentView.getSpinnerQuranPartNumber().setSelection(quranPartNumber - 1);

        //*********************SetOnItemOnQuranPartNumberAndSelectSurahBySurahNames**************************
        linearLayoutContentView.getSpinnerQuranPartNumber().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int quranPartNumber = position + 1;
                arrayAdapterSurahName.clear();
                arrayAdapterSurahName.addAll(quranNumbers.get(quranPartNumber).getSurahNames());
                //*****swap between notif and setAdapter if you want setSelection a same position ******
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

        //*********************Se
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
    private QuranPartSurahJoinQuranPartJoinSurah getQuranPartNumberFromArrayAndPutInSpinnerAndDetectSurahName(int quranPartSurahId){


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
    private int getQuranPartIdBySurahNameAndQuranPartNumber(String surahName,int quranPartNumber){
        for (int j = 0; j < quranPartSurahJoinQuranPartJoinSurahs.size(); j++) {

            if (quranPartSurahJoinQuranPartJoinSurahs.get(j).getQuranPartNumber() == quranPartNumber &&
                quranPartSurahJoinQuranPartJoinSurahs.get(j).getSurahName().equals(surahName)) {
                return quranPartSurahJoinQuranPartJoinSurahs.get(j).getQuranPartSurahId();
            }


    }
        return  -1;
    }

    private void createNewViews(View view){

        LinearLayout linearLayoutListAdapterParent=view.findViewById(R.id.linear_layout_list_adapter);


        LinearLayout linearLayoutNewChild = new LinearLayout(context);

        linearLayoutNewChild.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutNewChild.setId(linearLayoutId);
        LinearLayout.LayoutParams spinnerParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 6);
        LinearLayout.LayoutParams spinnerParamsName = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 5);

        {
            Spinner spinnerToVersesNew = new Spinner(context);
            spinnerToVersesNew.setPadding(4,4,4,4);
            spinnerToVersesNew.setLayoutParams(spinnerParams);
            spinnerToVersesNew.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            spinnerToVersesNew.setId(spinnerId);
            linearLayoutNewChild.addView(spinnerToVersesNew);
            arrayListSpinnerToVersesIds.add(spinnerId);
            spinnerId++;

            Spinner spinnerFromVersesNew =new Spinner(context);
            spinnerFromVersesNew.setPadding(4,4,4,4);
            spinnerFromVersesNew.setLayoutParams(spinnerParams);
            spinnerFromVersesNew.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            spinnerFromVersesNew.setId(spinnerId);
            linearLayoutNewChild.addView(spinnerFromVersesNew);
            arrayListSpinnerFromVersesIds.add(spinnerId);
            spinnerId++;

            Spinner spinnerSurahNameNew = new Spinner(context);
            spinnerSurahNameNew.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            spinnerSurahNameNew.setLayoutParams(spinnerParamsName);
            spinnerSurahNameNew.setId(spinnerId);
            linearLayoutNewChild.addView(spinnerSurahNameNew);
            arrayListSpinnerSurahNameIds.add(spinnerId);
            spinnerId++;

            Spinner spinnerQuranPartNumberNew = new Spinner(context);
            spinnerQuranPartNumberNew.setPadding(4,4,4,4);
            spinnerQuranPartNumberNew.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            spinnerQuranPartNumberNew.setLayoutParams(spinnerParams);
            spinnerQuranPartNumberNew.setId(spinnerId);
            linearLayoutNewChild.addView(spinnerQuranPartNumberNew);
            arrayListSpinnerQuranPartNumberIds.add(spinnerId);
            spinnerId++;







        }

        linearLayoutListAdapterParent.addView(linearLayoutNewChild, linearLayoutListAdapterParent.getChildCount()-1);
        linearLayoutId++;



    }




    private ArrayList<StudentLessonQuranPartSurah> extractDataFromTheNewViews(){


         ArrayList<StudentLessonQuranPartSurah>arrayListSave=new ArrayList<>();

        //save
        for(int j=0;j<viewList.size();j++){
            LinearLayout linearLayout= (LinearLayout) viewList.get(j);
            int startIndex=arrayListStartIndex.get(j);
            for (int i=arrayListGroupFromSameStudentLessonId.get(j)+1;i<linearLayout.getChildCount()-1;i++) {
                LinearLayout linearLayoutChild= (LinearLayout) linearLayout.getChildAt(i);

                StudentLessonQuranPartSurah element=new StudentLessonQuranPartSurah();


                Spinner spinnerTo= (Spinner) linearLayoutChild.getChildAt(0);
                Spinner spinnerFrom= (Spinner) linearLayoutChild.getChildAt(1);
                Spinner spinnerSurahName= (Spinner) linearLayoutChild.getChildAt(2);
                Spinner spinnerQuranPartNumber= (Spinner) linearLayoutChild.getChildAt(3);

                int quranPartSurahId= getQuranPartIdBySurahNameAndQuranPartNumber(spinnerSurahName.getSelectedItem().toString(),Integer.parseInt(spinnerQuranPartNumber.getSelectedItem().toString()));

                element.setStudentLessonId(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getStudentLessonId());
                element.setFromVerses(Integer.parseInt(spinnerFrom.getSelectedItem().toString()));
                element.setToVerses(Integer.parseInt(spinnerTo.getSelectedItem().toString()));
                element.setQuranPartSurahId(quranPartSurahId);


                arrayListSave.add(element);

            }
        }






        return arrayListSave;

    }
    private ArrayList<StudentLessonQuranPartSurah> extractDataFromTheOldViews(){


        ArrayList<StudentLessonQuranPartSurah>arrayListUpdate=new ArrayList<>();

        // update
         for(int j=0;j<viewList.size();j++){
            LinearLayout linearLayout= (LinearLayout) viewList.get(j);
            int startIndex=arrayListStartIndex.get(j);


            for (int i=0;i<arrayListGroupFromSameStudentLessonId.get(j);i++) {
                LinearLayout linearLayoutChild= (LinearLayout) linearLayout.getChildAt(i+1);
                StudentLessonQuranPartSurah element=new StudentLessonQuranPartSurah();


                Spinner spinnerTo= (Spinner) linearLayoutChild.getChildAt(0);
                Spinner spinnerFrom= (Spinner) linearLayoutChild.getChildAt(1);
                Spinner spinnerSurahName= (Spinner) linearLayoutChild.getChildAt(2);
                Spinner spinnerQuranPartNumber= (Spinner) linearLayoutChild.getChildAt(3);


                int quranPartSurahId= getQuranPartIdBySurahNameAndQuranPartNumber(spinnerSurahName.getSelectedItem().toString(),Integer.parseInt(spinnerQuranPartNumber.getSelectedItem().toString()));

                element.setStudentLessonQuranPartSurahId(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getStudentLessonQuranPartSurahId());
                element.setStudentLessonId(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getStudentLessonId());
                element.setFromVerses(Integer.parseInt(spinnerFrom.getSelectedItem().toString()));
                element.setToVerses(Integer.parseInt(spinnerTo.getSelectedItem().toString()));
                element.setQuranPartSurahId(quranPartSurahId);


                arrayListUpdate.add(element);
                startIndex++;
            }

        }

        return  arrayListUpdate;
    }
    private ArrayList<StudentLesson>extractNoteFromViews(){

        ArrayList<StudentLesson>arrayListSaveNote=new ArrayList<>();

        //save note
        for(int j=0;j<viewList.size();j++){
            int startIndex=arrayListStartIndex.get(j);
            LinearLayout linearLayout= (LinearLayout) viewList.get(j);
            EditText editTextNote = (EditText) linearLayout.getChildAt(linearLayout.getChildCount()-1);

            StudentLesson studentLesson=new StudentLesson();
            studentLesson.setStudentLessonId(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getStudentLessonId());
            studentLesson.setNote(editTextNote.getText().toString());

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
    public ArrayList<StringBuilder> createStudentsMessages(){

        ArrayList<StringBuilder>messages=new ArrayList<>();
        for(int j=0;j<viewList.size();j++){
            int startIndex=arrayListStartIndex.get(j);
            StringBuilder message=new StringBuilder();
            message.append("الاسم :").append(this.studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getName()).append("\n");
            LinearLayout linearLayout= (LinearLayout) viewList.get(j);
            EditText editTextNote = (EditText) linearLayout.getChildAt(linearLayout.getChildCount()-1);
            for (int i=0;i<linearLayout.getChildCount()-2;i++) {
                LinearLayout linearLayoutChild= (LinearLayout) linearLayout.getChildAt(i+1);
                Spinner spinnerTo= (Spinner) linearLayoutChild.getChildAt(0);
                Spinner spinnerFrom= (Spinner) linearLayoutChild.getChildAt(1);
                Spinner spinnerSurahName= (Spinner) linearLayoutChild.getChildAt(2);
                Spinner spinnerQuranPartNumber= (Spinner) linearLayoutChild.getChildAt(3);
                message.append("الجزء: ").append(spinnerQuranPartNumber.getSelectedItem().toString());
                message.append(" السورة: ").append(spinnerSurahName.getSelectedItem().toString());
                message.append(" من: ").append(spinnerFrom.getSelectedItem().toString());
                message.append(" الى: ").append(spinnerTo.getSelectedItem().toString()).append("\n");


            }
            message.append("الملاحظات: ").append(editTextNote.getText().toString()).append("\n");
            messages.add(message);
        }
          return messages;
    }

    public ArrayList<Integer>getArrayListStartIndex(){

        return arrayListStartIndex;
    }
    public ArrayList<StudentLesson> getNotes(){
        return extractNoteFromViews();
    }
    public ArrayList<StringBuilder>getStudentsMessages(){
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


}
