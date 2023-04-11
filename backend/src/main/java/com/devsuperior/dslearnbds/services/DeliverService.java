package com.devsuperior.dslearnbds.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslearnbds.dtos.DeliverRevisionDTO;
import com.devsuperior.dslearnbds.entities.Deliver;
import com.devsuperior.dslearnbds.repositories.DeliverRepository;

@Service
public class DeliverService {
	
	@Autowired
	private DeliverRepository repository;

	@Transactional
	public void saveDeliver(Long id, DeliverRevisionDTO dto) {
		Deliver entity = repository.getOne(id);
		entity.setStatus(dto.getStatus());
		entity.setFeedback(dto.getFeedback());
		entity.setCorrectCount(dto.getCorrectCount());
		repository.save(entity);
	}
}
