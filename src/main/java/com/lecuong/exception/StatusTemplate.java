package com.lecuong.exception;

import org.springframework.http.HttpStatus;

public interface StatusTemplate {

    StatusResponse SUCCESS =
            new StatusResponse("SHOP-SUCCESS", "SUCCESS", HttpStatus.OK);
    StatusResponse EXPIRE_TOKEN =
            new StatusResponse("SHOP-TOKEN-EXPIRED", "token expired", HttpStatus.UNAUTHORIZED);
    StatusResponse TOKEN_IN_VALID =
            new StatusResponse("SHOP-TOKEN-INVALID", "token invalid", HttpStatus.UNAUTHORIZED);
    StatusResponse USER_NOT_FOUND =
            new StatusResponse("SHOP-USER-NOT-FOUND", "User not found", HttpStatus.NOT_FOUND);
    StatusResponse USER_MUST_LOGIN =
            new StatusResponse("SHOP-USER-MUST-LOGIN", "User must login", HttpStatus.BAD_REQUEST);

    StatusResponse PROVIDER_EXIST =
            new StatusResponse("SHOP-PROVIDER-EXIST", "Provider exist", HttpStatus.BAD_REQUEST);
    StatusResponse PROVIDER_NOT_FOUND =
            new StatusResponse("SHOP-PROVIDER-NOT-FOUND", "Provider not found", HttpStatus.NOT_FOUND);

    StatusResponse CATEGORY_EXIST =
            new StatusResponse("SHOP-CATEGORY-EXIST", "Category exits", HttpStatus.BAD_REQUEST);
    StatusResponse CATEGORY_NOT_FOUND =
            new StatusResponse("SHOP-CATEGORY-NOT_FOUND", "Category not found", HttpStatus.NOT_FOUND);

    StatusResponse PRODUCT_EXIST =
            new StatusResponse("SHOP-PRODUCT-EXIST", "Product exits", HttpStatus.BAD_REQUEST);
    StatusResponse PRODUCT_NOT_FOUND =
            new StatusResponse("SHOP-PRODUCT-NOT_FOUND", "Product not found", HttpStatus.NOT_FOUND);

    StatusResponse CART_EXIST =
            new StatusResponse("SHOP-CART-EXIST", "Cart exist", HttpStatus.BAD_REQUEST);
    StatusResponse CART_NOT_FOUND =
            new StatusResponse("SHOP-CART-NOT_FOUND", "Cart not found", HttpStatus.NOT_FOUND);

    StatusResponse CART_ITEM_NOT_FOUND =
            new StatusResponse("SHOP-CART-ITEM-NOT_FOUND", "Cart item not found", HttpStatus.NOT_FOUND);
    StatusResponse CART_ITEM_EMPTY =
            new StatusResponse("SHOP-CART-ITEM-EMPTY", "Cart item empty", HttpStatus.BAD_REQUEST);
}
