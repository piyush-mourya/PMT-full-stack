package io.piyush.pmt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.piyush.pmt.domain.Project;
import io.piyush.pmt.exceptions.ProjectIdException;
import io.piyush.pmt.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("PROJECT ID '" + project.getProjectIdentifier() + "' already exists");
		}
	}

	public Project getByProjectId(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if (project == null) {
			throw new ProjectIdException(projectId + " does not exist");
		}
		return project;
	}

	public Iterable<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	public void deleteByProjectId(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if (project == null) {
			throw new ProjectIdException("Cannot delete unexisting project with PROJECT ID '" + projectId + "'");
		}
		projectRepository.delete(project);
	}
}
