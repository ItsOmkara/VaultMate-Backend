package in.omkar.moneymanager.repository;

import in.omkar.moneymanager.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
