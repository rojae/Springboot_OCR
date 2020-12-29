package knu.ialab.sponsor.account;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "TBL_USER")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @Id
    @GeneratedValue
    private Integer id;

    // 돔명이인은 존재할 수 있다
    @Column(name = "userName", nullable = false)
    private String userName;

    // 학번은 같을 수 없다
    @Column(name = "userSeq", nullable = false)
    private String userSeq;

    // 비밀번호는 같을 수 있다 ~~~~> 하지만, 해시 암호화 이후에 다른 값으로 암호화된다.
    @Column(name = "password", nullable = false)
    private String password;

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
    @Column(name = "userPoint", columnDefinition = "INT DEFAULT 0")
    private Integer userPoint;

    // 생성일자
    @Column(name = "createdDate", nullable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    // 사용자가 정보를 수정한 경우
    @Column(name = "modifiedDate")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    // 이메일 인증 여부
    @Column(name = "isAuth", nullable = true, columnDefinition = "TINYINT DEFAULT 0", length = 1)
    private boolean auth;

    // 회원 탈퇴 ~~~~> 사용자의 정보는 삭제되서는 않된다 ( 모든 히스토리를 등록 )
    @Column(name = "isDel", nullable = false, columnDefinition = "TINYINT DEFAULT 0", length = 1)
    private boolean isDel;

    // 테스트
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userSeq='" + userSeq + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", userType='" + userType + '\'' +
                ", userPoint=" + userPoint +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", isAuth=" + auth +
                ", isDel=" + isDel +
                '}';
    }

    // 패스워드 암호화 기법
    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
    }
}

