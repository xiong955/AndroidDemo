package com.xiong.databinding.model;

import android.databinding.ObservableField;

/**
 * @author: xiong
 * @time: 2018/08/14
 * @说明:
 */
public class User {
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> age = new ObservableField<>();
    public ObservableField<String> url = new ObservableField<>();
}
