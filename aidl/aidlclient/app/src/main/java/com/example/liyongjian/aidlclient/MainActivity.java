package com.example.liyongjian.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.liyongjian.aidlserver.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection mConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start();
    }

    private void start() {
        Log.e("TAG1", "start");
        Intent intent = new Intent("com.example.liyongjian.aidlclient.AIDL_SERVICE");
        intent.setPackage("com.example.liyongjian.aidlclient");
        if (mConnection == null) {
            Log.e("TAG1", "mConnection");
            mConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Log.e("TAG1", "onServiceConnected");
                    IBookManager iBookManager = IBookManager.Stub.asInterface(service);
                    try {
                        Log.e("TAG1", "---->ADD book1:");
                        iBookManager.addBook(new Book(3,"three"));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    try {
                        List<Book> bookList = iBookManager.getBookList();
                        for (Book book : bookList) {
                            Log.e("TAG1", "---->book1:" + book.toString());
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            };
        }
        bindService(intent,mConnection,BIND_AUTO_CREATE);
    }
}
