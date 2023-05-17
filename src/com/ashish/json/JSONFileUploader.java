package com.ashish.json;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("serial")
public class JSONFileUploader extends JFrame implements ActionListener {
    private JTextArea textArea;

    public JSONFileUploader() {
        setTitle("JSON File Uploader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton uploadButton = new JButton("Upload JSON File");
        uploadButton.addActionListener(this);
        panel.add(uploadButton, BorderLayout.NORTH);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Upload JSON File")) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String jsonContent = readJSONFile(selectedFile);
                textArea.setText(jsonContent);
            }
        }
    }

    private String readJSONFile(File file) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JSONFileUploader uploader = new JSONFileUploader();
            uploader.setVisible(true);
        });
    }
}
