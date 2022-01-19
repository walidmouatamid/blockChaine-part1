package ma.enset.blockchaineservice.web;


import lombok.AllArgsConstructor;
import lombok.Getter;
import ma.enset.blockchaineservice.entities.Block;
import ma.enset.blockchaineservice.entities.Blockchain;
import ma.enset.blockchaineservice.service.BlockService;
import ma.enset.blockchaineservice.service.BlockchainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
public class RestController {
    private BlockService blockService;
    private BlockchainService blockchainService;

    @PostMapping("/block/save")
    Block saveBlock(@RequestBody Block block) throws NoSuchAlgorithmException {
        return blockService.save(block);
    }

    @GetMapping("/block/get/{id}")
    Block getBlock(@PathVariable String id) {
        return blockService.getBlock(id);
    }

    @GetMapping("/block/get")
    List<Block> getAllBlocks() {
        return blockService.getBlocks();
    }


    @PostMapping("/blockChaine/save")
    Blockchain saveBlockChaine(@RequestBody Blockchain blockchain) throws NoSuchAlgorithmException {
        return blockchainService.save(blockchain);
    }

    @GetMapping("/blockChaine/get/{id}")
    Blockchain getBlockChaine(@PathVariable String id) {
        return blockchainService.getBlockchain(id);
    }

    @GetMapping("/blockChaine/get")
    List<Blockchain> getAllBlockChaines() {
        return blockchainService.getBlockchainList();
    }

}
