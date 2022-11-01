package TodoApp3;

import controller.ProjectController;
import controller.TaskController;
import java.util.Date;
import java.util.List;
import model.Project;
import model.Task;

public class App {

    public static void main(String[] args) {

        ProjectController projectController = new ProjectController();
        
//        Project project = new Project();
//        project.setName("Teste 2");
//        project.setDescription("Description");
//        projectController.save(project);
//        ProjectController projectController = new ProjectController();;
//        
//        Project project = new Project();
//        project.setId(1);
//        project.setName("Novo nome do projeto");
//        project.setDescription("Description");
//        
//        
//        projectController.update(project);        
//        
////        ProjectController projectController = new ProjectController();
////
//        List<Project> projects = projectController.getAll();
//        System.out.println("Total de projetos: " + projects.size());
//        
        TaskController taskController = new TaskController();

        Task task = new Task();
        
        
        task.setIdProject(8);
        task.setId(17);
        task.setName("New Name");
        task.setDescription("New Description");
        task.setNotes("New Notes ");
        task.setDeadline(new Date());
        
        taskController.update(task);
//
//        taskController.save(task);
          
//
        
//        
        List<Task> tasks = taskController.getAll(8);
        taskController.update(task);
        System.out.println("Total de tarefas = " + tasks.size());

    }

}
