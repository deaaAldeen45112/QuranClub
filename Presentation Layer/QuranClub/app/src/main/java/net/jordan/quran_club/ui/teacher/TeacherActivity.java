package net.jordan.quran_club.ui.teacher;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import net.jordan.quran_club.R;
import net.jordan.quran_club.adapter.CustomAdapter;
import net.jordan.quran_club.model.exbandableListView.ChildInfo;
import net.jordan.quran_club.model.exbandableListView.GroupInfo;
import net.jordan.quran_club.model.teacher.ResponseTeacher;
import net.jordan.quran_club.ui.Admin.crud.classes.delete.DeleteClass;
import net.jordan.quran_club.ui.Admin.crud.student.delete.DeleteStudent;
import net.jordan.quran_club.ui.Admin.crud.student.insert.InsertStudent;
import net.jordan.quran_club.ui.Admin.crud.student.update.UpdateStudent;
import net.jordan.quran_club.ui.login.LoginActivity;
import net.jordan.quran_club.ui.teacher.crud.lesson.delete.DeleteLessonFragment;
import net.jordan.quran_club.ui.teacher.crud.lesson.insert.InsertLessonFragment;
import net.jordan.quran_club.ui.teacher.crud.lesson.update.UpdateLessonFragment;
import net.jordan.quran_club.ui.teacher.crud.quranPart.InsertAndDeleteQuranPartFragment;
import net.jordan.quran_club.ui.teacher.crud.studentLesson.StudentLessonFragment;
import net.jordan.quran_club.ui.teacher.crud.studentLesson.StudentLessonFragmentTest;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TeacherActivity extends AppCompatActivity {



    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    private TeacherViewModel teacherViewModel;
    private AppBarConfiguration mAppBarConfiguration;
    private HashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
    private ArrayList<GroupInfo> deptList = new ArrayList<GroupInfo>();
    private CustomAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;
    private int teacherId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        SharedPreferences sharedPreferences=getSharedPreferences("sharedQuranClub",MODE_PRIVATE);
        int userloginId=sharedPreferences.getInt("userlogin_Id",0);
        teacherViewModel= new ViewModelProvider(this).get(TeacherViewModel.class);

        teacherViewModel.getTeacherByUserLoginId(userloginId);

        teacherViewModel.getTeacherLiveData().observe(this, new Observer<ResponseTeacher>() {
            @Override
            public void onChanged(ResponseTeacher responseTeacher) {

                 SharedPreferences.Editor editor = sharedPreferences.edit();
                 teacherId=responseTeacher.getData().get(0).getTeacherId();
                 editor.putInt("teacher_id",responseTeacher.getData().get(0).getTeacherId());
                 selectItemFromNav("Lesson","insert");

            }
        });


        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        Toolbar toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle("Teacher");
        setSupportActionBar(toolbar);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        TextView textViewUserName=findViewById(R.id.textView_username);
        String fullName=sharedPreferences.getString("full_name","");
        textViewUserName.setText(fullName);
        
        loadData();

        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);
        // create the adapter by passing your ArrayList data
        listAdapter = new CustomAdapter(this, deptList);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);

        //expand all the Groups


        // setOnChildClickListener listener for child row click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //get the child info
                ChildInfo detailInfo =  headerInfo.getProductList().get(childPosition);


                selectItemFromNav(headerInfo.getName(),detailInfo.getName());



                //display it or do something with it
                return false;
            }
        });
        // logout
        Button buttonLogOut=findViewById(R.id.button_logout);
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutDialog();

            }
        });
        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //display it or do something with it
                selectItemFromNav(headerInfo.getName(),"");

                return false;
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "SMS Permission Granted ",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }


    private void logoutDialog() {

        AlertDialog.Builder alertDialogLogout=new AlertDialog.Builder(TeacherActivity.this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout");
        alertDialogLogout.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                getSharedPreferences("sharedQuranClub",MODE_PRIVATE).edit().clear().commit();
                startActivity(new Intent(TeacherActivity.this, LoginActivity.class));
                finish();
            }
        });
        alertDialogLogout.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogLogout.show();
    }


    //select group item and child item

    private void selectItemFromNav(String groupName, String childName){

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        if(groupName.compareTo("Quran Part")==0&&childName.compareTo("insert & delete")==0){


            fragmentTransaction.replace(R.id.nav_host_fragment_content_main, InsertAndDeleteQuranPartFragment.newInstance(teacherId)).commit();
        }
        if(groupName.compareTo("Lesson")==0&&childName.compareTo("insert")==0){

            fragmentTransaction.replace(R.id.nav_host_fragment_content_main, InsertLessonFragment.newInstance(teacherId)).commit();
        }
        if(groupName.compareTo("Lesson")==0&&childName.compareTo("delete")==0){


            fragmentTransaction.replace(R.id.nav_host_fragment_content_main, DeleteLessonFragment.newInstance(teacherId)).commit();
        }
        if(groupName.compareTo("Lesson")==0&&childName.compareTo("update")==0){

            fragmentTransaction.replace(R.id.nav_host_fragment_content_main, UpdateLessonFragment.newInstance(teacherId)).commit();
        }
        if(groupName.compareTo("StudentLessons")==0){

            fragmentTransaction.replace(R.id.nav_host_fragment_content_main, StudentLessonFragmentTest.newInstance(teacherId)).commit();
        }
        if(groupName.compareTo("Class")==0&&childName.compareTo("delete")==0){

            DeleteClass deleteClass=new DeleteClass();
            fragmentTransaction.replace(R.id.nav_host_fragment_content_main,deleteClass).commit();

        }

        if(groupName.compareTo("Student")==0&&childName.compareTo("insert")==0){

            InsertStudent insertStudent=new InsertStudent();
            fragmentTransaction.replace(R.id.nav_host_fragment_content_main,insertStudent).commit();

        }
        if(groupName.compareTo("Student")==0&&childName.compareTo("delete")==0){

            DeleteStudent deleteStudent=new DeleteStudent();
            fragmentTransaction.replace(R.id.nav_host_fragment_content_main,deleteStudent).commit();

        }

        if(groupName.compareTo("Student")==0&&childName.compareTo("update")==0){

            UpdateStudent updateStudent=new UpdateStudent();
            fragmentTransaction.replace(R.id.nav_host_fragment_content_main,updateStudent).commit();

        }


    }
    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.expandGroup(i);
        }
    }
    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.collapseGroup(i);
        }
    }
    //load some initial data into out list
    private void loadData(){


        //----------------Lesson-------------------------
        addProduct("Lesson","insert");
        addProduct("Lesson","delete");
        addProduct("Lesson","update");

        //----------------QuranPart-------------------------
        addProduct("Quran Part","insert & delete");

        //----------------StudentLesson-------------------------
        addGroup("StudentLessons");

    }
    private void addGroup(String department) {

        int groupPosition = 0;

        //check the hash map if the group already exists
        GroupInfo headerInfo = subjects.get(department);
        //add the group if doesn't exists
        if (headerInfo == null) {
            headerInfo = new GroupInfo();
            headerInfo.setName(department);
            subjects.put(department, headerInfo);
            deptList.add(headerInfo);
        }
    }

    //here we maintain our products in various departments
    private int addProduct(String department, String product){

        int groupPosition = 0;

        //check the hash map if the group already exists
        GroupInfo headerInfo = subjects.get(department);
        //add the group if doesn't exists
        if(headerInfo == null){
            headerInfo = new GroupInfo();
            headerInfo.setName(department);
            subjects.put(department, headerInfo);
            deptList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<ChildInfo> productList = headerInfo.getProductList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        ChildInfo detailInfo = new ChildInfo();
        detailInfo.setSequence(String.valueOf(listSize));
        detailInfo.setName(product);
        productList.add(detailInfo);
        //find the group position inside the list
        groupPosition = deptList.indexOf(headerInfo);
        return groupPosition;
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}