package com.in28Minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {

	private TodoRepository todorepository;
	
	public TodoControllerJpa(TodoRepository todorepository) {
		super();
		this.todorepository = todorepository;
	}
	
	// list-todos (url)
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {

		String username = getLoggedInUsername(model);
		
		List<Todo> todos = todorepository.findByUsername(username);
		model.addAttribute("todos", todos);
		return "listTodos";
	}

//GET, POST
	@RequestMapping(value = "add-todo", method = RequestMethod.GET) // if u get a get request do this
	public String showNewTodoPage(ModelMap model) {
		String username = getLoggedInUsername(model);
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}

//GET, POST
	@RequestMapping(value = "add-todo", method = RequestMethod.POST) // if u get a post request do this
	public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		todorepository.save(todo);
		//todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
		return "redirect:list-todos";
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		// delete with specific id
		todorepository.deleteById(id);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todorepository.findById(id).get();
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST) // if u get a post request do this
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		todorepository.save(todo);
		return "redirect:list-todos";
	}

	private String getLoggedInUsername(ModelMap model) {
		String userDetails = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		userDetails = authentication.getName();
		return userDetails;
	}
}
