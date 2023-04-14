package ru.javarush.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javarush.todo.entity.Task;
import ru.javarush.todo.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String checkUser(Model model,
                            @RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        if (name == null) {
            return "userPage";
        } else {
            model.addAttribute("name", name);
            return tasks(model, name, page, limit);
        }
    }

    public String tasks(Model model,
                        @RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                        @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

        List<Task> tasks = taskService.getAll((page - 1) * limit, limit, name);
        model.addAttribute("tasks", tasks);
        model.addAttribute("current_page", page);
        int totalPages = (int) Math.ceil(1.0 * taskService.getAllCountByUser(name) / limit);
        if (totalPages > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("page_numbers", pageNumbers);
        }
        return "tasks";
    }

    @PostMapping("/{id}")
    public String edit(Model model,
                       @PathVariable Integer id,
                       @RequestBody TaskInfo info) {
        if (isNull(id) || id <= 0) {
            throw new RuntimeException("Invalid id");
        }

        Task task = taskService.edit(id, info.getDescription(), info.getStatus());
        return tasks(model, info.getUser(), 1, 10);
    }

    @PostMapping("/")
    public String add(Model model,
                      @RequestParam(value = "name", required = false) String name,
                      @RequestBody TaskInfo info) {
        Task task = taskService.create(info.getDescription(), info.getStatus(), info.getUser());

        return tasks(model, info.getUser(), 1, 10);

    }

    @DeleteMapping("/{id}")
    public String delete(Model model, @PathVariable Integer id) {
        if (isNull(id) || id <= 0) {
            throw new RuntimeException("Invalid id");
        }
        taskService.delete(id);
        return tasks(model, (String) model.getAttribute("name"), 1, 10);
    }
}