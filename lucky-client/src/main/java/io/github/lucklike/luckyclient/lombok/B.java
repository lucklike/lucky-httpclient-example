package io.github.lucklike.luckyclient.lombok;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class B extends A{

    private String b = "B";


    public static void main(String[] args) {
        B b1 = new B();
        System.out.println(b1);
    }
}
