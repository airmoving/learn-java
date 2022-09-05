package com.mingshashan.learn.io.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

public class FileChannelTest {

    public static void main(String[] args) {

        final String filePath = "/Users/kaifazhongxin/build/02.learn/01.code/01.java/lean-java/src/main/resources/data/string.txt";

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            FileChannel fileChannel = fileInputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(2048);
            fileChannel.read(buffer);
            System.out.println(new String(buffer.array(), "UTF-8"));

            String newAppend = "append: " + new Date().toString();
            buffer.flip();
            buffer.put(newAppend.getBytes());
            fileChannel.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
