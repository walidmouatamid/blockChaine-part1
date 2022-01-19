package ma.enset.blockchaineservice;

import ma.enset.blockchaineservice.entities.Block;
import ma.enset.blockchaineservice.entities.Blockchain;
import ma.enset.blockchaineservice.entities.Transaction;
import ma.enset.blockchaineservice.service.BlockService;
import ma.enset.blockchaineservice.service.BlockchainService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class BlockChaineServiceApplication implements CommandLineRunner{
    private BlockService blockService;
private BlockchainService blockchainService;

    public BlockChaineServiceApplication(BlockService blockService, BlockchainService blockchainService) {
        this.blockService = blockService;
        this.blockchainService = blockchainService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BlockChaineServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Blockchain blockchain=blockchainService.save(new Blockchain(UUID.randomUUID().toString(),"blockchaine01",4,0,null));
        List<Transaction> pendingTransactions=new ArrayList<>();
        pendingTransactions.add(new Transaction(UUID.randomUUID().toString(),new Date(),"walid","ccc",1000));
        pendingTransactions.add(new Transaction(UUID.randomUUID().toString(),new Date(),"walid","cc",2000));
        pendingTransactions.add(new Transaction(UUID.randomUUID().toString(),new Date(),"kuala","cccc",5000));
        Block block=new Block(UUID.randomUUID().toString(),new Date(),null,null,pendingTransactions,0);
        blockchainService.addBlockToBlockChaine(blockchain,block);

    }
}
