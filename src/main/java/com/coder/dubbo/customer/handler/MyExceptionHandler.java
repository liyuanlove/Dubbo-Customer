package com.coder.dubbo.customer.handler;


import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.coder.dubbo.customer.enumeration.MyException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * @author WJL
 */
@ControllerAdvice
public class MyExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> attributes = new HashMap<>(1);
        if (ex instanceof UnauthenticatedException) {
            attributes.put("code", MyException.TOKEN.getCode());
            attributes.put("msg", MyException.TOKEN.getMsg());
        } else if (ex instanceof UnauthorizedException) {
            attributes.put("code", MyException.AUTHORIZATION.getCode());
            attributes.put("msg", MyException.AUTHORIZATION.getMsg());
        } else {
            attributes.put("code", MyException.OTHER.getCode());
            attributes.put("msg", MyException.OTHER.getMsg()+ex.getMessage());
        }

        view.setAttributesMap(attributes);
        mv.setView(view);
        return mv;
    }


}
