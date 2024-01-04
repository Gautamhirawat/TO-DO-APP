import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoApp extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private List<String> todoItems;

    private JTextField titleField;
    private JTextArea pointsArea;

    public TodoApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("TODO List App");
        setSize(600, 400);
        setLocationRelativeTo(null);

        todoItems = new ArrayList<>();

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        mainPanel.add(createStartPanel(), "startPanel");
        mainPanel.add(createButtonPanel(), "buttonPanel");

        cardLayout.show(mainPanel, "startPanel");

        add(createMenuBar(), BorderLayout.NORTH);
        add(mainPanel);

        setVisible(true);
    }

    private JPanel createStartPanel() {
        JPanel startPanel = new JPanel(new BorderLayout());

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> cardLayout.show(mainPanel, "buttonPanel"));

        startPanel.add(startButton, BorderLayout.CENTER);

        return startPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton newButton = createButton("New");
        JButton deleteButton = createButton("Delete");
        JButton saveButton = createButton("Save");

        buttonPanel.add(newButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);

        titleField = new JTextField();
        pointsArea = new JTextArea();
        pointsArea.setLineWrap(true);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JLabel("Title:"), BorderLayout.NORTH);
        inputPanel.add(titleField, BorderLayout.CENTER);
        inputPanel.add(new JLabel("Points:"), BorderLayout.SOUTH);
        inputPanel.add(new JScrollPane(pointsArea), BorderLayout.SOUTH);

        buttonPanel.add(inputPanel);

        return buttonPanel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(this::handleButtonClick);
        return button;
    }

    private void handleButtonClick(ActionEvent event) {
        JButton source = (JButton) event.getSource();

        switch (source.getText()) {
            case "New":
                // No action needed here, input fields are already available
                break;
            case "Delete":
                clearInputFields();
                break;
            case "Save":
                saveDataToFile();
                break;
        }
    }

    private void clearInputFields() {
        titleField.setText("");
        pointsArea.setText("");
    }

    private void saveDataToFile() {
        String title = titleField.getText();
        String points = pointsArea.getText();

        if (!title.isEmpty() && !points.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("todo.txt", true))) {
                writer.write("Title: " + title + "\nPoints: " + points + "\n\n");
                writer.flush();
                JOptionPane.showMessageDialog(this, "Data saved successfully!");
                clearInputFields();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving data.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter title and points before saving.");
        }
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        JMenuItem saveMenuItem = new JMenuItem("Save");

        fileMenu.add(newMenuItem);
        fileMenu.add(deleteMenuItem);
        fileMenu.add(saveMenuItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");

        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TodoApp::new);
    }
}
