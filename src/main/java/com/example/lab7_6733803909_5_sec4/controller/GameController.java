package com.example.lab7_6733803909_5_sec4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.lab7_6733803909_5_sec4.model.Game;
import com.example.lab7_6733803909_5_sec4.service.GameService;

@Controller
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String listGames(Model model) {
        model.addAttribute("games", gameService.getAllGames());
        return "games/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("game", new Game());
        return "games/add";
    }

    @PostMapping("/save")
public String saveGame(@ModelAttribute("game") Game game, RedirectAttributes redirectAttributes) {
    gameService.saveGame(game);
    redirectAttributes.addFlashAttribute("message", "เพิ่มเกม \"" + game.getTitle() + "\" สำเร็จ");
    return "redirect:/games";
}

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("game", gameService.getGameById(id));
        return "games/edit";
    }

   @PostMapping("/update/{id}")
public String updateGame(@PathVariable Long id, @ModelAttribute("game") Game game,
                          RedirectAttributes redirectAttributes) {
    gameService.updateGame(id, game);
    redirectAttributes.addFlashAttribute("message", "แก้ไขเกม \"" + game.getTitle() + "\" สำเร็จ");
    return "redirect:/games";
}

    @GetMapping("/delete/{id}")
    public String showDeleteConfirm(@PathVariable Long id, Model model) {
        model.addAttribute("game", gameService.getGameById(id));
        return "games/delete";
    }

   @PostMapping("/delete/{id}")
public String deleteGame(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    gameService.deleteGame(id);
    redirectAttributes.addFlashAttribute("message", "ลบเกมสำเร็จ");
    return "redirect:/games";
}

}
