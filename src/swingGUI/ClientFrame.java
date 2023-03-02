package swingGUI;

import java.awt.HeadlessException;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

/**
 * @description ������ǿͻ��˵Ŀ��ӻ�����
 * @description �����������:�û��б��ı���������
 * @description ������ܽ��е�8�������У�
 * @function �ı���: ��ʾ��Ϣ
 * @function �û��б�: ���ر�ѡ���û��������û���ɾ���û�
 * @function �����: ԭ����4������
 */
public class ClientFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private TextPane textPane = new TextPane();
	private InputPanel inputPanel = new InputPanel("Ⱥ��", "˽��");
	private UserListPane userListPane = new UserListPane();
	private JSplitPane rightPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, textPane, inputPanel);
	private JSplitPane major = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, userListPane, rightPane);

	public ClientFrame(String name) throws HeadlessException {
		this.setTitle(name);// ����
		this.setSize(700, (int) (700 * 0.618));// ��С
		this.setLocation(100, 100);// λ��
		rightPane.setDividerLocation(300);// �ֽ���
		major.setDividerLocation(150);// �ֽ���
		this.setContentPane(major);// �������ݴ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �ر�
	}

	/**
	 * @description ��ʾ��Ϣ
	 */
	public void append(String str) {
		textPane.append(str);
	}

	/**
	 * @description ���ر�ѡ���û�
	 * @return ����һ��String�������û���ѡ���򷵻�null
	 */
	public String getSelectedUser() {
		return userListPane.getSelectedUser();
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
