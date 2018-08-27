package com.example.admin.scloud.service;

import android.support.annotation.IntDef;

@IntDef({
        RepeatType.NO_REPEAT,
        RepeatType.REPEAT_ONE,
        RepeatType.REPEAT_ALL
})
public @interface RepeatType {
    int NO_REPEAT = 0;
    int REPEAT_ONE = 1;
    int REPEAT_ALL = 2;
}
