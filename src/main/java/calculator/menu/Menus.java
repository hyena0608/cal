package calculator.menu;

import calculator.view.input.MenuInput;
import calculator.view.output.MenuOutput;

import java.util.Arrays;
import java.util.Objects;

import static calculator.exception.MenuException.MENU_FIND_NULL_EXCEPTION;

public enum Menus {

    MENU_HISTORY_FINDER(1L, "조회", new MenuHistoryFinder()),
    MENU_CALCULATOR(2L,"계산", new MenuCalculator());

    private static final String a= "";
    private final Long id;
    private final String title;
    private final Menu menu;

    Menus(Long id, String title, Menu menu) {
        this.id = id;
        this.title = title;
        this.menu = menu;
    }

    private static MenuInput menuInput() {
        return new MenuInput();
    }

    private static MenuOutput menuOutput() {
        return new MenuOutput();
    }

    public static void process() {
        menuOutput().printMenus();
        Long menuId = menuInput().askMenuId();
        Menu findMenu = findMenuById(menuId);

        findMenu.process();
    }

    private static Menu findMenuById(Long id) {
        return Arrays.stream(Menus.values())
                .filter(menu -> Objects.equals(menu.id, id))
                .findFirst()
                .orElseThrow(() -> new NullPointerException(MENU_FIND_NULL_EXCEPTION.message))
                .menu;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}