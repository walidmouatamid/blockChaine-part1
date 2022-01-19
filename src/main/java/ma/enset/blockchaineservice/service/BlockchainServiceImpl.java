package ma.enset.blockchaineservice.service;

import ma.enset.blockchaineservice.entities.Block;
import ma.enset.blockchaineservice.entities.Blockchain;
import ma.enset.blockchaineservice.repositories.BlockchainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Service
@Transactional
public class BlockchainServiceImpl implements BlockchainService {
    private BlockService blockService;
    private BlockchainRepository blockchainRepository;

    public BlockchainServiceImpl(BlockService blockService, BlockchainRepository blockchainRepository) {
        this.blockService = blockService;
        this.blockchainRepository = blockchainRepository;
    }

    @Override
    public Blockchain save(Blockchain blockchain) throws NoSuchAlgorithmException {
        Block GenisisBlock=new Block(UUID.randomUUID().toString(),new Date(),null,null,null,0);
        blockService.save(GenisisBlock);
        List<Block> blocks=new ArrayList<>();
        blocks.add(GenisisBlock);
        blockchain.setBlocks(blocks);
        return blockchainRepository.save(blockchain);
    }

    @Override
    public Blockchain getBlockchain(String id) {
        return blockchainRepository.findById(id).get();
    }

    @Override
    public List<Blockchain> getBlockchainList() {
        return blockchainRepository.findAll();
    }

    @Override
    public Blockchain update(Blockchain blockchain) {
        return blockchainRepository.save(blockchain);
    }

    @Override
    public void addBlockToBlockChaine(Blockchain blockchain,Block block) throws NoSuchAlgorithmException {
        String lastHash=blockchain.getBlocks().get(blockchain.getBlocks().size()-1).getHashBlock();
        block.setPreviousHash(lastHash);
        blockService.save(block);
        blockchain.getBlocks().add(block);
        blockchainRepository.save(blockchain);

    }

    @Override
    public Block getLastBlockinBlockChaine(Blockchain blockchain) {
        return blockchain.getBlocks().get(blockchain.getBlocks().size()-1);
    }
}
