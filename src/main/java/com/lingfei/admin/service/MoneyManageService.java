package com.lingfei.admin.service;

import com.lingfei.admin.entity.MoneyManage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/31 12:03
 */
@Service
public interface MoneyManageService {

    /**
     * 存入数据
     * @param manage
     * @return
     */
    int saveMoneyManage(MoneyManage manage);

    /**
     * 列出所有数据
     * @return
     */
    List<MoneyManage> listManage();

    /**
     * 根据id获取数据
     * @param id
     * @return
     */
    MoneyManage getManageById(int id);

    /**
     * 获取刚刚插入数据的id值
     * @return
     */
    int getMaxId();

    /**
     * 更新余额
     * @param balance
     * @param id
     * @return
     */
    int updateBalance(double balance,int id);

    /**
     * 根据id更新数据
     * @param manage
     * @return
     */
    int updateManage(MoneyManage manage);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteManage(int id);
}