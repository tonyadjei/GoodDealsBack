package com.antonadjei.eCommerce.services;

import com.antonadjei.eCommerce.enums.Type;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeService {

    public boolean validateProductType(String productType) {
        for (Type type: Type.values()) {
            if (type.toString().equals(productType)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getAllTypes() {
        List<String> types = new ArrayList<>();
        for (Type type: Type.values()) {
            types.add(type.toString());
        }
        return types;
    }
}
