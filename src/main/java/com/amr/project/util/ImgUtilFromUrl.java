package com.amr.project.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ImgUtilFromUrl {
    public static byte[] toByteArray(String url) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            int byteBlock;
            while ((byteBlock = inputStream.read()) != -1) {
                byteArrayOutputStream.write((byte) byteBlock);
            }
        }
        catch (IOException e) {
                e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
