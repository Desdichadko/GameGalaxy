package ru.pcs.web.gamegalaxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.pcs.web.gamegalaxy.services.CartService;

@Controller
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String getCartPage(Model model) {
        model.addAttribute("order", cartService.getCurrentOrderItems());
        model.addAttribute("totalSum", cartService.getActiveOrder().getTotalSum());
        return "cart";
    }

    @PostMapping("/add-to-cart")
    public String addItemToCart(String strGameId) {
        cartService.addItem(Long.parseLong(strGameId));
        return "redirect:/games";
    }

    @PostMapping("/cart/remove-item")
    public String removeItemFromCart(String strGameId) {
        cartService.removeItemFromOrder(Long.parseLong(strGameId));
        return "redirect:/cart";
    }

    @PostMapping("/cart/change-item-value")
    public String changeItemValue(@RequestParam("strGameId") String strGameId, @RequestParam("qtybutton") String quantity) {
        cartService.changeItemValueInOrder(Long.parseLong(strGameId), Integer.parseInt(quantity));
        return "redirect:/cart";
    }

    @GetMapping("/cart/payment")
    public String getPaymentPage() {
        cartService.payment();
        return "redirect:/games";
    }
}
