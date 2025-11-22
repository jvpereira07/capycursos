package com.capybara.capycursos.modules.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capybara.capycursos.modules.user.model.Role;
import com.capybara.capycursos.modules.user.repository.RoleRepository;

@Service
public class RoleService {

	private final RoleRepository repo;

	public RoleService(RoleRepository repo) {
		this.repo = repo;
	}

	/**
	 * Salva uma role no banco de dados via Repository
	 * "é o C do CRUD, é o C do FUCK"
	 */
	public Role saveRole(Role role) {
		return repo.saveAndFlush(role);
	}

	/**
	 * Retorna uma role pelo código
	 * "é o R do CRUD, é o F do FUCK"
	 */
	public Role getRoleByCode(String code) {
		return repo.findById(code).orElseThrow(
				() -> new RuntimeException("Código de role não encontrado"));
	}

	/**
	 * Retorna uma lista com todas as roles
	 */
	public List<Role> getRoles() {
		return repo.findAll();
	}

	/**
	 * Atualiza uma role existente
	 * "é o U do CRUD, é o U do FUCK"
	 */
	public Role updateRole(String code, Role role) {
		Role roleEntity = getRoleByCode(code);
		Role updated = Role.builder()
				.code(roleEntity.getCode())
				.name(role.getName() != null ? role.getName() : roleEntity.getName())
				.level(role.getLevel() != 0 ? role.getLevel() : roleEntity.getLevel())
				
				.build();
		return repo.saveAndFlush(updated);
	}

	/**
	 * Deleta permanentemente uma role.
	 * "É o D do CRUD e o K do FUCK"
	 */
	public void deleteRole(String code) {
		repo.deleteById(code);
	}
}
