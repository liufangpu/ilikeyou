package site.jjilikeyou.www.service;

import site.jjilikeyou.www.util.Page;
import site.jjilikeyou.www.model.BaseModel;

public interface IBaseService<M,QM extends BaseModel>
{
	public void create(M m);
	public void update(M m);
	public void delete(int uuid);
	
	public M getByUuid(int uuid);
	public Page<M> getByConditionPage(QM qm);	
}
