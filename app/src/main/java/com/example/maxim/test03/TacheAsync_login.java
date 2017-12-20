package com.example.maxim.test03;

import android.os.AsyncTask;



import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;


public class TacheAsync_login extends AsyncTask<Void,Void,String> {

    private String ident=null;
    private String pass=null;
    private String code="1000";

    public void setIdent(String ident){
        this.ident=ident;
    }
    public void setPass(String pass){
        this.pass=pass;
    }
    public String getCode(){
        return code;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onProgressUpdate(Void... progress) {

    }

    @Override
    protected String doInBackground(Void... data) {
        URL url;
        try {
            url = new URL("http://10.0.2.2:9191/android/login.php");

            Map<String,Object> params = new LinkedHashMap<>();
            params.put("ident", ident);
            params.put("password", pass);

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder result= new StringBuilder();
        int lineCount = 0; // limit the lines for the example
        while ((lineCount < 10) && ((inputLine = in.readLine()) != null)) {
            lineCount++;
            result.append(inputLine);
        }
        code=result.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }


}




