package com.example.lumberjackrewards;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;


public class UserModel {
    private String fName;
    private String lName;
    private String eMail;
    private String role;
    private String password;

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserModel(String fName, String lName, String eMail, String role, String password) {
        this.fName = fName;
        this.lName = lName;
        this.eMail = eMail;
        this.role = role;
        this.password = password;
    }

    public void addNewUser(FirebaseFirestore db){
        String documentID = this.geteMail().replaceAll("\\s", "");

        // on below line are are adding a new user to the db
        db.collection("users").document(documentID).set(this)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("NEW_USER", "Successfully added new user to db: " + this.toString());
                    }
                });
    }

    public void deleteUser(FirebaseFirestore db){
        String documentID = this.geteMail().replaceAll("\\s", "");

        // on below line are deleting a user to the db
        db.collection("users").document(documentID).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("DELETE_USER", "Successfully deleted user from db: " + this.toString());
                    }
                });
    }

    public void getPassword(FirebaseFirestore db){
        String documentID = this.geteMail().replaceAll("\\s", "");
        CollectionReference usersRef = db.collection("users");
        Query query = usersRef.whereEqualTo("email",this.eMail);

        db.collection("users")
                .whereEqualTo("email",this.eMail)
                .get()
                .addOnCompleteListener(new onCompleteListener<QuerySnapshot>()) {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                }
        }
    }
}
