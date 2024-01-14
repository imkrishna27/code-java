package java_core_2;

class TypePromotion {
    int sum(int a,int b)  {
        return (int)(a+b);
    }
}

class Test {
    public static void main(String[] args) {
        TypePromotion typePromotion = new TypePromotion();
        System.out.println(typePromotion.sum('a',2));
    }
}
