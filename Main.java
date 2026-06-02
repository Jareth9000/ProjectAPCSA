import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        JFrame frame = new JFrame("game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960,580);
        frame.setLocationRelativeTo(null);
        DisplayPanel panel = new DisplayPanel();
        frame.add(panel);

        frame.setVisible(true);


    }
}