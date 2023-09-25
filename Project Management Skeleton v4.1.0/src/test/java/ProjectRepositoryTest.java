import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import abeczkowska.model.Project;
import abeczkowska.repository.ProjectRepository;
import abeczkowska.model.Role;
import abeczkowska.persistence.Persistable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ProjectRepositoryTest {
    @Mock
    private ProjectRepository projectRepository;


    // Inicjalizacja atrap obiektów za pomocą Mockito przed każdym testem
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByName() {
        List<Project> projects = projectRepository.findByName("TestProject");

        assertNotNull(projects);
        assertEquals(0, projects.size());
    }

    @Test
    public void testFindById() {
        Project project = new Project();
        when(projectRepository.findById(anyInt())).thenReturn(project);

        Project result = (Project) projectRepository.findById(1);

        assertNotNull(result);
    }

    @Test
    public void testFindAll() {
        List<Project> projects = (List<Project>) (List<Project>) projectRepository.findAll();

        assertNotNull(projects);
    }

    @Test
    public void testCreateProject() {
        Project project = new Project();
        project.setName("TestProject");
        project.setDescription("Test Description");
        project.setCreatorId(1);

        when(projectRepository.createProject(any(), any())).thenReturn(project);

        Persistable result = projectRepository.createProject(project, Role.ADMINISTRATOR);

        assertNotNull(result);
        assertEquals("TestProject", ((Project) result).getName());
        assertEquals("Test Description", ((Project) result).getDescription());
        assertEquals(1, ((Project) result).getCreatorId());
    }

    @Test
    public void testUpdate() {
        Project project = new Project();
        project.setName("UpdatedProject");
        project.setDescription("Updated Description");
        project.setCreatorId(1);

        projectRepository.update(project, 1);

        verify(projectRepository, times(1)).update(project, 1);
    }

    @Test
    public void testDelete() {
        Project project = new Project();
        project.setId(1);

        doNothing().when(projectRepository).delete(any());

        projectRepository.delete(project);

        verify(projectRepository, times(1)).delete(any());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(projectRepository).deleteById(anyInt());

        projectRepository.deleteById(1);

        verify(projectRepository, times(1)).deleteById(anyInt());
    }
}
