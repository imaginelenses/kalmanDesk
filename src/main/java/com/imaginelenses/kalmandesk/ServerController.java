package com.imaginelenses.kalmandesk;

import com.google.zxing.WriterException;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class ServerController {

    private static String address;
    public Label title;
    public ImageView qrCode;
    public Hyperlink link;
    public Label footer;


    @FXML
    public void initialize() throws IOException, WriterException {
        gen_qr();
        System.out.println(address);
        InputStream imageData = new FileInputStream("./qrCode.png");
        Image image = new Image(imageData);
        qrCode.setImage(image);
        link.setText(address);
    }

    @FXML
    protected void onClick() throws URISyntaxException, IOException {
//        Desktop.getDesktop().browse(new URI(address));
    }

    private static void gen_qr() throws WriterException, IOException {
        address = "http://" + ServerApplication.ip + ":8080";
        String path = "./qrCode.png";

        BitMatrix matrix = new MultiFormatWriter()
                .encode(address, BarcodeFormat.QR_CODE, 500, 500);

        MatrixToImageWriter.writeToPath(matrix, "png", Paths.get(path));
    }
}