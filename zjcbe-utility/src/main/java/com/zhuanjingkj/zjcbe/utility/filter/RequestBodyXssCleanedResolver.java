package com.zhuanjingkj.zjcbe.utility.filter;

import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.List;

public class RequestBodyXssCleanedResolver extends RequestResponseBodyMethodProcessor {

	public RequestBodyXssCleanedResolver(List<HttpMessageConverter<?>> converters) {
		super(converters);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(RequestBodyXssCleaned.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		Object o = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
		//将所有string类型的feild进行xss过滤
		o = XssSqlFilterUtils.cleanXSSfields(o);
		return o;
	}

}

