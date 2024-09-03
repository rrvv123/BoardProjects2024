package kr.ac.kopo.board.repository;

import kr.ac.kopo.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b, w from Board b left join b.writer w where b.bno=:bno")
    Object getboardWithWriter(@Param("bno") Long bno);
}