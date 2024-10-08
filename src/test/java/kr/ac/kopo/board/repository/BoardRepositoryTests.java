package kr.ac.kopo.board.repository;

import kr.ac.kopo.board.entity.Board;
import kr.ac.kopo.board.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoard(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@kopo.ac.kr")
                    .build();

            Board board = Board.builder()
                    .title("Title   " + i)
                    .content("Content   " + i)
                    .writer(member)
                    .build();

            boardRepository.save(board);
        });
    }

    @Transactional // 무조건 이 어노테이션 붙이고 해야됨
    @Test
    public void testRead(){
        Optional<Board> result = boardRepository.findById(5L);
        Board board = result.get();

        System.out.println(board);
        System.out.println(board.getWriter());
    }

    @Test
    public void testReadWithWriter(){
        Object result = boardRepository.getboardWithWriter(10L);
        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testReadWithReply(){

        List<Object[]> result = boardRepository.getboardWithReply(77L);
        for (Object[] arr: result){
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    public void testBoardWithReplyCount(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    public void testRead3(){

        Object result = boardRepository.getBoardByBno(99L);
        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }
}
