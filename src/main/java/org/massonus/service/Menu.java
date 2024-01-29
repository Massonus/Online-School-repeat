package org.massonus.service;

import lombok.Getter;
import org.massonus.entity.MenuItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class Menu {

    private final List<MenuItem> menuItems = new ArrayList<>();
    {
        menuItems.add(new MenuItem("/OnlineSchool_repeat_war_exploded/", "Main"));
        menuItems.add(new MenuItem("/OnlineSchool_repeat_war_exploded/all-courses", "All courses"));
        menuItems.add(new MenuItem("/OnlineSchool_repeat_war_exploded/all-materials", "All materials"));
        menuItems.add(new MenuItem("/OnlineSchool_repeat_war_exploded/all-homework", "All homework"));
        menuItems.add(new MenuItem("/OnlineSchool_repeat_war_exploded/all-lectures", "All Lectures"));
        menuItems.add(new MenuItem("/OnlineSchool_repeat_war_exploded/all-people", "All people"));
    }

}
