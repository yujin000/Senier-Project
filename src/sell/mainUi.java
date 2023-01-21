package sell;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import sell.mainUi.ImagePanel;

public class mainUi extends JFrame {
	
	private JPanel contentPane;
	private String loginNm = "";
	private String loginPw = "";
	JTable jtable;
	
	JFrame frame = new JFrame();
	
	Connection con;
	query dao = new query();
	LineBorder Line = new LineBorder(Color.BLACK, 1);
	DefaultTableModel model;
	Vector vec;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainUi mainUi = new mainUi();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public mainUi() {
		// frame ����
		makeFrame();
		// Main ȭ�� ����
		drawMain();
	}
	
	// frame ����
	public void makeFrame() {
		frame.setTitle("We Pos");
		frame.setBounds(100, 100, 1200, 650);
	    frame.getContentPane().setBackground(Color.WHITE);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(null);
	    frame.setLocationRelativeTo(null); //ȭ�� �߾ӿ� �߰� �ϱ�
	    frame.setVisible(true);
	}
	
	// Main ȭ�� ����
	public void drawMain() {
		// backGround ����
		ImagePanel backGround = chBcakground("�α���");
	    backGround.setLayout(null);
	    backGround.setVisible(true);
	    
	    // ps �Է¶� ����
	    JTextField pw = new JPasswordField();
        pw.setBorder(Line);
        pw.setFont(new Font("�޸հ��", Font.BOLD, 23));
        pw.setBounds(480, 300, 248, 47);
        backGround.add(pw);
        
        // �̸� �Է¶� ����
        JTextField name = new JTextField();
        name.setBorder(Line);
        name.setFont(new Font("�޸հ��", Font.BOLD, 23));
        name.setBounds(480, 350, 248, 47);
        backGround.add(name);
        
        // ȸ������ ��ư ����
        JButton btnJoin = new JButton(new ImageIcon("image/ȸ������.png"));
        btnJoin.setBorder(Line);
        btnJoin.setBounds(800, 355, 154, 104);
        backGround.add(btnJoin);
        btnJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	drawJoin();
            }
        });
        
        // �α��� ��ư ����
        JButton btnLogin = new JButton(new ImageIcon("image/LoginBtn.png"));
        btnLogin.setBorder(Line);
        btnLogin.setBounds(800, 250, 154, 104);
        backGround.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		String getPw = pw.getText(), getName = name.getText();
        		Boolean state = true;
        		
        		// �Է°� Ȯ��
        		if(getPw.equals("") || getName.equals("")) {
        			if(getPw.equals("")) {
        				JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.");
        			} else {
        				JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���.");
        			}
        			state = false;
        		}
        		
        		// ���� ���� Check
        		if(state) {
        			int cnt = dao.findUser(getPw, getName);
        			
        			if(cnt > 0) {
        				JOptionPane.showMessageDialog(null, "�α��� ����");
        				loginNm = getName; // �α��� �� ����� name Setting
        				loginPw = getPw; // �α��� �� ����� pw Setting
        				drawLoginAfter();
        				setLocationRelativeTo(null);
        			} else {
        				JOptionPane.showMessageDialog(null, "�α��� ����");
        				setLocationRelativeTo(null);
        			}
        		}
            }
        });
        
        // ��й�ȣ ã�� ��ư ����
        JButton btnFindPw = new JButton("��й�ȣ ã��");
        btnFindPw.setBackground(new Color(255, 255, 255));
        btnFindPw.setFont(new Font("�޸հ��", Font.BOLD, 14));
        btnFindPw.setBorder(Line);
        btnFindPw.setBounds(618, 400, 110, 30);
        backGround.add(btnFindPw);
        btnFindPw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new findPw();
            }
        });
	}
	
	// Join ȭ�� ����
	public void drawJoin() {
		// backGround ����
		ImagePanel backGround = chBcakground("ȸ������ȭ��");
	    
	    JPasswordField pw = new JPasswordField();
	    pw.setBorder(Line);
	    pw.setBounds(480, 250, 248, 47);
	    backGround.add(pw);
	    pw.setColumns(20);
	    
	    JTextField name = new JTextField();
	    name.setBorder(Line);
	    name.setFont(new Font("�޸հ��", Font.BOLD, 23));
	    name.setBounds(480, 325, 248, 47);
	    backGround.add(name);
	    
	    JTextField hint = new JTextField();
	    hint.setBorder(Line);
	    hint.setFont(new Font("�޸հ��", Font.BOLD, 23));
	    hint.setBounds(480, 400, 248, 47);
	    backGround.add(hint);
	    
	    // �����ϱ� ��ư ����
	    JButton btnJoin = new JButton(new ImageIcon("image/�����ϱ�.png"));
	    btnJoin.setBorder(Line);
	    btnJoin.setBounds(800, 250, 154, 104);
	    backGround.add(btnJoin);
	    btnJoin.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
	    		String getPw = pw.getText(), getName = name.getText(), getHint = hint.getText();
        		Boolean state = true;
        		
        		// �Է°� Ȯ��
        		if(getPw.equals("") || getName.equals("") || getHint.equals("")) {
        			if(getPw.equals("")) {
        				JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.");
        			} else if(getName.equals("")) {
        				JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���.");
        			} else {
        				JOptionPane.showMessageDialog(null, "��Ʈ�� �Է����ּ���.");
        			}
        			state = false;
        		}
        		
        		if(state) {
        			int cnt = dao.findUser(getPw, getName);
        			
        			if(cnt > 0) {
        				JOptionPane.showMessageDialog(null, "�̹� ���� �Ǿ��ֽ��ϴ�.");
        				setLocationRelativeTo(null);
        			} else {
        				if(dao.join(getPw, getName, getHint)) {
        					JOptionPane.showMessageDialog(null, "���� ����");
        					drawMain();
        					setLocationRelativeTo(null);
        				} else {
        					JOptionPane.showMessageDialog(null, "���Կ� �����Ͽ����ϴ�.");
        					setLocationRelativeTo(null);
        				}
        			}
        		}
            }
	    });
	    
	    // ����ϱ� ��ư ����
	    JButton btnCancel = new JButton(new ImageIcon("image/�������.png"));
	    btnCancel.setBorder(Line);
	    btnCancel.setBounds(800, 355, 154, 104);
	    backGround.add(btnCancel);
	    btnCancel.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
	    		drawMain();
            }
	    });
	}
	
	// �α��� �� ȭ�� ����
	public void drawLoginAfter() {
		// backGround ����
		ImagePanel backGround = chBcakground("���");
	    
	    // �Ǹ� ��ư ����
	    JButton btnSell = new JButton(new ImageIcon("image/�Ǹ�.png"));
	    btnSell.setBorder(Line);
	    btnSell.setBounds(413, 200, 160, 120);      
        backGround.add(btnSell);
        btnSell.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		ImagePanel backGround = chBcakground("���");
        		drawLeftArea(backGround);
        		drawRightArea(backGround);
        		drawInfo(backGround);
        		drawBtn(backGround);
            }
        });
        
        // ���� ��ư ����
        JButton btnCntr = new JButton(new ImageIcon("image/����.png"));
        btnCntr.setBorder(Line);
        btnCntr.setBounds(613, 200, 160, 120);
        backGround.add(btnCntr);
        btnCntr.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		ImagePanel backGround = chBcakground("���");
        		drawCntr(backGround);
            }
        });
        
        // �α׾ƿ� ��ư ����
        JButton btnLogout = new JButton(new ImageIcon("image/�α׾ƿ�.png"));
        btnLogout.setBorder(Line);
        btnLogout.setBounds(513,370,160,60);
        backGround.add(btnLogout);
        btnLogout.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		loginNm = "";
        		loginPw = "";
        		drawMain();
            }
        });
	}
	
	// backGround ����
	public ImagePanel chBcakground(String txt) {
		ImagePanel backGround = new ImagePanel(new ImageIcon("image/"+ txt +".png").getImage());
	    frame.setSize(backGround.getWidth(),backGround.getHeight());
	    backGround.setBounds(0, 0, 1200, 650);
	    frame.getContentPane().removeAll();
	    frame.getContentPane().add(backGround);
	    frame.revalidate();
	    frame.repaint();
	    
	    return backGround;
	}
	
	// ��ư �׸���
	public void drawBtn(ImagePanel backGround) {
		// �Ǹź���, �������, ������ư �г�
        JPanel btnArea = new JPanel();
        btnArea.setBounds(10, 475, 600, 40);
        btnArea.setBackground(new Color(255, 0, 0, 0));
        backGround.add(btnArea);
        
        // �Ǹź��� ��ư
        JButton btnBuy = new JButton("���� ��ư");
        btnBuy.setBorder(Line);
        btnBuy.setBounds(317, 0, 123, 38);
        btnBuy.setBackground(new Color(255, 255, 255));
        btnBuy.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		int sellPrice = dao.getSellPrice();
        		
        		if(sellPrice == 0) {
        			JOptionPane.showMessageDialog(null, "��� �� ��ǰ�� �����ϴ�.");
        			return;
        		}
        		
        		new sellFrame(sellPrice);
    			
    			vec = dao.selectSell();
    	    	
    	    	model = new DefaultTableModel(vec, head());
    	    	jtable.setModel(model);	
            }
        });
        btnArea.add(btnBuy);
        
        // ���� ��� ��ư
        JButton btnCancel = new JButton("�������");
        btnCancel.setBorder(Line);
        btnCancel.setBounds(182, 0, 123, 38);
        btnCancel.setBackground(new Color(255, 255, 255));
        btnCancel.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		// ������ ���� ���� ��
        		if(jtable.getSelectedRow() == -1) {
        			JOptionPane.showMessageDialog(null, "���� �� ���� �������ּ���.");
        			return;
        		}
        		Object obj = jtable.getValueAt(jtable.getSelectedRow(), 0);
        		
        		int menuNum = Integer.parseInt(obj.toString());
        		
        		dao.delete(menuNum);
    			
    			vec = dao.selectSell();
    	    	
    	    	model = new DefaultTableModel(vec, head());
    	    	jtable.setModel(model);
            }
        });
        btnArea.add(btnCancel);
        
        // ��ü��� ��ư
        JButton btnView = new JButton("��ü ���");
        btnView.setBorder(Line);
        btnView.setBounds(47, 0, 123, 38);
        btnView.setBackground(new Color(255, 255, 255));
        btnView.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		String result = dao.deleteAll();

        		if(result.equals("")) {
        			JOptionPane.showMessageDialog(null, "������ �����Ͽ����ϴ�.");
        		} else {
        			vec = dao.selectSell();
	    	    	
	    	    	model = new DefaultTableModel(vec, head());
	    	    	jtable.setModel(model);
        		}
            }
        });
        btnArea.add(btnView);
      
        // �ڷΰ��� ��ư ����
        JPanel back = new JPanel();
        back.setBounds(1120, 6, 26, 26);
        back.setBackground(new Color(255,255,255));
        backGround.add(back);
      
        JButton backImg = new JButton(new ImageIcon("image/ȭ��ǥ.png"));
        backImg.setBorder(Line);
        backImg.setBounds(0, 0, 26, 26);
        backImg.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		drawLoginAfter();
            }
        });
        back.add(backImg);
	}
	
	// ��Ÿ Setting
	public void drawInfo(ImagePanel backGround) {
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		
		// ��������, �ֹ���ȣ, �Ǹſ� ���� ����
	    JPanel info = new JPanel();
	    info.setBorder(Line);
	    info.setBounds(800, 6, 300, 26);
	    info.setBackground(new Color(255, 255, 255));
	    backGround.add(info);
  
        // �ֹ���ȣ
        JLabel day = new JLabel("�������� : " + date.format(today));
        day.setFont(new Font("����", Font.BOLD, 13));
        day.setBounds(10, 3, 200, 20);
        info.add(day);
      
        // �Ǹſ�
        JLabel saleName = new JLabel("�Ǹſ� : " + loginNm);
        saleName.setFont(new Font("����", Font.BOLD, 13));
        saleName.setBounds(165, 3, 200, 20);
        info.add(saleName);
      
        // �ΰ� �׸���
        JPanel logo = new JPanel();
        logo.setBounds(0, 0, 130, 40);
        backGround.add(logo);
        JLabel logoImg = new JLabel(new ImageIcon("image/wepos.png"));
        logoImg.setBounds(0, 0, 130, 40);   
        logo.add(logoImg);
	}
	
	// ���� ���� �׸���
    public void drawLeftArea(ImagePanel backGround) {
        JPanel left = new JPanel();
        Vector head = new Vector();
        
    	left.setBounds(10, 40, 500, 400);
    	left.setBackground(new Color(255, 255, 255));
    	left.setBorder(BorderFactory.createLineBorder(Color.black));
    	backGround.add(left);
    	
    	vec = dao.selectSell();
    	
    	head.add("�޴���ȣ");
    	head.add("�޴��̸�");
    	head.add("����");
    	head.add("����");
    	
    	model = new DefaultTableModel(vec, head);
    	jtable = new JTable(model);
    	JScrollPane scrollPane = new JScrollPane(jtable);
    	scrollPane.getViewport().setBackground(new Color(255, 255, 255)); 
    	scrollPane.setBorder(Line);
    	scrollPane.setBounds(0, 0, 500, 400);
    	left.add(scrollPane);
    }
    
    private int[] numList = new int[20];
    
    // ������ ���� �׸���
    public void drawRightArea(ImagePanel backGround) {
        JPanel right = new JPanel();
        right.setBackground(new Color(255, 255, 255));
        right.setBounds(520, 40, 1200, 400);
        backGround.add(right);
        
        List<Map<String, Object>> list = dao.selectMenu();
    	int arr[] = {30, 150, 270, 390, 510};
    	int arr2[] = {0, 99, 198, 297};
    	int cnt = 0;
    	
    	for(int i=0; i<20; i++) {
    		JButton btnMenu = new JButton(String.valueOf(i));
    		String str = "";
    		if(list.size() != 0 && cnt < list.size()) {
    			if(Integer.parseInt(list.get(cnt).get("MENUNUM").toString()) == i+1) {
    				str = list.get(cnt).get("MENUNAME").toString();
    				numList[i] = Integer.parseInt(list.get(cnt).get("MENUNUM").toString());
        			cnt++;
    			}
    		}
    		btnMenu.setText(str);
    		btnMenu.setBorder(Line);
    		btnMenu.setBackground(new Color(255, 255, 255));
    		btnAddAction(btnMenu, i);
    		btnMenu.setBounds(arr[i%5], arr2[i/5], 120, 100);
    		right.add(btnMenu);
    	}
    }
    
    public void btnAddAction(JButton btnMenu, int num) {
    	btnMenu.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(numList[num] != 0) {
	    			dao.insert(numList[num]);
	    			
	    			vec = dao.selectSell();
	    	    	
	    	    	model = new DefaultTableModel(vec, head());
	    	    	jtable.setModel(model);
	    		}
	    	}
	    });
    }
    
    // ���� ȭ�� �׸���
    public void drawCntr(ImagePanel backGround) {
    	// �޴�/��� ���� ��ư
    	JButton count = new JButton(new ImageIcon("image/�޴�����.png"));
    	count.setBorder(Line);
    	count.setBounds(413, 200, 160, 120);     
    	backGround.add(count);
    	count.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
	    		new cntrFrame();
	    	}
    	});
    	
    	// ������ ��ư
    	JButton monthPrice = new JButton(new ImageIcon("image/����.png"));
    	monthPrice.setBorder(Line);
    	monthPrice.setBounds(613, 200, 160, 120);
    	backGround.add(monthPrice);
    	monthPrice.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
	    		new sellListFrame();
	    	}
    	});
    	
    	// �ڷΰ��� ��ư
    	JButton back = new JButton(new ImageIcon("image/�ڷΰ���.png"));
    	back.setBorder(Line);
    	back.setBounds(510,370,160,60);
    	backGround.add(back);
    	back.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			drawLoginAfter();
	    	}
    	});
    }
    
    public Vector head() {
    	Vector head = new Vector();
    	
    	head.add("�޴���ȣ");
    	head.add("�޴��̸�");
    	head.add("����");
    	head.add("����");
    	
    	return head;
    }
	
	class ImagePanel extends JPanel{
        private Image img;
        public ImagePanel(Image img) {
           this.img = img;
           setSize(new Dimension(img.getWidth(null),img.getHeight(null)));
           setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null)));
           setLayout(null);
        }
        public void paintComponent(Graphics g) {
           g.drawImage(img, 0, 0, null);
        }
     }

}
