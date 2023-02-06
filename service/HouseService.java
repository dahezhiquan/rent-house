package service;

import domain.House;

/**
 * 1.响应HouseView的调用
 * 2.完成对房屋信息的CRUD
 */
public class HouseService {
    private House[] houses; // 房屋数组

    private int houseNums = 1; // 记录当前有多少个房屋信息

    private int idCounter = 1; // 记录当前的房屋ID

    /**
     * @param size 创建数组时指定的数组大小
     */
    public HouseService(int size) {
        houses = new House[size];
        houses[0] = new House(1, "dahe", "112", "重庆", 2000, "未出租");
    }

    /**
     * 返回House列表
     */
    public House[] list() {
        return houses;
    }

    /**
     * 添加新对象，返回boolean值
     */
    public boolean add(House newHouse) {
        // 判断是否还可以继续添加
        if (houseNums == houses.length) {
            System.out.println("租位已满，不能继续添加了哦~");
            return false;
        }
        houses[houseNums++] = newHouse;
        // 房屋ID自增长机制，更新newHouse对象的ID信息
        newHouse.setId(++idCounter);
        return true;
    }

    /**
     * 删除一个房屋信息
     */
    public boolean del(int delId) {
        // 查找待删除的房屋在数组中的下标
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if (delId == houses[i].getId()) {
                // 找到待删除的房屋，它的下标是i
                index = i;
                break;
            }
        }
        if (index == -1) {
            // delId在数组中不存在
            return false;
        }
        // 删除了一个房屋对象，该房屋后面的房屋整体前移
        for (int i = index; i < houseNums - 1; i++) {
            houses[i] = houses[i + 1];
        }
        houses[--houseNums] = null; // 空出的最后的位置置空
        return true;
    }

    /**
     * 根据ID查询房屋信息
     */
    public House findById(int findId) {
        for (int i = 0; i < houseNums; i++) {
            if (findId == houses[i].getId()) {
                return houses[i];
            }
        }
        return null;
    }


}
