package swingGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @description 此类是一个输入框，包含一个文本框和两个按钮
 * @function 清空文本框
 * @function 返回文本框的文本
 * @function 给按钮1增加监听器
 * @function 给按钮2增加监听器
 */
public class InputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private TextPane textPane = new TextPane();
	private JPanel buttonPanel = new JPanel();
	private JButton button1 = null;
	private JButton button2 = null;

	/**
	 * @description 有参构造函数
	 * @description 利用两个参数创建两个按钮
	 * @description 设置按钮面板布局方式
	 * @description 添加按钮到按钮面板上
	 * @description 设置自己的布局方式
	 * @description 添加文本框到中心位置
	 * @description 添加按钮面板到东面
	 */
	public InputPanel(String name1, String name2) {
		button1 = new JButton(name1);
		button2 = new JButton(name2);
		buttonPanel.setLayout(new GridLayout(2, 1));
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		this.setLayout(new BorderLayout());
		this.add(textPane, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.EAST);
	}

	/**
	 * @description 清空文本框
	 */
	public void clear() {
		textPane.clear();
	}

	/**
	 * @description 返回文本框的文本
	 * @return 返回一个String
	 */
	public String getText() {
		return textPane.getText();
	}

	/**
	 * @description 给按钮1增加监听器
	 */
	public void addActionListener1(ActionListener l) {
		button1.addActionListener(l);
	}

	/**
	 * @description 给按钮2增加监听器
	 */
	public void addActionListener2(ActionListener l) {
		button2.addActionListener(l);
	}

}
