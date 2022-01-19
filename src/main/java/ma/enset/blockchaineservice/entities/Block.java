package ma.enset.blockchaineservice.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Block {
    @Id
    private String id;
    private Date date;
    private String hashBlock;
    private String previousHash;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Transaction> transactions;
    private int nonce;


}
