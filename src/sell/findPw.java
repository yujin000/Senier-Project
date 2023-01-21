package sell;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import javax.swing.border.LineBorder;


public class findPw extends JFrame {
	private JPanel panel;
	String name="", hint="";
	query dao = new query();
	LineBorder Line = new LineBorder(Color.BLACK,1);
	
	public findPw(){
		setTitle("비밀번호 찾기");
		setSize(350, 250);
		setVisible(true);
		setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null); //화면 중앙에 뜨게 하기
    
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 350, 350);
		getContentPane().add(panel);
		panel.setLayout(null);
    
		JLabel label = new JLabel("등록한 이름과 힌트가 같아야 비밀번호를 찾을 수 있습니다.");
		label.setFont(new Font("굴림", Font.PLAIN, 12));
		label.setBounds(6, 10, 350, 20);
    	panel.add(label);
    
    	JLabel centerLine = new JLabel(new ImageIcon("image/선.png"));
    	centerLine.setFont(new Font("굴림", Font.PLAIN, 12));
    	centerLine.setBounds(5, 30, 325, 20);
	    panel.add(centerLine);
	    
	    JLabel label2 = new JLabel("이름");
	    label2.setFont(new Font("굴림", Font.BOLD, 15));
	    label2.setBounds(5, 75, 30, 20);
	    panel.add(label2);
	    
	    JLabel label3 = new JLabel("힌트");
	    label3.setFont(new Font("굴림", Font.BOLD, 15));
	    label3.setBounds(5, 105, 30, 20);
	    panel.add(label3);
	    
	    JTextField name = new JTextField();
	    name.setBorder(Line);
	    name.setFont(new Font("휴먼고딕", Font.BOLD, 23));
	    name.setBounds(50, 70, 150, 25);
	    panel.add(name);
	    
	    JTextField hint = new JTextField();
	    hint.setBorder(Line);
	    hint.setFont(new Font("휴먼고딕", Font.BOLD, 23));
	    hint.setBounds(50, 100, 150, 25);
	    panel.add(hint);
	    
	    JLabel centerLine2 = new JLabel(new ImageIcon("image/선.png"));
	    centerLine2.setFont(new Font("굴림", Font.PLAIN, 12));
	    centerLine2.setBounds(5, 145, 325, 20);
	    panel.add(centerLine2);
	    
	    JButton btnFind = new JButton("찾기");
	    btnFind.setBorder(Line);
	    btnFind.setBounds(210, 165, 50, 30);
	    btnFind.setBackground(new Color(255, 255, 255));
	    btnFind.addActionListener(new ActionListener() { 
	        public void actionPerformed(ActionEvent e) {
	            String name1 = name.getText().trim();
	            String hint1 = hint.getText().trim();
	            if (name1.equals("")) {
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
					return;
				} else if(hint1.equals("")) {
	            	JOptionPane.showMessageDialog(null, "힌트를 입력해주세요.");
	            	return;
	            }
	            
	            String pw = dao.findPw(name1, hint1);
	            
	            if(pw.equals("")) {
	            	JOptionPane.showMessageDialog(null, "등록되지 않은 회원입니다.");
	            	return;
	            } else {
	            	JLabel Label = new JLabel("설정하신 비밀번호는 " + pw + " 입니다.");
	            	panel.add(Label);
		    	    Label.setFont(new Font("굴림", Font.BOLD, 15));
		    	    Label.setBounds(5, 130, 350, 20);
	            }
	        }
	    }); 
	    panel.add(btnFind);

	    JButton btnClose = new JButton("취소");
	    btnClose.setBorder(Line);
	    btnClose.setBounds(270, 165, 50, 30);
	    btnClose.setBackground(new Color(255, 255, 255));
	    btnClose.addActionListener(new ActionListener() { 
	        public void actionPerformed(ActionEvent e) { 
	            dispose();
	       } 
	    });

	    panel.add(btnClose);
	}
}