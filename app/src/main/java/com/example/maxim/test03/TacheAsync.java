package com.example.maxim.test03;

import android.os.AsyncTask;



public class TacheAsync extends AsyncTask<Void,Void,int[][]> {

    private int table[][] = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,2,2,2,1,2,2,1,1,1,1,1},
            {1,1,1,1,2,2,2,1,2,2,1,1,1,1,1},
            {1,1,1,1,2,2,2,2,2,2,1,1,1,1,1},
            {1,1,1,1,2,2,2,1,2,2,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onProgressUpdate(Void... progress) {

    }

    @Override
    protected int [][]doInBackground(Void... data) {

        return null;
    }

    @Override
    protected void onPostExecute(int[][] result) {

    }




}
