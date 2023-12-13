package org.jsp.user_foodorder.controller;

import java.util.List;
import java.util.Scanner;


import org.jsp.user_foodorder.dao.FoodOrderDao;
import org.jsp.user_foodorder.dao.UserDao;
import org.jsp.user_foodorder.dto.FoodOrder;
import org.jsp.user_foodorder.dto.User;

public class UserOrderController {

	static Scanner s = new Scanner(System.in);
	static FoodOrderDao foodOrderDao = new FoodOrderDao();
	static UserDao userDao = new UserDao();

	public static void main(String[] args) {
		while (true) {
			System.out.println("1.Save User");
			System.out.println("2.Update User");
			System.out.println("3.Find User By Id");
			System.out.println("4.Verify User By Phone and password");
			System.out.println("5.Verify User By Email and password");
			System.out.println("6.Delete User By Id");
			System.out.println("7.Add Food Order");
			System.out.println("8.Update Order");
			System.out.println("9.Find FoodOrder By User Id");
			System.out.println("10.Cancel FoodOrder");
			System.out.println("11.Exit");
			System.out.println("Enter Your Choice");

			switch (s.nextInt()) {
			case 1: {
				saveUser();
				break;
			}
			case 2: {
				updateUser();
				break;
			}
			case 3: {
				findUserById();
				break;
			}
			case 4: {
				verifyByPhone();
				break;
			}
			case 5: {
				verifyByEmail();
				break;
			}
			case 6: {
				deleteUser();
				break;
			}
			case 7: {
				addOrder();
				break;
			}
			case 8: {
				updateOrder();
				break;
			}
			case 9: {
				findFoodOrderByUserId();
				break;
			}
			case 10: {
				cancelOrder();
				break;
			}
			case 11:{
				System.exit(0);
			}
			default:
				break;
			}
		}
	}

	public static void saveUser() {
		System.out.println("Enter the name,phone,email and password to save User");
		User u = new User();
		u.setName(s.next());
		u.setPhone(s.nextLong());
		u.setEmail(s.next());
		u.setPassword(s.next());
		u = userDao.saveUser(u);
		System.out.println("User saved with Id:" + u.getId());
		System.out.println("--------------------------------------------------");
	}

	public static void updateUser() {
		System.out.println("Enter the name,phone,email and password to save User");
		User u = new User();
		u.setName(s.next());
		u.setPhone(s.nextLong());
		u.setEmail(s.next());
		u.setPassword(s.next());
		u = userDao.updateUser(u);
		System.out.println("User saved with Id:" + u.getId());
		System.out.println("--------------------------------------------------");
	}

	public static void findUserById() {
		System.out.println("Enter the User Id find User");
		User u = userDao.findUserById(s.nextInt());
		if (u != null) {
			System.out.println("User Id :" + u.getId());
			System.out.println("User Name :" + u.getName());
			System.out.println("User Phone :" + u.getPhone());
			System.out.println("User Email :" + u.getEmail());
			System.out.println("User order :" + u.getOrders());
			System.out.println("--------------------------------------------------");
		} else {
			System.err.println("Invalid User Id");
		}
	}

	public static void verifyByPhone() {
		System.out.println("Enter phone Number and Password to verify User");
		long phone = s.nextLong();
		String password = s.next();
		User u = userDao.verifyUser(phone, password);
		if (u != null) {
			System.out.println("User Id :" + u.getId());
			System.out.println("User Name :" + u.getName());
			System.out.println("User Phone :" + u.getPhone());
			System.out.println("User Email :" + u.getEmail());
			System.out.println("User order :" + u.getOrders());
			System.out.println("--------------------------------------------------");
		} else {
			System.err.println("Invalid User Phone number or Password");
		}
	}

	public static void verifyByEmail() {
		System.out.println("Enter phone Number and Password to verify User");
		String email = s.next();
		String password = s.next();
		User u = userDao.verifyUser(email, password);
		if (u != null) {
			System.out.println("User Id :" + u.getId());
			System.out.println("User Name :" + u.getName());
			System.out.println("User Phone :" + u.getPhone());
			System.out.println("User Email :" + u.getEmail());
			System.out.println("User order :" + u.getOrders());
			System.out.println("--------------------------------------------------");
		} else {
			System.err.println("Invalid User Email or Password");
		}
	}

	public static void deleteUser() {
		System.out.println("Enter the User id Delete User");
		int id = s.nextInt();
		boolean u = userDao.deleteUser(id);
		if (u) {
			System.out.println("User deleted");
		} else {
			System.out.println("User not found");
		}
	}

	public static void addOrder() {
		System.out.println("Enter the User id to add Order");
		int user_id = s.nextInt();
		System.out.println("Enter the foodItem, cost, address");
		FoodOrder order = new FoodOrder();
		order.setFoodItem(s.next());
		order.setCost(s.nextDouble());
		order.setAddress(s.next());
		order = foodOrderDao.saveFoodOrder(order, user_id);
		if (order!=null) {
			System.out.println("Food Ordered with Id:"+order.getId());
		} else {
			System.out.println("Invalid User Id!! cannot add Order");
		}
	}
	
	public static void updateOrder() {
		System.out.println("Enter the User id to Update Order");
		int user_id = s.nextInt();
		System.out.println("Enter the Id, foodItem, cost, address");
		FoodOrder order = new FoodOrder();
		order.setId(s.nextInt());
		order.setFoodItem(s.next());
		order.setCost(s.nextDouble());
		order.setAddress(s.next());
		order = foodOrderDao.updateOrder(order, user_id);
		if (order!=null) {
			System.out.println("FoodOrder with Id:" + order.getId() + " updated succesfully");
		} else {
			System.out.println("Invalid User Id!! cannot add Order");
		}
	}

	public static void findFoodOrderByUserId() {
		System.out.println("Enter the User Id to find Food Order");
		int id =s.nextInt();
		List<FoodOrder> orders=foodOrderDao.findFoodOrdersByUserId(id);
		if (orders.size() > 0) {
			for (FoodOrder foodOrder : orders) {
				System.out.println("Order Id :"+foodOrder.getId());
				System.out.println("Food Item :"+foodOrder.getFoodItem());
				System.out.println("Order Time :"+foodOrder.getOrdered_Time());
				System.out.println("Cost :"+foodOrder.getCost());
				System.out.println("Address :"+foodOrder.getAddress());
				System.out.println("Delivered time :"+foodOrder.getDelivery_Time());
				System.out.println("------------------------------------");
			} 
		} else {
			System.err.println("No Order found for entered User Id");
		}
	}
	
	public static void cancelOrder() {
		System.out.println("Enter the Ordered Id to Cancel the Order");
		int id=s.nextInt();
		boolean deleted=foodOrderDao.cancelOrder(id);
		if (deleted) {
			System.out.println("Order Cancelled");
		} else {
			System.err.println("Cannot cancel Order");
		}
	}
}
