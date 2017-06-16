package com.example.lucas.control_remote;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

/**
 * Created by Lucas on 16/06/2017.
 */

public class Store {
    private static final String _fileName = "ipAddressStore";

    public static void saveIpAddress(String ipAddress, Context context){
        try{
            FileOutputStream fos = context.openFileOutput(_fileName, Context.MODE_PRIVATE);
            fos.write(ipAddress.getBytes());
            fos.close();
        }catch (Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static String getIpAddress(Context context){
        String ipAddress = "";

        try{
            int c;
            String[] files = context.fileList();

            if(Arrays.asList(files).contains(_fileName)){
                FileInputStream fis = context.openFileInput(_fileName);

                while( (c = fis.read()) != -1){
                    ipAddress = ipAddress + Character.toString((char)c);
                }

                fis.close();
            }
        }catch (Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return ipAddress;
    }
}
