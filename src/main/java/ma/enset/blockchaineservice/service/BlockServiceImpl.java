package ma.enset.blockchaineservice.service;

import ma.enset.blockchaineservice.entities.Block;
import ma.enset.blockchaineservice.repositories.BlockRepository;
import ma.enset.blockchaineservice.repositories.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@Transactional
public class BlockServiceImpl implements BlockService {
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private BlockRepository blockRepository;
    private TransactionRepository transactionRepository;
    public BlockServiceImpl(BlockRepository blockRepository,TransactionRepository transactionRepository) {
        this.blockRepository = blockRepository;
        this.transactionRepository=transactionRepository;
    }

    @Override
    public Block save(Block block) throws NoSuchAlgorithmException {
        if(block.getTransactions()!=null)
        block.getTransactions().forEach(transaction -> {
            transactionRepository.save(transaction);
        });
        block.setHashBlock(getHashBlock(block));
        return blockRepository.save(block);
    }

    @Override
    public Block getBlock(String id) {
        return blockRepository.findById(id).get();
    }

    @Override
    public List<Block> getBlocks() {
        return blockRepository.findAll();
    }

    @Override
    public Block update(Block block) {
        return blockRepository.save(block);
    }

    @Override
    public String getHashBlock(Block block) throws NoSuchAlgorithmException {
        MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
        byte[] hash=messageDigest.digest(block.toString().getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hash);
    }

    @Override
    public String miner(Block block,int difficulty) throws NoSuchAlgorithmException {
        String prefix="";
        for(int i=0;i<difficulty;i++){
            prefix+="0";
        }
        String hash=this.getHashBlock(block);
        while(!hash.startsWith(prefix)){
            block.setNonce(block.getNonce()+1);
            hash=this.getHashBlock(block);
        }
        block.setHashBlock(hash);
        blockRepository.save(block);
        return hash;
    }
}
