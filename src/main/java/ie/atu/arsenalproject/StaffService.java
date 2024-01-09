package ie.atu.arsenalproject;

import ie.atu.arsenalproject.StaffRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StaffService {
    private final StaffRepo staffRepo;

    public StaffService(StaffRepo staffRepo) {
        this.staffRepo = staffRepo;
    }

    public void saveStaff(Staff staff) {
        staffRepo.save(staff);
    }

    public Staff getStaffByName(String name) {
        return staffRepo.findByName(name);
    }

    public List<Staff> getAllStaff() {
        return staffRepo.findAll();
    }

    public void deleteStaff(Staff staff) {
        staffRepo.delete(staff);
    }
}
