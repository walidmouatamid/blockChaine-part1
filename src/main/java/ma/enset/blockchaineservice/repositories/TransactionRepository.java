package ma.enset.blockchaineservice.repositories;

import ma.enset.blockchaineservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TransactionRepository extends JpaRepository<Transaction,String> {
}
