package gradePoint;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class GradePoint {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GradePoint window = new GradePoint();
					window.frame.setVisible(true);
					window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					//String path = textField.getText();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GradePoint() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();//文本框
		textField.setBounds(140, 27, 199, 24);
		//textField.setLineWrap(true);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JLabel filePath = new JLabel("文件路径");//标签
		filePath.setBounds(42, 26, 141, 27);
		frame.getContentPane().add(filePath);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(83, 171, 261, 54);//文本域位置
		textArea.setLineWrap(true);//可以自动换行
		frame.getContentPane().add(textArea);
		
		JButton button = new JButton("确认");//按钮
		button.setBounds(305, 75, 113, 27);
		frame.getContentPane().add(button);	
		button.addActionListener(new ActionListener() {
			
			boolean checkedpass = true;

			@Override
			public void actionPerformed(ActionEvent e) {
				checkedpass = true;
				checkEmpty(textField,"文件路径");
				String path = textField.getText();
				if(checkedpass) {
					textArea.setText("");
					TestGrade.path = path;
					float GPA = (float)(Math.round(TestGrade.result()*100))/100 ;//保留两位小数
					String str = "平均绩点为：" + GPA;
					textArea.append(str);
				}
			}
			private void checkEmpty(JTextField textField, String msg) {
				if(!checkedpass)
                    return;
                String path = textField.getText();
				if(0==path.length()){
					JOptionPane.showMessageDialog(frame, msg + "不能为空");
                    textField.grabFocus();
                    checkedpass = false;
                }
				else if(!path.endsWith(".csv")) {
					JOptionPane.showMessageDialog(frame, "该文件类型不能识别 ");				
					textField.grabFocus();
					checkedpass = false;
				}
			}
		});
		}
}
