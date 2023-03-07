package net.jordan.quran_club.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.quranPartSurah.QuranPartSurah;
import net.jordan.quran_club.model.quranPartSurah.QuranPartSurahJoinQuranPartJoinSurah;
import net.jordan.quran_club.model.studentLessonQuranPartSurah.StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ListShowStudentLessonViewHolderTestAdapter extends RecyclerView.Adapter<ListShowStudentLessonViewHolderTestAdapter.UserLoginViewHolder> {
    private Context context;
    private int resource;
    private HashMap<Integer, ArrayOfQuranPartSurah> quranNumbers = new HashMap<>();
    private ArrayList<QuranPartSurahJoinQuranPartJoinSurah> quranPartSurahJoinQuranPartJoinSurahs=new ArrayList<>();
    private ArrayList<StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin> studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins=new ArrayList<>();

    ArrayList<Integer>arrayListGroupFromSameStudentLessonId=new ArrayList<>();
    ArrayList<Integer>arrayListStartIndex=new ArrayList<>();
    public void setStudentLessonQuranPartSurahJoinStudentLessonJoinUserlogins(ArrayList<StudentLessonQuranPartSurahJoinStudentLessonJoinUserlogin> studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins) {
        this.studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins = studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins;
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
    //*******sizeDistanict important in getCount****

    public int  getSizeDistinctStudentLessonQuranPartSurahJoinStudentLessonJoinUserLogins(){

        HashSet<Integer> set=new HashSet<>();
        for (int i = 0; i < studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.size(); i++) {
            set.add(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(i).getStudentLessonId());
        }

  return set.size();


    }

    public ListShowStudentLessonViewHolderTestAdapter(Context context, int resource, ArrayList<QuranPartSurahJoinQuranPartJoinSurah> quranPartSurahJoinQuranPartJoinSurahs) {
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

    @Override
    public void onBindViewHolder(@NonNull UserLoginViewHolder holder, int position) {

        int startIndex=arrayListStartIndex.get(position);
        // *********************textView*****************
        TextView textViewLessonName = holder.textViewLessonName.
                findViewById(R.id.lesson_name);
        EditText editTextNote= holder.editTextNote.
                findViewById(R.id.edit_text_multi_line_notes);
        // *******************setNote********************
        editTextNote.setText(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getNote());
        editTextNote.setEnabled(false);

        //********************setStudentName*************
        QuranPartSurah quranPartSurah=new QuranPartSurah();
        textViewLessonName.setText(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getLessonName());

        for (int i=0;i<arrayListGroupFromSameStudentLessonId.get(position);i++) {

            int quranPartSurahId=studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getQuranPartSurahId();
            quranPartSurah.setQuranPartSurahId(studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getQuranPartSurahId());
            int fromVerse=studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getFromVerses();
            int toVerse= studentLessonQuranPartSurahJoinStudentLessonJoinUserlogins.get(startIndex).getToVerses();

            TextView textViewQuranPartNumber = holder.arrayTextViewQuranNumber[i];
            TextView textViewSurahName = holder.arrayTextViewSurahName[i];
            TextView textViewFromVerses =holder.arrayTextViewFromVerse[i];
            TextView textViewToVerses = holder.arrayTextViewToVerse[i];


            QuranPartSurahJoinQuranPartJoinSurah quranPartSurahJoinQuranPartJoinSurah=getQuranPartNumberFromArrayAndPutInSpinnerAndDetectSurahName(quranPartSurahId);
            int quranPartNumber=quranPartSurahJoinQuranPartJoinSurah.getQuranPartNumber();
            String surahName=quranPartSurahJoinQuranPartJoinSurah.getSurahName();

            textViewQuranPartNumber.setText(""+quranPartNumber);
            textViewFromVerses.setText(""+fromVerse);
            textViewToVerses.setText(""+toVerse);
            textViewSurahName.setText(surahName);
            startIndex++;


        }


        makeVisibleTextViews(0,arrayListGroupFromSameStudentLessonId.get(position),holder);
        makeGoneTextViews(arrayListGroupFromSameStudentLessonId.get(position),6,holder);

    }

    private void makeGoneTextViews(int from, int to,UserLoginViewHolder holder) {

        for (int i=from;i<to;i++){

            TextView textViewQuranPartNumber = holder.arrayTextViewQuranNumber[i];
            TextView textViewSurahName = holder.arrayTextViewSurahName[i];
            TextView textViewFromVerses =holder.arrayTextViewFromVerse[i];
            TextView textViewToVerses = holder.arrayTextViewToVerse[i];

            textViewQuranPartNumber.setVisibility(View.GONE);
            textViewSurahName.setVisibility(View.GONE);
            textViewFromVerses.setVisibility(View.GONE);
            textViewToVerses.setVisibility(View.GONE);
        }

    }

    private void makeVisibleTextViews(int from, int to,UserLoginViewHolder holder) {

        for (int i=from;i<to;i++){

            TextView textViewQuranPartNumber = holder.arrayTextViewQuranNumber[i];
            TextView textViewSurahName = holder.arrayTextViewSurahName[i];
            TextView textViewFromVerses =holder.arrayTextViewFromVerse[i];
            TextView textViewToVerses = holder.arrayTextViewToVerse[i];
            textViewQuranPartNumber.setVisibility(View.VISIBLE);
            textViewSurahName.setVisibility(View.VISIBLE);
            textViewFromVerses.setVisibility(View.VISIBLE);
            textViewToVerses.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return getSizeDistinctStudentLessonQuranPartSurahJoinStudentLessonJoinUserLogins();
    }
    class UserLoginViewHolder extends RecyclerView.ViewHolder {


        TextView[] arrayTextViewQuranNumber=new TextView[6];
        int[] textViewQuranNumberId={
                R.id.textView_quran_part_number,
                R.id.textView_quran_part_number_2,
                R.id.textView_quran_part_number_3,
                R.id.textView_quran_part_number_4,
                R.id.textView_quran_part_number_5,
                R.id.textView_quran_part_number_6};
        TextView[] arrayTextViewSurahName=new TextView[6];
        int[] arrayTextViewSurahNameId={
                R.id.textView_surah_name,
                R.id.textView_surah_name_2,
                R.id.textView_surah_name_3,
                R.id.textView_surah_name_4,
                R.id.textView_surah_name_5,
                R.id.textView_surah_name_6};
        TextView[] arrayTextViewFromVerse=new TextView[6];
        int[] arrayTextViewFromVerseId={
                R.id.textView_from_verses,
                R.id.textView_from_verses_2,
                R.id.textView_from_verses_3,
                R.id.textView_from_verses_4,
                R.id.textView_from_verses_5,
                R.id.textView_from_verses_6};
        TextView[] arrayTextViewToVerse=new TextView[6];
        int[] arrayTextViewToVerseId={
                R.id.textView_to_verses,
                R.id.textView_to_verses_2,
                R.id.textView_to_verses_3,
                R.id.textView_to_verses_4,
                R.id.textView_to_verses_5,
                R.id.textView_to_verses_6};

        EditText editTextNote;
        TextView textViewLessonName;




        public UserLoginViewHolder(@NonNull View itemView) {
            super(itemView);

            for (int i = 0; i < 6; i++) {
                arrayTextViewQuranNumber[i]=itemView.findViewById(textViewQuranNumberId[i]);
            }
            for (int i = 0; i < 6; i++) {
                arrayTextViewSurahName[i]=itemView.findViewById(arrayTextViewSurahNameId[i]);
            }
            for (int i = 0; i < 6; i++) {
                arrayTextViewFromVerse[i]=itemView.findViewById(arrayTextViewFromVerseId[i]);
            }
            for (int i = 0; i < 6; i++) {
                arrayTextViewToVerse[i]=itemView.findViewById(arrayTextViewToVerseId[i]);
            }

            editTextNote=itemView.findViewById(R.id.edit_text_multi_line_notes);
            textViewLessonName=itemView.findViewById(R.id.lesson_name);

        }
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


}

