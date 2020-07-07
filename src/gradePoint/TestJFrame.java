package gradePoint;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TestJFrame {

	public static void main(String[] args) {
		JFrame f = new JFrame("LoL");
        f.setSize(400, 300);
        f.setLocation(200, 200);
 
        f.setLayout(new FlowLayout());
 
        JLabel lName = new JLabel("账号：");
        // 输入框
        JTextField tfName = new JTextField("");
        tfName.setText("请输入本地文件路径");
        tfName.setPreferredSize(new Dimension(80, 30));
	}

}
