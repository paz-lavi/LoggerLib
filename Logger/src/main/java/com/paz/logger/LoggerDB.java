package com.paz.logger;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.paz.logger.data.UserId;
import com.paz.taskrunnerlib.task_runner.RunnerCallback;
import com.paz.taskrunnerlib.task_runner.TaskRunner;

public class LoggerDB {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "dd";


    protected void uploadLogsToDB(Context context, String devKey, SessionDocument doc) {
        TaskRunner<Void> ts = new TaskRunner<>();
        ts.executeAsync(new RunnerCallback<Void>() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public Void call() throws Exception {
                UserId user = new UserId(context, devKey);
                doc.setEzLogId(user.getUserId()).setSessionCounter(user.getSessionCounter()).updateDeviceData();
                return null;
            }

            @Override
            public void onPostExecute(Void result) {
                saveSession(devKey, doc);
            }
        });


    }

    private void saveSession(String devKey, SessionDocument doc) {
        // Add a new document with a generated ID

        db.collection("users").document(devKey).collection("logs_sessions")
                .add(doc)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        android.util.Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        android.util.Log.w(TAG, "Error adding document", e);
                    }
                });
    }



}
