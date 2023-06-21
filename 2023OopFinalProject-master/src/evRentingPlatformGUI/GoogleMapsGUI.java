package evRentingPlatformGUI;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoogleMapsGUI extends JFrame {
    private static  JFXPanel jfxPanel;
    private final JButton location1Button;
    private final JButton location2Button;

    public GoogleMapsGUI() {
        setTitle("Google Maps");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        jfxPanel = new JFXPanel();
        add(jfxPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        location1Button = new JButton("Location 1");
        location2Button = new JButton("Location 2");

        buttonPanel.add(location1Button);
        buttonPanel.add(location2Button);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button listeners
        location1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadMap(25.0409421, 121.5005733);
            }
        });

        location2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadMap(25.0319421, 121.4005733);
            }
        });

        // Start JavaFX on the Swing EDT
        SwingUtilities.invokeLater(this::initializeJavaFX);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeJavaFX() {
        Platform.runLater(() -> {
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            webEngine.load("https://www.google.com/maps");
            Scene scene = new Scene(webView);
            jfxPanel.setScene(scene);
        });
    }

    public static void loadMap(double lat, double lng) {
        Platform.runLater(() -> {
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            webEngine.load("https://www.google.com/maps?q=" + lat + "," + lng);

            Scene scene = new Scene(webView);
            jfxPanel.setScene(scene);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GoogleMapsGUI::new);
    }
}
