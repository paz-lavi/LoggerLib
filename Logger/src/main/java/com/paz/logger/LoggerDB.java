package com.paz.logger;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.paz.logger.data.UserId;
import com.paz.taskrunnerlib.task_runner.RunnerCallback;
import com.paz.taskrunnerlib.task_runner.TaskRunner;

import static com.paz.logger.data.Constants.API_KEY;
import static com.paz.logger.data.Constants.FIREBASE_APP_NAME;
import static com.paz.logger.data.Constants.LIB_APP_ID;
import static com.paz.logger.data.Constants.PROJECT_ID;
import static com.paz.logger.data.Constants.STORAGE_BUCKET;

public class LoggerDB {
    private Context context;
    private FirebaseFirestore db;
    private FirebaseApp loggerApp;
    private static final String TAG = "LoggerDB";

    public LoggerDB(Context context) {
        this.context = context;
        initFirebaseInstance();
    }

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


    private void initFirebaseInstance() {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setProjectId(PROJECT_ID)
                .setApplicationId(LIB_APP_ID)
                .setApiKey(API_KEY)
                .setStorageBucket(STORAGE_BUCKET)
                .setDatabaseUrl("<your DB url that ends in 'firebaseio.com/' ")
                .build();

        try {
            FirebaseApp.initializeApp(context.getApplicationContext(), options, FIREBASE_APP_NAME);
        } catch (Exception e) {
            Log.d(TAG, "initFirebaseInstance: ");
        }

        loggerApp = FirebaseApp.getInstance(FIREBASE_APP_NAME);
        db = FirebaseFirestore.getInstance(loggerApp);
    }


}
