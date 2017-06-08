package com.example.lucas.control_remote;

import android.os.AsyncTask;

/**
 * Created by Lucas on 07/06/2017.
 */

class RetrieveFeedTask extends AsyncTask<String, Void, Integer> {
    private Exception exception;

    protected Integer doInBackground(String... params) {
        try {
            String command = params[0];
            //TODO: remove the switch case
            switch (command){
                case "mouse":
                    mouseCommand(params);
                    break;
                case "click":
                    clickCommand(params);
                    break;
                case "right":
                    rightCommand(params);
                    break;
                case "left":
                    leftCommand(params);
                    break;
                case "up":
                    upCommand(params);
                    break;
                case "down":
                    downCommand(params);
                    break;
                case "increase":
                    increaseAudioCommand(params);
                    break;
                case "lower":
                    lowerAudioCommand(params);
                    break;
            }

        } catch (Exception e) {
            this.exception = e;
        }

        return 0;
    }

    protected void onPostExecute(Integer feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }

    private void mouseCommand(String... params) {
        String ip = params[1];
        String moveX = params[2];
        String moveY = params[3];

        client.Client.mouseCommandEndPoint(ip, moveX, moveY);
    }

    private void clickCommand(String... params) {
        String ip = params[1];

        client.Client.clickCommandEndPoint(ip);
    }

    private void leftCommand(String... params) {
        String ip = params[1];

        client.Client.leftCommandEndPoint(ip);
    }

    private void rightCommand(String... params) {
        String ip = params[1];

        client.Client.rightCommandEndPoint(ip);
    }

    private void lowerAudioCommand(String... params) {
        String ip = params[1];

        client.Client.lowerAudioCommandEndPoint(ip);
    }

    private void upCommand(String... params) {
        String ip = params[1];

        client.Client.upCommandEndPoint(ip);
    }

    private void downCommand(String... params) {
        String ip = params[1];

        client.Client.downCommandEndPoint(ip);
    }

    private void increaseAudioCommand(String... params) {
        String ip = params[1];

        client.Client.increaseAudioCommandEndPoint(ip);
    }
}
