package swingGUI;

import java.awt.HeadlessException;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

/**
 * @description ������Ƿ������Ŀ��ӻ�����
 * @description �����������:һ���û��б��һ�������
 * @description �����û��б�����������й���(8��)
 */
public class ServerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private UserListPane userListPane = new UserListPane();
	private InputPanel inputPanel = new InputPanel("Ⱥ��", "����");
	private JSplitPane major = new JSplitPane(JSplitPane.VERTICAL_SPLIT, userListPane, inputPanel);

	public ServerFrame() throws HeadlessException {
		this.setTitle("������");// ����
		this.setSize((int) (500 * 0.618), 500);// ��С
		this.setLocation(1200, 200);// λ��
		major.setDividerLocation(380);// �ֽ���
		this.setContentPane(major);// �������ݴ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �ر�
	}

	/**
	 * @description ���ر�ѡ���û�
	 * @return ����һ��String�������û���ѡ���򷵻�null
	 */
	public String getSelectedUser() {
		return userListPane.getSelectedUser();
	}

	/**
	 * @description ���������û�
	 * @return ����һ��String����
	 */
	public String[] getAllUser() {
		return userListPane.getAllUser();
	}

	/**
	 * @description �����û�
	 */
	public void addUser(String name) {
		userListPane.addUser(name);
	}

	/**
	 * @description ɾ���û�
	 */
	public boolean removeUser(String name) {
		return userListPane.removeUser(name);
	}

	/**
	 * @description ����ı���
	 */
	public void clear() {
		inputPanel.clear();
	}

	/**
	 * @description �����ı�����ı�
	 * @return ����һ��String
	 */
	public String getText() {
		return inputPanel.getText();
	}

	/**
	 * @description ����ť1���Ӽ�����
	 */
	public void addActionListener1(ActionListener l) {
		inputPanel.addActionListener1(l);
	}

	/**
	 * @description ����ť2���Ӽ�����
	 */
	public void addActionListener2(ActionListener l) {
		inputPanel.addActionListener2(l);
	}

}
