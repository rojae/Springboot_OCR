package knu.ialab.sponsor.account;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "TBL_BILL")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Bill {

    @Id
    @GeneratedValue
    private Integer id;

    // 가맹점 번호
    @Column(name = "mallNo", nullable = false)
    private String mallNo;

    // 승인번호
    @Column(name = "authNo", unique = true, nullable = false)
    private String authNo;

    // 합계
    @Column(name = "totalPrice", nullable = false)
    private String totalPrice;

    // 돔명이인은 존재할 수 있다
    @Column(name = "userName", nullable = false)
    private String userName;

    // 학번은 같을 수 없다
    @Column(name = "userSeq", nullable = false)
    private String userSeq;

    // 이메일은 같을 수 없다 (학교의 웹메일)
    @Column(name = "email", nullable = false)
    private String email;

    // 사용자, 가맹점, 관리자
    @Column(name = "role", columnDefinition = "VARCHAR(255) DEFAULT 'USER' ")
    private String role;

    // 사용자, 교직원, 관리자
    @Column(name = "userType", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'USER' ")
    private String userType;

    // 마일리지 포인트
    @Column(name = "userPoint")
    private Integer userPoint;

    // 생성일자
    @Column(name = "createdDate", nullable = false)
    @CreatedDate
    private LocalDateTime createdDate;

}

