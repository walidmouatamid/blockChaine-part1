package ma.enset.blockchaineservice.repositories;

import ma.enset.blockchaineservice.entities.Blockchain;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BlockchainRepository extends JpaRepository<Blockchain,String> {
}
