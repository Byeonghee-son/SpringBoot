package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@Controller
public class BoardController {
	
	
//	@RequestMapping("/getBoardList")
//	public String getBoardList(Model model) {
//		List<Board> boardList = new ArrayList<Board>();
//		
//		for(int i = 1; i <=10; i++) {
//			boardList.add(Board.builder()
//					.seq((long)i)
//					.title("title"+i)
//					.writer("writer"+i)
//					.content("content"+i)
//					.createDate(new Date())
//					.cnt(0L).build());
//		}
//		model.addAttribute("boardList",boardList);
//		return "getBoardList";
//	}
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> list = boardService.getBoardList();
		model.addAttribute("boardList", boardService.getBoardList());
		return "getBoardList";
	}
	
	@GetMapping("/insertBoard")
	public String insertBoard() {
		return "insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoardPost(Board board) {
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	@GetMapping("getBoard")
	public String getBoard(Long seq, Model model) {
		Board board = boardService.getBoard(Board.builder().seq(seq).build());
		model.addAttribute("board", board);
		//${board.seq}
		return "getBoard";
	}
}
