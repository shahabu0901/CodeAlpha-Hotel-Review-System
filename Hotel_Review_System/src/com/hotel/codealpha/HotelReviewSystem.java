package com.hotel.codealpha;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Review {
	private int rating;
	private String comment;

	public Review(int rating, String comment) {
		this.rating = rating;
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public String getComment() {
		return comment;
	}
}

class Hotel {
	private String name;
	private List<Review> reviews;

	public Hotel(String name) {
		this.name = name;
		this.reviews = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void addReview(Review review) {
		reviews.add(review);
	}

	public double averageRating() {
		if (reviews.isEmpty()) {
			return 0;
		}
		double totalRating = 0;
		for (Review review : reviews) {
			totalRating += review.getRating();
		}
		return totalRating / reviews.size();
	}

	public List<Review> getReviews() {
		return reviews;
	}
}

class HotelReviewSystems {
	private List<Hotel> hotels;

	public HotelReviewSystems() {
		hotels = new ArrayList<>();
	}

	public void addHotel(String name) {
		hotels.add(new Hotel(name));
	}

	public void addReview(String hotelName, int rating, String comment) {
		Hotel hotel = findHotel(hotelName);
		if (hotel != null) {
			hotel.addReview(new Review(rating, comment));
			System.out.println("Review Added Successfully.");
		} else {
			System.out.println("Hotel Not Found.");
		}
	}

	private Hotel findHotel(String hotelName) {
		for (Hotel hotel : hotels) {
			if (hotel.getName().equals(hotelName)) {
				return hotel;
			}
		}
		return null;
	}

	public void listHotels() {
		System.out.println("Hotels:");
		for (Hotel hotel : hotels) {
			System.out.println("Hotel Name : " + hotel.getName());
		}
	}

	public void listReviews(String hotelName) {
		Hotel hotel = findHotel(hotelName);
		if (hotel != null) {
			System.out.println("Reviews for " + hotelName + ":");
			for (Review review : hotel.getReviews()) {
				System.out.println("Rating: " + review.getRating() + ", Comment: " + review.getComment());
			}
		} else {
			System.out.println("Hotel not found.");
		}
	}

	public void sortHotelsByRating() {
		hotels.sort((hotel1, hotel2) -> Double.compare(hotel2.averageRating(), hotel1.averageRating()));
		System.out.println("Hotels Sorted by Rating:");
		for (Hotel hotel : hotels) {
			System.out.println(hotel.getName() + ": " + hotel.averageRating());
		}
	}

	public void filterHotelsByRating(double minRating) {
		System.out.println("Hotels with Rating >= " + minRating + ":");
		for (Hotel hotel : hotels) {
			if (hotel.averageRating() >= minRating) {
				System.out.println(hotel.getName() + ": " + hotel.averageRating());
			}
		}
	}
}

public class HotelReviewSystem {
	public static void main(String[] args) {
		HotelReviewSystems reviewSystem = new HotelReviewSystems();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Hotel Review System!");

		boolean running = true;
		while (running) {
			System.out.println("\nChoose an option:");
			System.out.println("1. Add a hotel");
			System.out.println("2. Add a review");
			System.out.println("3. List hotels");
			System.out.println("4. List reviews for a hotel");
			System.out.println("5. Sort hotels by rating");
			System.out.println("6. Filter hotels by rating");
			System.out.println("7. Exit");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.println("Enter Hotel name: ");
				String hotelName = scanner.nextLine();
				reviewSystem.addHotel(hotelName);
				break;
			case 2:
				System.out.println("Enter Hotel name: ");
				hotelName = scanner.nextLine();
				reviewSystem.addReview(hotelName, choice, hotelName);
				break;
			case 3:
				reviewSystem.listHotels();
				break;
			case 4:
				System.out.println("Enter Hotel name: ");
				hotelName = scanner.nextLine();
				reviewSystem.listReviews(hotelName);
				break;
			case 5:
				reviewSystem.sortHotelsByRating();
				break;
			case 6:
				System.out.println("Enter Minimum Rating: ");
				double minRating = scanner.nextDouble();
				reviewSystem.filterHotelsByRating(minRating);
				break;
			case 7:
				running = false;
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}

		scanner.close();
	}
}
