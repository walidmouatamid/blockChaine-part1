package ma.enset.blockchaineservice.service;


import ma.enset.blockchaineservice.entities.Block;
import ma.enset.blockchaineservice.entities.Blockchain;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface BlockchainService {
    Blockchain save(Blockchain blockchain) throws NoSuchAlgorithmException;
    Blockchain getBlockchain(String id);
    List<Blockchain> getBlockchainList();
    Blockchain update(Blockchain blockchain);
    void addBlockToBlockChaine(Blockchain blockchain,Block block) throws NoSuchAlgorithmException;
    Block getLastBlockinBlockChaine(Blockchain blockchain);
}
