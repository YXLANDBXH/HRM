package com.gec.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gec.bean.Document;
import com.gec.dao.DocumentDao;
import com.gec.dao.UserDao;
import com.gec.util.DBUtil;
import com.gec.util.PageModel;

public class DocumentDaoImpl extends DBUtil<Document> implements DocumentDao {

	UserDao ud = new UserDaoImpl();
	/*
	 * 文档分页查询和模糊查询
	 */
	@Override
	public PageModel<Document> findByPage(int pageIndex, Document entity) {
		PageModel<Document> pageModel = new PageModel<>();
		List<Object> obj = new ArrayList<>();
		pageModel.setPageIndex(pageIndex);
		String sql ="select count(id) from document_inf where 1=1 ";  
		String sql1 = "select * from document_inf where 1=1 ";   
		if (entity.getId()!=null && !entity.getId().equals("")) { 
			sql += "and id = ?";
			sql1 += "and id = ?";
			obj.add(entity.getId());
		}
		if (entity.getTitle()!=null&&!entity.getTitle().equals("")) { 
			sql += "and title like ?";
			sql1 += "and title like ?";
			obj.add("%"+entity.getTitle()+"%");
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
	public Document findById(Integer id) {
		List<Document> list = query("select * from document_inf where id = ?", id);
		if(list.size() > 0)
			return list.get(0);
		return null;
	}
	/*
	 * 文档添加
	 */
	@Override
	public boolean save(Document entity) {
		return update("insert into document_inf values(null,?,?,?,?,?,null,?)", entity.getTitle(),
			entity.getFileName(),entity.getFiletype(),entity.getFileBytes(),entity.getRemark(),entity.getUser().getId());
	}
	/*
	 * 文档修改
	 */
	@Override
	public boolean update(Document entity) {
		return update("update document_inf set title=?,remark=?,filename=? where id=?", entity.getTitle(),entity.getRemark(),entity.getFileName(),entity.getId());
	}
	/*
	 * 文档删除
	 */
	@Override
	public boolean del(String id) {
		return update("delete from document_inf where id = ?", id);
	}

	@Override
	public List<Document> findAll() {
		return null;
	}

	@Override
	public Document getEntity(ResultSet rs) throws Exception {
		Document d = new Document();
		d.setId(rs.getInt(1));
		d.setTitle(rs.getString(2));
		d.setFileName(rs.getString(3));
		d.setFiletype(rs.getString(4));
		d.setFileBytes(rs.getString(5));
		d.setRemark(rs.getString(6));
		d.setCreateDate(rs.getDate(7));
		d.setUser(ud.findById(rs.getInt(8)));
		return d;
	}
}
