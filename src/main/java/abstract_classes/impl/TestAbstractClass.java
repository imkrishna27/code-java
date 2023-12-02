package abstract_classes.impl;

import abstract_classes.abstarct.Phone;

class GetModelClass {
    Phone phone;
    GetModelClass(Phone phone) {
        this.phone = phone;
    }
    public String getModel() {
        return this.phone.getModel();
    }
}
public class TestAbstractClass {
    public static void main(String[] args) {
       String modelType =new GetModelClass(new SamsungModel()).getModel();
        System.out.println(modelType);
    }
}
