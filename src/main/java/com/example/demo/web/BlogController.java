package com.example.demo.web;


import com.example.demo.model.dto.BlogAddDto;
import com.example.demo.model.dto.CalendarAddDto;
import com.example.demo.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blog")
    public String getBlog(Model model){
        model.addAttribute("blogs", blogService.getAll());
        return "blog";
    }

    @GetMapping("/blog/{id}")
    private String blogDetails(@PathVariable Long id, Model model){
        model.addAttribute("blogDetails", blogService.findById(id));
        return "blog-page";
    }
    @GetMapping("/blog/add")
    private String addBlog(){
        return "blog-add-page";
    }

    @PostMapping("/blog/add")
    private String addCorrectBlog(@Valid BlogAddDto blogAddDto,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                  @AuthenticationPrincipal UserDetails userDetails){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("blogAddDto", blogAddDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.blogAddDto", bindingResult);
            return "redirect:/";
        }
        blogService.addBlog(blogAddDto, userDetails);

        return "redirect:/blog";

    }
    @ModelAttribute
    public BlogAddDto blogAddDto(){
        return new BlogAddDto();
    }
}
