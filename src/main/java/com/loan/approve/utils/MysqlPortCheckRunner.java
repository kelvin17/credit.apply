package com.loan.approve.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@Component
public class MysqlPortCheckRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        String host = "loan.mysql.database.azure.com";
        int port = 3306;
        int timeoutMs = 3000;

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeoutMs);
            System.out.println("MySQL port is reachable!");
        } catch (IOException e) {
            System.err.println("Cannot reach MySQL port!");
            e.printStackTrace();
            // 可以选择 System.exit(1) 强制停止容器
            System.exit(1);
        }
    }
}
