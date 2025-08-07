package com.prajwal.ecommerce.service;

import com.prajwal.ecommerce.exceptions.AddressNotFoundException;
import com.prajwal.ecommerce.exceptions.ProductNotFoundException;
import com.prajwal.ecommerce.libraries.GoogleMapsApi;
import com.prajwal.ecommerce.libraries.models.GLocation;
import com.prajwal.ecommerce.models.Address;
import com.prajwal.ecommerce.models.DeliveryHub;
import com.prajwal.ecommerce.models.Product;
import com.prajwal.ecommerce.models.Seller;
import com.prajwal.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    private final GoogleMapsApi calculatetime;
    private final AddressRepository addressRepository;
    private final DeliveryHubRepository deliveryHubRepository;
    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(GoogleMapsApi calculatetime,
                              AddressRepository addressRepository,
                              DeliveryHubRepository deliveryHubRepository,
                              SellerRepository sellerRepository,
                              UserRepository userRepository,
                              ProductRepository productRepository) {
        this.calculatetime = calculatetime;
        this.addressRepository = addressRepository;
        this.deliveryHubRepository = deliveryHubRepository;
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    /**
     * Estimates the delivery date for a product to a specific address
     *
     * @param productId The ID of the product to be delivered
     * @param addressId The ID of the delivery address
     * @return Estimated delivery date
     * @throws ProductNotFoundException if product is not found
     * @throws AddressNotFoundException if address is not found
     */
    @Override
    public Date estimateDeliveryDate(int productId, int addressId)
            throws ProductNotFoundException, AddressNotFoundException {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        Address userAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address not found with id: " + addressId));

        Seller seller = sellerRepository.findById(product.getSeller().getId())
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        Address sellerAddress = seller.getAddress();
        DeliveryHub hub = deliveryHubRepository.findByAddress_ZipCode(userAddress.getZipCode())
                .orElseThrow(() -> new RuntimeException("No delivery hub found for zip code: " + userAddress.getZipCode()));

        // Google API Locations
        GLocation sellerLoc = new GLocation();
        sellerLoc.setLatitude(sellerAddress.getLatitude());
        sellerLoc.setLongitude(sellerAddress.getLongitude());

        GLocation hubLoc = new GLocation();
        hubLoc.setLatitude(hub.getAddress().getLatitude());
        hubLoc.setLongitude(hub.getAddress().getLongitude());

        GLocation userLoc = new GLocation();
        userLoc.setLatitude(userAddress.getLatitude());
        userLoc.setLongitude(userAddress.getLongitude());

        // Estimate travel time
        long totalMinutes = calculatetime.estimate(sellerLoc, hubLoc) + calculatetime.estimate(hubLoc, userLoc);

        // Add 24 hours (buffer) to the estimated delivery time
        long deliveryTimeMillis = System.currentTimeMillis() + (totalMinutes + 24 * 60) * 60 * 1000;

        return new Date(deliveryTimeMillis);
    }
}
