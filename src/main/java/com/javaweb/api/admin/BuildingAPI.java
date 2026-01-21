package com.javaweb.api.admin;

import com.javaweb.entity.AssignBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private AssignmentBuildingService assignmentBuildingService;
    @PostMapping
    public void AddOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
        //Xuong DB update hoặc thêm dữ liệu
        BuildingEntity building = buildingService.addOrUpdateBuilding(buildingDTO);
        System.out.println("ok");
    }
    @DeleteMapping("/{ids}")
    public void deleteBuilding(@PathVariable Long[] ids) {
        //Xuong DB xóa building theo ds id gửi về
        buildingService.deleteBuilding(ids);
        System.out.println("ok");
    }
    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id) {
        ResponseDTO result = buildingService.listStaffs(id);
        return result;
    }
    @PostMapping("/assigment")
    public void updateAssigmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
        AssignBuildingEntity assignBuildingEntity = assignmentBuildingService.toAssigmentBuildingEntity(assignmentBuildingDTO);
        System.out.println("OK");
    }
}
