package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @description ������������߳��࣬��name��ΪΨһ��ʶ
 * @description �����ӵ��1�����֣�1��socket��4��ͨ��������1��server��
 * @description ��ʼ����Ҫname,socket,��server��
 * @description ���̳߳������ն�Ӧ�ͻ��˵���Ϣ����������Ϣ��������
 */
public class ChatThread implements Runnable {

	private String name = null;
	private Socket s = null;
	private OutputStream os = null;
	private DataOutputStream dos = null;
	private InputStream is = null;
	private DataInputStream dis = null;
	private Server server = null;

	/**
	 * @description �вι��캯��
	 * @description ��������������ʼ����������
	 * @description ���ĸ�ͨ����
	 */
	public ChatThread(String name, Socket s, Server server) {
		this.name = name;
		this.s = s;
		this.server = server;
		try {
			os = s.getOutputStream();
			is = s.getInputStream();
			dos = new DataOutputStream(os);
			dis = new DataInputStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @description �������շ���������Ϣ
	 * @description ���Ҹ�����Ϣ��������в���
	 */
	@Override
	public void run() {
		while (true) {
			try {
				String msg = dis.readUTF();
				String[] strs = msg.split("#");
				// Ⱥ����Ϣ "PUBLIC#user#message"
				if (strs[0].equals("PUBLIC")) {
					server.sendAll(msg);
				}
				// ������Ϣ "ONLINE#user"
				else if (strs[0].equals("ONLINE")) {
					server.sendAllUsers(this);
					send("END#");
					server.sendAll(msg);
					name = strs[1];
					server.addUser(strs[1]);
				}
				// ������Ϣ "OFFLINE#user"
				else if (strs[0].equals("OFFLINE")) {
//					closeAll();
					server.removeCT(this);
					server.removeUser(strs[1]);
					server.sendAll(msg);
					break;
				}
				// ������Ϣ "KICK#user"
				else if (strs[0].equals("KICK")) {
//					closeAll();
					server.removeCT(this);
					server.removeUser(strs[1]);
					server.sendAll(msg);
					break;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * @description ������Ϣ���ͻ���
	 */
	public void send(String msg) {
		try {
			dos.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @description �Լ���������name�ͷ���true������false
	 */
	public boolean is(String name) {
		return this.name.equals(name);
	}

	/**
	 * @description �رո�����Դ
	 */
	public void closeAll() {
		try {
			dos.close();
			dis.close();
			os.close();
			is.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
