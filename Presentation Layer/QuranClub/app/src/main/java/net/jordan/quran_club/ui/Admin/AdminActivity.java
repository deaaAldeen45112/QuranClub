package net.jordan.quran_club.ui.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import net.jordan.quran_club.R;
import net.jordan.quran_club.model.admin.ResponseAdmin;
import net.jordan.quran_club.ui.Admin.crud.classes.delete.DeleteClass;
import net.jordan.quran_club.ui.Admin.crud.classes.insert.InsertClass;
import net.jordan.quran_club.ui.Admin.crud.classes.update.UpdateClass;
import net.jordan.quran_club.ui.Admin.crud.student.delete.DeleteStudent;
import net.jordan.quran_club.ui.Admin.crud.student.insert.InsertStudent;
import net.jordan.quran_club.ui.Admin.crud.student.update.UpdateStudent;
import net.jordan.quran_club.ui.Admin.crud.teacher.delete.DeleteTeacher;
import net.jordan.quran_club.ui.Admin.crud.teacher.insert.InsertTeacher;

import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import net.jordan.quran_club.adapter.CustomAdapter;
import net.jordan.quran_club.model.exbandableListView.ChildInfo;
import net.jordan.quran_club.model.exbandableListView.GroupInfo;
import net.jordan.quran_club.ui.Admin.crud.teacher.update.UpdateTeacher;
import net.jordan.quran_club.ui.login.LoginActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AdminActivity extends AppCompatActivity {
    AdminViewModel adminViewModel;
    private AppBarConfiguration mAppBarConfiguration;
    private HashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
    private ArrayList<GroupInfo> deptList = new ArrayList<GroupInfo>();
    private CustomAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        adminViewModel = new ViewModelProvider(this).get(AdminViewModel.class);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedQuranClub", MODE_PRIVATE);
        int userloginId = sharedPreferences.getInt("userlogin_Id", 0);
        adminViewModel.getAdminsByUserloginId(userloginId);
        adminViewModel.getAdminLiveDate().observe(this, new Observer<ResponseAdmin>() {
            @Override
            public void onChanged(ResponseAdmin responseAdmin) {

                selectItemFromNav("Student", "insert");

            }
        });


        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace Admin with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Admin");
        setSupportActionBar(toolbar);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        TextView textViewUserName=findViewById(R.id.textView_username);
        String fullName=sharedPreferences.getString("full_name","");
        textViewUserName.setText(fullName);


        // add data for displaying in expandable list view
        loadData();
        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);
        //logout
        Button buttonLogOut = findViewById(R.id.button_logout);
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutDialog();

            }
        });

        // create the adapter by passing your ArrayList data
        listAdapter = new CustomAdapter(AdminActivity.this, deptList);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);

        // setOnChildClickListener listener for child row click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //get the child info
                ChildInfo detailInfo = headerInfo.getProductList().get(childPosition);


                selectItemFromNav(headerInfo.getName(), detailInfo.getName());
                return false;
            }
        });
        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);


                return false;
            }
        });


    }
    private void logoutDialog() {

        AlertDialog.Builder alertDialogLogout=new AlertDialog.Builder(AdminActivity.this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout");
        alertDialogLogout.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

               getSharedPreferences("sharedQuranClub",MODE_PRIVATE).edit().clear().commit();
               startActivity(new Intent(AdminActivity.this, LoginActivity.class));
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

        if(groupName.compareTo("Teacher")==0&&childName.compareTo("insert")==0){

            InsertTeacher insertTeacher=new InsertTeacher();
           fragmentTransaction.replace(R.id.nav_host_fragment_content_main,insertTeacher).commit();
        }
        if(groupName.compareTo("Teacher")==0&&childName.compareTo("delete")==0){

            DeleteTeacher deleteTeacher=new DeleteTeacher();
           fragmentTransaction.replace(R.id.nav_host_fragment_content_main,deleteTeacher).commit();
        }
        if(groupName.compareTo("Teacher")==0&&childName.compareTo("update")==0){

            UpdateTeacher updateTeacher=new UpdateTeacher();
           fragmentTransaction.replace(R.id.nav_host_fragment_content_main,updateTeacher).commit();
        }
        if(groupName.compareTo("Class")==0&&childName.compareTo("insert")==0){

            InsertClass insertClass=new InsertClass();
           fragmentTransaction.replace(R.id.nav_host_fragment_content_main,insertClass).commit();
        }
        if(groupName.compareTo("Class")==0&&childName.compareTo("update")==0){

            UpdateClass updateClass=new UpdateClass();
           fragmentTransaction.replace(R.id.nav_host_fragment_content_main,updateClass).commit();
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


     //----------------Student-------------------------
        addProduct("Student","insert");
        addProduct("Student","delete");
        addProduct("Student","update");
    //----------------Teacher-------------------------
        addProduct("Teacher","insert");
        addProduct("Teacher","delete");
        addProduct("Teacher","update");

    //----------------Class-------------------------
        addProduct("Class","insert");
        addProduct("Class","delete");
        addProduct("Class","update");
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