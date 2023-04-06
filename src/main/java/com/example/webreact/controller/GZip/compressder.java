package com.example.webreact.controller.GZip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class compressder {
    // Method to compress byte array using GZIP
    public byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(data.length);
        try (GZIPOutputStream zipStream = new GZIPOutputStream(byteStream)) {
            zipStream.write(data);
        }
        return byteStream.toByteArray();
    }
}
