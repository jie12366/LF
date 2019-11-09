package com.lingfei.admin.service.impl;

import com.lingfei.admin.entity.Competition;
import com.lingfei.admin.mapper.CompetitionMapper;
import com.lingfei.admin.service.CompetitionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/22 13:13
 */

@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Resource
    CompetitionMapper competitionMapper;

    @Override
    public int saveCompetition(Competition competition) {
        if (competition != null) {
            return competitionMapper.saveCompetition(competition.getAid(),competition.getName(),
                    competition.getStuClass(), competition.getNumber(),competition.getQq(), competition.getPhone(),competition.getItem());
        }
        return 0;
    }

    @Override
    public int updateCompetition(Competition competition) {
        if (competition != null) {
            return competitionMapper.updateCompetition(competition.getName(), competition.getStuClass(),competition.getNumber(),
                    competition.getQq(), competition.getPhone(),competition.getItem(), competition.getId());
        }
        return 0;
    }

    @Override
    public List<Competition> listCompetition(int aid) {
        return competitionMapper.listCompetition(aid);
    }

    @Override
    public Competition getCompetitionByPhone(String phone) {
        return competitionMapper.getCompetitionByPhone(phone);
    }

    @Override
    public Competition getCompetitionById(int id) {
        if (id > 0) {
            return competitionMapper.getCompetitionById(id);
        }
        return null;
    }

    @Override
    public int deleteCompetition(int id) {
        if (id > 0) {
            return competitionMapper.deleteCompetition(id);
        }
        return 0;
    }
}
