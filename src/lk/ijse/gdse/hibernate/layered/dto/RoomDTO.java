package lk.ijse.gdse.hibernate.layered.dto;

import lk.ijse.gdse.hibernate.layered.entity.Room;

public class RoomDTO {
    private long id;

    private String type;

    private String keyMoney;

    private int qty;

    public RoomDTO() {
    }

    public RoomDTO(long id, String type, String keyMoney, int qty) {
        this.id = id;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(String keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", keyMoney='" + keyMoney + '\'' +
                ", qty=" + qty +
                '}';
    }
    public Room toEnity(){
        Room room = new Room();
        room.setId(this.id);
        room.setType(this.type);
        room.setKeyMoney(this.keyMoney);
        room.setQty(this.qty);
        return room;
    }
}
