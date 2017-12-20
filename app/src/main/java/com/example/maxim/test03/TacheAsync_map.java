package com.example.maxim.test03;

import android.os.AsyncTask;


import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class TacheAsync_map extends AsyncTask<Void,Void,Boolean> {


    int[][] table=null;
    public int[][] getTable(){
        return table;
    }

    @Override

    protected void onPreExecute() {

    }

    @Override
    protected void onProgressUpdate(Void... progress) {

    }

    private int[][] CreateTable(StringBuffer result) {
        String str= result.toString();
        String[] strTable = str.split(";");
        String[][] doubleArray= new String[strTable.length][];
        table=new int[strTable.length][];
        for(int i=0;i<strTable.length;i++) {
            doubleArray[i] = strTable[i].split(",");
        }
        for(int i=0;i<doubleArray.length;i++){
            System.out.println("b2:"+i+"/"+strTable.length);
            table[i]=new int[doubleArray[i].length];
            for(int j=0;j<doubleArray[i].length;j++){
                System.out.println("b3:"+j+"/"+doubleArray[i].length);

                table[i][j]=Integer.parseInt(doubleArray[i][j]);
            }
        }
        for(int i=0;i<doubleArray.length;i++){

            for(int j=0;j<doubleArray[i].length;j++){
                System.out.print(table[i][j]);
            }
            System.out.println();
        }

        return table;
    }

    @Override
    protected Boolean doInBackground(Void... data) {
        StringBuffer result = new StringBuffer();
        URL url = null;
        table=null;
        try {
            url = new URL("http://10.0.2.2:9191/android/map.php");
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }
        if (url != null) {
            try {

                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoOutput(true);
                urlConn.setDoInput(true);
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                String inputLine;
                int lineCount = 0; // limit the lines for the example
                while ((lineCount < 10) && ((inputLine = in.readLine()) != null)) {
                    lineCount++;
                    result.append(inputLine);
                }
                System.out.println(result);
                table=CreateTable(result);
                in.close();
                urlConn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

        }
        return true;
    }

}




