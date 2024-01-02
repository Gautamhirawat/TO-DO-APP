import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public StartPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setTitle("TODO LIST");

        JButton startButton = new JButton("Start");
        startButton.setBounds(350, 150, 100, 30);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "buttonPage");
            }
        });

        JPanel startPanel = new JPanel();
        startPanel.setLayout(null);
        startPanel.add(startButton);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        cardPanel.add(startPanel, "startPage");

        button buttonPage = new button();
        cardPanel.add(buttonPage.getContentPane(), "buttonPage");

        getContentPane().add(cardPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartPage startPage = new StartPage();
            startPage.setVisible(true);
        });
    }
}

