package com.rjpard.spring5recipeapp.service;

import com.rjpard.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    public Set<Recipe> getRecipes();

    public Recipe findById(Long id);
}
