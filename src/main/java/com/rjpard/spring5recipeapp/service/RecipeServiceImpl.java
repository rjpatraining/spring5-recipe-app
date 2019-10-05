package com.rjpard.spring5recipeapp.service;


import com.rjpard.spring5recipeapp.commands.RecipeCommand;
import com.rjpard.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.rjpard.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.rjpard.spring5recipeapp.domain.Recipe;
import com.rjpard.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private  final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    public Set<Recipe> getRecipes() {
        log.debug("In getRecipes method");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe Not Found!");
        }
        return recipeOptional.get();

    }

    @Override
    @Transactional
    public RecipeCommand saveRecipCommand(RecipeCommand command) {

        Recipe detachedRecipe = recipeCommandToRecipe(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved Recipe Id : " + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }
}
