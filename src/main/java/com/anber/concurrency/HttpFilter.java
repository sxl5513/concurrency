package com.anber.concurrency;

import com.anber.concurrency.example.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author anber
 * @date 2018/11/7
 **/
@Slf4j
public class HttpFilter implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("do filter {}, {}", Thread.currentThread().getId(), request.getRequestURI());
        RequestHolder.add(Thread.currentThread().getId());
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
