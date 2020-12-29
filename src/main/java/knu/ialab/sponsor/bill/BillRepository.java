package knu.ialab.sponsor.bill;

import knu.ialab.sponsor.account.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {

    List<Bill> findAllByUserSeqAndEmail(String userSeq, String email);

    Bill findByAuthNo(String authNo);
}