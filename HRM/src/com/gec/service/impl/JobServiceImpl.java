package com.gec.service.impl;

import java.util.List;

import com.gec.bean.Job;
import com.gec.dao.JobDao;
import com.gec.dao.impl.JobDaoImpl;
import com.gec.service.JobService;
import com.gec.util.PageModel;

public class JobServiceImpl implements JobService {
	JobDao jd = new JobDaoImpl();
	@Override
	public PageModel<Job> findByPage(int pageIndex, Job entity) {
		return jd.findByPage(pageIndex, entity);
	}

	@Override
	public Job findById(Integer id) {
		return jd.findById(id);
	}

	@Override
	public boolean save(Job entity) {
		return jd.save(entity);
	}

	@Override
	public boolean update(Job entity) {
		return jd.update(entity);
	}
	//职位删除（可多个）
	@Override
	public boolean del(String[] id) {
		try {
			for (int i = 0; i < id.length; i++) {
				jd.del(id[i]);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public List<Job> findALL() {
		return jd.findAll();
	}
}
