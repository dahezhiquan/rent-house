package view;

import domain.House;
import service.HouseService;
import utils.Utility;

/**
 * 1.显示界面
 * 2.接受用户的输入信息
 * 3.调用HouseService完成对房屋信息的各种操作
 */
public class HouseView {
    // 显示主菜单
    private boolean loop = true; // 控制显示菜单
    private char key = ' '; // 接受用户的菜单选择

    // 设置房屋数组的默认大小为10
    private HouseService houseService = new HouseService(10);

    /**
     * 主菜单
     */
    public void mainMenu() {
        do {
            System.out.println("===============房屋出租系统===============");
            System.out.println("\t\t\t1 新 增 房 源🏠");
            System.out.println("\t\t\t2 查 找 房 屋🏘");
            System.out.println("\t\t\t3 删 除 房 屋 信 息❌");
            System.out.println("\t\t\t4 修 改 房 屋 信 息✍");
            System.out.println("\t\t\t5 显 示 房 屋 信 息👆");
            System.out.println("\t\t\t6 退 出👇");
            System.out.println("请输入你的选择（1 - 6）：");
            key = Utility.readChar(); // 接受一个用户输入
            switch (key) {
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    updateHouse();
                    break;
                case '5':
                    listHouses();
                    break;
                case '6':
                    exit();
                    break;
                default:
                    System.out.println("参数错误！请重新输入！");
            }
        } while (loop);
    }

    /**
     * 显示房屋列表
     */
    public void listHouses() {
        System.out.println("=========================房屋列表==============================");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态（未出租/出租）");
        House[] houses = houseService.list(); // 得到所有房屋的信息
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == null) { // 房屋信息为空则不用输出到列表
                break;
            }
            System.out.println(houses[i]);
        }
        System.out.println("=========================房屋列表显示完毕=======================\n");
    }

    /**
     * 添加房屋
     */
    public void addHouse() {
        System.out.println("=========================添加房屋=========================");
        System.out.println("姓名：");
        String name = Utility.readString(8);
        System.out.println("电话：");
        String phone = Utility.readString(12);
        System.out.println("地址：");
        String address = Utility.readString(16);
        System.out.println("月租：");
        int rent = Utility.readInt();
        System.out.println("状态：");
        String state = Utility.readString(3);
        House newHouse = new House(0, name, phone, address, rent, state);
        if (houseService.add(newHouse)) {
            System.out.println("=========================添加房屋成功======================");
        } else {
            System.out.println("=========================添加房屋失败======================");
        }
    }

    /**
     * 删除房屋
     */
    public void delHouse() {
        System.out.println("=========================删除房屋信息=========================");
        System.out.println("请输入待删除房屋的编号（-1退出）:");
        int delId = Utility.readInt();
        if (delId == -1) {
            System.out.println("放弃删除房屋信息");
            return;
        }
        char chocie = Utility.readConfirmSelection(); // 提示用户输入Y（确认删除）N（放弃删除）
        if (chocie == 'Y') {
            if (houseService.del(delId)) {
                System.out.println("=========================删除房屋信息成功======================");
            } else {
                System.out.println("=========================删除房屋信息失败======================");
            }
        } else {
            System.out.println("放弃删除房屋信息");
        }
    }

    /**
     * 退出确认
     */
    public void exit() {
        char c = Utility.readConfirmSelection();
        if (c == 'Y') {
            loop = false;
        }
    }

    /**
     * 查找房源
     */
    public void findHouse() {
        System.out.println("=========================查找房屋信息=========================");
        System.out.println("请输入要查找的id：");
        int findId = Utility.readInt();
        House house = houseService.findById(findId);
        if (house != null) {
            System.out.println(house);
        } else {
            System.out.println("=========================查找房屋信息失败=========================");
        }
    }

    /**
     * 修改房屋
     */
    public void updateHouse() {
        System.out.println("=========================修改房屋信息=========================");
        System.out.println("请选择待修改房屋信息编号（-1表示退出）：");
        int updateId = Utility.readInt();
        if (updateId == -1) {
            System.out.println("放弃修改房屋信息");
            return;
        }
        // 根据输入的updateId查找对象
        House house = houseService.findById(updateId);
        if (house == null) {
            System.out.println("=========================修改的房屋不存在=========================");
            return;
        }
        System.out.println("姓名（" + house.getName() + "）：");
        String name = Utility.readString(8, "");
        if (!"".equals(name)) { // 待修改的信息不为空串，则进行更新操作
            house.setName(name);
        }
        System.out.println("电话（" + house.getPhone() + "）：");
        String phone = Utility.readString(12, "");
        if (!"".equals(phone)) {
            house.setPhone(phone);
        }
        System.out.println("地址（" + house.getAddress() + "）：");
        String address = Utility.readString(18, "");
        if (!"".equals(address)) {
            house.setAddress(address);
        }
        System.out.println("月租（" + house.getRent() + "）：");
        int rent = Utility.readInt(-1);
        if (rent != -1) { // 待修改的信息不为空串，则进行更新操作
            house.setRent(rent);
        }
        System.out.println("状态（" + house.getState() + "）：");
        String state = Utility.readString(3, "");
        if (!"".equals(state)) { // 待修改的信息不为空串，则进行更新操作
            house.setState(state);
        }
    }
}
