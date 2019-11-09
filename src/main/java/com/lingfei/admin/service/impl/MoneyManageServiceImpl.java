package com.lingfei.admin.service.impl;

import com.lingfei.admin.entity.MoneyManage;
import com.lingfei.admin.mapper.MoneyManageMapper;
import com.lingfei.admin.service.MoneyManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/31 12:07
 */
@Service
public class MoneyManageServiceImpl implements MoneyManageService {

    @Resource
    private MoneyManageMapper manageMapper;

    private final Logger logger = LoggerFactory.getLogger(MoneyManageServiceImpl.class);

    @Override
    public int saveMoneyManage(MoneyManage manage) {
        if (manage != null){
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            int res =  manageMapper.saveMoneyManage(manage.getReimburse(),manage.getDeal(),manage.getContent()
                    ,manage.getSpend(),manage.getBalance(),sdf.format(date));

            int id = getMaxId();
            if (listManage().size() == 1){
                return updateBalance(manage.getSpend(),id);
            }else {
                double balance = manageMapper.getMoneyManageById(id - 1).getBalance();
                return updateBalance(balance + manage.getSpend(),id);
            }
        }
        else {
            logger.debug("插入错误");
            return 0;
        }
    }

    @Override
    public List<MoneyManage> listManage() {
        return manageMapper.listMoneyManage();
    }

    @Override
    public MoneyManage getManageById(int id) {
        if (id > 0){
            return manageMapper.getMoneyManageById(id);
        }else {
            logger.debug("传入id有误");
            return null;
        }
    }

    @Override
    public int getMaxId() {
        return manageMapper.getMaxId();
    }

    @Override
    public int updateBalance(double balance, int id) {
        if (id > 0){
            return manageMapper.updateBalance(balance, id);
        }
        logger.debug("更新失败");
        return 0;
    }

    @Override
    public int updateManage(MoneyManage manage) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        if (manage != null){
            double spend = getManageById(manage.getId()).getSpend();
            if (spend == manage.getSpend()){
                return manageMapper.updateMoneyManage(manage.getReimburse(),manage.getDeal(),manage.getContent()
                        ,manage.getSpend(),sdf.format(date),manage.getId());
            }else {
                if (listManage().size() == 1){
                    manageMapper.updateMoneyManage(manage.getReimburse(),manage.getDeal(),manage.getContent()
                            ,manage.getSpend(),sdf.format(date),manage.getId());
                    return updateBalance(manage.getSpend(),manage.getId());
                }else {
                    double balance = getManageById(manage.getId() - 1).getBalance() + manage.getSpend();
                    manageMapper.updateMoneyManage(manage.getReimburse(),manage.getDeal(),manage.getContent()
                            ,manage.getSpend(),sdf.format(date),manage.getId());
                    return updateBalance(balance,manage.getId());
                }
            }
        }
        logger.debug("更新失败");
        return 0;
    }

    @Override
    public int deleteManage(int id) {
        if (id > 0){
            if (listManage().size() == 1){
                return manageMapper.deleteMoneyManage(id);
            }else {
                double balance = manageMapper.getMoneyManageById(id - 1).getBalance();
                double spend = manageMapper.getMoneyManageById(id).getSpend();
                int res2 = updateBalance(balance - spend,id);
                return manageMapper.deleteMoneyManage(id);
            }
        }else {
            logger.debug("删除失败");
            return 0;
        }
    }
}