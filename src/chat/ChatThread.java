package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @description 这个类是聊天线程类，以name作为唯一标识
 * @description 这个类拥有1个名字，1个socket，4个通信流，和1个server类
 * @description 初始化需要name,socket,和server类
 * @description 此线程持续接收对应客户端的消息，并根据消息做出操作
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
	 * @description 有参构造函数
	 * @description 根据三个参数初始化三个属性
	 * @description 打开四个通信流
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
	 * @description 持续接收服务器的消息
	 * @description 并且根据消息的种类进行操作
	 */
	@Override
	public void run() {
		while (true) {
			try {
				String msg = dis.readUTF();
				String[] strs = msg.split("#");
				// 群发消息 "PUBLIC#user#message"
				if (strs[0].equals("PUBLIC")) {
					server.sendAll(msg);
				}
				// 上线消息 "ONLINE#user"
				else if (strs[0].equals("ONLINE")) {
					server.sendAllUsers(this);
					send("END#");
					server.sendAll(msg);
					name = strs[1];
					server.addUser(strs[1]);
				}
				// 下线消息 "OFFLINE#user"
				else if (strs[0].equals("OFFLINE")) {
//					closeAll();
					server.removeCT(this);
					server.removeUser(strs[1]);
					server.sendAll(msg);
					break;
				}
				// 踢人消息 "KICK#user"
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
	 * @description 发送消息给客户端
	 */
	public void send(String msg) {
		try {
			dos.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @description 自己的名字是name就返回true，否则false
	 */
	public boolean is(String name) {
		return this.name.equals(name);
	}

	/**
	 * @description 关闭各种资源
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
