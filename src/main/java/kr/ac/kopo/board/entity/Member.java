package kr.ac.kopo.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor //
@NoArgsConstructor // 기본생성자
@Getter
@ToString
public class Member extends BaseEntity{
    @Id
    private String email;

    private String password;
    private String name;
}
