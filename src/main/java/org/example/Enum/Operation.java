package org.example.Enum;

public enum Operation {
    ADD {
        @Override
        public double apply(double x, double y)
        {
            return x + y;
        }
    },
    SUBTRACT {
        @Override
        public double apply(double x, double y)
        {
            return x - y;
        }
    },
    DIVIDE {
        @Override
        public double apply(double x, double y)
        {
            return x / y;
        }
    },
    MULTIPLY {
        @Override
        public double apply(double x, double y)
        {
            return x * y;
        }
    };
    // 枚举可以定义方法，每个枚举常量都可以具备独特的行为
    public abstract double apply(double x, double y);
}
