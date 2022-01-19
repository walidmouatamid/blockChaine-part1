package ma.enset.blockchaineservice.service;

import ma.enset.blockchaineservice.entities.Block;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface BlockService {
    Block save(Block block) throws NoSuchAlgorithmException;
    Block getBlock(String id);
    List<Block> getBlocks();
    Block update(Block block);
    String getHashBlock(Block block) throws NoSuchAlgorithmException;
    String miner(Block block,int difficulty) throws NoSuchAlgorithmException;


}
