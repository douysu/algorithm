// JFrameDemo1.java

import javax.swing.*;

public class JFrameDemo {
    public static void main(String args[]) {
        //定义一个窗体对象f，窗体名称为"一个简单窗口"        
        JFrame f = new JFrame("sss");
        f.setLocation(300, 300);
        f.setSize(600, 600);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }
}
