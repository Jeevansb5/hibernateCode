package org.jsp.merchant_product.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.merchant_products.dao.Merchantdao;
import org.jsp.merchant_products.dao.Productsdao;
import org.jsp.product_merchant.dto.Merchant;
import org.jsp.product_merchant.dto.Product;

public class Merchant_Product_controller {

	static Scanner s = new Scanner(System.in);
	static Merchantdao merchantDao = new Merchantdao();
	static Productsdao productDao = new Productsdao();

	public static void main(String[] args) {

		System.out.println("1.Save Merchant");
		System.out.println("2.Update Merchant");
		System.out.println("3.Verify Merchant By Phone and password");
		System.out.println("4.Verify Merchant By Email and password");
		System.out.println("5.Delete Merchant By Id");
		System.out.println("6.Add Product");
		System.out.println("7.Update Product");
		System.out.println("8.Find Products By Merchant Id");
		System.out.println("9.Find Products By Brand");
		System.out.println("10.Find Products By Category");
		System.out.println("Enter Your Choice");

		switch (s.nextInt()) {
		case 1: {
			saveMerchant();
			break;
		}
		case 2: {
			updateMerchant();
			break;
		}
		case 3: {
			verifyByPhone();
			break;
		}
		case 4: {
			verifyByEmail();
			break;
		}
		case 5: {
			deleteMerchant();
			break;
		}
		case 6: {
			addProduct();
			break;
		}
		case 7: {
			updateProduct();
			break;
		}
		case 8: {
			findPrdByMerchantId();
			break;
		}
		case 9: {
			findPrdByBrand();
			break;
		}
		case 10: {
			findPrdByCategory();
			break;
		}
		default: {
			System.err.println("Invalid ChoiceF");
		}
		}
	}

	public static void saveMerchant() {
		System.out.println("Enter the name,phone,email,gst and password to save merchant");
		Merchant m = new Merchant();
		m.setName(s.next());
		m.setPhone(s.nextLong());
		m.setEmail(s.next());
		m.setGst_num(s.next());
		m.setPassword(s.next());
		m = merchantDao.saveMerchant(m);
		System.out.println("Merchant saved with Id:" + m.getId());
	}

	public static void updateMerchant() {
		System.out.println("Enter the Merchant id to update");
		int id = s.nextInt();
		System.out.println("Enter the name,phone,email,gst and password to save merchant");
		Merchant m = new Merchant();
		m.setId(id);
		m.setName(s.next());
		m.setPhone(s.nextLong());
		m.setEmail(s.next());
		m.setGst_num(s.next());
		m.setPassword(s.next());
		m = merchantDao.updateMerchant(m);
		System.out.println("Merchant  with Id:" + m.getId() + " updated");
	}

	public static void verifyByPhone() {
		System.out.println("Enter the Phone Number and password to verify Merchant");
		long phone = s.nextLong();
		String password = s.next();
		Merchant m = merchantDao.verifyMerchant(phone, password);
		if (m != null) {
			System.out.println("Verification succesfull");
			System.out.println("Merchant Id:" + m.getId());
			System.out.println("Merchant name:" + m.getName());
			System.out.println("Phone Number:" + m.getPhone());
			System.out.println("Email Id:" + m.getEmail());
			System.out.println("GST Number:" + m.getGst_num());
		} else {
			System.err.println("Invalid Phone Number or Password");
		}
	}

	public static void verifyByEmail() {
		System.out.println("Enter the Email Id and password to verify Merchant");
		String email = s.next();
		String password = s.next();
		Merchant m = merchantDao.verifyMerchant(email, password);
		if (m != null) {
			System.out.println("Verification succesfull");
			System.out.println("Merchant Id:" + m.getId());
			System.out.println("Merchant name:" + m.getName());
			System.out.println("Phone Number:" + m.getPhone());
			System.out.println("Email Id:" + m.getEmail());
			System.out.println("GST Number:" + m.getGst_num());
		} else {
			System.err.println("Invalid Email Id or Password");
		}
	}

	public static void addProduct() {
		System.out.println("Enter the Merchant id to add product");
		int merchant_id = s.nextInt();
		System.out.println("Enter the name,brand,category,description,cost and image url to add product");
		Product p = new Product();
		p.setName(s.next());
		p.setBrand(s.next());
		p.setCategory(s.next());
		p.setDiscription(s.next());
		p.setCost(s.nextDouble());
		p.setImg_url(s.next());
		p = productDao.saveProduct(p, merchant_id);
		if (p != null)
			System.out.println("product saved with Id:" + p.getId());
		else
			System.err.println("Invalid Merchant Id!! cannot add product");
	}

	public static void updateProduct() {
		System.out.println("Enter the Merchant id to update product");
		int merchant_id = s.nextInt();
		System.out.println("Enter the id name,brand,category,description,cost and image url to add product");
		Product p = new Product();
		p.setId(s.nextInt());
		p.setName(s.next());
		p.setBrand(s.next());
		p.setCategory(s.next());
		p.setDiscription(s.next());
		p.setCost(s.nextDouble());
		p.setImg_url(s.next());
		p = productDao.updateProduct(p, merchant_id);
		if (p != null)
			System.out.println("product with Id:" + p.getId() + " updated succesfully");
		else
			System.err.println("Invalid Merchant Id!! cannot add product");
	}

	public static void findPrdByMerchantId() {
		System.out.println("Enter the merchant Id to find Products");
		int merchant_id = s.nextInt();
		List<Product> products = productDao.findProductsByMerchantId(merchant_id);
		if (products.size() > 0) {
			for (Product product : products) {
				System.out.println("Product Id:" + product.getId());
				System.out.println("Product Name:" + product.getName());
				System.out.println("Brand:" + product.getBrand());
				System.out.println("Category:" + product.getCategory());
				System.out.println("Description:" + product.getDiscription());
				System.out.println("Cost:" + product.getCost());
				System.out.println("Image Url:" + product.getImg_url());
				System.out.println("------------------------------------");
			}
		} else {
			System.err.println("No Products found for entered Merchant Id");
		}
	}

	public static void findPrdByBrand() {
		System.out.println("Enter the brand to find Products");
		String brand = s.next();
		List<Product> products = productDao.findProductsByBrand(brand);
		if (products.size() > 0) {
			for (Product product : products) {
				System.out.println("Product Id:" + product.getId());
				System.out.println("Product Name:" + product.getName());
				System.out.println("Brand:" + product.getBrand());
				System.out.println("Category:" + product.getCategory());
				System.out.println("Description:" + product.getDiscription());
				System.out.println("Cost:" + product.getCost());
				System.out.println("Image Url:" + product.getImg_url());
				System.out.println("------------------------------------");
			}
		} else {
			System.err.println("No Products found for entered Brand");
		}
	}

	public static void findPrdByCategory() {
		System.out.println("Enter the category to find Products");
		String category = s.next();
		List<Product> products = productDao.findProductsByCategory(category);
		if (products.size() > 0) {
			for (Product product : products) {
				System.out.println("Product Id:" + product.getId());
				System.out.println("Product Name:" + product.getName());
				System.out.println("Brand:" + product.getBrand());
				System.out.println("Category:" + product.getCategory());
				System.out.println("Description:" + product.getDiscription());
				System.out.println("Cost:" + product.getCost());
				System.out.println("Image Url:" + product.getImg_url());
				System.out.println("------------------------------------");
			}
		} else {
			System.err.println("No Products found for entered Category");
		}
	}

	public static void deleteMerchant() {
		System.out.println("Enter the merchant id to delete the record");
		int id = s.nextInt();
		boolean deleted = merchantDao.deleteMerchant(id);
		if (deleted)
			System.out.println("Merchant deleted");
		else
			System.err.println("cannot delete merchant");
	}
}
