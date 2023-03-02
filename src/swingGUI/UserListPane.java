package swingGUI;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * @description 此类会可视化显示一个用户列表
 * @function 返回被选定用户
 * @function 返回所有用户
 * @function 增加用户
 * @function 删除用户
 */
public class UserListPane extends JScrollPane {

	private static final long serialVersionUID = 1L;// 这句不用管
	private DefaultListModel<String> model = new DefaultListModel<String>();// 这个model和下面的userList一起用
	private JList<String> userList = new JList<String>(model);// model用来管理userList的

	/**
	 * @description 无参构造函数
	 * @description 设置userList为单选模式
	 * @description 设置userList为显示内容
	 */
	public UserListPane() {
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 单选模式
		this.setViewportView(userList);// 设置显示内容为userList
	}

	/**
	 * @description 返回被选定用户
	 * @return 返回一个String，若无用户被选中则返回null
	 */
	public String getSelectedUser() {
		return userList.getSelectedValue();
	}

	/**
	 * @description 返回所有用户
	 * @return 返回一个String数组
	 */
	public String[] getAllUser() {
		int length = model.getSize();
		String[] users = new String[length];
		for (int i = 0; i < length; i++) {
			users[i] = model.getElementAt(i);
		}
		return users;
	}

	/**
	 * @description 增加用户
	 */
	public void addUser(String name) {
		model.addElement(name);
	}

	/**
	 * @description 删除用户
	 * @return 参数存在则返回true，否则false
	 */
	public boolean removeUser(String name) {
		return model.removeElement(name);
	}

}
