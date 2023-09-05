package com.buy_eat.buy_eat.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buy_eat.buy_eat.entity.FileData;
import com.buy_eat.buy_eat.entity.Product;
import com.buy_eat.buy_eat.entity.Shop;
import com.buy_eat.buy_eat.entity.Tab;
import com.buy_eat.buy_eat.model.request.BackstageProductAddRequest;
import com.buy_eat.buy_eat.model.request.BackstageProductPutRequest;
import com.buy_eat.buy_eat.model.response.BackstageProductResponse;
import com.buy_eat.buy_eat.repository.IFileDateRepository;
import com.buy_eat.buy_eat.repository.IProductRepository;
import com.buy_eat.buy_eat.repository.IShopRepository;
import com.buy_eat.buy_eat.repository.ITabRepository;
import com.buy_eat.buy_eat.service.IProductService;

@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    IProductRepository iProductRepository;
    @Autowired
    IShopRepository iShopRepository;
    @Autowired
    IFileDateRepository iFileDateRepository;
    @Autowired
    ITabRepository iTabRepository;

    @Override
    public List<Product> findAll() {
        // TODO Auto-generated method stub
        return iProductRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        Optional<Product> findById = iProductRepository.findById(id);
        if (!findById.isPresent()) {
            return null;
        }
        return findById.get();
    }

    @Override
    public Product deleteProductById(int id) {
        Optional<Product> productOptional = iProductRepository.findById(id);

        if (!productOptional.isPresent()) {
            throw new NullPointerException();
        }
        Product product = productOptional.get();
        if (product.isOrderable()) {
            return null;
        }
        product.setDelete(true);

        return iProductRepository.save(product);
    }

    @Override
    public List<Product> getProductsByShopId(int shopId) {
        return iProductRepository.getAllByTab_shop_id(shopId);
    }

    @Transactional
    @Override
    public Page<BackstageProductResponse> getByPage(Integer shopId, Pageable pageable) {
        return iProductRepository.findAllByShopId(shopId, pageable);
    }

    @Transactional
    @Override
    public boolean putShop(BackstageProductPutRequest productPutRequest) {

        Optional<Product> findById2 = iProductRepository.findById(productPutRequest.getId());
        if (!findById2.isPresent()) {
            throw new NullPointerException();
        }

        Product product = findById2.get();

        product.setProduct(productPutRequest);

        if (productPutRequest.getImgId() != null) {
            Optional<FileData> findById3 = iFileDateRepository.findById(productPutRequest.getImgId());
            if (!findById3.isPresent()) {
                throw new NullPointerException();
            }
            product.setFileData(findById3.get());
        }

        iProductRepository.save(product);

        return true;
    }

    @Transactional
    @Override
    public boolean addProduct(BackstageProductAddRequest productAddRequest) {

        Product product = new Product(productAddRequest);

        Optional<Shop> findById = iShopRepository.findById(productAddRequest.getShopId());
        if (!findById.isPresent()) {
            throw new NullPointerException();
        }

        Optional<Tab> tab = iTabRepository.findById(productAddRequest.getTabId());
        if (!tab.isPresent()) {
            throw new NullPointerException();
        }

        
        product.setTab(tab.get());

        if (productAddRequest.getImgId() != null) {
            Optional<FileData> findById3 = iFileDateRepository.findById(productAddRequest.getImgId());
            if (!findById3.isPresent()) {
                throw new NullPointerException();
            }
            product.setFileData(findById3.get());
        }

        iProductRepository.save(product);
        return true;
    }

}
