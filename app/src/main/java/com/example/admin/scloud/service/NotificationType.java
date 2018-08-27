package com.example.admin.scloud.service;

import android.support.annotation.IntDef;

@IntDef({
        NotificationType.REQUEST_CODE_NEXT,
        NotificationType.REQUEST_CODE_PAUSE,
        NotificationType.REQUEST_CODE_PREVIOUS,
        NotificationType.REQUEST_CODE_CLEAR
})

public @interface NotificationType {
    int REQUEST_CODE_NEXT = 0;
    int REQUEST_CODE_PAUSE = 1;
    int REQUEST_CODE_PREVIOUS = 2;
    int REQUEST_CODE_CLEAR = 3;
}
