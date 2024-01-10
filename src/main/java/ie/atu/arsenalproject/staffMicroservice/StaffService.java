package ie.atu.arsenalproject.staffMicroservice;

import jakarta.transaction.Transactional;
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

    @Transactional
    public Staff updateStaffByName(String name, Staff updatedStaff) {
        Staff staff = getStaffByName(name);
        if (staff != null) {
            staff.setName(updatedStaff.getName());
            staff.setAge(updatedStaff.getAge());
            staff.setEmail(updatedStaff.getEmail());
            staff.setNationality(updatedStaff.getNationality());
            staff.setRole(updatedStaff.getRole());
            saveStaff(staff);
            return staff;
        }
        return null;
    }

}
