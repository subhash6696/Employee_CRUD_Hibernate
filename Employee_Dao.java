package org.jsp.employee_app.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.jsp.employee_app.dto.Employee;

public class EmployeeDao {
	Session s=new Configuration().configure().buildSessionFactory().openSession();
	
	public Employee saveEmployee(Employee emp)
	{
		s.save(emp);
		Transaction t=s.beginTransaction();
		t.commit();
		return emp;
	}
	public Employee UpdateEmployee(Employee emp)
	{
		s.update(emp);
		Transaction t=s.beginTransaction();
		t.commit();
		return emp;
	}
	
	public Employee findEmployee(int id)
	{
		
		return s.get(Employee.class, id);
	}
	public boolean deleteEmployee(int id)
	{
		Employee e=findEmployee(id);
		if(e!=null)
		{
			s.delete(e);
			Transaction t=s.beginTransaction();
			t.commit();
			return true;
		}
		else 
			return false;
	}
	 public Employee verify (long phone, String password)
	{
		String hql="select e from Employee e where e.phone=?1 and e.password=?2";
		Query<Employee> q=s.createQuery(hql);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		
		List<Employee>emps=q.getResultList();
		if(emps.size()>0)
			return emps.get(0);
		return null;		
	}
}
