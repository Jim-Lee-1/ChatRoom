package swingGUI;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @description 此类是文本框类
 * @function 清空文本框
 * @function 返回文本框的文本
 * @function 显示消息
 */
public class TextPane extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private JTextArea textArea = new JTextArea();

	/**
	 * @description 无参构造函数
	 * @description 设置textArea为自动换行
	 * @description 设置textArea为显示内容
	 */
	public TextPane() {
		textArea.setLineWrap(true);
		this.setViewportView(textArea);
	}

	/**
	 * @description 清空文本框
	 */
	public void clear() {
		textArea.setText("");
	}

	/**
	 * @description 返回文本框的文本
	 * @return 返回一个String
	 */
	public String getText() {
		return textArea.getText();
	}

	/**
	 * @description 显示消息
	 */
	public void append(String msg) {
		textArea.append(msg + "\n");
	}

}
