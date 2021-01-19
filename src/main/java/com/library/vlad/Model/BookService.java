package com.library.vlad.Model;

import com.library.vlad.Model.Book;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;


@Service
public  class BookService {

    public static final String COL_NAME = "users";


    public void addBookToCloud(Book book) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef=dbFirestore.collection("Books").document(book.getName());
        ApiFuture<WriteResult> result=docRef.set(book);
        System.out.println("Update time : " + result.get().getUpdateTime());

    }

    public String saveBookDetails(Book book) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(book.getName()).set(book);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Book getBookDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Book book = null;

        if (document.exists()) {
            book = document.toObject(Book.class);
            return book;
        } else {
            return null;
        }
    }

    public String updateBookDetails(Book person) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(person.getName()).set(person);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteBook(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(name).delete();
        return "Document with Book ID " + name + " has been deleted";
    }


}
