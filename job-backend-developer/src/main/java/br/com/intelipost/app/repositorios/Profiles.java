package br.com.intelipost.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.intelipost.app.entity.Profile;


@Repository
public interface Profiles extends JpaRepository<Profile, Long>{
}