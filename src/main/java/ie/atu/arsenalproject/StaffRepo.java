package ie.atu.arsenalproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepo extends JpaRepository<Staff, Long> {
    Staff findByName(String name);
}
