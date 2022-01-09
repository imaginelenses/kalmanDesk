package com.imaginelenses.kalmandesk;

import com.corundumstudio.socketio.listener.*;
import com.corundumstudio.socketio.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ServerApplication extends Application {

    public static String ip;

    public static void main(String[] args) throws InterruptedException {

        ip = get_ip();
        Configuration config = new Configuration();
        config.setHostname(ip);
        config.setPort(8080);

        final SocketIOServer server = new SocketIOServer(config);

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("Client has Connected!");
            }
        });

        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient client) {
                System.out.println("Client has Disconnected!");
            }
        });

        server.addEventListener("move_event", DataObject.class, new DataListener<DataObject>() {
            @Override
            public void onData(SocketIOClient client, DataObject data, AckRequest ackRequest) {
                System.out.println(data.x);
                data.moveCur(data.x, data.y);

            }
        });

        server.start();
        launch();
        server.stop();

        File qrCode = new File("./qrCode.png");
        qrCode.delete();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ServerApplication.class.getResource("server-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Kalman");
        InputStream imageData = new FileInputStream(ServerApplication.class.getResource("kalman.png").getPath());
        stage.getIcons().add(new Image(imageData));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private static String get_ip() {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("imaginelenses.com", 80));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String ip = String.valueOf(socket.getLocalAddress()).substring(1);
        return ip;
    }

}
