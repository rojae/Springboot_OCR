package knu.ialab.sponsor.authentication.mail;

import knu.ialab.sponsor.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface MailRepository extends JpaRepository<Mail, Long> {

    Mail findByUserSeqAndAuthKeyAndAuthFalse(String userSeq, String authKey);

    @Modifying
    @Transactional
    @Query("UPDATE TBL_MAIL q set q.auth = true where q.userSeq = :#{#account.userSeq} AND q.auth = false")
    void updateIsAuth(@Param("account") Account account);}
