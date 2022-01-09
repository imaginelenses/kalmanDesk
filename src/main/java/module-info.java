module com.example.kalmandesk {
    requires javafx.controls;
    requires javafx.fxml;
    requires netty.socketio;
    requires java.desktop;
    requires com.google.zxing;
    requires com.google.zxing.javase;


    opens com.imaginelenses.kalmandesk to javafx.fxml;
    exports com.imaginelenses.kalmandesk;
}