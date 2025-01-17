package controller;

import com.google.gson.Gson;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/api/posts")
public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;
    private final Gson gson = new Gson();

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = service.all();
        response.getWriter().print(gson.toJson(data));
    }

    @GetMapping("/{id}")
    public void getById(@PathVariable long id, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var post = service.getById(id);
        response.getWriter().print(gson.toJson(post));
    }

    @PostMapping
    public void save(@RequestBody Post post, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var savedPost = service.save(post);
        response.getWriter().print(gson.toJson(savedPost));
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id, HttpServletResponse response) {
        service.removeById(id);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}