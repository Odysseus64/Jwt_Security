package plasma.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import plasma.dto.request.GroupsRequest;
import plasma.dto.response.GroupsResponse;
import plasma.models.Groups;
import plasma.repository.GroupsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupsService {

    private final GroupsRepository groupRepository;
    private final ModelMapper modelMapper;

    public GroupsResponse save(GroupsRequest groupRequest) {
        Groups group = modelMapper.map(groupRequest, Groups.class);
        group = groupRepository.save(group);
        return modelMapper.map(group, GroupsResponse.class);
    }

    public GroupsResponse findById(Long id) {
        Groups group = groupRepository.findById(id).orElse(null);
        return modelMapper.map(group, GroupsResponse.class);
    }

    public List<GroupsResponse> findAll() {
        List<Groups> groups = groupRepository.findAll();
        return groups.stream()
                .map(group -> modelMapper.map(group, GroupsResponse.class))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        groupRepository.deleteById(id);
    }

    public GroupsResponse updateGroup(Long id, GroupsRequest groupRequest) {
        Groups group = groupRepository.findById(id).orElse(null);
        modelMapper.map(groupRequest, group);
        group = groupRepository.save(group);
        return modelMapper.map(group, GroupsResponse.class);
    }
}