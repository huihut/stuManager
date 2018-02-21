package Class;

import java.awt.*;
import javax.swing.JFrame;
import com.borland.jbcl.layout.XYLayout;

import db.dbConn;

import com.borland.jbcl.layout.*;
import stuManager.MainFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.util.*;

/**
 * 
 * Title: 课程管理 
 * Description: 实现课程管理模块，包含课程查询，同时是课程修改和删除的入口。
 * 
 * @author 谢孟辉
 */

public class ClassManager extends JFrame {
	XYLayout xYLayout1 = new XYLayout();
	JLabel jLabel1 = new JLabel();
	JPanel jPanel1 = new JPanel();
	XYLayout xYLayout2 = new XYLayout();
	JRadioButton jRadioButton1 = new JRadioButton();
	JRadioButton jRadioButton2 = new JRadioButton();
	JRadioButton jRadioButton3 = new JRadioButton();
	ButtonGroup buttonGroup1 = new ButtonGroup();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JComboBox jComboBox1 = new JComboBox();
	JComboBox jComboBox2 = new JComboBox();
	JTextField jTextField1 = new JTextField();
	JLabel jLabel5 = new JLabel();
	JComboBox jComboBox3 = new JComboBox();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	JButton jButton3 = new JButton();
	JScrollPane jScrollPane1 = new JScrollPane();
	JTable jTable1 = new JTable();
	JButton jButton4 = new JButton();
	JButton jButton5 = new JButton();
	JButton jButton6 = new JButton();
	dbConn conn = new dbConn();
	Object[][] arrData = {};
	String[] arrField = { "班级编号", "学院名称", "专业名称", "班级名称" };
	JOptionPane jOptionPane1 = new JOptionPane();
	DefaultTableModel model = new DefaultTableModel();
	String sql, find;
	int intRow;

	public ClassManager() {
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
		jLabel1.setText("班 级 信 息 管 理");
		jLabel2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jLabel2.setText("请选择学院：");
		jLabel3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jLabel3.setText("请选择学院：");
		jLabel4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jLabel4.setText("请输入班级：");
		jComboBox1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jComboBox2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jComboBox2.setEditable(false);
		jComboBox2.addActionListener(new ClassManager_jComboBox2_actionAdapter(this));
		jTextField1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jRadioButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jRadioButton1.setText("按学院查询：");
		jRadioButton1.addItemListener(new ClassManager_jRadioButton1_itemAdapter(this));
		jRadioButton1.addActionListener(new ClassManager_jRadioButton1_actionAdapter(this));
		jPanel1.setBorder(BorderFactory.createEtchedBorder());
		jPanel1.setLayout(xYLayout2);
		jRadioButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jRadioButton2.setText("按专业查询：");
		jRadioButton2.addItemListener(new ClassManager_jRadioButton2_itemAdapter(this));
		jRadioButton2.addActionListener(new ClassManager_jRadioButton2_actionAdapter(this));
		jRadioButton3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jRadioButton3.setText("按名称查询：");
		jRadioButton3.addItemListener(new ClassManager_jRadioButton3_itemAdapter(this));
		jRadioButton3.addActionListener(new ClassManager_jRadioButton3_actionAdapter(this));
		jLabel5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jLabel5.setText("请选择专业：");
		jComboBox3.setEnabled(false);
		jComboBox3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton1.setText("查  询");
		jButton1.addActionListener(new ClassManager_jButton1_actionAdapter(this));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton2.setText("查  询");
		jButton2.addActionListener(new ClassManager_jButton2_actionAdapter(this));
		jButton3.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton3.setText("查  询");
		jButton3.addActionListener(new ClassManager_jButton3_actionAdapter(this));
		jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
		jButton4.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jButton4.setText("修  改");
		jButton4.addActionListener(new ClassManager_jButton4_actionAdapter(this));
		jButton5.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jButton5.setText("删  除");
		jButton5.addActionListener(new ClassManager_jButton5_actionAdapter(this));
		jButton6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jButton6.setText("返  回");
		jButton6.addActionListener(new ClassManager_jButton6_actionAdapter(this));
		jPanel1.add(jRadioButton2, new XYConstraints(20, 50, 125, 25));
		jPanel1.add(jRadioButton3, new XYConstraints(20, 130, 125, 25));
		jPanel1.add(jRadioButton1, new XYConstraints(20, 10, 125, 25));
		buttonGroup1.add(jRadioButton1);
		buttonGroup1.add(jRadioButton2);
		buttonGroup1.add(jRadioButton3);
		jPanel1.add(jLabel3, new XYConstraints(150, 50, 100, 25));
		jPanel1.add(jLabel4, new XYConstraints(150, 130, 100, 25));
		jPanel1.add(jLabel2, new XYConstraints(150, 10, 100, 25));
		jPanel1.add(jComboBox1, new XYConstraints(260, 10, 130, 25));
		jPanel1.add(jComboBox2, new XYConstraints(260, 50, 130, 25));
		jPanel1.add(jTextField1, new XYConstraints(260, 130, 130, 25));
		jPanel1.add(jLabel5, new XYConstraints(150, 90, 100, 25));
		jPanel1.add(jComboBox3, new XYConstraints(260, 90, 130, 25));
		this.getContentPane().add(jLabel1, new XYConstraints(177, 14, 177, 39));
		this.getContentPane().add(jPanel1, new XYConstraints(10, 59, 510, 170));
		jPanel1.add(jButton1, new XYConstraints(410, 10, 80, 25));
		jPanel1.add(jButton2, new XYConstraints(410, 90, 80, 25));
		jPanel1.add(jButton3, new XYConstraints(410, 130, 80, 25));
		jScrollPane1.getViewport().add(jTable1);
		this.getContentPane().add(jScrollPane1, new XYConstraints(10, 239, 510, 225));
		this.getContentPane().add(jButton4, new XYConstraints(100, 480, 90, 35));
		this.getContentPane().add(jButton5, new XYConstraints(220, 480, 90, 35));
		this.getContentPane().add(jButton6, new XYConstraints(340, 480, 90, 35));
		jComboBox1.addItem("请选择学院");
		jComboBox2.addItem("请选择学院");

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

		// 选择学院
		try {

			ResultSet rs = conn.getRs("select * from tb_depart ");
			while (rs.next()) {
				String xibu = rs.getString("departName");
				jComboBox1.addItem(xibu);
				jComboBox2.addItem(xibu);
			}

		} catch (Exception ce) {
			System.out.println("++++++++" + ce);
		}
		jComboBox1.setEnabled(false);
		jComboBox2.setEnabled(false);
		jComboBox3.setEnabled(false);
		jTextField1.setEditable(false);
		jButton1.setEnabled(false);
		jButton2.setEnabled(false);
		jButton3.setEnabled(false);
		sql = "select * from tb_class";
		UpdateRecord();
	}

	// 选择触发
	public void jRadioButton1_actionPerformed(ActionEvent e) {
		jComboBox1.setEnabled(true);
		jButton1.setEnabled(true);
	}

	public void jRadioButton2_actionPerformed(ActionEvent e) {
		jComboBox2.setEnabled(true);
		jButton2.setEnabled(true);
	}

	public void jRadioButton3_actionPerformed(ActionEvent e) {
		jTextField1.setEditable(true);
		jButton3.setEnabled(true);
	}

	public void jRadioButton1_itemStateChanged(ItemEvent e) {
		jComboBox1.setEnabled(false);
		jButton1.setEnabled(false);
	}

	public void jRadioButton2_itemStateChanged(ItemEvent e) {
		jComboBox2.setEnabled(false);
		jComboBox3.setEnabled(false);
		jButton2.setEnabled(false);
	}

	public void jRadioButton3_itemStateChanged(ItemEvent e) {
		jTextField1.setEditable(false);
		jButton3.setEnabled(false);
	}

	public void jComboBox2_actionPerformed(ActionEvent e) {
		jComboBox3.setEnabled(true);
		jcb();
	}

	public void jButton1_actionPerformed(ActionEvent e) {
		sql = "select * from tb_class where departName='" + String.valueOf(jComboBox1.getSelectedItem()) + "'";
		UpdateRecord();
	}

	public void jButton2_actionPerformed(ActionEvent e) {
		sql = "select * from tb_class where specName='" + String.valueOf(jComboBox3.getSelectedItem()) + "'";
		UpdateRecord();
	}

	public void jButton3_actionPerformed(ActionEvent e) {
		sql = "select * from tb_class where className='" + jTextField1.getText() + "'";
		UpdateRecord();
	}

	// 班级修改
	public void jButton4_actionPerformed(ActionEvent e) {
		getM();
		if (intRow == -1) {
			jOptionPane1.showMessageDialog(this, "请选择要修改的班级！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		} else {
			ClassChange siadd = new ClassChange(find);
			siadd.setLocation(400, 200);
			siadd.setSize(465, 310);
			siadd.setVisible(true);
			siadd.setResizable(false);
			siadd.validate();
			this.dispose();
		}
	}

	// 班级删除
	public void jButton5_actionPerformed(ActionEvent e) {
		getM();
		if (intRow == -1) {
			jOptionPane1.showMessageDialog(this, "请选择要删除的班级！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		}
		try {
			conn.getUpdate("delete from tb_class where classId='" + Integer.valueOf(find) + "'");
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

	// 选择专业
	public void jcb() {
		jComboBox3.removeAllItems();
		jComboBox3.addItem("请选择专业");
		try {
			ResultSet rs = conn.getRs(
					"select * from tb_spec where departName='" + String.valueOf(jComboBox2.getSelectedItem()) + "' ");
			while (rs.next()) {
				String zhy = rs.getString("specName");
				jComboBox3.addItem(zhy);
			}
			rs.close();
		} catch (Exception ce) {
			System.out.println("++++++++" + ce);
		}
	}

	// 更新表格
	public void UpdateRecord() {
		Object[][] arrTmp = {};
		Vector vec = new Vector(1, 1);
		model = new DefaultTableModel(arrTmp, arrField);
		jTable1 = new JTable(model);
		jScrollPane1.getViewport().add(jTable1, null);
		try {
			ResultSet rs3 = conn.getRs(sql);
			while (rs3.next()) {
				vec = new Vector();
				vec.add(String.valueOf(rs3.getInt("classId")));
				vec.add(rs3.getString("departName").trim());
				vec.add(rs3.getString("specName"));
				vec.add(rs3.getString("className"));
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

	// 获取选定的行
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

class ClassManager_jButton6_actionAdapter implements ActionListener {
	private ClassManager adaptee;

	ClassManager_jButton6_actionAdapter(ClassManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton6_actionPerformed(e);
	}
}

class ClassManager_jButton5_actionAdapter implements ActionListener {
	private ClassManager adaptee;

	ClassManager_jButton5_actionAdapter(ClassManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton5_actionPerformed(e);
	}
}

class ClassManager_jButton4_actionAdapter implements ActionListener {
	private ClassManager adaptee;

	ClassManager_jButton4_actionAdapter(ClassManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {

		adaptee.jButton4_actionPerformed(e);
	}
}

class ClassManager_jButton3_actionAdapter implements ActionListener {
	private ClassManager adaptee;

	ClassManager_jButton3_actionAdapter(ClassManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton3_actionPerformed(e);
	}
}

class ClassManager_jButton2_actionAdapter implements ActionListener {
	private ClassManager adaptee;

	ClassManager_jButton2_actionAdapter(ClassManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton2_actionPerformed(e);
	}
}

class ClassManager_jButton1_actionAdapter implements ActionListener {
	private ClassManager adaptee;

	ClassManager_jButton1_actionAdapter(ClassManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}

class ClassManager_jRadioButton1_actionAdapter implements ActionListener {
	private ClassManager adaptee;

	ClassManager_jRadioButton1_actionAdapter(ClassManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jRadioButton1_actionPerformed(e);
	}
}

class ClassManager_jRadioButton1_itemAdapter implements ItemListener {
	private ClassManager adaptee;

	ClassManager_jRadioButton1_itemAdapter(ClassManager adaptee) {
		this.adaptee = adaptee;
	}

	public void itemStateChanged(ItemEvent e) {
		adaptee.jRadioButton1_itemStateChanged(e);
	}
}

class ClassManager_jRadioButton2_actionAdapter implements ActionListener {
	private ClassManager adaptee;

	ClassManager_jRadioButton2_actionAdapter(ClassManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jRadioButton2_actionPerformed(e);
	}
}

class ClassManager_jRadioButton2_itemAdapter implements ItemListener {
	private ClassManager adaptee;

	ClassManager_jRadioButton2_itemAdapter(ClassManager adaptee) {
		this.adaptee = adaptee;
	}

	public void itemStateChanged(ItemEvent e) {
		adaptee.jRadioButton2_itemStateChanged(e);
	}
}

class ClassManager_jRadioButton3_actionAdapter implements ActionListener {
	private ClassManager adaptee;

	ClassManager_jRadioButton3_actionAdapter(ClassManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jRadioButton3_actionPerformed(e);
	}
}

class ClassManager_jRadioButton3_itemAdapter implements ItemListener {
	private ClassManager adaptee;

	ClassManager_jRadioButton3_itemAdapter(ClassManager adaptee) {
		this.adaptee = adaptee;
	}

	public void itemStateChanged(ItemEvent e) {
		adaptee.jRadioButton3_itemStateChanged(e);
	}
}

class ClassManager_jComboBox2_actionAdapter implements ActionListener {
	private ClassManager adaptee;

	ClassManager_jComboBox2_actionAdapter(ClassManager adaptee) {

		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jComboBox2_actionPerformed(e);
	}
}
