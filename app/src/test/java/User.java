import android.util.Log;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class User {
    private String fName;
    private String lName;
    private String eMail;
    private String role;

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

    public User(String fName, String lName, String eMail, String role) {
        this.fName = fName;
        this.lName = lName;
        this.eMail = eMail;
        this.role = role;
    }

    public void addNewUser(FirebaseFirestore db){
        String documentID = this.geteMail().replaceAll("\\s", "");

        // on below line are are deleting a new badge to the db
        db.collection("users").document(documentID).set(this)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    public void onSuccess(Void unused) {
                        Log.d("NEW_USER", "Successfully added new user to db: " + this.toString());
                    }
                });
    }

    public void deleteUser(FirebaseFirestore db){
        String documentID = this.geteMail().replaceAll("\\s", "");

        // on below line are are deleting a user to the db
        db.collection("users").document(documentID).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    public void onSuccess(Void unused) {
                        Log.d("DELETE_USER", "Successfully deleted user from db: " + this.toString());
                    }
                });
    }



}
