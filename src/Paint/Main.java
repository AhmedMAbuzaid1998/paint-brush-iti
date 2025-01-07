package Paint;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        JFrame splashFrame = new JFrame();
        splashFrame.setUndecorated(true);
        splashFrame.setSize(400, 300);
        splashFrame.setLocationRelativeTo(null);

        URL imageUrl = Main.class.getResource("/resources/iti.jpg");
        if (imageUrl != null) {
            ImageIcon splashImage = new ImageIcon(imageUrl);
            JLabel splashLabel = new JLabel(splashImage);
            splashFrame.add(splashLabel);
        } else {
            System.err.println("Error: Image not found in resources folder!");
        }


        splashFrame.setVisible(true);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splashFrame.dispose();

        JFrame frm = new JFrame();
        frm.setTitle("ITI Painter");
        EssPanel pn = new EssPanel();
        frm.setContentPane(pn);

        frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frm.setUndecorated(false);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
    }
}
