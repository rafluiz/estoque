package com.br.estoque.centroCusto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CentroCustoRepository extends JpaRepository<CentroCusto, Long> {

    Page<CentroCusto> findAllByAtivoTrue(Pageable paginacao);

}
