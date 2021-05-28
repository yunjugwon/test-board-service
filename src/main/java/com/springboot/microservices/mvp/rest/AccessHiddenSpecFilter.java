package com.springboot.microservices.mvp.rest;

/**
 * Specification filter to enable hiding of API parameters.
 */
//public class AccessHiddenSpecFilter implements SwaggerSpecFilter {
//
//    @Override
//    public boolean isOperationAllowed(Operation arg0, ApiDescription arg1, Map<String, List<String>> arg2, Map<String, String> arg3, Map<String, List<String>> arg4) {
//        return true;
//    }
//
//    @Override
//    public boolean isParamAllowed(Parameter param, Operation operation, ApiDescription desc, Map<String, List<String>> arg3, Map<String, String> arg4, Map<String, List<String>> arg5) {
//        final String paramAccess = param.paramAccess().toString();
//
//        return !paramAccess.equalsIgnoreCase("Some(hidden)");
//    }
//}