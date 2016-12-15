package site.jjilikeyou.www.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import site.jjilikeyou.www.dao.ICustomerDAO;
import site.jjilikeyou.www.service.ICustomerService;
import site.jjilikeyou.www.model.CustomerModel;
import site.jjilikeyou.www.model.CustomerQueryModel;


@Service
@Transactional
public class CustomerServiceImpl extends BaseServiceImpl<CustomerModel,CustomerQueryModel> implements ICustomerService
{
	private ICustomerDAO customerDao;
	
	@Autowired
	public void setCustomerDao(ICustomerDAO customerDao) 
	{
		this.customerDao = customerDao;
		super.setDao(customerDao);
	}
	
}
