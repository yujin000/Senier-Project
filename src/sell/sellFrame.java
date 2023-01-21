package sell;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class sellFrame extends JDialog {
	
	private JLabel jlabel;
	private JTextField jtextF;
	query dao = new query();
	
	public sellFrame(int price) {
		setModal(true);
	    setTitle("결제");
	    setSize(340, 409);
	    setLocationRelativeTo(getParent());
	    getContentPane().setLayout(null);
	      
	    jlabel = new JLabel();
	    jlabel.setOpaque(true); // // JLabel 색상변경하기 위해서는 setOpaque를 true로 해줘야한다.
	    jlabel.setBackground(new Color(255, 255, 255));
	    jlabel.setBorder(BorderFactory.createLineBorder(Color.black));
	    jlabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    jlabel.setBounds(95, 51, 154, 31);
	    getContentPane().add(jlabel);
	    
	    int arr[] = {12, 80, 148};
	    int arr2[] = {228, 166, 104};
	    String arr3[] = {"0", "00", "C"};
	    int cnt = 0, cnt2 = 0;
	    
	    // 숫자패드 그리기
	    for(int i=1; i<=9; i++) {
	    	JButton btnNumber = new JButton();
	    	btnNumber.setText(Integer.toString(i));
	    	btnNumber.setBackground(new Color(255, 255, 255));
	    	btnNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    	btnNumber.setBounds(arr[cnt], arr2[cnt2], 56, 56);
			getContentPane().add(btnNumber);
			cnt++;
			if(cnt == 3) {
				cnt = 0;
				cnt2++;
			}
			
			// 클릭 엑션 등록
			btnNumber.addActionListener(new NumberActionListener(jlabel,Integer.toString(i)));
	    }
	    
	    // 기타 버튼 그리기
	    for(int i=0; i<3; i++) {
	    	JButton btnNumber = new JButton();
	    	btnNumber.setText(arr3[i]);
	    	btnNumber.setBackground(new Color(255, 255, 255));
	    	btnNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    	btnNumber.setBounds(arr[cnt], 290, 56, 56);
			getContentPane().add(btnNumber);
			cnt++;

			if(i != 2) {
				// 클릭 엑션 등록
				btnNumber.addActionListener(new NumberActionListener(jlabel,arr3[i]));
			} else {
				btnNumber.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jlabel.setText("0");
					}
				});
			}
			
	    }
	    
	    // text 그리기
	    JLabel label = new JLabel("결제할 금액");
	    label.setBounds(12, 18, 73, 15);
	    getContentPane().add(label);
	    JLabel label_1 = new JLabel("받은 금액");
	    label_1.setBounds(12, 59, 73, 15);
	    getContentPane().add(label_1);
	    
	    jtextF = new JTextField(0);
	    jtextF.setBorder(BorderFactory.createLineBorder(Color.black));
	    jtextF.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    jtextF.setBackground(new Color(255, 255, 255));
	    jtextF.setEditable(false);
	    jtextF.setText(String.valueOf(price));
	    jtextF.setBounds(95, 10, 154, 31);
	    getContentPane().add(jtextF);
	    jtextF.setColumns(10);
	    
	    JButton btnSell = new JButton("결제");
	    btnSell.setBackground(new Color(255, 255, 255));
	    btnSell.setBounds(220, 186, 90, 36);
	    getContentPane().add(btnSell);
	    btnSell.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int sellPrice = Integer.parseInt(jtextF.getText());
	    		int getPrice = Integer.parseInt(jlabel.getText().equals("") ? "0" : jlabel.getText());
	    		if(sellPrice > getPrice) {
	    			JOptionPane.showMessageDialog(null, "받은 금액이 부족합니다.");
	    		} else {
	    			dao.insetSellResult();
	    			if(sellPrice < getPrice) {
	    				JOptionPane.showMessageDialog(null, "거스름돈은 " + (getPrice-sellPrice) + " 원 입니다.");
	    			} else {
	    				JOptionPane.showMessageDialog(null, "정상 결제되었습니다.");
	    			}
	    			dispose();
	    		}
	    	}
	    });

	    JButton btnCancel = new JButton("취소");
	    btnCancel.setBackground(new Color(255, 255, 255));
	    btnCancel.setBounds(220, 227, 90, 36);
	    btnCancel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();
	    	}
	    });
	    getContentPane().add(btnCancel);
	    
	    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    setVisible(true);
	    setType(Type.UTILITY);
	}
	
	// 키패드 눌렀을 때 동작 선언부
	class NumberActionListener implements ActionListener {
		private JLabel label;
		private String text;
		
		public NumberActionListener(JLabel l, String s){
			label = l;
			text = s;
		}

		public void actionPerformed(ActionEvent e) {
			String input = label.getText();
			if(input.equals("0")){
				label.setText(text);
			} else{		
				label.setText(label.getText() + text);
			}
		}
		
	}
}
