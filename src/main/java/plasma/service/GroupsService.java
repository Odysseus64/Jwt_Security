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
        Groups group = groupRepository.save(modelMapper.map(groupRequest, Groups.class));
        return modelMapper.map(group, GroupsResponse.class);
    }

    public GroupsResponse findById(Long id) {
        return groupRepository.findById(id)
                .map(group -> modelMapper.map(group, GroupsResponse.class))
                .orElse(null);
    }

    public List<GroupsResponse> findAll() {
        return groupRepository.findAll().stream()
                .map(group -> modelMapper.map(group, GroupsResponse.class))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        groupRepository.deleteById(id);
    }

    public GroupsResponse update(Long id, GroupsRequest groupRequest) {
        return groupRepository.findById(id).map(group -> {
                    modelMapper.map(groupRequest, group);
                    return modelMapper.map(groupRepository.save(group), GroupsResponse.class);
                })
                .orElse(null);
    }
}