package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import swingGUI.ServerFrame;

/**
 * @description ������Ƿ�������
 * @description ����1��ServerSocket��1��ServerUI��1��ChatThread����
 * @description ���̳߳�������8888�˿ڣ��½��������ӻᱻ��������ChatThread
 * @description ��������Ⱥ����Ϣ
 */
public class Server {

	private ServerSocket ss = null;
	private ServerFrame serverUI = new ServerFrame();
	private List<ChatThread> chatThreads = new Vector<>();

	/**
	 * @description �޲ι��캯��
	 * @description ���ӻ���������
	 * @description ��ʼ������
	 */
	public Server() throws IOException {
		serverUI.setVisible(true);
		ss = new ServerSocket(8888);
	}

	/**
	 * @description ���������˿�
	 * @description ÿ�����ӳɹ�������һ��ChatThreadʵ��
	 * @description ���Ѹ��߳�ʵ���ӽ�chatThreads��Ȼ��������������
	 */
	public void keepAccepting() {
		boolean flag = true;
		while (flag) {
			try {
				Socket s = ss.accept();
				ChatThread c = new ChatThread("???", s, this);
				Thread t = new Thread(c);
				t.start();
				synchronized (chatThreads) {
					chatThreads.add(c);
				}

			} catch (IOException e) {
				flag = false;
				e.printStackTrace();
			}
		}
	}

	/**
	 * @description ������Ϣ��ָ���û�
	 */
	public void send(ChatThread ct, String msg) {
		ct.send(msg);
	}

	/**
	 * @description ������Ϣ�������û�
	 */
	public void sendAll(String msg) {
		for (ChatThread ct : chatThreads) {
			ct.send(msg);
		}
	}

	/**
	 * @description ���������û��б��͸�ָ���û�
	 */
	public void sendAllUsers(ChatThread ct) {
		String[] users = getAllUser();
		for (String user : users) {
			ct.send(user);
		}
	}

	/**
	 * @description ɾ��ָ��ChatThread
	 */
	public void removeCT(ChatThread ct) {
		chatThreads.remove(ct);
	}

	/**
	 * @description ���ر�ѡ���û�
	 * @return ����һ��String�������û���ѡ���򷵻�null
	 */
	public String getSelectedUser() {
		return serverUI.getSelectedUser();
	}

	/**
	 * @description ���������û�
	 * @return ����һ��String����
	 */
	public String[] getAllUser() {
		return serverUI.getAllUser();
	}

	/**
	 * @description �����û�
	 */
	public void addUser(String name) {
		serverUI.addUser(name);
	}

	/**
	 * @description ɾ���û�
	 */
	public boolean removeUser(String name) {
		return serverUI.removeUser(name);
	}

	/**
	 * @description ����ı���
	 */
	public void clear() {
		serverUI.clear();
	}

	/**
	 * @description �����ı�����ı�
	 * @return ����һ��String
	 */
	public String getText() {
		return serverUI.getText();
	}

	/**
	 * @description ����ť1���Ӽ�����
	 */
	public void addActionListener1(ActionListener l) {
		serverUI.addActionListener1(l);
	}

	/**
	 * @description ����ť2���Ӽ�����
	 */
	public void addActionListener2(ActionListener l) {
		serverUI.addActionListener2(l);
	}

	// δʵ�֣�Ⱥ��������ģʽ
	public static void main(String[] args) throws IOException {
		Server s = new Server();
		// Ⱥ������ʵ��
		s.addActionListener1(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = s.getText();
				s.sendAll("PUBLIC#System#" + text);
				s.clear();
			}
		});
		// ���˹���ʵ��
		s.addActionListener2(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = s.getSelectedUser();
				for (ChatThread ct : s.chatThreads) {
					if (ct.is(name)) {
						ct.send("KICKED");
					}
				}
			}
		});
		s.keepAccepting();
	}
}
