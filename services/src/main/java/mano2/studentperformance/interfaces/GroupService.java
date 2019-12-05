package mano2.studentperformance.interfaces;

import mano2.studentperformance.dto.GroupDTO;
import mano2.studentperformance.exceptions.EntityNotFoundException;

public interface GroupService extends Service<GroupDTO> {

    GroupDTO getGroupByNumber(long groupNumber) throws EntityNotFoundException;
}
