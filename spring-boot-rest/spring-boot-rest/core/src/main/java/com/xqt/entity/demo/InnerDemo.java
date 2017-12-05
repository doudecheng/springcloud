package com.xqt.entity.demo;

/**
 * @author andy
 */
public class InnerDemo {

    private String id;

    private String name;


    private InnerDemo(String id,String name){
        this.id = id;
        this.name = name;
    }

    public static class Builder{
        private String id = null;
        private String name = null;

        public Builder() {
        }
        public InnerDemo.Builder setId(String id) {
            this.id = id;
            return this;
        }
        public InnerDemo.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public InnerDemo build(){
            return new InnerDemo(this.id,this.name);
        }

    }

    public static InnerDemo.Builder newBuilder(){
        return new InnerDemo.Builder();
    }


    @Override
    public String toString() {
        return "InnerDemo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
