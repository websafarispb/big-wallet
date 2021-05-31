package ru.stepev.bigwallet.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.stepev.bigwallet.model.Group;
import ru.stepev.bigwallet.service.GroupService;

@Controller
@RequestMapping("/groups")
public class GroupController {

	private GroupService groupService;

	public GroupController(GroupService groupService) {
		this.groupService = groupService;
	}

	@GetMapping
	public String showAllGroups(Model model) {
		List<Group> groups = groupService.getAll();
		model.addAttribute("groups", groups);
		return "groups-page";
	}
	
	@GetMapping("{id}")
	public String getGroup(@PathVariable int id, Model model) {
		Group group = groupService.getById(id).orElseThrow();
		model.addAttribute("group", group);
		return "show/show-group";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteGroup(@PathVariable int id, Model model) {
		Group group = groupService.getById(id).orElseThrow();
		groupService.delete(group);
		return "redirect:/groups";
	}
	
	@GetMapping("/add")
	public String addGroup (Model model) {
		Group group = new Group();
		model.addAttribute("group", group);
		return "add/add-group";
	}
	
	@PostMapping("/create")
	public String createGroup(@ModelAttribute Group group, Model model) {
		groupService.add(group);
		return "redirect:/groups";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model) {
		Group group = groupService.getById(id).orElseThrow();
		model.addAttribute("group", group);
		return "update/update-group";
	}
	
	@PostMapping("/save")
	public String saveGroup(@ModelAttribute Group group, Model model) {
		groupService.update(group);
		return "redirect:/groups";
	}
}
