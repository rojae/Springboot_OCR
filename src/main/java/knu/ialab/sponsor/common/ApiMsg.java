package knu.ialab.sponsor.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiMsg {
    public Integer code;
    public String msg;
    public String detail;
    public Object obj;
}
