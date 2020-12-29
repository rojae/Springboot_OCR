package knu.ialab.sponsor.bill;

import knu.ialab.sponsor.account.Account;
import knu.ialab.sponsor.account.AccountRepository;
import knu.ialab.sponsor.account.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("billService")
public class BillService {

    @Autowired
    BillRepository billRepository;

    @Autowired
    AccountRepository accountRepository;

    private final double percentage = 0.01;

    public void insertBill(Account account, BillData billData) {
        eraseKorean(billData);

        int point = getPoint(billData);

        Bill bill = Bill.builder().userName(account.getUserName()).userSeq(account.getUserSeq())
                .role(account.getRole()).userPoint(point)
                .email(account.getEmail()).userType(account.getUserType()).build();
        bill.setMallNo(billData.mallNo);
        bill.setAuthNo(billData.authNo);
        bill.setTotalPrice(billData.totalPrice);

        accountRepository.addPoint(account.getUserSeq(), bill.getUserPoint());

        this.billRepository.save(bill);
    }

    public List<Bill> getBillList(Account account){
        return billRepository.findAllByUserSeqAndEmail(account.getUserSeq(), account.getEmail());
    }

    public int getPoint(BillData billData){
        return (int) (Integer.parseInt(billData.totalPrice) * percentage);
    }

    public void eraseKorean(BillData billData){
        billData.totalPrice = billData.totalPrice.replace("원","");
        billData.totalPrice = billData.totalPrice.replace(",","");
        billData.totalPrice = billData.totalPrice.replace(".","");
    }

    public boolean isDuplicate(BillData billData){
        Bill isDuplicate = billRepository.findByAuthNo(billData.authNo);
        return (isDuplicate != null);
    }
}
