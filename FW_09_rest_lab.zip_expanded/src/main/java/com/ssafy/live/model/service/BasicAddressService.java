package com.ssafy.live.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.live.model.dao.AddressDao;
import com.ssafy.live.model.dto.Address;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BasicAddressService implements AddressService {

    private final AddressDao dao;

    @Override
    public void registAddress(Address address) {
        dao.insert(address);
    }

    @Override
    public void deleteAddress(int ano) {
        dao.delete(ano);
    }

}
