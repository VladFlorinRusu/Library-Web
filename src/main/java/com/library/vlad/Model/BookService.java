package com.library.vlad.Model;

import com.google.cloud.firestore.*;
import com.library.vlad.Model.*;
import com.google.api.core.ApiFuture;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Service
public class BookService {

    public static final String COL_NAME = "users";


    public <T> void addDataToCloud(T t) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = null;
        if (t instanceof Book) {

            docRef = dbFirestore.collection("Books").document(((Book) t).getName());
        } else if (t instanceof User) {
            docRef = dbFirestore.collection("Users").document(((User) t).getEmail());
        }

        ApiFuture<WriteResult> result = docRef.set(t);
        System.out.println("Update time : " + result.get().getUpdateTime());

    }

    public ArrayList<Book> getBooks() throws ExecutionException, InterruptedException {
        // asynchronously retrieve all books
        ArrayList<Book> books = new ArrayList<Book>();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> query = db.collection("Books").get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            books.add(new Book(document.getString("name"), document.getString("author")));


        }

        return books;
    }

    public User findUser(String email, String password) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference users = db.collection("Users");
        Query query = users.whereEqualTo("email", email).whereEqualTo("pass", password);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        if (querySnapshot != null) {
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                return new User(document.getString("name"),document.getString("email"),document.getString("pass"));
            }

        }
        return null;



    }
}