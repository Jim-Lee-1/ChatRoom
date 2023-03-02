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
 * @description 这个类是客户端类，以name作为唯一标识
 * @description 这个类拥有1个name，1个socket，4个通信流，和1个UI类
 * @description 主线程持续接收服务器的消息并且显示到文本框上
 * @description 线程会根据消息做出操作，如更新在线用户列表等
 * @description 输入框可以群发消息
 * @description 上下线会自动发送相应消息给服务器
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
	 * @description 无参构造函数
	 * @description 可视化界面设置，初始化时需要输入用户名字
	 * @description 构造方法里会尝试连接服务器，连接成功后打开四个流
	 * @description 发送上线消息给服务器，然后服务器返回在线用户列表
	 * @description 给群发按钮添加监听器
	 * @description 窗口关闭时发送下线消息
	 */
	public Client() {
		// 可视化界面设置
		name = JOptionPane.showInputDialog("用户名：");
		clientUI = new ClientFrame(name + "的聊天室");
		clientUI.setVisible(true);
		try {
			// 连接Socket，打开4个流
			s = new Socket("127.0.0.1", 8888);
			os = s.getOutputStream();
			dos = new DataOutputStream(os);
			is = s.getInputStream();
			dis = new DataInputStream(is);
			// 发送上线消息给服务器
			sendMsg("ONLINE#" + name);
			// 根据服务器的消息初始化在线列表
			String str = dis.readUTF();
			while (!str.equals("END#")) {
				clientUI.addUser(str);
				str = dis.readUTF();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 给群发按钮添加监听器
		clientUI.addActionListener1(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = clientUI.getText();
				sendMsg("PUBLIC#" + name + "#" + text);
				clientUI.clear();
			}
		});
		// 给可视化界面添加监听器，关闭时发送下线消息
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
	 * @description 发送消息给服务器
	 */
	public void sendMsg(String msg) {
		try {
			dos.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @description 持续接收服务器的消息
	 * @description 并且根据消息的种类进行操作
	 */
	public void keepListening() {
		while (true) {
			try {
				String msg = dis.readUTF();
				String[] strs = msg.split("#");
				// 某人群发消息 "PUBLIC#user#message"
				if (strs[0].equals("PUBLIC")) {
					clientUI.append(strs[1] + "：" + strs[2]);
				}
				// 某人上线消息 "ONLINE#user"
				else if (strs[0].equals("ONLINE")) {
					clientUI.addUser(strs[1]);
					clientUI.append("System：" + strs[1] + "已上线！");
				}
				// 某人下线消息 "OFFLINE#user"
				else if (strs[0].equals("OFFLINE")) {
					clientUI.removeUser(strs[1]);
					clientUI.append("System：" + strs[1] + "已下线！");
				}
				// 某人被踢消息 "KICK#user"
				else if (strs[0].equals("KICK")) {
					clientUI.removeUser(strs[1]);
					clientUI.append("System：" + strs[1] + "被踢了！");
				}
				// 自己被踢消息 "KICKED"
				else if (strs[0].equals("KICKED")) {
					JOptionPane.showMessageDialog(this.clientUI, "你被踢了！");
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
	 * @description 关闭各种资源，退出程序时会用到
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

	// 未实现功能：私聊，被私聊
	public static void main(String[] args) {
		Client client = new Client();
		client.keepListening();
	}

}
