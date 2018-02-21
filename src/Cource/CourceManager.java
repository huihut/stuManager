package Cource;

import java.awt.Font;

import com.borland.jbcl.layout.XYLayout;

import db.dbConn;

import com.borland.jbcl.layout.*;
import stuManager.MainFrame;

import javax.swing.*;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.awt.*;

/**
 * 
 * Title: 课程管理 
 * Description: 课程管理模块，实现课程查询，同时也是课程修改、删除的入口。
 * 
 * @author 谢孟辉
 */

public class CourceManager extends JFrame {
	XYLayout xYLayout1 = new XYLayout();
	JLabel jLabel1 = new JLabel();
	JScrollPane jScrollPane1 = new JScrollPane();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	XYLayout xYLayout2 = new XYLayout();
	JTable jTable1 = new JTable();
	ButtonGroup buttonGroup1 = new ButtonGroup();
	dbConn sta = new dbConn();
	String sql;
	Object[][] arrData = {};
	String[] arrField = { "课程编号", "专业名称", "课程名称", "课程学分" };
	DefaultTableModel model = new DefaultTableModel();
	int intRow;
	static int find;
	JOptionPane jOptionPane1 = new JOptionPane();
	JButton jButton6 = new JButton();

	public CourceManager() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		getContentPane().setLayout(xYLayout1);
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		jLabel1.setText("课  程  管  理");
		jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jButton1.setText("修   改");
		jButton1.addActionListener(new CourceF_jButton1_actionAdapter(this));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jButton2.setText("返   回");
		jButton2.addActionListener(new CourceF_jButton2_actionAdapter(this));
		xYLayout1.setWidth(550);
		xYLayout1.setHeight(560);
		this.setTitle("课程管理");
		jButton6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jButton6.setText("删   除");
		jButton6.addActionListener(new CourceF_jButton6_actionAdapter(this));
		jScrollPane1.getViewport().add(jTable1);
		this.getContentPane().add(jLabel1, new XYConstraints(208, 13, 135, 43));
		this.getContentPane().add(jButton1, new XYConstraints(74, 480, 100, -1));
		this.getContentPane().add(jButton2, new XYConstraints(374, 480, 100, -1));
		this.getContentPane().add(jScrollPane1, new XYConstraints(18, 60, 490, 400));
		this.getContentPane().add(jButton6, new XYConstraints(224, 480, 100, -1));

		// 用户等级判断
		// 1为管理员
		if (MainFrame.level.equals("1")) {
			// 不隐藏任何功能
		}
		// 2为普通用户
		else if (MainFrame.level.equals("2")) {
			// 隐藏部分功能
			jButton1.setVisible(false);
			jButton6.setVisible(false);
		}

		sql = "select * from tb_cource ";
		UpdateRecord();

	}

	// 查询显示
	public void UpdateRecord() {
		Object[][] arrTmp = {};
		Vector vec = new Vector(1, 1);
		model = new DefaultTableModel(arrTmp, arrField);
		jTable1 = new JTable(model);
		jScrollPane1.getViewport().add(jTable1, null);
		try {
			ResultSet rs = sta.getRs(sql);
			int i = 1;
			while (rs.next()) {
				vec = new Vector();
				vec.add(String.valueOf(rs.getString("courceId")));
				vec.add(String.valueOf(rs.getString("courceSpecName")));
				vec.add(String.valueOf(rs.getString("courceName")));
				vec.add(String.valueOf(rs.getString("courceHour")));
				model.addRow(vec);
			}
			rs.close();
		} catch (Exception ce) {
			System.out.println(ce);
		}
		jScrollPane1.getHorizontalScrollBar();
		jTable1.setGridColor(Color.blue);
		jTable1.setDragEnabled(true);
		jTable1.setSelectionForeground(Color.red);
		jTable1.setSelectionBackground(Color.green);
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable1.setRowSelectionAllowed(true);
		jTable1.setShowVerticalLines(true);

	}

	// 获取选定的行
	public void getM() {
		intRow = jTable1.getSelectedRow();
		if (intRow == -1) {
			jOptionPane1.showMessageDialog(this, "请选择要修改的课程！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			return;
		}
		try {
			find = Integer.parseInt(model.getValueAt(intRow, 0).toString());
			System.out.println(find);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 退出
	public void jButton2_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	// 修改
	public void jButton1_actionPerformed(ActionEvent e) {
		getM();
		if (find > 0) {
			CourceChange siadd = new CourceChange(find);
			siadd.setLocation(400, 200);
			siadd.setSize(465, 310);
			siadd.setVisible(true);
			siadd.setResizable(false);
			siadd.validate();
			this.dispose();
		}
	}

	// 删除
	public void jButton6_actionPerformed(ActionEvent e) {
		getM();
		if (intRow == -1) {
			jOptionPane1.showMessageDialog(this, "请选择要删除的课程！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		}
		try {
			sta.getUpdate("delete from tb_cource where courceId='" + Integer.valueOf(find) + "'");
			jOptionPane1.showMessageDialog(this, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		} catch (Exception ce) {
			System.out.println(ce.getMessage());
		}
		UpdateRecord();
	}
}

class CourceF_jButton1_actionAdapter implements ActionListener {
	private CourceManager adaptee;

	CourceF_jButton1_actionAdapter(CourceManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}

class CourceF_jButton6_actionAdapter implements ActionListener {
	private CourceManager adaptee;

	CourceF_jButton6_actionAdapter(CourceManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton6_actionPerformed(e);
	}
}

class CourceF_jButton2_actionAdapter implements ActionListener {
	private CourceManager adaptee;

	CourceF_jButton2_actionAdapter(CourceManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton2_actionPerformed(e);
	}
}
