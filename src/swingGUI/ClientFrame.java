package swingGUI;

import java.awt.HeadlessException;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

/**
 * @description 这个类是客户端的可视化界面
 * @description 包含三个组件:用户列表、文本框和输入框
 * @description 这个类能进行的8个操作有：
 * @function 文本框: 显示消息
 * @function 用户列表: 返回被选定用户、增加用户、删除用户
 * @function 输入框: 原本的4个功能
 */
public class ClientFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private TextPane textPane = new TextPane();
	private InputPanel inputPanel = new InputPanel("群发", "私聊");
	private UserListPane userListPane = new UserListPane();
	private JSplitPane rightPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, textPane, inputPanel);
	private JSplitPane major = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, userListPane, rightPane);

	public ClientFrame(String name) throws HeadlessException {
		this.setTitle(name);// 名称
		this.setSize(700, (int) (700 * 0.618));// 大小
		this.setLocation(100, 100);// 位置
		rightPane.setDividerLocation(300);// 分界线
		major.setDividerLocation(150);// 分界线
		this.setContentPane(major);// 设置内容窗格
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭
	}

	/**
	 * @description 显示消息
	 */
	public void append(String str) {
		textPane.append(str);
	}

	/**
	 * @description 返回被选定用户
	 * @return 返回一个String，若无用户被选中则返回null
	 */
	public String getSelectedUser() {
		return userListPane.getSelectedUser();
	}

	/**
	 * @description 增加用户
	 */
	public void addUser(String name) {
		userListPane.addUser(name);
	}

	/**
	 * @description 删除用户
	 */
	public boolean removeUser(String name) {
		return userListPane.removeUser(name);
	}

	/**
	 * @description 清空文本框
	 */
	public void clear() {
		inputPanel.clear();
	}

	/**
	 * @description 返回文本框的文本
	 * @return 返回一个String
	 */
	public String getText() {
		return inputPanel.getText();
	}

	/**
	 * @description 给按钮1增加监听器
	 */
	public void addActionListener1(ActionListener l) {
		inputPanel.addActionListener1(l);
	}

	/**
	 * @description 给按钮2增加监听器
	 */
	public void addActionListener2(ActionListener l) {
		inputPanel.addActionListener2(l);
	}

}
