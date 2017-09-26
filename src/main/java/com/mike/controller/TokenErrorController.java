package com.mike.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mike.token.annotation.IgnoreSecurity;

/**
 * 
 * 类名称：WebErrorController<br>
 * 类描述：错误页面控制<br>
 * 创建人：qinjiaxue<br>
 * 创建时间：2017年3月2日 下午4:49:57<br>
 * @version v1.0
 *
 */
@Controller
@RequestMapping("/error")
public class TokenErrorController{

    /**
     * token失效提示页面
     * @return
     */
    @IgnoreSecurity
    @RequestMapping(value = "/tokenFailure")
    public String tokenFailure() {
        return "token/CheckTokenFail.html";
    }
}
