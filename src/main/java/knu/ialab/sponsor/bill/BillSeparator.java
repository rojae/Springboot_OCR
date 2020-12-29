package knu.ialab.sponsor.bill;

import lombok.Getter;

@Getter
public class BillSeparator {
    public static final String separator = "fields";
    public static final String mallSepar = "mallNo";
    public static final String authSepar = "authNo";
    public static final String priceSepar = "totalPrice";

    public static boolean isMallNo(String str){
        return str.contains(mallSepar);
    }
    public static boolean isAuthNo(String str){
        return str.contains(authSepar);
    }
    public static boolean isPrice(String str){
        return str.contains(priceSepar);
    }
}
