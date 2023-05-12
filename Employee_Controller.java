package org.jsp.emplyee_app.controller;
import java.util.Scanner;
import org.jsp.employee_app.dao.EmployeeDao;
import org.jsp.employee_app.dto.Employee;

public class EmployeeController {
	static Scanner s = new Scanner(System.in);
	static EmployeeDao dao = new EmployeeDao();

	public static void main(String[] args) {
		System.out.println("1. Resgister Employee");
		System.out.println("2. Login");
		System.out.println("3. Update Employee");
		System.out.println("4. Delete Employee");
		int choice = s.nextInt();
		switch (choice) {
		case 1: {
			register();
			break;
		}
		case 2: {
			login();
			break;
		}
		case 3: {
			UpdateEmployee();
			break;
		}
		case 4: {
			DeleteEmployee();
			break;
		}
		}
	}

	private static void register() {
		System.out.println("enter name,desg,salary,phone and password");
		String name = s.next();
		String desg = s.next();
		double salary = s.nextDouble();
		long phone = s.nextLong();
		String password = s.next();

		Employee e = new Employee();

		e.setName(name);
		e.setDesg(desg);
		e.setSalary(salary);
		e.setPhone(phone);
		e.setPassword(password);

		e = dao.saveEmployee(e);
		System.out.println("Employee saved with ID: " + e.getId());

	}

	private static void login() {
		System.out.println("Enter your phone and passwoed");
		long phone = s.nextLong();
		String password = s.next();
		Employee e = dao.verify(phone, password);
		if (e != null) {
			System.out.println("Login Successfully");
			System.out.println("Name " + e.getName());
			System.out.println("Designation " + e.getDesg());
			System.out.println("Password " + e.getPassword());
			System.out.println("Salary " + e.getSalary());
		} else {
			System.err.println("Invalid phone number or pasworrd");
		}

	}

	private static void UpdateEmployee() {
		System.out.println(" enter ur existing ID");
		int id = s.nextInt();
		System.out.println("enter name,desg,salary,phone and password");
		String name = s.next();
		String desg = s.next();
		double salary = s.nextDouble();
		long phone = s.nextLong();
		String password = s.next();

		Employee e = new Employee();
		e.setId(id);
		e.setName(name);
		e.setDesg(desg);
		e.setSalary(salary);
		e.setPhone(phone);
		e.setPassword(password);

		e = dao.UpdateEmployee(e);
		System.out.println("Employee details are Updated with ID: " + e.getId());
	}
  
	private static void DeleteEmployee() {
		System.out.println("Enter your ID to delete the Record");
		int id = s.nextInt();
		boolean e = dao.deleteEmployee(id);
		if (e) {
			System.out.println("Deleted record Successfully");

		} else {
			System.out.println("Invalid ID");
		}
	}
}
