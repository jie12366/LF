package com.lingfei.admin.service.impl;

import com.lingfei.admin.entity.Activity;
import com.lingfei.admin.mapper.ActivityMapper;
import com.lingfei.admin.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 熊义杰
 * @date 2019-3-17
 */

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    ActivityMapper activityMapper;

    @Override
    public int save(Activity activity) {
        if (activity != null) {
            return activityMapper.save(activity.getTitle(),activity.getZone(),activity.getContent(), activity.getDate(), activity.getPicture());
        }
        return 0;
    }

    @Override
    public List<Activity> getAllResult() {
        return activityMapper.getAllResult();
    }

    @Override
    public Activity getAnnounceById(int id) {
        if (id > 0) {
            return activityMapper.getAnnounceById(id);
        }
        return null;
    }

    @Override
    public int getMaxId() {
        return activityMapper.getMaxId();
    }

    @Override
    public int deleteAnnounce(int id) {
        if (id > 0) {
            return activityMapper.deleteAnnounce(id);
        }
        return 0;
    }
    @Override
    public int updateAnnounce(Activity activity) {
        if (activity != null) {
            return activityMapper.updateAnnounce(activity.getTitle(),activity.getZone(),activity.getContent(),
                    activity.getDate(),activity.getPicture(), activity.getId());
        }
        return 0;
    }
}
