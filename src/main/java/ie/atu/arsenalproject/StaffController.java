package ie.atu.arsenalproject;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/staff/{name}")
    public ResponseEntity<?> getStaff(@PathVariable String name) {
        Staff staff = staffService.getStaffByName(name);
        if (staff == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(staff);
    }

    @GetMapping("/findAllStaff")
    public ResponseEntity<?> getAllStaff() {
        List<Staff> staffList = staffService.getAllStaff();
        if (staffList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(staffList);
    }

    @PostMapping("/createStaff")
    public ResponseEntity<String> createStaff(@Valid @RequestBody Staff staff) {
        staffService.saveStaff(staff);
        return new ResponseEntity<>("Staff created successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteStaff/{name}")
    public ResponseEntity<String> deleteStaffByName(@PathVariable String name) {
        // Check if the name is not empty
        if (name.isBlank()) {
            return ResponseEntity.badRequest().body("Staff name cannot be empty");
        }

        List<Staff> staffList = staffService.getAllStaff();
        Staff existingStaff = null;
        // Iterate through the list of staff and find a case-insensitive match
        for (Staff staff : staffList) {
            if (staff.getName().equalsIgnoreCase(name)) {
                existingStaff = staff;
                break; // Exit the loop as soon as a match is found
            }
        }
        if (existingStaff == null) {
            return ResponseEntity.notFound().build();
        }
        // Delete the staff from the repository
        staffService.deleteStaff(existingStaff);
        return new ResponseEntity<>("Staff deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/editStaff/{name}")
    public ResponseEntity<String> editStaffByName(@PathVariable String name, @RequestBody Staff updatedStaff) {
        Staff existingStaff = staffService.getStaffByName(name);

        if (existingStaff == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the existing staff with the new details
        existingStaff.setName(updatedStaff.getName());
        existingStaff.setAge(updatedStaff.getAge());
        existingStaff.setRole(updatedStaff.getRole());
        existingStaff.setEmail(updatedStaff.getEmail());
        existingStaff.setNationality(updatedStaff.getNationality());

        staffService.saveStaff(existingStaff);

        return new ResponseEntity<>("Staff updated successfully", HttpStatus.OK);
    }
}
