package ass.question5;
import java.util.ArrayList;
import java.util.Scanner;
public class JdbcMain {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println();
			System.out.println("Select the options");
			System.out.println("1->Add a employee");
			System.out.println("2->Update employee Details");
			System.out.println("3->Delete an employee");
			System.out.println("4->Display all the employees");
			System.out.println("5->Exit");
			int options=sc.nextInt();
			if(options==1) {
				System.out.println("Enter the Employee name");
				String ename=sc.next();
				System.out.println("Enter the Department name");
				String deptName=sc.next();
				System.out.println("Enter the employee id");
				int eid=sc.nextInt();
				System.out.println("Enter the Employee salary");
				double empSalary=sc.nextDouble();
				JdbcDao.insertEmp(eid, ename, deptName, empSalary);
			}
			else if(options==2) {
				System.out.println("Enter the Employee name");
				String ename=sc.next();
				System.out.println("Enter the Department name");
				String deptName=sc.next();
				System.out.println("Enter the employee id");
				int eid=sc.nextInt();
				System.out.println("Enter the Employee salary");
				double empSalary=sc.nextDouble();
				JdbcDao.updateEmpDetails(eid, ename, deptName, empSalary);
			}
			else if(options==3) {
				System.out.println("Enter the employee id for which you want to delete");
				int id=sc.nextInt();
				JdbcDao.deleteEmp(id);
			}
			else if(options==4) {
				ArrayList<Emp> display=JdbcDao.display();
				for(Emp e:display) {
					System.out.println(e);
				}
			}
			else if(options==5) {
				System.out.println("Exit");
				break;
			}
			else {
				System.out.println("Invalid option");
			}
		}
//		JdbcDao.createEmpTable();

	}


}
