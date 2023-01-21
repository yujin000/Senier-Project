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

public class addMenuFrame extends JFrame implements ActionListener {
	query dao = new query();
	DefaultTableModel model;
	GridBagConstraints gbc;
	GridBagLayout gridLayout;
	JTextField menuNum, menuName, price, inventory;
	cntrFrame cntrFrame;

	public static void main(String[] args) {
		new addMenuFrame();
	}

	public addMenuFrame() {
		addMenu();
	}

	public addMenuFrame(cntrFrame cntrFrame) {
		addMenu();
		this.cntrFrame = cntrFrame;
	}

	// 메뉴 추가 화면 띄우기
	public void addMenu() {
		this.setTitle("메뉴관리");
		gridLayout = new GridBagLayout();
		setLayout(gridLayout);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		// 메뉴번호
		JLabel head1 = new JLabel("메뉴 번호 : ");
		menuNum = new JTextField();
		gridLayoutAdd(head1, 0, 0, 1, 1);
		gridLayoutAdd(menuNum, 1, 0, 3, 1);

		// 메뉴이름
		JLabel head2 = new JLabel("메뉴 이름 : ");
		menuName = new JTextField();
		gridLayoutAdd(head2, 0, 1, 1, 1);
		gridLayoutAdd(menuName, 1, 1, 3, 1);

		// 가격
		JLabel head3 = new JLabel("가격 : ");
		price = new JTextField();
		gridLayoutAdd(head3, 0, 2, 1, 1);
		gridLayoutAdd(price, 1, 2, 3, 1);

		// 재고 수량
		JLabel head4 = new JLabel("재고 수량 : ");
		inventory = new JTextField();
		gridLayoutAdd(head4, 0, 3, 1, 1);
		gridLayoutAdd(inventory, 1, 3, 3, 1);

		// 버튼 영역 생성
		JPanel area = new JPanel();
		area.setBackground(Color.WHITE);

		// 추가 버튼 생성
		JButton btnAdd = new JButton("추가");
		btnAdd.setBackground(new Color(255, 255, 255));
		btnAdd.addActionListener(this);

		// 취소 버튼 생성
		JButton btnCancle = new JButton("취소");
		btnCancle.setBackground(new Color(255, 255, 255));
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		area.add(btnAdd);
		area.add(btnCancle);
		gridLayoutAdd(area, 0, 10, 4, 1);

		setSize(350, 350);
		setVisible(true);
		getContentPane().setBackground(Color.WHITE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// 그리드백레이아웃에 붙이는 메소드
	private void gridLayoutAdd(JComponent jC, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gridLayout.setConstraints(jC, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		add(jC, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String getMenuNum = menuNum.getText();
		String getMenuName = menuName.getText();
		String getPrice = price.getText();
		String getInventory = inventory.getText();
		int menuNumChk = 0;
		Boolean state = false;
		String msg = "메뉴 번호가 중복됩니다.";

		// 입력 값 체크
		if (getMenuNum.equals("")) {
			msg = "메뉴 번호를 입력해주세요.";
			state = true;
		} else {
			menuNumChk = Integer.parseInt(getMenuNum);

			if (getMenuName.equals("")) {
				msg = "메뉴 이름을 입력해주세요.";
				state = true;
			} else if (getPrice.equals("")) {
				msg = "가격을 입력해주세요.";
				state = true;
			} else if (getInventory.equals("")) {
				msg = "재고 수량 입력해주세요.";
				state = true;
			} else if (Integer.parseInt(getMenuNum) > 20) {
				msg = "메뉴 번호는 20번까지만 입력 가능합니다.";
				state = true;
			} else if (menuNumChk > 20 || menuNumChk == 0) {
				msg = "메뉴 번호 " + (menuNumChk == 0 ? "0 은 입력 불가능합니다." : "20 번까지만 입력 가능합니다.");
				state = true;
			}
		}

		if (state) {
			JOptionPane.showMessageDialog(null, msg);
			return;
		} else {
			List<String> list = new ArrayList<>();
			list.add(getMenuNum);
			list.add(getMenuName);
			list.add(getPrice);
			list.add(getInventory);

			String str = dao.insertMenu(list);

			if (str.equals("")) {
				JOptionPane.showMessageDialog(null, "등록 성공");
				cntrFrame.refresh();
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, str);
			}
		}
	}

}
