package edu.pnu;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	private BoardRepository boardRepo;
	
//	//@Test
//	public void testInsertBoard() {
//		for(int i = 1; i < 11; i++) {
//			boardRepo.save(Board.builder()
//					.title("title"+i)
//					.writer("writer"+i)
//					.content("content"+i)
//					.createDate(new Date())
//					.cnt(0L)
//					.build());
//			}
//	}
//	//@Test
//	public void testGetBoard() {
//		Board board = boardRepo.findById(5L).get();
//		System.out.println(board);
//		
//	}
//	//@Test
//	public void testUpdateBoard() {
//	{	Board board = boardRepo.findById(1L).get();
//		System.out.println("수정 전");
//		System.out.println(board);
//		
//		board.setTitle("제목 수정");
//		boardRepo.save(board);
//	}
//	{
//		Board board = boardRepo.findById(1L).get();
//		System.out.println("수정 후");
//		System.out.println(board);
//	}
//	}
//	
//	//@Test
//	public void testDeleteBoard() {
//		boardRepo.deleteById(2L);
//	}
//	
//	//@Test
//	public void testfindAll() {
//		List<Board> list = boardRepo.findAll();
//		
//		System.out.println("모든 데이터를 출력합니다.");
//		for(Board b : list) {
//			System.out.println(b);
//		}
//	}
//	
	@Test
	public void testQueryAnnotationTest1() {
		//select b from Board b where b.title like %title1% order by b.seq desc
		List<Board> list = boardRepo.queryAnnotationTest1("title1");
		for(Board b : list) {
			System.out.println(b);
		}
	}
	
	@Test
	public void testQueryAnnotationTest2() {
		List<Board> list = boardRepo.queryAnnotationTest2("title2");
		for(Board b : list) {
			System.out.println(b);
		}
	}
	
//	@Test
//	public void testQueryAnnotationTest3() {
//		List<Object[]> list = boardRepo.queryAnnotationTest3("title3");
//		for(Object[] b : list) {
//			System.out.println(Arrays.toString(b));
//		}
//	}
//	
//	@Test
//	public void testQueryAnnotationTest4() {
//		List<Object[]> list = boardRepo.queryAnnotationTest4("title4");
//		for(Object[] b : list) {
//			System.out.println(Arrays.toString(b));
//		}
//	}
//	
//	@Test
//	public void testQueryAnnotationTest5() {
//		Pageable paging = PageRequest.of(5, 5);
//		List<Board> list = boardRepo.queryAnnotationTest5(paging);
//		for(Board a : list) {
//			System.out.println("--->" + a);
//		}
//	}
}
