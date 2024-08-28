package servlet;

import controller.PostController;
import repository.PostRepository;
import service.PostService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MainServlet extends HttpServlet {
    private static final String GET_ALL_POSTS_PATH = "/api/posts";
    private static final String POST_BY_ID_PATH_PATTERN = "/api/posts/\\d+";

    private PostController controller;

    @Override
    public void init() {
        final var repository = new PostRepository();
        final var service = new PostService(repository);
        controller = new PostController(service);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            final var path = req.getRequestURI();
            final var method = req.getMethod();

            switch (method) {
                case "GET":
                    if (GET_ALL_POSTS_PATH.equals(path)) {
                        controller.all(resp);
                    } else if (path.matches(POST_BY_ID_PATH_PATTERN)) {
                        final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
                        controller.getById(id, resp);
                    } else {
                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    }
                    break;
                case "POST":
                    if (GET_ALL_POSTS_PATH.equals(path)) {
                        controller.save(req.getReader(), resp);
                    } else {
                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    }
                    break;
                case "DELETE":
                    if (path.matches(POST_BY_ID_PATH_PATTERN)) {
                        final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
                        controller.removeById(id, resp);
                    } else {
                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    }
                    break;
                default:
                    resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}