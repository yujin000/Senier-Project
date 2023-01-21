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
		super("메뉴관리");
		JPanel Area = new JPanel();
		JButton btnAdd = new JButton("메뉴추가");
		
		vec = dao.selectMenu2();

		model = new DefaultTableModel(vec, head());
		jTable = new JTable(model);
		JScrollPane jpane = new JScrollPane(jTable);
		add(jpane);
		
		jTable.addMouseListener(this); //리스너 등록
		
		Area.setBackground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.addActionListener(this);
		Area.add(btnAdd);
        add(Area, BorderLayout.NORTH);
        
        jpane.getViewport().setBackground(new Color(255, 255, 255));
        setSize(600,200);
        setVisible(true);
        setLocationRelativeTo(null); //화면 중앙에 뜨게 하기
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public Vector head() {
		Vector head = new Vector();
		
		head.add("메뉴번호");
		head.add("메뉴이름");
		head.add("가격");
		head.add("재고 수량");
		
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
		int menuNum = Integer.parseInt(jTable.getValueAt(row, 0).toString()); // 선택한 메뉴 번호
		
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
