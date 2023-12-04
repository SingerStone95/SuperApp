package com.singerstone.jojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * <p>   TIK
 * <p>
 * +     TOK
 * <p>
 * +      IS
 * <p>
 * +     FUN
 * <p>
 * --------
 * <p>
 * =   TRUE
 * <p>
 * 找到每个字母所代表的数字，让
 * <p>
 * 这个方程成立：TIK + TOK + IS + FUN = TRUE.
 * 每个字母代表的数字均不同
 * T, I 和 F 不能为 0（因为一个数字最前面的数不是0）
 * 一个可能的解为:
 * <p>
 * 174
 * +     154
 * <p>
 * +       73
 * <p>
 * +     628
 * <p>
 * --------
 * <p>
 * =   1029
 * <p>
 * (T=1 I=7 K=4 O=5 S=3 F=6 U=2 N=8 R=0 E=9)
 * 目标：
 * <p>
 * 找到任何一个其他的解
 * 找过所有的解，打印其个数
 */
public class TIKTOKISFUN {
    public static void main(String[] args) {
        List<int[]> result=new TIKTOKISFUN().findResult();
        System.out.println(result.size());
        result.forEach(new Consumer<int[]>() {
            @Override
            public void accept(int[] ints) {
                System.out.println(Arrays.toString(ints));
            }
        });

    }

    List<int[]> findResult() {
        List<int[]> result = new ArrayList<>();
        for (int t = 1; t <= 9; t++) {
            for (int i = 1; i <= 9; i++) {
                for (int k = 0; k <= 9; k++) {
                    for (int o = 0; o <= 9; o++) {
                        for (int s = 0; s <= 9; s++) {
                            for (int f = 1; f <= 9; f++) {
                                for (int u = 0; u <= 9; u++) {
                                    for (int n = 0; n <= 9; n++) {
                                        for (int r = 0; r <= 9; r++) {
                                            for (int e = 0; e <= 9; e++) {
                                                //2k+s+n=e
                                                if ((2 * k + s + n) % 10 != e) {
                                                    break;
                                                }
                                                //2i+o+u=u
                                                if (((2 * k + s + n) / 10 + (2 * i + o + u)) % 10 != u) {
                                                    break;
                                                }
                                                //2t+f=r
                                                if (((((2 * k + s + n) / 10 + (2 * i + o + u)) / 10) + (2 * t + f)) % 10 != r) {
                                                    break;
                                                }
                                                // 进位
                                                if (((((2 * k + s + n) / 10 + (2 * i + o + u)) / 10) + (2 * t + f)) / 10 != t) {
                                                    break;
                                                }
                                                int[] com = new int[]{t, i, k, o, s, f, u, n, r, e};
                                                // [1, 1, 3, 6, 9, 9, 6, 5, 2, 0]
                                                // 113+163+19+965=1260

                                                //[1, 1, 0, 7, 4, 7, 8, 6, 0, 0]
                                                //110+170+14+786=1080
                                                result.add(com);

                                            }

                                        }

                                    }

                                }

                            }

                        }

                    }
                }

            }

        }
        return result;
    }
}
