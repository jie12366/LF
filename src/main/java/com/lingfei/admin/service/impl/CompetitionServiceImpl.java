package com.lingfei.admin.service.impl;

import com.lingfei.admin.entity.Competition;
import com.lingfei.admin.mapper.CompetitionMapper;
import com.lingfei.admin.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/22 13:13
 */

@Service
@CacheConfig
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    CompetitionMapper competitionMapper;

    @Override
    @Cacheable("competition")
    public int saveCompetition(Competition competition){
        if(competition != null){
            return competitionMapper.saveCompetition(competition.getName(),competition.getStuClass(),competition.getQq(),competition.getPhone());
        }
        return 0;
    }

    @Override
    @CachePut("competition")
    public int updateCompetition(Competition competition){
        if(competition != null){
            return competitionMapper.updateCompetition(competition.getName(),competition.getStuClass(),competition.getQq(),competition.getPhone(),competition.getId());
        }
        return 0;
    }

    @Override
    public List<Competition> listCompetition(){
        return competitionMapper.listCompetition();
    }

    @Override
    @CachePut("competition")
    public Competition getCompetitionById(int id){
        if (id  > 0){
            return competitionMapper.getCompetitionById(id);
        }
        return null;
    }

    @Override
    @CacheEvict("competition")
    public int deleteCompetition(int id){
        if (id > 0){
            return competitionMapper.deleteCompetition(id);
        }
        return 0;
    }

    @Override
    @CacheEvict("competition")
    public int batchDeleteCompetition(List<Competition> competitions){
        if (competitions != null){
            return competitionMapper.batchDeleteCompetition(competitions);
        }
        return 0;
    }
}
