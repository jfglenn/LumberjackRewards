package com.example.lumberjackrewards;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class BadgesActivity extends AppCompatActivity {
    private EditText itemEdt;
    private ArrayList<String> lngList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badges);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.navigation_badges);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId())
            {
                case R.id.navigation_home:
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    //overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_badges:
                    break;
                case R.id.navigation_settings:
                    startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                    //overridePendingTransition(0,0);
                    break;
            }
            return true;
        });

        // on below line we are accessing Cloud Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // on below line we are initializing our variables.
        // on below line we are creating variables.
        ListView languageLV = findViewById(R.id.idLVLanguages);
        Button addBtn = findViewById(R.id.idBtnAdd);
        Button removeBtn = findViewById(R.id.idBtnRmv);
        itemEdt = findViewById(R.id.idEdtItemName);
        lngList = new ArrayList<>();

        // on below line we are adding items to our list
        lngList.add("C++");
        lngList.add("Python");

        // on the below line we are initializing the adapter for our list view.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lngList);

        // on below line we are setting adapter for our list view.
        languageLV.setAdapter(adapter);

        // on below line we are adding click listener for our button.
        addBtn.setOnClickListener(v -> {
            // on below line we are getting text from edit text
            String item = itemEdt.getText().toString();

            // on below line we are checking if item is not empty
            if (!item.isEmpty()) {

                // on below line we are splitting String item to
                // populate BadgeItemModel
                String[] badgeItemAttributes = item.split(", ");

                // on below line we are populating BadgeItemModel
                // int badgeID, String description, String name, String icon
                BadgeItemModel newBadge = new BadgeItemModel(Integer.parseInt(badgeItemAttributes[0]), badgeItemAttributes[1], badgeItemAttributes[2], badgeItemAttributes[3]);

                // on below line we are adding badge to database
                newBadge.addNewBadgeItem(db);

                // on below line we are adding item to our list.
                lngList.add(item);

                // on below line we are notifying adapter
                // that data in list is updated to
                // update our list view.
                adapter.notifyDataSetChanged();
            }

        });

        removeBtn.setOnClickListener(v -> {
            // on below line we are getting text from edit text
            String item = itemEdt.getText().toString();

            // on below line we are checking if item is not empty
            if (!item.isEmpty()) {

                // on below line we are adding item to our list.
                lngList.remove(item);

                // on below line we are notifying adapter
                // that data in list is updated to
                // update our list view.
                adapter.notifyDataSetChanged();
            }

        });

    }

}