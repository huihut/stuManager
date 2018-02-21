package Depart;

import java.awt.*;

import com.borland.jbcl.layout.*;

import db.dbConn;
import stuManager.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.util.*;

/**
 * 
 * Title: 院系管理 
 * Description: 院系管理模块，可以对院系信息进行查询，是修改和删除的入口。
 * 
 * @author 谢孟辉
 */

public class DepartManager extends JFrame {
	XYLayout xYLayout1 = new XYLayout();
	JLabel jLabel1 = new JLabel();
	ButtonGroup buttonGroup1 = new ButtonGroup();
	JScrollPane jScrollPane1 = new JScrollPane();
	JTable jTable1 = new JTable();
	JButton jButton4 = new JButton();
	JButton jButton5 = new JButton();
	JButton jButton6 = new JButton();
	dbConn conn = new dbConn();
	Object[][] arrData = {};
	String[] arrField = { "专业编号", "学院名称", "专业名称" };
	JOptionPane jOptionPane1 = new JOptionPane();
	DefaultTableModel model = new DefaultTableModel();
	String sql, find;
	int intRow;

	public DepartManager() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		getContentPane().setLayout(xYLayout1);
		xYLayout1.setWidth(530);
		xYLayout1.setHeight(540);
		jLabel1.setFont(new java.awt.Font("黑体", Font.PLAIN, 20));
		jLabel1.setText("院 系 信 息 管 理");
		jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
		jButton4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jButton4.setText("修  改");
		jButton4.addActionListener(new DepartManager_jButton4_actionAdapter(this));
		jButton5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jButton5.setText("删  除");
		jButton5.addActionListener(new DepartManager_jButton5_actionAdapter(this));
		jButton6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jButton6.setText("返  回");
		jButton6.addActionListener(new DepartManager_jButton6_actionAdapter(this));
		this.getContentPane().add(jLabel1, new XYConstraints(177, 14, 177, 39));
		jScrollPane1.getViewport().add(jTable1);
		this.getContentPane().add(jScrollPane1, new XYConstraints(10, 60, 510, 400));
		this.getContentPane().add(jButton4, new XYConstraints(100, 480, 90, 35));
		this.getContentPane().add(jButton5, new XYConstraints(220, 480, 90, 35));
		this.getContentPane().add(jButton6, new XYConstraints(340, 480, 90, 35));

		// 用户等级判断
		// 1为管理员
		if (MainFrame.level.equals("1")) {
			// 不隐藏任何功能
		}
		// 2为普通用户
		else if (MainFrame.level.equals("2")) {
			// 隐藏部分功能
			jButton4.setVisible(false);
			jButton5.setVisible(false);
		}

		// 选择专业表
		sql = "select * from tb_spec";
		// 刷新
		UpdateRecord();
	}

	// 修改院系
	public void jButton4_actionPerformed(ActionEvent e) {
		getM();
		if (intRow == -1) {
			jOptionPane1.showMessageDialog(this, "请选择要修改的院系！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		} else {
			DepartChange siadd = new DepartChange(find);
			siadd.setLocation(400, 200);
			siadd.setSize(465, 310);
			siadd.setVisible(true);
			siadd.setResizable(false);
			siadd.validate();
			this.dispose();
		}
	}

	// 删除院系
	public void jButton5_actionPerformed(ActionEvent e) {
		getM();
		if (intRow == -1) {
			jOptionPane1.showMessageDialog(this, "请选择要删除的院系！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		}
		try {
			conn.getUpdate("delete from tb_spec where specId='" + Integer.valueOf(find) + "'");
			jOptionPane1.showMessageDialog(this, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		} catch (Exception ce) {
			System.out.println(ce.getMessage());
		}
		UpdateRecord();
	}

	// 退出
	public void jButton6_actionPerformed(ActionEvent e) {
		this.dispose();
	}

	// 更新表格
	public void UpdateRecord() {
		Object[][] arrTmp = {}; // 设定表格的字段
		Vector vec = new Vector(1, 1);
		model = new DefaultTableModel(arrTmp, arrField);
		jTable1 = new JTable(model);
		jScrollPane1.getViewport().add(jTable1, null);
		try {
			ResultSet rs3 = conn.getRs(sql);
			while (rs3.next()) {
				vec = new Vector();
				vec.add(String.valueOf(rs3.getInt("specId")));
				vec.add(rs3.getString("departName"));
				vec.add(rs3.getString("specName"));
				model.addRow(vec);
			}
			rs3.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
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

	// 获取所选的行
	public void getM() {
		intRow = jTable1.getSelectedRow();

		if (intRow == -1) {
			return;
		}
		try {
			find = model.getValueAt(intRow, 0).toString().trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class DepartManager_jButton6_actionAdapter implements ActionListener {
	private DepartManager adaptee;

	DepartManager_jButton6_actionAdapter(DepartManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton6_actionPerformed(e);
	}
}

class DepartManager_jButton5_actionAdapter implements ActionListener {
	private DepartManager adaptee;

	DepartManager_jButton5_actionAdapter(DepartManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton5_actionPerformed(e);
	}
}

class DepartManager_jButton4_actionAdapter implements ActionListener {
	private DepartManager adaptee;

	DepartManager_jButton4_actionAdapter(DepartManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {

		adaptee.jButton4_actionPerformed(e);
	}
}
