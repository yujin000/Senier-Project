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
		// frame 설정
		makeFrame();
		// Main 화면 구성
		drawMain();
	}
	
	// frame 설정
	public void makeFrame() {
		frame.setTitle("We Pos");
		frame.setBounds(100, 100, 1200, 650);
	    frame.getContentPane().setBackground(Color.WHITE);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(null);
	    frame.setLocationRelativeTo(null); //화면 중앙에 뜨게 하기
	    frame.setVisible(true);
	}
	
	// Main 화면 구성
	public void drawMain() {
		// backGround 변경
		ImagePanel backGround = chBcakground("로그인");
	    backGround.setLayout(null);
	    backGround.setVisible(true);
	    
	    // ps 입력란 생성
	    JTextField pw = new JPasswordField();
        pw.setBorder(Line);
        pw.setFont(new Font("휴먼고딕", Font.BOLD, 23));
        pw.setBounds(480, 300, 248, 47);
        backGround.add(pw);
        
        // 이름 입력란 생성
        JTextField name = new JTextField();
        name.setBorder(Line);
        name.setFont(new Font("휴먼고딕", Font.BOLD, 23));
        name.setBounds(480, 350, 248, 47);
        backGround.add(name);
        
        // 회원가입 버튼 생성
        JButton btnJoin = new JButton(new ImageIcon("image/회원가입.png"));
        btnJoin.setBorder(Line);
        btnJoin.setBounds(800, 355, 154, 104);
        backGround.add(btnJoin);
        btnJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	drawJoin();
            }
        });
        
        // 로그인 버튼 생성
        JButton btnLogin = new JButton(new ImageIcon("image/LoginBtn.png"));
        btnLogin.setBorder(Line);
        btnLogin.setBounds(800, 250, 154, 104);
        backGround.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		String getPw = pw.getText(), getName = name.getText();
        		Boolean state = true;
        		
        		// 입력값 확인
        		if(getPw.equals("") || getName.equals("")) {
        			if(getPw.equals("")) {
        				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
        			} else {
        				JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
        			}
        			state = false;
        		}
        		
        		// 가입 여부 Check
        		if(state) {
        			int cnt = dao.findUser(getPw, getName);
        			
        			if(cnt > 0) {
        				JOptionPane.showMessageDialog(null, "로그인 성공");
        				loginNm = getName; // 로그인 한 사람의 name Setting
        				loginPw = getPw; // 로그인 한 사람의 pw Setting
        				drawLoginAfter();
        				setLocationRelativeTo(null);
        			} else {
        				JOptionPane.showMessageDialog(null, "로그인 실패");
        				setLocationRelativeTo(null);
        			}
        		}
            }
        });
        
        // 비밀번호 찾기 버튼 생성
        JButton btnFindPw = new JButton("비밀번호 찾기");
        btnFindPw.setBackground(new Color(255, 255, 255));
        btnFindPw.setFont(new Font("휴먼고딕", Font.BOLD, 14));
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
	
	// Join 화면 구성
	public void drawJoin() {
		// backGround 변경
		ImagePanel backGround = chBcakground("회원가입화면");
	    
	    JPasswordField pw = new JPasswordField();
	    pw.setBorder(Line);
	    pw.setBounds(480, 250, 248, 47);
	    backGround.add(pw);
	    pw.setColumns(20);
	    
	    JTextField name = new JTextField();
	    name.setBorder(Line);
	    name.setFont(new Font("휴먼고딕", Font.BOLD, 23));
	    name.setBounds(480, 325, 248, 47);
	    backGround.add(name);
	    
	    JTextField hint = new JTextField();
	    hint.setBorder(Line);
	    hint.setFont(new Font("휴먼고딕", Font.BOLD, 23));
	    hint.setBounds(480, 400, 248, 47);
	    backGround.add(hint);
	    
	    // 가입하기 버튼 생성
	    JButton btnJoin = new JButton(new ImageIcon("image/가입하기.png"));
	    btnJoin.setBorder(Line);
	    btnJoin.setBounds(800, 250, 154, 104);
	    backGround.add(btnJoin);
	    btnJoin.addActionListener(new ActionListener() {
	    	@Override
            public void actionPerformed(ActionEvent e) {
	    		String getPw = pw.getText(), getName = name.getText(), getHint = hint.getText();
        		Boolean state = true;
        		
        		// 입력값 확인
        		if(getPw.equals("") || getName.equals("") || getHint.equals("")) {
        			if(getPw.equals("")) {
        				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
        			} else if(getName.equals("")) {
        				JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
        			} else {
        				JOptionPane.showMessageDialog(null, "힌트를 입력해주세요.");
        			}
        			state = false;
        		}
        		
        		if(state) {
        			int cnt = dao.findUser(getPw, getName);
        			
        			if(cnt > 0) {
        				JOptionPane.showMessageDialog(null, "이미 가입 되어있습니다.");
        				setLocationRelativeTo(null);
        			} else {
        				if(dao.join(getPw, getName, getHint)) {
        					JOptionPane.showMessageDialog(null, "가입 성공");
        					drawMain();
        					setLocationRelativeTo(null);
        				} else {
        					JOptionPane.showMessageDialog(null, "가입에 실패하였습니다.");
        					setLocationRelativeTo(null);
        				}
        			}
        		}
            }
	    });
	    
	    // 취소하기 버튼 생성
	    JButton btnCancel = new JButton(new ImageIcon("image/가입취소.png"));
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
	
	// 로그인 후 화면 구성
	public void drawLoginAfter() {
		// backGround 변경
		ImagePanel backGround = chBcakground("배경");
	    
	    // 판매 버튼 생성
	    JButton btnSell = new JButton(new ImageIcon("image/판매.png"));
	    btnSell.setBorder(Line);
	    btnSell.setBounds(413, 200, 160, 120);      
        backGround.add(btnSell);
        btnSell.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		ImagePanel backGround = chBcakground("배경");
        		drawLeftArea(backGround);
        		drawRightArea(backGround);
        		drawInfo(backGround);
        		drawBtn(backGround);
            }
        });
        
        // 관리 버튼 생성
        JButton btnCntr = new JButton(new ImageIcon("image/관리.png"));
        btnCntr.setBorder(Line);
        btnCntr.setBounds(613, 200, 160, 120);
        backGround.add(btnCntr);
        btnCntr.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		ImagePanel backGround = chBcakground("배경");
        		drawCntr(backGround);
            }
        });
        
        // 로그아웃 버튼 생성
        JButton btnLogout = new JButton(new ImageIcon("image/로그아웃.png"));
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
	
	// backGround 변경
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
	
	// 버튼 그리기
	public void drawBtn(ImagePanel backGround) {
		// 판매보기, 선택취소, 결제버튼 패널
        JPanel btnArea = new JPanel();
        btnArea.setBounds(10, 475, 600, 40);
        btnArea.setBackground(new Color(255, 0, 0, 0));
        backGround.add(btnArea);
        
        // 판매보기 버튼
        JButton btnBuy = new JButton("결제 버튼");
        btnBuy.setBorder(Line);
        btnBuy.setBounds(317, 0, 123, 38);
        btnBuy.setBackground(new Color(255, 255, 255));
        btnBuy.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		int sellPrice = dao.getSellPrice();
        		
        		if(sellPrice == 0) {
        			JOptionPane.showMessageDialog(null, "계산 할 상품이 없습니다.");
        			return;
        		}
        		
        		new sellFrame(sellPrice);
    			
    			vec = dao.selectSell();
    	    	
    	    	model = new DefaultTableModel(vec, head());
    	    	jtable.setModel(model);	
            }
        });
        btnArea.add(btnBuy);
        
        // 선택 취소 버튼
        JButton btnCancel = new JButton("선택취소");
        btnCancel.setBorder(Line);
        btnCancel.setBounds(182, 0, 123, 38);
        btnCancel.setBackground(new Color(255, 255, 255));
        btnCancel.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		// 선택한 행이 없을 때
        		if(jtable.getSelectedRow() == -1) {
        			JOptionPane.showMessageDialog(null, "삭제 할 행을 선택해주세요.");
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
        
        // 전체취소 버튼
        JButton btnView = new JButton("전체 취소");
        btnView.setBorder(Line);
        btnView.setBounds(47, 0, 123, 38);
        btnView.setBackground(new Color(255, 255, 255));
        btnView.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		String result = dao.deleteAll();

        		if(result.equals("")) {
        			JOptionPane.showMessageDialog(null, "삭제에 실패하였습니다.");
        		} else {
        			vec = dao.selectSell();
	    	    	
	    	    	model = new DefaultTableModel(vec, head());
	    	    	jtable.setModel(model);
        		}
            }
        });
        btnArea.add(btnView);
      
        // 뒤로가기 버튼 영역
        JPanel back = new JPanel();
        back.setBounds(1120, 6, 26, 26);
        back.setBackground(new Color(255,255,255));
        backGround.add(back);
      
        JButton backImg = new JButton(new ImageIcon("image/화살표.png"));
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
	
	// 기타 Setting
	public void drawInfo(ImagePanel backGround) {
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		
		// 영업일자, 주문번호, 판매원 영역 생성
	    JPanel info = new JPanel();
	    info.setBorder(Line);
	    info.setBounds(800, 6, 300, 26);
	    info.setBackground(new Color(255, 255, 255));
	    backGround.add(info);
  
        // 주문번호
        JLabel day = new JLabel("영업일자 : " + date.format(today));
        day.setFont(new Font("굴림", Font.BOLD, 13));
        day.setBounds(10, 3, 200, 20);
        info.add(day);
      
        // 판매원
        JLabel saleName = new JLabel("판매원 : " + loginNm);
        saleName.setFont(new Font("굴림", Font.BOLD, 13));
        saleName.setBounds(165, 3, 200, 20);
        info.add(saleName);
      
        // 로고 그리기
        JPanel logo = new JPanel();
        logo.setBounds(0, 0, 130, 40);
        backGround.add(logo);
        JLabel logoImg = new JLabel(new ImageIcon("image/wepos.png"));
        logoImg.setBounds(0, 0, 130, 40);   
        logo.add(logoImg);
	}
	
	// 왼쪽 영역 그리기
    public void drawLeftArea(ImagePanel backGround) {
        JPanel left = new JPanel();
        Vector head = new Vector();
        
    	left.setBounds(10, 40, 500, 400);
    	left.setBackground(new Color(255, 255, 255));
    	left.setBorder(BorderFactory.createLineBorder(Color.black));
    	backGround.add(left);
    	
    	vec = dao.selectSell();
    	
    	head.add("메뉴번호");
    	head.add("메뉴이름");
    	head.add("가격");
    	head.add("수량");
    	
    	model = new DefaultTableModel(vec, head);
    	jtable = new JTable(model);
    	JScrollPane scrollPane = new JScrollPane(jtable);
    	scrollPane.getViewport().setBackground(new Color(255, 255, 255)); 
    	scrollPane.setBorder(Line);
    	scrollPane.setBounds(0, 0, 500, 400);
    	left.add(scrollPane);
    }
    
    private int[] numList = new int[20];
    
    // 오른쪽 영역 그리기
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
    
    // 관리 화면 그리기
    public void drawCntr(ImagePanel backGround) {
    	// 메뉴/재고 관리 버튼
    	JButton count = new JButton(new ImageIcon("image/메뉴관리.png"));
    	count.setBorder(Line);
    	count.setBounds(413, 200, 160, 120);     
    	backGround.add(count);
    	count.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
	    		new cntrFrame();
	    	}
    	});
    	
    	// 월매출 버튼
    	JButton monthPrice = new JButton(new ImageIcon("image/매출.png"));
    	monthPrice.setBorder(Line);
    	monthPrice.setBounds(613, 200, 160, 120);
    	backGround.add(monthPrice);
    	monthPrice.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
	    		new sellListFrame();
	    	}
    	});
    	
    	// 뒤로가기 버튼
    	JButton back = new JButton(new ImageIcon("image/뒤로가기.png"));
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
    	
    	head.add("메뉴번호");
    	head.add("메뉴이름");
    	head.add("가격");
    	head.add("수량");
    	
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
