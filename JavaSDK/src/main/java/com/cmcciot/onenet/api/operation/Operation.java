package com.cmcciot.onenet.api.operation;

import com.cmcciot.onenet.api.OnenetAPI;
import com.cmcciot.onenet.api.response.BasicResponse;

/**
 * Created by Yuzhou on 2017/3/21.
 */
public interface Operation<T> {
    BasicResponse<T> execute(OnenetAPI api);
}
