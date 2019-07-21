package com.wxd.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    String name;

    String age;
}
