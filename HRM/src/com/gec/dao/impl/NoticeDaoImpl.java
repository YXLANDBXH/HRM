package com.gec.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gec.bean.Notice;
import com.gec.dao.NoticeDao;
import com.gec.dao.TypesDao;
import com.gec.dao.UserDao;
import com.gec.util.DBUtil;
import com.gec.util.PageModel;

public class NoticeDaoImpl extends DBUtil<Notice> implements NoticeDao {
	TypesDao td = new TypeDaoImpl();
	UserDao ud = new UserDaoImpl();
	@Override
	public boolean del(String id) {
		return update("delete from notice_inf where id=?",id);
	}
	/*
	 * 公告分页查询和模糊查询
	 */
	@Override
	public PageModel<Notice> findByPage(int pageIndex, Notice entity) {
		PageModel<Notice> pageModel = new PageModel<>();
		List<Object> obj = new ArrayList<>();
		pageModel.setPageIndex(pageIndex);
		String sql ="select count(id) from notice_inf where 1=1 ";
		String sql1 = "select * from notice_inf where 1=1 ";     //模糊查询
		if (entity.getId()!=null&&!entity.getId().equals("")) { 
			sql += "and id = ?";
			sql1 += "and id = ?";
			obj.add(entity.getId());
		}
		if (entity.getName()!=null&&!entity.getName().equals("")) { 
			sql += "and name like ?";
			sql1 += "and name like ?";
			obj.add("%"+entity.getName()+"%");
		}
		pageModel.setTotalRecordSum(getCount(sql, obj.toArray()));
		if (pageModel.getTotalPageSum() < pageIndex) {
			pageIndex = 1;
		}
		sql1 += "limit ?,?";
		obj.add((pageIndex-1)*PageModel.getPagesize());
		obj.add(PageModel.getPagesize());
		pageModel.setList(query(sql1, obj.toArray()));
		return pageModel;
	}
	/*
	 * 公告添加
	 */
	@Override
	public boolean save(Notice entity) {
		return update("insert into notice_inf values(null,?,?,?,?,?,?)", entity.getName(),entity.getCreateDate(),entity.getType_id(),entity.getContent(),entity.getUser_id(),entity.getModifyDate());
	}
	/*
	 * 公告跟新
	 */
	@Override
	public boolean update(Notice entity) {
		return update("update notice_inf set name=?, type_id=?, content=? where id=?", entity.getName(),entity.getType_id(),entity.getContent(),entity.getId());
	}

	/*
	 * 查找id
	 */
	@Override
	public Notice findById(Integer id) {
		List<Notice> list = query("select * from notice_inf where id = ?", id);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}


	@Override
	public List<Notice> findAll() {
		return null;
	}
	

	@Override
	public Notice getEntity(ResultSet rs) throws Exception {
		Notice n = new Notice();
		n.setId(rs.getInt(1));
		n.setName(rs.getString(2));
		n.setCreateDate(rs.getDate(3));
		n.setType(td.findById(rs.getInt(4)));
		n.setContent(rs.getString(5));
		n.setUser(ud.findById(rs.getInt(6)));
		n.setModifyDate(rs.getDate(7));
		return n;
	}


}
