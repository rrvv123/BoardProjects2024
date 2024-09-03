package kr.ac.kopo.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor //
@NoArgsConstructor // 기본생성자
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity{
    @Id // 기본키(Primary Key) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1씩 자동증가(auto-increment)
    private Long bno;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계 (Board가 다, Member가 1)   ###ManyToOne의 경우 기본적으로 Eager로딩(즉시로딩)방식으로 동작###
    private Member writer; // 외래키 설정(참조무결성 유지)
}
// ###@ManyToOne과 같은 연관관계를 지정하는 경우에는 fetch라는 속성을 지정 가능###
// ###fetch는 항상 Lazy로딩을 가능하면 필요하지 않은 데이터를 가져오지 않도록 Lazy로딩을 기본으로 지정###
// 지연(lazy)로딩의 이해