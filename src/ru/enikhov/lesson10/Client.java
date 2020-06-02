package ru.enikhov.lesson10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Client {
    public static boolean quit = false;

    public static void main(String[] args) {
        String host = "localhost";

        System.out.println("Usage: UDPClient " + "Now using host = " + host);
        try {
            InetAddress ia = InetAddress.getByName(host);
            SenderThread sender = new SenderThread(ia);
            new Thread(sender).start();
            ReceiverThread receiver = new ReceiverThread(sender.getClientSocket());
            new Thread(receiver).start();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }
}

class SenderThread implements Runnable {
    private InetAddress iaServer;
    private DatagramSocket clientSocket;


    public SenderThread(InetAddress ia) throws SocketException {
        this.iaServer = ia;
        this.clientSocket = new DatagramSocket();
        this.clientSocket.connect(iaServer, Server.SERVER_PORT);
    }

    public DatagramSocket getClientSocket() {
        return clientSocket;
    }

    public void sendMessage(String message) {
        try {
            byte[] sendData = new byte[1024];
            sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData,
                    sendData.length,
                    iaServer,
                    Server.SERVER_PORT);
            clientSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String clientName = "Bob";
            sendMessage(clientName);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String clientMessage = br.readLine();
                if (clientMessage.equals("quit")) {
                    Client.quit = true;
                    break;
                }
                sendMessage(clientMessage);
            }
            if (Client.quit) {
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReceiverThread implements Runnable {
    private DatagramSocket clientSocket;

    public ReceiverThread(DatagramSocket ds) {
        this.clientSocket = ds;
    }

    @Override
    public void run() {
        byte[] receiveData = new byte[1024];
        while (!Client.quit) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                clientSocket.receive(receivePacket);
                String serverReplay = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Сервер => " + serverReplay + "\n");
            } catch (IOException e) {
                System.out.println("Клиент покинул чат.");
            }
        }
    }
}
