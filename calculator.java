import java.util.Scanner;

/**
 * 大数计算器
 *
 * @author YaHui Dong
 * @since 2022-08-03
 */
public class calculator {
    /*
     * 输入两个字符串，作为操作数，按加、减、乘、取模操作
     *
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("请您选择操作 1:加法， 2:减法， 3:乘法, 4:取模, 0:退出");
            String strs = scanner.nextLine();
            int choose = Integer.parseInt(strs);

            if (choose == 1) {
                System.out.println("请输入数据:");
                String strr = scanner.nextLine();
                String[] nums = strr.split(" ");
                System.out.print("结果为:");
                System.out.println(add(nums[0], nums[1]));
            } else if (choose == 2) {
                System.out.println("请输入数据:");
                String strr = scanner.nextLine();
                String[] nums = strr.split(" ");
                System.out.print("结果为:");
                System.out.println(sub(nums[0], nums[1]));
            } else if (choose == 3) {
                System.out.println("请输入数据:");
                String strr = scanner.nextLine();
                String[] nums = strr.split(" ");
                System.out.print("乘法结果为:");
                System.out.println(mul(nums[0], nums[1]));
            } else if (choose == 4) {
                System.out.println("请输入数据:");
                String strr = scanner.nextLine();
                String[] nums = strr.split(" ");
                System.out.print("结果为:");
                System.out.println(mod(nums[0], nums[1]));
            } else if (choose == 0) {
                System.out.println("退出系统");
                flag = false;
            } else {
                System.out.println("抱歉，未开通此功能，请您重新选择");
            }
        }

    }

    /**
     * 加法
     * 
     * @param num1 加数
     * @param num2 被加数
     * @return 结果
     */
    private static String add(String num1, String num2) {
        // int index1 = num1.indexOf("."); // 将小数考虑在内，返回小数的索引起始位置
        int len1 = num1.length(); // 截取整数部分
        int len2 = num2.length();

        if (len1 > len2) {
            num2 = compare1(num1, num2); // 长度不够补零
        } else if (len1 < len2) {
            num1 = compare1(num2, num1);
        }
        char[] ch1 = new char[num1.length()]; // 将num1反转
        reverse(num1, ch1);

        char[] ch2 = new char[num2.length()]; // 将num2反转
        reverse(num2, ch2);

        String[] res = new String[len1 > len2 ? len1 + 1 : len2 + 1]; // 返回较长的长度，用于存储结果，相加最多向前进一位
        StringBuilder sb = new StringBuilder(); // 创建一个空白可变的字符容器

        for (int i = 0; i < ch1.length; i++) {
            res[i] = String.valueOf((ch1[i] - '0') + (ch2[i] - '0'));
            if (Integer.parseInt(res[i]) >= 10 && i < ch1.length - 1) { // 进位计算
                res[i] = String.valueOf(Integer.parseInt(res[i]) % 10);
                ch1[i + 1] = (char) (ch1[i + 1] - '0' + '1' - '0' + 48);
            } else {
                StringBuilder bui = new StringBuilder(String.valueOf(res[i]));
                res[i] = bui.reverse().toString();
            }
            sb.append(res[i]); // 最终结果
        }
        String result = sb.reverse().toString();
        return result;

    }

    /**
     * 当第二个数比第一个数小时在前面加0
     * 
     * @param str1
     * @param str2
     * @return
     */
    private static String compare1(String str1, String str2) {
        int num = str1.length() - str2.length();
        for (int i = 0; i < num; i++) {
            str2 = "0" + str2;
        }
        return str2;
    }

    /**
     * 反转
     * 
     * @param str1
     * @param ch1
     */
    private static void reverse(String str1, char[] ch1) {
        for (int i = str1.length() - 1; i >= 0; i--) {
            ch1[i] = str1.charAt(str1.length() - 1 - i);
        }
    }

    /**
     * 减法
     * 
     * @param num1 被减数
     * @param num2 减数
     * @return 结果
     */
    private static String sub(String num1, String num2) {

        // 数值后面补0

        int len1 = num1.length();
        int len2 = num2.length();

        // 数值前面补0
        if (len1 > len2) {
            num2 = compare1(num1, num2);
        } else if (len1 < len2) {
            num1 = compare1(num2, num1);
        }
        // System.out.println(str1 + " " + str2);
        String result = "";
        if (len1 > len2) {
            result = minusxx(num1, num2);
        } else if (len1 == len2) {
            int count = 0;
            for (int i = 0; i < len1; i++) {
                if (num1.charAt(i) == num2.charAt(i)) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == num1.length()) {
                result = "0";
            } else {
                // System.out.println( count);
                int a = Integer.parseInt(num1.substring(count, count + 1));
                int b = Integer.parseInt(num2.substring(count, count + 1));

                if (a >= b) {
                    result = minusxx(num1, num2);
                } else {
                    result = "-" + minusxx(num2, num1);
                }
            }
        } else {
            result = "-" + minusxx(num2, num1);
        }
        return result;
    }

    /**
     * 减法
     * 
     * @param str11
     * @param str22
     * @return
     */
    private static String minusxx(String str11, String str22) {

        int len1 = str11.length();
        int len2 = str22.length();

        char[] ch1 = new char[len1];
        reverse(str11, ch1);

        char[] ch2 = new char[len2];
        reverse(str22, ch2);

        int[] res = new int[len1 > len2 ? len1 + 1 : len2 + 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ch1.length; i++) {
            res[i] = (ch1[i] - '0') - (ch2[i] - '0');
            if (res[i] < 0) {
                res[i] = res[i] + 10;
                ch1[i + 1] = (char) ((ch1[i + 1] - '0') - ('1' - '0') + 48);
            }

            sb.append(res[i]);
        }
        String result = sb.reverse().toString();
        int index = -1;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(0) == '0') {
                if (result.charAt(i) == '0' && result.charAt(i + 1) != '0') {
                    index = i;
                    break;
                }
            }
        }

        if (index != -1) {
            result = result.substring(index + 1);
        }
        return result;

    }

    public static int morePlus(int a, int b) {
        int temp = a;
        int result = 0;
        for (int i = 0; i < b; i++) {
            result += temp;
        }
        return result;

    }

    /**
     * 乘法
     * 
     * @param num1 乘数
     * @param num2 乘数
     * @return 结果
     */
    private static String mul(String num1, String num2) {
        String result = "0";

        if (num1.length() > num2.length()) {
            num2 = compare1(num1, num2);
        } else if (num1.length() < num2.length()) {
            num1 = compare1(num2, num1);
        }

        char[] ch1 = new char[num1.length()];
        reverse(num1, ch1);

        char[] ch2 = new char[num2.length()];
        reverse(num2, ch2);

        String[] res = new String[ch1.length * ch1.length];
        int r = 0;
        for (int i = 0; i < ch1.length; i++) {
            for (int j = 0; j < ch2.length; j++) {
                res[r] = String.valueOf(morePlus(ch1[i] - '0', ch2[j] - '0')); // 乘法操作转化为加法

                for (int k = 0; k <= i + j; k++) {
                    if (k > 0) {
                        res[r] = res[r] + "0";
                    }
                }
                if (r < res.length) {
                    r++;
                }
            }
        }
        for (String s : res) {
            result = add(result, s);
        }

        /** 把多余的零去掉 */
        int index0 = -1;
        if (result.length() > 1) {
            for (int i = 0; i < result.length(); i++) {
                if (result.charAt(0) == '0') {
                    if (result.charAt(i) == '0' && result.charAt(i + 1) != '0') {
                        index0 = i;
                        break;
                    }
                }
            }
            if (index0 != -1) {
                result = result.substring(index0 + 1);
            }
        }
        return result;
    }

    /**
     * 取模运算
     * 
     * 
     */
    public static String mod(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();

        int mod_num2 = Integer.parseInt(num2);
        if (len1 > 30 || len2 > 6) {
            return "0";
        }
        char[] ch1 = new char[num1.length()];
        for (int i = 0; i < num1.length(); i++) {
            ch1[i] = num1.charAt(i);
        }
        int a = 0;

        int nums = ch1[0] - '0';
        a = nums % mod_num2;

        for (int i = 1; i < len1; i++) {
            nums = ch1[i] - '0';
            a = (a * 10 + nums % mod_num2) % mod_num2;

        }

        String result = a + "";
        return result;
    }

    /**
     * 递归取模运算
     * 
     */
    public static String modx(String str1, int str2) {
        return "";

    }

}