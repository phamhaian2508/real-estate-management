package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        System.out.println(building);
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1,"STAFF");// lấy ra tất cả staff
        List<UserEntity> staffAssignment = building.getUserEntities(); // lấy ra các staff trong building dc xét
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        // đánh dấu những staff có trong building
        for (UserEntity it : staffs) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if(staffAssignment.contains(it)){
                staffResponseDTO.setChecked("checked");
            }
            else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }
    @Override
    public List<BuildingSearchResponse> searchBuildings(BuildingSearchRequest buildingSearchRequest){
        List<BuildingEntity> buildings = buildingRepository.search(buildingSearchRequest);
        List<BuildingSearchResponse> buildingSearchResponses = new ArrayList<>();
        for (BuildingEntity building : buildings) {
            BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
            buildingSearchResponse = buildingConverter.buildingResponseConverter(building);
            buildingSearchResponses.add(buildingSearchResponse);
        }
        return buildingSearchResponses;
    }
    @Override
    public BuildingDTO findBuildingDTO(Long id){
        BuildingEntity building = buildingRepository.findById(id).get();
        BuildingDTO buildingDTO = buildingConverter.buildingDTO(building);
        return buildingDTO;
    }
    @Override
    public BuildingEntity addOrUpdateBuilding(BuildingDTO buildingDTO){
        BuildingEntity building = buildingConverter.buildingEntityConverter(buildingDTO);
        return building;
    }
    @Override
    public void deleteBuilding(Long[] ids){
       rentAreaRepository.deleteByBuildingIdIn(ids);
       buildingRepository.deleteByIdIn(ids);
       assignmentBuildingRepository.deleteByBuildingEntity_IdIn(ids);
    }
}
