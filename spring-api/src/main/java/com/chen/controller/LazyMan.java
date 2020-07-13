package com.chen.controller;

public class LazyMan {
    private LazyMan(){
        System.out.println(Thread.currentThread().getName()+"启动");
    }
    private volatile static LazyMan LAZY_MAN=null;

    public static LazyMan getObj(){
        if (LAZY_MAN==null){
            synchronized (LazyMan.class){
                if (LAZY_MAN==null){
                    LAZY_MAN = new LazyMan();
                }
            }
        }
        return LAZY_MAN;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                LazyMan.getObj();
            }).start();
        }
    }



}
