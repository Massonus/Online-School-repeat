package org.massonus.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

    public void start() {

        String hostName = "localhost";
        int portNumber = 6208;

        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(hostName, portNumber);
            SocketChannel socketChannel = SocketChannel.open(inetSocketAddress);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            while ((stdIn.readLine()) != null) {
                byteBuffer.clear();
                byteBuffer.put("info".getBytes());
                byteBuffer.flip();

                socketChannel.write(byteBuffer);

                byteBuffer.clear();
                socketChannel.read(byteBuffer);
                System.out.println(new String(byteBuffer.array(), 0, byteBuffer.position()));

            }
            socketChannel.close();
        } catch (IOException e) {
            Arrays.stream(e.getStackTrace()).forEach(System.out::println);
        }
    }

}
