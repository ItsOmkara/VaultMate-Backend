package in.omkar.moneymanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_contacts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;
    private String email;
    @Column(length = 500)
    private String message;
}
