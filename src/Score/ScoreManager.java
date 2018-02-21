package Score;

import com.borland.jbcl.layout.*;

import db.dbConn;
import stuManager.MainFrame;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.awt.*;

/**
 * 
 * Title: 成绩管理 
 * Description: 成绩管理模块，可以查询成绩，是成绩修改和删除的入口。
 * 
 * @author 谢孟辉
 */

public class ScoreManager extends JFrame {
	XYLayout xYLayout1 = new XYLayout();
	JLabel jLabel1 = new JLabel();
	JPanel jPanel1 = new JPanel();
	JScrollPane jScrollPane1 = new JScrollPane();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	XYLayout xYLayout2 = new XYLayout();
	JTable jTable1 = new JTable();
	ButtonGroup buttonGroup1 = new ButtonGroup();
	JButton jButton_print = new JButton();
	JButton jButton_sort = new JButton();
	JButton jButton_average = new JButton();
	JButton jButton_failure = new JButton();
	dbConn sta = new dbConn();
	String sql;
	Object[][] arrData = {};
	String[] arrField = { "成绩编号", "学生学号", "姓名", "课程", "分数" };
	DefaultTableModel model = new DefaultTableModel();
	int intRow;
	static int find;
	int i_sortDesc = 0, i_print = 0;
	JOptionPane jOptionPane1 = new JOptionPane();
	JButton jButton6 = new JButton();

	public ScoreManager() {
		try {
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		getContentPane().setLayout(xYLayout1);
		jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 20));
		jLabel1.setText("成  绩  管  理");
		jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
		jButton1.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jButton1.setText("修   改");
		jButton1.addActionListener(new ScoreF_jButton1_actionAdapter(this));
		jButton2.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jButton2.setText("返   回");
		jButton2.addActionListener(new ScoreF_jButton2_actionAdapter(this));
		jPanel1.setLayout(xYLayout2);
		jPanel1.setBorder(BorderFactory.createEtchedBorder());
		xYLayout1.setWidth(550);
		xYLayout1.setHeight(560);
		jButton_print.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton_print.setText("打  印");
		jButton_print.addActionListener(new ScoreManager_jButton_print_actionAdapter(this));
		jButton_sort.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton_sort.setText("排  序");
		jButton_sort.addActionListener(new ScoreManager_jButton_sort_actionAdapter(this));
		jButton_average.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton_average.setText("平  均");
		jButton_average.addActionListener(new ScoreManager_jButton_average_actionAdapter(this));
		jButton_failure.setFont(new java.awt.Font("Dialog", Font.PLAIN, 16));
		jButton_failure.setText("不及格");
		jButton_failure.addActionListener(new ScoreManager_jButton_failure_actionAdapter(this));
		this.setTitle("成绩管理");
		jButton6.setFont(new java.awt.Font("Dialog", Font.PLAIN, 18));
		jButton6.setText("删   除");
		jButton6.addActionListener(new ScoreF_jButton6_actionAdapter(this));
		jScrollPane1.getViewport().add(jTable1);
		this.getContentPane().add(jLabel1, new XYConstraints(208, 13, 135, 43));
		this.getContentPane().add(jButton1, new XYConstraints(74, 470, 100, -1));
		this.getContentPane().add(jButton2, new XYConstraints(374, 470, 100, -1));
		this.getContentPane().add(jPanel1, new XYConstraints(17, 68, 515, 63));
		this.getContentPane().add(jScrollPane1, new XYConstraints(18, 150, 515, 300));
		this.getContentPane().add(jButton6, new XYConstraints(224, 470, 100, -1));
		jPanel1.add(jButton_print, new XYConstraints(10, 15, 80, 25));
		jPanel1.add(jButton_sort, new XYConstraints(147, 15, 80, 25));
		jPanel1.add(jButton_average, new XYConstraints(287, 15, 80, 25));
		jPanel1.add(jButton_failure, new XYConstraints(419, 15, 80, 25));

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

		// 设置默认查找所有成绩
		sql = "select * from tb_score ";
		// 更新
		UpdateRecord();
	}

	// 查询并更新表格
	public void UpdateRecord() {
		Object[][] arrTmp = {}; // 设定表格的字段
		Vector vec = new Vector(1, 1);
		model = new DefaultTableModel(arrTmp, arrField);
		jTable1 = new JTable(model);
		jScrollPane1.getViewport().add(jTable1, null);
		try {
			ResultSet rs = sta.getRs(sql);
			int i = 1;
			while (rs.next()) {
				vec = new Vector();
				vec.add(String.valueOf(rs.getString("scoreId")));
				vec.add(String.valueOf(rs.getString("stuNumber")));
				ResultSet rsTemp = sta.getRs("select stuName from tb_student where stuNumber='"
						+ String.valueOf(rs.getString("stuNumber")) + "'");
				while (rsTemp.next()) {
					vec.add(String.valueOf(rsTemp.getString("stuName")));
				}
				vec.add(String.valueOf(rs.getString("courceName")));
				vec.add(String.valueOf(rs.getString("score")));
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

	// 选定所选的行
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

	// 单次点击打印对学号进行升序排序
	// 双次点击打印对学号进行升序排序
	public void jButton_print_actionPerformed(ActionEvent e) {
		i_print++;
		if (i_print % 2 == 1) {
			sql = "select * from tb_score order by scoreId, stuNumber";
		} else if (i_print % 2 == 0) {
			sql = "select * from tb_score order by stuNumber, scoreId";
		}
		UpdateRecord();
	}

	// 单次点击排序对成绩进行降序排序
	// 双次点击排序对成绩进行升序排序
	public void jButton_sort_actionPerformed(ActionEvent e) {
		i_sortDesc++;
		if (i_sortDesc % 2 == 1) {
			sql = "select * from tb_score order by score desc, stuNumber";
		} else if (i_sortDesc % 2 == 0) {
			sql = "select * from tb_score order by score, stuNumber";
		}
		UpdateRecord();
	}

	// 平均分
	public void jButton_average_actionPerformed(ActionEvent e) {
		float f_average = 0;
		int averageBig = 0, averageSmall = 0;
		try {
			// 获得平均分
			ResultSet rs_average = sta.getRs("select avg(score) as scoreAverage from tb_score");
			while (rs_average.next()) {
				f_average = Float.valueOf(rs_average.getString("scoreAverage"));
			}

			// 获得大于等于平均分的人数
			ResultSet rs_averageBig = sta.getRs("select * from tb_score where score>='" + f_average + "'");
			while (rs_averageBig.next()) {
				averageBig++;
			}

			// 获得小于平均分的人数
			ResultSet rs_averageSmall = sta.getRs("select * from tb_score where score<'" + f_average + "'");
			while (rs_averageSmall.next()) {
				averageSmall++;
			}

		} catch (SQLException e1) {
			System.out.println(e1);
		}

		// 显示平均分
		jOptionPane1.showMessageDialog(this,
				"平均分  = " + f_average + "\n大于等于平均分有 " + averageBig + "人\n小于平均分有 " + averageSmall + "人", "提示",
				JOptionPane.INFORMATION_MESSAGE, null);
	}

	// 不及格
	public void jButton_failure_actionPerformed(ActionEvent e) {
		int totalNumber = 0, failureNumber = 0;
		try {
			// 获取总人数
			ResultSet rs_total = sta.getRs("select * from tb_score");
			while (rs_total.next()) {
				totalNumber++;
			}

			// 获取低于60分（不及格）人数
			ResultSet rs_failure = sta.getRs("select * from tb_score where score<'" + 60 + "'");
			while (rs_failure.next()) {
				failureNumber++;
			}
		} catch (SQLException e1) {
			System.out.println(e1);
		}

		// 显示不及格率
		jOptionPane1.showMessageDialog(this, "不及格率  = " + (float) failureNumber / totalNumber * 100 + "%\n不及格人数有 "
				+ failureNumber + "人\n总人数有 " + totalNumber + "人", "提示", JOptionPane.INFORMATION_MESSAGE, null);
	}

	// 修改课程
	public void jButton1_actionPerformed(ActionEvent e) {
		getM();
		if (find > 0) {
			ScoreChange siadd = new ScoreChange(find);
			siadd.setLocation(400, 200);
			siadd.setSize(465, 310);
			siadd.setVisible(true);
			siadd.setResizable(false);
			siadd.validate();
			this.dispose();
		}
	}

	// 删除课程
	public void jButton6_actionPerformed(ActionEvent e) {
		getM();
		if (intRow == -1) {
			jOptionPane1.showMessageDialog(this, "请选择要删除的课程！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		}
		try {
			sta.getUpdate("delete from tb_score where scoreId='" + Integer.valueOf(find) + "'");
			jOptionPane1.showMessageDialog(this, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
		} catch (Exception ce) {
			System.out.println(ce.getMessage());
		}
		UpdateRecord();
	}
}

class ScoreF_jButton1_actionAdapter implements ActionListener {
	private ScoreManager adaptee;

	ScoreF_jButton1_actionAdapter(ScoreManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton1_actionPerformed(e);
	}
}

class ScoreF_jButton6_actionAdapter implements ActionListener {
	private ScoreManager adaptee;

	ScoreF_jButton6_actionAdapter(ScoreManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton6_actionPerformed(e);
	}
}

class ScoreF_jButton2_actionAdapter implements ActionListener {
	private ScoreManager adaptee;

	ScoreF_jButton2_actionAdapter(ScoreManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton2_actionPerformed(e);
	}
}

class ScoreManager_jButton_print_actionAdapter implements ActionListener {
	private ScoreManager adaptee;

	ScoreManager_jButton_print_actionAdapter(ScoreManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_print_actionPerformed(e);
	}
}

class ScoreManager_jButton_sort_actionAdapter implements ActionListener {
	private ScoreManager adaptee;

	ScoreManager_jButton_sort_actionAdapter(ScoreManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_sort_actionPerformed(e);
	}
}

class ScoreManager_jButton_average_actionAdapter implements ActionListener {
	private ScoreManager adaptee;

	ScoreManager_jButton_average_actionAdapter(ScoreManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_average_actionPerformed(e);
	}
}

class ScoreManager_jButton_failure_actionAdapter implements ActionListener {
	private ScoreManager adaptee;

	ScoreManager_jButton_failure_actionAdapter(ScoreManager adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_failure_actionPerformed(e);
	}
}
