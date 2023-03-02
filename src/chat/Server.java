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
 * @description 这个类是服务器类
 * @description 包含1个ServerSocket，1个ServerUI，1个ChatThread集合
 * @description 主线程持续监听8888端口，新建立的连接会被用来构造ChatThread
 * @description 输入框可以群发消息
 */
public class Server {

	private ServerSocket ss = null;
	private ServerFrame serverUI = new ServerFrame();
	private List<ChatThread> chatThreads = new Vector<>();

	/**
	 * @description 无参构造函数
	 * @description 可视化界面设置
	 * @description 初始化属性
	 */
	public Server() throws IOException {
		serverUI.setVisible(true);
		ss = new ServerSocket(8888);
	}

	/**
	 * @description 持续监听端口
	 * @description 每次连接成功都创建一个ChatThread实例
	 * @description 并把该线程实例加进chatThreads，然后让它运行起来
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
	 * @description 发送消息给指定用户
	 */
	public void send(ChatThread ct, String msg) {
		ct.send(msg);
	}

	/**
	 * @description 发送消息给所有用户
	 */
	public void sendAll(String msg) {
		for (ChatThread ct : chatThreads) {
			ct.send(msg);
		}
	}

	/**
	 * @description 发送在线用户列表发送给指定用户
	 */
	public void sendAllUsers(ChatThread ct) {
		String[] users = getAllUser();
		for (String user : users) {
			ct.send(user);
		}
	}

	/**
	 * @description 删除指定ChatThread
	 */
	public void removeCT(ChatThread ct) {
		chatThreads.remove(ct);
	}

	/**
	 * @description 返回被选定用户
	 * @return 返回一个String，若无用户被选中则返回null
	 */
	public String getSelectedUser() {
		return serverUI.getSelectedUser();
	}

	/**
	 * @description 返回所有用户
	 * @return 返回一个String数组
	 */
	public String[] getAllUser() {
		return serverUI.getAllUser();
	}

	/**
	 * @description 增加用户
	 */
	public void addUser(String name) {
		serverUI.addUser(name);
	}

	/**
	 * @description 删除用户
	 */
	public boolean removeUser(String name) {
		return serverUI.removeUser(name);
	}

	/**
	 * @description 清空文本框
	 */
	public void clear() {
		serverUI.clear();
	}

	/**
	 * @description 返回文本框的文本
	 * @return 返回一个String
	 */
	public String getText() {
		return serverUI.getText();
	}

	/**
	 * @description 给按钮1增加监听器
	 */
	public void addActionListener1(ActionListener l) {
		serverUI.addActionListener1(l);
	}

	/**
	 * @description 给按钮2增加监听器
	 */
	public void addActionListener2(ActionListener l) {
		serverUI.addActionListener2(l);
	}

	// 未实现：群发，单例模式
	public static void main(String[] args) throws IOException {
		Server s = new Server();
		// 群发功能实现
		s.addActionListener1(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = s.getText();
				s.sendAll("PUBLIC#System#" + text);
				s.clear();
			}
		});
		// 踢人功能实现
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
