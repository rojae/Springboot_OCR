package knu.ialab.sponsor.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUserSeqAndAuthTrue(String userSeq);

    //Account findByUserSeq(String userSeq);

    @Modifying
    @Transactional
    @Query("UPDATE TBL_USER q set q.isDel = true where q.userSeq = :userSeq AND q.isDel = false")
    void updateIsDel(@Param("userSeq") String userSeq);

    List<Account> findAll();

    // only new password
    @Modifying
    @Transactional
    @Query("UPDATE TBL_USER q set q.password = :#{#account.password} where q.userSeq = :#{#account.userSeq} AND q.isDel = false")
    void updatePassword(@Param("account") Account account);

    // create account result ~> 0
    // deny ~> 1
    @Query("SELECT q FROM TBL_USER q WHERE q.userSeq = :#{#account.userSeq} AND q.isDel = false")
    Account checkDuplicate(@Param("account") Account account);

    @Modifying
    @Transactional
    @Query("UPDATE TBL_USER q set q.auth = true where q.userSeq = :#{#account.userSeq} AND q.isDel = false and q.auth = false")
    void updateIsAuth(@Param("account") Account account);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tbl_user set user_point = user_point + :point where user_seq = :userSeq AND is_del = 0 and is_auth = 1", nativeQuery = true)
    void addPoint(String userSeq, Integer point);
}