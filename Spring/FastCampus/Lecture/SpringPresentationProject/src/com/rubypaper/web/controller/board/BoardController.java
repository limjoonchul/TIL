package com.rubypaper.web.controller.board;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.rubypaper.biz.board.BoardService;
import com.rubypaper.biz.board.BoardVO;

// @SessionAttributes�� �̿��ϸ� Ư�� �̸����� Model�� ����� �����͸� ���ǿ��� ��ϵǵ��� �Ѵ�.
// ���� getBoard �޼ҵ忡�� Model�� "board"��� �̸����� BoardVO ��ü�� �����ϸ� 
// ���ǿ��� "board" ��� �̸����� BoardVO ��ü�� ��ϵǴ� ���̴�.
// �迭���·ε� ���� �� ��� �� �� �ִ�.
@SessionAttributes("board")
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService; // ��Ÿ���� ��ü�� �޸𸮿� ������ �ַ� ������ ��ü�� �ּҸ� �Ҵ��Ѵ�.
	
	
	@RequestMapping("/dataTransform.do")
	@ResponseBody // http ���� �������� body�� Json���� ��ȯ�� �� ����� ����ϵ��� �ϴ� ���� �����̽����� �˻����ϵ� url�� �������ָ� �����Ϸ��� �ý��ۿ���  �۸���� json���·� ���´�
	// ��json���·� ���� ���� �̿��ؼ� ȭ���� ����� ������� �����͸� ��� �ѷ��ָ� �Ǵ°��̴�. �߿��Ѱ� ��� ���� �����ϵ� �� ������� json�����ͷ� ó���� �� �ִ�.
	public List<BoardVO> dataTransform(BoardVO vo){
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		return boardService.getBoardList(vo);
	}
	
	// �۵�� ȭ�� �̵�
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.GET)
	public String insertBoardView(BoardVO vo) {
		// �Ű������� ���� vo ��ü�� �ڵ����� request ���� ��ü�� ��ϵȴ�.
		// ���� ���������� ����Ǵ� ȭ��(jsp)���� el�� �̿��Ͽ� ���� �Ѹ� �� �ִ�.
		vo.setTitle("200�� �̸�����..");
		vo.setWriter("�׽���");
		vo.setContent("2000�� �̸����� �ۼ����ּ���.");
		return "insertBoard"; // ������: �ϰ� ��������ϸ� �ȵ�~

		// jsp������ el�� ���ָ� boardVO�� �޴´�.
	}
	// �Ȱ��� insetboard.do �� ������ get/post����̳Ŀ� ���� �ٸ��� ���۽�Ŵ.
	// �޼ҵ尡 �ְ� ������ ���̶����� �����ε����� �ĺ��� ������ �̸��� �ٸ��� ����.

	// �۵��
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.POST)
	public String insertBoard(BoardVO vo ) throws Exception {
		
		MultipartFile uploadFile = vo.getUploadFile();
		if(!uploadFile.isEmpty()) // ���� ���ε� ������ �ϳ��� �ִٸ�...
			uploadFile.transferTo(new File("C:/DEV/upload_files/" + uploadFile.getOriginalFilename()));
		
		// �� ��� ��� ó��
		boardService.insertBoard(vo);	
		return "forward:getBoardList.do";
	}

	// �� ��
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		System.out.println("�Խñ� �� ��ȸ ��� ó��");
		BoardVO board = boardService.getBoard(vo);
//		session.setAttribute("board", board);
		System.out.println("�� �� ��ȸ������ BoardVO  ��ü ����");
		System.out.println(vo.toString());
		model.addAttribute("board", board);

		return "getBoard";
	}

	// �� ���
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo,Model model) {
		System.out.println("�Խñ� ��� ��� ó��");

		if (vo.getSearchCondition() == null)
			vo.setSearchCondition("TITLE");
		if (vo.getSearchKeyword() == null)
			vo.setSearchKeyword("");
		// ���� �˻� ����� ���ǿ� �����ؼ��� �ȵȴ�. �˻� ����� request�� ����ؾ� �Ѵ�!!
		// ModelAndView�� Model ��ü�� �˻� ����� ����ϸ� �ڵ����� request�� ������ش�. �߿��� �����̴�!!!! 
		// �׷��� ������ �������� �ʴ´�.
//		session.setAttribute("boardList", boardDAO.getBoardList(vo)); // �۸���� ���ǿ� ����
//		session.setAttribute("search", vo);
		
		model.addAttribute("boardList", boardService.getBoardList(vo));
		model.addAttribute("search", vo);

		return "getBoardList";

	}
	
	// �� ����
	// @ModelAttribute�� ���ǿ� "board"��� �̸����� �����Ͱ� ��ϵǾ��ִٸ� �� ��ü�� vo������ ���ε��ض� ��� �ǹ��̴�. ������ �н�
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {

		System.out.println("�Խñ� ���� ��� ó��");
		System.out.println("���������� BoardVO  ��ü ����");
		System.out.println(vo.toString());
		// Model�� ������ �����ʹ� �ڵ����� request�� ��ϵȴ�. ������ �ƴ϶�
		// 
		boardService.updateBoard(vo);
		return "forward:getBoardList.do";

	}

	// �� ����

	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) { // ���߿� �Ű��������þ�� �پ�� �� �ֱ� ������ ��ü�������� �־��ִ°� ����.
		System.out.println("�Խñ� ���� ��� ó��");
		boardService.deleteBoard(vo);
		return "forward:getBoardList.do";

	}

	/*
	 * // �� ����
	 * 
	 * @RequestMapping("/deleteBoard.do") public String
	 * deleteBoard(@RequestParam(value="seq", defaultValue="1", required=true) int
	 * boardSeq, BoardDAOJDBC boardDAO) { // �⺻���� ���� �� �ִ�. �Ű������̸��� �Ȱ��ٸ� ����. ������Ÿ�Ա�����
	 * ���������� ó������ System.out.println(boardSeq + "�� �� ���� ó��"); //
	 * boardDAO.deleteBoard(vo);
	 * 
	 * return "forward:getBoardList.do";
	 * 
	 * }
	 */

}
