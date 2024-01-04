package com.singerstone;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class Test {
    public static void main(String[] args) {
        List<Child> children = new ArrayList<>();
        List<Paren> parens = new ArrayList<>();
        List<GrandPa> grandPas = new ArrayList<>();

        List<Paren> list = new ArrayList<>();
        list.forEach(new Consumer<GrandPa>() {
            @Override
            public void accept(GrandPa paren) {

            }
        });
        YoGaList yoGaList = new YoGaList();
        yoGaList.addAll(parens);
        yoGaList.add(new Child());


        Paren paren = new Child();


    }

}

class GrandPa {


}

class Paren extends GrandPa {

    Paren(){

    }
    void dup() {

    }
}

class Child extends Paren {
    Child(){

    }
    void dup(String a) {

    }
}

class YoGaList {

    public void addAll(List<? super Paren> ts) {

    }

    public void add(Paren paren) {

    }


}

