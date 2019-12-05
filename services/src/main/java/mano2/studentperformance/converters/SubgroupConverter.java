package mano2.studentperformance.converters;

import mano2.studentperformance.dto.SubgroupDTO;
import mano2.studentperformance.entity.Subgroup;
import org.springframework.stereotype.Component;

@Component
public class SubgroupConverter {

   public Subgroup toEntity(SubgroupDTO subgroupDTO){
       Subgroup subgroup = new Subgroup();
       subgroup.setId(subgroupDTO.getId());
       subgroup.setNumber(subgroupDTO.getNumber());
       return subgroup;
   }

   public SubgroupDTO toDTO(Subgroup subgroup){
       SubgroupDTO subgroupDTO = new SubgroupDTO();
       subgroupDTO.setId(subgroup.getId());
       subgroupDTO.setNumber(subgroup.getNumber());
       return subgroupDTO;
   }
}
