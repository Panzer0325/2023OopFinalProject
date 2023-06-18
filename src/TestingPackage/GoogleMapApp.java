package TestingPackage;

import java.awt.BorderLayout;
import javax.swing.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class GoogleMapApp extends JFrame {
    private JTextField latitudeField;
    private JTextField longitudeField;
    private JButton showButton;
    private WebView webView;

    public GoogleMapApp() {
        setTitle("Google Map Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        latitudeField = new JTextField();
        longitudeField = new JTextField();
        showButton = new JButton("Show on Map");

        showButton.addActionListener(e -> {
            String latitude = latitudeField.getText();
            String longitude = longitudeField.getText();
            showLocationOnMap(latitude, longitude);
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Latitude:"));
        inputPanel.add(latitudeField);
        inputPanel.add(new JLabel("Longitude:"));
        inputPanel.add(longitudeField);
        inputPanel.add(showButton);

        JFXPanel mapPanel = new JFXPanel();
        add(inputPanel, BorderLayout.NORTH);
        add(mapPanel, BorderLayout.CENTER);

        Platform.runLater(() -> {
            webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            mapPanel.setScene(new Scene(webView));
            webEngine.load("https://maps.google.com");
        });
    }

    private void showLocationOnMap(String latitude, String longitude) {
        Platform.runLater(() -> {
            String script = String.format("map.setCenter(new google.maps.LatLng(%s, %s));", latitude, longitude);
            webView.getEngine().executeScript(script);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GoogleMapApp app = new GoogleMapApp();
            app.setVisible(true);
        });
    }
}

