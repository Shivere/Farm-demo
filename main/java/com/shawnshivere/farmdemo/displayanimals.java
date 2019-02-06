package com.shawnshivere.farmdemo;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class displayanimals extends AppCompatActivity implements AnimalsAdapter.AnimalsAdapterListener{

    private static final String TAG = displayanimals.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Animal> animalList;
    private AnimalsAdapter mAdapter;
    private SearchView searchView;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayanimals);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);

        recyclerView = findViewById(R.id.recycler_view);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        animalList = new ArrayList<>();
        mAdapter = new AnimalsAdapter(this,animalList,this);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);

//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                Animal animal = animalList.get(position);
//                Toast.makeText(getApplicationContext(), animal.getName() + " is selected!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//                Animal animal = animalList.get(position);
//                Snackbar.make(coordinatorLayout, "Do you want to delete " + animal.getName() + "?", Snackbar.LENGTH_LONG)
//                        .setAction("Delete", null).show();
//            }
//        }));

        prepareAnimalData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This adds animals", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void prepareAnimalData() {
        Animal animal = new Animal(1,"Cow",3,"Female","cow10", R.drawable.cow);
        animalList.add(animal);
        animal = new Animal(2,"Cow1",2,"Female","cow9", R.drawable.cow1);
        animalList.add(animal);
        animal = new Animal(3,"Cow2",3,"Female","cow8", R.drawable.cow2);
        animalList.add(animal);
        animal = new Animal(4,"Cow3",2,"Female","cow7", R.drawable.cow3);
        animalList.add(animal);
        animal = new Animal(5,"Cow4",4,"Female","cow6", R.drawable.cow4);
        animalList.add(animal);
        animal = new Animal(6,"Cow5",2,"Female","cow5", R.drawable.cow5);
        animalList.add(animal);
        animal = new Animal(7,"Cow6",1,"Female","cow4", R.drawable.cow6);
        animalList.add(animal);
        animal = new Animal(8,"Cow7",2,"Female","cow3", R.drawable.cow7);
        animalList.add(animal);
        animal = new Animal(9,"Cow8",3,"Female","cow2", R.drawable.cow8);
        animalList.add(animal);
        animal = new Animal(10,"Cow9",5,"Female","cow1", R.drawable.cow9);
        animalList.add(animal);
        animal = new Animal(11,"Cow10",3,"Female","cow", R.drawable.cow10);
        animalList.add(animal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onAnimalSelected(Animal animal) {
        Toast.makeText(getApplicationContext(), "Selected: " + animal.getName() + ", " + animal.getGender(), Toast.LENGTH_LONG).show();

    }
}
