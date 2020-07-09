package gradePoint;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
		frame.setLocationRelativeTo(null);//如果组件当前未显示，或者参数为 null，则此窗口将置于屏幕的中央
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
		
		JFileChooser jfc = new JFileChooser();//文件选择器
        jfc.setFileFilter(new FileFilter() {
             
            @Override
            public String getDescription() {
                // TODO Auto-generated method stub
                return ".csv";
            }
			@Override
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".csv");
			}
        });
		
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
                    textField.grabFocus();//让光标自己定位在框，便于输入
                    checkedpass = false;
                }
				else if(!path.endsWith(".csv")) {
					JOptionPane.showMessageDialog(frame, "该文件类型不能识别 ");				
					textField.grabFocus();//让光标自己定位在框，便于输入
					checkedpass = false;
				}
			}
		});
		
		JButton bOpenButton = new JButton("打开文件");
		bOpenButton.addActionListener(new ActionListener() {
			
			boolean checkedpass = true;
			
			public void actionPerformed(ActionEvent e) {
				
				checkedpass = true;
				
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
		        jfc.showDialog(new JLabel(), "选择");
		        
		        File file=jfc.getSelectedFile(); 
		        if(file != null) {
		        	if(file.isDirectory()){  
			            System.out.println("文件夹:"+file.getAbsolutePath());  
			        }else if(file.isFile()){  
			            System.out.println("文件:"+file.getAbsolutePath());
			            String Path = file.getAbsolutePath();			            
						if(checkedpass) {
							textArea.setText("");
							TestGrade.path = Path;
							float GPA = (float)(Math.round(TestGrade.result()*100))/100 ;//保留两位小数
							String str = "平均绩点为：" + GPA;
							textArea.append(str);
						}
			            
			        }  
			        System.out.println(jfc.getSelectedFile().getName());
		        }	         
			}		
		});
		bOpenButton.setBounds(25, 85, 113, 27);
		frame.getContentPane().add(bOpenButton);	
		}
}
