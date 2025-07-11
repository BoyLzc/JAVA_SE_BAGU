package org.example.VariableArguments;

public class Main {
    public static void main(String[] args)
    {
        print(5, "Holis", "公众号:Hollis", "博客：www.hollischuang.com", "QQ：907607222");
    }
    // 可变长参数
    public static void print(int num, String... strs)
    {
        for (int i = 0; i < num; i++){
            for (String str : strs)
            {
                System.out.println(str);
            }
        }
    }
}
