package kh.exam.view;

import java.util.ArrayList;
import java.util.Scanner;

import kh.exam.model.vo.Board;
import kh.exam.model.vo.Member;

public class ExamView {
	Scanner sc = new Scanner(System.in);

	public void printMsg(String str) {
		System.out.println(str);
	}

	public int printMain() {
		// TODO Auto-generated method stub
		System.out.println("---------------KH커뮤니티----------------");
		System.out.println("1 . 로그인하기");
		System.out.println("2 . 회원가입");
		System.out.println("3 . 아이디 찾기");
		System.out.println("0 . 프로그램 종료");
		System.out.print("선택 > ");
		int select = sc.nextInt();

		return select;
	}

	public Member signUpMember() {
		// TODO Auto-generated method stub
		System.out.println("\n-----------회원가입------------");
		System.out.print("ID 입력 : ");
		String memberId = sc.next();
		System.out.print("PW 입력 : ");
		String memberPw = sc.next();
		System.out.print("이름 입력 : ");
		String memberName = sc.next();
		System.out.print("전화번호 입력(ex.01011112222) : ");
		String phone = sc.next();

		return new Member(0, memberId, memberPw, memberName, phone);
	}

	public Member searchId() {
		// TODO Auto-generated method stub
		Member m = new Member();
		System.out.println("\n---------------아이디 찾기-----------------");
		System.out.print("이름 입력 : ");
		String memberName = sc.next();
		m.setMemberName(memberName);
		System.out.print("전화번호 입력 : ");
		String phone = sc.next();
		m.setPhone(phone);

		return m;
	}

	public Member signIn() {
		// TODO Auto-generated method stub
		Member m = new Member();
		System.out.println("\n----------------로그인----------------");
		System.out.print("ID 입력 : ");
		m.setMemberId(sc.next());
		System.out.print("PW 입력 : ");
		m.setMemberPw(sc.next());

		return m;
	}

	public int loginMenu() {
		// TODO Auto-generated method stub
		System.out.println("\n-----------KH커뮤니티----------------");
		System.out.println("1 . 게시물 목록 보기");
		System.out.println("2 . 게시물 상세 보기");
		System.out.println("3 . 게시물 등록");
		System.out.println("4 . 게시물 수정");
		System.out.println("5 . 게시물 삭제");
		System.out.println("6 . 내 정보 보기");
		System.out.println("7 . 내 정보 변경");
		System.out.println("8 . 회원 탈퇴");
		System.out.println("0 . 로그아웃");
		System.out.print("선택 > ");
		int select = sc.nextInt();

		return select;
	}

	public Member modifyMember(Member loginMember) {
		// TODO Auto-generated method stub
		Member m= new Member();
		m.setMemberId(loginMember.getMemberId());
		m.setMemberNo(loginMember.getMemberNo());
		m.setMemberName(loginMember.getMemberId());
		System.out.println("\n---------------내 정보 수정---------------");
		System.out.print("PW 입력 : ");
		m.setMemberPw(sc.next());
		System.out.print("전화번호 입력(ex.01011112222) : ");
		m.setPhone(sc.next());

		return m;
	}

	public void printMine(Member loginMember) {
		// TODO Auto-generated method stub
		System.out.println("\n----------------내 정보 보기--------------");
		System.out.println("회원 번호 :" + loginMember.getMemberNo());
		System.out.println("아이디   :" + loginMember.getMemberId());
		System.out.println("비밀번호 :" + loginMember.getMemberPw());
		System.out.println("이름 :" + loginMember.getMemberName());
		System.out.println("전화 :" + loginMember.getPhone());
	}

	public int deleteMember() {
		// TODO Auto-generated method stub

		System.out.println("\n----------회원탈퇴-----------");
		System.out.print("정말 탈퇴 하시겠습니까?(1.yes/2.no) ? ");

		return sc.nextInt();
	}

	public Board insertBoard() {
		// TODO Auto-generated method stub
		Board b = new Board();
		System.out.println("------게시물 작성--------");
		System.out.print("제목 입력 : ");
		String board_title = sc.next();
		System.out.print("내용 입력 : ");
		String board_content = sc.next();

		b.setBoardTitle(board_title);
		b.setBoardContent(board_content);

		return b;
	}

	public void printAllBoard(ArrayList<Board> boards) {
		// TODO Auto-generated method stub
		System.out.println("\n------------게시글 목록 ---------------");
		for (Board b : boards) {
			System.out.println(b.getBoardNo() + "\t" + b.getBoardTitle() + "\t"
					+ (b.getBoardWriter() == null ? "탈퇴회원" : b.getBoardWriter()) + "\t" + b.getReadCount() + "\t"
					+ b.getWriteDate());
		}

	}

	public int getBoardNum() {
		// TODO Auto-generated method stub
		System.out.print("게시물 번호 입력 : ");
		int num = sc.nextInt();
		return num;
	}

	public Board printDetail(Board b) {
		// TODO Auto-generated method stub
		System.out.println("-----------게시글 정보---------------");
		System.out.println("게시물 번호 : "+b.getBoardNo());
		System.out.println("게시물 제목 : "+b.getBoardTitle());
		System.out.println("게시물 내용 : "+b.getBoardContent());
		System.out.println("게시물 작성자 : "+b.getBoardWriter());
		System.out.println("게시물 조회수 : "+(b.getReadCount()+1));
		System.out.println("게시물 작성일 : "+b.getWriteDate());
		b.setReadCount(b.getReadCount()+1);
		
		return b;
	}

	public Board modifyBoard(Board b) {
		// TODO Auto-generated method stub
		System.out.print("제목 입력 : ");
		String boardTitle =  sc.next();
		b.setBoardTitle(boardTitle);
		System.out.print("내용 입력 : ");
		String boardContent = sc.next();
		b.setBoardContent(boardContent);
		
		return b;
	}
	
}
