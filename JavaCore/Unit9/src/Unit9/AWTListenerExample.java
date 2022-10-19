package Unit9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AWTListenerExample {
    public static void main(String[] args) {
        // tạo cửa sổ
    //    Frame screen = new Frame();

            Frame screen = new Frame();
            screen.addWindowListener((WindowClosing)(e)->System.exit(1));

        Button button = new Button("Press me");
        screen.add(button);
// thêm sựkienj cho đối tượng
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Welcome to Java by Example", "Java Sample", JOptionPane.INFORMATION_MESSAGE);
        //  JOptionPane.showMessageDialog(null, "Info Bõ: Ưelcome  to Java by Example", "Java Sample", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        screen.setSize(250, 400);
        // hiển thị
        screen.setVisible(true);
    }
}
