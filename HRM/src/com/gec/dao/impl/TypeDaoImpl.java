package com.gec.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gec.bean.Types;
import com.gec.dao.TypesDao;
import com.gec.dao.UserDao;
import com.gec.util.DBUtil;
import com.gec.util.PageModel;

public class TypeDaoImpl extends DBUtil<Types> implements TypesDao {

	UserDao ud = new UserDaoImpl();
	/*
	 * 公告类型分页查询和模糊查询
	 */
	@Override
	public PageModel<Types> findByPage(int pageIndex, Types entity) {
		PageModel<Types> pageModel = new PageModel<>();
		List<Object> obj = new ArrayList<>();
		pageModel.setPageIndex(pageIndex);
		String sql ="select count(id) from type_inf where 1=1 ";
		String sql1 = "select * from type_inf where 1=1 ";     //模糊查询
//		if(entity.getId()!=null&&!entity.getId().equals("")) {
//			sql += "and id = ?";
//			sql1 += "and id = ?";
//			obj.add(entity.getId());
//		}
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

	@Override
	public Types findById(Integer id) {
		List<Types> list = query("select * from type_inf where id = ?",id);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}
	/*
	 * 公告类型添加
	 */
	@Override
	public boolean save(Types entity) {
		return update("insert into type_inf values(null,?,null,0,1,null)", entity.getName());
	}
	/*
	 * 公告类型更新
	 */
	@Override
	public boolean update(Types entity) {
		return update("update type_inf set name = ? where id = ?", entity.getName(),entity.getId());
	}
	/*
	 * 公告类型删除
	 */
	@Override
	public boolean del(String id) {
		return update("delete from type_inf where id = ?", id);
	}

	@Override
	public List<Types> findAll() {
		return query("select * from type_inf");
	}

	@Override
	public Types getEntity(ResultSet rs) throws Exception {
		Types t = new Types();
		t.setId(rs.getInt(1));
		t.setName(rs.getString(2));
		t.setCreateDate(rs.getDate(3));
		t.setUser(ud.findById(rs.getInt(4)));
//		t.setModifyDate(rs.getDate(5));
		return t;
	}
}
