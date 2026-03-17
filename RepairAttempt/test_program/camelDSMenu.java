package org.firstinspires.ftc.teamcode.camel;

import android.annotation.SuppressLint;

public class camelDSMenu {
    public static final int TYPE_NONE = 0;
    public static final int TYPE_INT = 1;
    public static final int TYPE_DOUBLE = 2;

    private static class Items {
        public String name;
        public int type;
        public int value;
        public int increment_value;
        boolean have_high_limit=false, have_low_limit=false;
        int low_limit, high_limit;
    }

    Items[] items = new Items[20];
    private int num_items, selected_item, current_menu;

    public camelDSMenu() {
        num_items = 0;
        selected_item = 0;
        current_menu = 0;
    }

    public void addItem(String item) {
        items[num_items] = new Items();
        items[num_items].name = item;
        items[num_items].type = TYPE_NONE;
        num_items++;
    }

    public void addItem(String item, double value, double increment) {
        items[num_items] = new Items();
        items[num_items].name = item;
        items[num_items].type = TYPE_DOUBLE;
        items[num_items].value = (int) (value * 1000);
        items[num_items].increment_value = (int) (increment * 1000);
        num_items++;
    }

    public void addItem(String item, int value, int increment) {
        items[num_items] = new Items();
        items[num_items].name = item;
        items[num_items].type = TYPE_INT;
        items[num_items].value = value;
        items[num_items].increment_value = increment;
        num_items++;
    }

    public void addItem(String item, double value, double increment,double low_limit, double high_limit) {
        items[num_items] = new Items();
        items[num_items].name = item;
        items[num_items].type = TYPE_DOUBLE;
        items[num_items].value = (int) (value * 1000);
        items[num_items].increment_value = (int) (increment * 1000);
        items[num_items].have_low_limit=true;items[num_items].have_high_limit=true;
        items[num_items].low_limit=(int)(low_limit*1000);items[num_items].high_limit=(int)(high_limit*1000);
        num_items++;
    }

    public void addItem(String item, int value, int increment,int low_limit, int high_limit) {
        items[num_items] = new Items();
        items[num_items].name = item;
        items[num_items].type = TYPE_INT;
        items[num_items].value = value;
        items[num_items].increment_value = increment;
        items[num_items].have_low_limit=true;items[num_items].have_high_limit=true;
        items[num_items].low_limit=low_limit;items[num_items].high_limit=high_limit;
        num_items++;
    }

    public double getValueDouble() {
        if (items[selected_item].type == TYPE_NONE) return 0;
        if (items[selected_item].type == TYPE_DOUBLE)
            return (double) items[selected_item].value / 1000;
        return items[selected_item].value;
    }

    public double getValueDouble(int idx) {
        if (items[idx].type == TYPE_NONE) return 0;
        if (items[idx].type == TYPE_DOUBLE)
            return (double) items[idx].value / 1000;
        return items[idx].value;
    }

    public int getValueInt() {
        if (items[selected_item].type == TYPE_NONE) return 0;
        if (items[selected_item].type == TYPE_DOUBLE)
            return (int) (items[selected_item].value / 1000);
        return items[selected_item].value;
    }

    public int getValueInt(int idx) {
        if (items[idx].type == TYPE_NONE) return 0;
        if (items[idx].type == TYPE_DOUBLE)
            return (int) (items[idx].value / 1000);
        return items[idx].value;
    }

    public void increment() {
        if (items[selected_item].type == TYPE_INT || items[selected_item].type == TYPE_DOUBLE) {
            items[selected_item].value += items[selected_item].increment_value;
            if (items[selected_item].have_high_limit && items[selected_item].value > items[selected_item].high_limit)
                items[selected_item].value = items[selected_item].high_limit;
        }
    }

    public void decrement() {
        if (items[selected_item].type == TYPE_INT || items[selected_item].type == TYPE_DOUBLE) {
            items[selected_item].value -= items[selected_item].increment_value;
            if (items[selected_item].have_low_limit && items[selected_item].value < items[selected_item].low_limit)
                items[selected_item].value = items[selected_item].low_limit;
        }
    }

    public void selectNext() {
        selected_item++;
        if (selected_item >= num_items) selected_item = 0;
    }

    public void selectPrev() {
        selected_item--;
        if (selected_item < 0) selected_item = num_items - 1;
    }

    public int getSelected() {
        return selected_item;
    }

    public int getMenuNumber() {
        return current_menu;
    }
    public void setMenuNumber(int number) {current_menu=number; }
    public void clear() {num_items=0;selected_item=0;}

    @SuppressLint("DefaultLocale")
    public String getString() {
        String s = "";
        for (int f = 0; f < num_items; f++) {
            if (f == selected_item) s += ">";
            else s += " ";
            s += items[f].name;
            if (items[f].type == TYPE_INT) s += " " + items[f].value;
            if (items[f].type == TYPE_DOUBLE)
                s += " " + String.format("%.3f", (double) items[f].value / 1000);
            s += "\n";
        }
        return s;
    }

    public void loadItems(int menu_number) {
        current_menu = menu_number;
        num_items = 0;
        selected_item = 0;
        if (menu_number == 0) {
            addItem("Switch to COMPETITION mode");
            addItem("Motion test");
            addItem("Component test");
            addItem("Procedure test");
            addItem("Coregraphy test");
        }

        if (menu_number == 1) {
            //motion test menu
            addItem("<Go back>");
            addItem("move forward", 300, 50);
            addItem("move backward", 300, 50);
            addItem("strafe left", 300, 50);
            addItem("strafe right", 300, 50);
            addItem("rotate left", 30, 1);
            addItem("rotate right", 30, 1);
            addItem("rotate to absolute 0");
            addItem("move arc, dist=1m, left", 30, 1);
            addItem("autofix");
        }
        if (menu_number == 2) {
            //hardware test menu
            addItem("<Go back>");
            addItem("FL wheel", 0, 100);
            addItem("FR wheel", 0, 100);
            addItem("RL wheel", 0, 100);
            addItem("RL wheel", 0, 100);
            addItem("Intake", 0, 0.1);
            addItem("Transport", 0, 0.1);
            addItem("L eject", 0, 100);
            addItem("R eject", 0, 100);
            addItem("Shooter angle servo", 0.35, 0.05);
            addItem("Eject blocker servo", 0, 0.05);
        }
        if (menu_number == 3) {
            //procedure test menu
            addItem("<Go back>");
            addItem("Run eject continuously");
            addItem("Eject 3 balls");
            addItem("Find shooting angle");
            addItem("Grab 3 balls from field");
        }
    }
}
