package sell;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class updateMenuFrame extends JFrame {
	query dao = new query();
	DefaultTableModel model;
	GridBagConstraints gbc;
	GridBagLayout gridLayout;
	JTextField menuNum, menuName, price, inventory;
	cntrFrame cntrFrame;
	int menuNumOrigin = 0;
	
	public updateMenuFrame(cntrFrame cntrFrame, int num) {
		this.cntrFrame = cntrFrame;
		updateMenu(num);
	}
	
	// 메뉴 수정/삭제 화면 띄우기
	public void updateMenu(int num) {
		this.setTitle("메뉴 수정/삭제");
		gridLayout = new GridBagLayout();
        setLayout(gridLayout);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
        // 해당 메뉴 데이터 가져오기
        Vector info = dao.getInfo(num);
        
        // 원래 메뉴 번호
        menuNumOrigin = num;
        
        // 메뉴번호
        JLabel head1 = new JLabel("메뉴 번호 : ");
        menuNum = new JTextField(Integer.toString(num));  
        gridLayoutAdd(head1, 0, 0, 1, 1);
        gridLayoutAdd(menuNum, 1, 0, 3, 1);
       
        // 메뉴이름
        JLabel head2 = new JLabel("메뉴 이름 : ");
        menuName = new JTextField(info.get(0).toString());
        gridLayoutAdd(head2, 0, 1, 1, 1);
        gridLayoutAdd(menuName, 1, 1, 3, 1);
        
        // 가격
        JLabel head3 = new JLabel("가격 : ");
        price = new JTextField(info.get(1).toString());
        gridLayoutAdd(head3,0,2,1,1);
        gridLayoutAdd(price,1,2,3,1);
        
        // 재고 수량
        JLabel head4 = new JLabel("재고 수량 : ");
        inventory = new JTextField(info.get(2).toString());
        gridLayoutAdd(head4,0,3,1,1);
        gridLayoutAdd(inventory,1,3,3,1);
       
        // 버튼 영역 생성
        JPanel area = new JPanel();
        area.setBackground(Color.WHITE);
        
        // 수정 버튼 생성
        JButton btnUpdate = new JButton("수정");
        btnUpdate.setBackground(new Color(255, 255, 255));
        btnUpdate.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		Vector getVec = new Vector();
        		String getMenuNum = menuNum.getText();
        		String getMenuName = menuName.getText();
        		String getPrice = price.getText();
        		String getInventory = inventory.getText();
        		int menuNumChk = 0;
        		Boolean state = false;
        		String msg = "메뉴 번호가 중복됩니다.";
        		
        		// 입력 값 체크
        		if(getMenuNum.equals("")) {
        			msg = "메뉴 번호를 입력해주세요.";
        			state = true;
        		} else {
        			menuNumChk = Integer.parseInt(getMenuNum);
        			
            		if(getMenuName.equals("")) {
            			msg = "메뉴 이름을 입력해주세요.";
            			state = true;
            		} else if(getPrice.equals("")) {
            			msg = "가격을 입력해주세요.";
            			state = true;
            		} else if(getInventory.equals("")) {
            			msg = "재고 수량 입력해주세요.";
            			state = true;
            		} else if(menuNumChk > 20 || menuNumChk == 0) {
            			msg = "메뉴 번호 "+ (menuNumChk == 0 ? "0 은 입력 불가능합니다." : "20 번까지만 입력 가능합니다.");
            			state = true;
            		}
        		}
        		
        		if(state) {
        			JOptionPane.showMessageDialog(null, msg);
        			return;
        		} else {
        			getVec.add(getMenuNum);
        			getVec.add(getMenuName);
        			getVec.add(getPrice);
        			getVec.add(getInventory);
        			getVec.add(menuNumOrigin);
        			
        			if(dao.updateMenu(getVec)) {
        				JOptionPane.showMessageDialog(null, msg);
            			return;
        			} else {
        				JOptionPane.showMessageDialog(null, "변경에 성공하였습니다.");
        				cntrFrame.refresh();
        				dispose();
        			}
        		}
            }
        });
        
        // 삭제 버튼 생성
        JButton btnDelete = new JButton("삭제");
        btnDelete.setBackground(new Color(255, 255, 255));
        btnDelete.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		dao.deleteMenu(menuNumOrigin);
        		JOptionPane.showMessageDialog(null, "삭제에 성공하였습니다.");
				cntrFrame.refresh();
				dispose();
            }
        });
        
        // 취소 버튼 생성
        JButton btnCancle = new JButton("취소");     
        btnCancle.setBackground(new Color(255, 255, 255));
        btnCancle.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			dispose();
	    	}
    	});
        
        area.add(btnUpdate);
        area.add(btnDelete);
        area.add(btnCancle);    
        gridLayoutAdd(area, 0, 10, 4, 1);
       
        setSize(350,350);
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	//그리드백레이아웃에 붙이는 메소드
    private void gridLayoutAdd(JComponent jC, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gridLayout.setConstraints(jC, gbc);
        gbc.insets = new Insets(2, 2, 2, 2);
        add(jC, gbc);
    }
}
