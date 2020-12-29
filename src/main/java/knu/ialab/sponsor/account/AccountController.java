package knu.ialab.sponsor.account;

import knu.ialab.sponsor.authentication.mail.Mail;
import knu.ialab.sponsor.authentication.mail.MailService;
import knu.ialab.sponsor.bill.BillService;
import knu.ialab.sponsor.common.ApiMsg;
import knu.ialab.sponsor.common.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    MailService mailService;

    @Autowired
    BillService billService;

    //http://localhost:8080/account/오재성/201413314/201513430@kangwon.ac.kr/123/ADMIN/ADMIN
    @PostMapping("/account/{userName}/{userSeq}/{email}/{password}/{role}/{userType}")
    public Object createAccount(@ModelAttribute Account account) {
        Account createdAccount = accountService.createNew(account);
        boolean isSent = false;

        if (createdAccount == null)
            return ApiMsg.builder().code(999).msg("이미 존재하는 계정입니다").build();
        else {
            try {
                isSent = mailService.sendMail(account);
            } catch (MessagingException e) {
                return ApiMsg.builder().code(500).msg("시스템 오류입니다").detail("관리자에게 문의하세요").build();
            }
            if (isSent)
                return ApiMsg.builder().code(200).msg("계정 생성에 성공하였습니다").detail("이메일 인증을 진행하세요").obj(createdAccount).build();
            else
                return ApiMsg.builder().code(403).msg("이메일 형식에 어긋납니다").detail("강원대학교의 이메일이 아닙니다").build();
        }
    }

    @PostMapping("/account/me")
    public Object showMe(@CurrentUser Account user){
        System.out.println("SHOW ME : " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return accountService.showMe(user);
    }

    @DeleteMapping("/account")
    public Object deleteAccount(@CurrentUser Account user) {
        System.out.println("DELETE : " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        accountService.deleteAccount(user);
        return ApiMsg.builder().code(200).msg("회원 탈퇴에 성공하셨습니다").detail("감사합니다").build();
    }

    @PutMapping("/account")
    public Object updateAccount(@CurrentUser Account user, @RequestParam("newPwd") String newPassword) {
        System.out.println("UPDATE BEFORE : " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        accountService.updateAccount(user, newPassword);
        System.out.println("UPDATE TO : " + user.toString());
        return ApiMsg.builder().code(200).msg("비밀번호 변경되었습니다").detail("재로그인을 시도합니다").build();
    }

    @PostMapping("/account/points")
    @Secured("ROLE_USER")
    @ResponseBody
    public Object getPoint(@CurrentUser Account user){
        System.out.println("GET USER POINT");
        return accountService.getPoint(user);
    }

    @PostMapping("/account/bills")
    @Secured("ROLE_USER")
    @ResponseBody
    public Object getBill(@CurrentUser Account user){
        System.out.println("GET USER BILL LIST");
        return billService.getBillList(user);
    }



}
