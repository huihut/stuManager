package Cource;

import java.awt.Font;
import javax.swing.JFrame;
import com.borland.jbcl.layout.XYLayout;

import db.dbConn;

import com.borland.jbcl.layout.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

/**
 * 
 * Title: 课程修改 
 * Description: 课程修改模块
 * 
 * @author 谢孟辉
 */

public class CourceChange extends JFrame {
	XYLayout xYLayout1 = new XYLayout();
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel5 = new JLabel();
	JTextField jTextField1 = new JTextField();
	JComboBox jComboBox1 = new JComboBox();
	JTextField jTextField2 = new JTextField();
	JButton jButton1 = new JButton();
	JButton jButton3 = new JButton();
	dbConn sta = new dbConn();
	JOptionPane jOptionPane1 = new JOptionPane();
	int csid;
	int kefind;

	public CourceChange(int find) {
		kefind = find;
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		getContentPane().setLayout(xYLayout1);
		xYLayout1.setWidth(500);
		xYLayout1.setHeight(350);
		jLabel1.setFont(new java.awt.Font("新宋体", Font.BOLD, 20));
		jLabel1.setText("课   程   修   改 ");
		jButton3.addActionListener(new CourceManager_jButton3_actionAdapter(this));
		jButton1.addActionListener(new CourceManager_jButton1_actionAdapter(this));
		jTextField2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton3.setText("退    出");
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton1.setText("修    改");
		jLabel5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jLabel5.setText("学        分：");
		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jLabel3.setText("所属专业：");
		this.getContentPane().add(jComboBox1, new XYConstraints(200, 130, 140, 30));
		this.getContentPane().add(jTextField2, new XYConstraints(200, 180, 140, 30));
		this.getContentPane().add(jLabel2, new XYConstraints(85, 80, 80, 30));
		this.getContentPane().add(jLabel5, new XYConstraints(85, 180, 80, 30));
		this.getContentPane().add(jLabel3, new XYConstraints(85, 130, 80, 30));
		this.getContentPane().add(jTextField1, new XYConstraints(200, 80, 140, 30));
		this.getContentPane().add(jLabel1, new XYConstraints(158, 21, -1, 49));
		this.getContentPane().add(jButton1, new XYConstraints(130, 230, 90, 30));
		this.getContentPane().add(jButton3, new XYConstraints(295, 230, 90, 30));
		jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jLabel2.setText("课程名称：");
		this.setTitle("课程修改与删除");
		jComboBox1.addItem("请选择");
		// 选择专业
		try {
			ResultSet rs = sta.getRs("select * from tb_spec");
			while (rs.next()) {
				String xibu = rs.getString("specName");
				jComboBox1.addItem(xibu);
			}
			rs.close();
		} catch (Exception ce) {
			System.out.println("++++++++" + ce);
		}
		setF();
	}

	// 选定行后在修改界面显示选定默认值
	public void setF() {
		if (kefind < 0)
			return;
		else {
			try {
				ResultSet rs = sta.getRs("select * from tb_cource where courceId='" + Integer.valueOf(kefind) + "'");
				while (rs.next()) {
					jComboBox1.setSelectedItem(String.valueOf(rs.getString("courceSpecName")));
					jTextField1.setText(rs.getString("courceName").trim());
					jTextField2.setText(rs.getString("courceHour").trim());
				}
				rs.close();
			} catch (Exception ce) {
				System.out.println("++++++++" + ce);
			}
		}
	}

	// 退出
	public void jButton3_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	// 修改
	public void jButton1_actionPerformed(ActionEvent e) {
		String keCName, zyName, tName, xueFen;
		keCName = jTextField1.getText().trim();
		zyName = String.valueOf(jComboBox1.getSelectedItem());
		xueFen = jTextField2.getText().trim();
		try {
			sta.getUpdate("update tb_cource set courceSpecName='" + zyName + "', courceName='" + keCName
					+ "',courceHour='" + Float.valueOf(xueFen) + "' where courceId='" + kefind + "'");
			jOptionPane1.showMessageDialog(this, "课程修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		} catch (Exception a) {
			System.out.println(a.getMessage());
		}
	}
}

class CourceManager_jButton1_actionAdapter implements ActionListener {
	private CourceChange adaptee;

	CourceManager_jButton1_actionAdapter(CourceChange adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}

class CourceManager_jButton3_actionAdapter implements ActionListener {
	private CourceChange adaptee;

	CourceManager_jButton3_actionAdapter(CourceChange adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton3_actionPerformed(e);
	}
}
