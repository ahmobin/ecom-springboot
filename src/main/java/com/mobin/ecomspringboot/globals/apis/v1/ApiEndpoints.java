package com.mobin.ecomspringboot.globals.apis.v1;

public class ApiEndpoints {

    //currencies apis
    public static final String CURRENCIES_API = "/api/v1/currencies";
    public static final String SINGLE_CURRENCIES_API = "/api/v1/currencies/{id}";
    public static final String CURRENCIES_UPDATE_API = "/api/v1/currencies/{id}/update";
    public static final String PRODUCT_STATUSES_API = "/api/v1/product-statuses";
    public static final String SINGLE_PRODUCT_STATUSES_API = "/api/v1/product-statuses/{id}";
    public static final String PRODUCT_STATUSES_UPDATE_API = "/api/v1/product-statuses/{id}/update";
    public static final String STOCK_STATUSES_API = "/api/v1/stock-statuses";
    public static final String SINGLE_STOCK_STATUSES_API = "/api/v1/stock-statuses/{id}";
    public static final String STOCK_STATUSES_UPDATE_API = "/api/v1/stock-statuses/{id}/update";
    public static final String PRODUCT_UNITS_API = "/api/v1/units";
    public static final String SINGLE_PRODUCT_UNITS_API = "/api/v1/units/{id}";
    public static final String PRODUCT_UNITS_UPDATE_API = "/api/v1/units/{id}/update";
    public static final String CATEGORIES_API = "/api/v1/categories";
    public static final String SINGLE_CATEGORIES_API = "/api/v1/categories/{id}";
    public static final String PRODUCT_CATEGORIES_UPDATE_API = "/api/v1/categories/{id}/update";

    public static final String SUB_CATEGORIES_API = "/api/v1/{categoryId}/sub-categories";
    public static final String SINGLE_SUB_CATEGORIES_API = "/api/v1/{categoryId}/sub-categories/{id}";
    public static final String PRODUCT_SUB_CATEGORIES_UPDATE_API = "/api/v1/{categoryId}/sub-categories/{id}/update";

}
