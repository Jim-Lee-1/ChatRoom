package swingGUI;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @description �������ı�����
 * @function ����ı���
 * @function �����ı�����ı�
 * @function ��ʾ��Ϣ
 */
public class TextPane extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private JTextArea textArea = new JTextArea();

	/**
	 * @description �޲ι��캯��
	 * @description ����textAreaΪ�Զ�����
	 * @description ����textAreaΪ��ʾ����
	 */
	public TextPane() {
		textArea.setLineWrap(true);
		this.setViewportView(textArea);
	}

	/**
	 * @description ����ı���
	 */
	public void clear() {
		textArea.setText("");
	}

	/**
	 * @description �����ı�����ı�
	 * @return ����һ��String
	 */
	public String getText() {
		return textArea.getText();
	}

	/**
	 * @description ��ʾ��Ϣ
	 */
	public void append(String msg) {
		textArea.append(msg + "\n");
	}

}
