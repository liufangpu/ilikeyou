package site.jjilikeyou.www.test;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import site.jjilikeyou.www.model.CustomerModel;
import site.jjilikeyou.www.model.CustomerQueryModel;
import site.jjilikeyou.www.service.ICustomerService;
import site.jjilikeyou.www.util.Page;


@Service
public class Client_Service 
{
	@Resource
	private ICustomerService service = null;
	
	
	
	public ICustomerService getServiceT() {
		return service;
	}



	public static void main(String[] args) 
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Client_Service t = (Client_Service)ctx.getBean("client_Service");
		
		/*for (int i = 16; i <17; i++) 
		{
			CustomerModel cm = new CustomerModel();
			cm.setCustomerId("cid"+RandomUtils.nextInt());
			cm.setPwd(UUID.randomUUID().toString().substring(26));
			cm.setRegisterTime(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
			cm.setShowName("CA:"+i);
			cm.setTrueName("李"+i);		
			
			t.getServiceT().create(cm);
		}
		*/
		CustomerQueryModel cqm = new CustomerQueryModel();
		cqm.getPage().setNowPage(3);
		cqm.getPage().setPageShow(10);
		
		Page<CustomerModel> p = t.getServiceT().getByConditionPage(cqm);

		List<CustomerModel> list = p.getResult();
		
		for (CustomerModel customerModel : list) 
		{
			System.out.println(customerModel);
		}
	}
}
