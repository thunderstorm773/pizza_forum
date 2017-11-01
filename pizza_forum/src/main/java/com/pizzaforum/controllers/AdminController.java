package com.pizzaforum.controllers;

import com.pizzaforum.models.bindingModels.AddCategory;
import com.pizzaforum.models.bindingModels.EditCategory;
import com.pizzaforum.models.viewModels.CategoryView;
import com.pizzaforum.services.api.CategoryService;
import com.pizzaforum.staticData.Constants;
import com.pizzaforum.utils.ValidationUtil;
import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.parameters.ModelAttribute;
import mvcFramework.annotations.parameters.PathVariable;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.annotations.request.PostMapping;
import mvcFramework.model.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Controller
public class AdminController {

    private CategoryService categoryService;

    @Inject
    public AdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories/new")
    public String getCreateCategoryPage(Model model) {
        model.addAttribute(Constants.TITLE_KEY, Constants.CREATE_CATEGORY_TITLE_VALUE);
        model.addAttribute(Constants.VIEW_KEY, Constants.CREATE_CATEGORY_VIEW_VALUE);
        return "base-layout";
    }

    @PostMapping("/categories/new")
    public String createCategory(@ModelAttribute AddCategory addCategory,
                                 Model model) {
        ValidationUtil<AddCategory> validationUtil = new ValidationUtil<>(addCategory);
        List<String> errors = validationUtil.getInvalidParamsMessages();

        if (errors.isEmpty()) {
            boolean isCategorySuccessfullyCreated = this.categoryService.create(addCategory);
            if (!isCategorySuccessfullyCreated) {
                errors.add(Constants.CATEGORY_NAME_ALREADY_EXISTS_MESSAGE);
            }
        }

        if (!errors.isEmpty()) {
            model.addAttribute(Constants.TITLE_KEY, Constants.CREATE_CATEGORY_TITLE_VALUE);
            model.addAttribute(Constants.ERRORS_KEY, errors);
            model.addAttribute(Constants.VIEW_KEY, Constants.CREATE_CATEGORY_VIEW_VALUE);
            return "base-layout";
        }

        return "redirect:/categories/all";
    }

    @GetMapping("/categories/all")
    public String getAllCategoriesPage(Model model) {
        List<CategoryView> categoryViews = this.categoryService.findAll();
        model.addAttribute(Constants.CATEGORIES_KEY, categoryViews);
        model.addAttribute(Constants.TITLE_KEY, Constants.ALL_CATEGORIES_TITLE_VALUE);
        model.addAttribute(Constants.VIEW_KEY, Constants.ALL_CATEGORIES_VIEW_VALUE);
        return "base-layout";
    }

    @GetMapping("/categories/edit/{id}")
    public String getEditCategoryPage(Model model, @PathVariable("id") Long categoryId) {
        CategoryView categoryView = this.categoryService.findById(categoryId);
        if (categoryView == null) {
            return "redirect:/categories/all";
        }

        model.addAttribute(Constants.CATEGORY_KEY, categoryView);
        model.addAttribute(Constants.TITLE_KEY, Constants.EDIT_CATEGORY_TITLE_VALUE);
        model.addAttribute(Constants.VIEW_KEY, Constants.EDIT_CATEGORY_VIEW_VALUE);
        return "base-layout";
    }

    @PostMapping("/categories/edit/{id}")
    public String editCategory(@ModelAttribute EditCategory editCategory,
                               @PathVariable("id") Long categoryId,
                               Model model) {
        CategoryView categoryView = this.categoryService.findById(categoryId);
        if (categoryView == null) {
            return "redirect:/categories/all";
        }

        ValidationUtil<EditCategory> validationUtil = new ValidationUtil<>(editCategory);
        List<String> errors = validationUtil.getInvalidParamsMessages();
        if (errors.isEmpty()) {
            editCategory.setId(categoryId);
            boolean isCategorySuccessfullyEdited = this.categoryService.edit(editCategory);
            if (!isCategorySuccessfullyEdited) {
                errors.add(Constants.CATEGORY_NAME_ALREADY_EXISTS_MESSAGE);
            }
        }

        if (!errors.isEmpty()) {
            model.addAttribute(Constants.TITLE_KEY, Constants.EDIT_CATEGORY_TITLE_VALUE);
            model.addAttribute(Constants.ERRORS_KEY, errors);
            model.addAttribute(Constants.VIEW_KEY, Constants.EDIT_CATEGORY_VIEW_VALUE);
            return "base-layout";
        }

        return "redirect:/categories/all";
    }

    @GetMapping("/categories/delete/{id}")
    public String getDeleteCategoryPage(@PathVariable("id") Long categoryId,
                                        Model model) {
        CategoryView categoryView = this.categoryService.findById(categoryId);
        if (categoryView == null) {
            return "redirect:/categories/all";
        }

        model.addAttribute(Constants.CATEGORY_KEY, categoryView);
        model.addAttribute(Constants.TITLE_KEY, Constants.DELETE_CATEGORY_TITLE_VALUE);
        model.addAttribute(Constants.VIEW_KEY, Constants.DELETE_CATEGORY_VIEW_VALUE);
        return "base-layout";
    }

    @PostMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long categoryId) {
        this.categoryService.deleteById(categoryId);
        return "redirect:/categories/all";
    }
}
