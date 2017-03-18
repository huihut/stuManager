package User;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.dbConn;

/**
 * 
 * Title: 用户删除 
 * Description: 用户删除模块，只对管理员显示。
 * 
 * @author 谢孟辉
 */

public class UserDelete extends JFrame {
	JPanel contentPane;
	String level;
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JTextField jTextField1 = new JTextField();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	JOptionPane jOptionPane1 = new JOptionPane();
	dbConn conn = new dbConn();

	public UserDelete() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		setSize(new Dimension(469, 315));
		setTitle("删除用户");
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 23));
		jLabel1.setText("删  除   用   户");
		jLabel1.setBounds(new Rectangle(134, 12, 198, 27));
		jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jLabel2.setText("用户名:");
		jLabel2.setBounds(new Rectangle(90, 100, 68, 22));
		jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
		jTextField1.setBounds(new Rectangle(191, 100, 155, 24));
		jButton1.setBounds(new Rectangle(99, 180, 89, 25));
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		jButton1.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton1.setText("确    认");
		jButton1.addActionListener(new UserDeleteFrame_jButton1_actionAdapter(this));
		jButton2.setBounds(new Rectangle(267, 180, 89, 25));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 13));
		jButton2.setBorder(BorderFactory.createRaisedBevelBorder());
		jButton2.setText("退    出");
		jButton2.addActionListener(new UserDeleteFrame_jButton2_actionAdapter(this));
		jOptionPane1.setBounds(new Rectangle(0, 233, 262, 90));
		jOptionPane1.setLayout(null);
		contentPane.add(jLabel2);
		contentPane.add(jTextField1);
		contentPane.add(jButton1);
		contentPane.add(jButton2);
		contentPane.add(jLabel1);
		contentPane.add(jOptionPane1);
	}

	// 退出
	public void jButton2_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	// 删除用户
	public void jButton1_actionPerformed(ActionEvent e) {
		boolean isAdmin = false, noUser = true;
		// 异常判断
		if (jTextField1.getText().length() == 0) {
			jOptionPane1.showMessageDialog(this, "用户名不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		} else {
			try {
				ResultSet rs = conn
						.getRs("select * from tb_user where userName='" + jTextField1.getText().trim() + "'");
				while (rs.next()) {
					// 判断输入的用户名是否存在
					noUser = false;
					// 判断删除的用户是否管理员（无法删除管理员用户）
					if (1 == Integer.valueOf(rs.getString("userType").trim())) {
						isAdmin = true;
					}
				}
				if (noUser) {
					jOptionPane1.showMessageDialog(this, "该用户名不存在！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} else if (isAdmin) {
					jOptionPane1.showMessageDialog(this, "无法删除管理员账号！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					conn.getUpdate("delete from tb_user where userName='" + jTextField1.getText().trim() + "'");
					jOptionPane1.showMessageDialog(this, "恭喜您删除用户成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				}
				rs.close();
			} catch (Exception ce) {
				System.out.println(ce);
			}
		}
	}
}

class UserDeleteFrame_jButton1_actionAdapter implements ActionListener {
	private UserDelete adaptee;

	UserDeleteFrame_jButton1_actionAdapter(UserDelete adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}

class UserDeleteFrame_jButton2_actionAdapter implements ActionListener {
	private UserDelete adaptee;

	UserDeleteFrame_jButton2_actionAdapter(UserDelete adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton2_actionPerformed(e);
	}
}
