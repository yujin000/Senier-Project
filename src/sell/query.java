package sell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class query {

	Connection con; //db연결 나타내는 객체
	Statement st; //sql문 실행 객체
	ResultSetMetaData rsm; //esultSet에 관련된 메타데이터(데이터에 대한 데이터)를 나타내는 객체
	ResultSet rs; //sql쿼리 결과 집합 객체
	PreparedStatement ps; //미리 컴파일된 sql문을 나타내는 객체, sql쿼리 실행할 때 매개변수 전달

	//db연결
	public query() {
		try {
			//로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//연결
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "root", "1234");
		} catch (ClassNotFoundException e) {
			System.out.println(e + "=> 로드 fail");
		} catch (SQLException e) {
			System.out.println(e + "=> 연결 fail");
		}
	}

	//db 종료
	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}
	
	/** 로그인, 회원가입 **/
	//회원가입
	public Boolean join(String pw, String name, String hint) {
		Boolean state = false;

		try {
			ps = con.prepareStatement("INSERT INTO PWSET VALUES(?, ?, ?)");
			ps.setString(1, pw);
			ps.setString(2, name);
			ps.setString(3, hint);
			ps.executeUpdate();
			state = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return state;
	}
	
	//user 찾기, 로그인 시 가입 여부 체크할 때 사용
	public int findUser(String pw, String name) {
		int cnt = 0;
		try {
			ps = con.prepareStatement(
					"SELECT COUNT(*) AS CNT FROM PWSET WHERE PW = '" + pw + "' AND NAME = '" + name + "'");
			rs = ps.executeQuery(); //쿼리 실행 결과 저장
			if (rs.next()) {
				cnt = Integer.parseInt(rs.getString("CNT"));
			}
		} catch (SQLException e) {
			e.printStackTrace(); //예외 발생 시 예외 정보 출력
		} finally {
			dbClose();
		}
		return cnt;
	}
	
	//비밀번호 찾기
	public String findPw(String name, String hint) {
		String pw = "";

		try {
			ps = con.prepareStatement("SELECT * FROM PWSET WHERE NAME='" + name + "' AND HINT='" + hint + "'");
			rs = ps.executeQuery();

			if (rs.next()) {
				pw = rs.getString("PW").trim();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return pw;
	}
	
	
	
	/** 메뉴관리 **/
	//메뉴관리에서 메뉴목록들 출력
	public Vector selectMenu2() {
		Vector vec = new Vector();

		try {
			ps = con.prepareStatement("SELECT * FROM MENU ORDER BY MENUNUM ASC");
			rs = ps.executeQuery();

			while (rs.next()) {
				Vector row = new Vector();
				row.add(rs.getInt("MENUNUM"));
				row.add(rs.getString("MENUNAME"));
				row.add(rs.getInt("PRICE"));
				row.add(rs.getInt("INVENTORY"));

				vec.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return vec;
	}
	
	//메뉴 추가
	public String insertMenu(List<String> list) {
		String str = "";

		try {
			//메뉴번호 중복 검사 및 추가
			ps = con.prepareStatement("INSERT INTO MENU (MENUNUM, MENUNAME, PRICE, INVENTORY) SELECT ?, ?, ?, ? FROM DUAL WHERE NOT EXISTS(SELECT 1 FROM MENU WHERE MENUNUM=?)");
				ps.setString(1, list.get(0));
				ps.setString(2, list.get(1));
				ps.setString(3, list.get(2));
				ps.setString(4, list.get(3));
				ps.setString(5, list.get(0));
				int cnt = ps.executeUpdate();
			 if(cnt==0) {
				str = "메뉴번호가 중복됩니다.";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		//showMessageDialog에 보여줄 문자열 리턴
		return str;
	}
	
	//메뉴관리에서 메뉴 수정할 때 해당 메뉴 가져오기
	public Vector getInfo(int menuNum) {
		Vector vec = new Vector();

		try {
			ps = con.prepareStatement("SELECT * FROM MENU WHERE MENUNUM = " + menuNum);
			rs = ps.executeQuery();

			if (rs.next()) {
				vec.add(rs.getString("MENUNAME"));
				vec.add(rs.getInt("PRICE"));
				vec.add(rs.getInt("INVENTORY"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return vec;
	}
	
	//메뉴 수정
	public Boolean updateMenu(Vector vec) {
		Boolean result = false;
		int cnt = 0;
		int menuNum = 0;
		
		try {
			//메뉴 번호를 수정하는지 안 하는지 확인
			if(Integer.parseInt(vec.get(0).toString()) != Integer.parseInt(vec.get(4).toString())) {
				ps = con.prepareStatement("SELECT * FROM MENU WHERE MENUNUM = " + vec.get(0));
				rs = ps.executeQuery();
				
				if(rs.next()) {
					cnt++;
				}
			}
			
			if(cnt != 0) {
				result = true;
			} else {
				ps = con.prepareStatement("UPDATE MENU SET MENUNUM = "+ Integer.parseInt(vec.get(0).toString()) +" "
						+ ", MENUNAME = '"+ vec.get(1).toString() +"' "
						+ ", PRICE = "+ Integer.parseInt(vec.get(2).toString()) +" "
						+ ", INVENTORY = " + Integer.parseInt(vec.get(3).toString()) + " WHERE MENUNUM = " + Integer.parseInt(vec.get(4).toString()));
				
				ps.executeUpdate();
			}
		} catch(SQLException e) {
			e.printStackTrace();
	   	} finally {
            dbClose();
        }
		return result;
	}
	
	//메뉴 삭제
	public void deleteMenu(int menuNum) {
		try {
			ps = con.prepareStatement("DELETE FROM MENU WHERE MENUNUM = " + menuNum);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}
	
	
	
	/** 판매 **/
	//오른쪽 패널에 메뉴 출력
		public List<Map<String, Object>> selectMenu() {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			try {
				ps = con.prepareStatement("SELECT * FROM MENU ORDER BY MENUNUM ASC");
				rs = ps.executeQuery();

				while (rs.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("MENUNUM", rs.getObject("MENUNUM"));
					map.put("MENUNAME", rs.getObject("MENUNAME"));
					map.put("PRICE", rs.getObject("PRICE"));
					map.put("INVENTORY", rs.getObject("INVENTORY"));
					list.add(map);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
			return list;
		}
		
	//주문목록에 주문한 메뉴 추가
	public void insert(int menuNum) {
		try {
			ps = con.prepareStatement("SELECT * FROM SELL WHERE MENUNUM = '" + menuNum + "'");
			rs = ps.executeQuery();
			int price = 0; 
			String menuName = "";

			//주문목록에 없다면
			if (!rs.next()) {
				ps = con.prepareStatement("SELECT * FROM MENU WHERE MENUNUM = " + menuNum);
				rs = ps.executeQuery();

				if (rs.next()) {
					menuName = rs.getString("MENUNAME");
					price = rs.getInt("PRICE");
				}

				ps = con.prepareStatement("INSERT INTO SELL VALUES(?, ?, ?, ?)");
				ps.setInt(1, menuNum);
				ps.setString(2, menuName);
				ps.setInt(3, price);
				ps.setInt(4, 1);
			} else { //주문목록에 있다면
				ps = con.prepareStatement("UPDATE SELL SET MENUC = MENUC + 1 WHERE MENUNUM = '" + menuNum + "'");
			}

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}
	
	//전체취소, 선택취소로 주문 삭제할 때 현재 주문목록에 있는 주문들 select
	public Vector selectSell() {
		Vector vec = new Vector();

		try {
			ps = con.prepareStatement("SELECT * FROM SELL ORDER BY MENUNUM");
			rs = ps.executeQuery();

			while (rs.next()) {
				Vector row = new Vector();
				row.add(rs.getInt("MENUNUM"));
				row.add(rs.getString("MENUNAME"));
				row.add(rs.getInt("PRICE"));
				row.add(rs.getInt("MENUC"));

				vec.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return vec;
	}
	
	//주문목록 전체 삭제
	public String deleteAll() {
		String result = "";

		try {
			ps = con.prepareStatement("DELETE FROM SELL");
			ps.executeUpdate();

			result = "ok";
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}
	
	//주문목록에서 선택한 주문 삭제
	public void delete(int menuNum) {
		try {
			ps = con.prepareStatement("DELETE SELL WHERE MENUNUM = ?");
			ps.setInt(1, menuNum);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}

	//주문목록에 있는 메뉴들의 총 금액
	public int getSellPrice() {
		int result = 0;
		try {
			ps = con.prepareStatement("SELECT * FROM SELL");
			rs = ps.executeQuery();

			while (rs.next()) {
				int num = rs.getInt("PRICE") * rs.getInt("MENUC");
				result += num;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;
	}

	//결제한 메뉴들을 sellresult테이블에 추가 + sell테이블 비우기
	public void insetSellResult() {
		try {
			ps = con.prepareStatement("SELECT * FROM SELL");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int num = 0;
				//sellnum 1개씩 증가시켜주려고 현재 sellresult에 몇번째 sellnum이 있는지 확인
				PreparedStatement ps1 = con.prepareStatement("SELECT COUNT(*) C FROM SELLRESULT");
				ResultSet rs1 = ps1.executeQuery();
				if (rs1.next()) {
					num = rs1.getInt("C");
				}
				num++;

				ps1 = con.prepareStatement("INSERT INTO SELLRESULT VALUES(?, ?, ?, ?, SYSDATE)");
				ps1.setInt(1, num);
				ps1.setString(2, rs.getString("MENUNAME"));
				ps1.setInt(3, rs.getInt("PRICE"));
				ps1.setInt(4, rs.getInt("MENUC"));
				ps1.executeUpdate();
				ps1.close();
			}

			ps = con.prepareStatement("DELETE SELL");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}
	
	
	
	/** 매출 관리 **/
	//오늘 판매리스트 출력
	public Vector getSellList1() {
		Vector vec = new Vector();

		try {
			// YY/MM/DD 형식으로 문자열 변환 후 오늘 날짜와 동일한 범위에 있는지 확인
			ps = con.prepareStatement(
					"SELECT * FROM SELLRESULT WHERE TO_CHAR(SELLTIME, 'YY/MM/DD') BETWEEN TO_CHAR(SYSDATE, 'YY/MM/DD') AND TO_CHAR(SYSDATE, 'YY/MM/DD') ORDER BY SELLTIME");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Vector row = new Vector();
				row.add(rs.getString("SELLTIME"));
				row.add(rs.getString("MENUNAME"));
				row.add(rs.getInt("PRICE"));
				row.add(rs.getInt("C"));

				vec.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return vec;
	}
	
	//월별 판매리스트 출력
	public Vector getSellList2(String date) {
		Vector vec = new Vector();

		try {
			// YY/MM 형식으로 문자열 변환 후 특정 월에 속하는지 확인
			ps = con.prepareStatement(
					"SELECT * FROM SELLRESULT WHERE TO_CHAR(SELLTIME, 'YY/MM') BETWEEN TO_CHAR(TO_DATE('" + date
							+ "'), 'YY/MM') AND TO_CHAR(TO_DATE('" + date + "'), 'YY/MM') ORDER BY SELLTIME");
			rs = ps.executeQuery();

			while (rs.next()) {
				Vector row = new Vector();
				row.add(rs.getString("SELLTIME"));
				row.add(rs.getString("MENUNAME"));
				row.add(rs.getInt("PRICE"));
				row.add(rs.getInt("C"));

				vec.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return vec;
	}

	//오늘 메뉴별 판매량 출력
	public Vector getSellList3() {
		Vector vec = new Vector();

		try {
			// YY/MM 형식으로 문자열 변환 후 오늘 날짜와 동일 범위에 있는 행들을 MENUNAME으로 묶은 후 그룹 별 수량 내림차순 정렬
			ps = con.prepareStatement(
					"SELECT MENUNAME, SUM(C) AS C FROM SELLRESULT WHERE TO_CHAR(SELLTIME, 'YY/MM/DD') BETWEEN TO_CHAR(SYSDATE, 'YY/MM/DD') AND TO_CHAR(SYSDATE, 'YY/MM/DD') GROUP BY MENUNAME ORDER BY COUNT(*) DESC");
			rs = ps.executeQuery();

			while (rs.next()) {
				Vector row = new Vector();
				row.add(rs.getString("MENUNAME"));
				row.add(rs.getInt("C"));

				vec.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return vec;
	}
	
	
	//일별 판매리스트 출력
	public Vector getSellList4(String date) {
		Vector vec = new Vector();

		try {
			// YY/MM/DD 형식으로 문자열 변환 후 설정한 날짜와 동일한 범위에 있는지 확인
			ps = con.prepareStatement(
					"SELECT * FROM SELLRESULT WHERE TO_CHAR(SELLTIME, 'YY/MM/DD') BETWEEN TO_CHAR(TO_DATE('" + date
							+ "'), 'YY/MM/DD') AND TO_CHAR(TO_DATE('" + date + "'), 'YY/MM/DD') ORDER BY SELLTIME");
			rs = ps.executeQuery();

			while (rs.next()) {
				Vector row = new Vector();
				row.add(rs.getString("SELLTIME"));
				row.add(rs.getString("MENUNAME"));
				row.add(rs.getInt("PRICE"));
				row.add(rs.getInt("C"));

				vec.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return vec;
	}
}
