package sell;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class cntrFrame extends JFrame implements MouseListener,ActionListener {

	Vector vec = new Vector();
	query dao = new query();
	DefaultTableModel model;
	GridBagConstraints gbc;
	GridBagLayout gridLayout;
	JTable jTable;
	
	public static void main(String[] args) {
		new cntrFrame();
	}
	
	public cntrFrame() {
		super("�޴�����");
		JPanel Area = new JPanel();
		JButton btnAdd = new JButton("�޴��߰�");
		
		vec = dao.selectMenu2();

		model = new DefaultTableModel(vec, head());
		jTable = new JTable(model);
		JScrollPane jpane = new JScrollPane(jTable);
		add(jpane);
		
		jTable.addMouseListener(this); //������ ���
		
		Area.setBackground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.addActionListener(this);
		Area.add(btnAdd);
        add(Area, BorderLayout.NORTH);
        
        jpane.getViewport().setBackground(new Color(255, 255, 255));
        setSize(600,200);
        setVisible(true);
        setLocationRelativeTo(null); //ȭ�� �߾ӿ� �߰� �ϱ�
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public Vector head() {
		Vector head = new Vector();
		
		head.add("�޴���ȣ");
		head.add("�޴��̸�");
		head.add("����");
		head.add("��� ����");
		
		return head;
	}
	
	public void refresh() {
		DefaultTableModel model= new DefaultTableModel(dao.selectMenu2(), head());
        jTable.setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new addMenuFrame(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = jTable.getSelectedRow();
		int menuNum = Integer.parseInt(jTable.getValueAt(row, 0).toString()); // ������ �޴� ��ȣ
		
		new updateMenuFrame(this, menuNum);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
