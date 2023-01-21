package sell;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class sellListFrame extends JFrame {
	
	query dao = new query();
	Object columnNames[] = {"�̸�","����","����"};
	LineBorder Line = new LineBorder(Color.BLACK, 1);
	JTextField field = null, field2 = null, field3 = null, field4 = null, field5 = null;
	JTable jtable2 = null, jtable4 = null;
	
	public sellListFrame() {
		super("���� ����");
		setSize(1200, 650);
	    setVisible(true);
	    getContentPane().setBackground(Color.WHITE);
	    setLocation(900, 100);
	    getContentPane().setLayout(null);
	    setLocationRelativeTo(null); //ȭ�� �߾ӿ� �߰� �ϱ�
	    
	    // ���� �Ǹ� ����Ʈ �׸���
	    drawDay();
	    
	    // ���� �Ǹ� ����Ʈ �׸���
	    drawMonth();
	    
	    // ���� �޴��� �Ǹŷ� �׸���
	    drawDayMenu();
	    
	    // �Ϻ� �Ǹ� ����Ʈ �׸���
	    drawDayList();
	}
	
	public Vector head() {
		Vector head = new Vector();
		
		head.add("�Ǹ� ��¥");
		head.add("�޴��̸�");
		head.add("����");
		head.add("����");
		
		return head;
	}
	
	public Vector head2() {
		Vector head = new Vector();
		
		head.add("�޴��̸�");
		head.add("����");
		
		return head;
	}
	
	// ���� �Ǹ� ����Ʈ �׸���
	public void drawDay() {
	    DefaultTableModel model = new DefaultTableModel(dao.getSellList1(), head());
	    
	    JLabel label = new JLabel("���� �ǸŸ���Ʈ");
	    label.setFont(new Font("����", Font.BOLD, 20));
	    label.setBounds(30, 21, 555, 20);
	    add(label);
	    
	    JTable jtable = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(jtable);
	    scrollPane.getViewport().setBackground(new Color(255, 255, 255));
	    scrollPane.setBorder(Line);
	    scrollPane.setBounds(30, 41, 555, 250);
	    add(scrollPane);
	}
	
	// ���� �Ǹ� ����Ʈ �׸���
	public void drawMonth() {
		Date now = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sd1 = new SimpleDateFormat("yy/MM/dd");
		String mowTime = sd.format(now);
		String nowTime1 = sd1.format(now);
		
		DefaultTableModel model = new DefaultTableModel(dao.getSellList2(nowTime1), head());
	    
		JLabel label = new JLabel("���� �ǸŸ���Ʈ");
	    label.setFont(new Font("����", Font.BOLD, 20));
	    label.setBounds(615, 21, 180, 20);
	    add(label);
	    
	    // ���� Setting
        JLabel label2 = new JLabel("���� : ");
        label2.setFont(new Font("����", Font.BOLD, 15));
        label2.setBounds(780, 21, 50, 20);
	    add(label2);
        field = new JTextField(mowTime.split("/")[0]);  
        field.setBounds(830, 21, 40, 20);
        add(field);
        
        // �� Setting
        JLabel label3 = new JLabel("�� : ");
        label3.setFont(new Font("����", Font.BOLD, 15));
        label3.setBounds(880, 21, 50, 20);
	    add(label3);
        field2 = new JTextField(mowTime.split("/")[1]);  
        field2.setBounds(910, 21, 30, 20);
        add(field2);
        
        // ��ȸ ��ư ����
        JButton btnSearch = new JButton("��ȸ");
        btnSearch.setBorder(Line);
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setBounds(960, 20, 60, 20);
        add(btnSearch);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// �Է°� Ȯ��
            	if(field.getText().equals("")) {
            		JOptionPane.showMessageDialog(null, "������ �Է����ּ���");
            		return;
            	} else if(field.getText().length() != 4) {
            		JOptionPane.showMessageDialog(null, "������ ���������� �Է����ּ���.\nex)2022");
            		return;
            	} else if(field2.getText().equals("")) {
            		JOptionPane.showMessageDialog(null, "���ڸ� �Է����ּ���.");
            		return;
            	}
            	
            	// ���� ��ȸ
            	String str = field.getText().substring(2) + "/" + field2.getText() + "/01";
            	DefaultTableModel model = new DefaultTableModel(dao.getSellList2(str), head());
            	jtable2.setModel(model);
            }
        });
		
	    jtable2 = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(jtable2);
	    scrollPane.getViewport().setBackground(new Color(255, 255, 255));
	    scrollPane.setBorder(Line);
	    scrollPane.setBounds(615, 41, 555, 250);
	    add(scrollPane);
	}
	
	// ���� �޴��� �Ǹŷ� �׸���
	public void drawDayMenu() {
		DefaultTableModel model = new DefaultTableModel(dao.getSellList3(), head2());
		
		JLabel label = new JLabel("���� �޴��� �Ǹŷ�");
	    label.setFont(new Font("����", Font.BOLD, 20));
	    label.setBounds(30, 321, 555, 20);
	    add(label);
	    
	    JTable jtable = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(jtable);
	    scrollPane.getViewport().setBackground(new Color(255, 255, 255));
	    scrollPane.setBorder(Line);
	    scrollPane.setBounds(30, 341, 555, 250);
	    add(scrollPane);
	}
	
	// �Ϻ� �Ǹ� ����Ʈ �׸���
	public void drawDayList() {
		Date now = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sd1 = new SimpleDateFormat("yy/MM/dd");
		String mowTime = sd.format(now);
		String nowTime1 = sd1.format(now);
		
		DefaultTableModel model = new DefaultTableModel(dao.getSellList4(nowTime1), head());
		
		JLabel label = new JLabel("�Ϻ� �ǸŸ���Ʈ");
	    label.setFont(new Font("����", Font.BOLD, 20));
	    label.setBounds(615, 321, 180, 20);
	    add(label);
		
		// ���� Setting
        JLabel label2 = new JLabel("���� : ");
        label2.setFont(new Font("����", Font.BOLD, 15));
        label2.setBounds(780, 321, 50, 20);
	    add(label2);
        field3 = new JTextField(mowTime.split("/")[0]);  
        field3.setBounds(830, 321, 40, 20);
        add(field3);
        
        // �� Setting
        JLabel label3 = new JLabel("�� : ");
        label3.setFont(new Font("����", Font.BOLD, 15));
        label3.setBounds(880, 321, 50, 20);
	    add(label3);
        field4 = new JTextField(mowTime.split("/")[1]);  
        field4.setBounds(910, 321, 30, 20);
        add(field4);
        
        // �� Setting
        JLabel label4 = new JLabel("�� : ");
        label4.setFont(new Font("����", Font.BOLD, 15));
        label4.setBounds(950, 321, 50, 20);
	    add(label4);
        field5 = new JTextField(mowTime.split("/")[2]);  
        field5.setBounds(980, 321, 30, 20);
        add(field5);
        
        // ��ȸ ��ư ����
        JButton btnSearch = new JButton("��ȸ");
        btnSearch.setBorder(Line);
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setBounds(1020, 320, 60, 20);
        add(btnSearch);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// �Է°� Ȯ��
            	if(field3.getText().equals("")) {
            		JOptionPane.showMessageDialog(null, "������ �Է����ּ���");
            		return;
            	} else if(field3.getText().length() != 4) {
            		JOptionPane.showMessageDialog(null, "������ ���������� �Է����ּ���.\nex)2022");
            		return;
            	} else if(field4.getText().equals("")) {
            		JOptionPane.showMessageDialog(null, "���ڸ� �Է����ּ���.");
            		return;
            	} else if(field5.getText().equals("")) {
            		JOptionPane.showMessageDialog(null, "���ڸ� �Է����ּ���.");
            		return;
            	}
            	
            	// ���� ��ȸ
            	String str = field3.getText().substring(2) + "/" + field4.getText() + "/" + field5.getText();
            	DefaultTableModel model = new DefaultTableModel(dao.getSellList4(str), head());
            	jtable4.setModel(model);
            }
        });

	    jtable4 = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(jtable4);
	    scrollPane.getViewport().setBackground(new Color(255, 255, 255));
	    scrollPane.setBorder(Line);
	    scrollPane.setBounds(615, 341, 555, 250);
	    add(scrollPane);
	}
}
