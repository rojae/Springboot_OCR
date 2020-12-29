package knu.ialab.sponsor.common;

public class MaskingUtils {

    /*
     * @Param target : 대상
     * @Param maskSize : 마스킹 할 사이즈 (인덱스 0부터 시작)
     */
    public static String doMasking(String target, int showingSize){
        String res = target.substring(0, showingSize);
        for (int i = showingSize; i < target.length(); i++) {
            res += "*";
        }
        return res;
    }
}
