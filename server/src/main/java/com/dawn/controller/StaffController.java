package com.dawn.controller;

import com.dawn.common.DawnCodingError;
import com.dawn.common.DawnCodingResult;
import com.dawn.dto.StaffDTO;
import com.dawn.exception.DawnException;
import com.dawn.service.StaffService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping("/staff")
    public ResponseEntity<DawnCodingResult<StaffDTO.GetStaff>> createNewStaff(@RequestPart("staff") String newStaffString,
                                                           @RequestPart("staffImage") MultipartFile staffImage) throws IOException, ParseException {
        newStaffString = new String(newStaffString.getBytes("8859_1"), StandardCharsets.UTF_8);

        StaffDTO.CreateStaff newStaff = new ObjectMapper().readValue(newStaffString, StaffDTO.CreateStaff.class);
        return new ResponseEntity<>(
                new DawnCodingResult<>(null,
                        staffService.createStaff(newStaff, staffImage)),HttpStatus.OK);
    }

    @PutMapping("/staff")
    public ResponseEntity<DawnCodingResult<StaffDTO.GetStaff>> updateNewStaff(@RequestBody StaffDTO.UpdateStaff updateStaff) {
        StaffDTO.GetStaff updatedStaff;
        try {
            updatedStaff = staffService.updateStaff(updateStaff);
        } catch (DawnException e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    new DawnCodingResult<>(new DawnCodingError(e), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(
                new DawnCodingResult<>(null, updatedStaff), HttpStatus.OK);
    }

    @GetMapping("/staffs/store/{storeId}")
    public ResponseEntity<DawnCodingResult<List<StaffDTO.GetStaff>>> getStaffOfStoreByStoreId(@PathVariable("storeId") int storeId) {
        return new ResponseEntity<>(
                new DawnCodingResult<>(null,
                        staffService.getAllStaffOfStore(storeId)),HttpStatus.OK);
    }
    @DeleteMapping("/staff/{staffId}")
    public ResponseEntity deleteStaff(@PathVariable int staffId) {
        staffService.removeStaff(staffId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
