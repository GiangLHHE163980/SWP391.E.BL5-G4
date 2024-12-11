package controller;

import dao.SliderDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Slider;
import ultil.Upload;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50)
@WebServlet(name = "SliderController", urlPatterns = {"/SliderController"})
public class SliderController extends HttpServlet {

    private SliderDAO sliderDAO;

    public void init() {
        sliderDAO = new SliderDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            listSliders(request, response);
        } else if (action.equals("edit")) {
            showEditForm(request, response);
        } else if (action.equals("delete")) {
            deleteSlider(request, response);
        } else {
            listSliders(request, response);
        }
    }

    private void listSliders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Slider> sliders = sliderDAO.getAllSlidersAdmin();
            request.setAttribute("sliders", sliders);
            RequestDispatcher dispatcher = request.getRequestDispatcher("slider-list.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Slider slider = sliderDAO.getSliderById(id);
            request.setAttribute("slider", slider);
            RequestDispatcher dispatcher = request.getRequestDispatcher("slider-form.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void deleteSlider(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            sliderDAO.deleteSlider(id);
            response.sendRedirect("SliderController");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errors = validateSlider(request);

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            RequestDispatcher dispatcher = request.getRequestDispatcher("slider-form.jsp");
            dispatcher.forward(request, response);
            return;  
        }

        String id = request.getParameter("sliderID");
        Part imageUrl = request.getPart("imageUrl");
        String title = request.getParameter("title");
        String backLink = request.getParameter("backLink");
        String description = request.getParameter("description");
        boolean publish = Boolean.parseBoolean(request.getParameter("publish"));

        String pathBanner = "./uploads/banner/";
        Upload upload = new Upload();
        String uploadPath = getServletContext().getRealPath(pathBanner);

        String nameImgBanner = upload.uploadFile(imageUrl, uploadPath);
        if((id == null || id.isEmpty()) && nameImgBanner == null) {
            request.setAttribute("errors", "Please choose image");
            RequestDispatcher dispatcher = request.getRequestDispatcher("slider-form.jsp");
            dispatcher.forward(request, response);
            return;
        }
        String fileNameSaveDb = request.getParameter("old-media");
        if (nameImgBanner != null && !nameImgBanner.isEmpty()) {
            fileNameSaveDb = pathBanner + nameImgBanner;
        }

        Slider slider = createSliderFromRequest(request);
        slider.setImageUrl(fileNameSaveDb); 

        try {
            if (id == null || id.isEmpty()) {
                sliderDAO.addSlider(slider);
            } else {
                slider.setSliderID(Integer.parseInt(id));
                sliderDAO.updateSlider(slider);
            }
            response.sendRedirect("SliderController");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private List<String> validateSlider(HttpServletRequest request) {
        List<String> errors = new ArrayList<>();

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        if (title == null || title.trim().isEmpty()) {
            errors.add("Title is required.");
        }

        if (description == null || description.trim().isEmpty()) {
            errors.add("Description is required.");
        }

        return errors;
    }

    private Slider createSliderFromRequest(HttpServletRequest request) {
        Slider slider = new Slider();
        slider.setTitle(request.getParameter("title"));
        slider.setBackLink(request.getParameter("backLink"));
        slider.setDescription(request.getParameter("description"));
        slider.setPublish(Boolean.parseBoolean(request.getParameter("publish")));
        return slider;
    }
}
