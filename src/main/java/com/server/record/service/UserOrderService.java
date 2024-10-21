package com.server.record.service;

import com.server.record.domain.UserOrder;
import com.server.record.repo.UserOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderService {

    @Autowired
    private UserOrderDAO dao;

    public List<UserOrder> userOrderList(String id) {
        return dao.userOrderList(id);
    }
}
