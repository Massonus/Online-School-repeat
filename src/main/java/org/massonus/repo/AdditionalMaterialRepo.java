package org.massonus.repo;

import org.massonus.entity.AdditionalMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalMaterialRepo extends JpaRepository<AdditionalMaterial, Long> {


}
