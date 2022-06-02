package com.kunal52;

import com.kunal52.exception.PairingException;
import com.kunal52.remote.Remotemessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;

public class Main {
    public static void main(String[] args) throws GeneralSecurityException, IOException, InterruptedException, PairingException {
        AndroidRemoteTv androidRemoteTv = new AndroidRemoteTv();
        androidRemoteTv.connect("192.168.1.8", new AndroidRemoteTv.AndroidTvListener() {
            @Override
            public void onSessionCreated() {

            }

            @Override
            public void onSecretRequested() {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));

                // Reading data using readLine
                try {
                    String name = reader.readLine();
                    androidRemoteTv.setSecret(name);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onPaired() {

            }

            @Override
            public void onConnectingToRemote() {

            }

            @Override
            public void onConnected() {
                System.out.println("Connected");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                androidRemoteTv.sendCommand(Remotemessage.RemoteKeyCode.KEYCODE_POWER, Remotemessage.RemoteDirection.SHORT);

            }

            @Override
            public void onDisconnect() {

            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
