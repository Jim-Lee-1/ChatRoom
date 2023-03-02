package swingGUI;

import java.awt.HeadlessException;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

/**
 * @description 这个类是服务器的可视化界面
 * @description 包含两个组件:一个用户列表和一个输入框
 * @description 包含用户列表和输入框的所有功能(8个)
 */
public class ServerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private UserListPane userListPane = new UserListPane();
	private InputPanel inputPanel = new InputPanel("群发", "踢人");
	private JSplitPane major = new JSplitPane(JSplitPane.VERTICAL_SPLIT, userListPane, inputPanel);

	public ServerFrame() throws HeadlessException {
		this.setTitle("服务器");// 名称
		this.setSize((int) (500 * 0.618), 500);// 大小
		this.setLocation(1200, 200);// 位置
		major.setDividerLocation(380);// 分界线
		this.setContentPane(major);// 设置内容窗格
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭
	}

	/**
	 * @description 返回被选定用户
	 * @return 返回一个String，若无用户被选中则返回null
	 */
	public String getSelectedUser() {
		return userListPane.getSelectedUser();
	}

	/**
	 * @description 返回所有用户
	 * @return 返回一个String数组
	 */
	public String[] getAllUser() {
		return userListPane.getAllUser();
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
