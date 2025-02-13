package com.dhilara.jobapp.review.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhilara.jobapp.company.Company;
import com.dhilara.jobapp.company.CompanyService;
import com.dhilara.jobapp.review.Review;
import com.dhilara.jobapp.review.ReviewRepository;
import com.dhilara.jobapp.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewRepository reviewRepo;
	@Autowired
	private CompanyService companyService;
	
	@Override
	public List<Review> getAllReviews(Long companyId) {
		List<Review> reviews = reviewRepo.findByCompanyId(companyId);
		return reviews;
	}

	@Override
	public boolean addReview(Long companyId, Review review) {
		Company company = companyService.getCompanyById(companyId);
		if (company != null) {
			review.setCompany(company);
			reviewRepo.save(review);
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public Review getReview(Long companyId, Long reviewId) {
		
		List<Review> reviews = reviewRepo.findByCompanyId(companyId);
		return reviews.stream().filter(review->review.getId().equals(reviewId)).findFirst().orElse(null);
	}

	@Override
	public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {

		if(companyService.getCompanyById(companyId) != null) {
			updatedReview.setCompany(companyService.getCompanyById(companyId));
			updatedReview.setId(reviewId);
			reviewRepo.save(updatedReview);
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean deleteReview(Long companyId, Long reviewId) {
		if(companyService.getCompanyById(companyId) != null && reviewRepo.existsById(reviewId)) {
			Review review = reviewRepo.findById(reviewId).orElse(null);
			Company company = companyService.getCompanyById(companyId);
			company.getReviews().remove(review);
			review.setCompany(null);
			companyService.updateCompany(company, companyId);
			reviewRepo.deleteById(reviewId);
			return true;
		}
		return false;
	}

}
