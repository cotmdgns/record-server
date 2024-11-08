package com.server.record.service;

import com.server.record.domain.Address;
import com.server.record.repo.AddressDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressDAO dao;

    // 유저 정보 가져오기 ( 모달 )
    public List<Address> allAddress(int userCode){
        return dao.allAddress(userCode);
    }
    // 유저 정보 가져오기
    public Address viewAddress(Address address){
        return dao.viewAddress(address.getUserCode(),address.getAddressUserState());
    }


    // 주소 정보 가져와서 만들기
    public void createAddress(Address address){
        dao.save(address);
    }

    // 주소 정보 변경하기
    public void putAddress(Address address){
        dao.save(address);
    }

    // 주소 정보 삭제하기
    public void deleteAddress(int code){
        dao.deleteById(code);
    }

}
