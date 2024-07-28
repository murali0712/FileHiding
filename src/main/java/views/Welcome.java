package views;

import java.sql.SQLException;
import java.util.Scanner;

import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;
import views.UserView;
public class Welcome {
	public void welcomeScreen() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome to the App");
		System.out.println("Press 1 to login");
		System.out.println("Press 2 to signup");
		System.out.println("Press 0 to exit");
		
		int choice=sc.nextInt();
		
		switch(choice) {
		case 1: login();
				break;
		case 2: signup();
					break;
		case 0: System.exit(0);
				
		}
	}
	
	private void login() {
		
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter your email: ");
		String email= sc.nextLine();
		try {
			if(UserDAO.isExist(email)) {
				String genotp= GenerateOTP.getOTP();
				SendOTPService.sendOTP(email,genotp);
				System.out.println("Enter the OTP: ");
				String otp= sc.nextLine();
				if(otp.equals(genotp)) {
					//System.out.println("Welcome");
					new UserView(email).home();
				}
				else {
					System.out.println("Wrong OTP");
				}
			}
			else {
				System.out.println("User not found");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void signup() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter name");
		String name= sc.nextLine();
		System.out.println("Enter email");
		String email= sc.nextLine();
		String genotp= GenerateOTP.getOTP();
		SendOTPService.sendOTP(email,genotp);
		System.out.println("Enter the OTP: ");
		String otp= sc.nextLine();
		if(otp.equals(genotp)) {
			User user= new User(name,email);
			int resp= UserService.saveUser(user);
			switch(resp) {
			case 0: System.out.println("User registered");
				break;
			case 1: System.out.println("User already exists");
			break;
			}
		}
		else {
			System.out.println("Wrong OTP");
		}
		
	}
}
