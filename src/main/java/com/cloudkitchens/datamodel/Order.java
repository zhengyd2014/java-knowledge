package com.cloudkitchens.datamodel;

public class Order {
    public String id;
    public String name;
    public OrderTemperature temp;
    public long shelf_life;
    public float decay_rate;
    public long created_time;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{")
                .append("id: ").append(id)
                .append(", name: ").append(name)
                .append(", temp: ").append(temp)
                .append(", shelf_life: ").append(shelf_life)
                .append(", decay_rate: ").append(decay_rate)
                .append("}");
        return sb.toString();
    }

    public float getValue(String shelfName) {
        long order_age = System.currentTimeMillis() - created_time;
        int shelf_decay_modifier = 1;
        if (shelfName.equals("overflow")) {
            shelf_decay_modifier = 2;
        }
        float value = (shelf_life - order_age - decay_rate * order_age * shelf_decay_modifier);
        return value / shelf_life;
    }
}
