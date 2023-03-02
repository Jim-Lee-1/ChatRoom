package chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import swingGUI.ClientFrame;

/**
 * @description ������ǿͻ����࣬��name��ΪΨһ��ʶ
 * @description �����ӵ��1��name��1��socket��4��ͨ��������1��UI��
 * @description ���̳߳������շ���������Ϣ������ʾ���ı�����
 * @description �̻߳������Ϣ��������������������û��б��
 * @description ��������Ⱥ����Ϣ
 * @description �����߻��Զ�������Ӧ��Ϣ��������
 */
public class Client {

	private String name = null;
	private Socket s = null;
	private OutputStream os = null;
	private DataOutputStream dos = null;
	private InputStream is = null;
	private DataInputStream dis = null;
	private ClientFrame clientUI = null;

	/**
	 * @description �޲ι��캯��
	 * @description ���ӻ��������ã���ʼ��ʱ��Ҫ�����û�����
	 * @description ���췽����᳢�����ӷ����������ӳɹ�����ĸ���
	 * @description ����������Ϣ����������Ȼ����������������û��б�
	 * @description ��Ⱥ����ť��Ӽ�����
	 * @description ���ڹر�ʱ����������Ϣ
	 */
	public Client() {
		// ���ӻ���������
		name = JOptionPane.showInputDialog("�û�����");
		clientUI = new ClientFrame(name + "��������");
		clientUI.setVisible(true);
		try {
			// ����Socket����4����
			s = new Socket("127.0.0.1", 8888);
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			is = s.getInputStream();
			dis = new DataInputStream(is);
			// ����������Ϣ��������
			sendMsg("ONLINE#" + name);
			// ���ݷ���������Ϣ��ʼ�������б�
			String str = dis.readUTF();
			while (!str.equals("END#")) {
				clientUI.addUser(str);
				str = dis.readUTF();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ��Ⱥ����ť��Ӽ�����
		clientUI.addActionListener1(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = clientUI.getText();
				sendMsg("PUBLIC#" + name + "#" + text);
				clientUI.clear();
			}
		});
		// �����ӻ�������Ӽ��������ر�ʱ����������Ϣ
		clientUI.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sendMsg("OFFLINE#" + name);
				System.exit(0);
//				closeAll();
			}
		});
	}

	/**
	 * @description ������Ϣ��������
	 */
	public void sendMsg(String msg) {
		try {
			dos.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @description �������շ���������Ϣ
	 * @description ���Ҹ�����Ϣ��������в���
	 */
	public void keepListening() {
		while (true) {
			try {
				String msg = dis.readUTF();
				String[] strs = msg.split("#");
				// ĳ��Ⱥ����Ϣ "PUBLIC#user#message"
				if (strs[0].equals("PUBLIC")) {
					clientUI.append(strs[1] + "��" + strs[2]);
				}
				// ĳ��������Ϣ "ONLINE#user"
				else if (strs[0].equals("ONLINE")) {
					clientUI.addUser(strs[1]);
					clientUI.append("System��" + strs[1] + "�����ߣ�");
				}
				// ĳ��������Ϣ "OFFLINE#user"
				else if (strs[0].equals("OFFLINE")) {
					clientUI.removeUser(strs[1]);
					clientUI.append("System��" + strs[1] + "�����ߣ�");
				}
				// ĳ�˱�����Ϣ "KICK#user"
				else if (strs[0].equals("KICK")) {
					clientUI.removeUser(strs[1]);
					clientUI.append("System��" + strs[1] + "�����ˣ�");
				}
				// �Լ�������Ϣ "KICKED"
				else if (strs[0].equals("KICKED")) {
					JOptionPane.showMessageDialog(this.clientUI, "�㱻���ˣ�");
//					closeAll();
					sendMsg("KICK#"+this.name);
					System.exit(0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @description �رո�����Դ���˳�����ʱ���õ�
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

	// δʵ�ֹ��ܣ�˽�ģ���˽��
	public static void main(String[] args) {
		Client client = new Client();
		client.keepListening();
	}

}
