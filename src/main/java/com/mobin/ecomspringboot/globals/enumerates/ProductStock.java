package com.mobin.ecomspringboot.globals.enumerates;

public enum ProductStock {
    IN_STOCK("In-stock"),
    STOCK_OUT("Stock-out"),
    HOLD("Hold");

    final String status;

    ProductStock (String status){
        this.status = status;
    }
}
