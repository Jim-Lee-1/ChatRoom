package swingGUI;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * @description �������ӻ���ʾһ���û��б�
 * @function ���ر�ѡ���û�
 * @function ���������û�
 * @function �����û�
 * @function ɾ���û�
 */
public class UserListPane extends JScrollPane {

	private static final long serialVersionUID = 1L;// ��䲻�ù�
	private DefaultListModel<String> model = new DefaultListModel<String>();// ���model�������userListһ����
	private JList<String> userList = new JList<String>(model);// model��������userList��

	/**
	 * @description �޲ι��캯��
	 * @description ����userListΪ��ѡģʽ
	 * @description ����userListΪ��ʾ����
	 */
	public UserListPane() {
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// ��ѡģʽ
		this.setViewportView(userList);// ������ʾ����ΪuserList
	}

	/**
	 * @description ���ر�ѡ���û�
	 * @return ����һ��String�������û���ѡ���򷵻�null
	 */
	public String getSelectedUser() {
		return userList.getSelectedValue();
	}

	/**
	 * @description ���������û�
	 * @return ����һ��String����
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
	 * @description �����û�
	 */
	public void addUser(String name) {
		model.addElement(name);
	}

	/**
	 * @description ɾ���û�
	 * @return ���������򷵻�true������false
	 */
	public boolean removeUser(String name) {
		return model.removeElement(name);
	}

}
