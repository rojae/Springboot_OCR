package knu.ialab.sponsor.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userSeq) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserSeqAndAuthTrue(userSeq);
        if (account == null) {
            throw new UsernameNotFoundException(userSeq);
        }
        System.out.println(account.getUserName() + "님이 로그인 성공하셨습니다.");
        return new UserAccount(account);
    }

    public Account createNew(Account account) {
        if(this.isDuplicate(account))   return null;
        account.encodePassword(passwordEncoder);
        account.setCreatedDate(LocalDateTime.now());
        account.setUserPoint(0);
        return this.accountRepository.save(account);
    }

    public void deleteAccount(Account account) {
        account.setModifiedDate(LocalDateTime.now());
        this.accountRepository.updateIsDel(account.getUserSeq());
    }

    public void updateAccount(Account account, String newPassword){
        account.setPassword(newPassword);
        account.setModifiedDate(LocalDateTime.now());
        account.encodePassword(passwordEncoder);
        this.accountRepository.updatePassword(account);
    }

    public List<Account> getAllUserData(){
        return this.accountRepository.findAll();
    }

    public boolean isDuplicate(Account account){
        return this.accountRepository.checkDuplicate(account) != null;
    }

    public Integer getPoint(Account account){
        Account user =  this.accountRepository.findByUserSeqAndAuthTrue(account.getUserSeq());
        Integer point =  user.getUserPoint();
        return (point == null)? 0 : point;
    }

    public Account showMe(Account account){
       return this.accountRepository.findByUserSeqAndAuthTrue(account.getUserSeq());
    }

}