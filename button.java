import javax.swing.*;


public class button extends JFrame{ 


/*we have to give name of our file TODO (YOU CAN GIVE ANY NAME) 
then we have extended the JFrame it helps us to use all the function inside it.
 we have to create a same name function TODO here so that all execute at once.
*/
    
    public button(){


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setTitle("My Swing App");
/* Here i have added some buttons to the screen and we can change location of thoese buttons by 
 setBounds fnx .  */
        JButton button1 = new JButton("NEW");
        button1.setBounds(50, 50, 100, 30);

        JButton button2 = new JButton("DELETE");
        button2.setBounds(200, 50, 100, 30);

        JButton button3 = new JButton("SAVE");
        button3.setBounds(350, 50, 100, 30);

        getContentPane().setLayout(null);

        getContentPane().add(button1);
        getContentPane().add(button2);
        getContentPane().add(button3);

/* this addActionListener function tells us what the button works
 for now it is just showing us a message dialog */

        button1.addActionListener(e -> JOptionPane.showMessageDialog(this, "ADDED"));
        button2.addActionListener(e -> JOptionPane.showMessageDialog(this, "DELETED "));
        button3.addActionListener(e -> JOptionPane.showMessageDialog(this, "SAVE"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            button app = new button();
            app.setVisible(true);
        });
}
}

