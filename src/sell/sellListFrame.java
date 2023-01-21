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
	Object columnNames[] = {"이름","가격","수량"};
	LineBorder Line = new LineBorder(Color.BLACK, 1);
	JTextField field = null, field2 = null, field3 = null, field4 = null, field5 = null;
	JTable jtable2 = null, jtable4 = null;
	
	public sellListFrame() {
		super("매출 관리");
		setSize(1200, 650);
	    setVisible(true);
	    getContentPane().setBackground(Color.WHITE);
	    setLocation(900, 100);
	    getContentPane().setLayout(null);
	    setLocationRelativeTo(null); //화면 중앙에 뜨게 하기
	    
	    // 오늘 판매 리스트 그리기
	    drawDay();
	    
	    // 월별 판매 리스트 그리기
	    drawMonth();
	    
	    // 오늘 메뉴별 판매량 그리기
	    drawDayMenu();
	    
	    // 일별 판매 리스트 그리기
	    drawDayList();
	}
	
	public Vector head() {
		Vector head = new Vector();
		
		head.add("판매 날짜");
		head.add("메뉴이름");
		head.add("가격");
		head.add("수량");
		
		return head;
	}
	
	public Vector head2() {
		Vector head = new Vector();
		
		head.add("메뉴이름");
		head.add("수량");
		
		return head;
	}
	
	// 오늘 판매 리스트 그리기
	public void drawDay() {
	    DefaultTableModel model = new DefaultTableModel(dao.getSellList1(), head());
	    
	    JLabel label = new JLabel("오늘 판매리스트");
	    label.setFont(new Font("굴림", Font.BOLD, 20));
	    label.setBounds(30, 21, 555, 20);
	    add(label);
	    
	    JTable jtable = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(jtable);
	    scrollPane.getViewport().setBackground(new Color(255, 255, 255));
	    scrollPane.setBorder(Line);
	    scrollPane.setBounds(30, 41, 555, 250);
	    add(scrollPane);
	}
	
	// 월별 판매 리스트 그리기
	public void drawMonth() {
		Date now = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sd1 = new SimpleDateFormat("yy/MM/dd");
		String mowTime = sd.format(now);
		String nowTime1 = sd1.format(now);
		
		DefaultTableModel model = new DefaultTableModel(dao.getSellList2(nowTime1), head());
	    
		JLabel label = new JLabel("월별 판매리스트");
	    label.setFont(new Font("굴림", Font.BOLD, 20));
	    label.setBounds(615, 21, 180, 20);
	    add(label);
	    
	    // 연도 Setting
        JLabel label2 = new JLabel("연도 : ");
        label2.setFont(new Font("굴림", Font.BOLD, 15));
        label2.setBounds(780, 21, 50, 20);
	    add(label2);
        field = new JTextField(mowTime.split("/")[0]);  
        field.setBounds(830, 21, 40, 20);
        add(field);
        
        // 월 Setting
        JLabel label3 = new JLabel("월 : ");
        label3.setFont(new Font("굴림", Font.BOLD, 15));
        label3.setBounds(880, 21, 50, 20);
	    add(label3);
        field2 = new JTextField(mowTime.split("/")[1]);  
        field2.setBounds(910, 21, 30, 20);
        add(field2);
        
        // 조회 버튼 생성
        JButton btnSearch = new JButton("조회");
        btnSearch.setBorder(Line);
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setBounds(960, 20, 60, 20);
        add(btnSearch);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// 입력값 확인
            	if(field.getText().equals("")) {
            		JOptionPane.showMessageDialog(null, "연도를 입력해주세요");
            		return;
            	} else if(field.getText().length() != 4) {
            		JOptionPane.showMessageDialog(null, "연도를 정상적으로 입력해주세요.\nex)2022");
            		return;
            	} else if(field2.getText().equals("")) {
            		JOptionPane.showMessageDialog(null, "월자를 입력해주세요.");
            		return;
            	}
            	
            	// 새로 조회
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
	
	// 오늘 메뉴별 판매량 그리기
	public void drawDayMenu() {
		DefaultTableModel model = new DefaultTableModel(dao.getSellList3(), head2());
		
		JLabel label = new JLabel("오늘 메뉴별 판매량");
	    label.setFont(new Font("굴림", Font.BOLD, 20));
	    label.setBounds(30, 321, 555, 20);
	    add(label);
	    
	    JTable jtable = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(jtable);
	    scrollPane.getViewport().setBackground(new Color(255, 255, 255));
	    scrollPane.setBorder(Line);
	    scrollPane.setBounds(30, 341, 555, 250);
	    add(scrollPane);
	}
	
	// 일별 판매 리스트 그리기
	public void drawDayList() {
		Date now = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sd1 = new SimpleDateFormat("yy/MM/dd");
		String mowTime = sd.format(now);
		String nowTime1 = sd1.format(now);
		
		DefaultTableModel model = new DefaultTableModel(dao.getSellList4(nowTime1), head());
		
		JLabel label = new JLabel("일별 판매리스트");
	    label.setFont(new Font("굴림", Font.BOLD, 20));
	    label.setBounds(615, 321, 180, 20);
	    add(label);
		
		// 연도 Setting
        JLabel label2 = new JLabel("연도 : ");
        label2.setFont(new Font("굴림", Font.BOLD, 15));
        label2.setBounds(780, 321, 50, 20);
	    add(label2);
        field3 = new JTextField(mowTime.split("/")[0]);  
        field3.setBounds(830, 321, 40, 20);
        add(field3);
        
        // 월 Setting
        JLabel label3 = new JLabel("월 : ");
        label3.setFont(new Font("굴림", Font.BOLD, 15));
        label3.setBounds(880, 321, 50, 20);
	    add(label3);
        field4 = new JTextField(mowTime.split("/")[1]);  
        field4.setBounds(910, 321, 30, 20);
        add(field4);
        
        // 일 Setting
        JLabel label4 = new JLabel("일 : ");
        label4.setFont(new Font("굴림", Font.BOLD, 15));
        label4.setBounds(950, 321, 50, 20);
	    add(label4);
        field5 = new JTextField(mowTime.split("/")[2]);  
        field5.setBounds(980, 321, 30, 20);
        add(field5);
        
        // 조회 버튼 생성
        JButton btnSearch = new JButton("조회");
        btnSearch.setBorder(Line);
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setBounds(1020, 320, 60, 20);
        add(btnSearch);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// 입력값 확인
            	if(field3.getText().equals("")) {
            		JOptionPane.showMessageDialog(null, "연도를 입력해주세요");
            		return;
            	} else if(field3.getText().length() != 4) {
            		JOptionPane.showMessageDialog(null, "연도를 정상적으로 입력해주세요.\nex)2022");
            		return;
            	} else if(field4.getText().equals("")) {
            		JOptionPane.showMessageDialog(null, "월자를 입력해주세요.");
            		return;
            	} else if(field5.getText().equals("")) {
            		JOptionPane.showMessageDialog(null, "일자를 입력해주세요.");
            		return;
            	}
            	
            	// 새로 조회
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
