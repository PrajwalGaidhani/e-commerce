package com.prajwal.ecommerce.service;


import com.prajwal.ecommerce.exceptions.AddressNotFoundException;
import com.prajwal.ecommerce.exceptions.ProductNotFoundException;

import java.util.Date;

public interface ProductService {
    public Date estimateDeliveryDate(int productId, int addressId) throws ProductNotFoundException, AddressNotFoundException;
}