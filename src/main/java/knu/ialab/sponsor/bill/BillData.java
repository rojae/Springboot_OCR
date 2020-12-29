package knu.ialab.sponsor.bill;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class BillData {
    public String mallNo;
    public String authNo;
    public String totalPrice;
    public String now;

    public boolean isFull(BillData billData){
        return (mallNo == null && authNo == null && totalPrice == null);
    }

}
