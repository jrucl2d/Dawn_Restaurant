package com.dawn.service;

import com.dawn.common.CloudConstatns;
import com.dawn.dto.StaffDTO;
import com.dawn.model.Staff;
import com.dawn.model.Store;
import com.dawn.repository.staff.StaffRepository;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffDTO.GetStaff createStaff(StaffDTO.CreateStaff newStaff, MultipartFile staffImage) throws ParseException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse(newStaff.getBirthDate());

        Staff staff = staffRepository.save(new Staff(
                new Store(newStaff.getStoreId()), newStaff.getName(), newStaff.getPosition(),
                          birthDate, newStaff.isSex(), newStaff.getWagePerHour()));
        String staffImageName = "staff/" + staff.getStaffId() + "-image.jpg";
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(CloudConstatns.KEYFILE_PATH))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder("sogong", staffImageName).build(), staffImage.getBytes());
        System.out.println("generated blob = " + blobInfo.getName());
        staff.setProfileImageName(staffImageName);
        return Staff.toGetStaff(staffRepository.save(staff));
    }

    public List<StaffDTO.GetStaff> getAllStaffOfStore(int storeId) {
        List<Staff> staffs = staffRepository.findByStoreByStoreId(storeId);
        List<StaffDTO.GetStaff> staffDTOs = new ArrayList<>();
        staffs.parallelStream().map(Staff::toGetStaff).forEach(staffDTOs::add);
        return staffDTOs;
    }
}
