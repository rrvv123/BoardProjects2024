package kr.ac.kopo.board.repository;

import kr.ac.kopo.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b, w from Board b left join b.writer w where b.bno=:bno")
    Object getboardWithWriter(@Param("bno") Long bno);

    @Query("select b, r from Board b left join Reply r ON r.board = b where b.bno=:bno")
    List<Object[]> getboardWithReply(@Param("bno") Long bno);

    @Query(value = "select b, w, count (r) from Board b "
            + "left join b.writer w " // **문장의 앞이나 뒷줄에 공백 한칸 필수로 입력**
            + "left join Reply r on r.board = b "
            + "group by b, w",
            countQuery = "select count(b) from Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    @Query("select b, w, count(r) "
            + "from Board b left join b.writer w "
            + "left outer join Reply r on r.board = b "
            + "where b.bno=:bno group by b, w")
    Object getBoardByBno(@Param("bno")Long bno);
}