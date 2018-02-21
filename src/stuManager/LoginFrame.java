package stuManager;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JTextField;

import User.UserAddFrame;
import db.dbConn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;

/**
 * 
 * Title: 登录模块 
 * Description: 登录模块的可视化实现
 * 
 * @author 谢孟辉
 */

public class LoginFrame extends JFrame {
	JPanel contentPane;
	URL url = getClass().getResource("/img/bg.jpg");
	ImageIcon loginBg = new ImageIcon(url);
	JLabel jLabel_welcomeTitle = new JLabel();
	JLabel jLabel_systemTitle = new JLabel();
	JLabel jLabel_userName = new JLabel();
	JLabel jLabel_pwd = new JLabel();
	JTextField jTextField_userName = new JTextField();
	JButton jButton_Login = new JButton();
	JButton jButton_quit = new JButton();
	JLabel jLabel_systemTitle2 = new JLabel();
	JLabel jLabel_welcomeTitle2 = new JLabel();
	JOptionPane jOptionPane_LoginFeedback = new JOptionPane();
	JPasswordField jPasswordField_pwd = new JPasswordField();
	JPanel jPanel_bgBox = new JPanel();
	JLabel jLabel_bg = new JLabel();
	CardLayout cardLayout1 = new CardLayout();
	static String level, name;
	dbConn conn = new dbConn();

	public LoginFrame() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		setSize(new Dimension(504, 344));
		setTitle("欢迎登录学生管理系统");
		jLabel_welcomeTitle.setFont(new java.awt.Font("Dialog", Font.BOLD, 25));
		jLabel_welcomeTitle.setToolTipText("");
		jLabel_welcomeTitle.setText("欢  迎  登  录  学  生");
		jLabel_welcomeTitle.setBounds(new Rectangle(128, 19, 246, 30));
		jLabel_systemTitle.setFont(new java.awt.Font("Dialog", Font.BOLD, 25));
		jLabel_systemTitle.setToolTipText("");
		jLabel_systemTitle.setText("管  理  系  统");
		jLabel_systemTitle.setBounds(new Rectangle(173, 61, 164, 29));
		jLabel_userName.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel_userName.setText("用户名:");
		jLabel_userName.setBounds(new Rectangle(95, 123, 77, 25));
		jLabel_pwd.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel_pwd.setText("密    码:");
		jLabel_pwd.setBounds(new Rectangle(95, 172, 74, 21));
		jTextField_userName.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		jTextField_userName.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField_userName.setBounds(new Rectangle(188, 122, 212, 27));
		jButton_Login.setBounds(new Rectangle(124, 243, 93, 30));
		jButton_Login.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		jButton_Login.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton_Login.setText("登  录");
		jButton_Login.addActionListener(new LoginFrame_jButton_Login_actionAdapter(this));
		jButton_quit.setBounds(new Rectangle(286, 243, 93, 30));
		jButton_quit.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		jButton_quit.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton_quit.setText("注  册");
		jButton_quit.addActionListener(new LoginFrame_jButton_SignUp_actionAdapter(this));
		jLabel_systemTitle2.setFont(new java.awt.Font("Dialog", Font.BOLD, 25));
		jLabel_systemTitle2.setForeground(Color.blue);
		jLabel_systemTitle2.setToolTipText("");
		jLabel_systemTitle2.setText("管  理  系  统");
		jLabel_systemTitle2.setBounds(new Rectangle(173, 62, 164, 29));
		jLabel_welcomeTitle2.setFont(new java.awt.Font("Dialog", Font.BOLD, 25));
		jLabel_welcomeTitle2.setForeground(Color.blue);
		jLabel_welcomeTitle2.setToolTipText("");
		jLabel_welcomeTitle2.setText("欢  迎  登  录  学  生");
		jLabel_welcomeTitle2.setBounds(new Rectangle(128, 20, 246, 30));
		jOptionPane_LoginFeedback.setBounds(new Rectangle(28, 263, 262, 90));
		jOptionPane_LoginFeedback.setLayout(null);
		jPasswordField_pwd.setBorder(BorderFactory.createLoweredBevelBorder());
		jPasswordField_pwd.setBounds(new Rectangle(187, 168, 213, 26));
		jPanel_bgBox.setBounds(new Rectangle(-15, 0, 529, 375));
		jPanel_bgBox.setLayout(cardLayout1);
		jLabel_bg.setIcon(loginBg);
		contentPane.add(jTextField_userName);
		contentPane.add(jLabel_userName);
		contentPane.add(jLabel_pwd);
		contentPane.add(jButton_Login);
		contentPane.add(jButton_quit);
		contentPane.add(jLabel_welcomeTitle);
		contentPane.add(jLabel_systemTitle);
		contentPane.add(jLabel_welcomeTitle2);
		contentPane.add(jLabel_systemTitle2);
		contentPane.add(jPasswordField_pwd);
		contentPane.add(jPanel_bgBox);
		jPanel_bgBox.add(jLabel_bg, "jLabel8");
		contentPane.add(jOptionPane_LoginFeedback);
	}
	
	// 添加用户模块
		public void jButton_SignUp_actionPerformed(ActionEvent e) {
			UserAddFrame siadd = new UserAddFrame();
			siadd.setLocation(400, 200);
			siadd.setSize(469, 315);
			siadd.setVisible(true);
			siadd.setResizable(false);
			siadd.validate();
		}

	// 登录
	public void jButton_Login_actionPerformed(ActionEvent e) {
		boolean cheng = false;
		// 异常判断
		if (jTextField_userName.getText().length() == 0) {
			jOptionPane_LoginFeedback.showMessageDialog(this, "用户名不能为空！", "提 示",
					jOptionPane_LoginFeedback.INFORMATION_MESSAGE);
		} else {
			try {
				ResultSet rs = conn
						.getRs("select * from tb_user where userName='" + jTextField_userName.getText() + "'");
				while (rs.next()) {
					if (rs.getString("userPwd").trim().equals(this.jPasswordField_pwd.getText())) {
						level = rs.getString("UserType").trim();
						name = jTextField_userName.getText().trim();
						MainFrame main = new MainFrame(level, jTextField_userName.getText());
						main.setLocation(200, 40);
						main.setSize(900, 650);
						main.setVisible(true);
						main.setResizable(false);
						main.validate();
						this.dispose();
						cheng = false;
						break;
					} else {
						cheng = true;
					}
				}
				if (cheng) {
					jOptionPane_LoginFeedback.showMessageDialog(this, "用户名或密码错误！", "提 示",
							jOptionPane_LoginFeedback.INFORMATION_MESSAGE);
				}
				rs.close();
			} catch (Exception ce) {
				System.out.println(ce);
			}
		}
	}
	
}

class LoginFrame_jButton_Login_actionAdapter implements ActionListener {
	private LoginFrame adaptee;

	LoginFrame_jButton_Login_actionAdapter(LoginFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Login_actionPerformed(e);
	}
}

class LoginFrame_jButton_SignUp_actionAdapter implements ActionListener {
	private LoginFrame adaptee;

	LoginFrame_jButton_SignUp_actionAdapter(LoginFrame adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_SignUp_actionPerformed(e);
	}
}
