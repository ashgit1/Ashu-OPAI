package com.ashish.diary;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DiaryApp extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JButton saveButton;
    private JButton openButton;

    public DiaryApp() {
        setTitle("Personal Diary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        buttonPanel.add(saveButton);

        openButton = new JButton("Open");
        openButton.addActionListener(this);
        buttonPanel.add(openButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            String content = textArea.getText();
            if (!content.isEmpty()) {
                String fileName = generateFileName();
                saveContentToFile(fileName, content);
                JOptionPane.showMessageDialog(this, "Diary entry saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                textArea.setText("");
            }
        } else if (e.getSource() == openButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Open Text File");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String fileContent = readContentFromFile(file);
                if (fileContent != null) {
                    textArea.setText(fileContent);
                } else {
                    JOptionPane.showMessageDialog(this, "Error occurred while reading the file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private String generateFileName() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return "diary_" + now.format(formatter) + ".txt";
    }

    private void saveContentToFile(String fileName, String content) {
    	
    	String directoryPath = "/Users/a0k00ee/Documents/workspace/javaworkspace/Ashu-OPAI/src/com/ashish/diary/"; 

        try {
            File file = new File(directoryPath + File.separator + fileName);
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while saving the diary entry.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String readContentFromFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DiaryApp diaryApp = new DiaryApp();
            diaryApp.setVisible(true);
        });
    }
}