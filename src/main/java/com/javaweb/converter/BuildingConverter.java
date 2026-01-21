package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.District;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BuildingConverter {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RentAreaRepository rentAreaRepository;

    public BuildingSearchResponse buildingResponseConverter(BuildingEntity item) {
        BuildingSearchResponse building = modelMapper.map(item, BuildingSearchResponse.class);
        Map<String,String> districts = District.type();
        String res = ""+item.getDistrict();
        if(districts.containsKey(res)){
            building.setAddress(item.getStreet()+", "+item.getWard()+", "+districts.get(item.getDistrict()));
        }
        List<String> value = new ArrayList<>();
        for(RentAreaEntity it:item.getRentArea()){
            value.add(""+it.getValue());
        }
        building.setRentArea(String.join(""+",", value)+"");
        return building;
    }
    public BuildingDTO buildingDTO(BuildingEntity item) {
        BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
        Map<String,String> districts = District.type();
        String res = ""+item.getDistrict();
        List<String> value = new ArrayList<>();
        for(RentAreaEntity it:item.getRentArea()){
            value.add(""+it.getValue());
        }
        String type = item.getType().trim();
        String[] types = type.split(",");
        List<String> typeCode = new ArrayList<>();
        for(String it:types){
            typeCode.add(it);
        }
        building.setTypeCode(typeCode);
        building.setRentArea(String.join(""+",", value)+"");
        return building;
    }
    public BuildingEntity buildingEntityConverter(BuildingDTO item) {
        BuildingEntity building = modelMapper.map(item, BuildingEntity.class);
        String rentArea = item.getRentArea().trim();
        String[] rentAreas = rentArea.split(",");
        if(item.getId()!=null){
            List<RentAreaEntity> list = rentAreaRepository.findByBuildingid(item.getId());
            for (RentAreaEntity rentAreaEntity : list) {
                rentAreaRepository.deleteById(rentAreaEntity.getId());
            }
        }
        List<String> typeCode = item.getTypeCode();
        building.setType(String.join(",", typeCode));
        buildingRepository.save(building);
        for (String it:rentAreas){
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setValue(Integer.parseInt(it));
            rentAreaEntity.setBuilding(building);
            rentAreaRepository.save(rentAreaEntity);
        }
        return building;
    }
}
