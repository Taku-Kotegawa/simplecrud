package com.example.app.simplecrud;

import com.example.domain.model.simplecrud.Users;
import com.example.domain.model.simplecrud.UsersExample;
import com.example.domain.service.simplecrud.UsersService;
import com.github.dozermapper.core.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("users")
@Controller
public class UsersController {

    private static final Logger logger = LoggerFactory
            .getLogger(UsersController.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu/MM/dd hh:mm:ss");
    @Inject
    protected Mapper beanMapper;
    @Inject
    UsersService usersService;


    // ---------------- 一覧 -----------------------------------------------------

    @GetMapping("list")
    public String list(Model model) {

        List<UsersForm> usersFormList = new ArrayList<>();

        for (Users users : usersService.findAllByExample(new UsersExample())) {
            UsersForm usersForm = beanMapper.map(users, UsersForm.class);
            usersForm.setCreatedAtDisp(users.getCreatedAt().format(DATE_TIME_FORMATTER));
            usersForm.setChangedAtDisp(users.getChangedAt().format(DATE_TIME_FORMATTER));

            usersFormList.add(usersForm);
        }

        model.addAttribute("usersList", usersFormList);

        return "users/list";
    }

    // ---------------- 新規登録 -----------------------------------------------------

    @RequestMapping(value = "create", params = "form")
    public String createForm(UsersForm form, Model model) {
        return "users/createForm";
    }

    @RequestMapping(value = "create", params = "confirm", method = RequestMethod.POST)
    public String createConfirm(@Validated UsersForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return createRedo(form, model);
        }

        model.addAttribute("users", form);
        return "users/createConfirm";
    }

    @RequestMapping(value = "create", params = "redo", method = RequestMethod.POST)
    public String createRedo(UsersForm form, Model model) {
        return "users/createForm";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Validated UsersForm form, BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return createRedo(form, model);
        }

        Users users = beanMapper.map(form, Users.class);
        usersService.create(users);

        return "redirect:/users/create?complete";
    }

    @RequestMapping(value = "create", params = "complete")
    public String createComplete(Model model) {
        return "users/createComplete";
    }

    // ---------------- 編集 ---------------------------------------------------------

    @RequestMapping(value = "update", params = "form")
    public String updateForm(UsersForm form, Model model) {

        Users users = usersService.findOneByPrimaryKey(form.getUid());
        beanMapper.map(users, form);

        return "users/updateForm";
    }

    @RequestMapping(value = "update", params = "confirm", method = RequestMethod.POST)
    public String updateConfirm(@Validated UsersForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return updateRedo(form, model);
        }

        model.addAttribute("users", form);
        return "users/updateConfirm";
    }

    @RequestMapping(value = "update", params = "redo", method = RequestMethod.POST)
    public String updateRedo(UsersForm form, Model model) {
        return "users/updateForm";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Validated UsersForm form, BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return updateRedo(form, model);
        }

        Users users = beanMapper.map(form, Users.class);
        usersService.update(users);

        return "redirect:/users/update?complete";
    }

    @RequestMapping(value = "update", params = "complete")
    public String updateComplete(Model model) {
        return "users/updateComplete";
    }

    // ---------------- 削除 ---------------------------------------------------------

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@Validated UsersForm form, BindingResult result, Model model, RedirectAttributes redirect) {

        usersService.delete(form.getUid());

        return "redirect:/users/delete?complete";
    }

    @RequestMapping(value = "delete", params = "complete")
    public String deleteComplete(Model model) {
        return "users/deleteComplete";
    }

    // ---------------- 参照 ---------------------------------------------------------

    @RequestMapping(value = "detail")
    public String detail(UsersForm form, Model model) {

        Users users = usersService.findOneByPrimaryKey(form.getUid());

        model.addAttribute("users", users);

        return "users/detail";
    }

    // -------------------------------------------------------------------------

    @ModelAttribute
    public UsersForm setUpUsersForm() {
        return new UsersForm();
    }
}
