package com.ssafy.live.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.ssafy.live.model.dao.AddressDao;
import com.ssafy.live.model.dto.Address;
import com.ssafy.live.util.DBUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BasicAddressService implements AddressService {

    private final AddressDao dao;
    private final DBUtil util;

    @Override
    public void registAddress(Address address) throws SQLException {
        Connection con = util.getConnection();
        try {
            con.setAutoCommit(false);
            dao.insert(con, address);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            util.close(con);
        }
    }

    @Override
    public void deleteAddress(int ano) throws SQLException {
        Connection con = util.getConnection();
        try {
            con.setAutoCommit(false);
            dao.delete(con, ano);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            util.close(con);
        }
    }

}
