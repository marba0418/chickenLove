package kh.exam.controller;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import kh.exam.model.dao.ExamDao;
import kh.exam.model.vo.Board;
import kh.exam.model.vo.Member;
import kh.exam.view.ExamView;

public class ExamController {

	ExamView view = new ExamView();
	Member loginMember; // 로그인회원정보

	public void main() {
		while (true) {
			int select = view.printMain();
			switch (select) {
			case 1:
				signIn();
				break;
			case 2:
				signUpMember();
				break;
			case 3:
				searchId();
				break;
			case 0:
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}

		}

	}

	public void signIn() {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		ExamDao dao = new ExamDao();
		Member m = view.signIn();
		loginMember = dao.signIn(conn, m);

		if (loginMember != null) {
			view.printMsg("로그인 성공!!!");
			successLogin();
		} else {
			view.printMsg("아이디 또는 비밀번호를 확인하세요.");
		}

		JDBCTemplate.close(conn);

	}

	public void successLogin() {
		while (true) {
			int select = view.loginMenu();
			switch (select) {
			case 1:
				printAllBoard();
				break;
			case 2:
				printDetail();
				break;
			case 3:
				insertBoard();
				break;
			case 4:
				modifyBoard();
				break;
			case 5:
				deleteBoard();
				break;
			case 6:
				printMine();
				break;
			case 7:
				modifyMember();
				break;
			case 8:
				int flag = deleteMember();
				if (flag == 1) {
					return;
				}
				break;
			case 0:
				view.printMsg("bye~");
				loginMember = null;
				return;
			default:
				System.out.println("잘못 입력하셨습니다");
			}
		}
	}

	public void deleteBoard() {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		ExamDao dao = new ExamDao();
		int boardNo = view.getBoardNum();
		Board b = dao.modifyBoard(conn, boardNo, loginMember.getMemberId());

		if (b == null) {
			view.printMsg("작성자만 삭제가 가능합니다");
		} else {
			int result = dao.deleteBoard(conn,b);
			if(result >0) {
				JDBCTemplate.commit(conn);
				view.printMsg("게시글 삭제 성공!");
			}else {
				JDBCTemplate.rollback(conn);
				view.printMsg("삭제 실패");
			}
		}
		JDBCTemplate.close(conn);
	}

	public void modifyBoard() {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		ExamDao dao = new ExamDao();
		int boardNo = view.getBoardNum();
		Board b = dao.modifyBoard(conn, boardNo, loginMember.getMemberId());

		if (b == null) {
			view.printMsg("작성자만 수정가능합니다");
		} else {

			b = view.modifyBoard(b);
			int result = dao.modifyBoard(conn, b);

			if (result > 0) {
				JDBCTemplate.commit(conn);
				view.printMsg("게시글 수정 성공!");
			} else {
				JDBCTemplate.rollback(conn);
				view.printMsg("게시글 수정 실패");

			}
		}

		JDBCTemplate.close(conn);

	}

	public void printDetail() {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		ExamDao dao = new ExamDao();
		int boardNo = view.getBoardNum();

		Board b = dao.printDetail(conn, boardNo);
		if (b == null) {
			view.printMsg("게시물 번호를 확인하세요");
		} else {
			b = view.printDetail(b);
			int result = dao.modifyCount(conn, b);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		JDBCTemplate.close(conn);
	}

	public void printAllBoard() {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		ExamDao dao = new ExamDao();

		ArrayList<Board> boards = dao.printAllBoard(conn);
		if (boards.isEmpty()) {
			view.printMsg("게시글이 없습니다");
		} else {
			view.printAllBoard(boards);
		}

		JDBCTemplate.close(conn);
	}

	public void insertBoard() {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		ExamDao dao = new ExamDao();

		Board b = view.insertBoard();
		b.setBoardWriter(loginMember.getMemberId());
		int result = dao.insertBoard(conn, b);

		if (result > 0) {
			JDBCTemplate.commit(conn);
			view.printMsg("게시글 등록 성공!!");
		} else {
			JDBCTemplate.rollback(conn);
			view.printMsg("등록 실패");
		}

		JDBCTemplate.close(conn);

	}

	public int deleteMember() {
		// TODO Auto-generated method stub
		int flag = 0;
		Connection conn = JDBCTemplate.getConnection();
		int select = view.deleteMember();
		ExamDao dao = new ExamDao();
		if (select == 1) {
			int result = dao.deleteMember(conn, loginMember);
			if (result > 0) {
				JDBCTemplate.commit(conn);
				view.printMsg("Bye~Bye~");
				flag = 1;
			} else {
				JDBCTemplate.rollback(conn);
				view.printMsg("삭제 실패");
			}

		} else {
			view.printMsg("취소하셨습니다.");
		}
		return flag;
	}

	public void printMine() {
		// TODO Auto-generated method stub
		view.printMine(loginMember);
	}

	public void modifyMember() {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		ExamDao dao = new ExamDao();
		Member m = view.modifyMember(loginMember);
		int result = dao.modifyMember(conn, m);

		if (result > 0) {
			JDBCTemplate.commit(conn);
			view.printMsg("정보 수정 성공");
			loginMember = m;
		} else {
			JDBCTemplate.rollback(conn);
			view.printMsg("수정 실패");
		}

		JDBCTemplate.close(conn);

	}

	public void searchId() {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		Member m = view.searchId();
		ExamDao dao = new ExamDao();
		String ID = dao.searchId(conn, m);

		if (ID != null) {
			view.printMsg("아이디는 [" + ID + "] 입니다.");
		} else {
			view.printMsg("일치하는 정보가 없습니다.");
		}

		JDBCTemplate.close(conn);
	}

	public void signUpMember() {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		Member m = view.signUpMember();
		ExamDao dao = new ExamDao();
		int result = dao.signUpMember(conn, m);

		if (result > 0) {
			JDBCTemplate.commit(conn);
			view.printMsg("회원가입성공");
		} else {
			JDBCTemplate.rollback(conn);
			view.printMsg("회원가입 실패");
		}
		JDBCTemplate.close(conn);

		// 예외처리필요함 이미 존재하는회원
	}
}
