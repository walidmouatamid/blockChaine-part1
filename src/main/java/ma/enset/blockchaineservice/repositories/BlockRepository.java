package ma.enset.blockchaineservice.repositories;

import ma.enset.blockchaineservice.entities.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block,String> {
}
