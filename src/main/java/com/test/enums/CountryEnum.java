package com.test.enums;

public enum CountryEnum {

    ONE(1,"韩"),TWO(2,"魏"),THREE(3,"赵"),FOUR(4,"齐"),FIVE(5,"楚"),SIX(6,"燕");
    // mysql
    // ID	col1 col2 col3 col4
    //ONE(k,v1,v2,v3,v4)

    private Integer id;
    private String name;

    private CountryEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static CountryEnum getKey(int key){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if(key == value.getId()){
                return value;
            }
        }
        return null;
    }



    public static void main(String[] args){

        /*int a = 3000+3000;
        int b = 500+2300+3800;
        System.out.println(a-b);*/

        int bd = 9000 + 1500 +3000;

        int zs = 245*6+490*5+870*5+245*7+245*6;

        int c = 500 +2300;
        System.out.println(bd+zs-c);

    }

}
