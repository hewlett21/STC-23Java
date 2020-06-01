package ru.enikhov.lesson10;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public static final Integer SERVER_PORT = 4999;
    private static Map<Integer, String> clientMap = new HashMap<>();

    public static void main(String[] args) {
        int clientPort;
        String clientName;
        try {
            DatagramSocket socket = new DatagramSocket(SERVER_PORT);
            System.out.println("Сервер стартовал ...\n");
            byte[] inPacket = new byte[1024];
            DatagramPacket incoming = new DatagramPacket(inPacket, inPacket.length);
            while (true) {
                socket.receive(incoming);
                byte[] data = incoming.getData();
                InetAddress clientIP = incoming.getAddress();
                clientPort = incoming.getPort();
                String s = new String(data, 0, incoming.getLength());
                if (!clientMap.containsKey(clientPort)) {
                    clientMap.put(clientPort, s);
                    System.out.println("К серверу подключился " + s + "-" + clientPort);
                } else {
                    clientName = clientMap.get(clientPort) + "-" + clientPort;
                    System.out.println("Сервер получил " + s + " от " + clientName);
                    String returnMessage = "Клиент " + clientName + " сообщает: " + s;

                    byte[] sendData = new byte[1024];
                    sendData = returnMessage.getBytes();
                    for (Map.Entry<Integer, String> clientItem : clientMap.entrySet()) {
                        if (clientItem.getKey() != clientPort) {
                            DatagramPacket sendPacket = new DatagramPacket(sendData,
                                    sendData.length,
                                    clientIP,
                                    clientItem.getKey());
                            socket.send(sendPacket);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("IOException " + e);
        }
    }
}
