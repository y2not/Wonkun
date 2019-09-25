package com.example.demo.order.model;

public enum Currency  {
      DEFAULT(0) { public void showCurrency(){ System.out.println("Current currency id: " + getId());} }
    , DOLLLAR(1) { public void showCurrency(){ System.out.println("Current currency id: " + getId());} }
    , EURO(2)    { public void showCurrency(){ System.out.println("Current currency id: " + getId());} }
    , YEN(3)     { public void showCurrency(){ System.out.println("Current currency id: " + getId());} }
    , YUAN(4)    { public void showCurrency(){ System.out.println("Current currency id: " + getId());} };

    int id;
    private Currency(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

}
