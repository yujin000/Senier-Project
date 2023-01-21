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
	
	// �޴� ����/���� ȭ�� ����
	public void updateMenu(int num) {
		this.setTitle("�޴� ����/����");
		gridLayout = new GridBagLayout();
        setLayout(gridLayout);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        
        // �ش� �޴� ������ ��������
        Vector info = dao.getInfo(num);
        
        // ���� �޴� ��ȣ
        menuNumOrigin = num;
        
        // �޴���ȣ
        JLabel head1 = new JLabel("�޴� ��ȣ : ");
        menuNum = new JTextField(Integer.toString(num));  
        gridLayoutAdd(head1, 0, 0, 1, 1);
        gridLayoutAdd(menuNum, 1, 0, 3, 1);
       
        // �޴��̸�
        JLabel head2 = new JLabel("�޴� �̸� : ");
        menuName = new JTextField(info.get(0).toString());
        gridLayoutAdd(head2, 0, 1, 1, 1);
        gridLayoutAdd(menuName, 1, 1, 3, 1);
        
        // ����
        JLabel head3 = new JLabel("���� : ");
        price = new JTextField(info.get(1).toString());
        gridLayoutAdd(head3,0,2,1,1);
        gridLayoutAdd(price,1,2,3,1);
        
        // ��� ����
        JLabel head4 = new JLabel("��� ���� : ");
        inventory = new JTextField(info.get(2).toString());
        gridLayoutAdd(head4,0,3,1,1);
        gridLayoutAdd(inventory,1,3,3,1);
       
        // ��ư ���� ����
        JPanel area = new JPanel();
        area.setBackground(Color.WHITE);
        
        // ���� ��ư ����
        JButton btnUpdate = new JButton("����");
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
        		String msg = "�޴� ��ȣ�� �ߺ��˴ϴ�.";
        		
        		// �Է� �� üũ
        		if(getMenuNum.equals("")) {
        			msg = "�޴� ��ȣ�� �Է����ּ���.";
        			state = true;
        		} else {
        			menuNumChk = Integer.parseInt(getMenuNum);
        			
            		if(getMenuName.equals("")) {
            			msg = "�޴� �̸��� �Է����ּ���.";
            			state = true;
            		} else if(getPrice.equals("")) {
            			msg = "������ �Է����ּ���.";
            			state = true;
            		} else if(getInventory.equals("")) {
            			msg = "��� ���� �Է����ּ���.";
            			state = true;
            		} else if(menuNumChk > 20 || menuNumChk == 0) {
            			msg = "�޴� ��ȣ "+ (menuNumChk == 0 ? "0 �� �Է� �Ұ����մϴ�." : "20 �������� �Է� �����մϴ�.");
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
        				JOptionPane.showMessageDialog(null, "���濡 �����Ͽ����ϴ�.");
        				cntrFrame.refresh();
        				dispose();
        			}
        		}
            }
        });
        
        // ���� ��ư ����
        JButton btnDelete = new JButton("����");
        btnDelete.setBackground(new Color(255, 255, 255));
        btnDelete.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		dao.deleteMenu(menuNumOrigin);
        		JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�.");
				cntrFrame.refresh();
				dispose();
            }
        });
        
        // ��� ��ư ����
        JButton btnCancle = new JButton("���");     
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
	
	//�׸���鷹�̾ƿ��� ���̴� �޼ҵ�
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
