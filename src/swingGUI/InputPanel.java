package swingGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @description ������һ������򣬰���һ���ı����������ť
 * @function ����ı���
 * @function �����ı�����ı�
 * @function ����ť1���Ӽ�����
 * @function ����ť2���Ӽ�����
 */
public class InputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private TextPane textPane = new TextPane();
	private JPanel buttonPanel = new JPanel();
	private JButton button1 = null;
	private JButton button2 = null;

	/**
	 * @description �вι��캯��
	 * @description ����������������������ť
	 * @description ���ð�ť��岼�ַ�ʽ
	 * @description ��Ӱ�ť����ť�����
	 * @description �����Լ��Ĳ��ַ�ʽ
	 * @description ����ı�������λ��
	 * @description ��Ӱ�ť��嵽����
	 */
	public InputPanel(String name1, String name2) {
		button1 = new JButton(name1);
		button2 = new JButton(name2);
		buttonPanel.setLayout(new GridLayout(2, 1));
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		this.setLayout(new BorderLayout());
		this.add(textPane, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.EAST);
	}

	/**
	 * @description ����ı���
	 */
	public void clear() {
		textPane.clear();
	}

	/**
	 * @description �����ı�����ı�
	 * @return ����һ��String
	 */
	public String getText() {
		return textPane.getText();
	}

	/**
	 * @description ����ť1���Ӽ�����
	 */
	public void addActionListener1(ActionListener l) {
		button1.addActionListener(l);
	}

	/**
	 * @description ����ť2���Ӽ�����
	 */
	public void addActionListener2(ActionListener l) {
		button2.addActionListener(l);
	}

}
