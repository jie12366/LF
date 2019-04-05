package com.lingfei.admin.service;

import com.lingfei.admin.entity.Competition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/22 13:07
 */

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)

public interface CompetitionService {

    /**
     * 插入数据
     * @param competition Competition
     * @return 是否插入成功
     */
    int saveCompetition(Competition competition);

    /**
     * 根据id更新数据
     * @param competition Competition
     * @return 是否更新成功
     */
    int updateCompetition(Competition competition);

    /**
     * 查询所有的数据
     * @return List<Competition>
     */
    List<Competition> listCompetition();

    /**
     * 根据id查询数据
     * @param id int
     * @return Competition
     */
    Competition getCompetitionById(int id);

    /**
     * 根据id删除数据
     * @param id int
     * @return 是否删除成功
     */
    int deleteCompetition(int id);

    /**
     * 批量删除数据
     * @param competitions List<Competition>
     * @return 是否删除成功
     */
    int batchDeleteCompetition(List<Competition> competitions);
}
